package ont;
import org.universAAL.ontology.phThing.Device;
import org.universAAL.ontology.profile.HWSubProfile;

import mainpackage.CaressesOntology;

public class DeviceSuperClass extends Device{
	
	public static final String MY_URI = CaressesOntology.NAMESPACE +"SuperClass";
	public static final String PROPERTY_HAS_OPERATION_STATUS = CaressesOntology.NAMESPACE +"hasOperationStatus";
	public static final String PROPERTY_HAS_INSTALLATION_LOCATION = CaressesOntology.NAMESPACE +"hasInstallationLocation";
	public static final String PROPERTY_STANDARD_VERSION_INFORMATION = CaressesOntology.NAMESPACE +"hasStandardVersionInformation";
	public static final String PROPERTY_HAS_FAULT_STATUS = CaressesOntology.NAMESPACE +"hasFaultStatus";
	public static final String PROPERTY_HAS_FAULT_DESCRIPTION = CaressesOntology.NAMESPACE +"hasFaultDescription";
	public static final String PROPERTY_HAS_MANUFACTURER_CODE = CaressesOntology.NAMESPACE +"hasManufacturerCode";
	public static final String PROPERTY_HAS_BUSINESS_FACILITY_CODE = CaressesOntology.NAMESPACE +"hasBusinessFacilityCode";
	public static final String PROPERTY_HAS_PRODUCT_CODE = CaressesOntology.NAMESPACE +"hasProductCode";
	public static final String PROPERTY_HAS_PRODUCTION_NUMBER = CaressesOntology.NAMESPACE +"hasProductionNumber";
	public static final String PROPERTY_HAS_PRODUCTION_DATE = CaressesOntology.NAMESPACE +"hasProductionDate";
	public static final String PROPERTY_HAS_IDENTIFICATION_NUMBER = CaressesOntology.NAMESPACE +"hasIdentificationNumber";
	public static final String PROPERTY_HAS_MANUFACTURER_FAULT_CODE = CaressesOntology.NAMESPACE +"hasManufacturerFaultCode";
	public static final String PROPERTY_HAS_CURRENT_LIMIT_SETTING = CaressesOntology.NAMESPACE +"hasCurrentLimitSetting";
	public static final String PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING = CaressesOntology.NAMESPACE +"hasPowerSavingOperationSetting";
	public static final String PROPERTY_HAS_REMOTE_CONTROL_SETTING = CaressesOntology.NAMESPACE +"hasRemoteControlSetting";
	public static final String PROPERTY_HAS_CUMULATIVE_OPERATING_TIME = CaressesOntology.NAMESPACE +"hasCumulativeOperatingTime";
	public static final String PROPERTY_HAS_CURRENT_TIME_SETTING= CaressesOntology.NAMESPACE +"hasCurrentTimeSetting";
	public static final String PROPERTY_HAS_CURRENT_DATE_SETTING= CaressesOntology.NAMESPACE +"hasCurrentDateSetting";
	public static final String PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION = CaressesOntology.NAMESPACE +"hasMeasuredInstantaneousPowerConsumption";
	public static final String PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION = CaressesOntology.NAMESPACE +"hasMeasuredCumulativePowerConsumption";
	public static final String PROPERTY_HAS_POWER_LIMIT_SETTING = CaressesOntology.NAMESPACE +"hasPowerLimitSetting";
	@Override
	public int getPropSerializationType(String arg0) {
		// TODO Auto-generated method stub
		return PROP_SERIALIZATION_FULL;
	}
	
	public DeviceSuperClass(String URI) {
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
	
	/*
	//---- setter
	
	public  void setPropertyHasOperationStatus(String msg) {
		changeProperty(PROPERTY_HAS_OPERATION_STATUS, arg1)
	}


	public  void setPropertyHasInstallationLocation(String msg) {
		String installationLocation = (String) getProperty(PROPERTY_HAS_INSTALLATION_LOCATION);
		return (installationLocation == null) ? "" : installationLocation;
	}


	public  void setPropertyStandardVersionInformation() {
		String standardVersionInformation = (String) getProperty(PROPERTY_STANDARD_VERSION_INFORMATION);
		return (standardVersionInformation == null) ? "" : standardVersionInformation;
	}


	public  String setPropertyHasFaultStatus() {
		String faultStatus = (String) getProperty(PROPERTY_HAS_FAULT_STATUS);
		return (faultStatus == null) ? "" : faultStatus;
	}


	public  String setPropertyHasFaultDescription() {
		String faultDescription = (String) getProperty(PROPERTY_HAS_FAULT_DESCRIPTION);
		return (faultDescription == null) ? "" : faultDescription;
	}


	public  String setPropertyHasManufacturerCode() {
		String manufacturerCode = (String) getProperty(PROPERTY_HAS_MANUFACTURER_CODE);
		return (manufacturerCode == null) ? "" : manufacturerCode;
	}


	public  String setPropertyHasBusinessFacilityCode() {
		String businessFacilityCode = (String) getProperty(PROPERTY_HAS_BUSINESS_FACILITY_CODE);
		return (businessFacilityCode == null) ? "" : businessFacilityCode;

	}


	public  String setPropertyHasProductCode() {
		String productCode = (String) getProperty(PROPERTY_HAS_PRODUCT_CODE);
		return (productCode == null) ? "" : productCode;
	}


	public  String setPropertyHasProductionNumber() {
		String productionNumber = (String) getProperty(PROPERTY_HAS_PRODUCTION_NUMBER);
		return (productionNumber == null) ? "" : productionNumber;
	}


	public  String setPropertyHasProductionDate() {
		String productionDate = (String) getProperty(PROPERTY_HAS_PRODUCTION_DATE);
		return (productionDate == null) ? "" : productionDate;

	}


	public  String setPropertyHasIdentificationNumber() {
		String identificationNumber = (String) getProperty(PROPERTY_HAS_IDENTIFICATION_NUMBER);
		return (identificationNumber == null) ? "" : identificationNumber;
	}


	public  String setPropertyHasManufacturerFaultCode() {
		String manufacturerFaultCode = (String) getProperty(PROPERTY_HAS_MANUFACTURER_FAULT_CODE);
		return (manufacturerFaultCode == null) ? "" : manufacturerFaultCode;
	}


	public  String setPropertyHasCurrentLimitSetting() {
		String currentLimitSetting = (String) getProperty(PROPERTY_HAS_CURRENT_LIMIT_SETTING);
		return (currentLimitSetting == null) ? "" : currentLimitSetting;
	}


	public  String setPropertyHasPowerSavingOperationSetting() {
		String powerSavingOperationSetting = (String) getProperty(PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING);
		return (powerSavingOperationSetting == null) ? "" : powerSavingOperationSetting;
	}


	public  String setPropertyHasRemoteControlSetting() {
		String remoteControlSetting = (String) getProperty(PROPERTY_HAS_REMOTE_CONTROL_SETTING);
		return (remoteControlSetting == null) ? "" : remoteControlSetting;
	}


	public  String setPropertyHasCumulativeOperatingTime() {
		String cumulativeOperatingTime = (String) getProperty(PROPERTY_HAS_CUMULATIVE_OPERATING_TIME);
		return (cumulativeOperatingTime == null) ? "" : cumulativeOperatingTime;
	}


	public  String setPropertyHasCurrentTimeSetting() {
		String currentTimeSetting = (String) getProperty(PROPERTY_HAS_CURRENT_TIME_SETTING);
		return (currentTimeSetting == null) ? "" : currentTimeSetting;
	}


	public  String setPropertyHasCurrentDateSetting() {
		String currentDateSetting = (String) getProperty(PROPERTY_HAS_CURRENT_DATE_SETTING);
		return (currentDateSetting == null) ? "" : currentDateSetting;
	}


	public  String setPropertyHasMeasuredInstantaneousPowerConsumption() {
		String measuredInstantaneousPowerConsumption = (String) getProperty(PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION);
		return (measuredInstantaneousPowerConsumption == null) ? "" : measuredInstantaneousPowerConsumption;
	}


	public  String setPropertyHasMeasuredCumulativePowerConsumption() {
		String measuredCumulativePowerConsumption = (String) getProperty(PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION);
		return (measuredCumulativePowerConsumption == null) ? "" : measuredCumulativePowerConsumption;
	}


	public  String setPropertyHasPowerLimitSetting() {
		String powerLimitSetting = (String) getProperty(PROPERTY_HAS_POWER_LIMIT_SETTING);
		return (powerLimitSetting == null) ? "" : powerLimitSetting;
	}
	*/
	

}
