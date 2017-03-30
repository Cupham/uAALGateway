package echowand.info;

import echowand.util.ConstraintByte;

public class PropertyConstraintOnOff extends ConstraintByte {
	
	public static final byte ON=0x30;
    
    public static final byte OFF=0x31;
    
    public PropertyConstraintOnOff() {
        super(ON, OFF);
    }
}
