package echowand.object;

import echowand.common.EOJ;
import echowand.common.EPC;

public interface EchonetObject {

    public EOJ getEOJ();

    public boolean contains(EPC epc) throws EchonetObjectException;

    public boolean isGettable(EPC epc) throws EchonetObjectException;

    public boolean isSettable(EPC epc) throws EchonetObjectException;

    public boolean isObservable(EPC epc) throws EchonetObjectException;

    public ObjectData getData(EPC epc) throws EchonetObjectException;

    public boolean setData(EPC epc, ObjectData data) throws EchonetObjectException;
}
