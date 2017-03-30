package echowand.object;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import echowand.common.Data;

class ExtraData {
    ArrayList<Data> extraDataList;
    public ExtraData() {
        extraDataList = new ArrayList<Data>();
    }
    public ExtraData(List<Data> data) {
        extraDataList = new ArrayList<Data>(data);
    }
    
    public boolean isEmpty() {
        return extraDataList.isEmpty();
    }
    
    public int size() {
        return extraDataList.size();
    }
    
    public Data get(int i) {
        return extraDataList.get(i);
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ExtraData)) {
            return false;
        }
        
        ExtraData other = (ExtraData) o;
        if (this.size() != other.size()) {
            return false;
        }
        int len = this.size();
        for (int i = 0; i < len; i++) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

public class ObjectData {
    private Data data;
    private ExtraData extraData;

    public ObjectData(Data data) {
        this.data = data;
        this.extraData = new ExtraData();
    }

    public ObjectData(byte... data) {
        this.data = new Data(data);
        this.extraData = new ExtraData();
    }

    public ObjectData(List<Data> dataList) {
        if (dataList.isEmpty()) {
            this.data = new Data();
        } else {
            this.data = dataList.get(0);
            int len = dataList.size();
            LinkedList<Data> extras = new LinkedList<Data>();
            for (int i=1; i<len; i++) {
                extras.add(dataList.get(i));
            }
            this.extraData = new ExtraData(extras);
        }
    }

    public Data getData() {
        return data;
    }

    public int getExtraSize() {
        return extraData.size();
    }

    public Data getExtraDataAt(int i) {
        return extraData.get(i);
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public byte get(int i) {
        return data.get(i);
    }

    public byte[] toBytes() {
        return data.toBytes();
    }

    @Override
    public String toString() {
        return data.toString();
    }

    @Override
    public boolean equals(Object otherObj) {
        if (!(otherObj instanceof ObjectData)) {
            return false;
        }
        
        ObjectData other = (ObjectData) otherObj;
        return this.data.equals(other.data)
                && this.extraData.equals(other.extraData);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.data.hashCode();
        hash = 53 * hash + (this.extraData != null ? this.extraData.hashCode() : 0);
        return hash;
    }
}
