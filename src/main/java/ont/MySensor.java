package ont;


import org.universAAL.ontology.device.Sensor;

import echonet.objects.EchonetLiteDevice;
import echonet.objects.eDataObject;
import echonet.objects.eSuperClass;
import echonet.objects.eTemperatureSensor;

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
		changeProperty(MY_URI, uri);
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
	public boolean setData (EchonetLiteDevice obj) {
		boolean changed = false;
		eSuperClass profile = obj.getProfileObj();
		for(eDataObject eData : obj.getDataObjList()) {
			if(eData.getClass().equals(eTemperatureSensor.class)) {
				eTemperatureSensor sensor = (eTemperatureSensor) eData;	
				if(this.getMySensorOperationStatus() != sensor.isOperationStatus()) {
					changeProperty(PROP_OPERATION_STATUS, sensor.isOperationStatus());
					changed= true;
				} else if (this.getMySensorValue() != sensor.getTemperature()) {
					changeProperty(PROP_VALUE, sensor.getTemperature());
					changed= true;
				}else if (this.getMySensorLocation() != profile.getInstallLocation()) {
					changeProperty(PROP_LOCALTION, profile.getInstallLocation());
					changed= true;
				} else {
					changed = false;
				}
			}
		}
		return changed;
	}
	

}
