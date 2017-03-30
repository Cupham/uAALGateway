package echowand.net;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.LinkedList;

import echowand.common.EOJ;
import echowand.common.ESV;

/*
 * @author HiepNguyen
 */
public class StandardPayload implements Payload {
	private EOJ seoj;
    private EOJ deoj;
    private ESV esv;
    private LinkedList<Property> firstProperties;
    private LinkedList<Property> secondProperties;
    
    public StandardPayload() {
        firstProperties = new LinkedList<Property>();
        secondProperties = new LinkedList<Property>();
    }
    
    public StandardPayload(EOJ seoj, EOJ deoj, ESV esv) {
        this();
        this.seoj = seoj;
        this.deoj = deoj;
        this.esv = esv;
    }
    
    public StandardPayload(byte[] bytes) throws InvalidDataException {
        this();
        if (bytes != null) {
            parse(bytes, 0);
        }
    }
    
    public StandardPayload(byte[] bytes, int offset) throws InvalidDataException {
        this();
        if (bytes != null) {
            parse(bytes, offset);
        }
    }
    
    private int parse(byte[] bytes, int offset) throws InvalidDataException {
        try {
            this.seoj = new EOJ(bytes, offset);
            offset += 3;
            this.deoj = new EOJ(bytes, offset);
            offset += 3;
            this.esv = ESV.fromByte(bytes[offset++]);
            if (bytes.length > offset) {
                offset = parseProperties(firstProperties, bytes, offset);
            }
            if (esv.isSetGet()) {
                offset = parseProperties(secondProperties, bytes, offset);
            }
            return offset;
        } catch (Exception e) {
            throw new InvalidDataException("invalid data at: " + offset, e);
        }
    }
    
    private int parseProperties(LinkedList<Property> properties, byte[] bytes, int offset) {
        int len = 0xff & (int)bytes[offset++];
        for (int i=0; i<len; i++) {
            Property property = new Property(bytes, offset);
            properties.add(property);
            offset += 2 + (0xff & property.getPDC());
        }
        return offset;
    }
    
    public void setSEOJ(EOJ seoj) {
        this.seoj = seoj;
    }
    
    public EOJ getSEOJ() {
        return this.seoj;
    }
    
    public void setDEOJ(EOJ deoj) {
        this.deoj = deoj;
    }
    
    public EOJ getDEOJ() {
        return this.deoj;
    }
    
    public void setESV(ESV esv) {
        this.esv = esv;
    }
    
    public ESV getESV() {
        return this.esv;
    }
    
    private boolean addProperty(LinkedList<Property> properties, Property property) {
        if (properties.size() < 0xff) {
            return properties.add(property);
        } else {
            return false;
        }
    }
    
    public boolean addFirstProperty(Property property) {
        return addProperty(firstProperties, property);
    }
    
    public Property getFirstPropertyAt(int index) {
        return firstProperties.get(index);
    }
    
    public byte getFirstOPC() {
        return (byte)firstProperties.size();
    }
    
    public boolean addSecondProperty(Property property) {
        return addProperty(secondProperties, property);
    }
    
    public Property getSecondPropertyAt(int index) {
        return secondProperties.get(index);
    }
    
    public byte getSecondOPC() {
        return (byte)secondProperties.size();
    }
    
    private int propertiesLength(Collection<Property> properties) {
        int len = 1;
        for (Property p : properties) {
            len += p.size();
        }
        return len;
    }

    private byte[] propertiesBytes(Collection<Property> properties) {
        int len = propertiesLength(properties);
        ByteBuffer buffer = ByteBuffer.allocate(len);
        buffer.put((byte) properties.size());
        for (Property p : properties) {
            buffer.put(p.toBytes());
        }
        return buffer.array();
    }
    
    @Override
    public int size() {
        int len = 7;
        len += propertiesLength(firstProperties);
        if (esv.isSetGet()) {
            len += propertiesLength(secondProperties);
        }
        return len;
    }
    
    @Override
    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(size());
        buffer.put(seoj.toBytes());
        buffer.put(deoj.toBytes());
        buffer.put(esv.toByte());
        buffer.put(propertiesBytes(firstProperties));
        if (esv.isSetGet()) {
            buffer.put(propertiesBytes(secondProperties));
        }
        return buffer.array();
    }
    
    private String propertiesToString(LinkedList<Property> properties) {
        StringBuilder builder = new StringBuilder();
        for (Property property : properties) {
            builder.append("(");
            builder.append(property);
            builder.append(")");
        }
        return builder.toString();
    }
    
    @Override
    public String toString() {
        String format = "SEOJ: %s DEOJ: %s ESV: %s %s";
        
        String propList1 = propertiesToString(firstProperties);
        
        String propString;
        if (esv.isSetGet()) {
            String propList2 = propertiesToString(secondProperties);
            String propFormat = "OPC1=%02x [%s] OPC2=%02x [%s]";
            propString = String.format(propFormat, getFirstOPC(), propList1, getSecondOPC(), propList2);
        } else {
            String propFormat = "OPC=%02x [%s]";
            propString = String.format(propFormat, getFirstOPC(), propList1);
        }
        return String.format(format, seoj, deoj, esv, propString);
    }
}
