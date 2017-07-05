package ServiceBus;


import org.universAAL.middleware.owl.MergedRestriction;
import org.universAAL.middleware.owl.OntologyManagement;
import org.universAAL.middleware.owl.SimpleOntology;
import org.universAAL.middleware.rdf.Resource;
import org.universAAL.middleware.rdf.ResourceFactory;
/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_5#Ontologies_in_universAAL */
import org.universAAL.middleware.service.owls.profile.ServiceProfile;

import mainpackage.Activator;
import mainpackage.EchonetService;
import old.TemperatureSensor_odd;

import java.util.Hashtable;

public class SCallee_SmartFacilityProvidedService extends EchonetService{

	public static ServiceProfile[] profiles = new ServiceProfile[2];
	private static Hashtable serverLevelRestrictions = new Hashtable();
	
	public static final String NAMESPACE = "http://CARESSESuniversAALskeleton.org/Callee_SmartFacility.owl#";
	public static final String MY_URI    = NAMESPACE + "Smart_Facility_Service";
	
	// : Services
		//Temperature Sensor
	static final String SERVICE_GET_TEMPERATURE_SENSORS= NAMESPACE + "get_temperature_sensors";
	static final String SERVICE_GET_TEMPERATURE_SENSOR_TEMPERATURE= NAMESPACE + "get_temperature_sensor_temperature";
	static final String SERVICE_GET_TEMPERATURE_SENSOR_LOCATION= NAMESPACE + "get_temperature_sensor_location";
		// Airconditioner
	static final String SERVICE_GET_AIRCONDITIONER_TEMPERATURE= NAMESPACE + "get_airconditioner_temperature";
	
	// : Outputs
		//Temperature Sensor
	static final String OUTPUT_TEMPERATURE_SENSORS= NAMESPACE + "output_temperature_sensors";
	static final String OUTPUT_TEMPERATURE_SENSOR_TEMPERATURE = NAMESPACE + "output_temperature_sensor_temperature";
	static final String OUTPUT_TEMPERATURE_SENSOR_LOCATION = NAMESPACE + "output_temperature_sensor_location";
		// Airconditioner
	static final String OUTPUT_AIRCONDTIONER_TEMPERATURE = NAMESPACE + "output_airconditioner_temperature";
	
	
	public SCallee_SmartFacilityProvidedService (String uri) {
		super(uri);
	}
	public String getClassURI(){
		return MY_URI;
	}
	
	static{
		OntologyManagement.getInstance().register(Activator.context, new SimpleOntology(MY_URI, EchonetService.MY_URI, new ResourceFactory(){
			public Resource createInstance(String classURI, String instanceURI, int factoryIndex){
				return new SCallee_SmartFacilityProvidedService(instanceURI);
			}
		}));

		String[] ppTemperature_Sensor = new String[]{EchonetService.PROP_PROVIDES_TEMPERATURE_SENSOR_SERVICE};
		String[] ppAircondtioner = new String[]{EchonetService.PROP_PROVIDES_TEMPERATURE_SENSOR_SERVICE};
		
		addRestriction((MergedRestriction)EchonetService.getClassRestrictionsOnProperty(
				EchonetService.MY_URI, EchonetService.PROP_PROVIDES_TEMPERATURE_SENSOR_SERVICE).copy(), ppTemperature_Sensor, serverLevelRestrictions);
		addRestriction((MergedRestriction)EchonetService.getClassRestrictionsOnProperty(
				EchonetService.MY_URI, EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE).copy(), ppAircondtioner, serverLevelRestrictions);
		
		SCallee_SmartFacilityProvidedService getTemperatureSensors = 
				new SCallee_SmartFacilityProvidedService(SERVICE_GET_TEMPERATURE_SENSORS);
		getTemperatureSensors.addOutput(OUTPUT_TEMPERATURE_SENSORS, TemperatureSensor_odd.MY_URI, 1, 1, ppTemperature_Sensor);
		profiles[0] = getTemperatureSensors.myProfile;
		
		SCallee_SmartFacilityProvidedService getTemperatureSensor_Temperature = 
				new SCallee_SmartFacilityProvidedService(SERVICE_GET_AIRCONDITIONER_TEMPERATURE);
		getTemperatureSensor_Temperature.addOutput(OUTPUT_AIRCONDTIONER_TEMPERATURE, TemperatureSensor_odd.MY_URI, 1, 1, ppTemperature_Sensor);
		profiles[1] = getTemperatureSensor_Temperature.myProfile;
		
	}
	
}
