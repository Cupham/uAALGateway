package echowand.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPAcceptor {
    private static final Logger LOGGER = Logger.getLogger(TCPAcceptor.class.getName());
    private static final String CLASS_NAME = TCPAcceptor.class.getName();
    
    private InetAddress address;
    private int port;
    private ServerSocket serverSocket;
    private LinkedList<TCPAcceptorObserver> observers;

    public TCPAcceptor(int port) {
        this(null, port);
    }

    public TCPAcceptor(InetAddress address, int port) {
        this.address = address;
        this.port = port;
        observers = new LinkedList<TCPAcceptorObserver>();
    }
    
    private synchronized LinkedList<TCPAcceptorObserver> cloneObservers() {
        return new LinkedList<TCPAcceptorObserver>(observers);
    }
    
    private void notifyAccepted(TCPConnection connection) {
        LOGGER.entering(CLASS_NAME, "notifyAccepted");
        
        for (TCPAcceptorObserver observer : cloneObservers()) {
            observer.notifyAccepted(connection);
        }
        
        LOGGER.exiting(CLASS_NAME, "notifyAccepted");
    }

    public synchronized boolean addObserver(TCPAcceptorObserver observer) {
        return observers.add(observer);
    }

    public synchronized boolean removeObserver(TCPAcceptorObserver observer) {
        return observers.remove(observer);
    }

    public synchronized boolean isInService() {
        return serverSocket != null;
    }
    
    private synchronized  ServerSocket getServerSocket() {
        return serverSocket;
    }

    public TCPConnection accept() throws NetworkException {
        LOGGER.entering(CLASS_NAME, "accept");
        
        ServerSocket ss = getServerSocket();
        
        if (ss == null) {
            NetworkException exception = new NetworkException("not working");
            LOGGER.throwing(CLASS_NAME, "accept", exception);
            throw exception;
        }
        
        try {
            Socket socket = ss.accept();
            NodeInfo localNodeInfo = new InetNodeInfo(socket.getLocalAddress());
            NodeInfo remoteNodeInfo = new InetNodeInfo(socket.getInetAddress());
            TCPConnection connection = new TCPConnection(socket, localNodeInfo, remoteNodeInfo);
            
            notifyAccepted(connection);
            
            LOGGER.exiting(CLASS_NAME, "accept", connection);
            return connection;
        } catch (IOException ex) {
            NetworkException exception = new NetworkException("catched exception", ex);
            LOGGER.throwing(CLASS_NAME, "accept", exception);
            throw exception;
        }
    }

    public synchronized boolean startService() throws NetworkException {
        LOGGER.entering(CLASS_NAME, "startService");

        if (serverSocket != null) {
            LOGGER.exiting(CLASS_NAME, "startService", false);
            return false;
        }
        
        try {
            serverSocket = new ServerSocket();
            serverSocket.setReuseAddress(true);
            
            InetSocketAddress saddr;
            
            if (address == null) {
                saddr = new InetSocketAddress(port);
            } else {
                saddr = new InetSocketAddress(address, port);
            }
            serverSocket.bind(saddr);
        } catch (IOException ex) {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException ex1) {
                }
            }
            
            NetworkException exception = new NetworkException("catched exception", ex);
            LOGGER.throwing(CLASS_NAME, "startService", exception);
            throw exception;
        }
        
        LOGGER.exiting(CLASS_NAME, "startService", true);
        return true;
    }

    public synchronized boolean stopService() {
        LOGGER.entering(CLASS_NAME, "stopService");
        
        boolean result;
        
        if (serverSocket == null) {
            result = false;
        } else {
            try {
                serverSocket.close();
                result = true;
            } catch (IOException ex) {
                LOGGER.logp(Level.WARNING, CLASS_NAME, "stopService", "catched exception", ex);
                result = false;
            }

            serverSocket = null;
        }

        LOGGER.exiting(CLASS_NAME, "stopService", result);
        return result;
    }
}
