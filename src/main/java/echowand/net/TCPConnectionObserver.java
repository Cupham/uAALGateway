package echowand.net;

public interface TCPConnectionObserver {

    public void notifyReceived(TCPConnection connection, CommonFrame commonFrame);

    public void notifySent(TCPConnection connection, CommonFrame commonfram);

    public void notifyClosed(TCPConnection connection);
}
