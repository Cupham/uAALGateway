package echowand.net;

import java.util.concurrent.SynchronousQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import echowand.util.Pair;

public class InetSubnetTCPReceiverThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(InetSubnetTCPReceiverThread.class.getName());
    private static final String CLASS_NAME = InetSubnetTCPReceiverThread.class.getName();

    private InetSubnet subnet;
    private TCPReceiver receiver;
    private SynchronousQueue<Frame> queue;
    private boolean terminated = false;

    public InetSubnetTCPReceiverThread(InetSubnet subnet, TCPReceiver receiver, SynchronousQueue<Frame> queue) {
        this.subnet = subnet;
        this.receiver = receiver;
        this.queue = queue;
    }

    public void terminate() {
        LOGGER.entering(CLASS_NAME, "terminate");

        terminated = true;

        LOGGER.exiting(CLASS_NAME, "terminate");
    }

    @Override
    public void run() {
        while (!terminated) {
            try {
                Pair<TCPConnection, CommonFrame> pair = receiver.receive();
                LOGGER.logp(Level.FINE, CLASS_NAME, "run", "receive: " + pair);
                TCPConnection connection = pair.first;
                CommonFrame commonFrame = pair.second;
                Node localNode = subnet.getLocalNode();
                Node remoteNode = subnet.getRemoteNode(connection.getRemoteNodeInfo());
                queue.put(new Frame(remoteNode, localNode, commonFrame, connection));
            } catch (SubnetException ex) {
                LOGGER.logp(Level.INFO, CLASS_NAME, "run", "invalid remoteNode", ex);
            } catch (InterruptedException ex) {
                LOGGER.logp(Level.INFO, CLASS_NAME, "run", "interrupted", ex);
            } catch (NetworkException ex) {
                LOGGER.logp(Level.FINE, CLASS_NAME, "run", "catched exception", ex);
            }
        }
    }
}
