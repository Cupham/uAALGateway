package echowand.monitor;

import java.util.List;

import echowand.common.EOJ;
import echowand.net.Node;

/**
 *
 * @author ymakino
 */
public interface MonitorListener {
    void detectEOJsJoined(Monitor monitor, Node node, List<EOJ> eojs);
    void detectEOJsExpired(Monitor monitor, Node node, List<EOJ> eojs);
}
