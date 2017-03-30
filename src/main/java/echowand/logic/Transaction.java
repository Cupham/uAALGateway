package echowand.logic;

import java.util.*;
import java.util.logging.Logger;

import echowand.common.EOJ;
import echowand.common.ESV;
import echowand.net.*;

public class Transaction {
    private static final Logger logger = Logger.getLogger(Transaction.class.getName());
    private static final String className = Transaction.class.getName();
    
    private TransactionConfig transactionConfig;
    private static final int DEFAULT_TIMEOUT = 60;
    private static short nextTID;
    
    static {
        nextTID = 1;
    }
    
    private Subnet subnet;
    private TransactionManager transactionManager;
    private int timeout;
    private short tid;
    
    private Timer timer;
    private boolean done;
    private boolean waiting;
    private int countResponse;
    
    private LinkedList<TransactionListener> transactionListeners;
    
    private static EnumMap<ESV, LinkedList<ESV>> responseESVMap = new EnumMap<ESV, LinkedList<ESV>>(ESV.class);
    
    private static short getNextTID() {
        if (nextTID == 0) {
            nextTID = 1;
        }
        return nextTID++;
    }

    public Transaction(Subnet subnet, TransactionManager transactionManager, TransactionConfig transactionConfig) {
        logger.entering(className, "Transaction", new Object[]{subnet, transactionManager, transactionConfig});
        
        this.subnet = subnet;
        this.transactionManager = transactionManager;
        this.transactionConfig = transactionConfig;
        this.tid = getNextTID();
        this.done = false;
        this.countResponse = 0;
        this.timeout = DEFAULT_TIMEOUT;
        this.transactionListeners = new LinkedList<TransactionListener>();
        initResponseESVMap();
        
        logger.exiting(className, "Transaction");
    }
    
    private void initResponseESVMap() {
        if (responseESVMap.isEmpty()) {
            addResponseESVMap(ESV.SetC, ESV.Set_Res);
            addResponseESVMap(ESV.SetC, ESV.SetC_SNA);
            addResponseESVMap(ESV.SetI, ESV.SetI_SNA);
            addResponseESVMap(ESV.Get, ESV.Get_Res);
            addResponseESVMap(ESV.Get, ESV.Get_SNA);
            addResponseESVMap(ESV.SetGet, ESV.SetGet_Res);
            addResponseESVMap(ESV.SetGet, ESV.SetGet_SNA);
            addResponseESVMap(ESV.INF_REQ, ESV.INF);
            addResponseESVMap(ESV.INF_REQ, ESV.INF_SNA);
            addResponseESVMap(ESV.INF, ESV.INF_SNA);
            addResponseESVMap(ESV.INFC, ESV.INFC_Res);
            addResponseESVMap(ESV.INFC, ESV.INF_SNA);
        }
    }
    
    protected void addResponseESVMap(ESV req, ESV res) {
        LinkedList<ESV> esvs;
        if (responseESVMap.containsKey(req)) {
            esvs = responseESVMap.get(req);
        } else {
            esvs = new LinkedList<ESV>();
        }
        esvs.add(res);
        responseESVMap.put(req, esvs);
    }
    
    public TransactionConfig getTransactionConfig() {
        return transactionConfig;
    }
    
    public synchronized void addTransactionListener(TransactionListener listener) {
        logger.entering(className, "addTransactionListener", listener);
        
        transactionListeners.add(listener);
        
        logger.exiting(className, "addTransactionListener");
    }
    
    public synchronized void removeTransactionListener(TransactionListener listener) {
        logger.entering(className, "removeTransactionListener", listener);
        
        transactionListeners.remove(listener);
        
        logger.exiting(className, "removeTransactionListener");
    }
    
    private synchronized List<TransactionListener> cloneTransactionListeners() {
        return new ArrayList<TransactionListener>(transactionListeners);
    }
    
    private void doCallBeginTransactionListeners() {
        logger.entering(className, "doCallBeginTransactionListeners");
        
        for (TransactionListener l : cloneTransactionListeners()) {
            l.begin(this);
        }
        
        logger.exiting(className, "doCallBeginTransactionListeners");
    }
    
    private void doCallSentTransactionListeners(Frame frame, boolean success) {
        logger.entering(className, "doCallSentTransactionListeners", frame);
        
        for (TransactionListener l : cloneTransactionListeners()) {
            l.send(this, subnet, frame, success);
        }
        
        logger.exiting(className, "doCallSentTransactionListeners");
    }
    
    private void doCallReceiveTransactionListeners(Frame frame) {
        logger.entering(className, "doCallReceiveTransactionListeners", frame);
        
        for (TransactionListener l : cloneTransactionListeners()) {
            l.receive(this, subnet, frame);
        }
        
        logger.exiting(className, "doCallReceiveTransactionListeners");
    }
    
    private void doCallFinishTransactionListeners() {
        logger.entering(className, "doCallFinishTransactionListeners");
        
        for (TransactionListener l : cloneTransactionListeners()) {
            l.finish(this);
        }
        
        logger.exiting(className, "doCallFinishTransactionListeners");
    }
    
    public synchronized int countTransactionListeners() {
        logger.entering(className, "countTransactionListeners", timeout);
        
        int count = transactionListeners.size();
        
        logger.exiting(className, "countTransactionListeners", count);
        return count;
    }
    
    public synchronized void setTimeout(int timeout) {
        logger.entering(className, "setTimeout", timeout);
        
        this.timeout = timeout;
        
        logger.exiting(className, "setTimeout");
    }
    
    public synchronized int getTimeout() {
        return timeout;
    }
    
    public short getTID() {
        return tid;
    }
    
    private StandardPayload createPayload(int index) {
        logger.entering(className, "createPayload", index);
        
        StandardPayload payload = new StandardPayload();
        payload.setDEOJ(transactionConfig.getDestinationEOJ());
        payload.setSEOJ(transactionConfig.getSourceEOJ());
        
        payload.setESV(transactionConfig.getESV());
        
        transactionConfig.addPayloadProperties(index, payload);
        
        logger.exiting(className, "createPayload", payload);
        return payload;
    }
    
    private boolean sendRequest() throws SubnetException {
        logger.entering(className, "sendRequest");
        
        int count = transactionConfig.getCountPayloads();
        
        if (count == 0) {
            logger.exiting(className, "sendRequest", false);
            return false;
        }
        
        boolean result = true;
        
        for (int i = 0; i < count; i++) {
            StandardPayload payload = createPayload(i);

            CommonFrame cf = new CommonFrame();
            cf.setEDATA(payload);
            cf.setTID(tid);
            Frame frame = new Frame(transactionConfig.getSenderNode(), transactionConfig.getReceiverNode(), cf);
            boolean success = subnet.send(frame);
            
            doCallSentTransactionListeners(frame, success);
            
            result &= success;
        }
        
        logger.exiting(className, "sendRequest", result);
        return result;
    }
    
    private boolean isValidTransactionESVPair(ESV req, ESV res) {
        logger.entering(className, "isValidTransactionESVPair", new Object[]{req, res});

        LinkedList<ESV> esvs = responseESVMap.get(req);
        boolean valid = false;
        if (esvs != null) {
            valid = esvs.contains(res);
        }

        logger.exiting(className, "isValidTransactionESVPair", valid);
        return valid;
    }

    public synchronized boolean receiveResponse(Frame frame) {
    	
        logger.entering(className, "receiveResponse");
        
        if (!this.waiting) {
            logger.exiting(className, "receiveResponse", false);
            return false;
        }
        
        if (!frame.getCommonFrame().isStandardPayload()) {
            logger.exiting(className, "receiveResponse", false);
            return false;
        }
        
        CommonFrame cf = frame.getCommonFrame();
        
        if (cf.getTID() != this.getTID()) {
            logger.exiting(className, "receiveResponse", false);
            return false;
        }
        
        StandardPayload payload = cf.getEDATA(StandardPayload.class);
        
        if (payload == null) {
            logger.exiting(className, "receiveResponse", false);
            return false;
        }
        
        EOJ responseSEOJ = payload.getSEOJ();
        EOJ responseDEOJ = payload.getDEOJ();
        EOJ requestDEOJ = transactionConfig.getDestinationEOJ();
        EOJ requestSEOJ = transactionConfig.getSourceEOJ();
        
        if (!responseSEOJ.equals(requestDEOJ)) {
            if (!requestDEOJ.isAllInstance()) {
                logger.exiting(className, "receiveResponse", false);
                return false;
            }

            if (!responseSEOJ.getClassEOJ().equals(requestDEOJ.getClassEOJ())) {
                logger.exiting(className, "receiveResponse", false);
                return false;
            }
        }
        if (!responseDEOJ.equals(requestSEOJ)) {
            logger.exiting(className, "receiveResponse", false);
            return false;
        }

        ESV reqESV = transactionConfig.getESV();
        ESV resESV = payload.getESV();
        
        if (!isValidTransactionESVPair(reqESV, resESV)) {
            logger.exiting(className, "receiveResponse", false);
            return false;
        }
        
        this.countResponse++;
        
        doCallReceiveTransactionListeners(frame);
        
        logger.exiting(className, "receiveResponse", true);
        return true;
    }
    
    public synchronized void finish() {
        logger.entering(className, "finish");
        
        if (!this.done) {
            this.waiting = false;
            this.done = true;

            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        
            transactionManager.removeTransaction(this);
            
            doCallFinishTransactionListeners();
            
            notifyAll();
        }
        
        logger.exiting(className, "finish");
    }
    
    private static class TimeoutTimerTask extends TimerTask {
        public Transaction t;
        public TimeoutTimerTask(Transaction t) {
            this.t = t;
        }

        @Override
        public void run() {
            logger.entering(className, "TimeoutTimerTask.run");
            t.finish();
            logger.exiting(className, "TimeoutTimerTask.run");
        }
    }

    public synchronized void execute() throws SubnetException {
        logger.entering(className, "execute");
        
        if (this.waiting || this.done) {
            logger.exiting(className, "execute");
            return;
        }
        
        doCallBeginTransactionListeners();

        this.waiting = true;
        
        transactionManager.addTransaction(this);
        
        sendRequest();
        
        int timeout = getTimeout();
        
        if (timeout == 0) {
            finish();
        } else if (timeout > 0) {
            timer = new Timer(true);
            timer.schedule(new TimeoutTimerTask(this), timeout);
        }

        logger.exiting(className, "execute");
    }

    public synchronized void join() throws InterruptedException {
        logger.entering(className, "join");
        
        while (isWaitingResponse()) {
            wait();
        }
        
        logger.exiting(className, "join");
    }
    
    public synchronized int countResponses() {
        logger.entering(className, "countResponses");
        
        int count = this.countResponse;
        
        logger.exiting(className, "countResponses", count);
        return count;
    }
    
    public synchronized boolean isWaitingResponse() {
        return waiting;
    }

    public synchronized boolean isDone() {
        return done;
    }
}
