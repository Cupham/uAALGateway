package sensors;

import ont.MySensorOntology;

import org.universAAL.ontology.device.Sensor;

public class MySensor extends Sensor{
	
	public static final String MY_URI = MySensorOntology.NAMESPACE + "MySensor";
	public static final String PROP_VALUE = MySensorOntology.NAMESPACE + "sensorValue";
	public static final String PROP_LOCALTION = MySensorOntology.NAMESPACE + "sensorLocaltion";
	public static final String PROP_OPERATION_STATUS = MySensorOntology.NAMESPACE + "sensorOperationStatus";
	
	public MySensor() {
		super();
	}
	public MySensor(String uri) {
		super(uri);
	}
	public String getClassURI() {
		return MY_URI;
	}
	public  int getPropSerializationType(String arg0) {
		return PROP_SERIALIZATION_FULL;
	}
	@Override
	public boolean isWellFormed() {
		return true;
	}
	public float getMySensorValue() {
		Float f = (Float) getProperty(PROP_VALUE);
		return (f == null) ? 0 : f.floatValue();
	}
	
	public void setMySensorValue(Float val) {
		changeProperty(PROP_VALUE, new Float(val));
	}
	
	public String getMySensorLocation() {
		return getProperty(PROP_LOCALTION).toString();
	}
	public void setMySensorLocation(String Location) {
		changeProperty(PROP_LOCALTION, Location);
	}
	
	public boolean getMySensorOperationStatus() {
		return (boolean)getProperty(PROP_OPERATION_STATUS);
	}
	public void setMySensorOperationStatus(boolean status) {
		changeProperty(PROP_OPERATION_STATUS, status);
	}
	

}
