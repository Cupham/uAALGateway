package echowand.info;

import echowand.util.Constraint;
import echowand.util.ConstraintShort;
import echowand.util.ConstraintUnion;

public class PropertyConstraintIlluminance implements Constraint {
    private static final short MIN_VALUE = (short)0;
    private static final short MAX_VALUE = (short)0xfffd;
    private static final short OVERFLOW = (short)0xffff;
    private static final short UNDERFLOW = (short)0xfffe;
    
    private ConstraintUnion constraint;
    
    /**
     * PropertyConstraintIlluminance
     */
    public PropertyConstraintIlluminance() {
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
    
    /**
     * PropertyConstraintIlluminance
     * @return PropertyConstraintIlluminance
     */
    @Override
    public String toString() {
        return constraint.toString();
    }
}
