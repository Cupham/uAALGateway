package utils;


import echonet.objects.eAirConditioner;
import echonet.objects.eCurtain;
import echonet.objects.eElectricConsent;
import echonet.objects.eLighting;
import echonet.objects.eTemperatureSensor;
import ontologies.Curtain;
import ontologies.EchonetDevice;
import ontologies.ElectricConsent;
import ontologies.HomeAirConditioner;
import ontologies.Lighting;
import ontologies.TemperatureSensor;


public class OntologyHelper {
	public static void  initTemperatureSensorOntology(eTemperatureSensor dev, TemperatureSensor ont) {
		//Data object
		ont.changeProperty(TemperatureSensor.MY_URI, ont.getURI());
		//System.out.println("URI " +  ont.getProperty(TemperatureSensor.MY_URI));
		ont.setOperationStatus(dev.isOperationStatus());
		//System.out.println("STT " +  ont.getProperty(TemperatureSensor.PROPERTY_HAS_OPERATION_STATUS));
		ont.setMesuredTemperatureValue(dev.getTemperature());
		//System.out.println("VALUE " +  ont.getProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE));

		//Profile object
		ont.setInstanceCode( dev.getInstanceCode());
		ont.setIPAddress(dev.getNode().toString());
		ont.setBusinessFacilityCode(dev.getBusinessFacilityCode());
		ont.setCumulativeOperatingTime(dev.getCumulativeTime());
		ont.setCurrentDateSetting(ont.getCurrentDateSetting());
		ont.setCurrentLimitSetting(ont.getCurrentLimitSetting());
		ont.setFaultDescription(dev.getFaultDescription());
		ont.setFaultStatus(dev.isFaultStatus());
		ont.setIdentificationNumber(dev.getIdentificationNumber());
		ont.setInstallationLocation(dev.getInstallLocation());
		ont.setManufacturerCode(dev.getManufacturerCode());
		ont.setManufacturerFaultCode(dev.getManufacturerFaultCode());
		ont.setMeasuredCumulativePowerConsumption(dev.getCumulativePower());
		ont.setMeasuredInstantaneousPowerConsumption(dev.getInstantaneousPower());
		ont.setPowerLimitSetting(dev.getPowerLimit());
		ont.setPowerSavingOperationSetting(dev.isPowerSaving());
		ont.setProductCode(dev.getProductCode());
		ont.setProductionDate(dev.getProductDate());
		ont.setProductionNumber(dev.getProductNumber());
		ont.setRemoteControlSetting(dev.isThroughPublicNetwork());
	}
	public static void  updateTemperatureOntologyProperty(eTemperatureSensor dev, TemperatureSensor ont,String property) {
		//Data object
		if(property.equals(TemperatureSensor.PROPERTY_HAS_OPERATION_STATUS)) {
			ont.setOperationStatus(dev.isOperationStatus());
			//System.out.println("STT " +  ont.getProperty(TemperatureSensor.PROPERTY_HAS_OPERATION_STATUS));
		}
		
		
		if(property.equals(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE)) {
			ont.setMesuredTemperatureValue(dev.getTemperature());
			//System.out.println("VALUE " +  ont.getProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE));
		}
		
		

		//Profile object
		if(property.equals(EchonetDevice.PROPERTY_HAS_INSTANCE_CODE)) {
			ont.setInstanceCode( dev.getInstanceCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_BUSINESS_FACILITY_CODE)) {
			ont.setBusinessFacilityCode(dev.getBusinessFacilityCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_CUMULATIVE_OPERATING_TIME)) {
			ont.setCumulativeOperatingTime(dev.getCumulativeTime());
		}
		if(property.equals(EchonetDevice.PROPERTY_STANDARD_VERSION_INFORMATION)) {
			ont.setPropertyStandardVersionInformation(dev.getStandardVersionInfo());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_CURRENT_DATE_SETTING)) {
			ont.setCurrentDateSetting(ont.getCurrentDateSetting());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_CURRENT_LIMIT_SETTING)) {
			ont.setCurrentLimitSetting(ont.getCurrentLimitSetting());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_FAULT_DESCRIPTION)) {
			ont.setFaultDescription(dev.getFaultDescription());
		}	
		if(property.equals(EchonetDevice.PROPERTY_HAS_FAULT_STATUS)) {
			ont.setFaultStatus(dev.isFaultStatus());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_IDENTIFICATION_NUMBER)) {
			ont.setIdentificationNumber(dev.getIdentificationNumber());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_INSTALLATION_LOCATION)) {
			ont.setInstallationLocation(dev.getInstallLocation());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_MANUFACTURER_CODE)) {
			ont.setManufacturerCode(dev.getManufacturerCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_MANUFACTURER_FAULT_CODE)) {
			ont.setManufacturerFaultCode(dev.getManufacturerFaultCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION)) {
			ont.setMeasuredCumulativePowerConsumption(dev.getCumulativePower());
		}
		
		if(property.equals(EchonetDevice.PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION)) {
			ont.setMeasuredInstantaneousPowerConsumption(dev.getInstantaneousPower());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_POWER_LIMIT_SETTING)) {
			ont.setPowerLimitSetting(dev.getPowerLimit());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING)) {
			ont.setPowerSavingOperationSetting(dev.isPowerSaving());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_PRODUCT_CODE)) {
			ont.setProductCode(dev.getProductCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_PRODUCTION_DATE)) {
			ont.setProductionDate(dev.getProductDate());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_PRODUCTION_NUMBER)) {
			ont.setProductionNumber(dev.getProductNumber());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_REMOTE_CONTROL_SETTING)) {
			ont.setRemoteControlSetting(dev.isThroughPublicNetwork());
		}
		
		
	}
	public static void  initHomeAirconditionerOntology(eAirConditioner dev, HomeAirConditioner ont) {
		//Data object
		ont.changeProperty(HomeAirConditioner.MY_URI, ont.getURI());
		ont.setOperationStatus(dev.isOperationStatus());
		ont.setOperationPowerSaving(dev.isOperationPowerSaving());
		ont.setOperationModeSetting(dev.getOperationModeSetting());
		ont.setSettingTemperatureValue(dev.getCurrentSettingTemperature());
		ont.setMeasuredRoomTemperatureValue(dev.getRoomTemperature());

		
		//Profile object
		ont.setInstanceCode(dev.getInstanceCode());
		ont.setIPAddress(dev.getNode().toString());
		ont.setBusinessFacilityCode(dev.getBusinessFacilityCode());
		ont.setCumulativeOperatingTime(dev.getCumulativeTime());
		ont.setCurrentDateSetting(ont.getCurrentDateSetting());
		ont.setCurrentLimitSetting(ont.getCurrentLimitSetting());
		ont.setFaultDescription(dev.getFaultDescription());
		ont.setFaultStatus(dev.isFaultStatus());
		ont.setIdentificationNumber(dev.getIdentificationNumber());
		ont.setInstallationLocation(dev.getInstallLocation());
		ont.setManufacturerCode(dev.getManufacturerCode());
		ont.setManufacturerFaultCode(dev.getManufacturerFaultCode());
		ont.setMeasuredCumulativePowerConsumption(dev.getCumulativePower());
		ont.setMeasuredInstantaneousPowerConsumption(dev.getInstantaneousPower());
		ont.setPowerLimitSetting(dev.getPowerLimit());
		ont.setPowerSavingOperationSetting(dev.isPowerSaving());
		ont.setProductCode(dev.getProductCode());
		ont.setProductionDate(dev.getProductDate());
		ont.setProductionNumber(dev.getProductNumber());
		ont.setRemoteControlSetting(dev.isThroughPublicNetwork());
	}
	public static void  updateAirconditionerOntologyProperty(eAirConditioner dev, HomeAirConditioner ont,String property) {
		//Data object
		if(property.equals(HomeAirConditioner.PROPERTY_HAS_OPERATION_STATUS)) {
			ont.setOperationStatus(dev.isOperationStatus());
			//System.out.println("STT " +  ont.getProperty(TemperatureSensor.PROPERTY_HAS_OPERATION_STATUS));
		}	
		if(property.equals(HomeAirConditioner.PROPERTY_HAS_AIR_FLOW_RATE_SETTING)) {
			ont.setAirFlowRateSetting(dev.getAirFlowRate());
			//System.out.println("VALUE " +  ont.getProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE));
		}
		if(property.equals(HomeAirConditioner.PROPERTY_HAS_MEASURED_ROOM_TEMPERATURE_VALUE)) {
			ont.setMeasuredRoomTemperatureValue(dev.getRoomTemperature());
			//System.out.println("VALUE " +  ont.getProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE));
		}
		if(property.equals(HomeAirConditioner.PROPERTY_HAS_OPERATION_MODE_SETTING)) {
			ont.setOperationModeSetting(dev.getOperationModeSetting());
			//System.out.println("VALUE " +  ont.getProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE));
		}
		if(property.equals(HomeAirConditioner.PROPERTY_HAS_OPERATION_POWER_SAVING)) {
			ont.setOperationPowerSaving(dev.isOperationPowerSaving());
			//System.out.println("VALUE " +  ont.getProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE));
		}
		if(property.equals(HomeAirConditioner.PROPERTY_HAS_SETTING_TEMPERATURE_VALUE)) {
			ont.setSettingTemperatureValue(dev.getCurrentSettingTemperature());
			//System.out.println("VALUE " +  ont.getProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE));
		}

		//Profile object
		if(property.equals(EchonetDevice.PROPERTY_HAS_INSTANCE_CODE)) {
			ont.setInstanceCode( dev.getInstanceCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_BUSINESS_FACILITY_CODE)) {
			ont.setBusinessFacilityCode(dev.getBusinessFacilityCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_CUMULATIVE_OPERATING_TIME)) {
			ont.setCumulativeOperatingTime(dev.getCumulativeTime());
		}
		if(property.equals(EchonetDevice.PROPERTY_STANDARD_VERSION_INFORMATION)) {
			ont.setPropertyStandardVersionInformation(dev.getStandardVersionInfo());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_CURRENT_DATE_SETTING)) {
			ont.setCurrentDateSetting(ont.getCurrentDateSetting());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_CURRENT_LIMIT_SETTING)) {
			ont.setCurrentLimitSetting(ont.getCurrentLimitSetting());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_FAULT_DESCRIPTION)) {
			ont.setFaultDescription(dev.getFaultDescription());
		}	
		if(property.equals(EchonetDevice.PROPERTY_HAS_FAULT_STATUS)) {
			ont.setFaultStatus(dev.isFaultStatus());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_IDENTIFICATION_NUMBER)) {
			ont.setIdentificationNumber(dev.getIdentificationNumber());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_INSTALLATION_LOCATION)) {
			ont.setInstallationLocation(dev.getInstallLocation());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_MANUFACTURER_CODE)) {
			ont.setManufacturerCode(dev.getManufacturerCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_MANUFACTURER_FAULT_CODE)) {
			ont.setManufacturerFaultCode(dev.getManufacturerFaultCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION)) {
			ont.setMeasuredCumulativePowerConsumption(dev.getCumulativePower());
		}
		
		if(property.equals(EchonetDevice.PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION)) {
			ont.setMeasuredInstantaneousPowerConsumption(dev.getInstantaneousPower());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_POWER_LIMIT_SETTING)) {
			ont.setPowerLimitSetting(dev.getPowerLimit());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING)) {
			ont.setPowerSavingOperationSetting(dev.isPowerSaving());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_PRODUCT_CODE)) {
			ont.setProductCode(dev.getProductCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_PRODUCTION_DATE)) {
			ont.setProductionDate(dev.getProductDate());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_PRODUCTION_NUMBER)) {
			ont.setProductionNumber(dev.getProductNumber());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_REMOTE_CONTROL_SETTING)) {
			ont.setRemoteControlSetting(dev.isThroughPublicNetwork());
		}
		
	}
	
	public static void  initLightingOntology(eLighting dev, Lighting ont) {
		//Data object
		ont.changeProperty(Lighting.MY_URI, ont.getURI());
		ont.setIlluminationLevel(dev.getIlluminateLevel());
		ont.setOperationStatus(dev.isOperationStatus());

		
		//Profile object
		ont.setInstanceCode(dev.getInstanceCode());
		ont.setIPAddress(dev.getNode().toString());
		ont.setBusinessFacilityCode(dev.getBusinessFacilityCode());
		ont.setCumulativeOperatingTime(dev.getCumulativeTime());
		ont.setCurrentDateSetting(ont.getCurrentDateSetting());
		ont.setCurrentLimitSetting(ont.getCurrentLimitSetting());
		ont.setFaultDescription(dev.getFaultDescription());
		ont.setFaultStatus(dev.isFaultStatus());
		ont.setIdentificationNumber(dev.getIdentificationNumber());
		ont.setInstallationLocation(dev.getInstallLocation());
		ont.setManufacturerCode(dev.getManufacturerCode());
		ont.setManufacturerFaultCode(dev.getManufacturerFaultCode());
		ont.setMeasuredCumulativePowerConsumption(dev.getCumulativePower());
		ont.setMeasuredInstantaneousPowerConsumption(dev.getInstantaneousPower());
		ont.setPowerLimitSetting(dev.getPowerLimit());
		ont.setPowerSavingOperationSetting(dev.isPowerSaving());
		ont.setProductCode(dev.getProductCode());
		ont.setProductionDate(dev.getProductDate());
		ont.setProductionNumber(dev.getProductNumber());
		ont.setRemoteControlSetting(dev.isThroughPublicNetwork());
	}
	public static void  updateLightingOntologyProperty(eLighting dev, Lighting ont,String property) {
		//Data object
		if(property.equals(Lighting.PROPERTY_HAS_OPERATION_STATUS)) {
			ont.setOperationStatus(dev.isOperationStatus());
			//System.out.println("STT " +  ont.getProperty(TemperatureSensor.PROPERTY_HAS_OPERATION_STATUS));
		}	
		if(property.equals(Lighting.PROPERTY_HAS_ILLUMINATION_LEVEL)) {
			ont.setIlluminationLevel(dev.getIlluminateLevel());
			//System.out.println("VALUE " +  ont.getProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE));
		}

		//Profile object
		if(property.equals(EchonetDevice.PROPERTY_HAS_INSTANCE_CODE)) {
			ont.setInstanceCode( dev.getInstanceCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_BUSINESS_FACILITY_CODE)) {
			ont.setBusinessFacilityCode(dev.getBusinessFacilityCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_CUMULATIVE_OPERATING_TIME)) {
			ont.setCumulativeOperatingTime(dev.getCumulativeTime());
		}
		if(property.equals(EchonetDevice.PROPERTY_STANDARD_VERSION_INFORMATION)) {
			ont.setPropertyStandardVersionInformation(dev.getStandardVersionInfo());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_CURRENT_DATE_SETTING)) {
			ont.setCurrentDateSetting(ont.getCurrentDateSetting());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_CURRENT_LIMIT_SETTING)) {
			ont.setCurrentLimitSetting(ont.getCurrentLimitSetting());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_FAULT_DESCRIPTION)) {
			ont.setFaultDescription(dev.getFaultDescription());
		}	
		if(property.equals(EchonetDevice.PROPERTY_HAS_FAULT_STATUS)) {
			ont.setFaultStatus(dev.isFaultStatus());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_IDENTIFICATION_NUMBER)) {
			ont.setIdentificationNumber(dev.getIdentificationNumber());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_INSTALLATION_LOCATION)) {
			ont.setInstallationLocation(dev.getInstallLocation());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_MANUFACTURER_CODE)) {
			ont.setManufacturerCode(dev.getManufacturerCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_MANUFACTURER_FAULT_CODE)) {
			ont.setManufacturerFaultCode(dev.getManufacturerFaultCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION)) {
			ont.setMeasuredCumulativePowerConsumption(dev.getCumulativePower());
		}
		
		if(property.equals(EchonetDevice.PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION)) {
			ont.setMeasuredInstantaneousPowerConsumption(dev.getInstantaneousPower());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_POWER_LIMIT_SETTING)) {
			ont.setPowerLimitSetting(dev.getPowerLimit());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING)) {
			ont.setPowerSavingOperationSetting(dev.isPowerSaving());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_PRODUCT_CODE)) {
			ont.setProductCode(dev.getProductCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_PRODUCTION_DATE)) {
			ont.setProductionDate(dev.getProductDate());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_PRODUCTION_NUMBER)) {
			ont.setProductionNumber(dev.getProductNumber());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_REMOTE_CONTROL_SETTING)) {
			ont.setRemoteControlSetting(dev.isThroughPublicNetwork());
		}
			
	}
	
	public static void  initCurtainOntology(eCurtain dev, Curtain ont) {
		//Data object
		ont.changeProperty(Curtain.MY_URI, ont.getURI());
		ont.setStatus(dev.isStatus());

		
		//Profile object
		ont.setInstanceCode(dev.getInstanceCode());
		ont.setIPAddress(dev.getNode().toString());
		ont.setBusinessFacilityCode(dev.getBusinessFacilityCode());
		ont.setCumulativeOperatingTime(dev.getCumulativeTime());
		ont.setCurrentDateSetting(ont.getCurrentDateSetting());
		ont.setCurrentLimitSetting(ont.getCurrentLimitSetting());
		ont.setFaultDescription(dev.getFaultDescription());
		ont.setFaultStatus(dev.isFaultStatus());
		ont.setIdentificationNumber(dev.getIdentificationNumber());
		ont.setInstallationLocation(dev.getInstallLocation());
		ont.setManufacturerCode(dev.getManufacturerCode());
		ont.setManufacturerFaultCode(dev.getManufacturerFaultCode());
		ont.setMeasuredCumulativePowerConsumption(dev.getCumulativePower());
		ont.setMeasuredInstantaneousPowerConsumption(dev.getInstantaneousPower());
		ont.setPowerLimitSetting(dev.getPowerLimit());
		ont.setPowerSavingOperationSetting(dev.isPowerSaving());
		ont.setProductCode(dev.getProductCode());
		ont.setProductionDate(dev.getProductDate());
		ont.setProductionNumber(dev.getProductNumber());
		ont.setRemoteControlSetting(dev.isThroughPublicNetwork());
	}
	public static void  updateCurtainOntologyProperty(eCurtain dev, Curtain ont,String property) {
		//Data object
		if(property.equals(Curtain.PROPERTY_HAS_STATUS)) {
			ont.setStatus(dev.isStatus());
			//System.out.println("STT " +  ont.getProperty(TemperatureSensor.PROPERTY_HAS_OPERATION_STATUS));
		}	
		//Profile object
		if(property.equals(EchonetDevice.PROPERTY_HAS_INSTANCE_CODE)) {
			ont.setInstanceCode( dev.getInstanceCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_BUSINESS_FACILITY_CODE)) {
			ont.setBusinessFacilityCode(dev.getBusinessFacilityCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_CUMULATIVE_OPERATING_TIME)) {
			ont.setCumulativeOperatingTime(dev.getCumulativeTime());
		}
		if(property.equals(EchonetDevice.PROPERTY_STANDARD_VERSION_INFORMATION)) {
			ont.setPropertyStandardVersionInformation(dev.getStandardVersionInfo());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_CURRENT_DATE_SETTING)) {
			ont.setCurrentDateSetting(ont.getCurrentDateSetting());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_CURRENT_LIMIT_SETTING)) {
			ont.setCurrentLimitSetting(ont.getCurrentLimitSetting());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_FAULT_DESCRIPTION)) {
			ont.setFaultDescription(dev.getFaultDescription());
		}	
		if(property.equals(EchonetDevice.PROPERTY_HAS_FAULT_STATUS)) {
			ont.setFaultStatus(dev.isFaultStatus());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_IDENTIFICATION_NUMBER)) {
			ont.setIdentificationNumber(dev.getIdentificationNumber());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_INSTALLATION_LOCATION)) {
			ont.setInstallationLocation(dev.getInstallLocation());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_MANUFACTURER_CODE)) {
			ont.setManufacturerCode(dev.getManufacturerCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_MANUFACTURER_FAULT_CODE)) {
			ont.setManufacturerFaultCode(dev.getManufacturerFaultCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION)) {
			ont.setMeasuredCumulativePowerConsumption(dev.getCumulativePower());
		}
		
		if(property.equals(EchonetDevice.PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION)) {
			ont.setMeasuredInstantaneousPowerConsumption(dev.getInstantaneousPower());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_POWER_LIMIT_SETTING)) {
			ont.setPowerLimitSetting(dev.getPowerLimit());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING)) {
			ont.setPowerSavingOperationSetting(dev.isPowerSaving());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_PRODUCT_CODE)) {
			ont.setProductCode(dev.getProductCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_PRODUCTION_DATE)) {
			ont.setProductionDate(dev.getProductDate());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_PRODUCTION_NUMBER)) {
			ont.setProductionNumber(dev.getProductNumber());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_REMOTE_CONTROL_SETTING)) {
			ont.setRemoteControlSetting(dev.isThroughPublicNetwork());
		}	
	}
	
	public static void  initConsentntology(eElectricConsent dev, ElectricConsent ont) {
		//Data object
		ont.changeProperty(ElectricConsent.MY_URI, ont.getURI());
		ont.setStatus(dev.isOperationStatus());

		
		//Profile object
		ont.setInstanceCode(dev.getInstanceCode());
		ont.setIPAddress(dev.getNode().toString());
		ont.setBusinessFacilityCode(dev.getBusinessFacilityCode());
		ont.setCumulativeOperatingTime(dev.getCumulativeTime());
		ont.setCurrentDateSetting(ont.getCurrentDateSetting());
		ont.setCurrentLimitSetting(ont.getCurrentLimitSetting());
		ont.setFaultDescription(dev.getFaultDescription());
		ont.setFaultStatus(dev.isFaultStatus());
		ont.setIdentificationNumber(dev.getIdentificationNumber());
		ont.setInstallationLocation(dev.getInstallLocation());
		ont.setManufacturerCode(dev.getManufacturerCode());
		ont.setManufacturerFaultCode(dev.getManufacturerFaultCode());
		ont.setMeasuredCumulativePowerConsumption(dev.getCumulativePower());
		ont.setMeasuredInstantaneousPowerConsumption(dev.getInstantaneousPower());
		ont.setPowerLimitSetting(dev.getPowerLimit());
		ont.setPowerSavingOperationSetting(dev.isPowerSaving());
		ont.setProductCode(dev.getProductCode());
		ont.setProductionDate(dev.getProductDate());
		ont.setProductionNumber(dev.getProductNumber());
		ont.setRemoteControlSetting(dev.isThroughPublicNetwork());
	}
	public static void  updateConsentOntologyProperty(eElectricConsent dev, ElectricConsent ont,String property) {
		//Data object
		if(property.equals(ElectricConsent.PROPERTY_HAS_OPERATION_STATUS)) {
			ont.setStatus(dev.isOperationStatus());
			//System.out.println("STT " +  ont.getProperty(TemperatureSensor.PROPERTY_HAS_OPERATION_STATUS));
		}	
		//Profile object
		if(property.equals(EchonetDevice.PROPERTY_HAS_INSTANCE_CODE)) {
			ont.setInstanceCode( dev.getInstanceCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_BUSINESS_FACILITY_CODE)) {
			ont.setBusinessFacilityCode(dev.getBusinessFacilityCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_CUMULATIVE_OPERATING_TIME)) {
			ont.setCumulativeOperatingTime(dev.getCumulativeTime());
		}
		if(property.equals(EchonetDevice.PROPERTY_STANDARD_VERSION_INFORMATION)) {
			ont.setPropertyStandardVersionInformation(dev.getStandardVersionInfo());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_CURRENT_DATE_SETTING)) {
			ont.setCurrentDateSetting(ont.getCurrentDateSetting());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_CURRENT_LIMIT_SETTING)) {
			ont.setCurrentLimitSetting(ont.getCurrentLimitSetting());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_FAULT_DESCRIPTION)) {
			ont.setFaultDescription(dev.getFaultDescription());
		}	
		if(property.equals(EchonetDevice.PROPERTY_HAS_FAULT_STATUS)) {
			ont.setFaultStatus(dev.isFaultStatus());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_IDENTIFICATION_NUMBER)) {
			ont.setIdentificationNumber(dev.getIdentificationNumber());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_INSTALLATION_LOCATION)) {
			ont.setInstallationLocation(dev.getInstallLocation());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_MANUFACTURER_CODE)) {
			ont.setManufacturerCode(dev.getManufacturerCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_MANUFACTURER_FAULT_CODE)) {
			ont.setManufacturerFaultCode(dev.getManufacturerFaultCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION)) {
			ont.setMeasuredCumulativePowerConsumption(dev.getCumulativePower());
		}
		
		if(property.equals(EchonetDevice.PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION)) {
			ont.setMeasuredInstantaneousPowerConsumption(dev.getInstantaneousPower());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_POWER_LIMIT_SETTING)) {
			ont.setPowerLimitSetting(dev.getPowerLimit());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING)) {
			ont.setPowerSavingOperationSetting(dev.isPowerSaving());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_PRODUCT_CODE)) {
			ont.setProductCode(dev.getProductCode());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_PRODUCTION_DATE)) {
			ont.setProductionDate(dev.getProductDate());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_PRODUCTION_NUMBER)) {
			ont.setProductionNumber(dev.getProductNumber());
		}
		if(property.equals(EchonetDevice.PROPERTY_HAS_REMOTE_CONTROL_SETTING)) {
			ont.setRemoteControlSetting(dev.isThroughPublicNetwork());
		}	
	}

}
