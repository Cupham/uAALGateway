package echowand.object;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import echowand.common.EPC;
import echowand.net.Property;

public class LocalSetGetAtomic implements Runnable {
    private static final Logger logger = Logger.getLogger(LocalSetGetAtomic.class.getName());
    private static final String className = LocalSetGetAtomic.class.getName();
    
    private LocalObject object;
    private LinkedList<Property> setProperties;
    private LinkedList<Property> getProperties;
    private LinkedList<Property> setResult;
    private LinkedList<Property> getResult;
    private boolean announce = false;
    private boolean success = true;
    private boolean done = false;

    public LocalSetGetAtomic(LocalObject object) {
        logger.entering(className, "LocalSetGetAtomic", object);
        
        this.object = object;
        setProperties = new LinkedList<Property>();
        getProperties = new LinkedList<Property>();
        setResult = new LinkedList<Property>();
        getResult = new LinkedList<Property>();
        
        logger.exiting(className, "LocalSetGetAtomic");
    }

    public void initialize() {
        logger.entering(className, "initialize", object);
        
        setProperties.clear();
        getProperties.clear();
        setResult.clear();
        getResult.clear();
        success = true;
        done = false;
        
        logger.exiting(className, "initialize");
    }

    public void setAnnounce(boolean announce) {
        logger.entering(className, "setAnnounce", announce);
        
        this.announce = announce;
        
        logger.exiting(className, "setAnnounce");
    }

    public void addSet(Property property) {
        logger.entering(className, "addSet", property);
        
        setProperties.add(property);
        
        logger.exiting(className, "addSet");
    }

    public void addGet(Property property) {
        logger.entering(className, "addGet", property);
        
        getProperties.add(property);
        
        logger.exiting(className, "addGet");
    }

    private boolean hasGetOrAnnouncePermission(LocalObject object, EPC epc) {
        logger.entering(className, "hasGetOrAnnouncePermission", new Object[]{object, epc});
        
        boolean permission;
        if (announce) {
            permission = object.isObservable(epc);
        } else {
            permission = object.isGettable(epc);
        }
        
        logger.exiting(className, "hasGetOrAnnouncePermission", permission);
        return permission;
    }

    @Override
    public void run() {
        logger.entering(className, "run");

        try {
            if (done) {
                return;
            }

            for (Property property : setProperties) {
                if (object.setData(property.getEPC(), new ObjectData(property.getEDT()))) {
                    setResult.add(new Property(property.getEPC()));
                } else {
                    setResult.add(property);
                    success = false;
                }
            }

            for (Property property : getProperties) {
                ObjectData data = null;

                if (hasGetOrAnnouncePermission(object, property.getEPC())) {
                    data = object.forceGetData(property.getEPC());
                }

                if (data != null) {
                    getResult.add(new Property(property.getEPC(), data.getData()));
                } else {
                    getResult.add(new Property(property.getEPC()));
                    success = false;
                }
            }

            done = true;
        } finally {
            logger.exiting(className, "run");
        }
    }

    public List<Property> getSetResult() {
        return setResult;
    }

    public List<Property> getGetResult() {
        return getResult;
    }

    public boolean isDone() {
        return done;
    }

    public boolean isSuccess() {
        return success;
    }
}
