package echowand.net;

import java.net.InetAddress;

public class InetNodeInfo implements NodeInfo {
    private InetAddress address;

    public InetNodeInfo(InetAddress address) {
        this.address = address;
    }

    public InetAddress getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return address.getHostAddress();
    }
    
    @Override
    public boolean equals(Object o) {
        if (! (o instanceof InetNodeInfo)) {
            return false;
        }
        
        InetNodeInfo info = (InetNodeInfo)o;
        return (this.address.equals(info.address));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.address != null ? this.address.hashCode() : 0);
        return hash;
    }
}
