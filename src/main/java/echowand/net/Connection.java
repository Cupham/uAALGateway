package echowand.net;
/*
 * @author CuPham
 */
public interface Connection {
	public NodeInfo getLocalNodeInfo();
	public NodeInfo getRemoteNodeInfo();
	public boolean isClosed();
	public void close() throws NetworkException;
	public void send(CommonFrame commonFrame) throws NetworkException;
	public CommonFrame receive() throws NetworkException;
}
