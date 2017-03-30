package echowand.service;

import echowand.net.Frame;

/**
 * CaptureSubnet
 */
public interface CaptureSubnetObserver {
    /**
     * 
     * @param frame
     * @param success
     */
    public void notifySent(Frame frame, boolean success);
    
    /**
     * 
     * @param frame
     */
    public void notifyReceived(Frame frame);
}
