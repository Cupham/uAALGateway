package echowand.info;

import echowand.util.Constraint;
import echowand.util.ConstraintShort;
import echowand.util.ConstraintUnion;

public class PropertyConstraintAirSpeed implements Constraint {
	
	private static final short MIN_VALUE = (short)0x0000;
    private static final short MAX_VALUE = (short)0xfffd;
    private static final short OVERFLOW = (short)0xffff;
    private static final short UNDERFLOW = (short)0xfffe;
    
    private ConstraintUnion constraint;
    
    /**
     * PropertyConstraintAirSpeed
     */
    public PropertyConstraintAirSpeed() {
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
     * PropertyConstraintAirSpeed
     * @return PropertyConstraintAirSpeed
     */
    @Override
    public String toString() {
        return constraint.toString();
    }

}