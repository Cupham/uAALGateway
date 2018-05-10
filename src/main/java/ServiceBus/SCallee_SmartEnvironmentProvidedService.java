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
import org.universAAL.ontology.phThing.DeviceService;

import mainpackage.Activator;
import mainpackage.EchonetService;
import ontologies.Curtain;
import ontologies.ElectricConsent;
import ontologies.HomeAirConditioner;
import ontologies.Lighting;
import ontologies.TemperatureSensor;

import java.util.Hashtable;

public class SCallee_SmartEnvironmentProvidedService extends EchonetService{

	public static ServiceProfile[] profiles = new ServiceProfile[22];
	private static Hashtable serverLevelRestrictions = new Hashtable();
	
	public static final String NAMESPACE = "http://CARESSESuniversAALskeleton.org/Callee_SmartEnvironment.owl#";
	public static final String MY_URI    = NAMESPACE + "Smart_Environment_Service";
	
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
		//Lighting
	static final String SERVICE_GET_LIGHTING_DEVICES= NAMESPACE + "get_lighting_devices";
	static final String SERVICE_TURN_ON_LIGHTING_DEVICE= NAMESPACE + "turnon_lighting_devices";
	static final String SERVICE_TURN_OFF_LIGHTING_DEVICE= NAMESPACE + "turnoff_lighting_devices";
	static final String SERVICE_SET_LIGHTING_ILLUMINATION_LEVEL= NAMESPACE + "set_lighting_illumination_level";
	
		//Curtain
	static final String SERVICE_GET_CURTAIN_CONTROLLERS= NAMESPACE + "get_curtains";
	static final String SERVICE_OPEN_CURTAIN= NAMESPACE + "open_curtain";
	static final String SERVICE_CLOSE_CURTAIN= NAMESPACE + "close_curtain";
		//Consent
	static final String SERVICE_GET_CONSENTS= NAMESPACE + "get_consents";
	static final String SERVICE_TURN_ON_CONSENTS= NAMESPACE + "turnon_consent";
	static final String SERVICE_TURN_OFF_CONSENTS= NAMESPACE + "turnoff_consent";

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
		// LIGHTING
	static final String INPUT_LIGHTING_URI= NAMESPACE + "input_lighting_uri";
	static final String INPUT_LIGHTING_STATUS= NAMESPACE + "input_lighting_status";
	static final String INPUT_LIGHTING_ILLUMINATION_LEVEL = NAMESPACE + "input_lighting_illumination_level";
		// CURTAIN
	static final String INPUT_CURTAIN_URI= NAMESPACE + "input_curtain_uri";
	static final String INPUT_CURTAIN_STATUS= NAMESPACE + "input_curtain_status";
		// CONSENT
	static final String INPUT_CONSENT_URI= NAMESPACE + "input_consent_uri";
	static final String INPUT_CONSENT_STATUS= NAMESPACE + "input_consent_status";
	// : Outputs
		//Temperature Sensor
	static final String OUTPUT_TEMPERATURE_SENSORS= NAMESPACE + "output_temperature_sensors";
		// Airconditioner
	static final String OUTPUT_AIRCONDTIONERS= NAMESPACE + "output_airconditioners";
		// lightings
	static final String OUTPUT_LIGHTINGS= NAMESPACE + "output_lightings";
		// curtains
	static final String OUTPUT_CURTAINS= NAMESPACE + "output_curtains";
		// consent
	static final String OUTPUT_CONSENTS= NAMESPACE + "output_consents";
	
	
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

		String[] ppTemperature_Sensors = new String[]{EchonetService.PROP_PROVIDES_TEMPERATURE_SENSOR_SERVICE,TemperatureSensor.MY_URI};
		String[] ppTemperature_Sensor_OperationStatus = new String[]{DeviceService.PROP_CONTROLS,EchonetService.PROP_PROVIDES_TEMPERATURE_SENSOR_SERVICE,TemperatureSensor.PROPERTY_HAS_OPERATION_STATUS};
		String[] ppTemperature_Sensor_InstallationLocation = new String[]{DeviceService.PROP_CONTROLS,EchonetService.PROP_PROVIDES_TEMPERATURE_SENSOR_SERVICE,TemperatureSensor.PROPERTY_HAS_INSTALLATION_LOCATION};
		
		String[] ppAirconditioners = new String[]{EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE,HomeAirConditioner.MY_URI};
		String[] ppAirconditioner_OperationStatus = new String[]{DeviceService.PROP_CONTROLS,EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE,HomeAirConditioner.PROPERTY_HAS_OPERATION_STATUS};
		String[] ppAirconditioner_InstallationLocation = new String[]{DeviceService.PROP_CONTROLS,EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE,HomeAirConditioner.PROPERTY_HAS_INSTALLATION_LOCATION};
		String[] ppAirconditioner_OperationPowerSaving = new String[]{DeviceService.PROP_CONTROLS,EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE,HomeAirConditioner.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING};
		String[] ppAirconditioner_OperationMode = new String[]{DeviceService.PROP_CONTROLS,EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE,HomeAirConditioner.PROPERTY_HAS_OPERATION_MODE_SETTING};
		String[] ppAirconditioner_TemperatureValue = new String[]{DeviceService.PROP_CONTROLS,EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE,HomeAirConditioner.PROPERTY_HAS_SETTING_TEMPERATURE_VALUE};
		String[] ppAirconditioner_AirFlowRate = new String[]{DeviceService.PROP_CONTROLS,EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE,HomeAirConditioner.PROPERTY_HAS_AIR_FLOW_RATE_SETTING};
		
		String[] ppLightings =  new String[]{EchonetService.PROP_PROVIDES_LIGHTING_SERVICE,Lighting.MY_URI};
		String[] ppLighting_Operation_Status =  new String[]{DeviceService.PROP_CONTROLS,EchonetService.PROP_PROVIDES_LIGHTING_SERVICE,Lighting.PROPERTY_HAS_OPERATION_STATUS};
		String[] ppLighting_Illumination_Level =  new String[]{DeviceService.PROP_CONTROLS,EchonetService.PROP_PROVIDES_LIGHTING_SERVICE,Lighting.PROPERTY_HAS_ILLUMINATION_LEVEL};
		
		String[] ppCurtains =  new String[]{EchonetService.PROP_PROVIDES_CURTAIN_SERVICE,Curtain.MY_URI};
		String[] ppCurtains_Status =  new String[]{DeviceService.PROP_CONTROLS,EchonetService.PROP_PROVIDES_CURTAIN_SERVICE,Curtain.PROPERTY_HAS_STATUS};
		
		String[] ppConsents =  new String[]{EchonetService.PROP_PROVIDES_CONSENT_SERVICE,ElectricConsent.MY_URI};
		String[] ppConsents_Status =  new String[]{DeviceService.PROP_CONTROLS,EchonetService.PROP_PROVIDES_CONSENT_SERVICE,ElectricConsent.PROPERTY_HAS_OPERATION_STATUS};
		
		addRestriction((MergedRestriction)EchonetService.getClassRestrictionsOnProperty(
				EchonetService.MY_URI, EchonetService.PROP_PROVIDES_TEMPERATURE_SENSOR_SERVICE).copy(), ppTemperature_Sensors, serverLevelRestrictions);
		addRestriction((MergedRestriction)EchonetService.getClassRestrictionsOnProperty(
				EchonetService.MY_URI, EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE).copy(), ppAirconditioners, serverLevelRestrictions);
		addRestriction((MergedRestriction)EchonetService.getClassRestrictionsOnProperty(
				EchonetService.MY_URI, EchonetService.PROP_PROVIDES_LIGHTING_SERVICE).copy(), ppLightings, serverLevelRestrictions);
		addRestriction((MergedRestriction)EchonetService.getClassRestrictionsOnProperty(
				EchonetService.MY_URI, EchonetService.PROP_PROVIDES_CURTAIN_SERVICE).copy(), ppCurtains, serverLevelRestrictions);
		addRestriction((MergedRestriction)EchonetService.getClassRestrictionsOnProperty(
				EchonetService.MY_URI, EchonetService.PROP_PROVIDES_CONSENT_SERVICE).copy(), ppConsents, serverLevelRestrictions);
		
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
		
		SCallee_SmartEnvironmentProvidedService getLightings = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_GET_LIGHTING_DEVICES);
		getLightings.addOutput(OUTPUT_LIGHTINGS, Lighting.MY_URI, 0, 0, ppLightings);
		profiles[12] = getLightings.myProfile;
		
		SCallee_SmartEnvironmentProvidedService turnOnLightingdevice = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_TURN_ON_LIGHTING_DEVICE);
		turnOnLightingdevice.addFilteringInput(INPUT_LIGHTING_URI, Lighting.MY_URI, 1, 1, ppLightings);
		turnOnLightingdevice.myProfile.addChangeEffect(ppLighting_Operation_Status, new Boolean(true));
		profiles[13] = turnOnLightingdevice.myProfile;
		
		SCallee_SmartEnvironmentProvidedService turnOffLightingdevice = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_TURN_OFF_LIGHTING_DEVICE);
		turnOffLightingdevice.addFilteringInput(INPUT_LIGHTING_URI, Lighting.MY_URI, 1, 1, ppLightings);
		turnOffLightingdevice.myProfile.addChangeEffect(ppLighting_Operation_Status, new Boolean(false));
		profiles[14] = turnOffLightingdevice.myProfile;
		
		SCallee_SmartEnvironmentProvidedService setLightingIlluminationLevel = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_SET_LIGHTING_ILLUMINATION_LEVEL);
		setLightingIlluminationLevel.addFilteringInput(INPUT_LIGHTING_URI, Lighting.MY_URI, 1, 1, ppLightings);
		setLightingIlluminationLevel.addInputWithChangeEffect(INPUT_LIGHTING_ILLUMINATION_LEVEL, TypeMapper.getDatatypeURI(Integer.class), 1, 1, ppLighting_Illumination_Level);
		profiles[15] = setLightingIlluminationLevel.myProfile;
		
		SCallee_SmartEnvironmentProvidedService getCurtains = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_GET_CURTAIN_CONTROLLERS);
		getCurtains.addOutput(OUTPUT_CURTAINS, Curtain.MY_URI, 0, 0, ppCurtains);
		profiles[16] = getCurtains.myProfile;
		
		SCallee_SmartEnvironmentProvidedService openCurtain = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_OPEN_CURTAIN);
		openCurtain.addFilteringInput(INPUT_CURTAIN_URI, Curtain.MY_URI, 1, 1, ppCurtains);
		openCurtain.myProfile.addChangeEffect(ppCurtains_Status, new Boolean(true));
		profiles[17] = openCurtain.myProfile;
		
		SCallee_SmartEnvironmentProvidedService closeCurtain = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_CLOSE_CURTAIN);
		closeCurtain.addFilteringInput(INPUT_CURTAIN_URI, Curtain.MY_URI, 1, 1, ppCurtains);
		closeCurtain.myProfile.addChangeEffect(ppCurtains_Status, new Boolean(false));
		profiles[18] = closeCurtain.myProfile;
		
		SCallee_SmartEnvironmentProvidedService getConsents = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_GET_CONSENTS);
		getConsents.addOutput(OUTPUT_CURTAINS, ElectricConsent.MY_URI, 0, 0, ppConsents);
		profiles[19] = getConsents.myProfile;
		
		SCallee_SmartEnvironmentProvidedService turnOnConsent = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_TURN_ON_CONSENTS);
		turnOnConsent.addFilteringInput(INPUT_CONSENT_URI, ElectricConsent.MY_URI, 1, 1, ppConsents);
		turnOnConsent.myProfile.addChangeEffect(ppConsents_Status, new Boolean(true));
		profiles[20] = turnOnConsent.myProfile;
		
		SCallee_SmartEnvironmentProvidedService turnOffConsent = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_TURN_OFF_CONSENTS);
		turnOffConsent.addFilteringInput(INPUT_CONSENT_URI, ElectricConsent.MY_URI, 1, 1, ppConsents);
		turnOffConsent.myProfile.addChangeEffect(ppConsents_Status, new Boolean(false));
		profiles[21] = turnOffConsent.myProfile;
	}
	
}
