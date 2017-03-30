package echowand.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import echowand.util.Pair;

public class UDPNetwork {
    private static final Logger LOGGER = Logger.getLogger(UDPNetwork.class.getName());
    private static final String CLASS_NAME = UDPNetwork.class.getName();

    public static final short  DEFAULT_BUFFER_SIZE = 1500;
    
    private NetworkInterface networkInterface;
    private List<NetworkInterface> receiverInterfaces;
    private InetAddress localAddress;
    private InetAddress multicastAddress;
    private MulticastSocket multicastSocket;
    private int portNumber;
    private int bufferSize = DEFAULT_BUFFER_SIZE;
    private boolean inService = false;
    
    public UDPNetwork(InetAddress localAddress, Collection<? extends NetworkInterface> receiverInterfaces, InetAddress multicastAddress, int portNumber) {
        this.localAddress = localAddress;
        this.networkInterface = null;
        this.receiverInterfaces = new LinkedList<NetworkInterface>(receiverInterfaces);
        this.multicastAddress = multicastAddress;
        this.portNumber = portNumber;
    }

    public UDPNetwork(NetworkInterface networkInterface, Collection<? extends NetworkInterface> receiverInterfaces, InetAddress multicastAddress, int portNumber) {
        this.localAddress = null;
        this.networkInterface = networkInterface;
        this.receiverInterfaces = new LinkedList<NetworkInterface>(receiverInterfaces);
        this.multicastAddress = multicastAddress;
        this.portNumber = portNumber;
    }

    public UDPNetwork(Collection<? extends NetworkInterface> receiverInterfaces, InetAddress multicastAddress, int portNumber) {
        this.localAddress = null;
        this.networkInterface = null;
        this.receiverInterfaces = new LinkedList<NetworkInterface>(receiverInterfaces);
        this.multicastAddress = multicastAddress;
        this.portNumber = portNumber;
    }

    public UDPNetwork(InetAddress multicastAddress, int portNumber) {
        this.localAddress = null;
        this.networkInterface = null;
        this.receiverInterfaces = new LinkedList<NetworkInterface>();
        this.multicastAddress = multicastAddress;
        this.portNumber = portNumber;
    }
    
    private synchronized void closeSocket() {
        LOGGER.entering(CLASS_NAME, "closeSocket");
        
        if (multicastSocket != null) {
            multicastSocket.close();
            multicastSocket = null;
        }
            
        inService = false;
        
        LOGGER.exiting(CLASS_NAME, "closeSocket");
    }
    
    private synchronized void openSocket() throws NetworkException {
        LOGGER.entering(CLASS_NAME, "openSocket");
        
        try {
            multicastSocket = new MulticastSocket(getPortNumber());

            if (localAddress != null) {
                multicastSocket.setInterface(localAddress);
            }
            
            if (networkInterface != null) {
                multicastSocket.setNetworkInterface(networkInterface);
            }
            
            multicastSocket.joinGroup(multicastAddress);
            
            if (!receiverInterfaces.isEmpty()) {
                InetSocketAddress saddr = new InetSocketAddress(multicastAddress, getPortNumber());
                for (NetworkInterface receiverInterface : receiverInterfaces) {
                    multicastSocket.joinGroup(saddr, receiverInterface);
                }
            }
            
            multicastSocket.setLoopbackMode(false);
            multicastSocket.setReuseAddress(false);

            inService = true;
        } catch (IOException ex) {
            closeSocket();
            NetworkException exception = new NetworkException("catched exception", ex);
            LOGGER.throwing(CLASS_NAME, "openSocket", exception);
            throw exception;
        }
        
        LOGGER.exiting(CLASS_NAME, "openSocket");
    }

    public int getPortNumber() {
        return portNumber;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public synchronized boolean isInService() {
        return inService;
    }

    public synchronized boolean stopService() {
        LOGGER.entering(CLASS_NAME, "stopService");
        boolean result;
        
        if (inService) {
            closeSocket();
            result = !inService;
        } else {
            result = false;
        }
        
        LOGGER.exiting(CLASS_NAME, "stopService", result);
        return result;
    }

    public synchronized boolean startService() throws NetworkException {
        LOGGER.entering(CLASS_NAME, "startService");
        
        boolean result;
        
        if (inService) {
            result = false;
        } else {
            closeSocket();
            openSocket();
            result = inService;
        }
        
        LOGGER.exiting(CLASS_NAME, "startService", result);
        return result;
    }

    public synchronized void send(InetNodeInfo remoteNodeInfo, CommonFrame commonFrame) throws NetworkException {
        LOGGER.entering(CLASS_NAME, "send", new Object[]{remoteNodeInfo, commonFrame});
        
        if (!isInService()) {
            NetworkException exception = new NetworkException("not working");
            LOGGER.throwing(CLASS_NAME, "send", exception);
            throw exception;
        }
        
        byte[] data = commonFrame.toBytes();

        try {
            InetAddress receiver = remoteNodeInfo.getAddress();
            int port = getPortNumber();
            
            DatagramPacket packet = new DatagramPacket(data, data.length, receiver, port);
            
            multicastSocket.send(packet);
        } catch (IOException ex) {
            NetworkException exception = new NetworkException("catched exception", ex);
            LOGGER.throwing(CLASS_NAME, "send", exception);
            throw exception;
        }
        
        LOGGER.exiting(CLASS_NAME, "send");
    }
    
    private DatagramPacket receivePacket() throws IOException {
        byte[] packetData = new byte[this.bufferSize];
        DatagramPacket packet = new DatagramPacket(packetData, packetData.length);
        multicastSocket.receive(packet);
        return packet;
    }
    
    private byte[] getData(DatagramPacket packet) throws IOException {
        byte[] packetData = packet.getData();

        int len = packet.getLength();
        byte[] data = new byte[len];
        
        System.arraycopy(packetData, 0, data, 0, len);

        return data;
    }

    public Pair<InetNodeInfo, CommonFrame> receive()  throws NetworkException {
        LOGGER.entering(CLASS_NAME, "receive");
        
        if (!isInService()) {
            throw new NetworkException("not working");
        }
        
        try {
            DatagramPacket packet = receivePacket();
            byte[] data = getData(packet);

            CommonFrame commonFrame = new CommonFrame(data);
            
            InetAddress addr = packet.getAddress();
            
            Pair<InetNodeInfo, CommonFrame> pair = new Pair<InetNodeInfo, CommonFrame>(new InetNodeInfo(addr), commonFrame);
            LOGGER.exiting(CLASS_NAME, "receive", pair);
            return pair;
        } catch (IOException ex) {
            NetworkException exception = new NetworkException("catched exception", ex);
            LOGGER.throwing(CLASS_NAME, "receive", exception);
            throw exception;
        } catch (InvalidDataException ex) {
            NetworkException exception = new NetworkException("invalid frame", ex);
            LOGGER.throwing(CLASS_NAME, "receive", exception);
            throw exception;
        }
    }
}
