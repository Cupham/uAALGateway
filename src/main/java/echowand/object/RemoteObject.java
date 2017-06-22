package echowand.object;

import java.util.LinkedList;
import java.util.logging.Logger;

import echowand.common.Data;
import echowand.common.EOJ;
import echowand.common.EPC;
import echowand.common.PropertyMap;
import echowand.logic.SetGetTransactionConfig;
import echowand.logic.Transaction;
import echowand.logic.TransactionListener;
import echowand.logic.TransactionManager;
import echowand.net.*;

public class RemoteObject implements EchonetObject {
    private static final Logger logger = Logger.getLogger(RemoteObject.class.getName());
    private static final String className = RemoteObject.class.getName();

    public static final int TRANSACTION_TIMEOUT = 5000;

    public static final EOJ SOURCE_EOJ = new EOJ("0ef001");

    public static final EPC GET_PROPERTYMAP_EPC = EPC.x9F;

    public static final EPC SET_PROPERTYMAP_EPC = EPC.x9E;

    public static final EPC ANNOUNCE_PROPERTYMAP_EPC = EPC.x9D;
    
    private TransactionManager transactionManager;
    private Subnet subnet;
    private Node node;
    private EOJ eoj;
    private int timeout;
    
    private LinkedList<RemoteObjectObserver> observers;

    public RemoteObject(Subnet subnet, Node node, EOJ eoj, TransactionManager transactionManager) {
        logger.entering(className, "RemoteObject", new Object[]{subnet, node, eoj, transactionManager});
        
        this.subnet = subnet;
        this.node = node;
        this.eoj = eoj;
        this.transactionManager = transactionManager;
        this.observers = new LinkedList<RemoteObjectObserver>();
        this.timeout = TRANSACTION_TIMEOUT;
        
        logger.entering(className, "RemoteObject");
    }
    
    private synchronized LinkedList<RemoteObjectObserver> cloneObservers() {
        return new LinkedList<RemoteObjectObserver>(observers);
    }

    public TransactionManager getListener() {
        return transactionManager;
    }

    public Subnet getSubnet() {
        return subnet;
    }

    public Node getNode() {
        return node;
    }

    @Override
    public EOJ getEOJ() {
        return eoj;
    }

    public boolean setTimeout(int timeout) {
        logger.entering(className, "setTimeout", timeout);
        
        if (timeout > 0) {
            this.timeout = timeout;
            logger.exiting(className, "setTimeout", true);
            return true;
        } else {
            logger.exiting(className, "setTimeout", false);
            return false;
        }
    }

    public int getTimeout() {
        logger.entering(className, "getTimeout");
        
        int timeout = this.timeout;
        logger.exiting(className, "getTimeout", timeout);
        return timeout;
    }
    
    private boolean isValidFrame(Frame frame) {
        logger.entering(className, "isValidFrame", frame);

        if (!frame.getSender().equals(node)) {
            logger.exiting(className, "isValidFrame", false);
            return false;
        }

        CommonFrame cf = frame.getCommonFrame();
        StandardPayload payload = cf.getEDATA(StandardPayload.class);
        
        if (payload == null) {
            logger.exiting(className, "isValidFrame", false);
            return false;
        }
        
        if (!payload.getSEOJ().equals(eoj)) {
            logger.exiting(className, "isValidFrame", false);
            return false;
        }

        logger.exiting(className, "isValidFrame", true);
        return true;
    }

    private Property getValidFirstProperty(Frame frame, EPC epc) {
        logger.entering(className, "getValidFirstProperty", new Object[]{frame, epc});
        
        if (!isValidFrame(frame)) {
            logger.exiting(className, "getValidFirstProperty", null);
            return null;
        }
        CommonFrame cf = frame.getCommonFrame();
        StandardPayload payload = cf.getEDATA(StandardPayload.class);
        
        if (payload == null) {
            logger.exiting(className, "getValidFirstProperty", null);
            return null;
        }
        
        if (payload.getFirstOPC() != 1) {
            logger.exiting(className, "getValidFirstProperty", null);
            return null;
        }
        Property property = payload.getFirstPropertyAt(0);
        if (property.getEPC() == epc) {
            logger.exiting(className, "getValidFirstProperty", property);
            return property;
        }
        
        logger.exiting(className, "getValidFirstProperty", null);
        return null;
    }

    class RemoteObjectGetTransactionListener implements TransactionListener {

        private EPC epc;
        private ObjectData data;
        private LinkedList<Data> dataList;

        public RemoteObjectGetTransactionListener(EPC epc) {
            this.epc = epc;
        }

        public ObjectData getData() {
            return data;
        }
        
        @Override
        public void begin(Transaction t) {
            logger.entering(className, "RemoteObjectGetTransactionListener.begin", t);
            
            dataList = new LinkedList<Data>();
            
            logger.exiting(className, "RemoteObjectGetTransactionListener.begin");
        }
        
        @Override
        public void send(Transaction t, Subnet subnet, Frame frame, boolean success) {
        }

        @Override
        public void receive(Transaction t, Subnet subnet, Frame frame) {
            logger.entering(className, "RemoteObjectGetTransactionListener.receive", new Object[]{t, subnet, frame});
            
            Property property = getValidFirstProperty(frame, this.epc);
            if (property != null) {
                if (property.getPDC() != 0) {
                    dataList.add(property.getEDT());
                    t.finish();
                }
            }
            
            logger.exiting(className, "RemoteObjectGetTransactionListener.receive");
        }

        @Override
        public void finish(Transaction t) {
            logger.entering(className, "RemoteObjectGetTransactionListener.finish", t);
            
            if (!dataList.isEmpty()) {
                data = new ObjectData(dataList);
            }
            dataList = null;
            
            logger.exiting(className, "RemoteObjectGetTransactionListener.finish");
        }
    }
    
    class RemoteObjectSetTransactionListener implements TransactionListener {
        private EPC epc;
        private boolean success;
        
        public RemoteObjectSetTransactionListener(EPC epc) {
            this.epc = epc;
            this.success = false;
        }
        
        public boolean isSuccess() {
            return success;
        }
        
        @Override
        public void begin(Transaction t) {
        }

        @Override
        public void send(Transaction t, Subnet subnet, Frame frame, boolean success) {
        }

        @Override
        public void receive(Transaction t, Subnet subnet, Frame frame) {
            logger.entering(className, "RemoteObjectSetTransactionListener.receive", new Object[]{t, subnet, frame});
            
            Property property = getValidFirstProperty(frame, this.epc);
            if (property != null) {
                success = (property.getPDC() == 0);
                t.finish();
            }
            
            logger.exiting(className, "RemoteObjectSetTransactionListener.receive");
        }

        @Override
        public void finish(Transaction t) {
        }
    }
    
    private SetGetTransactionConfig createSetGetTransactionConfig() {
        logger.entering(className, "createSetGetTransactionConfig");
        
        SetGetTransactionConfig transactionConfig = new SetGetTransactionConfig();
        transactionConfig.setResponseRequired(true);
        transactionConfig.setSenderNode(subnet.getLocalNode());
        transactionConfig.setReceiverNode(this.getNode());
        transactionConfig.setSourceEOJ(SOURCE_EOJ);
        transactionConfig.setDestinationEOJ(eoj);
        
        logger.exiting(className, "createSetGetTransactionConfig", transactionConfig);
        return transactionConfig;
    }
    
    private Transaction createSetGetTransaction(SetGetTransactionConfig transactionConfig, TransactionListener transactionListener) {
        logger.entering(className, "createSetGetTransaction", new Object[]{transactionConfig, transactionListener});
        
        Transaction transaction = transactionManager.createTransaction(transactionConfig);
        transaction.setTimeout(timeout);
        transaction.addTransactionListener(transactionListener);
        
        logger.exiting(className, "createSetGetTransaction", transaction);
        return transaction;
    }

    @Override
    public ObjectData getData(EPC epc) throws EchonetObjectException {
        logger.entering(className, "getData", epc);
        
        RemoteObjectGetTransactionListener transactionListener;

        SetGetTransactionConfig transactionConfig = createSetGetTransactionConfig();
        transactionConfig.addGet(epc);

        transactionListener = new RemoteObjectGetTransactionListener(epc);
        Transaction transaction = createSetGetTransaction(transactionConfig, transactionListener);
        
        try {
            transaction.execute();
        } catch (SubnetException e) {
            EchonetObjectException exception = new EchonetObjectException("getData failed", e);
            logger.throwing(className, "getData", exception);
            throw exception;
        }
        
        try {
            transaction.join();
        } catch (InterruptedException e) {
            EchonetObjectException exception = new EchonetObjectException("interrupted", e);
            logger.throwing(className, "getData", exception);
            throw exception;
        }
        
        if (transaction.countResponses() == 0) {
            EchonetObjectException exception = new EchonetObjectException("no response");
            logger.throwing(className, "getData", exception);
            throw exception;
        }
        
        ObjectData data =  transactionListener.getData();
        if (data == null) {
            EchonetObjectException exception = new EchonetObjectException("no valid data");
            logger.throwing(className, "getData", exception);
            throw exception;
        }
        
        logger.exiting(className, "getData", data);
        return data;
    }

    public void observeData(EPC epc) throws EchonetObjectException {
        logger.entering(className, "observeData", epc);
        
        RemoteObjectGetTransactionListener transactionListener;

        SetGetTransactionConfig transactionConfig = createSetGetTransactionConfig();
        transactionConfig.setAnnouncePreferred(true);
        transactionConfig.addGet(epc);

        transactionListener = new RemoteObjectGetTransactionListener(epc);
        Transaction transaction = createSetGetTransaction(transactionConfig, transactionListener);
        
        try {
            transaction.execute();
        } catch (SubnetException e) {
            EchonetObjectException exception = new EchonetObjectException("getData failed", e);
            logger.throwing(className, "observeData", exception);
            throw exception;
        }
        
        logger.exiting(className, "observeData");
    }

    @Override
    public boolean setData(EPC epc, ObjectData data) throws EchonetObjectException {
        logger.entering(className, "setData", new Object[]{epc, data});
        
        RemoteObjectSetTransactionListener transactionListener;

        SetGetTransactionConfig transactionConfig = createSetGetTransactionConfig();
        transactionConfig.addSet(epc, data.getData());

        transactionListener = new RemoteObjectSetTransactionListener(epc);
        Transaction transaction = createSetGetTransaction(transactionConfig, transactionListener);

        try {
            transaction.execute();
            transaction.join();
        } catch (SubnetException e) {
            EchonetObjectException exception = new EchonetObjectException("setData failed", e);
            logger.throwing(className, "setData", exception);
            throw exception;
        } catch (InterruptedException e) {
            EchonetObjectException exception = new EchonetObjectException("interrupted", e);
            logger.throwing(className, "setData", exception);
            throw exception;
        }

        boolean success = transactionListener.isSuccess();
        logger.exiting(className, "setData", success);
        return success;
    }

    private PropertyMap getPropertyMap(EPC epc) throws EchonetObjectException {
        return new PropertyMap(getData(epc).toBytes());
    }

    @Override
    public boolean contains(EPC epc) throws EchonetObjectException {
        return isGettable(epc) || isSettable(epc) || isObservable(epc);
    }

    @Override
    public boolean isGettable(EPC epc) throws EchonetObjectException {
        return getPropertyMap(GET_PROPERTYMAP_EPC).isSet(epc);
    }

    @Override
    public boolean isSettable(EPC epc) throws EchonetObjectException {
        return getPropertyMap(SET_PROPERTYMAP_EPC).isSet(epc);
    }

    @Override
    public boolean isObservable(EPC epc) throws EchonetObjectException {
        return getPropertyMap(ANNOUNCE_PROPERTYMAP_EPC).isSet(epc);
    }

    public synchronized void addObserver(RemoteObjectObserver observer) {
        logger.entering(className, "addObserver", observer);
        
        observers.add(observer);
        
        logger.exiting(className, "addObserver");
    }

    public synchronized void removeObserver(RemoteObjectObserver observer) {
        logger.entering(className, "removeObserver", observer);
        
        observers.remove(observer);
        
        logger.exiting(className, "removeObserver");
    }

    public synchronized int countObservers() {
        return observers.size();
    }

    public void notifyData(EPC epc, ObjectData data) {
        logger.entering(className, "notifyData", new Object[]{epc, data});
        
        for (RemoteObjectObserver observer : cloneObservers()) {
            observer.notifyData(this, epc, data);
        }
        
        logger.exiting(className, "notifyData");
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{Node: " + node + ", EOJ: " + eoj + "}";
    }
}
