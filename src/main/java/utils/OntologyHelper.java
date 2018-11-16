package utils;


import org.apache.log4j.Logger;
import org.universAAL.ontology.device.CurtainController;
import org.universAAL.ontology.echonetontology.EchonetSuperDevice;
import org.universAAL.ontology.echonetontology.airconditionerRelatedDevices.HomeAirConditioner;
import org.universAAL.ontology.echonetontology.housingFacilitiesRelatedDevices.GeneralLighting;
import org.universAAL.ontology.echonetontology.managementOperationRelatedDevices.Switch;
import org.universAAL.ontology.echonetontology.sensorRelatedDevices.TemperatureSensor;
import org.universAAL.ontology.echonetontology.values.DateTimeValue;
import org.universAAL.ontology.echonetontology.values.InstallationLocationValue;
import org.universAAL.ontology.echonetontology.values.MeasuredValue;
import org.universAAL.ontology.echonetontology.values.OperationStatusValue;
import org.universAAL.ontology.echonetontology.values.ValueCategory;

import echonet.Objects.eAirConditioner;
import echonet.Objects.eCurtain;
import echonet.Objects.eElectricConsent;
import echonet.Objects.eLighting;
import echonet.Objects.eTemperatureSensor;
import main.Activator;


public class OntologyHelper {
	private static final Logger LOGGER = Logger.getLogger(OntologyHelper.class.getName());
	public static void  initTemperatureSensorOntology(eTemperatureSensor dev, TemperatureSensor ont) {
		ont.changeProperty(TemperatureSensor.MY_URI, ont.getURI());
		
		if(dev.isOperationStatus()) {
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_OPERATION_STATUS, OperationStatusValue.On);
		} else {
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_OPERATION_STATUS, OperationStatusValue.Off);
		}
		
		// set value
		ont.changeProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE, new Float(dev.getTemperature()));
		///
		
		if("living room".equalsIgnoreCase(dev.getInstallLocation())) {
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_INSTALLATION_LOCATION, InstallationLocationValue.LivingRoom);
		} else {
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_INSTALLATION_LOCATION, InstallationLocationValue.InstallationLocationNotSpecified);
		}

		//Profile object
		ont.changeProperty(TemperatureSensor.PROPERTY_HAS_INSTANCE_CODE, dev.getInstanceCode());
		ont.changeProperty(TemperatureSensor.PROPERTY_HAS_NODE_IP_ADDRESS,dev.getNode().toString());
		
		ont.changeProperty(TemperatureSensor.PROPERTY_HAS_BUSINESS_FACILITY_CODE,dev.getBusinessFacilityCode());
		
		ont.changeProperty(TemperatureSensor.PROPERTY_HAS_CUMULATIVE_OPERATING_TIME,dev.getCumulativeTime());
		ont.changeProperty(TemperatureSensor.PROPERTY_HAS_CURRENT_DATE_SETTING,dev.getCurrentDateSetting());
		ont.changeProperty(TemperatureSensor.PROPERTY_HAS_CURRENT_LIMIT_SETTING,dev.getCurrentLimitSetting());
		ont.changeProperty(TemperatureSensor.PROPERTY_HAS_FAULT_DESCRIPTION,dev.getFaultDescription());
		ont.changeProperty(TemperatureSensor.PROPERTY_HAS_FAULT_STATUS,dev.isFaultStatus());
		ont.changeProperty(TemperatureSensor.PROPERTY_HAS_IDENTIFICATION_NUMBER,dev.getIdentificationNumber());
		//ont.changeProperty(TemperatureSensor.PROPERTY_HAS_INSTALLATION_LOCATION,dev.getInstallLocation());
		ont.changeProperty(TemperatureSensor.PROPERTY_HAS_MANUFACTURER_CODE,dev.getManufacturerCode());
		ont.changeProperty(TemperatureSensor.PROPERTY_HAS_MANUFACTURER_FAULT_CODE,dev.getManufacturerFaultCode());
		ont.changeProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION,dev.getCumulativePower());
		ont.changeProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION,dev.getInstantaneousPower());
		ont.changeProperty(TemperatureSensor.PROPERTY_HAS_POWER_LIMIT_SETTING,dev.getPowerLimit());
		ont.changeProperty(TemperatureSensor.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING,dev.getPowerSaving());
		ont.changeProperty(TemperatureSensor.PROPERTY_HAS_PRODUCT_CODE,dev.getProductCode());
		ont.changeProperty(TemperatureSensor.PROPERTY_HAS_PRODUCTION_DATE,dev.getProductDate());
		ont.changeProperty(TemperatureSensor.PROPERTY_HAS_PRODUCTION_NUMBER,dev.getProductNumber());
		ont.changeProperty(TemperatureSensor.PROPERTY_HAS_REMOTE_CONTROL_SETTING,dev.getThroughPublicNetwork());
		ont.changeProperty(TemperatureSensor.PROPERTY_STANDARD_VERSION_INFORMATION, dev.getStandardVersionInfo());
	}
	public static void  updateTemperatureOntologyProperty(eTemperatureSensor dev, TemperatureSensor ont,String property) {
		//Data object
		long start = System.currentTimeMillis();
		if(property.equals(TemperatureSensor.PROPERTY_HAS_OPERATION_STATUS)) {
			if(dev.isOperationStatus()) {
				ont.changeProperty(TemperatureSensor.PROPERTY_HAS_OPERATION_STATUS, OperationStatusValue.On);
			} else {
				ont.changeProperty(TemperatureSensor.PROPERTY_HAS_OPERATION_STATUS, OperationStatusValue.Off);
			}
		}
		
		
		if(property.equals(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE)) {
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE, new Float(dev.getTemperature()));
		}
		
		

		//Profile object
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_INSTANCE_CODE)) {
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_INSTANCE_CODE, dev.getInstanceCode());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_BUSINESS_FACILITY_CODE)) {
			
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_BUSINESS_FACILITY_CODE,dev.getBusinessFacilityCode());		
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_CUMULATIVE_OPERATING_TIME)) {
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_CUMULATIVE_OPERATING_TIME,dev.getCumulativeTime());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_STANDARD_VERSION_INFORMATION)) {
			ont.changeProperty(TemperatureSensor.PROPERTY_STANDARD_VERSION_INFORMATION, dev.getStandardVersionInfo());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_CURRENT_DATE_SETTING)) {
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_CURRENT_DATE_SETTING,dev.getCurrentDateSetting());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_CURRENT_LIMIT_SETTING)) {
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_CURRENT_LIMIT_SETTING,dev.getCurrentLimitSetting());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_FAULT_DESCRIPTION)) {
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_FAULT_DESCRIPTION,dev.getFaultDescription());
		}	
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_FAULT_STATUS)) {
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_FAULT_STATUS,dev.isFaultStatus());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_IDENTIFICATION_NUMBER)) {
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_IDENTIFICATION_NUMBER,dev.getIdentificationNumber());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_INSTALLATION_LOCATION)) {
			if("living room".equalsIgnoreCase(dev.getInstallLocation())) {
				ont.changeProperty(TemperatureSensor.PROPERTY_HAS_INSTALLATION_LOCATION, InstallationLocationValue.LivingRoom);
			} else {
				ont.changeProperty(TemperatureSensor.PROPERTY_HAS_INSTALLATION_LOCATION, InstallationLocationValue.InstallationLocationNotSpecified);
			}
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_MANUFACTURER_CODE)) {
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_MANUFACTURER_CODE,dev.getManufacturerCode());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_MANUFACTURER_FAULT_CODE)) {
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_MANUFACTURER_FAULT_CODE,dev.getManufacturerFaultCode());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION)) {
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION,dev.getCumulativePower());	
		}
		
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION)) {
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION,dev.getInstantaneousPower());
			
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_POWER_LIMIT_SETTING)) {
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_POWER_LIMIT_SETTING,dev.getPowerLimit());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING)) {
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING,dev.getPowerSaving());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_PRODUCT_CODE)) {
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_PRODUCT_CODE,dev.getProductCode());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_PRODUCTION_DATE)) {
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_PRODUCTION_DATE,dev.getProductDate());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_PRODUCTION_NUMBER)) {
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_PRODUCTION_NUMBER,dev.getProductNumber());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_REMOTE_CONTROL_SETTING)) {
			ont.changeProperty(TemperatureSensor.PROPERTY_HAS_REMOTE_CONTROL_SETTING,dev.getThroughPublicNetwork());
		}
		long time = System.currentTimeMillis() -start;
		LOGGER.info("Update TEMP_TIME " + time);
		
	}
	public static void  initHomeAirconditionerOntology(eAirConditioner dev, HomeAirConditioner ont) {
		long start = System.currentTimeMillis();
		//Data object
		ont.changeProperty(HomeAirConditioner.MY_URI, ont.getURI());
		

		if(dev.isOperationStatus()) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_OPERATION_STATUS, OperationStatusValue.On);
		} else {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_OPERATION_STATUS, OperationStatusValue.Off);
		}
		
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_AIR_FLOW_RATE_SETTING, dev.getAirFlowRate());
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_OPERATION_POWER_SAVING_MODE, dev.isOperationPowerSaving());
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_OPERATION_MODE_SETTING, dev.getOperationModeSetting());
		
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_CURRENT_TEMPERATER_SETTING_OF_REMOTE_CONTROL, dev.getCurrentSettingTemperature());
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_MEASURED_ROOM_TEMPERATURE, dev.getRoomTemperature());

		
		//Profile object
		//Profile object
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_INSTANCE_CODE, dev.getInstanceCode());
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_NODE_IP_ADDRESS,dev.getNode().toString());
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_BUSINESS_FACILITY_CODE,dev.getBusinessFacilityCode());
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_CUMULATIVE_OPERATING_TIME,dev.getCumulativeTime());
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_CURRENT_DATE_SETTING,dev.getCurrentDateSetting());
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_CURRENT_LIMIT_SETTING,dev.getCurrentLimitSetting());
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_FAULT_DESCRIPTION,dev.getFaultDescription());
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_FAULT_STATUS,dev.isFaultStatus());
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_IDENTIFICATION_NUMBER,dev.getIdentificationNumber());
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_INSTALLATION_LOCATION,dev.getInstallLocation());

			if("living room".equalsIgnoreCase(dev.getInstallLocation())) {
				ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_INSTALLATION_LOCATION, InstallationLocationValue.LivingRoom);
			} else {
				ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_INSTALLATION_LOCATION, InstallationLocationValue.InstallationLocationNotSpecified);
			}

		
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_MANUFACTURER_CODE,dev.getManufacturerCode());
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_MANUFACTURER_FAULT_CODE,dev.getManufacturerFaultCode());
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION,dev.getCumulativePower());
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION,dev.getInstantaneousPower());
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_POWER_LIMIT_SETTING,dev.getPowerLimit());
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING,dev.getPowerSaving());
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_PRODUCT_CODE,dev.getProductCode());
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_PRODUCTION_DATE,dev.getProductDate());
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_PRODUCTION_NUMBER,dev.getProductNumber());
		ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_REMOTE_CONTROL_SETTING,dev.getThroughPublicNetwork());
		ont.changeProperty(HomeAirConditioner.PROPERTY_STANDARD_VERSION_INFORMATION, dev.getStandardVersionInfo());
		long time = System.currentTimeMillis() -start;
		LOGGER.info("Init AIR_TIME " + time);
	}
	public static void  updateAirconditionerOntologyProperty(eAirConditioner dev, HomeAirConditioner ont,String property) {
		long start = System.currentTimeMillis();
		//Data object
		if(property.equals(HomeAirConditioner.PROPERTY_HAS_OPERATION_STATUS)) {
			if(dev.isOperationStatus()) {
				ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_OPERATION_STATUS, OperationStatusValue.On);
			} else {
				ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_OPERATION_STATUS, OperationStatusValue.Off);
			}
		}	
		if(property.equals(HomeAirConditioner.PROPERTY_HAS_AIR_FLOW_RATE_SETTING)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_AIR_FLOW_RATE_SETTING, dev.getAirFlowRate());
			//System.out.println("VALUE " +  ont.getProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE));
		}
		if(property.equals(HomeAirConditioner.PROPERTY_HAS_MEASURED_ROOM_TEMPERATURE)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_MEASURED_ROOM_TEMPERATURE, dev.getRoomTemperature());
			//System.out.println("VALUE " +  ont.getProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE));
		}
		if(property.equals(HomeAirConditioner.PROPERTY_HAS_OPERATION_MODE_SETTING)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_OPERATION_MODE_SETTING, dev.getOperationModeSetting());
			//System.out.println("VALUE " +  ont.getProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE));
		}
		if(property.equals(HomeAirConditioner.PROPERTY_HAS_OPERATION_POWER_SAVING_MODE)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_OPERATION_POWER_SAVING_MODE, dev.isOperationPowerSaving());
			//System.out.println("VALUE " +  ont.getProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE));
		}
		if(property.equals(HomeAirConditioner.PROPERTY_HAS_CURRENT_TEMPERATER_SETTING_OF_REMOTE_CONTROL)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_CURRENT_TEMPERATER_SETTING_OF_REMOTE_CONTROL, dev.getCurrentSettingTemperature());
			//System.out.println("VALUE " +  ont.getProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE));
		}

		//Profile object
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_INSTANCE_CODE)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_INSTANCE_CODE, dev.getInstanceCode());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_BUSINESS_FACILITY_CODE)) {
			
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_BUSINESS_FACILITY_CODE,dev.getBusinessFacilityCode());		
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_CUMULATIVE_OPERATING_TIME)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_CUMULATIVE_OPERATING_TIME,dev.getCumulativeTime());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_STANDARD_VERSION_INFORMATION)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_STANDARD_VERSION_INFORMATION, dev.getStandardVersionInfo());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_CURRENT_DATE_SETTING)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_CURRENT_DATE_SETTING,dev.getCurrentDateSetting());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_CURRENT_LIMIT_SETTING)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_CURRENT_LIMIT_SETTING,dev.getCurrentLimitSetting());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_FAULT_DESCRIPTION)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_FAULT_DESCRIPTION,dev.getFaultDescription());
		}	
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_FAULT_STATUS)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_FAULT_STATUS,dev.isFaultStatus());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_IDENTIFICATION_NUMBER)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_IDENTIFICATION_NUMBER,dev.getIdentificationNumber());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_INSTALLATION_LOCATION)) {
			if("living room".equalsIgnoreCase(dev.getInstallLocation())) {
				ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_INSTALLATION_LOCATION, InstallationLocationValue.LivingRoom);
			} else {
				ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_INSTALLATION_LOCATION, InstallationLocationValue.InstallationLocationNotSpecified);
			}
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_MANUFACTURER_CODE)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_MANUFACTURER_CODE,dev.getManufacturerCode());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_MANUFACTURER_FAULT_CODE)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_MANUFACTURER_FAULT_CODE,dev.getManufacturerFaultCode());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION,dev.getCumulativePower());	
		}
		
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION,dev.getInstantaneousPower());
			
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_POWER_LIMIT_SETTING)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_POWER_LIMIT_SETTING,dev.getPowerLimit());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING,dev.getPowerSaving());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_PRODUCT_CODE)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_PRODUCT_CODE,dev.getProductCode());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_PRODUCTION_DATE)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_PRODUCTION_DATE,dev.getProductDate());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_PRODUCTION_NUMBER)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_PRODUCTION_NUMBER,dev.getProductNumber());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_REMOTE_CONTROL_SETTING)) {
			ont.changeProperty(HomeAirConditioner.PROPERTY_HAS_REMOTE_CONTROL_SETTING,dev.getThroughPublicNetwork());
		}
		long time = System.currentTimeMillis() -start;
		LOGGER.info("Update AIR_TIME " + time);
		
	}
	
	public static void  initLightingOntology(eLighting dev, GeneralLighting ont) {
		//Data object
		ont.changeProperty(GeneralLighting.MY_URI, ont.getURI());
		ont.changeProperty(GeneralLighting.PROPERTY_HAS_ILLUMINANCE_LEVEL,dev.getIlluminateLevel());	
		if(dev.isOperationStatus()) {
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_OPERATION_STATUS, OperationStatusValue.On);
		} else {
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_OPERATION_STATUS, OperationStatusValue.Off);
		}

		
		//Profile object
		ont.changeProperty(GeneralLighting.PROPERTY_HAS_INSTANCE_CODE, dev.getInstanceCode());
		ont.changeProperty(GeneralLighting.PROPERTY_HAS_NODE_IP_ADDRESS,dev.getNode().toString());
		ont.changeProperty(GeneralLighting.PROPERTY_HAS_BUSINESS_FACILITY_CODE,dev.getBusinessFacilityCode());
		ont.changeProperty(GeneralLighting.PROPERTY_HAS_CUMULATIVE_OPERATING_TIME,dev.getCumulativeTime());
		ont.changeProperty(GeneralLighting.PROPERTY_HAS_CURRENT_DATE_SETTING,dev.getCurrentDateSetting());
		ont.changeProperty(GeneralLighting.PROPERTY_HAS_CURRENT_LIMIT_SETTING,dev.getCurrentLimitSetting());
		ont.changeProperty(GeneralLighting.PROPERTY_HAS_FAULT_DESCRIPTION,dev.getFaultDescription());
		ont.changeProperty(GeneralLighting.PROPERTY_HAS_FAULT_STATUS,dev.isFaultStatus());
		ont.changeProperty(GeneralLighting.PROPERTY_HAS_IDENTIFICATION_NUMBER,dev.getIdentificationNumber());
		if("living room".equalsIgnoreCase(dev.getInstallLocation())) {
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_INSTALLATION_LOCATION, InstallationLocationValue.LivingRoom);
		} else {
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_INSTALLATION_LOCATION, InstallationLocationValue.InstallationLocationNotSpecified);
		}
		ont.changeProperty(GeneralLighting.PROPERTY_HAS_MANUFACTURER_CODE,dev.getManufacturerCode());
		ont.changeProperty(GeneralLighting.PROPERTY_HAS_MANUFACTURER_FAULT_CODE,dev.getManufacturerFaultCode());
		ont.changeProperty(GeneralLighting.PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION,dev.getCumulativePower());
		ont.changeProperty(GeneralLighting.PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION,dev.getInstantaneousPower());
		ont.changeProperty(GeneralLighting.PROPERTY_HAS_POWER_LIMIT_SETTING,dev.getPowerLimit());
		ont.changeProperty(GeneralLighting.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING,dev.getPowerSaving());
		ont.changeProperty(GeneralLighting.PROPERTY_HAS_PRODUCT_CODE,dev.getProductCode());
		ont.changeProperty(GeneralLighting.PROPERTY_HAS_PRODUCTION_DATE,dev.getProductDate());
		ont.changeProperty(GeneralLighting.PROPERTY_HAS_PRODUCTION_NUMBER,dev.getProductNumber());
		ont.changeProperty(GeneralLighting.PROPERTY_HAS_REMOTE_CONTROL_SETTING,dev.getThroughPublicNetwork());
		ont.changeProperty(GeneralLighting.PROPERTY_STANDARD_VERSION_INFORMATION, dev.getStandardVersionInfo());
	}
	public static void  updateLightingOntologyProperty(eLighting dev, GeneralLighting ont,String property) {
		//Data object
		if(property.equals(GeneralLighting.PROPERTY_HAS_OPERATION_STATUS)) {
			if(dev.isOperationStatus()) {
				ont.changeProperty(GeneralLighting.PROPERTY_HAS_OPERATION_STATUS, OperationStatusValue.On);
			} else {
				ont.changeProperty(GeneralLighting.PROPERTY_HAS_OPERATION_STATUS, OperationStatusValue.Off);
			}
		}	
		if(property.equals(GeneralLighting.PROPERTY_HAS_ILLUMINANCE_LEVEL)) {
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_ILLUMINANCE_LEVEL,dev.getIlluminateLevel());
			//System.out.println("VALUE " +  ont.getProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE));
		}

		//Profile object
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_INSTANCE_CODE)) {
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_INSTANCE_CODE, dev.getInstanceCode());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_BUSINESS_FACILITY_CODE)) {
			
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_BUSINESS_FACILITY_CODE,dev.getBusinessFacilityCode());		
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_CUMULATIVE_OPERATING_TIME)) {
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_CUMULATIVE_OPERATING_TIME,dev.getCumulativeTime());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_STANDARD_VERSION_INFORMATION)) {
			ont.changeProperty(GeneralLighting.PROPERTY_STANDARD_VERSION_INFORMATION, dev.getStandardVersionInfo());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_CURRENT_DATE_SETTING)) {
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_CURRENT_DATE_SETTING,dev.getCurrentDateSetting());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_CURRENT_LIMIT_SETTING)) {
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_CURRENT_LIMIT_SETTING,dev.getCurrentLimitSetting());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_FAULT_DESCRIPTION)) {
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_FAULT_DESCRIPTION,dev.getFaultDescription());
		}	
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_FAULT_STATUS)) {
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_FAULT_STATUS,dev.isFaultStatus());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_IDENTIFICATION_NUMBER)) {
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_IDENTIFICATION_NUMBER,dev.getIdentificationNumber());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_INSTALLATION_LOCATION)) {
			if(dev.getInstallLocation().equalsIgnoreCase("living room")) {
				ont.changeProperty(GeneralLighting.PROPERTY_HAS_INSTALLATION_LOCATION, InstallationLocationValue.LivingRoom);
			} else {
				ont.changeProperty(GeneralLighting.PROPERTY_HAS_INSTALLATION_LOCATION, InstallationLocationValue.InstallationLocationNotSpecified);
			}
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_MANUFACTURER_CODE)) {
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_MANUFACTURER_CODE,dev.getManufacturerCode());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_MANUFACTURER_FAULT_CODE)) {
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_MANUFACTURER_FAULT_CODE,dev.getManufacturerFaultCode());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION)) {
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION,dev.getCumulativePower());	
		}
		
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION)) {
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION,dev.getInstantaneousPower());
			
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_POWER_LIMIT_SETTING)) {
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_POWER_LIMIT_SETTING,dev.getPowerLimit());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING)) {
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING,dev.getPowerSaving());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_PRODUCT_CODE)) {
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_PRODUCT_CODE,dev.getProductCode());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_PRODUCTION_DATE)) {
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_PRODUCTION_DATE,dev.getProductDate());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_PRODUCTION_NUMBER)) {
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_PRODUCTION_NUMBER,dev.getProductNumber());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_REMOTE_CONTROL_SETTING)) {
			ont.changeProperty(GeneralLighting.PROPERTY_HAS_REMOTE_CONTROL_SETTING,dev.getThroughPublicNetwork());
		}
			
	}
	
	public static void  initCurtainOntology(eCurtain dev, CurtainController ont) {
		//Data object
		/*
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
		ont.setPowerSavingOperationSetting(dev.getPowerSaving());
		ont.setProductCode(dev.getProductCode());
		ont.setProductionDate(dev.getProductDate());
		ont.setProductionNumber(dev.getProductNumber());
		ont.setRemoteControlSetting(dev.getThroughPublicNetwork());
		*/
	}
	public static void  updateCurtainOntologyProperty(eCurtain dev, CurtainController ont,String property) {
		/*
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
			ont.setPowerSavingOperationSetting(dev.getPowerSaving());
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
			ont.setRemoteControlSetting(dev.getThroughPublicNetwork());
		}	
		*/
	}
	
	public static void  initConsentntology(eElectricConsent dev, Switch ont) {
		//Data object
		ont.changeProperty(Switch.MY_URI, ont.getURI());
		if(dev.isOperationStatus()) {
			ont.changeProperty(Switch.PROPERTY_HAS_OPERATION_STATUS, OperationStatusValue.On);
		} else {
			ont.changeProperty(Switch.PROPERTY_HAS_OPERATION_STATUS, OperationStatusValue.Off);
		}

		
		//Profile object
		ont.changeProperty(Switch.PROPERTY_HAS_INSTANCE_CODE, dev.getInstanceCode());
		ont.changeProperty(Switch.PROPERTY_HAS_NODE_IP_ADDRESS,dev.getNode().toString());
		ont.changeProperty(Switch.PROPERTY_HAS_BUSINESS_FACILITY_CODE,dev.getBusinessFacilityCode());
		ont.changeProperty(Switch.PROPERTY_HAS_CUMULATIVE_OPERATING_TIME,dev.getCumulativeTime());
		ont.changeProperty(Switch.PROPERTY_HAS_CURRENT_DATE_SETTING,dev.getCurrentDateSetting());
		ont.changeProperty(Switch.PROPERTY_HAS_CURRENT_LIMIT_SETTING,dev.getCurrentLimitSetting());
		ont.changeProperty(Switch.PROPERTY_HAS_FAULT_DESCRIPTION,dev.getFaultDescription());
		ont.changeProperty(Switch.PROPERTY_HAS_FAULT_STATUS,dev.isFaultStatus());
		ont.changeProperty(Switch.PROPERTY_HAS_IDENTIFICATION_NUMBER,dev.getIdentificationNumber());
		if("living room".equalsIgnoreCase(dev.getInstallLocation())) {
			ont.changeProperty(Switch.PROPERTY_HAS_INSTALLATION_LOCATION, InstallationLocationValue.LivingRoom);
		} else {
			ont.changeProperty(Switch.PROPERTY_HAS_INSTALLATION_LOCATION, InstallationLocationValue.InstallationLocationNotSpecified);
		}
		ont.changeProperty(Switch.PROPERTY_HAS_MANUFACTURER_CODE,dev.getManufacturerCode());
		ont.changeProperty(Switch.PROPERTY_HAS_MANUFACTURER_FAULT_CODE,dev.getManufacturerFaultCode());
		ont.changeProperty(Switch.PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION,dev.getCumulativePower());
		ont.changeProperty(Switch.PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION,dev.getInstantaneousPower());
		ont.changeProperty(Switch.PROPERTY_HAS_POWER_LIMIT_SETTING,dev.getPowerLimit());
		ont.changeProperty(Switch.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING,dev.getPowerSaving());
		ont.changeProperty(Switch.PROPERTY_HAS_PRODUCT_CODE,dev.getProductCode());
		ont.changeProperty(Switch.PROPERTY_HAS_PRODUCTION_DATE,dev.getProductDate());
		ont.changeProperty(Switch.PROPERTY_HAS_PRODUCTION_NUMBER,dev.getProductNumber());
		ont.changeProperty(Switch.PROPERTY_HAS_REMOTE_CONTROL_SETTING,dev.getThroughPublicNetwork());
		ont.changeProperty(Switch.PROPERTY_STANDARD_VERSION_INFORMATION, dev.getStandardVersionInfo());
	}
	public static void  updateConsentOntologyProperty(eElectricConsent dev, Switch ont,String property) {
		//Data object
		if(property.equals(Switch.PROPERTY_HAS_OPERATION_STATUS)) {
			ont.changeProperty(Switch.PROPERTY_HAS_OPERATION_STATUS,dev.isOperationStatus());
			//System.out.println("STT " +  ont.getProperty(TemperatureSensor.PROPERTY_HAS_OPERATION_STATUS));
		}	
		//Profile object
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_INSTANCE_CODE)) {
			ont.changeProperty(Switch.PROPERTY_HAS_INSTANCE_CODE, dev.getInstanceCode());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_BUSINESS_FACILITY_CODE)) {
			
			ont.changeProperty(Switch.PROPERTY_HAS_BUSINESS_FACILITY_CODE,dev.getBusinessFacilityCode());		
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_CUMULATIVE_OPERATING_TIME)) {
			ont.changeProperty(Switch.PROPERTY_HAS_CUMULATIVE_OPERATING_TIME,dev.getCumulativeTime());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_STANDARD_VERSION_INFORMATION)) {
			ont.changeProperty(Switch.PROPERTY_STANDARD_VERSION_INFORMATION, dev.getStandardVersionInfo());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_CURRENT_DATE_SETTING)) {
			ont.changeProperty(Switch.PROPERTY_HAS_CURRENT_DATE_SETTING,dev.getCurrentDateSetting());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_CURRENT_LIMIT_SETTING)) {
			ont.changeProperty(Switch.PROPERTY_HAS_CURRENT_LIMIT_SETTING,dev.getCurrentLimitSetting());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_FAULT_DESCRIPTION)) {
			ont.changeProperty(Switch.PROPERTY_HAS_FAULT_DESCRIPTION,dev.getFaultDescription());
		}	
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_FAULT_STATUS)) {
			ont.changeProperty(Switch.PROPERTY_HAS_FAULT_STATUS,dev.isFaultStatus());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_IDENTIFICATION_NUMBER)) {
			ont.changeProperty(Switch.PROPERTY_HAS_IDENTIFICATION_NUMBER,dev.getIdentificationNumber());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_INSTALLATION_LOCATION)) {
			if("living room".equalsIgnoreCase(dev.getInstallLocation())) {
				ont.changeProperty(Switch.PROPERTY_HAS_INSTALLATION_LOCATION, InstallationLocationValue.LivingRoom);
			} else {
				ont.changeProperty(Switch.PROPERTY_HAS_INSTALLATION_LOCATION, InstallationLocationValue.InstallationLocationNotSpecified);
			}
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_MANUFACTURER_CODE)) {
			ont.changeProperty(Switch.PROPERTY_HAS_MANUFACTURER_CODE,dev.getManufacturerCode());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_MANUFACTURER_FAULT_CODE)) {
			ont.changeProperty(Switch.PROPERTY_HAS_MANUFACTURER_FAULT_CODE,dev.getManufacturerFaultCode());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION)) {
			ont.changeProperty(Switch.PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION,dev.getCumulativePower());	
		}
		
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION)) {
			ont.changeProperty(Switch.PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION,dev.getInstantaneousPower());
			
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_POWER_LIMIT_SETTING)) {
			ont.changeProperty(Switch.PROPERTY_HAS_POWER_LIMIT_SETTING,dev.getPowerLimit());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING)) {
			ont.changeProperty(Switch.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING,dev.getPowerSaving());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_PRODUCT_CODE)) {
			ont.changeProperty(Switch.PROPERTY_HAS_PRODUCT_CODE,dev.getProductCode());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_PRODUCTION_DATE)) {
			ont.changeProperty(Switch.PROPERTY_HAS_PRODUCTION_DATE,dev.getProductDate());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_PRODUCTION_NUMBER)) {
			ont.changeProperty(Switch.PROPERTY_HAS_PRODUCTION_NUMBER,dev.getProductNumber());
		}
		if(property.equals(EchonetSuperDevice.PROPERTY_HAS_REMOTE_CONTROL_SETTING)) {
			ont.changeProperty(Switch.PROPERTY_HAS_REMOTE_CONTROL_SETTING,dev.getThroughPublicNetwork());
		}	
	}

}
