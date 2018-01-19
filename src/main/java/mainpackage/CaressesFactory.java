package mainpackage;

import org.universAAL.middleware.rdf.Resource;
import org.universAAL.middleware.rdf.ResourceFactory;

import ontologies.AirConditionerRelatedOntology;
import ontologies.AudioVisualRelatedOntology;
import ontologies.CookingHouseholdRelatedOntology;
import ontologies.EchonetDevice;
import ontologies.HealthRelatedOntology;
import ontologies.SensorRelatedOntology;

public class CaressesFactory implements ResourceFactory {
	
	public static final int CARESSES_COMPONENT = 0;
	
	public static final int SMART_FACILITY     = 100;
	
	public static final int ECHONET_SERVICE 	 = 101;
	
	public static final int ECHONET_DEVICE       = 102;
	public static final int SENSOR_RELATED_ONT   = 103;
	public static final int AIRCONDITIONER_RELATED_ONT   = 104;
	public static final int AUDIO_VISUAL_RELARED_ONT   = 105;
	public static final int COOKING_HOUSEHOLD_RELATED_ONT   = 106;
	public static final int HEALTH_RELATED_ONT   = 107;
	public static final int HOUSING_FACILITY_RELARED_ONT   = 108;
	public static final int MANAGEMENT_OPERATION_RELATED_ONT   = 109;
	
	public static final int TEMPERATURE_SENSOR   = 110;
	public static final int HOME_AIRCONDITIONER   = 111;
	public static final int LIGHTING   = 112;
	public static final int CURTAIN   = 113;
	public static final int CONSENT   = 114;
	
	
	
	public Resource createInstance(String classURI, String instanceURI, int factoryIndex){
		switch(factoryIndex){
			case CARESSES_COMPONENT:
				return new CaressesComponent(instanceURI);
			case SMART_FACILITY:
				return new SmartFacility(instanceURI);
			case ECHONET_DEVICE:
				return new EchonetDevice(instanceURI);				
			case SENSOR_RELATED_ONT:
				return new SensorRelatedOntology(instanceURI);	
			case AIRCONDITIONER_RELATED_ONT:
				return new AirConditionerRelatedOntology(instanceURI);
			case AUDIO_VISUAL_RELARED_ONT:
				return new AudioVisualRelatedOntology(instanceURI);	
			case COOKING_HOUSEHOLD_RELATED_ONT:
				return new CookingHouseholdRelatedOntology(instanceURI);			
			case HEALTH_RELATED_ONT:
				return new HealthRelatedOntology(instanceURI);
			case HOUSING_FACILITY_RELARED_ONT:
				return new CookingHouseholdRelatedOntology(instanceURI);			
			case MANAGEMENT_OPERATION_RELATED_ONT:
				return new HealthRelatedOntology(instanceURI);		
			case TEMPERATURE_SENSOR:
				return new ontologies.TemperatureSensor(instanceURI);
			case HOME_AIRCONDITIONER:
				return new ontologies.HomeAirConditioner(instanceURI);
			case LIGHTING:
				return new ontologies.Lighting(instanceURI);
			case CURTAIN:
				return new ontologies.Curtain(instanceURI);
			case CONSENT:
				return new ontologies.ElectricConsent(instanceURI);
			case ECHONET_SERVICE:
				return new EchonetService(instanceURI);

		}
		
		return null;
	}

}
