package echowand.object;

import java.util.LinkedList;

import echowand.common.EPC;

public interface LocalObjectDelegate {

    static class State {
        private boolean done = false;
        private boolean fail = false;
        private LinkedList<String> messages = new LinkedList<String>();

        public void setDone() {
            done = true;
        }

        public boolean isDone() {
            return done;
        }

        public void setFail() {
            fail = true;
        }

        public boolean isFail() {
            return fail;
        }

        public void addMessage(String message) {
            messages.add(message);
        }

        public int countMessages() {
            return messages.size();
        }

        public String getMessage(int index) {
            return messages.get(index);
        }
    }

    static class GetState extends State {
        private ObjectData data;

        public GetState(ObjectData data) {
            this.data = data;
        }

        public void setGetData(ObjectData data) {
            this.data = data;
        }

        public ObjectData getGetData() {
            return data;
        }
    }

    static class SetState extends State {
        private ObjectData newData;
        private ObjectData curData;

        public SetState(ObjectData newData, ObjectData curData) {
            this.newData = newData;
            this.curData = curData;
        }

        public void setSetData(ObjectData newData, ObjectData curData) {
            this.newData = newData;
            this.curData = curData;
        }

        public void setNewData(ObjectData data) {
            newData = data;
        }

        public ObjectData getNewData() {
            return newData;
        }

        public void setCurrentData(ObjectData data) {
            curData = data;
        }

        public ObjectData getCurrentData() {
            return curData;
        }

        public boolean isDataChanged() {
            if (curData == null) {
                return newData != null;
            } else {
                return !curData.equals(newData);
            }
        }
    }

    static class NotifyState extends State {
    }

    void getData(GetState result, LocalObject object, EPC epc);

    void setData(SetState result, LocalObject object, EPC epc, ObjectData newData, ObjectData curData);

    void notifyDataChanged(NotifyState result, LocalObject object, EPC epc, ObjectData curData, ObjectData oldData);
}
