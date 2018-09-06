package echowand.monitor;

import java.util.logging.Level;
import java.util.logging.Logger;

import echowand.common.EOJ;
import echowand.net.Node;

/**
 *
 * @author ymakino
 */
public class Entry {
    private static final Logger LOGGER = Logger.getLogger(Entry.class.getName());
    private static final String CLASS_NAME = Entry.class.getName();
    
    public static final long DEFAULT_FRESH_DURATION = 120000;
    public static final long DEFAULT_ALIVE_DURATION = 300000;
    public static final long DEFAULT_MAXIMUM_DURATION = 600000;
    
    private Status status;
    private final Node node;
    private final EOJ eoj;
    private long updatedTime;
    
    private long freshDuration = DEFAULT_FRESH_DURATION;
    private long aliveDuration = DEFAULT_ALIVE_DURATION;
    private long maximamDuration = DEFAULT_MAXIMUM_DURATION;
    
    public Entry(Node node, EOJ eoj) {
        status = Status.Fresh;
        this.node = node;
        this.eoj = eoj;
        updatedTime = System.currentTimeMillis();
    }
    
    public Node getNode() {
        return node;
    }
    
    public boolean isEntryOf(Node node) {
        return this.node.equals(node);
    }
    
    public boolean isEntryOf(EOJ eoj) {
        return this.eoj.equals(eoj);
    }
    
    public boolean isEntryOf(Node node, EOJ eoj) {
        return isEntryOf(node) && isEntryOf(eoj);
    }
    
    public EOJ getEOJ() {
        return eoj;
    }
    
    public long getUpdatedTime() {
        return updatedTime;
    }
    
    public void refresh() {
        status = Status.Fresh;
        updatedTime = System.currentTimeMillis();
    }
    
    public void expire() {
        status = Status.Expired;
    }
    
    public boolean isFresh() {
        return status == Status.Fresh;
    }
    
    public boolean isOld() {
        return status == Status.Old;
    }
    
    public boolean isAlive() {
        return status != Status.Expired;
    }
    
    public boolean isExpired() {
        return status == Status.Expired;
    }
    
    public Status getStatus() {
        return status;
    }
    
    private void fixUpdatedTime(long current) {
        if (status == Status.Expired) {
            return;
        }
        
        if (current < updatedTime) {
            updatedTime = current;
        }
        
        if (updatedTime + maximamDuration < current) {
            updatedTime = current - maximamDuration;
        }
    }
    
    public Status updateStatus() {
        
        long current = System.currentTimeMillis();
        
        fixUpdatedTime(current);
        
        switch (status) {
        case Fresh:
            if (updatedTime + freshDuration < current) {
                status = Status.Old;
                LOGGER.logp(Level.INFO, CLASS_NAME, "updateStatus", "Fresh -> Old: " + this);
            }
            break;
        case Old:
            if (updatedTime + aliveDuration < current) {
                status = Status.Expired;
                LOGGER.logp(Level.INFO, CLASS_NAME, "updateStatus", "Old -> Expired: " + this);
            }
            break;
        }
        
        return status;
    }
    
    @Override
    public String toString() {
        return "Entry(" + node + ", " + eoj + ", " + status + ")";
    }
    
    public static enum Status {
        Fresh,
        Old,
        Expired;
    }
}
