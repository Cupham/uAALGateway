package echowand.net;

import echowand.common.Data;
import echowand.common.EPC;

public class Property {
	private EPC epc;
    private Data edt;
    
    public Property() {
        this(EPC.Invalid);
    }
    
    public Property(EPC epc) {
        this(epc, new Data());
    }
    
    public Property(EPC epc, Data data) {
        this.epc = epc;
        this.edt = data;
    }
    
    public Property(byte[] bytes) {
        this(bytes, 0);
    }
    
    public Property(byte[] bytes, int offset) {
        this.epc = EPC.fromByte(bytes[offset]);
        int pdc = 0xff & (int)bytes[offset+1];
        this.edt = new Data(bytes, offset+2, pdc);
    }
    
    public void setEPC(EPC epc) {
        this.epc = epc;
    }
    
    public EPC getEPC() {
        return epc;
    }
    
    public byte getPDC() {
        if (this.edt == null) {
            return 0;
        } else {
            return (byte)edt.size();
        }
    }
    
    public void setEDT(Data edt) {
        this.edt = edt;
    }
    
    public Data getEDT() {
        return edt;
    }
    
    public byte[] toBytes() {
        int pdc = 0xff & (int)getPDC();
        byte[] bytes = new byte[2 + pdc];
        bytes[0] = epc.toByte();
        bytes[1] = getPDC();
        if (pdc > 0) {
            edt.copyBytes(0, bytes, 2, pdc);
        }
        return bytes;
    }
    
    public int size() {
        return 2 + (0xff & (int)getPDC());
    }
    
    @Override
    public String toString() {
        if (getPDC() == 0) {
            String format = "EPC=%02x PDC=%02x";
            return String.format(format, epc.toByte(), getPDC());
        } else {
            String format = "EPC=%02x PDC=%02x EDT=%s";
            return String.format(format, epc.toByte(), getPDC(), getEDT().toString());
        }
    }
}
