package echowand.service.result;

import java.util.List;

import echowand.common.EOJ;
import echowand.net.Node;

/**
 *
 * @author ymakino
 */
public class UpdateRemoteInfoListener {

    public void begin(UpdateRemoteInfoResult result) {
    }

    public void send(UpdateRemoteInfoResult result, ResultFrame resultFrame, boolean success) {
    }

    public void receive(UpdateRemoteInfoResult result, ResultFrame resultFrame) {
    }
    
    public void receive(UpdateRemoteInfoResult result, ResultFrame resultFrame, Node node, List<EOJ> eojs) {
    }
    
    public void finish(UpdateRemoteInfoResult result) {
    }
    
}