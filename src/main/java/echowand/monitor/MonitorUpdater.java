package echowand.monitor;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import echowand.common.ClassEOJ;
import echowand.common.Data;
import echowand.common.EOJ;
import echowand.common.EPC;
import echowand.net.Node;
import echowand.net.SubnetException;
import echowand.service.Service;
import echowand.service.result.GetListener;
import echowand.service.result.GetResult;
import echowand.service.result.ObserveListener;
import echowand.service.result.ObserveResult;
import echowand.service.result.ResultData;
import echowand.service.result.ResultFrame;

/**
 *
 * @author ymakino
 */
public class MonitorUpdater {
    private static final Logger LOGGER = Logger.getLogger(MonitorUpdater.class.getName());
    private static final String CLASS_NAME = MonitorUpdater.class.getName();
    
    public static final int GET_TIMEOUT = 10000;
    public static final int GROUPGET_TIMEOUT = 10000;
    
    private int initialDelay = 1000;
    private int updateInterval = 5000;
    private int getInterval = 10000;
    private int groupGetInterval = 120000;
    private Monitor monitor;
    private Service service;
    private Timer timer;
    private ObserveResult observeResult;
    
    private boolean observeEnabled = true;
    private boolean getEnabled = true;
    private boolean groupGetEnabled = true;
    
    public MonitorUpdater(Monitor monitor, Service service) {
        this.monitor = monitor;
        this.service = service;
        timer = null;
    }
    
    private LinkedList<EOJ> getEOJs(Data data) {
        LinkedList<EOJ> eojs = new LinkedList<EOJ>();
        int len = data.size();
        
        if (len <= 1) {
            return eojs;
        }
        
        int count = 0x00ff & data.get(0);
        
        if (1 + count * 3 != len) {
            return eojs;
        }
        
        for (int i=1; i < len; i+=3) {
            byte[] bytes = data.toBytes(i, 3);
            eojs.add(new EOJ(bytes));
        }
        
        return eojs;
    }
    
    private void startObserve() throws SubnetException {
        observeResult = service.doObserve(new ClassEOJ("0ef0"), EPC.xD5, new ObserveListener() {
            @Override
            public void receive(ObserveResult result, ResultFrame resultFrame, ResultData resultData) {
                LOGGER.logp(Level.INFO, CLASS_NAME, "ObserveListener.receive", "receive: " + resultFrame);
                LinkedList<EOJ> eojs = getEOJs(resultData.getActualData());
                eojs.add(0, resultData.getEOJ());
                monitor.detectEOJs(resultData.getNode(), eojs);
            }
        });
        
        observeResult.disableDataList();
        observeResult.disableFrameList();
    }
    
    public boolean startUpdater() throws SubnetException {
        if (timer != null) {
            return false;
        }
        
        if (observeEnabled) {
            startObserve();
        }
        
        timer = new Timer();
        timer.schedule(new IntervalUpdateTask(), initialDelay, updateInterval);
        
        if (getEnabled) {
            timer.schedule(new IntervalGetTask(), initialDelay, getInterval);
        }
        
        if (groupGetEnabled) {
            timer.schedule(new IntervalGroupGetTask(), initialDelay, groupGetInterval);
        }
        
        return true;
    }
    
    public boolean stopUpdater() {
        if (timer == null) {
            return false;
        }
        
        timer.cancel();
        
        if (observeResult != null) {
            observeResult.stopObserve();
        }
        
        timer = null;
        observeResult = null;
        
        return true;
    }
    
    private class IntervalUpdateTask extends TimerTask {
        @Override
        public void run() {
            LOGGER.entering(CLASS_NAME, "IntervalUpdateTask.run");
            
            monitor.updateStatus();
            
            LOGGER.exiting(CLASS_NAME, "IntervalUpdateTask.run");
        }
    }
    
    private class IntervalGetTask extends TimerTask {
        @Override
        public void run() {
            LOGGER.entering(CLASS_NAME, "IntervalGetTask.run");
            
            List<Entry> entries = monitor.getOldEntries();
            LinkedList<Node> nodes = new LinkedList<Node>();
            
            for (Entry entry : entries) {
                Node node = entry.getNode();
                if (!nodes.contains(node)) {
                    nodes.add(node);
                }
            }
            
            for (Node node : nodes) {
                try {
                    service.doGet(node, new EOJ("0ef001"), EPC.xD6, GET_TIMEOUT, new GetListener() {
                        private int count = 0;
                        
                        @Override
                        public void receive(GetResult result, ResultFrame resultFrame, ResultData resultData) {
                            LinkedList<EOJ> eojs = getEOJs(resultData.getActualData());
                            if (!eojs.contains(resultData.getEOJ())) {
                                eojs.push(resultData.getEOJ());
                            }
                            monitor.detectEOJs(resultData.getNode(), eojs);
                            count++;
                        }

                        @Override
                        public void finish(GetResult result) {
                            if (count == 0) {
                                LOGGER.logp(Level.INFO, CLASS_NAME, "GetListener.finish.", "no response: " + node);
                            }
                        }
                    });
                } catch (SubnetException ex) {
                    Logger.getLogger(MonitorUpdater.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            LOGGER.exiting(CLASS_NAME, "IntervalGetTask.run");
        }
    }
    
    private class IntervalGroupGetTask extends TimerTask {
        @Override
        public void run() {
            LOGGER.entering(CLASS_NAME, "IntervalGroupGetTask.run");
            
            try {
                service.doGet(service.getGroupNode(), new ClassEOJ("0ef0"), EPC.xD6, GROUPGET_TIMEOUT, new GetListener() {
                    @Override
                    public void receive(GetResult result, ResultFrame resultFrame, ResultData resultData) {
                        LinkedList<EOJ> eojs = getEOJs(resultData.getActualData());
                        if (!eojs.contains(resultData.getEOJ())) {
                            eojs.push(resultData.getEOJ());
                        }
                        monitor.detectEOJs(resultData.getNode(), eojs);
                    }
                });
            } catch (SubnetException ex) {
                Logger.getLogger(MonitorUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            LOGGER.exiting(CLASS_NAME, "IntervalGroupGetTask.run");
        }
    }
}
