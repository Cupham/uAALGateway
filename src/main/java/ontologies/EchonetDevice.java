package ontologies;

import java.util.Date;

import org.universAAL.ontology.phThing.Device;

import mainpackage.CaressesOntology;

public class EchonetDevice extends Device{
	public static final String MY_URI = CaressesOntology.NAMESPACE + "echonet_device";
	public static final String PROPERTY_IS_SMART_FACILITY_COMPONENT = CaressesOntology.NAMESPACE + "is_smart_facility_component";
	public static final String PROPERTY_HAS_CLASS_GROUP_CODE = CaressesOntology.NAMESPACE + "echonet_device_has_class_group_code";
	public static final String PROPERTY_HAS_CLASS_CODE = CaressesOntology.NAMESPACE + "echonet_device_has_class_code";
	public static final String PROPERTY_HAS_INSTANCE_CODE = CaressesOntology.NAMESPACE + "echonet_device_has_instance_code";
	public static final String PROPERTY_HAS_OPERATION_STATUS = CaressesOntology.NAMESPACE +"echonet_device_has_operation_status";
	public static final String PROPERTY_HAS_IP_ADDRESS = CaressesOntology.NAMESPACE + "echonet_device_has_IP_address";
	public static final String PROPERTY_HAS_INSTALLATION_LOCATION = CaressesOntology.NAMESPACE +"echonet_device_has_installation_ocation";
	public static final String PROPERTY_STANDARD_VERSION_INFORMATION = CaressesOntology.NAMESPACE +"echonet_device_has_standard_version_information";
	public static final String PROPERTY_HAS_FAULT_STATUS = CaressesOntology.NAMESPACE +"echonet_device_has_fault_status";
	public static final String PROPERTY_HAS_FAULT_DESCRIPTION = CaressesOntology.NAMESPACE +"echonet_device_has_fault_description";
	public static final String PROPERTY_HAS_MANUFACTURER_CODE = CaressesOntology.NAMESPACE +"echonet_device_has_manufacturer_code";
	public static final String PROPERTY_HAS_BUSINESS_FACILITY_CODE = CaressesOntology.NAMESPACE +"echonet_device_has_business_facility_code";
	public static final String PROPERTY_HAS_PRODUCT_CODE = CaressesOntology.NAMESPACE +"echonet_device_has_product_code";
	public static final String PROPERTY_HAS_PRODUCTION_NUMBER = CaressesOntology.NAMESPACE +"echonet_device_has_production_number";
	public static final String PROPERTY_HAS_PRODUCTION_DATE = CaressesOntology.NAMESPACE +"echonet_device_has_production_date";
	public static final String PROPERTY_HAS_IDENTIFICATION_NUMBER = CaressesOntology.NAMESPACE +"echonet_device_has_identification_number";
	public static final String PROPERTY_HAS_MANUFACTURER_FAULT_CODE = CaressesOntology.NAMESPACE +"echonet_device_has_manufacturer_fault_code";
	public static final String PROPERTY_HAS_CURRENT_LIMIT_SETTING = CaressesOntology.NAMESPACE +"echonet_device_has_current_limit_setting";
	public static final String PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING = CaressesOntology.NAMESPACE +"echonet_device_has_power_saving_operation_setting";
	public static final String PROPERTY_HAS_REMOTE_CONTROL_SETTING = CaressesOntology.NAMESPACE +"echonet_device_has_remote_control_setting";
	public static final String PROPERTY_HAS_CUMULATIVE_OPERATING_TIME = CaressesOntology.NAMESPACE +"echonet_device_has_cumulative_operating_time";
	public static final String PROPERTY_HAS_CURRENT_TIME_SETTING= CaressesOntology.NAMESPACE +"echonet_device_has_current_time_setting";
	public static final String PROPERTY_HAS_CURRENT_DATE_SETTING= CaressesOntology.NAMESPACE +"echonet_device_has_current_date_setting";
	public static final String PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION = CaressesOntology.NAMESPACE +"echonet_device_has_measured_instantaneous_power_consumption";
	public static final String PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION = CaressesOntology.NAMESPACE +"echonet_device_has_measured_cumulative_power_consumption";
	public static final String PROPERTY_HAS_POWER_LIMIT_SETTING = CaressesOntology.NAMESPACE +"echonet_device_has_power_limit_setting";
	
	public EchonetDevice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EchonetDevice(String uri) {
		super(uri);
		// TODO Auto-generated constructor stub
	}

	public  String getClassURI() {
		return MY_URI;
	}
	
	public byte getClassGroupCode(){
		Byte cg_Code = (Byte) getProperty(PROPERTY_HAS_CLASS_GROUP_CODE);
		return (cg_Code == null) ? null : cg_Code.byteValue(); 
	}	
	public void setClassGroupCode(byte msg) {
		changeProperty(PROPERTY_HAS_CLASS_GROUP_CODE, new Byte(msg));	
	}
	
	public byte getClassCode(){
		Byte c_Code = (Byte) getProperty(PROPERTY_HAS_CLASS_CODE);
		return (c_Code == null) ? null : c_Code.byteValue(); 
	}	
	public void setClassCode(byte msg) {
		changeProperty(PROPERTY_HAS_CLASS_CODE, new Byte(msg));	
	}
	
	public byte getInstanceCode(){
		Byte i_Code = Byte.parseByte(getProperty(PROPERTY_HAS_INSTANCE_CODE).toString());
		return (i_Code == null) ? null : i_Code.byteValue(); 
	}	
	public void setInstanceCode(byte msg) {
		changeProperty(PROPERTY_HAS_INSTANCE_CODE, new Byte(msg));	
	}
	
	
	public boolean getOperationStatus(){
		Boolean data = (Boolean) getProperty(PROPERTY_HAS_OPERATION_STATUS);
		return (data == null) ? null : data.booleanValue(); 
	}
	public void setOperationStatus(boolean operationStatus) {
		changeProperty(PROPERTY_HAS_OPERATION_STATUS, new Boolean(operationStatus));	
	}
	
	
	public String getIPAddress(){
		String ipAddr = (String) getProperty(PROPERTY_HAS_IP_ADDRESS);
		return (ipAddr == null) ? "EMPTY_MSG" : ipAddr; 
	}	
	public void setIPAddress(String msg) {
		changeProperty(PROPERTY_HAS_IP_ADDRESS, msg);	
	}

	
	public  String getInstallationLocation() {
		String installationLOC = (String) getProperty(PROPERTY_HAS_INSTALLATION_LOCATION);
		return (installationLOC == null) ? "EMPTY_MSG" : installationLOC; 
	}
	public  void setInstallationLocation(String msg) {
		changeProperty(PROPERTY_HAS_INSTALLATION_LOCATION, msg);
	}


	public  String getPropertyStandardVersionInformation() {
		String standardVersionInformation = (String) getProperty(PROPERTY_STANDARD_VERSION_INFORMATION);
		return (standardVersionInformation == null) ? "EMPTY_MSG" : standardVersionInformation;
	}	
	public  void setPropertyStandardVersionInformation(String msg) {
		changeProperty(PROPERTY_STANDARD_VERSION_INFORMATION, msg);
	}

	
	public  boolean getFaultStatus() {
		Boolean faultStatus = (Boolean) getProperty(PROPERTY_HAS_FAULT_STATUS);
		return (faultStatus == null) ? null : faultStatus.booleanValue();
	}
	public  void setFaultStatus(boolean msg) {
		changeProperty(PROPERTY_HAS_FAULT_STATUS, new Boolean(msg));
	}

	
	public  String getFaultDescription() {
		String faultDescription = (String) getProperty(PROPERTY_HAS_FAULT_DESCRIPTION);
		return (faultDescription == null) ? "EMPTY_MSG" : faultDescription;
	}	
	public  void setFaultDescription(String msg) {
		changeProperty(PROPERTY_HAS_FAULT_DESCRIPTION, msg);
	}


	public  String getManufacturerCode() {
		String manufacturerCode = (String) getProperty(PROPERTY_HAS_MANUFACTURER_CODE);
		return (manufacturerCode == null) ? "EMPTY_MSG" : manufacturerCode;
	}
	public  void setManufacturerCode(String msg) {
		changeProperty(PROPERTY_HAS_MANUFACTURER_CODE, msg);
	}
	
	
	public  String getBusinessFacilityCode() {
		String businessFacilityCode = (String) getProperty(PROPERTY_HAS_BUSINESS_FACILITY_CODE);
		return (businessFacilityCode == null) ? "EMPTY_MSG" : businessFacilityCode;
	}
	public  void setBusinessFacilityCode(String msg) {
		changeProperty(PROPERTY_HAS_BUSINESS_FACILITY_CODE, msg);
	}

	
	public  String getProductCode() {
		String productCode = (String) getProperty(PROPERTY_HAS_PRODUCT_CODE);
		return (productCode == null) ? "EMPTY_MSG" : productCode;
	}
	public  void setProductCode(String msg) {
		changeProperty(PROPERTY_HAS_PRODUCT_CODE, msg);
	}


	public  String getProductionNumber() {
		String productionNumber = (String) getProperty(PROPERTY_HAS_PRODUCTION_NUMBER);
		return (productionNumber == null) ? "EMPTY_MSG" : productionNumber;
	}
	public  void setProductionNumber(String msg) {
		changeProperty(PROPERTY_HAS_PRODUCTION_NUMBER, msg);
	}


	public  Date getProductionDate() {
		Date productionDate = (Date) getProperty(PROPERTY_HAS_PRODUCTION_DATE);
		return (productionDate == null) ? null : productionDate;
	}
	public  void setProductionDate(Date msg) {
		changeProperty(PROPERTY_HAS_PRODUCTION_DATE, msg);

	}


	public  String getIdentificationNumber() {
		String identificationNumber = (String) getProperty(PROPERTY_HAS_IDENTIFICATION_NUMBER);
		return (identificationNumber == null) ? "EMPTY_MSG" : identificationNumber;
	}
	public  void setIdentificationNumber(String msg) {
		changeProperty(PROPERTY_HAS_IDENTIFICATION_NUMBER, msg);
	}


	public  String getManufacturerFaultCode() {
		String manufacturerFaultCode = (String) getProperty(PROPERTY_HAS_MANUFACTURER_FAULT_CODE);
		return (manufacturerFaultCode == null) ? "EMPTY_MSG" : manufacturerFaultCode;
	}
	public  void setManufacturerFaultCode(String msg) {
		changeProperty(PROPERTY_HAS_MANUFACTURER_FAULT_CODE, msg);
	}


	public  String getCurrentLimitSetting() {
		String currentLimitSetting = (String) getProperty(PROPERTY_HAS_CURRENT_LIMIT_SETTING);
		return (currentLimitSetting == null) ? "EMPTY_MSG" : currentLimitSetting;
	}
	public  void setCurrentLimitSetting(String msg) {
		changeProperty(PROPERTY_HAS_CURRENT_LIMIT_SETTING, msg);
	}


	public  boolean getPowerSavingOperationSetting() {
		Boolean powerSavingOperationSetting = (Boolean) getProperty(PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING);
		return (powerSavingOperationSetting == null) ? null : powerSavingOperationSetting.booleanValue();
	}
	public  void setPowerSavingOperationSetting(boolean msg) {
		changeProperty(PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING, new Boolean(msg));
	}


	public  boolean getRemoteControlSetting() {
		Boolean remoteControlSetting = (Boolean) getProperty(PROPERTY_HAS_REMOTE_CONTROL_SETTING);
		return (remoteControlSetting == null) ? null : remoteControlSetting.booleanValue();
	}
	public  void setRemoteControlSetting(boolean msg) {
		changeProperty(PROPERTY_HAS_REMOTE_CONTROL_SETTING, new Boolean(msg));
	}

	public  String getCumulativeOperatingTime() {
		String cumulativeOperatingTime = (String) getProperty(PROPERTY_HAS_CUMULATIVE_OPERATING_TIME);
		return (cumulativeOperatingTime == null) ? "EMPTY_MSG" : cumulativeOperatingTime;
	}
	public  void setCumulativeOperatingTime(String msg) {
		changeProperty(PROPERTY_HAS_CUMULATIVE_OPERATING_TIME, msg);
	}


	public  String getCurrentTimeSetting() {
		String currentTimeSetting = (String) getProperty(PROPERTY_HAS_CURRENT_TIME_SETTING);
		return (currentTimeSetting == null) ? "EMPTY_MSG" : currentTimeSetting;
	}
	public  void setCurrentTimeSetting(String msg) {
		changeProperty(PROPERTY_HAS_CURRENT_TIME_SETTING, msg);
	}


	public  Date getCurrentDateSetting() {
		Date currentDateSetting = (Date) getProperty(PROPERTY_HAS_CURRENT_DATE_SETTING);
		return (currentDateSetting == null) ? null : currentDateSetting;
	}
	public  void setCurrentDateSetting(Date msg) {
		changeProperty(PROPERTY_HAS_CURRENT_DATE_SETTING, msg);
	}


	public  short getMeasuredInstantaneousPowerConsumption() {
		Short measuredInstantaneousPowerConsumption = (Short) getProperty(PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION);
		return (measuredInstantaneousPowerConsumption == null) ? null : measuredInstantaneousPowerConsumption.shortValue();
	}
	public  void setMeasuredInstantaneousPowerConsumption(short msg) {
		changeProperty(PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION, new Short(msg));
	}


	public  long getMeasuredCumulativePowerConsumption() {
		Long measuredCumulativePowerConsumption = (Long) getProperty(PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION);
		return (measuredCumulativePowerConsumption == null) ? null : measuredCumulativePowerConsumption.longValue();
	}
	public  void setMeasuredCumulativePowerConsumption(long msg) {
		changeProperty(PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION, new Long(msg));
	}


	public  short getPowerLimitSetting() {
		Short powerLimitSetting = (Short) getProperty(PROPERTY_HAS_POWER_LIMIT_SETTING);
		return (powerLimitSetting == null) ? null : powerLimitSetting.shortValue();
	}
	public  void setPowerLimitSetting(short msg) {
		changeProperty(PROPERTY_HAS_POWER_LIMIT_SETTING, new Short(msg));
	}
	
}
