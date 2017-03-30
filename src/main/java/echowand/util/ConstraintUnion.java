package echowand.util;

/*
 * @author HiepNguyen
 */
public class ConstraintUnion implements Constraint {
	Constraint constraint1;
    Constraint constraint2;
    
    public ConstraintUnion(Constraint constraint1, Constraint constraint2) {
        this.constraint1 = constraint1;
        this.constraint2 = constraint2;
    }
    
    @Override
    public boolean isValid(byte[] data) {
        return constraint1.isValid(data) || constraint2.isValid(data);
    }
    
    @Override
    public String toString() {
        return String.format("(%s | %s)", constraint1.toString(), constraint2.toString());
    }
}
