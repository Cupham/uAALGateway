package echowand.net;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InetSubnetTCPAcceptorThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(InetSubnetTCPAcceptorThread.class.getName());
    private static final String CLASS_NAME = InetSubnetTCPAcceptorThread.class.getName();

    private InetSubnet subnet;
    private TCPAcceptor acceptor;
    private boolean terminated = false;

    public InetSubnetTCPAcceptorThread(InetSubnet subnet, TCPAcceptor acceptor) {
        this.subnet = subnet;
        this.acceptor = acceptor;
    }

    public void terminate() {
        LOGGER.entering(CLASS_NAME, "terminate");
        
        terminated = true;
        
        LOGGER.exiting(CLASS_NAME, "terminate");
    }

    @Override
    public void run() {
        LOGGER.entering(CLASS_NAME, "run");

        while (!terminated) {
            try {
                TCPConnection connection = acceptor.accept();
                subnet.registerTCPConnection(connection);
                LOGGER.logp(Level.FINE, CLASS_NAME, "run", "accept", connection);
            } catch (NetworkException ex) {
                LOGGER.logp(Level.INFO, CLASS_NAME, "run", "catched exception", ex);
            }
        }

        LOGGER.exiting(CLASS_NAME, "run");
    }
}
