package ontologies;

import java.util.StringTokenizer;

import mainpackage.CaressesOntology;
import mainpackage.SmartFacility;

public class TemperatureSensor extends SensorRelatedOntology{
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "echonet_temperature_sensor";
	public static final String PROPERTY_HAS_OPERATION_STATUS = CaressesOntology.NAMESPACE + "echonet_temperature_sensor_has_operation_status";
	public static final String PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE = CaressesOntology.NAMESPACE + "echonet_temperature_sensor_has_mesured_temperature_value";
	public static final String PROPERTY_HAS_TEMPERATURE_SENSOR_SUPER_CLASS_INSTALLATION_LOCATION = 
			CaressesOntology.NAMESPACE + "echonet_temperature_sensor_has_super_class_installation_location";
	
	public TemperatureSensor(String URI) {
		super(URI);
		changeProperty(MY_URI, URI);
	}
	
	public String getOperationStatus(){
		String msg = (String) getProperty(PROPERTY_HAS_OPERATION_STATUS);
		return (msg == null) ? "" : msg;
		
	}
	
	public String getMesuredTemperatureValue(){
		String msg = (String) getProperty(PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE);
		return (msg == null) ? "" : msg;		
	}
	public void setInput(SmartFacility AALEnvironment_individual){
		setProperty(PROPERTY_IS_SMART_FACILITY_COMPONENT, AALEnvironment_individual);
	}
	
	public void initOntology(boolean operationStt, float temperature, String location){
			changeProperty(PROPERTY_HAS_OPERATION_STATUS, operationStt);
			changeProperty(PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE, temperature);
			changeProperty(PROPERTY_HAS_TEMPERATURE_SENSOR_SUPER_CLASS_INSTALLATION_LOCATION, location);	
	}
	@Override
	public int getPropSerializationType(String propURI) {
		return super.getPropSerializationType(propURI);
	}
}
