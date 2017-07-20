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
	
	public HomeAirConditioner() {
		super();
	}
	public HomeAirConditioner(String uri) {
		super(uri);
		setClassCode((byte) 0x30);
	}
	public String getClassURI(){
		return MY_URI;
	}
	public void setInput(SmartFacility AALEnvironment_individual){
		setProperty(PROPERTY_IS_SMART_FACILITY_COMPONENT, AALEnvironment_individual);
	}
	@Override
	public int getPropSerializationType(String propURI) {
		return super.getPropSerializationType(propURI);
	}

	
	// data objects
	public void setOperationStatus(boolean stt){
		changeProperty(PROPERTY_HAS_OPERATION_STATUS, new Boolean(stt));
	}
	public boolean getOperationStatus(){
		Boolean msg = (Boolean) getProperty(PROPERTY_HAS_OPERATION_STATUS);
		return (msg == null) ? null : msg.booleanValue();	
	}
	
	public  void setOperationPowerSaving(boolean stt){
		changeProperty(PROPERTY_HAS_OPERATION_POWER_SAVING,new Boolean(stt));		
	}
	public boolean getOperationPowerSaving(){
		Boolean msg = (Boolean) getProperty(PROPERTY_HAS_OPERATION_POWER_SAVING);
		return (msg == null) ? null : msg.booleanValue();		
	}
	
	
	public void setOperationModeSetting(String msg){
		changeProperty(PROPERTY_HAS_OPERATION_MODE_SETTING,msg );	
	}
	public String getOperationModeSetting(){
		String msg = (String) getProperty(PROPERTY_HAS_OPERATION_MODE_SETTING);
		return (msg== null) ? "EMPTY_MSG" : msg;
		
	}

	public void setSettingTemperatureValue(int temperature){
		changeProperty(PROPERTY_HAS_SETTING_TEMPERATURE_VALUE,new Integer(temperature));
	}
	public int getSettingTemperatureValue(){
		Integer msg = (Integer) getProperty(PROPERTY_HAS_SETTING_TEMPERATURE_VALUE);
		return  (msg == null) ? null : msg.intValue();	
	}
	
	
	public void setMeasuredRoomTemperatureValue(int temperature){
		changeProperty(PROPERTY_HAS_MEASURED_ROOM_TEMPERATURE_VALUE,new Integer(temperature));
	}
	public int getMeasuredRoomTemperatureValue(){
		Integer msg = (Integer) getProperty(PROPERTY_HAS_MEASURED_ROOM_TEMPERATURE_VALUE);
		return  (msg == null) ? null : msg.intValue();
	}
	
	public void setAirFlowRateSetting(String msg){
		changeProperty(PROPERTY_HAS_AIR_FLOW_RATE_SETTING, msg);		
	}
	public String getAirFlowRateSetting(){
		String msg = (String) getProperty(PROPERTY_HAS_AIR_FLOW_RATE_SETTING);
		return (msg == null) ? "EMPTY_MSG" : msg;
		
	}
	
}
