package echowand.net;

import java.util.Arrays;

/*
 * @author HiepNguyen
 */

public class SimplePayload implements Payload {
	private byte[] payload;
	
	public SimplePayload() {
        this.payload = new byte[0];
    }
	
	public SimplePayload(byte[] payload) {
        this(payload, 0);
    }
	
	public SimplePayload(byte[] payload, int offset) {
        int length = payload.length - offset;
        this.payload = new byte[length];
        System.arraycopy(payload, offset, this.payload, 0, length);
    }
	
	public void setPayload(byte[] payload) {
        this.payload = Arrays.copyOf(payload, payload.length);
    }
	
	public byte[] getPayload() {
        return Arrays.copyOf(payload, payload.length);
    }
	
	@Override
    public int size() {
        return payload.length;
    }
	
	@Override
    public byte[] toBytes() {
        return Arrays.copyOf(payload, payload.length);
    }
	
	@Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (byte b : toBytes()) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
