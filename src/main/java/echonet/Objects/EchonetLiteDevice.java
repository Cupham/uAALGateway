package echonet.Objects;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import echowand.common.EOJ;
import echowand.net.Node;
import echowand.object.EchonetObjectException;
import echowand.service.Service;
import main.Activator;

public class EchonetLiteDevice {
	private static final Logger LOGGER = Logger.getLogger(EchonetLiteDevice.class.getName());
	
	private NodeProfileObject profileObj;
	private Node node;
	private ArrayList<eDataObject> dataObjList;
	public EchonetLiteDevice() {
		this.dataObjList = new ArrayList<eDataObject>();
		this.profileObj = null;
		this.node = null;
	}
	public EchonetLiteDevice(Node node) {
		this.dataObjList = new ArrayList<eDataObject>();
		this.profileObj = null;
		this.node = node;
	}
	
	public boolean addDataObject(eDataObject dataObj) throws EchonetObjectException {
		if(this.dataObjList == null) {
			this.dataObjList = new ArrayList<eDataObject>();
		}
		this.dataObjList.add(dataObj);
		return true;
	}
	public boolean parseDataObject(EOJ eoj, Node node, Service service) throws EchonetObjectException{
		byte classGroupCode = eoj.getClassGroupCode();
		byte classCode = eoj.getClassCode();
		eDataObject dataObj = null;
		//init object class 
		
		switch (classGroupCode) {		
		case (byte) (0x00): // Sensor-related Device Class Group
			switch(classCode) {
			case (byte) (0x11): //temperature sensor
				System.out.println("   			Creating TemperatureSensor object from ECHONET frame...");
				
				dataObj = new eTemperatureSensor(eoj, node);
				
				break;
			case (byte) (0x12): //humidity sensor
				//TODO: implement humidity sensor class
				break;
			case (byte) (0x22):
				System.out.println("   			Creating Electric Consent object from ECHONET frame...");
				dataObj = new eElectricConsent(eoj,node);
				break;
			default:
				return false;
			}
		break;
		
		case (byte)(0x01): //air conditioner related class
			switch (classCode) {
			case (byte)(0x30):
				System.out.println("   			Creating Air-Conditioner object from ECHONET frame...");
				dataObj = new eAirConditioner(eoj, node);

				break;
			default:
				return false;
			}	
		break;	
		
		case (byte)(0x02): //lighting related class
			switch (classCode) {
			case (byte)(0x90):
				//System.out.println("   			Creating Lighting object from ECHONET frame...");
				dataObj = new eLighting(eoj, node);
				break;
			default:
				return false;
			}	
		break;
		
		case (byte)(0x05): //Management
			switch (classCode) {
			case (byte)(0xfd):
				if(eoj.getInstanceCode() == (byte) 0x01) {
					//System.out.println("   			Creating Curtain object from ECHONET frame...");
					//dataObj = new eCurtain(eoj, node);
				}
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
			dataObj.ParseDataFromEOJ(service);
			dataObj.ParseProfileObjectFromEPC(service);
			this.addDataObject(dataObj);
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
        if(this.profileObj.equals(checkDevice.profileObj)) {
        	if(this.getDataObjList().equals(checkDevice.getDataObjList())) {
        		return true;
        	}
        }
        return false;
	}
	@Override
	public String toString() {
		StringBuilder rs = new StringBuilder();
		rs.append("\r\n*********************************************");
		rs.append("\r\n>Node IP: " + this.node.getNodeInfo().toString());
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
	public Node getNode() {
		return node;
	}
	public void setNode(Node node) {
		this.node = node;
	}
	public void setProfileObj(NodeProfileObject profileObj) {
		if(!profileObj.equals(this.profileObj)) {
			this.profileObj = profileObj;
		}
		
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
