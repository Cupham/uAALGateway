package echowand.object;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import echowand.common.EOJ;
import echowand.net.Node;
import echowand.util.Collector;
import echowand.util.Selector;

public class RemoteObjectManager {
    private static final Logger logger = Logger.getLogger(RemoteObjectManager.class.getName());
    private static final String className = RemoteObjectManager.class.getName();
    
    private HashMap<Node, HashMap<EOJ, RemoteObject>> objects;

    public RemoteObjectManager() {
        logger.entering(className, "RemoteObjectManager");
        
        this.objects = new HashMap<Node, HashMap<EOJ, RemoteObject>>();
        
        logger.exiting(className, "RemoteObjectManager");
    }
    
    private synchronized HashMap<EOJ, RemoteObject> getOrCreateNodeHashMap(Node node) {
        logger.entering(className, "getOrCreateNodeHashMap", node);
        
        HashMap<EOJ, RemoteObject> map = objects.get(node);
        if (map == null) {
            map = new HashMap<EOJ, RemoteObject>();
            objects.put(node, map);
        }
        
        logger.exiting(className, "getOrCreateNodeHashMap", map);
        return map;
    }

    public synchronized boolean add(RemoteObject object) {
        logger.entering(className, "add", object);
        
        boolean result = false;
        
        HashMap<EOJ, RemoteObject> map = getOrCreateNodeHashMap(object.getNode());
        if (!map.containsKey(object.getEOJ())) {
            map.put(object.getEOJ(), object);
            result = true;
        }
        
        logger.exiting(className, "add", result);
        return result;
    }

    public synchronized boolean remove(RemoteObject object) {
        logger.entering(className, "remove", object);
        
        boolean result = false;
        
        HashMap<EOJ, RemoteObject> map = getOrCreateNodeHashMap(object.getNode());
        if (map.remove(object.getEOJ()) != null) {
            result = true;
        }
        
        logger.exiting(className, "remove", result);
        return result;
    }

    public synchronized RemoteObject get(Node node, EOJ eoj) {
        logger.entering(className, "get", new Object[]{node, eoj});
        
        HashMap<EOJ, RemoteObject> map = getOrCreateNodeHashMap(node);
        RemoteObject object = map.get(eoj);
        
        logger.exiting(className, "get", object);
        return object;
    }
    
    private synchronized List<RemoteObject> getAllObjects() {
        logger.entering(className, "getAllObjects");
        
        LinkedList<RemoteObject> newList = new LinkedList<RemoteObject>();
        for (Node node : objects.keySet()) {
            HashMap<EOJ, RemoteObject> objectsAtNode = objects.get(node);
            for (RemoteObject obj : objectsAtNode.values()) {
                newList.add(obj);
            }
        }
        
        logger.exiting(className, "getAllObjects", newList);
        return newList;
    }

    public synchronized List<RemoteObject> getAtNode(final Node node) {
        logger.entering(className, "getAtNode", node);
        
        List<RemoteObject> objectList = get(new Selector<RemoteObject>() {
            @Override
            public boolean match(RemoteObject object) {
                return object.getNode().equals(node);
            }
        });
        
        logger.exiting(className, "getAtNode", objectList);
        return objectList;
    }

    public List<RemoteObject> get(Selector<? super RemoteObject> selector) {
        logger.entering(className, "get", selector);
        
        Collector<RemoteObject> collector = new Collector<RemoteObject>(selector);
        List<RemoteObject> objectList = collector.collect(getAllObjects());
        
        logger.exiting(className, "get", objectList);
        return objectList;
    }

    public List<Node> getNodes() {
        logger.entering(className, "getNodes");
        
        LinkedList<Node> nodeList = new LinkedList<Node>(objects.keySet());
        
        logger.exiting(className, "getNodes", nodeList);
        return nodeList;
    }
}
