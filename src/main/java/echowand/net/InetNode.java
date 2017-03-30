package echowand.net;

import java.net.InetAddress;

public class InetNode implements Node {
    private InetSubnet subnet;
    private InetNodeInfo nodeInfo;

    public InetNode(InetSubnet subnet, InetAddress address) {
        this(subnet, new InetNodeInfo(address));
    }

    public InetNode(InetSubnet subnet, InetNodeInfo nodeInfo) {
        this.subnet = subnet;
        this.nodeInfo = nodeInfo;
    }

    @Override
    public NodeInfo getNodeInfo() {
        return nodeInfo;
    }

    public InetAddress getAddress() {
        return nodeInfo.getAddress();
    }
    
    @Override
    public boolean isMemberOf(Subnet subnet) {
        return this.subnet == subnet;
    }

    @Override
    public String toString() {
        return nodeInfo.toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (! (o instanceof InetNode)) {
            return false;
        }
        
        InetNode node = (InetNode)o;
        return (getNodeInfo().equals(node.getNodeInfo()) && subnet.equals(node.subnet));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.nodeInfo != null ? this.nodeInfo.hashCode() : 0);
        return hash;
    }
}