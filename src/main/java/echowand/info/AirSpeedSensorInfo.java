package echowand.info;

import echowand.common.ClassEOJ;
import echowand.common.EPC;

public class AirSpeedSensorInfo extends DeviceObjectInfo {
    /**
     * AirSpeedSensorInfo
     */
    public AirSpeedSensorInfo() {
        setClassEOJ(new ClassEOJ((byte)0x00, (byte)(0x1f)));
        
        add(EPC.xE0, true, false, false, 2, new PropertyConstraintAirSpeed());
    }
}
