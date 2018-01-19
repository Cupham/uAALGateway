package echowand.monitor;

import echowand.common.EOJ;
import echowand.net.Node;
import java.util.List;

/**
 *
 * @author ymakino
 */
public interface MonitorListener {
    void detectEOJsJoined(Monitor monitor, Node node, List<EOJ> eojs);
    void detectEOJsExpired(Monitor monitor, Node node, List<EOJ> eojs);
}
