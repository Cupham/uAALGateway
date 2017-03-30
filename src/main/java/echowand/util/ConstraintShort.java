package echowand.util;

import echowand.util.ConstraintSize;

public class ConstraintShort extends ConstraintSize {
	private boolean isSigned;
    private int minValue;
    private int maxValue;
    
    private int U(short s) {
        return 0x0000ffff & (s);
    }
    
    private short S(byte[] data) {
        int h = ((int)data[0]) << 8;
        int l = 0x000000ff & (int)data[1];
        return (short)(h | l);
    }
    
    public ConstraintShort() {
        this((short)0x0000, (short)0xffff);
    }
    
    public ConstraintShort(short value) {
        this(value, value);
    }
    
    public ConstraintShort(short minValue, short maxValue) {
        super(2);
        if (minValue <= maxValue) {
            this.minValue = minValue;
            this.maxValue = maxValue;
            isSigned = true;
        } else {
            this.minValue = U(minValue);
            this.maxValue = U(maxValue);
            isSigned = false;
        }
    }
    
    @Override
    public boolean isValid(byte[] data) {
        if (!super.isValid(data)) {
            return false;
        }
        
        int value;
        if (isSigned) {
            value = S(data);
        } else {
            value = U(S(data));
        }
        return (minValue <= value) && (value <= maxValue);
    }
    
    @Override
    public String toString() {
        return String.format("Short[0x%04x, 0x%04x]", minValue, maxValue);
    }
}
