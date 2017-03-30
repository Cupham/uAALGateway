package echowand.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import echowand.common.ClassEOJ;
import echowand.common.EOJ;
import echowand.logic.TooManyObjectsException;
import echowand.util.Collector;
import echowand.util.Selector;

public class LocalObjectManager {
    private static final Logger logger = Logger.getLogger(LocalObjectManager.class.getName());
    private static final String className = LocalObjectManager.class.getName();
    
    private HashMap<EOJ, LocalObject> objectsMap;
    private LinkedList<LocalObject>   objects;
    private UnusedEOJGenerator eojGenerator;

    public LocalObjectManager() {
        logger.entering(className, "LocalObjectManager");
        
        objectsMap = new HashMap<EOJ, LocalObject>();
        objects = new LinkedList<LocalObject>();
        eojGenerator = new UnusedEOJGenerator();
        
        logger.exiting(className, "LocalObjectManager");
    }

    public int size() {
        return objects.size();
    }
    
    private synchronized void addObject(LocalObject object) {
        logger.entering(className, "addObject", object);
        
        objectsMap.put(object.getEOJ(), object);
        objects.add(object);
        
        logger.exiting(className, "addObject");
    }

    public void add(LocalObject object, boolean updateInstanceCode) throws TooManyObjectsException {
        logger.entering(className, "add", new Object[]{object, updateInstanceCode});
        
        if (updateInstanceCode) {
            ClassEOJ classEOJ = object.getEOJ().getClassEOJ();
            EOJ newEOJ = eojGenerator.generate(classEOJ);
            object.setInstanceCode(newEOJ.getInstanceCode());
        } else {
            eojGenerator.addUsed(object.getEOJ());
        }
        
        addObject(object);

        logger.exiting(className, "add");
    }

    public void add(LocalObject object) throws TooManyObjectsException {
        logger.entering(className, "add", object);

        add(object, true);

        logger.exiting(className, "add");
    }

    public synchronized LocalObject get(EOJ eoj) {
        logger.entering(className, "get", eoj);
        
        LocalObject object = objectsMap.get(eoj);
        
        logger.exiting(className, "get", object);
        return object;
    }

    public synchronized List<LocalObject> get(Selector<? super LocalObject> selector) {
        logger.entering(className, "get", selector);
        
        Collector<LocalObject> collector = new Collector<LocalObject>(selector);
        List<LocalObject> objectList = collector.collect(new ArrayList<LocalObject>(objects));
        
        logger.exiting(className, "get", objectList);
        return objectList;
    }

    public synchronized LocalObject getAtIndex(int index) {
        logger.entering(className, "getAtIndex", index);
        
        LocalObject object = objects.get(index);
        
        logger.exiting(className, "getAtIndex", object);
        return object;
    }

    public synchronized List<LocalObject> getWithClassEOJ(final ClassEOJ ceoj) {
        logger.entering(className, "getWithClassEOJ", ceoj);
        
        List<LocalObject> objectList = get(new Selector<LocalObject>() {
            @Override
            public boolean match(LocalObject object) {
                return object.getEOJ().isMemberOf(ceoj);
            }
        });
        
        logger.exiting(className, "getWithClassEOJ", objectList);
        return objectList;
    }

    public synchronized List<LocalObject> getAllObjects() {
        logger.entering(className, "getObjects");
        
        List<LocalObject> objectList = get(new Selector<LocalObject>() {
            @Override
            public boolean match(LocalObject object) {
                return true;
            }
        });
        
        logger.exiting(className, "getObjects", objectList);
        return objectList;
    }

    public synchronized List<LocalObject> getDeviceObjects() {
        logger.entering(className, "getDeviceObjects");
        
        List<LocalObject> objectList = get(new Selector<LocalObject>() {
            @Override
            public boolean match(LocalObject object) {
                return object.getEOJ().isDeviceObject();
            }
        });
        
        logger.exiting(className, "getDeviceObjects", objectList);
        return objectList;
    }
}
