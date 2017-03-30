package echowand.net;

import java.util.concurrent.SynchronousQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import echowand.util.Pair;

public class InetSubnetUDPReceiverThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(InetSubnetUDPReceiverThread.class.getName());
    private static final String CLASS_NAME = InetSubnetUDPReceiverThread.class.getName();

    private InetSubnet subnet;
    private UDPNetwork network;
    private SynchronousQueue<Frame> queue;
    private boolean terminated = false;

    public InetSubnetUDPReceiverThread(InetSubnet subnet, UDPNetwork network, SynchronousQueue<Frame> queue) {
        this.subnet = subnet;
        this.network = network;
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
                Pair<InetNodeInfo, CommonFrame> pair = network.receive();
                InetNodeInfo nodeInfo = pair.first;
                CommonFrame commonFrame = pair.second;
                Node localNode = subnet.getLocalNode();
                Node remoteNode = subnet.getRemoteNode(nodeInfo);
                queue.put(new Frame(remoteNode, localNode, commonFrame));
            } catch (InterruptedException ex) {
                LOGGER.logp(Level.INFO, CLASS_NAME, "InetSubnetUDPReceiver.run", "interrupted", ex);
            } catch (NetworkException ex) {
                LOGGER.logp(Level.FINE, CLASS_NAME, "InetSubnetUDPReceiver.run", "catched exception", ex);
            } catch (SubnetException ex) {
                LOGGER.logp(Level.INFO, CLASS_NAME, "InetSubnetUDPReceiver.run", "invalid remoteNode", ex);
            }
        }
    }
}
