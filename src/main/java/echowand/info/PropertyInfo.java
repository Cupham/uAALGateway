package echowand.info;

import java.util.Arrays;

import echowand.common.Data;
import echowand.common.EPC;
import echowand.util.Constraint;
import echowand.util.ConstraintSize;

public class PropertyInfo {
	public EPC epc;
    public boolean gettable;
    public boolean settable;
    public boolean observable;
    public Constraint constraint;
    public byte[] initialData;
    
    public PropertyInfo(EPC epc, boolean gettable, boolean settable, boolean observable, int initialSize) {
        this(epc, gettable, settable, observable, new byte[initialSize]);
    }
    
    public PropertyInfo(EPC epc, boolean gettable, boolean settable, boolean observable, int initialSize, Constraint constraint) {
        this(epc, gettable, settable, observable, new byte[initialSize], constraint);
    }
    
    public PropertyInfo(EPC epc, boolean gettable, boolean settable, boolean observable, byte[] initialData) {
        this(epc, gettable, settable, observable, initialData, new ConstraintSize(initialData.length));
    }
    
    public PropertyInfo(EPC epc, boolean gettable, boolean settable, boolean observable, byte[] initialData, Constraint constraint) {
        this.epc = epc;
        this.gettable = gettable;
        this.settable = settable;
        this.observable = observable;
        this.constraint = constraint;
        this.initialData = Arrays.copyOf(initialData, initialData.length);
    }
    
    @Override
    public boolean equals(Object prop) {
        if (! (prop instanceof PropertyInfo)) {
            return false;
        }
        
        return this.epc == ((PropertyInfo)prop).epc;
    }
    
    @Override
    public int hashCode() {
        return this.epc.hashCode();
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PropertyInfo(EPC: ");
        builder.append(epc);
        builder.append(" Gettable: ");
        builder.append(gettable);
        builder.append(" Settable: ");
        builder.append(settable);
        builder.append(" Observable: ");
        builder.append(observable);
        builder.append(" InitialData: ");
        builder.append(new Data(initialData));
        builder.append(" Constraint: ");
        builder.append(constraint);
        builder.append(")");
        return builder.toString();
    }
}
