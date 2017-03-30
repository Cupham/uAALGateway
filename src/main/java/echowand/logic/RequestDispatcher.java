package echowand.logic;

import java.util.LinkedList;
import java.util.logging.Logger;

import echowand.net.CommonFrame;
import echowand.net.Frame;
import echowand.net.StandardPayload;
import echowand.net.Subnet;

public class RequestDispatcher implements Listener {
    private static final Logger logger = Logger.getLogger(RequestDispatcher.class.getName());
    private static final String className = RequestDispatcher.class.getName();
    
    private LinkedList<RequestProcessor> processors;
    
    public RequestDispatcher() {
        logger.entering(className, "RequestDispatcher");
        
        processors = new LinkedList<RequestProcessor>();
        
        logger.exiting(className, "RequestDispatcher");
    }
    
    private synchronized LinkedList<RequestProcessor> cloneProcessors() {
        return new LinkedList<RequestProcessor>(processors);
    }
    
    public synchronized void addRequestProcessor(RequestProcessor processor) {
        logger.entering(className, "addRequestProcessor", processor);
        
        processors.add(processor);
        
        logger.exiting(className, "addRequestProcessor");
    }
    
    public synchronized void removeRequestProcessor(RequestProcessor processor) {
        logger.entering(className, "removeRequestProcessor", processor);
        
        processors.remove(processor);
        
        logger.exiting(className, "removeRequestProcessor");
    }
    
    @Override
    public boolean process(Subnet subnet, Frame frame, boolean processed) {
        logger.entering(className, "process", new Object[]{subnet, frame, processed});
        
        if (processed) {
            logger.exiting(className, "process", false);
            return false;
        }
        
        if (!frame.getCommonFrame().isStandardPayload()) {
            logger.exiting(className, "process", false);
            return false;
        }
        
        boolean success = false;
        CommonFrame cf = frame.getCommonFrame();
        StandardPayload payload = cf.getEDATA(StandardPayload.class);
        
        if (payload == null) {
            logger.exiting(className, "process", false);
            return false;
        }
        
        switch (payload.getESV()) {
            case SetI:
                success = this.processSetI(subnet, frame);
                break;
            case SetC:
                success = this.processSetC(subnet, frame);
                break;
            case Get:
                success = this.processGet(subnet, frame);
                break;
            case SetGet:
                success = this.processSetGet(subnet, frame);
                break;
            case INF_REQ:
                success = this.processINF_REQ(subnet, frame);
                break;
            case INF:
                success = this.processINF(subnet, frame);
                break;
            case INFC:
                success = this.processINFC(subnet, frame);
                break;
            //Add default nay vao de switch k con canh bao
            default :
            	break;
        }
        
        logger.exiting(className, "process", success);
        return success;
    }

    public boolean processSetI(Subnet subnet, Frame frame) {
        logger.entering(className, "processSetI", new Object[]{subnet, frame});
        
        boolean processed = false;
        for (RequestProcessor processor : cloneProcessors()) {
            processed |= processor.processSetI(subnet, frame, processed);
        }
        
        logger.exiting(className, "processSetI", processed);
        return processed;
    }

    public boolean processSetC(Subnet subnet, Frame frame) {
        logger.entering(className, "processSetC", new Object[]{subnet, frame});
        
        boolean processed = false;
        for (RequestProcessor processor : cloneProcessors()) {
            processed |= processor.processSetC(subnet, frame, processed);
        }
        
        logger.exiting(className, "processSetC", processed);
        return processed;
    }
    
    public boolean processGet(Subnet subnet, Frame frame) {
        logger.entering(className, "processGet", new Object[]{subnet, frame});
        
        boolean processed = false;
        for (RequestProcessor processor : cloneProcessors()) {
            processed |= processor.processGet(subnet, frame, processed);
        }
        
        logger.exiting(className, "processGet", processed);
        return processed;
    }
    
    public boolean processSetGet(Subnet subnet, Frame frame) {
        logger.entering(className, "processSetGet", new Object[]{subnet, frame});
        
        boolean processed = false;
        for (RequestProcessor processor : cloneProcessors()) {
            processed |= processor.processSetGet(subnet, frame, processed);
        }
        
        logger.exiting(className, "processSetGet", processed);
        return processed;
    }
    
    public boolean processINF_REQ(Subnet subnet, Frame frame) {
        logger.entering(className, "processINF_REQ", new Object[]{subnet, frame});
        
        boolean processed = false;
        for (RequestProcessor processor : cloneProcessors()) {
            processed |= processor.processINF_REQ(subnet, frame, processed);
        }
        
        logger.exiting(className, "processINF_REQ", processed);
        return processed;
    }
    
    public boolean processINF(Subnet subnet, Frame frame) {
        logger.entering(className, "processINF", new Object[]{subnet, frame});
        
        boolean processed = false;
        for (RequestProcessor processor : cloneProcessors()) {
            processed |= processor.processINF(subnet, frame, processed);
        }
        
        logger.exiting(className, "processINF", processed);
        return processed;
    }
    
    public boolean processINFC(Subnet subnet, Frame frame) {
        logger.entering(className, "processINFC", new Object[]{subnet, frame});
        
        boolean processed = false;
        for (RequestProcessor processor : cloneProcessors()) {
            processed |= processor.processINFC(subnet, frame, processed);
        }
        
        logger.exiting(className, "processINFC", processed);
        return processed;
    }
}
