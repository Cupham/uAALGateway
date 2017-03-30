package echowand.net;

public class Frame {
	private Node sender;
    private Node receiver;
    private CommonFrame commonFrame;
    private Connection connection;
    
    public Frame(Node sender, Node receiver, CommonFrame commonFrame) {
        this.sender = sender;
        this.receiver = receiver;
        this.commonFrame = commonFrame;
        this.connection = null;
    }
    
    public Frame(Node sender, Node receiver, CommonFrame commonFrame, Connection connection) {
        this.sender = sender;
        this.receiver = receiver;
        this.commonFrame = commonFrame;
        this.connection = connection;
    }
    
    public Connection setConnection(Connection connection) {
        Connection lastConnection = this.connection;
        this.connection = connection;
        return lastConnection;
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public CommonFrame getCommonFrame() {
        return commonFrame;
    }
    
    public Node getReceiver() {
        return receiver;
    }
    
    public Node getSender() {
        return sender;
    }
    
    @Override
    public String toString() {
        String format = "[Sender=%s Receiver=%s %s]";
        return String.format(format, sender, receiver, commonFrame);
    }
}
