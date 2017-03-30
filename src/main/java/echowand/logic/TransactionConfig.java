package echowand.logic;

import java.util.logging.Logger;

import echowand.common.EOJ;
import echowand.common.ESV;
import echowand.net.Node;
import echowand.net.StandardPayload;

public abstract class TransactionConfig {
    private static final Logger logger = Logger.getLogger(TransactionConfig.class.getName());
    private static final String className = TransactionConfig.class.getName();
    
    private Node senderNode;
    private Node receiverNode;
    private EOJ sourceEOJ;
    private EOJ destinationEOJ;
    
    public TransactionConfig() {}
    
    public abstract ESV getESV();
    
    public abstract int getCountPayloads();
    
    public abstract void addPayloadProperties(int index, StandardPayload payload);
    
    public void setSenderNode(Node senderNode) {
        logger.entering(className, "setSenderNode", senderNode);
        
        this.senderNode = senderNode;
        
        logger.exiting(className, "setSenderNode");
    }
    
    public Node getSenderNode() {
        return senderNode;
    }

    public void setReceiverNode(Node receiverNode) {
        logger.entering(className, "setReceiverNode", receiverNode);
        
        this.receiverNode = receiverNode;
        
        logger.exiting(className, "setReceiverNode");
    }
    
    public Node getReceiverNode() {
        return receiverNode;
    }
    
    public void setSourceEOJ(EOJ sourceEOJ) {
        logger.entering(className, "setSourceEOJ", sourceEOJ);
        
        this.sourceEOJ = sourceEOJ;
        
        logger.exiting(className, "setSourceEOJ");
    }
    
    public EOJ getSourceEOJ() {
        return sourceEOJ;
    }
    
    public void setDestinationEOJ(EOJ destinationEOJ) {
        logger.entering(className, "setDestinationEOJ", destinationEOJ);
        
        this.destinationEOJ = destinationEOJ;
        
        logger.exiting(className, "setDestinationEOJ");
    }
    
    public EOJ getDestinationEOJ() {
        return destinationEOJ;
    }
}
