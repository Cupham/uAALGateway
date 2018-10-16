package main;

import org.universAAL.ontology.echonetontology.EchonetOntology;
import org.universAAL.ontology.echonetontology.EchonetSuperDevice;
import org.universAAL.ontology.echonetontology.airconditionerRelatedDevices.HomeAirConditioner;
import org.universAAL.ontology.echonetontology.sensorRelatedDevices.TemperatureSensor;
import org.universAAL.ontology.phThing.Device;


public class SmartFacility extends CaressesComponent{
	public static final String MY_URI = CaressesOntology.NAMESPACE + "SmartFacility";
	public static final String PROPERTY_HAS_SMART_FACILITY_OUTPUT = CaressesOntology.NAMESPACE + "has_Smart Facility_output"; 
	
	public SmartFacility(String uri){	
		super(uri);
	}
	public String setOutput(Device message_individual){
		// ! message_individual can only be echonet sensor
		if (message_individual instanceof TemperatureSensor || message_individual instanceof HomeAirConditioner){
			setProperty(PROPERTY_HAS_SMART_FACILITY_OUTPUT, message_individual);	
			return "Right output assignment!";
		} else {
			return "ERROR: Smart Facility has only outputs of type EchonetSensor!";
		}
	}
	
}
