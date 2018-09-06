package echowand.monitor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import echowand.common.EOJ;
import echowand.net.Node;
import echowand.net.SubnetException;
import echowand.service.Core;
import echowand.service.Service;

/**
 *
 * @author ymakino
 */
public class Monitor {
    private static final Logger LOGGER = Logger.getLogger(Monitor.class.getName());
    private static final String CLASS_NAME = Monitor.class.getName();
    
    private Core core;
    private LinkedList<Entry> entries;
    private LinkedList<MonitorListener> listeners;
    private MonitorUpdater updater = null;
    
    public Monitor(Core core) {
        LOGGER.entering(CLASS_NAME, "Monitor", core);
        
        this.core = core;
        entries = new LinkedList<Entry>();
        listeners = new LinkedList<MonitorListener>();
        
        LOGGER.exiting(CLASS_NAME, "Monitor");
    }
    
    public synchronized List<Node> getNodes() {
        LOGGER.entering(CLASS_NAME, "getNodes");
        
        LinkedList<Node> nodes = new LinkedList<Node>();
        
        for (Entry entry : entries) {
            Node node = entry.getNode();
            if (!entry.isExpired() && !nodes.contains(node)) {
                nodes.add(node);
            }
        }
        
        List<Node> result = new ArrayList<Node>(nodes);
        LOGGER.exiting(CLASS_NAME, "getNodes", result);
        return result;
    }
    
    public synchronized List<EOJ> getEOJs(Node node) {
        LOGGER.entering(CLASS_NAME, "getEOJs", node);
        
        LinkedList<EOJ> eojs = new LinkedList<EOJ>();
        
        for (Entry entry : entries) {
            if (entry.isAlive() && entry.isEntryOf(node)) {
                eojs.add(entry.getEOJ());
            }
        }
        
        List<EOJ> result = new ArrayList<EOJ>(eojs);
        LOGGER.exiting(CLASS_NAME, "getNodes", result);
        return result;
    }
    
    public synchronized List<Entry> getOldEntries() {
        LOGGER.entering(CLASS_NAME, "getOldEntries");
        
        LinkedList<Entry> oldEntries = new LinkedList<Entry>();
        
        for (Entry entry : entries) {
            if (entry.isOld()) {
                oldEntries.add(entry);
            }
        }
        
        List<Entry> result = new ArrayList<Entry>(oldEntries);
        LOGGER.exiting(CLASS_NAME, "getOldEntries", result);
        return result;
    }
    
    public synchronized List<Entry> getExpiredEntries() {
        LOGGER.entering(CLASS_NAME, "getExpiredEntries");
        
        LinkedList<Entry> expiredEntries = new LinkedList<Entry>();
        
        for (Entry entry : entries) {
            if (entry.isExpired()) {
                expiredEntries.push(entry);
            }
        }
        
        List<Entry> result = new ArrayList<Entry>(expiredEntries);
        LOGGER.exiting(CLASS_NAME, "getExpiredEntries", result);
        return result;
    }
    
    public int countExpiredEntries() {
        LOGGER.entering(CLASS_NAME, "countExpiredEntries");
        
        int result = getExpiredEntries().size();
        LOGGER.exiting(CLASS_NAME, "countExpiredEntries", result);
        return result;
    }
    
    public synchronized void removeExpiredEntries() {
        LOGGER.entering(CLASS_NAME, "removeExpiredEntries");
        
        entries.removeAll(getExpiredEntries());
        
        LOGGER.exiting(CLASS_NAME, "removeExpiredEntries");
    }
    
    private List<Node> toNodes(List<Entry> entries) {
        LinkedList<Node> nodes = new LinkedList<Node>();
        
        for (Entry entry : entries) {
            Node node = entry.getNode();
            if (!nodes.contains(node)) {
                nodes.add(node);
            }
        }
        
        return new ArrayList<Node>(nodes);
    }
    
    private List<Entry> getEntries(Node node) {
        LinkedList<Entry> selected = new LinkedList<Entry>();
        
        for (Entry entry : entries) {
            if (entry.isEntryOf(node)) {
                selected.add(entry);
            }
        }
        
        return new ArrayList<Entry>(selected);
    }
    
    private int expireEntries(List<Entry> entries) {
        
        int count = 0;
        List<Node> nodes = toNodes(entries);
        
        for (Node node : nodes) {
            LinkedList<EOJ> eojs = new LinkedList<EOJ>();
            
            for (Entry entry: entries) {
                if (!entry.isExpired() && entry.isEntryOf(node)) {
                    eojs.add(entry.getEOJ());
                    count++;
                    entry.expire();
                }
            }
            
            if (!eojs.isEmpty()) {
                for (MonitorListener listener : listeners) {
                    listener.detectEOJsExpired(this, node, eojs);
                }
            }
        }
        
        return count;
    }
    
    public synchronized int removeNode(Node node) {
        LOGGER.entering(CLASS_NAME, "removeNode", node);
        
        int s1 = entries.size();
        entries.removeAll(getEntries(node));
        int s2 = entries.size();
        
        int result = s1 - s2;
        LOGGER.entering(CLASS_NAME, "removeNode", result);
        return result;
    }
    
    public synchronized int expireNode(Node node) {
        LOGGER.entering(CLASS_NAME, "expireNode", node);
        
        int result = expireEntries(getEntries(node));
        LOGGER.entering(CLASS_NAME, "expireNode", result);
        return result;
    }
    
    public synchronized void updateStatus() {
        LOGGER.entering(CLASS_NAME, "updateStatus");
        
        List<Entry> lastExpiredEntries = getExpiredEntries();
        
        for (Entry entry : entries) {
            entry.updateStatus();
        }
        
        List<Entry> currentExpiredEntries = getExpiredEntries();
        
        currentExpiredEntries.removeAll(lastExpiredEntries);
        
        List<Node> nodes = toNodes(currentExpiredEntries);
        
        for (Node node : nodes) {
            LinkedList<EOJ> eojs = new LinkedList<EOJ>();
            
            for (Entry entry : currentExpiredEntries) {
                if (entry.isEntryOf(node)) {
                    eojs.add(entry.getEOJ());
                }
            }
            
            for (MonitorListener listener : listeners) {
                listener.detectEOJsExpired(this, node, eojs);
            }
        }
        
        LOGGER.exiting(CLASS_NAME, "updateStatus");
    }
    
    public synchronized void addMonitorListener(MonitorListener listener) {
        LOGGER.entering(CLASS_NAME, "addMonitorListener", listener);
        
        listeners.add(listener);
        
        LOGGER.exiting(CLASS_NAME, "addMonitorListener");
    }
    
    public synchronized void removeMonitorListener(MonitorListener listener) {
        LOGGER.entering(CLASS_NAME, "removeMonitorListener", listener);
        
        listeners.remove(listener);
        
        LOGGER.exiting(CLASS_NAME, "removeMonitorListener");
    }
    
    public synchronized void detectEOJs(Node node, List<EOJ> eojs) {
        LOGGER.entering(CLASS_NAME, "detectEOJs", new Object[]{node, eojs});
        
        LOGGER.logp(Level.INFO, CLASS_NAME, "detectEOJs", "detectEOJs: " + node.toString() + ": " + eojs);
        
        LinkedList<EOJ> remains = new LinkedList<EOJ>(eojs);
        LinkedList<EOJ> updated = new LinkedList<EOJ>();
        
        for (Entry entry : entries) {
            for (EOJ eoj : eojs) {
                if (entry.isEntryOf(node, eoj)) {
                    if (entry.isExpired()) {
                        updated.add(eoj);
                    }
                    
                    LOGGER.logp(Level.INFO, CLASS_NAME, "detectEOJs", "Refresh an entry: " + entry);
                    entry.refresh();
                    remains.remove(eoj);
                }
            }
        }
        
        for (EOJ eoj : remains) {
            updated.add(eoj);
            Entry entry = new Entry(node, eoj);
            LOGGER.logp(Level.INFO, CLASS_NAME, "detectEOJs", "Add a new entry: " + entry);
            entries.add(entry);
        }
        
        if (!updated.isEmpty()) {
            for (MonitorListener listener : listeners) {
                listener.detectEOJsJoined(this, node, updated);
            }
        }
        
        LOGGER.exiting(CLASS_NAME, "detectEOJs");
    }
    
    public boolean start() throws SubnetException {
        LOGGER.entering(CLASS_NAME, "start");
        
        if (updater == null) {
            updater = new MonitorUpdater(this, new Service(core));
        }
        
        boolean result = updater.startUpdater();
        LOGGER.exiting(CLASS_NAME, "start", result);
        return result;
    }
    
    public boolean stop() {
        LOGGER.entering(CLASS_NAME, "stop");
        
        if (updater == null) {
            LOGGER.exiting(CLASS_NAME, "stop", false);
            return false;
        }
        
        boolean result = updater.stopUpdater();
        LOGGER.exiting(CLASS_NAME, "stop", result);
        return result;
    }
}
