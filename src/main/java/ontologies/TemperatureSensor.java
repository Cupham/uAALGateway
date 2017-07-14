package ontologies;


import mainpackage.CaressesOntology;
import mainpackage.SmartFacility;

public class TemperatureSensor extends SensorRelatedOntology{
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "echonet_temperature_sensor";
	public static final String PROPERTY_HAS_OPERATION_STATUS = CaressesOntology.NAMESPACE + "echonet_temperature_sensor_has_operation_status";
	public static final String PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE = CaressesOntology.NAMESPACE + "echonet_temperature_sensor_has_mesured_temperature_value";
	
	public TemperatureSensor() {
		super();
	}
	public TemperatureSensor(String URI) {
		super(URI);
	}
	public String getClassURI(){
		return MY_URI;
	}
	public void setInput(SmartFacility AALEnvironment_individual){
		setProperty(PROPERTY_IS_SMART_FACILITY_COMPONENT, AALEnvironment_individual);
	}
	

	public boolean getOperationStatus(){
		Boolean data = (Boolean) getProperty(PROPERTY_HAS_OPERATION_STATUS);
		return (data == null) ? null : data.booleanValue(); 
	}
	public void setOperationStatus(boolean stt) {
		changeProperty(PROPERTY_HAS_OPERATION_STATUS, new Boolean(stt));	
	}

	public float getMesuredTemperatureValue(){
		Float data =  (Float) getProperty(PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE);	
		return (data ==null) ? null : data.floatValue();
	}
	public void setMesuredTemperatureValue(float temperature) {
		changeProperty(PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE, new Float(temperature));
	}

	@Override
	public int getPropSerializationType(String propURI) {
		return super.getPropSerializationType(propURI);
	}
}
