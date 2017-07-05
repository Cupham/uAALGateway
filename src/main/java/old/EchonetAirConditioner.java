package old;

import echonet.objects.NodeProfileObject;
import mainpackage.CaressesOntology;
import mainpackage.DataMessage;

public class EchonetAirConditioner extends DataMessage{
	public static final String MY_URI = CaressesOntology.NAMESPACE + "EchonetAirConditioner";
	public static final String PROPERTY_IS_AALENVIRONMENT_INPUT = CaressesOntology.NAMESPACE + "is_AALENVIRONMENT_input";
	public static final String PROPERTY_OPERATION_STATUS = CaressesOntology.NAMESPACE + "EchonetAirConditioner_has_Operation_Status";
	public static final String PROPERTY_HAS_IP = CaressesOntology.NAMESPACE + "EchonetAirConditioner_has_IP_Address";
	public static final String PROPERTY_PROFILE = CaressesOntology.NAMESPACE + "EchonetAirConditioner_has_Profile";
	public EchonetAirConditioner(String uri) {
		super(uri);
		changeProperty(MY_URI, uri);
		
	}
	public String getClassURI(){
		return MY_URI;
	}
	
	public NodeProfileObject getProfile() {
		return (NodeProfileObject) getProperty(PROPERTY_PROFILE);
	}
	public void setProfile(NodeProfileObject obj) {
		changeProperty(PROPERTY_PROFILE, obj);
		changeProperty(PROPERTY_HAS_IP, obj.getDeviceIP());
		changeProperty(PROPERTY_OPERATION_STATUS, obj.isOperationStatus());
	}
	public void setOperationStatus(boolean status) {
		changeProperty(PROPERTY_OPERATION_STATUS, status);
	}
	public boolean getOperationStatus() {
		return (boolean) getProperty(PROPERTY_OPERATION_STATUS);
	}
	public void setIP(String ip) {
		changeProperty(PROPERTY_HAS_IP, ip);
	}
	public String getIP() {
		return getProperty(PROPERTY_HAS_IP).toString();
	}

}
