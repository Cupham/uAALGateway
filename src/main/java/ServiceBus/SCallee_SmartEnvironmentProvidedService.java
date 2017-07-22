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
import ontologies.HomeAirConditioner;
import ontologies.TemperatureSensor;

import java.util.Hashtable;

public class SCallee_SmartEnvironmentProvidedService extends EchonetService{

	public static ServiceProfile[] profiles = new ServiceProfile[15];
	private static Hashtable serverLevelRestrictions = new Hashtable();
	
	public static final String NAMESPACE = "http://CARESSESuniversAALskeleton.org/Callee_SmartFacility.owl#";
	public static final String MY_URI    = NAMESPACE + "Smart_Facility_Service";
	
	// : Services
		//Temperature Sensor
	static final String SERVICE_GET_TEMPERATURE_SENSORS= NAMESPACE + "get_temperature_sensors";
	static final String SERVICE_TURN_ON_TEMPERATURE_SENSOR= NAMESPACE + "turnon_temperature_sensor_temperature";
	static final String SERVICE_TURN_OFF_TEMPERATURE_SENSOR= NAMESPACE + "turnoff_temperature_sensor_temperature";
	static final String SERVICE_SET_TEMPERATURE_SENSOR_LOCATION= NAMESPACE + "set_temperature_sensor_location";
		// Airconditioner
	static final String SERVICE_GET_AIRCONDITIONERS= NAMESPACE + "get_airconditioners";
	static final String SERVICE_TURN_ON_AIRCONDITIONER= NAMESPACE + "turnon_airconditioner";
	static final String SERVICE_TURN_OFF_AIRCONDITIONER= NAMESPACE + "turnoff_airconditioner";
	static final String SERVICE_SET_AIRCONDITIONER_LOCATION= NAMESPACE + "set_airconditioner_location";
	static final String SERVICE_SET_AIRCONDITIONER_OPERATION_POWER_SAVING= NAMESPACE + "set_airconditioner_power_saving_option";
	static final String SERVICE_SET_AIRCONDITIONER_OPERATION_MODE= NAMESPACE + "set_airconditioner_operation_mode";
	static final String SERVICE_SET_AIRCONDITIONER_TEMPERATURE= NAMESPACE + "set_airconditioner_temperature";
	static final String SERVICE_SET_AIRCONDITIONER_AIR_FLOW_RATE= NAMESPACE + "set_airconditioner_air_low_rate";
	// : Inputs
	 	//Temperature Sensor Properties
	static final String INPUT_TEMPERATURE_SENSOR_URI= NAMESPACE + "input_temperature_sensor_uri";
	static final String INPUT_TEMPERATURE_SENSOR_LOCATION= NAMESPACE + "input_temperature_sensor_location";
		 //Airconditioner Properties	
	static final String INPUT_AIRCONDITIONER_URI= NAMESPACE + "input_airconditioner_uri";
	static final String INPUT_AIRCONDITIONER_LOCATION= NAMESPACE + "input_airconditioner_location";
	static final String INPUT_AIRCONDITIONER_POWER_SAVING_OPTION= NAMESPACE + "input_airconditioner_power_saving_option";
	static final String INPUT_AIRCONDITIONER_OPERATION_MODE= NAMESPACE + "input_airconditioner_operation_mode";
	static final String INPUT_AIRCONDITIONER_TEMPERATURE= NAMESPACE + "input_airconditioner_operation_temperature";
	static final String INPUT_AIRCONDITIONER_AIR_FLOW_RATE= NAMESPACE + "input_airconditioner_operation_air_flow_rate";
	// : Outputs
		//Temperature Sensor
	static final String OUTPUT_TEMPERATURE_SENSORS= NAMESPACE + "output_temperature_sensors";
		// Airconditioner
	static final String OUTPUT_AIRCONDTIONERS= NAMESPACE + "output_airconditioners";
	
	
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
		String[] ppTemperature_Sensor_InstallationLocation = new String[]{EchonetService.PROP_PROVIDES_TEMPERATURE_SENSOR_SERVICE,TemperatureSensor.PROPERTY_HAS_INSTALLATION_LOCATION};
		
		String[] ppAirconditioners = new String[]{EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE};
		String[] ppAirconditioner_OperationStatus = new String[]{EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE,HomeAirConditioner.PROPERTY_HAS_OPERATION_STATUS};
		String[] ppAirconditioner_InstallationLocation = new String[]{EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE,HomeAirConditioner.PROPERTY_HAS_INSTALLATION_LOCATION};
		String[] ppAirconditioner_OperationPowerSaving = new String[]{EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE,HomeAirConditioner.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING};
		String[] ppAirconditioner_OperationMode = new String[]{EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE,HomeAirConditioner.PROPERTY_HAS_OPERATION_MODE_SETTING};
		String[] ppAirconditioner_TemperatureValue = new String[]{EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE,HomeAirConditioner.PROPERTY_HAS_SETTING_TEMPERATURE_VALUE};
		String[] ppAirconditioner_AirFlowRate = new String[]{EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE,HomeAirConditioner.PROPERTY_HAS_AIR_FLOW_RATE_SETTING};
		
		
		addRestriction((MergedRestriction)EchonetService.getClassRestrictionsOnProperty(
				EchonetService.MY_URI, EchonetService.PROP_PROVIDES_TEMPERATURE_SENSOR_SERVICE).copy(), ppTemperature_Sensors, serverLevelRestrictions);
		addRestriction((MergedRestriction)EchonetService.getClassRestrictionsOnProperty(
				EchonetService.MY_URI, EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE).copy(), ppAirconditioners, serverLevelRestrictions);
		
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
		
		SCallee_SmartEnvironmentProvidedService getAirconditioners = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_GET_AIRCONDITIONERS);
		getAirconditioners.addOutput(OUTPUT_AIRCONDTIONERS, HomeAirConditioner.MY_URI, 0, 0, ppAirconditioners);
		profiles[4] = getAirconditioners.myProfile;
		
		SCallee_SmartEnvironmentProvidedService turnOnAirconditioner = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_TURN_ON_AIRCONDITIONER);
		turnOnAirconditioner.addFilteringInput(INPUT_AIRCONDITIONER_URI, HomeAirConditioner.MY_URI, 1, 1, ppAirconditioners);
		turnOnAirconditioner.myProfile.addChangeEffect(ppAirconditioner_OperationStatus, new Boolean(true));
		profiles[5] = turnOnAirconditioner.myProfile;
		
		SCallee_SmartEnvironmentProvidedService turnOffAirconditioner = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_TURN_OFF_AIRCONDITIONER);
		turnOffAirconditioner.addFilteringInput(INPUT_AIRCONDITIONER_URI, HomeAirConditioner.MY_URI, 1, 1, ppAirconditioners);
		turnOffAirconditioner.myProfile.addChangeEffect(ppAirconditioner_OperationStatus, new Boolean(false));
		profiles[6] = turnOffAirconditioner.myProfile;
		
		SCallee_SmartEnvironmentProvidedService setAirconditionerLocation = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_SET_AIRCONDITIONER_LOCATION);
		setAirconditionerLocation.addFilteringInput(INPUT_AIRCONDITIONER_URI, HomeAirConditioner.MY_URI, 1, 1, ppAirconditioners);
		setAirconditionerLocation.addInputWithChangeEffect(INPUT_AIRCONDITIONER_LOCATION, TypeMapper.getDatatypeURI(String.class), 1, 1, ppAirconditioner_InstallationLocation);
		profiles[7] = setAirconditionerLocation.myProfile;
		
		SCallee_SmartEnvironmentProvidedService setAirconditionerOperationPowerSaving = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_SET_AIRCONDITIONER_OPERATION_POWER_SAVING);
		setAirconditionerOperationPowerSaving.addFilteringInput(INPUT_AIRCONDITIONER_URI, HomeAirConditioner.MY_URI, 1, 1, ppAirconditioners);
		setAirconditionerOperationPowerSaving.addInputWithChangeEffect(INPUT_AIRCONDITIONER_POWER_SAVING_OPTION, TypeMapper.getDatatypeURI(Boolean.class), 1, 1, ppAirconditioner_OperationPowerSaving);
		profiles[8] = setAirconditionerOperationPowerSaving.myProfile;
		
		SCallee_SmartEnvironmentProvidedService setAirconditionerOperationMode = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_SET_AIRCONDITIONER_OPERATION_MODE);
		setAirconditionerOperationMode.addFilteringInput(INPUT_AIRCONDITIONER_URI, HomeAirConditioner.MY_URI, 1, 1, ppAirconditioners);
		setAirconditionerOperationMode.addInputWithChangeEffect(INPUT_AIRCONDITIONER_OPERATION_MODE, TypeMapper.getDatatypeURI(String.class), 1, 1, ppAirconditioner_OperationMode);
		profiles[9] = setAirconditionerOperationMode.myProfile;
		
		SCallee_SmartEnvironmentProvidedService setAirconditionerTemperature = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_SET_AIRCONDITIONER_TEMPERATURE);
		setAirconditionerTemperature.addFilteringInput(INPUT_AIRCONDITIONER_URI, HomeAirConditioner.MY_URI, 1, 1, ppAirconditioners);
		setAirconditionerTemperature.addInputWithChangeEffect(INPUT_AIRCONDITIONER_TEMPERATURE, TypeMapper.getDatatypeURI(Integer.class), 1, 1, ppAirconditioner_TemperatureValue);
		profiles[10] = setAirconditionerTemperature.myProfile;
		
		SCallee_SmartEnvironmentProvidedService setAirconditionerAirFlowRate = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_SET_AIRCONDITIONER_TEMPERATURE);
		setAirconditionerAirFlowRate.addFilteringInput(INPUT_AIRCONDITIONER_URI, HomeAirConditioner.MY_URI, 1, 1, ppAirconditioners);
		setAirconditionerAirFlowRate.addInputWithChangeEffect(INPUT_AIRCONDITIONER_AIR_FLOW_RATE, TypeMapper.getDatatypeURI(String.class), 1, 1, ppAirconditioner_AirFlowRate);
		profiles[11] = setAirconditionerAirFlowRate.myProfile;
	}
	
}
