package mainpackage;


/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_5#Ontologies_in_universAAL */

import org.universAAL.middleware.owl.MergedRestriction;
import org.universAAL.middleware.owl.OntologyManagement;
import org.universAAL.middleware.owl.SimpleOntology;
import org.universAAL.middleware.rdf.Resource;
import org.universAAL.middleware.rdf.ResourceFactory;
import org.universAAL.middleware.rdf.TypeMapper;
import org.universAAL.middleware.service.owls.profile.ServiceProfile;
import org.universAAL.ontology.phThing.DeviceService;

import ontologies.HomeAirConditioner;
import ontologies.TemperatureSensor;

import java.util.Hashtable;

public class TemperatureServiceCalleeProvidedService extends DeviceService{
	public static final String TEMPERATURE_SERVER_NAMESPACE = "http://CARESSESuniversAALskeleton.org/Services.owl#";
	public static final String MY_URI = TEMPERATURE_SERVER_NAMESPACE+ "TemperatureSensorService";
	
	public static final String GET_CONTROLLED_TEMPERATURE_SENSORS = TEMPERATURE_SERVER_NAMESPACE+ "GetControlledTemperatureSensors";
	public static final String OUTPUT_CONTROLLED_TEMPERATURE_SENSORS = TEMPERATURE_SERVER_NAMESPACE+ "ControlledTemperatureSensors";
	
	public static final String GET_TEMPERATURESENSOR_PROFILE = TEMPERATURE_SERVER_NAMESPACE+ "GetTemperatureSensorProfile";
	public static final String GET_TEMPERATURE = TEMPERATURE_SERVER_NAMESPACE+ "GetTemperature";
	public static final String OUTPUT_TEMPERATURE = TEMPERATURE_SERVER_NAMESPACE+ "Temperature";
	
	public static final String GET_TEMPERATURESENSOR_LOCATION = TEMPERATURE_SERVER_NAMESPACE+ "GetTemperatureSensorLocation";
	public static final String OUTPUT_TEMPERATURESENSOR_LOCATION = TEMPERATURE_SERVER_NAMESPACE+ "TemperatureSensorLocation";

	public static final String GET_TEMPERATURESENSOR_IP = TEMPERATURE_SERVER_NAMESPACE+ "GetTemperatureSensorIP";
	
	public static final String GET_CONTROLLED_AIRCONDTIONER = TEMPERATURE_SERVER_NAMESPACE+ "GetControlledAirconditioners";
	public static final String OUTPUT_CONTROLLED_AIRCONDTIONER = TEMPERATURE_SERVER_NAMESPACE+ "ControlledAirconditioners";

	public static final String SET_AIRCONDTIONER_TEMPERATURE = TEMPERATURE_SERVER_NAMESPACE+ "SetAirconditionerTemperature";
	public static final String AIRCONDITIONER_URI = TEMPERATURE_SERVER_NAMESPACE+"AirConditionerURI";
	public static final String AIRCONDITIONER_STATUS = TEMPERATURE_SERVER_NAMESPACE+"AirConditionerStatus";
	public static ServiceProfile[] profiles = new ServiceProfile[3];
	private static Hashtable serverLevelRestrictions = new Hashtable();
	
	
	static {
		OntologyManagement.getInstance().register(Activator.context,
				new SimpleOntology(MY_URI, DeviceService.MY_URI,
						new ResourceFactory() {						
							@Override
							public Resource createInstance(String classURI, String instanceURI, int index) {
								return new TemperatureServiceCalleeProvidedService(instanceURI);
							}
						}));
		
		String[] ppControls = new String[] { DeviceService.PROP_CONTROLS};
		String[] ppCurrentSettingTemperuture = new String[] {DeviceService.PROP_CONTROLS,TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE};
		
		String[] ppTemperatureValue = new String[]{DeviceService.PROP_CONTROLS,TemperatureSensor.MY_URI,TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE};
		String[] ppTemperatureLocation = new String[]{DeviceService.PROP_CONTROLS,TemperatureSensor.MY_URI,TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE};
		
		
		String[] ppTemperatureSensor = new String[] { DeviceService.PROP_CONTROLS,
				TemperatureSensor.MY_URI,TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE};
		
		String[] ppAirConditioner = new String[] { DeviceService.PROP_CONTROLS,
				TemperatureSensor.MY_URI,HomeAirConditioner.PROPERTY_HAS_OPERATION_POWER_SAVING};
		
		
		addRestriction((MergedRestriction) HomeAirConditioner
				.getClassRestrictionsOnProperty(DeviceService.MY_URI,
				DeviceService.PROP_CONTROLS).copy(),ppTemperatureSensor,
				serverLevelRestrictions);
		
		TemperatureServiceCalleeProvidedService getControlledTemperatureSensor = new TemperatureServiceCalleeProvidedService(GET_CONTROLLED_TEMPERATURE_SENSORS);
		getControlledTemperatureSensor.addOutput(OUTPUT_CONTROLLED_TEMPERATURE_SENSORS
				, HomeAirConditioner.MY_URI, 0, 0, ppTemperatureSensor);
		profiles[0] = getControlledTemperatureSensor.myProfile;
		
		TemperatureServiceCalleeProvidedService getControlledAirConditioner = new TemperatureServiceCalleeProvidedService(GET_CONTROLLED_AIRCONDTIONER);
		getControlledAirConditioner.addOutput(OUTPUT_CONTROLLED_AIRCONDTIONER
				, HomeAirConditioner.MY_URI, 0, 0, ppAirConditioner);
		profiles[1] = getControlledAirConditioner.myProfile;
		
		TemperatureServiceCalleeProvidedService setAirconditionerTemperature = new TemperatureServiceCalleeProvidedService(SET_AIRCONDTIONER_TEMPERATURE);
		setAirconditionerTemperature.addFilteringInput(AIRCONDITIONER_URI, HomeAirConditioner.MY_URI, 1, 1, ppControls);
		setAirconditionerTemperature.addInputWithChangeEffect(AIRCONDITIONER_STATUS, TypeMapper.
				getDatatypeURI(Boolean.class), 1, 1, ppCurrentSettingTemperuture);
		//setAirconditionerTemperature.myProfile.addChangeEffect(ppCurrentSettingTemperuture, new Boolean(true));
		profiles[2] = setAirconditionerTemperature.myProfile;
		
		
		
		/*
		TemperatureServiceCalleeProvidedService getTemperatureSensorLocation = new TemperatureServiceCalleeProvidedService(GET_TEMPERATURESENSOR_LOCATION);
		
		getTemperatureSensorLocation.addOutput(OUTPUT_TEMPERATURESENSOR_LOCATION,
					TemperatureSensor.PROPERTY_HAS_LOCATION, 1, 1, ppTemperatureLocation);
		profiles[1] = getTemperatureSensorLocation.myProfile;
		
		
		TemperatureServiceCalleeProvidedService getTemperature = new TemperatureServiceCalleeProvidedService(GET_TEMPERATURE);
		getTemperature.addOutput(OUTPUT_TEMPERATURE, TypeMapper.getDatatypeURI(Float.class), 1, 1, ppTemperatureValue);
		profiles[2] = getTemperature.myProfile;
		*/
	}
	
	public String getClassURI() {
		return MY_URI;
	}

	public TemperatureServiceCalleeProvidedService(String URI) {
		super(URI);
		// TODO Auto-generated constructor stub
	}
	

}
