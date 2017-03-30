package echowand.net;

public interface Node {

    public NodeInfo getNodeInfo();

    public boolean isMemberOf(Subnet subnet);
}
