package echowand.logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Logger;

import echowand.net.Frame;
import echowand.net.Subnet;
import echowand.net.SubnetException;

public class MainLoop implements Runnable {
	private static final Logger logger = Logger.getLogger(MainLoop.class.getName());
    private static final String className = MainLoop.class.getName();
    
    private Subnet subnet;
    private LinkedList<Listener> listeners;
    
    public MainLoop() {
        logger.entering(className, "MainLoop");
        
        this.listeners = new LinkedList<Listener>();
        
        logger.exiting(className, "MainLoop");
    }
    
    public void setSubnet(Subnet subnet) {
        logger.entering(className, "setSubnet", subnet);
        
        this.subnet = subnet;
        
        logger.exiting(className, "setSubnet");
    }
    
    public Subnet getSubnet() {
        return this.subnet;
    }
    
    public Frame receiveFrame() throws SubnetException {
        logger.entering(className, "receiveFrame");
        
        Frame frame = subnet.receive();
        
        logger.exiting(className, "receiveFrame");
        
        return frame;
    }
    
    public synchronized void addListener(Listener listener) {
        logger.entering(className, "addListener", listener);
        
        listeners.add(listener);
        
        logger.exiting(className, "addListener");
    }
    
    public synchronized void removeListener(Listener listener) {
        logger.entering(className, "removeListener", listener);
        
        listeners.remove(listener);
        
        logger.exiting(className, "removeListener");
    }
    
    public synchronized int countListeners() {
        logger.entering(className, "countListeners");
        
        int count = listeners.size();
        
        logger.exiting(className, "countListeners", count);
        return count;
    }
    
    private synchronized void invokeListeners(Frame frame) {
        logger.entering(className, "invokeListeners", frame);
        
        boolean processed = false;
        for (Listener listener : new ArrayList<Listener>(listeners)) {
            processed |= listener.process(subnet, frame, processed);
        }
        
        logger.exiting(className, "invokeListeners");
    }
    
    @Override
    public void run() {
        logger.entering(className, "run");

        try {
            for (;;) {
                try {
                    Frame frame = receiveFrame();
                    invokeListeners(frame);
                } catch (SubnetException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            logger.exiting(className, "run");
        }
    }
}
