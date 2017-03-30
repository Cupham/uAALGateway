package echowand.net;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.logging.Logger;

public class InetSubnet implements Subnet {
    private static final Logger LOGGER = Logger.getLogger(InetSubnet.class.getName());
    private static final String CLASS_NAME = InetSubnet.class.getName();

    public static final short DEFAULT_PORT_NUMBER = 3610;

    private UDPNetwork udpNetwork;
    private TCPReceiver tcpReceiver;
    private TCPAcceptor tcpAcceptor;

    private NetworkInterface networkInterface;
    private List<NetworkInterface> receiverInterfaces;
    private InetAddress multicastAddress;
    private int portNumber;

    private InetAddress localAddress;
    private InetAddress loopbackAddress;
    private InetNode groupNode;
    private InetNode localNode;

    private SynchronousQueue<Frame> receiveQueue = null;

    private InetSubnetUDPReceiverThread udpReceiverThread;
    private InetSubnetTCPReceiverThread tcpReceiverThread;
    private InetSubnetTCPAcceptorThread tcpAcceptorThread;

    private boolean tcpAcceptorEnabled = false;

    protected void initialize(NetworkInterface networkInterface, Collection<? extends NetworkInterface> receiverInterfaces, InetAddress loopbackAddress, InetAddress multicastAddress, int portNumber) throws SubnetException {
        LOGGER.entering(CLASS_NAME, "initialize", new Object[]{networkInterface, loopbackAddress, multicastAddress, portNumber});

        if (!isValidAddress(loopbackAddress)) {
            throw new SubnetException("invalid loopback address: " + loopbackAddress);
        }

        if (!isValidAddress(multicastAddress)) {
            throw new SubnetException("invalid multicast address: " + multicastAddress);
        }

        this.localAddress = null;
        this.networkInterface = networkInterface;
        this.receiverInterfaces = new LinkedList<NetworkInterface>(receiverInterfaces);
        this.loopbackAddress = loopbackAddress;
        this.multicastAddress = multicastAddress;
        this.portNumber = portNumber;

        createUDPNetwork();
        createTCPReceiver();
        createTCPAcceptor();

        LOGGER.exiting(CLASS_NAME, "initialize");
    }

    protected void initialize(InetAddress localAddress, Collection<? extends NetworkInterface> receiverInterfaces, InetAddress loopbackAddress, InetAddress multicastAddress, int portNumber) throws SubnetException {
        LOGGER.entering(CLASS_NAME, "initialize", new Object[]{localAddress, loopbackAddress, multicastAddress, portNumber});
        
        if (!isValidAddress(localAddress)) {
            throw new SubnetException("invalid local address: " + localAddress);
        }

        if (!isValidAddress(loopbackAddress)) {
            throw new SubnetException("invalid loopback address: " + loopbackAddress);
        }

        if (!isValidAddress(multicastAddress)) {
            throw new SubnetException("invalid multicast address: " + multicastAddress);
        }

        this.localAddress = localAddress;
        this.networkInterface = null;
        this.receiverInterfaces = new LinkedList<NetworkInterface>(receiverInterfaces);
        this.loopbackAddress = loopbackAddress;
        this.multicastAddress = multicastAddress;
        this.portNumber = portNumber;
        
        createUDPNetwork();
        createTCPReceiver();
        createTCPAcceptor();

        LOGGER.exiting(CLASS_NAME, "initialize");
    }
	
    protected void initialize(InetAddress loopbackAddress, InetAddress multicastAddress, int portNumber) throws SubnetException {
        LOGGER.entering(CLASS_NAME, "initialize", new Object[]{loopbackAddress, multicastAddress, portNumber});

        if (!isValidAddress(loopbackAddress)) {
            throw new SubnetException("invalid loopback address: " + localAddress);
        }

        if (!isValidAddress(multicastAddress)) {
            throw new SubnetException("invalid multicast address: " + multicastAddress);
        }

        this.localAddress = null;
        this.networkInterface = null;
        this.receiverInterfaces = new LinkedList<NetworkInterface>();
        this.loopbackAddress = loopbackAddress;
        this.multicastAddress = multicastAddress;
        this.portNumber = portNumber;

        createUDPNetwork();
        createTCPReceiver();
        createTCPAcceptor();

        LOGGER.exiting(CLASS_NAME, "initialize");
    }

    private void createUDPNetwork() {
        LOGGER.entering(CLASS_NAME, "createUDPNetwork");

        if (localAddress != null) {
            udpNetwork = new UDPNetwork(localAddress, receiverInterfaces, multicastAddress, portNumber);
        } else if (networkInterface != null) {
            udpNetwork = new UDPNetwork(networkInterface, receiverInterfaces, multicastAddress, portNumber);
        } else if (!receiverInterfaces.isEmpty()) {
            udpNetwork = new UDPNetwork(receiverInterfaces, multicastAddress, portNumber);
        } else {
            udpNetwork = new UDPNetwork(multicastAddress, portNumber);
        }

        LOGGER.exiting(CLASS_NAME, "createUDPNetwork");
    }

    private void createTCPReceiver() {
        LOGGER.entering(CLASS_NAME, "createTCPNetwork");

        tcpReceiver = new TCPReceiver();

        LOGGER.exiting(CLASS_NAME, "createTCPNetwork");
    }

    private void createTCPAcceptor() {
        LOGGER.entering(CLASS_NAME, "createTCPAcceptor");

        if (localAddress != null) {
            tcpAcceptor = new TCPAcceptor(localAddress, portNumber);
        } else {
            tcpAcceptor = new TCPAcceptor(portNumber);
        }

        LOGGER.exiting(CLASS_NAME, "createTCPAcceptor");
    }

    private UDPNetwork getUDPNetwork() {
        return udpNetwork;
    }

    private TCPReceiver getTCPReceiver() {
        return tcpReceiver;
    }

    private TCPAcceptor getTCPAcceptor() {
        return tcpAcceptor;
    }

    public synchronized boolean enableTCPAcceptor() {
        if (isInService()) {
            return false;
        }

        tcpAcceptorEnabled = true;

        return true;
    }

    public synchronized boolean disableTCPAcceptor() {
        if (isInService()) {
            return false;
        }

        tcpAcceptorEnabled = false;

        return true;
    }

    public synchronized boolean isTCPAcceptorEnabled() {
        return tcpAcceptorEnabled;
    }

    public NetworkInterface getNetworkInterface() {
        return networkInterface;
    }

    public synchronized boolean isInService() {
        UDPNetwork network = getUDPNetwork();

        if (network == null) {
            return false;
        }

        return network.isInService();
    }

    private synchronized void startThreads() {
        LOGGER.entering(CLASS_NAME, "startThreads");

        receiveQueue = new SynchronousQueue<Frame>();

        udpReceiverThread = new InetSubnetUDPReceiverThread(this, getUDPNetwork(), receiveQueue);
        udpReceiverThread.start();

        tcpReceiverThread = new InetSubnetTCPReceiverThread(this, getTCPReceiver(), receiveQueue);
        tcpReceiverThread.start();

        if (tcpAcceptorEnabled) {
            tcpAcceptorThread = new InetSubnetTCPAcceptorThread(this, getTCPAcceptor());
            tcpAcceptorThread.start();
        }

        LOGGER.exiting(CLASS_NAME, "startThreads");
    }

    private synchronized void stopThreads() {
        LOGGER.entering(CLASS_NAME, "stopThreads");

        if (udpReceiverThread != null) {
            udpReceiverThread.terminate();
            udpReceiverThread = null;
        }

        if (tcpReceiverThread != null) {
            tcpReceiverThread.terminate();
            tcpReceiverThread = null;
        }

        if (tcpAcceptorThread != null) {
            tcpAcceptorThread.terminate();
            tcpAcceptorThread = null;
        }

        LOGGER.exiting(CLASS_NAME, "stopThreads");
    }

    public synchronized boolean startService() throws SubnetException {
        LOGGER.entering(CLASS_NAME, "startService");

        if (isInService()) {
            LOGGER.exiting(CLASS_NAME, "startService", false);
            return false;
        }

        try {
            boolean result = getUDPNetwork().startService();
            if (result == false) {
                LOGGER.exiting(CLASS_NAME, "startService", false);
                return false;
            }

            result = getTCPReceiver().startService();
            if (result == false) {
                getUDPNetwork().stopService();
                LOGGER.exiting(CLASS_NAME, "startService", false);
                return false;
            }

            if (tcpAcceptorEnabled) {
                result = getTCPAcceptor().startService();
            }

            startThreads();

            LOGGER.exiting(CLASS_NAME, "startService", result);
            return true;
        } catch (NetworkException ex) {
            SubnetException exception = new SubnetException("catched exception", ex);
            LOGGER.throwing(CLASS_NAME, "startService", exception);
            throw exception;
        }
    }

    public synchronized boolean stopService() {
        LOGGER.entering(CLASS_NAME, "stopService");

        boolean result = true;

        if (!isInService()) {
            LOGGER.exiting(CLASS_NAME, "stopService", false);
            return false;
        }

        stopThreads();

        if (tcpAcceptorEnabled) {
            result &= getTCPAcceptor().stopService();
        }

        result &= getTCPReceiver().stopService();
        result &= getUDPNetwork().stopService();

        LOGGER.exiting(CLASS_NAME, "stopService", result);
        return result;
    }

    public TCPConnection newTCPConnection(Node remoteNode, int timeout) throws SubnetException {
        LOGGER.entering(CLASS_NAME, "newTCPConnection", new Object[]{remoteNode, timeout});

        TCPConnection connection;

        try {
            connection = new TCPConnection(getLocalNode().getNodeInfo(), remoteNode.getNodeInfo(), DEFAULT_PORT_NUMBER, timeout);
        } catch (NetworkException ex) {
            SubnetException exception = new SubnetException("catched exception", ex);
            LOGGER.throwing(CLASS_NAME, "newTCPConnection", exception);
            throw exception;
        }

        LOGGER.exiting(CLASS_NAME, "newTCPConnection", connection);
        return connection;
    }

    public TCPConnection newTCPConnection(Node remoteNode) throws SubnetException {
        LOGGER.entering(CLASS_NAME, "newTCPConnection", remoteNode);
        
        TCPConnection connection = newTCPConnection(remoteNode, 0);

        LOGGER.exiting(CLASS_NAME, "newTCPConnection", connection);
        return connection;
    }

    public boolean registerTCPConnection(TCPConnection connection) {
        LOGGER.entering(CLASS_NAME, "registerTCPConnection", connection);

        boolean result = getTCPReceiver().addConnection((TCPConnection) connection);

        LOGGER.exiting(CLASS_NAME, "createTCPConnection", result);
        return result;
    }

    public boolean unregisterTCPConnection(TCPConnection connection) {
        LOGGER.entering(CLASS_NAME, "unregisterTCPConnection", connection);

        boolean result = getTCPReceiver().removeConnection((TCPConnection) connection);

        LOGGER.exiting(CLASS_NAME, "unregisterTCPConnection", result);
        return result;
    }

    @Override
    public boolean send(Frame frame) throws SubnetException {
        LOGGER.entering(CLASS_NAME, "send", frame);

        if (!isInService()) {
            SubnetException exception = new SubnetException("not enabled");
            LOGGER.throwing(CLASS_NAME, "send", exception);
            throw exception;
        }

        if (!frame.getSender().isMemberOf(this)) {
            SubnetException exception = new SubnetException("invalid sender");
            LOGGER.throwing(CLASS_NAME, "send", exception);
            throw exception;
        }

        if (!frame.getReceiver().isMemberOf(this)) {
            SubnetException exception = new SubnetException("invalid receiver");
            LOGGER.throwing(CLASS_NAME, "send", exception);
            throw exception;
        }

        Connection connection = frame.getConnection();
        NodeInfo remoteNodeInfo = frame.getReceiver().getNodeInfo();

        try {
            if (connection != null) {
                if (!(connection instanceof TCPConnection)) {
                    SubnetException exception = new SubnetException("invalid connection: " + connection);
                    LOGGER.throwing(CLASS_NAME, "send", exception);
                    throw exception;
                }

                connection.send(frame.getCommonFrame());
            } else {
                if (!(remoteNodeInfo instanceof InetNodeInfo)) {
                    SubnetException exception = new SubnetException("invalid remote node: " + remoteNodeInfo);
                    LOGGER.throwing(CLASS_NAME, "send", exception);
                    throw exception;
                }
                getUDPNetwork().send((InetNodeInfo) remoteNodeInfo, frame.getCommonFrame());
            }

            LOGGER.exiting(CLASS_NAME, "send", true);
            return true;
        } catch (NetworkException ex) {
            SubnetException exception = new SubnetException("catched exception", ex);
            LOGGER.throwing(CLASS_NAME, "send", exception);
            throw exception;
        }
    }
	
    @Override
    public Frame receive() throws SubnetException {
        LOGGER.entering(CLASS_NAME, "receive");

        if (!isInService()) {
            SubnetException exception = new SubnetException("not enabled");
            LOGGER.throwing(CLASS_NAME, "receive", exception);
            throw exception;
        }

        try {
            Frame frame = receiveQueue.take();
            LOGGER.exiting(CLASS_NAME, "receive", frame);
            return frame;
        } catch (InterruptedException ex) {
            SubnetException exception = new SubnetException("catched exception", ex);
            LOGGER.throwing(CLASS_NAME, "receive", exception);
            throw exception;
        }
    }

    public boolean isValidAddress(InetAddress address) {
        return true;
    }

    public boolean isValidNodeInfo(InetNodeInfo nodeInfo) {
        return isValidAddress(nodeInfo.getAddress());
    }

    @Override
    public Node getRemoteNode(String name) throws SubnetException {
        try {
            InetAddress[] addrs = InetAddress.getAllByName(name);
            
            for (int i=0; i<addrs.length; i++) {
                InetAddress addr = addrs[i];
                if (isValidAddress(addr)) {
                    return new InetNode(this, addr);
                }
            }
        } catch (UnknownHostException ex) {
            throw new SubnetException("invalid name: " + name, ex);
        }
        
        throw new SubnetException("invalid name: " + name);
    }

    public Node getRemoteNode(InetAddress addr) throws SubnetException {
        LOGGER.entering(CLASS_NAME, "getRemoteNode", addr);
        
        if (isValidAddress(addr)) {
            InetNode inetNode = new InetNode(this, addr);
            LOGGER.exiting(CLASS_NAME, "getRemoteNode", inetNode);
            return inetNode;
        } else {
            SubnetException exception = new SubnetException("invalid address: " + addr);
            LOGGER.throwing(CLASS_NAME, "getRemoteNode", exception);
            throw exception;
        }
    }

    @Override
    public Node getRemoteNode(NodeInfo nodeInfo) throws SubnetException {
        LOGGER.entering(CLASS_NAME, "getRemoteNode", nodeInfo);
        
        if (nodeInfo instanceof InetNodeInfo) {
            InetNodeInfo inetNodeInfo = (InetNodeInfo) nodeInfo;

            if (isValidNodeInfo(inetNodeInfo)) {
                InetNode inetNode = new InetNode(this, (InetNodeInfo) nodeInfo);
                LOGGER.exiting(CLASS_NAME, "getRemoteNode", inetNode);
                return inetNode;
            } else {
                SubnetException exception = new SubnetException("invalid nodeInfo: " + nodeInfo);
                LOGGER.throwing(CLASS_NAME, "getRemoteNode", exception);
                throw exception;
            }
        } else {
            SubnetException exception = new SubnetException("invalid nodeInfo: " + nodeInfo);
            LOGGER.throwing(CLASS_NAME, "getRemoteNode", exception);
            throw exception;
        }
    }

    public InetAddress getLocalAddress() {
        if (localAddress != null) {
            return localAddress;
        } else {
            return loopbackAddress;
        }
    }

    @Override
    public synchronized Node getLocalNode() {
        LOGGER.entering(CLASS_NAME, "getLocalNode");
        
        if (localNode == null) {
            localNode = new InetNode(this, getLocalAddress());
        }

        LOGGER.exiting(CLASS_NAME, "getLocalNode", localNode);
        return localNode;
    }

    @Override
    public synchronized Node getGroupNode() {
        LOGGER.entering(CLASS_NAME, "getGroupNode");
        
        if (groupNode == null) {
            groupNode = new InetNode(this, multicastAddress);
        }

        LOGGER.exiting(CLASS_NAME, "getGroupNode", groupNode);
        return groupNode;
    }
}
