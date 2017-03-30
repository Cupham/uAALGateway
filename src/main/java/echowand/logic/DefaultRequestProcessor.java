package echowand.logic;

import echowand.net.Frame;
import echowand.net.Subnet;

public class DefaultRequestProcessor implements RequestProcessor {

    public DefaultRequestProcessor() {}

    @Override
    public boolean processSetI(Subnet subnet, Frame frame, boolean processed){ return false; }

    @Override
    public boolean processSetC(Subnet subnet, Frame frame, boolean processed){ return false; }

    @Override
    public boolean processGet(Subnet subnet, Frame frame, boolean processed){ return false; }

    @Override
    public boolean processSetGet(Subnet subnet, Frame frame, boolean processed){ return false; }

    @Override
    public boolean processINF_REQ(Subnet subnet, Frame frame, boolean processed){ return false; }

    @Override
    public boolean processINF(Subnet subnet, Frame frame, boolean processed){ return false; }

    @Override
    public boolean processINFC(Subnet subnet, Frame frame, boolean processed){ return false; }
}
