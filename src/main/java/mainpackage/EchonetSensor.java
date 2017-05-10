package mainpackage;

import echonet.objects.eProfileObject;

public class EchonetSensor extends DataMessage{
	public static final String MY_URI = CaressesOntology.NAMESPACE + "EchonetSensor";
	public static final String PROPERTY_IS_AALENVIRONMENT_INPUT = CaressesOntology.NAMESPACE + "is_AALENVIRONMENT_input";
	public static final String PROPERTY_OPERATION_STATUS = CaressesOntology.NAMESPACE + "EchonetSensor_has_Operation_Status";
	public static final String PROPERTY_HAS_IP = CaressesOntology.NAMESPACE + "EchonetSensor_has_IP_Address";
	public static final String PROPERTY_PROFILE = CaressesOntology.NAMESPACE + "EchonetSensor_has_Profile";
	public EchonetSensor(String uri) {
		super(uri);
		
	}
	public String getClassURI(){
		return MY_URI;
	}
	
	public eProfileObject getProfile() {
		return (eProfileObject) getProperty(PROPERTY_PROFILE);
	}
	public void setProfile(eProfileObject obj) {
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
