package ontologies;
import mainpackage.CaressesOntology;
import mainpackage.SmartFacility;


public class HomeAirConditioner extends AirConditionerRelatedOntology{
	public static final String MY_URI = CaressesOntology.NAMESPACE + "echonet_home_airconditioner";	
	
	public static final String PROPERTY_HAS_OPERATION_STATUS = CaressesOntology.NAMESPACE + "echonet_home_airconditioner_has_operation_status";
	public static final String PROPERTY_HAS_OPERATION_POWER_SAVING = CaressesOntology.NAMESPACE + "echonet_home_airconditioner_has_operation_power_saving";
	public static final String PROPERTY_HAS_OPERATION_MODE_SETTING = CaressesOntology.NAMESPACE + "echonet_home_airconditioner_has_operation_mode_setting";
	public static final String PROPERTY_HAS_SETTING_TEMPERATURE_VALUE = CaressesOntology.NAMESPACE + "echonet_home_airconditioner_has_setting_temperature_value";
	public static final String PROPERTY_HAS_MEASURED_ROOM_TEMPERATURE_VALUE = CaressesOntology.NAMESPACE + "echonet_home_airconditioner_has_mesured_room_temperature_value";
	public static final String PROPERTY_HAS_AIR_FLOW_RATE_SETTING = CaressesOntology.NAMESPACE + "echonet_home_airconditioner_has_air_flow_rate_setting";
	public static final String PROPERTY_HAS_HOME_AIRCONDITIONER_SUPER_CLASS_INSTALLATION_LOCATION = CaressesOntology.NAMESPACE + "echonet_home_airconditioner_has_super_class_installation_location";
	
	public HomeAirConditioner(String uri) {
		super(uri);
		changeProperty(MY_URI, uri);
	}
	public String getClassURI(){
		return MY_URI;
	}
	
	
	public String getOperationStatus(){
		String msg = (String) getProperty(PROPERTY_HAS_OPERATION_STATUS);
		return (msg == null) ? "" : msg;
		
	}
	
	public String getOperationPowerSaving(){
		String msg = (String) getProperty(PROPERTY_HAS_OPERATION_POWER_SAVING);
		return (msg == null) ? "" : msg;		
	}
	
	
	public String getOperationModeSetting(){
		String msg = (String) getProperty(PROPERTY_HAS_OPERATION_MODE_SETTING);
		return (msg == null) ? "" : msg;
		
	}
	
	public String getSettingTemperatureValue(){
		String msg = (String) getProperty(PROPERTY_HAS_SETTING_TEMPERATURE_VALUE);
		return (msg == null) ? "" : msg;		
	}
	
	public String getMeasuredRoomTemperatureValue(){
		String msg = (String) getProperty(PROPERTY_HAS_MEASURED_ROOM_TEMPERATURE_VALUE);
		return (msg == null) ? "" : msg;		
	}
	
	
	public String getAirFlowRateSetting(){
		String msg = (String) getProperty(PROPERTY_HAS_AIR_FLOW_RATE_SETTING);
		return (msg == null) ? "" : msg;
		
	}
	
	public void initOntology(boolean operation, boolean operationPowerSaving,String operationMode, int temperaturevalue, 
			int roomTemperature,  String air_flowRate, String location) {
		changeProperty(PROPERTY_HAS_OPERATION_STATUS, operation);
		changeProperty(PROPERTY_HAS_OPERATION_POWER_SAVING, operationPowerSaving);
		changeProperty(PROPERTY_HAS_OPERATION_MODE_SETTING, operationMode);
		changeProperty(PROPERTY_HAS_SETTING_TEMPERATURE_VALUE, temperaturevalue);
		changeProperty(PROPERTY_HAS_MEASURED_ROOM_TEMPERATURE_VALUE, roomTemperature);
		changeProperty(PROPERTY_HAS_HOME_AIRCONDITIONER_SUPER_CLASS_INSTALLATION_LOCATION, location);
	}
	
	public void setInput(SmartFacility AALEnvironment_individual){
		setProperty(PROPERTY_IS_SMART_FACILITY_COMPONENT, AALEnvironment_individual);
	}
	@Override
	public int getPropSerializationType(String propURI) {
		return super.getPropSerializationType(propURI);
	}

}
