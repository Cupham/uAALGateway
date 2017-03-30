package echowand.object;

import echowand.common.EPC;

public interface RemoteObjectObserver {

    public void notifyData(RemoteObject object, EPC epc, ObjectData data);
}
