package mainpackage;

import utils.SerializeUtils;

import com.google.gson.Gson;

import echonet.objects.EchonetLiteDevice;
import echonet.objects.eDataObject;
import echonet.objects.eTemperatureSensor;

public class TemperatureSensor extends EchonetSensor{
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "TemperatureSensor";	
	// : Data Properties
	public static final String PROPERTY_HAS_TEMPERATURE_SENSOR_DESCRIPTION = CaressesOntology.NAMESPACE + "has_Temperature_Sensor_description";
	
	public static final String PROPERTY_HAS_TEMPERATURE = CaressesOntology.NAMESPACE + "has_Temperature";	
	public static final String PROPERTY_HAS_LOCATION = CaressesOntology.NAMESPACE + "has_Location";
	
	public TemperatureSensor(String uri) {
		super(uri);
		changeProperty(MY_URI, uri);
	}
	public TemperatureSensor() {
		super(MY_URI);
	}
	public String getClassURI(){
		return MY_URI;
	}
	
	public boolean setMessage(String message){
		boolean rs = false;
		changeProperty(PROPERTY_HAS_TEMPERATURE_SENSOR_DESCRIPTION, message);
		eTemperatureSensor dev = SerializeUtils.temperatureSensorFromMessage(message);
		if(null != dev)
		{
			changeProperty(PROPERTY_HAS_TEMPERATURE, dev.getTemperature());
			changeProperty(EchonetSensor.PROPERTY_OPERATION_STATUS, dev.isOperationStatus());
			changeProperty(PROPERTY_HAS_LOCATION, dev.getInstallLocation());
			changeProperty(EchonetSensor.PROPERTY_HAS_IP, dev.getDeviceIP());		
			rs = true;
		}		
		return rs;	
	}
	
	public String getMessage(){
		String msg = (String) getProperty(PROPERTY_HAS_TEMPERATURE_SENSOR_DESCRIPTION);
		return (msg == null) ? "" : msg;
		
	}
	
	public void setInput(AALEnvironment AALEnvironment_individual){
		setProperty(PROPERTY_IS_AALENVIRONMENT_INPUT, AALEnvironment_individual);
	}
	@Override
	public int getPropSerializationType(String propURI) {
		return super.getPropSerializationType(propURI);
	}
	
}
