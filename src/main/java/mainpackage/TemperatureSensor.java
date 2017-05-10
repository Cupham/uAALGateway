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
	
	public TemperatureSensor(String uri) {
		super(uri);
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
			changeProperty(EchonetSensor.PROPERTY_PROFILE, dev.getProfile());
			changeProperty(EchonetSensor.PROPERTY_OPERATION_STATUS, dev.getProfile().isOperationStatus());
			changeProperty(EchonetSensor.PROPERTY_HAS_IP, dev.getProfile().getDeviceIP());
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
}
