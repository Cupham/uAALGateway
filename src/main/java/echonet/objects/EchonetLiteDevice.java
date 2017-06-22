package echonet.objects;

import java.util.ArrayList;

import echowand.common.EOJ;
import echowand.object.EchonetObjectException;
import echowand.object.RemoteObject;

public class EchonetLiteDevice {
	
	private NodeProfileObject profileObj;
	private ArrayList<eDataObject> dataObjList;
	
	public EchonetLiteDevice() {
		this.dataObjList = new ArrayList<eDataObject>();
		this.profileObj = null;
	}
	
	public boolean addDataObject(eDataObject dataObj) throws EchonetObjectException {
		if(this.dataObjList == null) {
			this.dataObjList = new ArrayList<eDataObject>();
		}
		this.dataObjList.add(dataObj);
		return true;
	}
	public boolean addDataObject(EOJ eoj, RemoteObject rObj) throws EchonetObjectException{
		byte classGroupCode = eoj.getClassGroupCode();
		byte classCode = eoj.getClassCode();
		byte instanceCode = eoj.getInstanceCode();
		eDataObject dataObj = null;
		//init object class 
		
		switch (classGroupCode) {		
		case (byte) (0x00): // Sensor-related Device Class Group
			switch(classCode) {
			case (byte) (0x11): //temperature sensor
				System.out.println("   			Creating TemperatureSensor object from ECHONET frame...");
				dataObj = new eTemperatureSensor(instanceCode);
				break;
			case (byte) (0x12): //humidity sensor
				//TODO: implement humidity sensor class
				break;
			default:
				return false;
			}
		break;
		
		case (byte)(0x01): //air conditioner related class
			switch (classCode) {
			case (byte)(0x30):
				System.out.println("   			Creating Air-Conditioner object from ECHONET frame...");
				dataObj = new eAirConditioner(instanceCode);
				break;
			default:
				return false;
			}	
		break;	
		
			//TODO: implement other device class here
		default:
			return false;
			
		}

		if(dataObj != null) {
			dataObj.ParseDataFromRemoteObject(rObj);
			dataObj.ParseProfileObjectFromEPC(rObj);
			this.dataObjList.add(dataObj);
			return true;
		}
		return false;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof EchonetLiteDevice))
            return false;
        if (obj == this)
            return true;

        EchonetLiteDevice checkDevice = (EchonetLiteDevice) obj;
        return this.profileObj.equals(checkDevice.profileObj);
	}
	@Override
	public String toString() {
		StringBuilder rs = new StringBuilder();
		rs.append("\r\n*********************************************");
		rs.append("\r\n>Node Profile Object: \r\n");
		rs.append(this.profileObj.toString());
		rs.append("\r\n>Data Object: "+dataObjList.size()+" devices\r\n");

		for (eDataObject dataObj : dataObjList) {
			rs.append("\r\n\t####################\r\n");
			rs.append("\t"+ dataObj.ToString()+"\r\n");
			rs.append("\t####################\r\n");
		}
		rs.append("*********************************************\r\n");
		return rs.toString();
	}
	// getter setter
	public NodeProfileObject getProfileObj() {
		return profileObj;
	}
	public void setProfileObj(NodeProfileObject profileObj) {
		this.profileObj = profileObj;
	}
	public ArrayList<eDataObject> getDataObjList() {
		return dataObjList;
	}
	public void setDataObjList(ArrayList<eDataObject> dataObjList) {
		this.dataObjList = dataObjList;
	}
	public void setDataObjList(eDataObject dataObj) {
		ArrayList<eDataObject> objList = new ArrayList<eDataObject>();
		objList.add(dataObj);
		this.dataObjList = objList;
	}
}
