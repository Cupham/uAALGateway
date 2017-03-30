package echowand.net;
/*
 * @author HiepNguyen
 */
public interface Connection {
	public NodeInfo getLocalNodeInfo();
	public NodeInfo getRemoteNodeInfo();
	public boolean isClosed();
	public void close() throws NetworkException;
	public void send(CommonFrame commonFrame) throws NetworkException;
	public CommonFrame receive() throws NetworkException;
}
