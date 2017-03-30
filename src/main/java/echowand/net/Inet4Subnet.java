package echowand.net;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.LinkedList;

public class Inet4Subnet extends InetSubnet {
    
    public static final String LOOPBACK_ADDRESS = "127.0.0.1";

    public static final String MULTICAST_ADDRESS = "224.0.23.0";

    public Inet4Subnet() throws SubnetException {
        try {
            Inet4Address loopbackAddress = (Inet4Address)Inet4Address.getByName(LOOPBACK_ADDRESS);
            Inet4Address multicastAddress = (Inet4Address)Inet4Address.getByName(MULTICAST_ADDRESS);
            
            initialize(loopbackAddress, multicastAddress, DEFAULT_PORT_NUMBER);
        } catch (UnknownHostException ex) {
            throw new SubnetException("catched exception", ex);
        }
    }

    public Inet4Subnet(Inet4Address localAddress) throws SubnetException {
        if (localAddress == null) {
            throw new SubnetException("invalid address: " + localAddress);
        }
        
        try {
            Inet4Address loopbackAddress = (Inet4Address)Inet4Address.getByName(LOOPBACK_ADDRESS);
            Inet4Address multicastAddress = (Inet4Address)Inet4Address.getByName(MULTICAST_ADDRESS);
            
            initialize(localAddress, new LinkedList<NetworkInterface>(), loopbackAddress, multicastAddress, DEFAULT_PORT_NUMBER);
        } catch (UnknownHostException ex) {
            throw new SubnetException("catched exception", ex);
        }
    }

    public Inet4Subnet(Inet4Address localAddress, NetworkInterface... receiverInterfaces) throws SubnetException {
        if (localAddress == null) {
            throw new SubnetException("invalid address: " + localAddress);
        }
        
        try {
            Inet4Address loopbackAddress = (Inet4Address)Inet4Address.getByName(LOOPBACK_ADDRESS);
            Inet4Address multicastAddress = (Inet4Address)Inet4Address.getByName(MULTICAST_ADDRESS);
            
            initialize(localAddress, Arrays.asList(receiverInterfaces), loopbackAddress, multicastAddress, DEFAULT_PORT_NUMBER);
        } catch (UnknownHostException ex) {
            throw new SubnetException("catched exception", ex);
        }
    }

    public Inet4Subnet(NetworkInterface networkInterface) throws SubnetException {
        if (networkInterface == null) {
            throw new SubnetException("invalid network interface: " + networkInterface);
        }
        
        try {
            Inet4Address loopbackAddress = (Inet4Address)Inet4Address.getByName(LOOPBACK_ADDRESS);
            Inet4Address multicastAddress = (Inet4Address)Inet4Address.getByName(MULTICAST_ADDRESS);
            
            initialize(networkInterface, new LinkedList<NetworkInterface>(), loopbackAddress, multicastAddress, DEFAULT_PORT_NUMBER);
        } catch (UnknownHostException ex) {
            throw new SubnetException("catched exception", ex);
        }
    }

    public Inet4Subnet(NetworkInterface networkInterface, NetworkInterface... receiverInterfaces) throws SubnetException {
        if (networkInterface == null) {
            throw new SubnetException("invalid network interface: " + networkInterface);
        }
        
        try {
            Inet4Address loopbackAddress = (Inet4Address)Inet4Address.getByName(LOOPBACK_ADDRESS);
            Inet4Address multicastAddress = (Inet4Address)Inet4Address.getByName(MULTICAST_ADDRESS);
            
            initialize(networkInterface, Arrays.asList(receiverInterfaces), loopbackAddress, multicastAddress, DEFAULT_PORT_NUMBER);
        } catch (UnknownHostException ex) {
            throw new SubnetException("catched exception", ex);
        }
    }

    public static Inet4Subnet startSubnet() throws SubnetException {
        Inet4Subnet subnet = new Inet4Subnet();
        subnet.startService();
        return subnet;
    }

    public static Inet4Subnet startSubnet(Inet4Address localAddress) throws SubnetException {
        Inet4Subnet subnet = new Inet4Subnet(localAddress);
        subnet.startService();
        return subnet;
    }

    public static Inet4Subnet startSubnet(Inet4Address localAddress, NetworkInterface... receiverInterfaces) throws SubnetException {
        Inet4Subnet subnet = new Inet4Subnet(localAddress, receiverInterfaces);
        subnet.startService();
        return subnet;
    }

    public static Inet4Subnet startSubnet(NetworkInterface networkInterface) throws SubnetException {
        Inet4Subnet subnet = new Inet4Subnet(networkInterface);
        subnet.startService();
        return subnet;
    }

    public static Inet4Subnet startSubnet(NetworkInterface networkInterface, NetworkInterface... receiverInterfaces) throws SubnetException {
        Inet4Subnet subnet = new Inet4Subnet(networkInterface, receiverInterfaces);
        subnet.startService();
        return subnet;
    }

    @Override
    public boolean isValidAddress(InetAddress address) {
        return (address instanceof Inet4Address);
    }
}
