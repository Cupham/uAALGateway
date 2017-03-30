package echowand.logic;

import echowand.net.Frame;
import echowand.net.Subnet;

public interface TransactionListener {

    public void begin(Transaction t);

    public void send(Transaction t, Subnet subnet, Frame frame, boolean success);

    public void receive(Transaction t, Subnet subnet, Frame frame);

    public void finish(Transaction t);
}
