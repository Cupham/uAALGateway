package echowand.net;

public interface Subnet {

    public boolean send(Frame frame) throws SubnetException;

    public Frame receive() throws SubnetException;

    public Node getLocalNode();

    public Node getRemoteNode(String name) throws SubnetException;

    public Node getRemoteNode(NodeInfo nodeInfo) throws SubnetException;

    public Node getGroupNode();
}
