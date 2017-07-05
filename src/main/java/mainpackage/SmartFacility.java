package mainpackage;

import ontologies.EchonetDevice;
import ontologies.HomeAirConditioner;
import ontologies.TemperatureSensor;

public class SmartFacility extends CaressesComponent{
	public static final String MY_URI = CaressesOntology.NAMESPACE + "SmartFacility";
	public static final String PROPERTY_HAS_SMART_FACILITY_OUTPUT = CaressesOntology.NAMESPACE + "has_Smart Facility_output"; 
	
	public SmartFacility(String uri){	
		super(uri);
	}
	public String setOutput(EchonetDevice message_individual){
		// ! message_individual can only be echonet sensor
		if (message_individual instanceof TemperatureSensor || message_individual instanceof HomeAirConditioner){
			setProperty(PROPERTY_HAS_SMART_FACILITY_OUTPUT, message_individual);	
			return "Right output assignment!";
		} else {
			return "ERROR: Smart Facility has only outputs of type EchonetSensor!";
		}
	}
	
}
