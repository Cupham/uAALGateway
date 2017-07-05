package ontologies;

import org.universAAL.ontology.profile.HWSubProfile;

import mainpackage.CaressesOntology;

public class EchonetDeviceSuperOntology extends HWSubProfile{
	
	public static final String MY_URI = CaressesOntology.NAMESPACE +"SuperClass";
	public static final String PROPERTY_HAS_OPERATION_STATUS = CaressesOntology.NAMESPACE +"echonet_device_has_operation_status";
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
	@Override
	public int getPropSerializationType(String arg0) {
		// TODO Auto-generated method stub
		return PROP_SERIALIZATION_FULL;
	}
	
	public EchonetDeviceSuperOntology(String URI) {
		super(URI);
	}

	public  String getClassURI() {
		return MY_URI;
	}


	public  String getPropertyHasOperationStatus() {
		String operationStatus = (String) getProperty(PROPERTY_HAS_OPERATION_STATUS);
		return (operationStatus == null) ? "" : operationStatus;
	}


	public  String getPropertyHasInstallationLocation() {
		String installationLocation = (String) getProperty(PROPERTY_HAS_INSTALLATION_LOCATION);
		return (installationLocation == null) ? "" : installationLocation;
	}


	public  String getPropertyStandardVersionInformation() {
		String standardVersionInformation = (String) getProperty(PROPERTY_STANDARD_VERSION_INFORMATION);
		return (standardVersionInformation == null) ? "" : standardVersionInformation;
	}


	public  String getPropertyHasFaultStatus() {
		String faultStatus = (String) getProperty(PROPERTY_HAS_FAULT_STATUS);
		return (faultStatus == null) ? "" : faultStatus;
	}


	public  String getPropertyHasFaultDescription() {
		String faultDescription = (String) getProperty(PROPERTY_HAS_FAULT_DESCRIPTION);
		return (faultDescription == null) ? "" : faultDescription;
	}


	public  String getPropertyHasManufacturerCode() {
		String manufacturerCode = (String) getProperty(PROPERTY_HAS_MANUFACTURER_CODE);
		return (manufacturerCode == null) ? "" : manufacturerCode;
	}


	public  String getPropertyHasBusinessFacilityCode() {
		String businessFacilityCode = (String) getProperty(PROPERTY_HAS_BUSINESS_FACILITY_CODE);
		return (businessFacilityCode == null) ? "" : businessFacilityCode;

	}


	public  String getPropertyHasProductCode() {
		String productCode = (String) getProperty(PROPERTY_HAS_PRODUCT_CODE);
		return (productCode == null) ? "" : productCode;
	}


	public  String getPropertyHasProductionNumber() {
		String productionNumber = (String) getProperty(PROPERTY_HAS_PRODUCTION_NUMBER);
		return (productionNumber == null) ? "" : productionNumber;
	}


	public  String getPropertyHasProductionDate() {
		String productionDate = (String) getProperty(PROPERTY_HAS_PRODUCTION_DATE);
		return (productionDate == null) ? "" : productionDate;

	}


	public  String getPropertyHasIdentificationNumber() {
		String identificationNumber = (String) getProperty(PROPERTY_HAS_IDENTIFICATION_NUMBER);
		return (identificationNumber == null) ? "" : identificationNumber;
	}


	public  String getPropertyHasManufacturerFaultCode() {
		String manufacturerFaultCode = (String) getProperty(PROPERTY_HAS_MANUFACTURER_FAULT_CODE);
		return (manufacturerFaultCode == null) ? "" : manufacturerFaultCode;
	}


	public  String getPropertyHasCurrentLimitSetting() {
		String currentLimitSetting = (String) getProperty(PROPERTY_HAS_CURRENT_LIMIT_SETTING);
		return (currentLimitSetting == null) ? "" : currentLimitSetting;
	}


	public  String getPropertyHasPowerSavingOperationSetting() {
		String powerSavingOperationSetting = (String) getProperty(PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING);
		return (powerSavingOperationSetting == null) ? "" : powerSavingOperationSetting;
	}


	public  String getPropertyHasRemoteControlSetting() {
		String remoteControlSetting = (String) getProperty(PROPERTY_HAS_REMOTE_CONTROL_SETTING);
		return (remoteControlSetting == null) ? "" : remoteControlSetting;
	}


	public  String getPropertyHasCumulativeOperatingTime() {
		String cumulativeOperatingTime = (String) getProperty(PROPERTY_HAS_CUMULATIVE_OPERATING_TIME);
		return (cumulativeOperatingTime == null) ? "" : cumulativeOperatingTime;
	}


	public  String getPropertyHasCurrentTimeSetting() {
		String currentTimeSetting = (String) getProperty(PROPERTY_HAS_CURRENT_TIME_SETTING);
		return (currentTimeSetting == null) ? "" : currentTimeSetting;
	}


	public  String getPropertyHasCurrentDateSetting() {
		String currentDateSetting = (String) getProperty(PROPERTY_HAS_CURRENT_DATE_SETTING);
		return (currentDateSetting == null) ? "" : currentDateSetting;
	}


	public  String getPropertyHasMeasuredInstantaneousPowerConsumption() {
		String measuredInstantaneousPowerConsumption = (String) getProperty(PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION);
		return (measuredInstantaneousPowerConsumption == null) ? "" : measuredInstantaneousPowerConsumption;
	}


	public  String getPropertyHasMeasuredCumulativePowerConsumption() {
		String measuredCumulativePowerConsumption = (String) getProperty(PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION);
		return (measuredCumulativePowerConsumption == null) ? "" : measuredCumulativePowerConsumption;
	}


	public  String getPropertyHasPowerLimitSetting() {
		String powerLimitSetting = (String) getProperty(PROPERTY_HAS_POWER_LIMIT_SETTING);
		return (powerLimitSetting == null) ? "" : powerLimitSetting;
	}
	
	//---- setter
	
	public  void setPropertyHasOperationStatus(String msg) {
		changeProperty(PROPERTY_HAS_OPERATION_STATUS, msg);
	}


	public  void setPropertyHasInstallationLocation(String msg) {
		changeProperty(PROPERTY_HAS_INSTALLATION_LOCATION, msg);
	}


	public  void setPropertyStandardVersionInformation(String msg) {
		changeProperty(PROPERTY_STANDARD_VERSION_INFORMATION, msg);
	}


	public  void setPropertyHasFaultStatus(String msg) {
		changeProperty(PROPERTY_HAS_FAULT_STATUS, msg);
	}


	public  void setPropertyHasFaultDescription(String msg) {
		changeProperty(PROPERTY_HAS_FAULT_DESCRIPTION, msg);
	}


	public  void setPropertyHasManufacturerCode(String msg) {
		changeProperty(PROPERTY_HAS_MANUFACTURER_CODE, msg);
	}


	public  void setPropertyHasBusinessFacilityCode(String msg) {
		changeProperty(PROPERTY_HAS_BUSINESS_FACILITY_CODE, msg);
	}


	public  void setPropertyHasProductCode(String msg) {
		changeProperty(PROPERTY_HAS_PRODUCT_CODE, msg);
	}


	public  void setPropertyHasProductionNumber(String msg) {
		changeProperty(PROPERTY_HAS_PRODUCTION_NUMBER, msg);
	}


	public  void setPropertyHasProductionDate(String msg) {
		changeProperty(PROPERTY_HAS_PRODUCTION_DATE, msg);

	}


	public  void setPropertyHasIdentificationNumber(String msg) {
		changeProperty(PROPERTY_HAS_IDENTIFICATION_NUMBER, msg);
	}


	public  void setPropertyHasManufacturerFaultCode(String msg) {
		changeProperty(PROPERTY_HAS_MANUFACTURER_FAULT_CODE, msg);
	}


	public  void setPropertyHasCurrentLimitSetting(String msg) {
		changeProperty(PROPERTY_HAS_CURRENT_LIMIT_SETTING, msg);
	}


	public  void setPropertyHasPowerSavingOperationSetting(String msg) {
		changeProperty(PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING, msg);
	}


	public  void setPropertyHasRemoteControlSetting(String msg) {
		changeProperty(PROPERTY_HAS_REMOTE_CONTROL_SETTING, msg);
	}


	public  void setPropertyHasCumulativeOperatingTime(String msg) {
		changeProperty(PROPERTY_HAS_CUMULATIVE_OPERATING_TIME, msg);
	}


	public  void setPropertyHasCurrentTimeSetting(String msg) {
		changeProperty(PROPERTY_HAS_CURRENT_TIME_SETTING, msg);
	}


	public  void setPropertyHasCurrentDateSetting(String msg) {
		changeProperty(PROPERTY_HAS_CURRENT_DATE_SETTING, msg);
	}


	public  void setPropertyHasMeasuredInstantaneousPowerConsumption(String msg) {
		changeProperty(PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION, msg);
	}


	public  void setPropertyHasMeasuredCumulativePowerConsumption(String msg) {
		changeProperty(PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION, msg);
	}


	public  void setPropertyHasPowerLimitSetting(String msg) {
		changeProperty(PROPERTY_HAS_POWER_LIMIT_SETTING, msg);
	}
	

}
