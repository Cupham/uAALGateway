package echowand.object;

import echowand.common.EPC;

public class LocalObjectDefaultDelegate implements LocalObjectDelegate {

    @Override
    public void getData(GetState result, LocalObject object, EPC epc){}

    @Override
    public void setData(SetState result, LocalObject object, EPC epc, ObjectData newData, ObjectData curData){}

    @Override
    public void notifyDataChanged(NotifyState result, LocalObject object, EPC epc, ObjectData curData, ObjectData oldData){}
}
