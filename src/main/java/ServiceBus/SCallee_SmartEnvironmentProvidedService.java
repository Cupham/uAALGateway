package ServiceBus;


import org.universAAL.middleware.owl.MergedRestriction;
import org.universAAL.middleware.owl.OntologyManagement;
import org.universAAL.middleware.owl.SimpleOntology;
import org.universAAL.middleware.rdf.Resource;
import org.universAAL.middleware.rdf.ResourceFactory;
import org.universAAL.middleware.rdf.TypeMapper;
/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_5#Ontologies_in_universAAL */
import org.universAAL.middleware.service.owls.profile.ServiceProfile;

import mainpackage.Activator;
import mainpackage.EchonetService;
import ontologies.TemperatureSensor;

import java.util.Hashtable;

public class SCallee_SmartEnvironmentProvidedService extends EchonetService{

	public static ServiceProfile[] profiles = new ServiceProfile[4];
	private static Hashtable serverLevelRestrictions = new Hashtable();
	
	public static final String NAMESPACE = "http://CARESSESuniversAALskeleton.org/Callee_SmartFacility.owl#";
	public static final String MY_URI    = NAMESPACE + "Smart_Facility_Service";
	
	// : Services
		//Temperature Sensor
	static final String SERVICE_GET_TEMPERATURE_SENSORS= NAMESPACE + "get_temperature_sensors";
	static final String SERVICE_TURN_ON_TEMPERATURE_SENSOR= NAMESPACE + "turnon_temperature_sensor_temperature";
	static final String SERVICE_TURN_OFF_TEMPERATURE_SENSOR= NAMESPACE + "turnoff_temperature_sensor_temperature";
	static final String SERVICE_SET_TEMPERATURE_SENSOR_LOCATION= NAMESPACE + "get_temperature_sensor_location";
		// Airconditioner
	static final String SERVICE_GET_AIRCONDITIONER_TEMPERATURE= NAMESPACE + "get_airconditioner_temperature";
	
	// : Inputs
	 	//Temperature Sensor Properties
	static final String INPUT_TEMPERATURE_SENSOR_URI= NAMESPACE + "input_temperature_sensor_uri";
	static final String INPUT_TEMPERATURE_SENSOR_LOCATION= NAMESPACE + "input_temperature_sensor_location";
		
	// : Outputs
		//Temperature Sensor
	static final String OUTPUT_TEMPERATURE_SENSORS= NAMESPACE + "output_temperature_sensors";
	static final String OUTPUT_TEMPERATURE_SENSOR_TEMPERATURE = NAMESPACE + "output_temperature_sensor_temperature";
	static final String OUTPUT_TEMPERATURE_SENSOR_LOCATION = NAMESPACE + "output_temperature_sensor_location";
		// Airconditioner
	static final String OUTPUT_AIRCONDTIONERS= NAMESPACE + "output_airconditioners";
	static final String OUTPUT_AIRCONDTIONER_TEMPERATURE = NAMESPACE + "output_airconditioner_temperature";
	
	
	public SCallee_SmartEnvironmentProvidedService (String uri) {
		super(uri);
	}
	public String getClassURI(){
		return MY_URI;
	}
	
	static{
		OntologyManagement.getInstance().register(Activator.context, new SimpleOntology(MY_URI, EchonetService.MY_URI, new ResourceFactory(){
			public Resource createInstance(String classURI, String instanceURI, int factoryIndex){
				return new SCallee_SmartEnvironmentProvidedService(instanceURI);
			}
		}));

		String[] ppTemperature_Sensors = new String[]{EchonetService.PROP_PROVIDES_TEMPERATURE_SENSOR_SERVICE};
		String[] ppTemperature_Sensor_OperationStatus = new String[]{EchonetService.PROP_PROVIDES_TEMPERATURE_SENSOR_SERVICE,TemperatureSensor.PROPERTY_HAS_OPERATION_STATUS};
		String[] ppTemperature_Sensor_InstallationLocation = new String[]{EchonetService.PROP_PROVIDES_TEMPERATURE_SENSOR_SERVICE,TemperatureSensor.PROPERTY_HAS_OPERATION_STATUS};

		
		String[] ppAirconditioner = new String[]{EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE};
		
		addRestriction((MergedRestriction)EchonetService.getClassRestrictionsOnProperty(
				EchonetService.MY_URI, EchonetService.PROP_PROVIDES_TEMPERATURE_SENSOR_SERVICE).copy(), ppTemperature_Sensors, serverLevelRestrictions);
		addRestriction((MergedRestriction)EchonetService.getClassRestrictionsOnProperty(
				EchonetService.MY_URI, EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE).copy(), ppAirconditioner, serverLevelRestrictions);
		
		SCallee_SmartEnvironmentProvidedService getTemperatureSensors = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_GET_TEMPERATURE_SENSORS);
		getTemperatureSensors.addOutput(OUTPUT_TEMPERATURE_SENSORS, TemperatureSensor.MY_URI, 0, 0, ppTemperature_Sensors);
		profiles[0] = getTemperatureSensors.myProfile;
		
		SCallee_SmartEnvironmentProvidedService turnOnTemperatureSensor = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_TURN_ON_TEMPERATURE_SENSOR);
		turnOnTemperatureSensor.addFilteringInput(INPUT_TEMPERATURE_SENSOR_URI, TemperatureSensor.MY_URI, 1, 1, ppTemperature_Sensors);
		turnOnTemperatureSensor.myProfile.addChangeEffect(ppTemperature_Sensor_OperationStatus, new Boolean(true));
		profiles[1] = turnOnTemperatureSensor.myProfile;
		
		SCallee_SmartEnvironmentProvidedService turnOffTemperatureSensor = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_TURN_OFF_TEMPERATURE_SENSOR);
		turnOffTemperatureSensor.addFilteringInput(INPUT_TEMPERATURE_SENSOR_URI, TemperatureSensor.MY_URI, 1, 1, ppTemperature_Sensors);
		turnOffTemperatureSensor.myProfile.addChangeEffect(ppTemperature_Sensor_OperationStatus, new Boolean(false));
		profiles[2] = turnOffTemperatureSensor.myProfile;
		
		SCallee_SmartEnvironmentProvidedService setTemperatureSensorLocation = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_SET_TEMPERATURE_SENSOR_LOCATION);
		setTemperatureSensorLocation.addFilteringInput(INPUT_TEMPERATURE_SENSOR_URI, TemperatureSensor.MY_URI, 1, 1, ppTemperature_Sensors);
		setTemperatureSensorLocation.addInputWithChangeEffect(INPUT_TEMPERATURE_SENSOR_LOCATION, TypeMapper.getDatatypeURI(String.class), 1, 1, ppTemperature_Sensor_InstallationLocation);
		profiles[3] = setTemperatureSensorLocation.myProfile;
		
		
	}
	
}
