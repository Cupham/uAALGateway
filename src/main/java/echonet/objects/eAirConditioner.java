package echonet.objects;

import java.util.Date;

import echowand.common.EPC;
import echowand.common.PropertyMap;
import echowand.object.EchonetObjectException;
import echowand.object.ObjectData;
import echowand.object.RemoteObject;
import utils.EchonetDataConverter;

public class eAirConditioner extends eDataObject{
	private boolean operationPowerSaving;
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
	
	private NodeProfileObject profile;
	public eAirConditioner() {
		this.classCode = (byte)0x01;
		this.groupCode = (byte)0x30;
	}
	public eAirConditioner(byte instanceCode) {
		this.classCode = (byte)0x01;
		this.groupCode = (byte)0x30;
		this.instanceCode = instanceCode;
	}
	@Override
	public void ParseDataFromRemoteObject(RemoteObject rObj) throws EchonetObjectException {
		this.instanceCode = rObj.getEOJ().getInstanceCode();
		ObjectData data = rObj.getData(EPC.x9F);
		PropertyMap propertyMap = new PropertyMap(data.toBytes());
		if(propertyMap.isSet(EPC.xB0)) {
			this.setOperationModeSetting(EchonetDataConverter.dataToAirConditionerOperationMode(rObj.getData(EPC.xB0)));
		}
		if(propertyMap.isSet(EPC.xB3)) {
			this.setCurrentSettingTemperature(EchonetDataConverter.dataToInteger(rObj.getData(EPC.xB3)));
		}
		
	}

	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean isOperationPowerSaving() {
		return operationPowerSaving;
	}
	public void setOperationPowerSaving(boolean operationPowerSaving) {
		this.operationPowerSaving = operationPowerSaving;
	}
	public String getOperationModeSetting() {
		return operationModeSetting;
	}
	public void setOperationModeSetting(String operationModeSetting) {
		this.operationModeSetting = operationModeSetting;
	}
	public boolean isAutomaticTemperatureControlSetting() {
		return automaticTemperatureControlSetting;
	}
	public void setAutomaticTemperatureControlSetting(boolean automaticTemperatureControlSetting) {
		this.automaticTemperatureControlSetting = automaticTemperatureControlSetting;
	}
	public String getTypeOfOperation() {
		return typeOfOperation;
	}
	public void setTypeOfOperation(String typeOfOperation) {
		this.typeOfOperation = typeOfOperation;
	}
	public int getCurrentSettingTemperature() {
		return currentSettingTemperature;
	}
	public void setCurrentSettingTemperature(int currentSettingTemperature) {
		this.currentSettingTemperature = currentSettingTemperature;
	}
	public int getCurrentHumidity() {
		return currentHumidity;
	}
	public void setCurrentHumidity(int currentHumidity) {
		this.currentHumidity = currentHumidity;
	}
	public int getSetCoolingTemperature() {
		return setCoolingTemperature;
	}
	public void setSetCoolingTemperature(int setCoolingTemperature) {
		this.setCoolingTemperature = setCoolingTemperature;
	}
	public int getSetHeatingTemperature() {
		return setHeatingTemperature;
	}
	public void setSetHeatingTemperature(int setHeatingTemperature) {
		this.setHeatingTemperature = setHeatingTemperature;
	}
	public int getSetDehumidifyingTemperature() {
		return setDehumidifyingTemperature;
	}
	public void setSetDehumidifyingTemperature(int setDehumidifyingTemperature) {
		this.setDehumidifyingTemperature = setDehumidifyingTemperature;
	}
	public int getRatedPowerConsumption() {
		return ratedPowerConsumption;
	}
	public void setRatedPowerConsumption(int ratedPowerConsumption) {
		this.ratedPowerConsumption = ratedPowerConsumption;
	}
	public int getCurrentPowerConsumption() {
		return currentPowerConsumption;
	}
	public void setCurrentPowerConsumption(int currentPowerConsumption) {
		this.currentPowerConsumption = currentPowerConsumption;
	}
	public int getRoomTemperature() {
		return roomTemperature;
	}
	public void setRoomTemperature(int roomTemperature) {
		this.roomTemperature = roomTemperature;
	}
	public int getRoomHumidity() {
		return roomHumidity;
	}
	public void setRoomHumidity(int roomHumidity) {
		this.roomHumidity = roomHumidity;
	}
	public int getTemperatureOfRemoteControl() {
		return temperatureOfRemoteControl;
	}
	public void setTemperatureOfRemoteControl(int temperatureOfRemoteControl) {
		this.temperatureOfRemoteControl = temperatureOfRemoteControl;
	}
	public int getMeasuredCooledAirTemperature() {
		return measuredCooledAirTemperature;
	}
	public void setMeasuredCooledAirTemperature(int measuredCooledAirTemperature) {
		this.measuredCooledAirTemperature = measuredCooledAirTemperature;
	}
	public int getMeasuredOutDoorAirTemperature() {
		return measuredOutDoorAirTemperature;
	}
	public void setMeasuredOutDoorAirTemperature(int measuredOutDoorAirTemperature) {
		this.measuredOutDoorAirTemperature = measuredOutDoorAirTemperature;
	}
	public int getRelativeTemperatureSetting() {
		return relativeTemperatureSetting;
	}
	public void setRelativeTemperatureSetting(int relativeTemperatureSetting) {
		this.relativeTemperatureSetting = relativeTemperatureSetting;
	}
	public String getAirFlowRate() {
		return airFlowRate;
	}
	public void setAirFlowRate(String airFlowRate) {
		this.airFlowRate = airFlowRate;
	}
	public String getAirFlowDirectionSetting() {
		return airFlowDirectionSetting;
	}
	public void setAirFlowDirectionSetting(String airFlowDirectionSetting) {
		this.airFlowDirectionSetting = airFlowDirectionSetting;
	}
	public String getAirFlowSwingSetting() {
		return airFlowSwingSetting;
	}
	public void setAirFlowSwingSetting(String airFlowSwingSetting) {
		this.airFlowSwingSetting = airFlowSwingSetting;
	}
	public String getAirFllowVerticalDirection() {
		return airFllowVerticalDirection;
	}
	public void setAirFllowVerticalDirection(String airFllowVerticalDirection) {
		this.airFllowVerticalDirection = airFllowVerticalDirection;
	}
	public String getAirFllowHorizontalDirection() {
		return airFllowHorizontalDirection;
	}
	public void setAirFllowHorizontalDirection(String airFllowHorizontalDirection) {
		this.airFllowHorizontalDirection = airFllowHorizontalDirection;
	}
	public String getSpecialState() {
		return specialState;
	}
	public void setSpecialState(String specialState) {
		this.specialState = specialState;
	}
	public boolean isNonPriorityState() {
		return nonPriorityState;
	}
	public void setNonPriorityState(boolean nonPriorityState) {
		this.nonPriorityState = nonPriorityState;
	}
	public String getVentilationFuctionSetting() {
		return ventilationFuctionSetting;
	}
	public void setVentilationFuctionSetting(String ventilationFuctionSetting) {
		this.ventilationFuctionSetting = ventilationFuctionSetting;
	}
	public boolean isHimidifierFuctionSetting() {
		return himidifierFuctionSetting;
	}
	public void setHimidifierFuctionSetting(boolean himidifierFuctionSetting) {
		this.himidifierFuctionSetting = himidifierFuctionSetting;
	}
	public String getVentilationAirFlowRateSetting() {
		return ventilationAirFlowRateSetting;
	}
	public void setVentilationAirFlowRateSetting(String ventilationAirFlowRateSetting) {
		this.ventilationAirFlowRateSetting = ventilationAirFlowRateSetting;
	}
	public String getHumidificationSetting() {
		return humidificationSetting;
	}
	public void setHumidificationSetting(String humidificationSetting) {
		this.humidificationSetting = humidificationSetting;
	}
	public String getMountedAirCleaningMethod() {
		return mountedAirCleaningMethod;
	}
	public void setMountedAirCleaningMethod(String mountedAirCleaningMethod) {
		this.mountedAirCleaningMethod = mountedAirCleaningMethod;
	}
	public String getAirPurifierFunctionSetting() {
		return airPurifierFunctionSetting;
	}
	public void setAirPurifierFunctionSetting(String airPurifierFunctionSetting) {
		this.airPurifierFunctionSetting = airPurifierFunctionSetting;
	}
	public String getMountedAirRefreshMethod() {
		return mountedAirRefreshMethod;
	}
	public void setMountedAirRefreshMethod(String mountedAirRefreshMethod) {
		this.mountedAirRefreshMethod = mountedAirRefreshMethod;
	}
	public String getAirRefreshFunctionSetting() {
		return airRefreshFunctionSetting;
	}
	public void setAirRefreshFunctionSetting(String airRefreshFunctionSetting) {
		this.airRefreshFunctionSetting = airRefreshFunctionSetting;
	}
	public String getMountedSelfCleaningMethod() {
		return mountedSelfCleaningMethod;
	}
	public void setMountedSelfCleaningMethod(String mountedSelfCleaningMethod) {
		this.mountedSelfCleaningMethod = mountedSelfCleaningMethod;
	}
	public String getSpecialFunctionSetting() {
		return specialFunctionSetting;
	}
	public void setSpecialFunctionSetting(String specialFunctionSetting) {
		this.specialFunctionSetting = specialFunctionSetting;
	}
	public String getOperationStatusOfComponent() {
		return operationStatusOfComponent;
	}
	public void setOperationStatusOfComponent(String operationStatusOfComponent) {
		this.operationStatusOfComponent = operationStatusOfComponent;
	}
	public String getThermostatSettingOverrideFunction() {
		return thermostatSettingOverrideFunction;
	}
	public void setThermostatSettingOverrideFunction(String thermostatSettingOverrideFunction) {
		this.thermostatSettingOverrideFunction = thermostatSettingOverrideFunction;
	}
	public boolean isAirPurificationModeSetting() {
		return airPurificationModeSetting;
	}
	public void setAirPurificationModeSetting(boolean airPurificationModeSetting) {
		this.airPurificationModeSetting = airPurificationModeSetting;
	}
	public boolean isBuzzer() {
		return buzzer;
	}
	public void setBuzzer(boolean buzzer) {
		this.buzzer = buzzer;
	}
	public String getOnTimerBasedReservationSetting() {
		return onTimerBasedReservationSetting;
	}
	public void setOnTimerBasedReservationSetting(String onTimerBasedReservationSetting) {
		this.onTimerBasedReservationSetting = onTimerBasedReservationSetting;
	}
	public Date getOnTimerSetting() {
		return onTimerSetting;
	}
	public void setOnTimerSetting(Date onTimerSetting) {
		this.onTimerSetting = onTimerSetting;
	}
	public Date getOnTimerRelativeSetting() {
		return onTimerRelativeSetting;
	}
	public void setOnTimerRelativeSetting(Date onTimerRelativeSetting) {
		this.onTimerRelativeSetting = onTimerRelativeSetting;
	}
	public String getOffTimerBasedReservationSetting() {
		return offTimerBasedReservationSetting;
	}
	public void setOffTimerBasedReservationSetting(String offTimerBasedReservationSetting) {
		this.offTimerBasedReservationSetting = offTimerBasedReservationSetting;
	}
	public Date getOffTimerSetting() {
		return offTimerSetting;
	}
	public void setOffTimerSetting(Date offTimerSetting) {
		this.offTimerSetting = offTimerSetting;
	}
	public Date getOffTimerRelativeSetting() {
		return offTimerRelativeSetting;
	}
	public void setOffTimerRelativeSetting(Date offTimerRelativeSetting) {
		this.offTimerRelativeSetting = offTimerRelativeSetting;
	}
	public NodeProfileObject getProfile() {
		return profile;
	}
	public void setProfile(NodeProfileObject profile) {
		this.profile = profile;
	}
	
	
}
