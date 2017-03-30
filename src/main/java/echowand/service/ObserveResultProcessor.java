package echowand.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import echowand.logic.DefaultRequestProcessor;
import echowand.net.Frame;
import echowand.net.Subnet;
import echowand.service.result.ObserveResult;

/**
 * ObserveResult
 * @author ymakino
 */
public class ObserveResultProcessor extends DefaultRequestProcessor {
    private static final Logger LOGGER = Logger.getLogger(ObserveResultProcessor.class.getName());
    private static final String CLASS_NAME = ObserveResultProcessor.class.getName();
    
    private LinkedList<ObserveResult> observeResults;
    
    public ObserveResultProcessor() {
        LOGGER.entering(CLASS_NAME, "ObserveResultProcessor");
        
        observeResults = new LinkedList<ObserveResult>();
        
        LOGGER.exiting(CLASS_NAME, "ObserveResultProcessor");
    }
    
    public synchronized boolean addObserveResult(ObserveResult observeResult) {
        LOGGER.entering(CLASS_NAME, "addObserveResult", observeResult);
        
        boolean result = observeResults.add(observeResult);
        
        LOGGER.exiting(CLASS_NAME, "addObserveResult", result);
        return result;
    }
    
    public synchronized boolean removeObserveResult(ObserveResult observeResult) {
        LOGGER.entering(CLASS_NAME, "removeObserveResult", observeResult);
        
        boolean result = observeResults.remove(observeResult);
        
        LOGGER.exiting(CLASS_NAME, "removeObserveResult", result);
        return result;
    }
    
    private synchronized List<ObserveResult> cloneResultNotifies() {
        return new ArrayList<ObserveResult>(observeResults);
    }
    
    @Override
    public boolean processINF(Subnet subnet, Frame frame, boolean processed) {
        LOGGER.entering(CLASS_NAME, "processINF", new Object[]{subnet, frame, processed});
        
        boolean result = false;
        for (ObserveResult observeResult: cloneResultNotifies()) {
            if (observeResult.shouldReceive(frame)) {
                result |= observeResult.addFrame(frame);
            }
        }
        
        LOGGER.exiting(CLASS_NAME, "processINF", result);
        return result;
    }


    @Override
    public boolean processINFC(Subnet subnet, Frame frame, boolean processed) {
        LOGGER.entering(CLASS_NAME, "processINFC", new Object[]{subnet, frame, processed});
        
        boolean result = false;
        for (ObserveResult observeResult: cloneResultNotifies()) {
            if (observeResult.shouldReceive(frame)) {
                result |= observeResult.addFrame(frame);
            }
        }
        
        LOGGER.exiting(CLASS_NAME, "processINFC", result);
        return result;
    }
}
