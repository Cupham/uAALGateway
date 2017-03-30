package echowand.net;

import java.nio.ByteBuffer;

import echowand.common.EOJ;
import echowand.common.ESV;

/*
 * @author HiepNguyen
 */
public class CommonFrame {
	private byte ehd1;
	private byte ehd2;
	private short tid;
	private Payload edata;
	
	public static final byte EHD1_ECHONET_LITE = (byte)0x01;
	public static final byte EHD2_STANDARD_PAYLOAD = (byte)0x81;
	public static final byte EHD2_ARBITRARY_PAYLOAD=(byte)0x82;
	
	public CommonFrame() {
        ehd1 = EHD1_ECHONET_LITE;
        ehd2 = EHD2_ARBITRARY_PAYLOAD;
        tid = (short) 0x0000;
        edata = new SimplePayload();
    }
	
	public CommonFrame(EOJ seoj, EOJ deoj, ESV esv) {
        ehd1 = EHD1_ECHONET_LITE;
        ehd2 = EHD2_STANDARD_PAYLOAD;
        tid = (short) 0x0000;
        edata = new StandardPayload(seoj, deoj, esv);
    }
	
	public CommonFrame(byte[] bytes) throws InvalidDataException {
        int offset = 0;
        try {
            this.ehd1 = bytes[offset++];
            this.ehd2 = bytes[offset++];
            this.tid = (short) ((0xff & (int) bytes[offset++]) << 8);
            this.tid |= (short) (0xff & (int) bytes[offset++]);
            if (this.ehd2 == EHD2_STANDARD_PAYLOAD) {
                this.edata = new StandardPayload(bytes, offset);
            } else {
                this.edata = new SimplePayload(bytes, offset);
            }
        } catch (Exception e) {
            throw new InvalidDataException("invalid data at: " + offset, e);
        }
    }
	
	public byte getEHD1() {
        return ehd1;
    }
	
	public byte getEHD2() {
        return ehd2;
    }
	
	public short getTID() {
        return tid;
    }
	
	public void setTID(short tid) {
        this.tid = tid;
    }
	
	public Payload getEDATA() {
        return edata;
    }
	
	public <P extends Payload> P getEDATA(Class<P> cls) {
        try {
            return cls.cast(edata);
        } catch (ClassCastException ex) {
            return null;
        }
    }
	
	public void setEDATA(Payload payload) {
        if (payload instanceof StandardPayload) {
            ehd2 = EHD2_STANDARD_PAYLOAD;
        } else {
            ehd2 = EHD2_ARBITRARY_PAYLOAD;
        }
        this.edata = payload;
    }
	
	public boolean isEchonetLite() {
        return (ehd1 == EHD1_ECHONET_LITE);
    }
	
	public boolean isStandardPayload() {
        return (ehd2 == EHD2_STANDARD_PAYLOAD);
    }
	
	public byte[] toBytes() {
        int len = 4;
        if (edata != null) {
            len += edata.size();
        }
        ByteBuffer buffer = ByteBuffer.allocate(len);
        buffer.put(ehd1);
        buffer.put(ehd2);
        buffer.putShort(tid);
        if (edata != null) {
            buffer.put(edata.toBytes());
        }
        return buffer.array();
    }
	
	@Override
    public String toString() {
        String format = "EHD1=%02x EHD2=%02x TID=%04x EDATA=[%s]";
        return String.format(format, ehd1, ehd2, tid, edata);
    }
}
