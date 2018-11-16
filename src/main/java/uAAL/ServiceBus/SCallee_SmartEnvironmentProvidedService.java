package uAAL.ServiceBus;


import org.apache.log4j.Logger;
import org.universAAL.middleware.owl.MergedRestriction;
import org.universAAL.middleware.owl.OntologyManagement;
import org.universAAL.middleware.owl.SimpleOntology;
import org.universAAL.middleware.rdf.Resource;
import org.universAAL.middleware.rdf.ResourceFactory;
import org.universAAL.middleware.rdf.TypeMapper;
/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_5#Ontologies_in_universAAL */
import org.universAAL.middleware.service.owls.profile.ServiceProfile;
import org.universAAL.ontology.device.CurtainController;
import org.universAAL.ontology.echonetontology.EchonetOntology;
import org.universAAL.ontology.echonetontology.EchonetSuperDevice;
import org.universAAL.ontology.echonetontology.airconditionerRelatedDevices.HomeAirConditioner;
import org.universAAL.ontology.echonetontology.audiovisualRelatedDevices.Audio;
import org.universAAL.ontology.echonetontology.audiovisualRelatedDevices.Television;
import org.universAAL.ontology.echonetontology.housingFacilitiesRelatedDevices.ElectricWindow;
import org.universAAL.ontology.echonetontology.housingFacilitiesRelatedDevices.GeneralLighting;
import org.universAAL.ontology.echonetontology.managementOperationRelatedDevices.Switch;
import org.universAAL.ontology.echonetontology.sensorRelatedDevices.ElectricEnergySensor;
import org.universAAL.ontology.echonetontology.sensorRelatedDevices.TemperatureSensor;
import org.universAAL.ontology.echonetontology.values.InstallationLocationValue;
import org.universAAL.ontology.phThing.DeviceService;

import echonet.Objects.eDataObject;
import main.Activator;

import java.util.Hashtable;

public class SCallee_SmartEnvironmentProvidedService extends DeviceService{
	private static final Logger LOGGER = Logger.getLogger(SCallee_SmartEnvironmentProvidedService.class.getName());
	public static ServiceProfile[] profiles = new ServiceProfile[29];
	
	public static final String NAMESPACE = "http://CARESSESuniversAALskeleton.org/Callee_SmartEnvironment.owl#";
	public static final String MY_URI    = NAMESPACE + "Smart_Environment_Service";
	
	// : Services 
		//Temperature Sensor
	static final String SERVICE_GET_TEMPERATURE_SENSORS= NAMESPACE + "get_temperature_sensors";
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
		//Window
	static final String SERVICE_GET_WINDOW= NAMESPACE + "get_window";
	static final String SERVICE_OPEN_WINDOW= NAMESPACE + "open_window";
	static final String SERVICE_CLOSE_WINDOW= NAMESPACE + "close_window";
	
	//RADIO
	static final String SERVICE_GET_RADIO= NAMESPACE + "get_radio";
	static final String SERVICE_TURN_ON_RADIO= NAMESPACE + "turnon_radio";
	static final String SERVICE_TURN_OFF_RADIO= NAMESPACE + "turnoff_radio";
	
	//TV
	static final String SERVICE_GET_TV= NAMESPACE + "get_tv";
	static final String SERVICE_TURN_ON_TV= NAMESPACE + "turnon_tv";
	static final String SERVICE_TURN_OFF_TV= NAMESPACE + "turnoff_tv";
	// : Inputs
	static final String INPUT_LOCATION= NAMESPACE + "input_location";
	 	//Temperature Sensor Properties
	static final String INPUT_CURTAIN= NAMESPACE + "input_curtain";
	static final String INPUT_WINDOW= NAMESPACE + "input_window_uri";
	static final String INPUT_RADIO= NAMESPACE + "input_radio_uri";
	static final String INPUT_TV= NAMESPACE + "input_tv_uri";
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
	
	static final String OUTPUT_WINDOW= NAMESPACE + "output_window";
	static final String OUTPUT_TV= NAMESPACE + "output_televison";
	static final String OUTPUT_RADIO= NAMESPACE + "output_radio";
	
	public SCallee_SmartEnvironmentProvidedService (String uri) {
		super(uri);
	}
	public String getClassURI(){
		return MY_URI;
	}
	
	static{
		OntologyManagement.getInstance().register(Activator.context, new SimpleOntology(MY_URI, DeviceService.MY_URI, new ResourceFactory(){
			public Resource createInstance(String classURI, String instanceURI, int factoryIndex){
				return new SCallee_SmartEnvironmentProvidedService(instanceURI);
			}
		}));

		String[] ppTemperature_Sensors = new String[]{TemperatureSensor.MY_URI};
		String[] ppTemperature_Sensor_InstallationLocation = new String[]{DeviceService.PROP_CONTROLS,TemperatureSensor.PROPERTY_HAS_INSTALLATION_LOCATION};
		
		String[] ppLocation = new String[]{EchonetSuperDevice.PROPERTY_HAS_INSTALLATION_LOCATION};
		String[] ppAirconditioners = new String[]{HomeAirConditioner.MY_URI};
		String[] ppAirconditioner_OperationStatus = new String[]{DeviceService.PROP_CONTROLS,HomeAirConditioner.PROPERTY_HAS_OPERATION_STATUS};
		String[] ppAirconditioner_InstallationLocation = new String[]{DeviceService.PROP_CONTROLS,HomeAirConditioner.PROPERTY_HAS_INSTALLATION_LOCATION};
		String[] ppAirconditioner_OperationPowerSaving = new String[]{DeviceService.PROP_CONTROLS,HomeAirConditioner.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING};
		String[] ppAirconditioner_OperationMode = new String[]{DeviceService.PROP_CONTROLS,HomeAirConditioner.PROPERTY_HAS_OPERATION_MODE_SETTING};
		String[] ppAirconditioner_TemperatureValue = new String[]{DeviceService.PROP_CONTROLS,HomeAirConditioner.PROPERTY_HAS_CURRENT_SETTING_OF_TEMPERATURE};
		String[] ppAirconditioner_AirFlowRate = new String[]{DeviceService.PROP_CONTROLS,HomeAirConditioner.PROPERTY_HAS_AIR_FLOW_RATE_SETTING};
		
		String[] ppLightings =  new String[]{GeneralLighting.MY_URI};
		String[] ppLighting_Operation_Status =  new String[]{DeviceService.PROP_CONTROLS,GeneralLighting.PROPERTY_HAS_OPERATION_STATUS};
		String[] ppLighting_Illumination_Level =  new String[]{DeviceService.PROP_CONTROLS,GeneralLighting.PROPERTY_HAS_ILLUMINANCE_LEVEL};
		
		String[] ppConsents =  new String[]{ElectricEnergySensor.MY_URI};
		String[] ppConsents_Status =  new String[]{DeviceService.PROP_CONTROLS,ElectricEnergySensor.PROPERTY_HAS_OPERATION_STATUS};
		
		String[] ppWindow =  new String[]{ElectricWindow.MY_URI};
		String[] ppWindow_Status =  new String[]{DeviceService.PROP_CONTROLS,ElectricWindow.PROPERTY_HAS_OPERATION_STATUS};
		
		String[] ppCurtain =  new String[]{Switch.MY_URI};
		String[] ppCurtain_Status =  new String[]{DeviceService.PROP_CONTROLS,Switch.PROPERTY_HAS_OPERATION_STATUS};
		
		String[] ppRadio =  new String[]{Audio.MY_URI};
		String[] ppRadio_Status =  new String[]{DeviceService.PROP_CONTROLS,Audio.PROPERTY_HAS_OPERATION_STATUS};
		
		String[] ppTV =  new String[]{Television.MY_URI};
		String[] ppTV_Status =  new String[]{DeviceService.PROP_CONTROLS,Television.PROPERTY_HAS_OPERATION_STATUS};
		
		
		SCallee_SmartEnvironmentProvidedService getTemperatureSensors = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_GET_TEMPERATURE_SENSORS);
		getTemperatureSensors.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		getTemperatureSensors.addOutput(OUTPUT_TEMPERATURE_SENSORS, TemperatureSensor.MY_URI, 0, 0, ppTemperature_Sensors);
		profiles[0] = getTemperatureSensors.myProfile;
		
		SCallee_SmartEnvironmentProvidedService setTemperatureSensorLocation = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_SET_TEMPERATURE_SENSOR_LOCATION);
		setTemperatureSensorLocation.addFilteringInput(INPUT_TEMPERATURE_SENSOR_URI, TemperatureSensor.MY_URI, 1, 1, ppTemperature_Sensors);
		setTemperatureSensorLocation.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		setTemperatureSensorLocation.addInputWithChangeEffect(INPUT_TEMPERATURE_SENSOR_LOCATION, TypeMapper.getDatatypeURI(String.class), 1, 1, ppTemperature_Sensor_InstallationLocation);
		profiles[1] = setTemperatureSensorLocation.myProfile;
		
		SCallee_SmartEnvironmentProvidedService getAirconditioners = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_GET_AIRCONDITIONERS);
		getAirconditioners.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		getAirconditioners.addOutput(OUTPUT_AIRCONDTIONERS, HomeAirConditioner.MY_URI, 0, 0, ppAirconditioners);
		profiles[2] = getAirconditioners.myProfile;
		
		SCallee_SmartEnvironmentProvidedService turnOnAirconditioner = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_TURN_ON_AIRCONDITIONER);
		turnOnAirconditioner.addFilteringInput(INPUT_AIRCONDITIONER_URI, HomeAirConditioner.MY_URI, 1, 1, ppAirconditioners);
		turnOnAirconditioner.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		turnOnAirconditioner.myProfile.addChangeEffect(ppAirconditioner_OperationStatus, new Boolean(true));
		profiles[3] = turnOnAirconditioner.myProfile;
		
		SCallee_SmartEnvironmentProvidedService turnOffAirconditioner = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_TURN_OFF_AIRCONDITIONER);
		turnOffAirconditioner.addFilteringInput(INPUT_AIRCONDITIONER_URI, HomeAirConditioner.MY_URI, 1, 1, ppAirconditioners);
		turnOffAirconditioner.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		turnOffAirconditioner.myProfile.addChangeEffect(ppAirconditioner_OperationStatus, new Boolean(false));
		profiles[4] = turnOffAirconditioner.myProfile;
		
		SCallee_SmartEnvironmentProvidedService setAirconditionerLocation = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_SET_AIRCONDITIONER_LOCATION);
		setAirconditionerLocation.addFilteringInput(INPUT_AIRCONDITIONER_URI, HomeAirConditioner.MY_URI, 1, 1, ppAirconditioners);
		setAirconditionerLocation.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		setAirconditionerLocation.addInputWithChangeEffect(INPUT_AIRCONDITIONER_LOCATION, TypeMapper.getDatatypeURI(String.class), 1, 1, ppAirconditioner_InstallationLocation);
		profiles[5] = setAirconditionerLocation.myProfile;
		
		SCallee_SmartEnvironmentProvidedService setAirconditionerOperationPowerSaving = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_SET_AIRCONDITIONER_OPERATION_POWER_SAVING);
		setAirconditionerOperationPowerSaving.addFilteringInput(INPUT_AIRCONDITIONER_URI, HomeAirConditioner.MY_URI, 1, 1, ppAirconditioners);
		setAirconditionerOperationPowerSaving.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		setAirconditionerOperationPowerSaving.addInputWithChangeEffect(INPUT_AIRCONDITIONER_POWER_SAVING_OPTION, TypeMapper.getDatatypeURI(Boolean.class), 1, 1, ppAirconditioner_OperationPowerSaving);
		profiles[6] = setAirconditionerOperationPowerSaving.myProfile;
		
		SCallee_SmartEnvironmentProvidedService setAirconditionerOperationMode = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_SET_AIRCONDITIONER_OPERATION_MODE);
		setAirconditionerOperationMode.addFilteringInput(INPUT_AIRCONDITIONER_URI, HomeAirConditioner.MY_URI, 1, 1, ppAirconditioners);
		setAirconditionerOperationMode.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		setAirconditionerOperationMode.addInputWithChangeEffect(INPUT_AIRCONDITIONER_OPERATION_MODE, TypeMapper.getDatatypeURI(String.class), 1, 1, ppAirconditioner_OperationMode);
		profiles[7] = setAirconditionerOperationMode.myProfile;
		
		SCallee_SmartEnvironmentProvidedService setAirconditionerTemperature = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_SET_AIRCONDITIONER_TEMPERATURE);
		setAirconditionerTemperature.addFilteringInput(INPUT_AIRCONDITIONER_URI, HomeAirConditioner.MY_URI, 1, 1, ppAirconditioners);
		setAirconditionerTemperature.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		setAirconditionerTemperature.addInputWithChangeEffect(INPUT_AIRCONDITIONER_TEMPERATURE, TypeMapper.getDatatypeURI(Integer.class), 1, 1, ppAirconditioner_TemperatureValue);
		profiles[8] = setAirconditionerTemperature.myProfile;
		
		SCallee_SmartEnvironmentProvidedService setAirconditionerAirFlowRate = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_SET_AIRCONDITIONER_TEMPERATURE);
		setAirconditionerAirFlowRate.addFilteringInput(INPUT_AIRCONDITIONER_URI, HomeAirConditioner.MY_URI, 1, 1, ppAirconditioners);
		setAirconditionerAirFlowRate.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		setAirconditionerAirFlowRate.addInputWithChangeEffect(INPUT_AIRCONDITIONER_AIR_FLOW_RATE, TypeMapper.getDatatypeURI(String.class), 1, 1, ppAirconditioner_AirFlowRate);
		profiles[9] = setAirconditionerAirFlowRate.myProfile;
		
		SCallee_SmartEnvironmentProvidedService getLightings = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_GET_LIGHTING_DEVICES);
		getLightings.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		getLightings.addOutput(OUTPUT_LIGHTINGS, GeneralLighting.MY_URI, 0, 0, ppLightings);
		profiles[10] = getLightings.myProfile;
		
		SCallee_SmartEnvironmentProvidedService turnOnLightingdevice = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_TURN_ON_LIGHTING_DEVICE);
		turnOnLightingdevice.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		turnOnLightingdevice.addFilteringInput(INPUT_LIGHTING_URI, GeneralLighting.MY_URI, 1, 1, ppLightings);
		turnOnLightingdevice.myProfile.addChangeEffect(ppLighting_Operation_Status, new Boolean(true));
		profiles[11] = turnOnLightingdevice.myProfile;
		
		SCallee_SmartEnvironmentProvidedService turnOffLightingdevice = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_TURN_OFF_LIGHTING_DEVICE);
		turnOffLightingdevice.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		turnOffLightingdevice.addFilteringInput(INPUT_LIGHTING_URI, GeneralLighting.MY_URI, 1, 1, ppLightings);
		turnOffLightingdevice.myProfile.addChangeEffect(ppLighting_Operation_Status, new Boolean(false));
		profiles[12] = turnOffLightingdevice.myProfile;
		
		SCallee_SmartEnvironmentProvidedService setLightingIlluminationLevel = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_SET_LIGHTING_ILLUMINATION_LEVEL);
		setLightingIlluminationLevel.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		setLightingIlluminationLevel.addFilteringInput(INPUT_LIGHTING_URI, GeneralLighting.MY_URI, 1, 1, ppLightings);
		setLightingIlluminationLevel.addInputWithChangeEffect(INPUT_LIGHTING_ILLUMINATION_LEVEL, TypeMapper.getDatatypeURI(Integer.class), 1, 1, ppLighting_Illumination_Level);
		profiles[13] = setLightingIlluminationLevel.myProfile;
		
		SCallee_SmartEnvironmentProvidedService getConsents = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_GET_CONSENTS);
		getConsents.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		getConsents.addOutput(OUTPUT_CONSENTS, ElectricEnergySensor.MY_URI, 0, 0, ppConsents);
		profiles[14] = getConsents.myProfile;
		
		SCallee_SmartEnvironmentProvidedService turnOnConsent = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_TURN_ON_CONSENTS);
		turnOnConsent.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		turnOnConsent.addFilteringInput(INPUT_CONSENT_URI, ElectricEnergySensor.MY_URI, 1, 1, ppConsents);
		turnOnConsent.myProfile.addChangeEffect(ppConsents_Status, new Boolean(true));
		profiles[15] = turnOnConsent.myProfile;
		
		SCallee_SmartEnvironmentProvidedService turnOffConsent = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_TURN_OFF_CONSENTS);
		turnOffConsent.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		turnOffConsent.addFilteringInput(INPUT_CONSENT_URI, ElectricEnergySensor.MY_URI, 1, 1, ppConsents);
		turnOffConsent.myProfile.addChangeEffect(ppConsents_Status, new Boolean(false));
		profiles[16] = turnOffConsent.myProfile;
		
		SCallee_SmartEnvironmentProvidedService getWindow = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_GET_WINDOW);
		getWindow.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		getWindow.addOutput(OUTPUT_WINDOW, ElectricWindow.MY_URI, 0, 0, ppWindow);
		profiles[17] = getWindow.myProfile;
		
		SCallee_SmartEnvironmentProvidedService openWindow = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_OPEN_WINDOW);
		openWindow.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		openWindow.addFilteringInput(INPUT_WINDOW, ElectricWindow.MY_URI, 1, 1, ppWindow);
		openWindow.myProfile.addChangeEffect(ppWindow_Status, new Boolean(true));
		profiles[18] = openWindow.myProfile;
		
		SCallee_SmartEnvironmentProvidedService closeWindow = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_CLOSE_WINDOW);
		closeWindow.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		closeWindow.addFilteringInput(INPUT_WINDOW, ElectricWindow.MY_URI, 1, 1, ppWindow);
		closeWindow.myProfile.addChangeEffect(ppWindow_Status, new Boolean(false));
		profiles[19] = closeWindow.myProfile;
		
		
		SCallee_SmartEnvironmentProvidedService getRadio = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_GET_RADIO);
		getRadio.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		getRadio.addOutput(OUTPUT_RADIO, Audio.MY_URI, 0, 0, ppRadio);
		profiles[20] = getRadio.myProfile;
		
		SCallee_SmartEnvironmentProvidedService turnOnRadio = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_TURN_ON_RADIO);
		turnOnRadio.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		turnOnRadio.addFilteringInput(INPUT_RADIO, Audio.MY_URI, 1, 1, ppRadio);
		turnOnRadio.myProfile.addChangeEffect(ppRadio_Status, new Boolean(true));
		profiles[21] = turnOnRadio.myProfile;
		
		SCallee_SmartEnvironmentProvidedService turnOffRadio = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_TURN_OFF_RADIO);
		turnOffRadio.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		turnOffRadio.addFilteringInput(INPUT_RADIO, Audio.MY_URI, 1, 1, ppRadio);
		turnOffRadio.myProfile.addChangeEffect(ppRadio_Status, new Boolean(false));
		profiles[22] = turnOffRadio.myProfile;
		
		SCallee_SmartEnvironmentProvidedService getTV = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_GET_TV);
		getTV.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		getTV.addOutput(OUTPUT_TV, Television.MY_URI, 0, 0, ppTV);
		profiles[23] = getTV.myProfile;
		
		SCallee_SmartEnvironmentProvidedService turnOnTV = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_TURN_ON_TV);
		turnOnTV.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		turnOnTV.addFilteringInput(INPUT_TV, Television.MY_URI, 1, 1, ppTV);
		turnOnTV.myProfile.addChangeEffect(ppTV_Status, new Boolean(true));
		profiles[24] = turnOnTV.myProfile;
		
		SCallee_SmartEnvironmentProvidedService turnOffTV = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_TURN_OFF_TV);
		turnOffTV.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		turnOffTV.addFilteringInput(INPUT_TV, Television.MY_URI, 1, 1, ppTV);
		turnOffTV.myProfile.addChangeEffect(ppTV_Status, new Boolean(false));
		profiles[25] = turnOffTV.myProfile;
		
		SCallee_SmartEnvironmentProvidedService getCurtain = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_GET_CURTAIN_CONTROLLERS);
		getCurtain.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		getCurtain.addOutput(OUTPUT_CURTAINS, Switch.MY_URI, 0, 0, ppCurtain);
		profiles[26] = getCurtain.myProfile;
		
		SCallee_SmartEnvironmentProvidedService openCurtain = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_OPEN_CURTAIN);
		openCurtain.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		openCurtain.addFilteringInput(INPUT_CURTAIN, Switch.MY_URI, 1, 1, ppCurtain);
		openCurtain.myProfile.addChangeEffect(ppCurtain_Status, new Boolean(true));
		profiles[27] = openCurtain.myProfile;
		
		SCallee_SmartEnvironmentProvidedService closeCurtain = 
				new SCallee_SmartEnvironmentProvidedService(SERVICE_CLOSE_CURTAIN);
		closeCurtain.addFilteringInput(INPUT_LOCATION, InstallationLocationValue.MY_URI, 0, 1, ppLocation);
		closeCurtain.addFilteringInput(INPUT_CURTAIN, Switch.MY_URI, 1, 1, ppCurtain);
		closeCurtain.myProfile.addChangeEffect(ppCurtain_Status, new Boolean(false));
		profiles[28] = closeCurtain.myProfile;
				
	}
	
}
