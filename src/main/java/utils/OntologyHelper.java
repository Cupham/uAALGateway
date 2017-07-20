package utils;

import echonet.objects.eAirConditioner;
import echonet.objects.eTemperatureSensor;
import ontologies.HomeAirConditioner;
import ontologies.TemperatureSensor;


public class OntologyHelper {
	public static void  initTemperatureSensorOntology(eTemperatureSensor dev, TemperatureSensor ont) {
		//Data object
		ont.changeProperty(TemperatureSensor.MY_URI, ont.getURI());
		ont.setOperationStatus(dev.isOperationStatus());
		ont.setMesuredTemperatureValue(dev.getTemperature());

		//Profile object
		ont.setInscanceCode(dev.getInstanceCode());
		ont.setIPAddress(dev.getDeviceIP());
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
	
	public static void  initHomeAirconditionerOntology(eAirConditioner dev, HomeAirConditioner ont) {
		//Data object
		ont.changeProperty(HomeAirConditioner.MY_URI, ont.getURI());
		ont.setOperationStatus(dev.isOperationStatus());
		ont.setOperationPowerSaving(dev.isOperationPowerSaving());
		ont.setOperationModeSetting(dev.getOperationModeSetting());
		ont.setSettingTemperatureValue(dev.getCurrentSettingTemperature());
		ont.setMeasuredRoomTemperatureValue(dev.getRoomTemperature());

		
		//Profile object
		ont.setInscanceCode(dev.getInstanceCode());
		ont.setIPAddress(dev.getDeviceIP());
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
	


}
