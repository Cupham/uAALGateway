package old;

import echonet.objects.NodeProfileObject;
import mainpackage.CaressesOntology;
import mainpackage.DataMessage;

public class EchonetSensor extends DataMessage{
	public static final String MY_URI = CaressesOntology.NAMESPACE + "echonetSensor";
	public static final String PROPERTY_IS_AALENVIRONMENT_INPUT = CaressesOntology.NAMESPACE + "is_AALENVIRONMENT_input";
	public static final String PROPERTY_OPERATION_STATUS = CaressesOntology.NAMESPACE + "echonetSensor_has_Operation_Status";
	public static final String PROPERTY_HAS_IP = CaressesOntology.NAMESPACE + "echonetSensor_has_IP_address";
	public EchonetSensor(String uri) {
		super(uri);
		
	}
	public String getClassURI(){
		return MY_URI;
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
