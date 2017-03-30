package echowand.info;

import echowand.common.ClassEOJ;
import echowand.common.EPC;
/*
 * 
 */
public class TemperatureSensorInfo extends DeviceObjectInfo {
	public TemperatureSensorInfo() {
        setClassEOJ(new ClassEOJ((byte)0x00, (byte)(0x11)));
        
        add(EPC.xE0, true, false, false, 2, new PropertyConstraintTemperature());
    }
}
