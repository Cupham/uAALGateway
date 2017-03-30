package echowand.logic;

import echowand.net.Frame;
import echowand.net.Subnet;

public interface RequestProcessor {
    
    public boolean processSetI(Subnet subnet, Frame frame, boolean processed);
    
    public boolean processSetC(Subnet subnet, Frame frame, boolean processed);
    
    public boolean processGet(Subnet subnet, Frame frame, boolean processed);
    
    public boolean processSetGet(Subnet subnet, Frame frame, boolean processed);
    
    public boolean processINF_REQ(Subnet subnet, Frame frame, boolean processed);
    
    public boolean processINF(Subnet subnet, Frame frame, boolean processed);
    
    public boolean processINFC(Subnet subnet, Frame frame, boolean processed);
}
