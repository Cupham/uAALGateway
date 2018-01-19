package mainpackage;

import org.universAAL.middleware.service.owl.Service;
import org.universAAL.ontology.phThing.DeviceService;

public class EchonetService extends Service{
	public static final String MY_URI= CaressesOntology.NAMESPACE + "Echonet_Service";
	
	public static final String PROP_PROVIDES_TEMPERATURE_SENSOR_SERVICE = CaressesOntology.NAMESPACE + "provides_temperature_sensor_service";
	public static final String PROP_PROVIDES_AIRCONDTIONER_SERVICE = CaressesOntology.NAMESPACE + "provides_airconditioner_service";
	public static final String PROP_PROVIDES_LIGHTING_SERVICE = CaressesOntology.NAMESPACE + "provides_lighting_service";
	public static final String PROP_PROVIDES_CURTAIN_SERVICE = CaressesOntology.NAMESPACE + "provides_curtain_service";
	public static final String PROP_PROVIDES_CONSENT_SERVICE = CaressesOntology.NAMESPACE + "provides_consent_service";
	public EchonetService() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EchonetService(String uri) {
		super(uri);
		// TODO Auto-generated constructor stub
	}
	public String getClassURI(){
		return MY_URI;
	}
	
	public int getPropSerializationType(String propURI){

		if ((PROP_PROVIDES_TEMPERATURE_SENSOR_SERVICE.equals(propURI)) || 
			(PROP_PROVIDES_AIRCONDTIONER_SERVICE.equals(propURI))){
				return PROP_SERIALIZATION_FULL;
		} else {
				return super.getPropSerializationType(propURI);
		}
	}
	
	public boolean isWellFormed(){
		// TODO Auto-generated constructor stub
		return true;
	}
}
