package echowand.net;

public interface TCPAcceptorObserver {

    public void notifyAccepted(TCPConnection connection);
}
