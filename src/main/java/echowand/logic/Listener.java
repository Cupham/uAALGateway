package echowand.logic;

import echowand.net.Frame;
import echowand.net.Subnet;

public interface Listener {

    public boolean process(Subnet subnet, Frame frame, boolean processed);
}
