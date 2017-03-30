package echowand.info;

import echowand.common.ClassEOJ;
import echowand.info.DeviceObjectInfo;

public class IlluminanceSensorInfo extends DeviceObjectInfo {
    /**
     * IlluminanceSensorInfo
     */
    public IlluminanceSensorInfo() {
        setClassEOJ(new ClassEOJ((byte)0x00, (byte)(0x0d)));
    }
}
