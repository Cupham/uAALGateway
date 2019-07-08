package echonet.Objects;


import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.ontology.echonetontology.airconditionerRelatedDevices.HomeAirConditioner;
import org.universAAL.ontology.echonetontology.values.OccurenceStatusValue;
import org.universAAL.ontology.echonetontology.values.OperationModeSettingValue;
import org.universAAL.ontology.echonetontology.values.OperationStatusValue;
import org.universAAL.ontology.echonetontology.values.RemoteControlSettingValue;

import echowand.common.EOJ;
import echowand.common.EPC;
import echowand.net.Node;
import echowand.net.SubnetException;
import echowand.object.EchonetObjectException;
import echowand.object.ObjectData;
import echowand.service.Service;
import echowand.service.result.GetListener;
import echowand.service.result.GetResult;
import echowand.service.result.ObserveListener;
import echowand.service.result.ObserveResult;
import echowand.service.result.ResultData;
import echowand.service.result.ResultFrame;
import main.Activator;
import utils.EchonetDataConverter;
import utils.SampleConstants;

public class eAirConditioner extends eDataObject{
	private static final Logger LOGGER = Logger.getLogger(eAirConditioner.class.getName());
	HomeAirConditioner airConditioner;
	private String operationModeSetting;
	private boolean automaticTemperatureControlSetting;
	private String typeOfOperation;
	private int currentSettingTemperature;
	private int currentHumidity;
	private int setCoolingTemperature;
	private int setHeatingTemperature;
	private int setDehumidifyingTemperature;
	private int ratedPowerConsumption;
	private int currentPowerConsumption;
	private int roomTemperature;
	private int roomHumidity;
	private int temperatureOfRemoteControl;
	private int measuredCooledAirTemperature;
	private int measuredOutDoorAirTemperature;
	private int relativeTemperatureSetting;
	private String airFlowRate;
	private String airFlowDirectionSetting;
	private String airFlowSwingSetting;
	private String airFllowVerticalDirection;
	private String airFllowHorizontalDirection;
	private String specialState;
	private boolean nonPriorityState;
	private String ventilationFuctionSetting;
	private boolean himidifierFuctionSetting;
	private String ventilationAirFlowRateSetting;
	private String humidificationSetting;
	private String mountedAirCleaningMethod;
	private String airPurifierFunctionSetting;
	private String mountedAirRefreshMethod;
	private String airRefreshFunctionSetting;
	private String mountedSelfCleaningMethod;
	private String specialFunctionSetting;
	private String operationStatusOfComponent;
	private String thermostatSettingOverrideFunction;
	private boolean airPurificationModeSetting;
	private boolean buzzer;
	private String onTimerBasedReservationSetting;
	private Date onTimerSetting;
	private Date onTimerRelativeSetting;
	private String offTimerBasedReservationSetting;
	private Date offTimerSetting;
	private Date offTimerRelativeSetting;
	
	private Timer timer;
	
	//private NodeProfileObject profile;
	public eAirConditioner() {
		this.classCode = (byte)0x01;
		this.groupCode = (byte)0x30;
	}
	public eAirConditioner(byte instanceCode) {
		super();
		this.classCode = (byte)0x01;
		this.groupCode = (byte)0x30;
		this.instanceCode = instanceCode;
	}
	public eAirConditioner( EOJ eoj,Node node) {
		super(node,eoj);
		this.classCode = (byte)0x01;
		this.groupCode = (byte)0x30;
		this.instanceCode = eoj.getInstanceCode();
		
		airConditioner = new HomeAirConditioner(HomeAirConditioner.MY_URI+getDeviceID());
		Activator.cPublisher.publicContextEvent(new ContextEvent(ContextEvent.CONTEXT_EVENT_URI_PREFIX+getDeviceID()));
	}
	// Provided Services
	public boolean setDeviceLocation(String location) {
		boolean rs = false;
		if(location.equals(getInstallLocation())) {
			LOGGER.info(String.format("Airconditioner is already at %s nothing to do", location));
			rs = true;
		} else {
			if(Activator.commandExecutor.executeCommand(this.node,this.eoj,EPC.x81, EchonetDataConverter.installLocationtoDataObj(location))) {
				refreshInstallLocation(location);
				rs= true;
			} else {
				rs = false;
			}
		}
		return rs;
	}
	public boolean setOn() {
		boolean rs = false;
		if(getOperationStatus()) {
			LOGGER.info("Airconditioner is already ON! nothing to do");
			rs = true;
		} else {
			if(Activator.commandExecutor.executeCommand(this.node,this.eoj,EPC.x80, new ObjectData((byte) 0x30))) {
				refreshOperationStatus(true);
				rs= true;
			} else {
				rs = false;
			}
		}
		return rs;
	}
	public boolean setOff() {
		boolean rs = false;
		if(!getOperationStatus()) {
			LOGGER.info("Airconditioner is already OFF! nothing to do");
			rs = true;
		} else {
			if(Activator.commandExecutor.executeCommand(this.node,this.eoj,EPC.x80, new ObjectData((byte) 0x31))) {
				refreshOperationStatus(false);
				rs= true;
			} else {
				rs = false;
			}
		}
		return rs;
	}
	public boolean setOperationMode(String mode) {
		boolean rs = false;
		if(mode.equals(getOperationModeSetting())) {
			LOGGER.info(String.format("Airconditioner is already in %s mode nothing to do", mode));
			rs = true;
		} else {
			if(Activator.commandExecutor.executeCommand(this.node,this.eoj,EPC.xB0, new ObjectData(mode.getBytes()))) {
				refreshOperationModeSetting(mode);
				rs= true;
			} else {
				rs = false;
			}
		}
		return rs;
	}
	public boolean setSettingTemperature(int temperature) {
		boolean rs = false;
		if(getCurrentSettingTemperature() == temperature) {
			LOGGER.info(String.format("Airconditioner temperature is already set to %d mode nothing to do", temperature));
			rs = true;
		} else {
			if(Activator.commandExecutor.executeCommand(this.node,this.eoj,EPC.xB3, new ObjectData(new Integer(temperature).byteValue()))) {
				refreshCurrentSettingTemperature(temperature);
				rs= true;
			} else {
				rs = false;
			}
		}
		return rs;
	}
	
	public boolean turnOnPowerSavingMode() {
		boolean rs = false;
		if(this.getPowerSaving()) {
			LOGGER.info("Airconditioner is already in power saving mode nothing to do");
			rs = true;
		} else {
			if(Activator.commandExecutor.executeCommand(this.node,this.eoj,EPC.x8F, new ObjectData((byte) 0x41))) {
				refreshPowerSaving(true);
				rs= true;
			} else {
				rs = false;
			}
		}
		return rs;
		
	}
	public boolean turnOffPowerSavingMode() {
		boolean rs = false;
		if(!getPowerSaving()) {
			LOGGER.info("Airconditioner is already in normal mode nothing to do");
			rs = true;
		} else {
			if(Activator.commandExecutor.executeCommand(this.node,this.eoj,EPC.x8F, new ObjectData((byte) 0x42))) {
				refreshPowerSaving(false);
				rs= true;
			} else {
				rs = false;
			}
		}
		return rs;
	}
	
	public boolean setAirFlowRate(String airFlowrate) {
		boolean rs = false;
		if(airFlowrate.equals(getAirFlowRate())) {
			LOGGER.info(String.format("Airconditioner flowrate is already in %s nothing to do", airFlowrate));
			rs = true;
		} else {
			if(Activator.commandExecutor.executeCommand(this.node,this.eoj,EPC.xA0, EchonetDataConverter.dataFromAirConditionerFlowRate(airFlowrate.trim()))) {
				refreshAirFlowRate(airFlowrate);
				rs= true;
			} else {
				rs = false;
			}
		}
		return rs;
		
	}
	
				
				
				
	
	// Device Property Monitoring
	
			public void refreshOperationStatus(boolean operationStatus) {
				if(this.getOperationStatus() != operationStatus) {
					this.operationStatus = operationStatus;
					
					if(operationStatus) {
						airConditioner.setOperationStatus(OperationStatusValue.On);
					} else {
						airConditioner.setOperationStatus(OperationStatusValue.Off);
					}
					Activator.cPublisher.publicContextEvent(new ContextEvent(airConditioner,HomeAirConditioner.PROPERTY_HAS_OPERATION_STATUS));
				}
				
			}
			public void refreshInstallLocation(String installLocation) {
				if(!installLocation.equals(this.getInstallLocation())) {
					this.installLocation = installLocation;
					airConditioner.setInstallationLocation(EchonetDataConverter.installationLocationToEnum(installLocation));
					Activator.cPublisher.publicContextEvent(new ContextEvent(airConditioner,HomeAirConditioner.PROPERTY_HAS_INSTALLATION_LOCATION));
				}
			}
			public void refreshStandardVersionInfo(String standardVersionInfo) {
				if(!standardVersionInfo.equals(this.getStandardVersionInfo())) {
					this.standardVersionInfo = standardVersionInfo;
					airConditioner.setStandardVersionInformation(standardVersionInfo);
					Activator.cPublisher.publicContextEvent(new ContextEvent(airConditioner,HomeAirConditioner.PROPERTY_STANDARD_VERSION_INFORMATION));
				}		
			}
			public void refreshIdentificationNumber(String identificationNumber) {
				if(!identificationNumber.equals(this.getIdentificationNumber())) {
					this.identificationNumber = identificationNumber;
					airConditioner.setIdentificationNumber(EchonetDataConverter.stringToIdentificationNumber(identificationNumber));
					Activator.cPublisher.publicContextEvent(new ContextEvent(airConditioner,HomeAirConditioner.PROPERTY_HAS_IDENTIFICATION_NUMBER));
				}
				
			}
			public void refreshInstantaneousPower(short instantaneousPower) {
				if(this.getInstantaneousPower() != instantaneousPower) {
					this.instantaneousPower = instantaneousPower;
					airConditioner.setMeasuredInstantaneousPowerConsumption(new Integer(instantaneousPower));
					Activator.cPublisher.publicContextEvent(new ContextEvent(airConditioner,HomeAirConditioner.PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION));
				}
				
			}
			public void refreshCumulativePower(long cumulativePower) {
				if(this.getCumulativePower() != cumulativePower) {
					this.cumulativePower = cumulativePower;
					airConditioner.setMeasuredCumulativePowerConsumption(new Float(cumulativePower));
					Activator.cPublisher.publicContextEvent(new ContextEvent(airConditioner,HomeAirConditioner.PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION));
				}	
			}
			public void refreshManufactureerFaultCode(String manufactureerFaultCode) {
				if(!manufactureerFaultCode.equals(this.getManufacturerFaultCode())) {
					this.manufacturerFaultCode = manufactureerFaultCode;
					airConditioner.setManufacturerFaultCode(manufactureerFaultCode);
					Activator.cPublisher.publicContextEvent(new ContextEvent(airConditioner,HomeAirConditioner.PROPERTY_HAS_MANUFACTURER_FAULT_CODE));
				}	
			}
			public void refreshCurrentLimitSetting(int currentLimitSetting) {
				if(this.getCurrentLimitSetting()!=currentLimitSetting) {
					this.currentLimitSetting = currentLimitSetting;
					airConditioner.setCurrentLimitSetting(new Integer(currentLimitSetting));
					Activator.cPublisher.publicContextEvent(new ContextEvent(airConditioner,HomeAirConditioner.PROPERTY_HAS_CURRENT_LIMIT_SETTING));
				}	
			}
			public void refreshFaultStatus(boolean faultStatus) {
				if(this.isFaultStatus() != faultStatus) {
					this.faultStatus = faultStatus;
					if(faultStatus) {
						airConditioner.setFaultStatus(OccurenceStatusValue.OccurenceStatusFound);
					} else {
						airConditioner.setFaultStatus(OccurenceStatusValue.OccurenceStatusNotFound);
					}	
					Activator.cPublisher.publicContextEvent(new ContextEvent(airConditioner,HomeAirConditioner.PROPERTY_HAS_FAULT_STATUS));
				}	
			}
			public void refreshFaultDescription(String faultDescription) {
				if(!faultDescription.equals(this.getFaultDescription())) {
					this.faultDescription = faultDescription;
					airConditioner.setFaultDesciptionValue(EchonetDataConverter.stringToFaultDescription(faultDescription));
					Activator.cPublisher.publicContextEvent(new ContextEvent(airConditioner,HomeAirConditioner.PROPERTY_HAS_FAULT_DESCRIPTION));
				}
			}
			public void refreshManufacturerCode(String manufacturerCode) {
				if(!manufacturerCode.equals(this.getManufacturerCode())) {
					this.manufacturerCode = manufacturerCode;
					airConditioner.setManufacturerCode(manufacturerCode);
					Activator.cPublisher.publicContextEvent(new ContextEvent(airConditioner,HomeAirConditioner.PROPERTY_HAS_MANUFACTURER_CODE));
				}		
			}
			public void refreshBusinessFacilityCode(String businessFacilityCode) {
				if(!businessFacilityCode.equals(this.getBusinessFacilityCode())) {
					this.businessFacilityCode = businessFacilityCode;
					airConditioner.setBusinessFacilityCode(businessFacilityCode);
					Activator.cPublisher.publicContextEvent(new ContextEvent(airConditioner,HomeAirConditioner.PROPERTY_HAS_BUSINESS_FACILITY_CODE));
				}	
			}
			public void refreshProductCode(String productCode) {
				if(!productCode.equals(this.getProductCode())) {
					this.productCode = productCode;
					airConditioner.setProductCode(productCode);
					Activator.cPublisher.publicContextEvent(new ContextEvent(airConditioner,HomeAirConditioner.PROPERTY_HAS_PRODUCT_CODE));
				}	
			}
			public void refreshProductNumber(String productNumber) {
				if(!productNumber.equals(this.getProductNumber())) {
					this.productNumber = productNumber;
					airConditioner.setProductionNumber(productNumber);
					Activator.cPublisher.publicContextEvent(new ContextEvent(airConditioner,HomeAirConditioner.PROPERTY_HAS_PRODUCTION_NUMBER));
				}	
			}
			public void refreshProductDate(Date productDate) {
				if(!productDate.equals(this.getProductDate())) {
					this.productDate = productDate;
					airConditioner.setProductionString(productDate.toString());
					Activator.cPublisher.publicContextEvent(new ContextEvent(airConditioner,HomeAirConditioner.PROPERTY_HAS_PRODUCTION_DATE));
				}
				
			}
			public void refreshPowerSaving(boolean powerSaving) {
				if(this.getPowerSaving()!=powerSaving) {
					this.powerSaving = powerSaving;
					if(powerSaving) {
						airConditioner.setPowerSavingOperationSetting(OperationModeSettingValue.PowerSavingMode);
					} else {
						airConditioner.setPowerSavingOperationSetting(OperationModeSettingValue.NormalMode);
					}
					Activator.cPublisher.publicContextEvent(new ContextEvent(airConditioner,HomeAirConditioner.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING));
				}
				
			}
			public void refreshThroughPublicNetwork(boolean throughPublicNetwork) {
				if(this.getThroughPublicNetwork()!=throughPublicNetwork) {
					this.throughPublicNetwork = throughPublicNetwork;
					if(throughPublicNetwork) {
						airConditioner.setRemoteControlSetting(RemoteControlSettingValue.ThroughPublicNetwork);
					} else {
						airConditioner.setRemoteControlSetting(RemoteControlSettingValue.NotThroughPublicNetwork);
					}
					Activator.cPublisher.publicContextEvent(new ContextEvent(airConditioner,HomeAirConditioner.PROPERTY_HAS_REMOTE_CONTROL_SETTING));
				}
				
			}
			public void refreshCurrentTimeSetting(String currentTimeSetting) {
				if(!currentTimeSetting.equals(this.getCurrentTimeSetting())) {
					this.currentTimeSetting = currentTimeSetting;
					airConditioner.setCurrentTimeSetting(currentTimeSetting);
					Activator.cPublisher.publicContextEvent(new ContextEvent(airConditioner,HomeAirConditioner.PROPERTY_HAS_CURRENT_TIME_SETTING));
				}
			}
			public void refreshCurrentDateSetting(Date currentDateSetting) {
				if(!currentDateSetting.equals(this.getCurrentDateSetting())) {
					this.currentDateSetting = currentDateSetting;
					airConditioner.setCurrentStringSetting(currentDateSetting.toString());
					Activator.cPublisher.publicContextEvent(new ContextEvent(airConditioner,HomeAirConditioner.PROPERTY_HAS_CURRENT_DATE_SETTING));
				}	
			}
			public void refreshPowerLimit(int powerLimit) {
				if(this.getPowerLimit()!=powerLimit) {
					this.powerLimit = powerLimit;
					airConditioner.setPowerLimitSetting(new Integer(powerLimit));
					Activator.cPublisher.publicContextEvent(new ContextEvent(airConditioner,HomeAirConditioner.PROPERTY_HAS_POWER_LIMIT_SETTING));
				}
			}
			public void refreshCumulativeTime(String cumulativeTime) {
				if(!cumulativeTime.equals(this.getCumulativeTime())) {
					this.cumulativeTime = cumulativeTime;
					airConditioner.setCumulativeOperatingTime(cumulativeTime);
					Activator.cPublisher.publicContextEvent(new ContextEvent(airConditioner,HomeAirConditioner.PROPERTY_HAS_CUMULATIVE_OPERATING_TIME));
				}
			}	
	
	//TODO: support more attribute
	
	public String getOperationModeSetting() {
		return operationModeSetting;
		
	}
	public void refreshOperationModeSetting(String operationModeSetting) {
		if(!operationModeSetting.equalsIgnoreCase(getOperationModeSetting())) {
			this.operationModeSetting = operationModeSetting;
		}
	}
	public boolean isAutomaticTemperatureControlSetting() {
		return automaticTemperatureControlSetting;
	}
	public void refreshAutomaticTemperatureControlSetting(boolean automaticTemperatureControlSetting) {
		this.automaticTemperatureControlSetting = automaticTemperatureControlSetting;
	}
	public String getTypeOfOperation() {
		return typeOfOperation;
	}
	public void refreshTypeOfOperation(String typeOfOperation) {
		this.typeOfOperation = typeOfOperation;
	}
	public int getCurrentSettingTemperature() {
		return currentSettingTemperature;
	}
	public void refreshCurrentSettingTemperature(int currentSettingTemperature) {
		this.currentSettingTemperature = currentSettingTemperature;
	}
	public int getCurrentHumidity() {
		return currentHumidity;
	}
	public void refreshCurrentHumidity(int currentHumidity) {
		this.currentHumidity = currentHumidity;
	}
	public int getSetCoolingTemperature() {
		return setCoolingTemperature;
	}
	public void refreshSetCoolingTemperature(int setCoolingTemperature) {
		this.setCoolingTemperature = setCoolingTemperature;
	}
	public int getSetHeatingTemperature() {
		return setHeatingTemperature;
	}
	public void refreshSetHeatingTemperature(int setHeatingTemperature) {
		this.setHeatingTemperature = setHeatingTemperature;
	}
	public int getSetDehumidifyingTemperature() {
		return setDehumidifyingTemperature;
	}
	public void refreshSetDehumidifyingTemperature(int setDehumidifyingTemperature) {
		this.setDehumidifyingTemperature = setDehumidifyingTemperature;
	}
	public int getRatedPowerConsumption() {
		return ratedPowerConsumption;
	}
	public void refreshRatedPowerConsumption(int ratedPowerConsumption) {
		this.ratedPowerConsumption = ratedPowerConsumption;
	}
	public int getCurrentPowerConsumption() {
		return currentPowerConsumption;
	}
	public void refreshCurrentPowerConsumption(int currentPowerConsumption) {
		this.currentPowerConsumption = currentPowerConsumption;
	}
	public int getRoomTemperature() {
		return roomTemperature;
	}
	public void refreshRoomTemperature(int roomTemperature) {
		this.roomTemperature = roomTemperature;
	}
	public int getRoomHumidity() {
		return roomHumidity;
	}
	public void refreshRoomHumidity(int roomHumidity) {
		this.roomHumidity = roomHumidity;
	}
	public int getTemperatureOfRemoteControl() {
		return temperatureOfRemoteControl;
	}
	public void refreshTemperatureOfRemoteControl(int temperatureOfRemoteControl) {
		this.temperatureOfRemoteControl = temperatureOfRemoteControl;
	}
	public int getMeasuredCooledAirTemperature() {
		return measuredCooledAirTemperature;
	}
	public void refreshMeasuredCooledAirTemperature(int measuredCooledAirTemperature) {
		this.measuredCooledAirTemperature = measuredCooledAirTemperature;
	}
	public int getMeasuredOutDoorAirTemperature() {
		return measuredOutDoorAirTemperature;
	}
	public void refreshMeasuredOutDoorAirTemperature(int measuredOutDoorAirTemperature) {
		this.measuredOutDoorAirTemperature = measuredOutDoorAirTemperature;
	}
	public int getRelativeTemperatureSetting() {
		return relativeTemperatureSetting;
	}
	public void refreshRelativeTemperatureSetting(int relativeTemperatureSetting) {
		this.relativeTemperatureSetting = relativeTemperatureSetting;
	}
	public String getAirFlowRate() {
		return airFlowRate;
	}
	public void refreshAirFlowRate(String airFlowRate) {
		this.airFlowRate = airFlowRate;
	}
	
	public String getAirFlowDirectionSetting() {
		return airFlowDirectionSetting;
	}
	public void refreshAirFlowDirectionSetting(String airFlowDirectionSetting) {
		this.airFlowDirectionSetting = airFlowDirectionSetting;
	}
	public String getAirFlowSwingSetting() {
		return airFlowSwingSetting;
	}
	public void refreshAirFlowSwingSetting(String airFlowSwingSetting) {
		this.airFlowSwingSetting = airFlowSwingSetting;
	}
	public String getAirFllowVerticalDirection() {
		return airFllowVerticalDirection;
	}
	public void refreshAirFllowVerticalDirection(String airFllowVerticalDirection) {
		this.airFllowVerticalDirection = airFllowVerticalDirection;
	}
	public String getAirFllowHorizontalDirection() {
		return airFllowHorizontalDirection;
	}
	public void refreshAirFllowHorizontalDirection(String airFllowHorizontalDirection) {
		this.airFllowHorizontalDirection = airFllowHorizontalDirection;
	}
	public String getSpecialState() {
		return specialState;
	}
	public void refreshSpecialState(String specialState) {
		this.specialState = specialState;
	}
	public boolean isNonPriorityState() {
		return nonPriorityState;
	}
	public void refreshNonPriorityState(boolean nonPriorityState) {
		this.nonPriorityState = nonPriorityState;
	}
	public String getVentilationFuctionSetting() {
		return ventilationFuctionSetting;
	}
	public void refreshVentilationFuctionSetting(String ventilationFuctionSetting) {
		this.ventilationFuctionSetting = ventilationFuctionSetting;
	}
	public boolean isHimidifierFuctionSetting() {
		return himidifierFuctionSetting;
	}
	public void refreshHimidifierFuctionSetting(boolean himidifierFuctionSetting) {
		this.himidifierFuctionSetting = himidifierFuctionSetting;
	}
	public String getVentilationAirFlowRateSetting() {
		return ventilationAirFlowRateSetting;
	}
	public void refreshVentilationAirFlowRateSetting(String ventilationAirFlowRateSetting) {
		this.ventilationAirFlowRateSetting = ventilationAirFlowRateSetting;
	}
	public String getHumidificationSetting() {
		return humidificationSetting;
	}
	public void refreshHumidificationSetting(String humidificationSetting) {
		this.humidificationSetting = humidificationSetting;
	}
	public String getMountedAirCleaningMethod() {
		return mountedAirCleaningMethod;
	}
	public void refreshMountedAirCleaningMethod(String mountedAirCleaningMethod) {
		this.mountedAirCleaningMethod = mountedAirCleaningMethod;
	}
	public String getAirPurifierFunctionSetting() {
		return airPurifierFunctionSetting;
	}
	public void refreshAirPurifierFunctionSetting(String airPurifierFunctionSetting) {
		this.airPurifierFunctionSetting = airPurifierFunctionSetting;
	}
	public String getMountedAirRefreshMethod() {
		return mountedAirRefreshMethod;
	}
	public void refreshMountedAirRefreshMethod(String mountedAirRefreshMethod) {
		this.mountedAirRefreshMethod = mountedAirRefreshMethod;
	}
	public String getAirRefreshFunctionSetting() {
		return airRefreshFunctionSetting;
	}
	public void refreshAirRefreshFunctionSetting(String airRefreshFunctionSetting) {
		this.airRefreshFunctionSetting = airRefreshFunctionSetting;
	}
	public String getMountedSelfCleaningMethod() {
		return mountedSelfCleaningMethod;
	}
	public void refreshMountedSelfCleaningMethod(String mountedSelfCleaningMethod) {
		this.mountedSelfCleaningMethod = mountedSelfCleaningMethod;
	}
	public String getSpecialFunctionSetting() {
		return specialFunctionSetting;
	}
	public void refreshSpecialFunctionSetting(String specialFunctionSetting) {
		this.specialFunctionSetting = specialFunctionSetting;
	}
	public String getOperationStatusOfComponent() {
		return operationStatusOfComponent;
	}
	public void refreshOperationStatusOfComponent(String operationStatusOfComponent) {
		this.operationStatusOfComponent = operationStatusOfComponent;
	}
	public String getThermostatSettingOverrideFunction() {
		return thermostatSettingOverrideFunction;
	}
	public void refreshThermostatSettingOverrideFunction(String thermostatSettingOverrideFunction) {
		this.thermostatSettingOverrideFunction = thermostatSettingOverrideFunction;
	}
	public boolean isAirPurificationModeSetting() {
		return airPurificationModeSetting;
	}
	public void refreshAirPurificationModeSetting(boolean airPurificationModeSetting) {
		this.airPurificationModeSetting = airPurificationModeSetting;
	}
	public boolean isBuzzer() {
		return buzzer;
	}
	public void refreshBuzzer(boolean buzzer) {
		this.buzzer = buzzer;
	}
	public String getOnTimerBasedReservationSetting() {
		return onTimerBasedReservationSetting;
	}
	public void refreshOnTimerBasedReservationSetting(String onTimerBasedReservationSetting) {
		this.onTimerBasedReservationSetting = onTimerBasedReservationSetting;
	}
	public Date getOnTimerSetting() {
		return onTimerSetting;
	}
	public void refreshOnTimerSetting(Date onTimerSetting) {
		this.onTimerSetting = onTimerSetting;
	}
	public Date getOnTimerRelativeSetting() {
		return onTimerRelativeSetting;
	}
	public void refreshOnTimerRelativeSetting(Date onTimerRelativeSetting) {
		this.onTimerRelativeSetting = onTimerRelativeSetting;
	}
	public String getOffTimerBasedReservationSetting() {
		return offTimerBasedReservationSetting;
	}
	public void refreshOffTimerBasedReservationSetting(String offTimerBasedReservationSetting) {
		this.offTimerBasedReservationSetting = offTimerBasedReservationSetting;
	}
	public Date getOffTimerSetting() {
		return offTimerSetting;
	}
	public void refreshOffTimerSetting(Date offTimerSetting) {
		this.offTimerSetting = offTimerSetting;
	}
	public Date getOffTimerRelativeSetting() {
		return offTimerRelativeSetting;
	}
	public void refreshOffTimerRelativeSetting(Date offTimerRelativeSetting) {
		this.offTimerRelativeSetting = offTimerRelativeSetting;
	}
	
	private void getData(Service service){
		LinkedList<EPC> epcs = new LinkedList<EPC>();
		epcs.addAll(SampleConstants.defaultEPCs);
		epcs.add(EPC.xB0);
		epcs.add(EPC.xB3);
		epcs.add(EPC.xBB);
		epcs.add(EPC.xA0);
		try {
			service.doGet(node, eoj, epcs, 5000, new GetListener() {
				@Override
			    public void receive(GetResult result, ResultFrame resultFrame, ResultData resultData) {
					if (resultData.isEmpty()) {
						return;
					}
					switch (resultData.getEPC()) 
					{
					case x80:
						if(EchonetDataConverter.dataToInteger(resultData) == 48) {
							refreshOperationStatus(true);
						} else {
							refreshOperationStatus(false);
						}
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0x80, EDT: 0x%02X}=={OperationStatus:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getOperationStatus()));

						break;
					case x81:
						String rsLocation = EchonetDataConverter.dataToInstallLocation(resultData);	
						if (rsLocation == null) {
							rsLocation = "The installation location has not been set";
						}
						refreshInstallLocation(rsLocation);		
						
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0x81, EDT: 0x%02X}=={InstallationLocation:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getInstallLocation()));
						break;
					case x82:
						refreshStandardVersionInfo(EchonetDataConverter.dataToVersion(resultData));
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0x82, EDT: 0x%02X}=={Version Information:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getStandardVersionInfo()));
						break;
					case x83:
						refreshIdentificationNumber(EchonetDataConverter.dataToIdentifiCationNumber(resultData));
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0x83, EDT: 0x%02X}=={Identification Number:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getIdentificationNumber()));
						break;
					case x84:
						refreshInstantaneousPower(EchonetDataConverter.dataToShort(resultData));
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0x84, EDT: 0x%02X}=={Instantaneous Power Consumption:%d}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getInstantaneousPower()));
						break;		
					case x85:
						refreshCumulativePower(EchonetDataConverter.dataToLong(resultData));
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0x85, EDT: 0x%02X}=={Cumulative Power Consumption:%d}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getCumulativePower()));
						break;
					case x86:
						refreshManufactureerFaultCode(EchonetDataConverter.dataToFaultCode(resultData));
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0x86, EDT: 0x%02X}=={Manufacturer Fault Code:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getManufacturerFaultCode()));
						break;
					case x87:
						refreshCurrentLimitSetting(EchonetDataConverter.dataToInteger(resultData));
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0x87, EDT: 0x%02X}=={Current Limit Setting:%d}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getCurrentLimitSetting()));
						break;		
					case x88:
						if(EchonetDataConverter.dataToInteger(resultData) == 65) {
							refreshFaultStatus(true);
						} else {
							refreshFaultStatus(false);
						}
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0x88, EDT: 0x%02X}=={Fault Status:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],isFaultStatus()));
						break;		
					case x89:
						if (isFaultStatus()) {
							try {
								refreshFaultDescription(EchonetDataConverter.getFaultDetail(resultData));
							} catch (EchonetObjectException e) {
								e.printStackTrace();
							}
						} else {
							refreshFaultDescription("NO FAULT");
						}
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0x89, EDT: 0x%02X}=={Fault Description:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getFaultDescription()));
						break;
					case x8A:
						refreshManufacturerCode(EchonetDataConverter.dataToString(resultData));
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0x8A, EDT: 0x%02X}=={Manufacturer Code:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getManufacturerCode()));
						break;	
					case x8B:						
						refreshBusinessFacilityCode(EchonetDataConverter.dataToString(resultData));
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0x8B, EDT: 0x%02X}=={Business Facility Code:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getBusinessFacilityCode()));
						break;
					case x8C:
						refreshProductCode(EchonetDataConverter.dataToString(resultData));
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0x8C, EDT: 0x%02X}=={Product Code:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getProductCode()));
						break;
					case x8D:
						refreshProductNumber(EchonetDataConverter.dataToString(resultData));
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0x8D, EDT: 0x%02X}=={Product Number:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getProductNumber()));
						break;		
					case x8E:
						refreshProductDate(EchonetDataConverter.dataToDate(resultData));
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0x8E, EDT: 0x%02X}=={Production Date:%tA}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getProductDate()));
						break;
					case x8F:				
						if(EchonetDataConverter.dataToInteger(resultData) == 65) {
							refreshPowerSaving(true);
						} else {
							refreshPowerSaving(false);
						}
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0x8F, EDT: 0x%02X}=={PowerSaving Mode:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getPowerSaving()));
						break;
					case x93:
						if(EchonetDataConverter.dataToInteger(resultData) == 65) {
							refreshThroughPublicNetwork(false);
						} else {
							refreshThroughPublicNetwork(true);
						}
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0x93, EDT: 0x%02X}=={isThrough Public Network:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getThroughPublicNetwork()));
						break;
					case x97:
						refreshCurrentTimeSetting(EchonetDataConverter.dataToTime(resultData));		
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0x97, EDT: 0x%02X}=={Current Time Setting:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getCurrentTimeSetting()));
						break;
					case x98:
						refreshCurrentDateSetting(EchonetDataConverter.dataToDate(resultData));
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0x98, EDT: 0x%02X}=={Current Date Setting:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getCurrentDateSetting()));
						break;
					case x99:
						refreshPowerLimit(EchonetDataConverter.dataToShort(resultData));
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0x99, EDT: 0x%02X}=={Power Limit:%d}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getPowerLimit()));
						break;
					case x9A:
						refreshCumulativeTime(EchonetDataConverter.dataToCummalativeTime(resultData));
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0x9A, EDT: 0x%02X}=={Up Time:%s}",
								getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getCumulativeTime()));
						break;	
					case xB0:
						refreshOperationModeSetting(EchonetDataConverter.dataToAirConditionerOperationMode(resultData));
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0xB0, EDT: 0x%02X}=={OperationMode:%s}",
								getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getOperationModeSetting()));
						break;
					case xB3:
						refreshCurrentSettingTemperature(EchonetDataConverter.dataToInteger(resultData));
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0xB3, EDT: 0x%02X}=={CurrentSettingTemperature:%d}",
								getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getCurrentSettingTemperature()));
						break;
					case xBB:
						refreshRoomTemperature(EchonetDataConverter.dataToInteger(resultData));
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0xBB, EDT: 0x%02X}=={RoomTemperature:%d}",
								getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getRoomTemperature()));
						break;
					case xA0:
						refreshAirFlowRate(EchonetDataConverter.dataToAirConditionerFlowRate(resultData));
						LOGGER.info(String.format("Node:%s@EOJ:%s {EPC:0xA0, EDT: 0x%02X}=={AirFlowRate:%s}",
								getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getAirFlowRate()));
						break;		
					default:
						break;
					}	
				}
			});
		} catch (SubnetException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void ParseDataFromEOJ(Service service){
		observeData(service);
		timer = new Timer(true);
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				getData(service);
				
			}
		}, SampleConstants.getDelayInterval(), SampleConstants.getRefreshInterval());	
	}
	
	public void observeData(Service service) {
		ArrayList<EPC> epcs = new ArrayList<EPC>();
		epcs.add(EPC.x80);
		epcs.add(EPC.x81);
		epcs.add(EPC.x88);
		try {
			service.doObserve(node, eoj, epcs, new ObserveListener() {
				@Override
			    public void receive(ObserveResult result, ResultFrame resultFrame, ResultData resultData) {
					if (resultData.isEmpty()) {
						return;
					}
					switch (resultData.getEPC()) 
					{
					case x80:
						if(EchonetDataConverter.dataToInteger(resultData) == 48) {
							refreshOperationStatus(true);
						} else {
							refreshOperationStatus(false);
						}
						LOGGER.info(String.format("OBSERVER: Node:%s@EOJ:%s {EPC:0x80, EDT: 0x%02X}=={OperationStatus:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getOperationStatus()));
						break;
					case x81:
						String rsLocation = EchonetDataConverter.dataToInstallLocation(resultData);	
						if (rsLocation == null) {
							rsLocation = "The installation location has not been set";
						}
						refreshInstallLocation(rsLocation);		
						
						LOGGER.info(String.format("OBSERVER: Node:%s@EOJ:%s {EPC:0x81, EDT: 0x%02X}=={InstallationLocation:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getInstallLocation()));
						break;
					case x88:
						if(EchonetDataConverter.dataToInteger(resultData) == 65) {
							refreshFaultStatus(true);
						} else {
							refreshFaultStatus(false);
						}
						LOGGER.info(String.format("OBSERVER: Node:%s@EOJ:%s {EPC:0x88, EDT: 0x%02X}=={Fault Status:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],isFaultStatus()));
						break;
						
					default:
						LOGGER.error("Something happended when observing device!!");
						break;
					}	
				}	
			});
		} catch (SubnetException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public String TouAALReponse() {
		return String.format("%s;%s;%s;%d;%d;%s", getOperationStatus()?"ON":"OFF", getPowerSaving(),
				              getOperationModeSetting(), getCurrentSettingTemperature(), getRoomTemperature(),getAirFlowRate());
	}
	
}
