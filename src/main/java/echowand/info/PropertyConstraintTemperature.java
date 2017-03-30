package echowand.info;

import echowand.util.Constraint;
import echowand.util.ConstraintShort;
import echowand.util.ConstraintUnion;

public class PropertyConstraintTemperature implements Constraint {
	private static final short MIN_VALUE = (short)0xf554;
    private static final short MAX_VALUE = (short)0x7ffd;
    private static final short OVERFLOW = (short)0x8000;
    private static final short UNDERFLOW = (short)0x7fff;
    
    private ConstraintUnion constraint;
    
    public PropertyConstraintTemperature() {
        ConstraintShort normal = new ConstraintShort(MIN_VALUE, MAX_VALUE);
        
        ConstraintShort overflow = new ConstraintShort(OVERFLOW);
        ConstraintShort underflow = new ConstraintShort(UNDERFLOW);
        ConstraintUnion invalid = new ConstraintUnion(overflow, underflow);
        
        constraint = new ConstraintUnion(normal, invalid);
    }

    @Override
    public boolean isValid(byte[] data) {
        return constraint.isValid(data);
    }
    
    @Override
    public String toString() {
        return constraint.toString();
    }
}
