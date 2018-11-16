package echonet.Objects;


import java.util.Date;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.universAAL.ontology.echonetontology.airconditionerRelatedDevices.HomeAirConditioner;

import echowand.common.EOJ;
import echowand.common.EPC;
import echowand.net.Node;
import echowand.net.SubnetException;
import echowand.object.EchonetObjectException;
import echowand.object.ObjectData;
import echowand.object.RemoteObject;
import echowand.service.Service;
import echowand.service.result.GetListener;
import echowand.service.result.GetResult;
import echowand.service.result.ResultData;
import echowand.service.result.ResultFrame;
import main.Activator;
import utils.EchonetDataConverter;

public class eAirConditioner extends eDataObject{
	private static final Logger LOGGER = Logger.getLogger(eAirConditioner.class.getName());
	private boolean operationStatus;
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
	}
	
	public boolean isOperationStatus() {
		return operationStatus;
	}
	public void setOperationStatus(boolean operationStatus) {
		if(operationStatus != isOperationStatus()) {
			this.operationStatus = operationStatus;
			notifyDataChanged(this, HomeAirConditioner.PROPERTY_HAS_OPERATION_STATUS);
		}
		
	}
	public boolean setOn() {
		boolean rs = false;
		if(isOperationStatus()) {
			LOGGER.info("Airconditioner is already ON! nothing to do");
			rs = true;
		} else {
			if(executeCommand(EPC.x80, new ObjectData((byte) 0x30))) {
				setOperationStatus(true);
				rs= true;
			} else {
				rs = false;
			}
		}
		return rs;
	}
	public boolean setOff() {
		boolean rs = false;
		if(!isOperationStatus()) {
			LOGGER.info("Airconditioner is already OFF! nothing to do");
			rs = true;
		} else {
			if(executeCommand(EPC.x80, new ObjectData((byte) 0x31))) {
				setOperationStatus(false);
				rs= true;
			} else {
				rs = false;
			}
		}
		return rs;
	}
	public boolean isOperationPowerSaving() {
		return operationPowerSaving;
	}
	public boolean setPowerSavingMode(boolean mode) {
		if(mode) {
			return turnOnPowerSavingMode();
		} else {
			return turnOffPowerSavingMode();
		}
		
	}
	public boolean turnOnPowerSavingMode() {
		boolean rs = false;
		if(isOperationPowerSaving()) {
			LOGGER.info("Airconditioner is already in power saving mode nothing to do");
			rs = true;
		} else {
			if(executeCommand(EPC.x8F, new ObjectData((byte) 0x41))) {
				setOperationPowerSaving(true);
				rs= true;
			} else {
				rs = false;
			}
		}
		return rs;
		
	}
	public boolean turnOffPowerSavingMode() {
		boolean rs = false;
		if(!isOperationPowerSaving()) {
			LOGGER.info("Airconditioner is already in normal mode nothing to do");
			rs = true;
		} else {
			if(executeCommand(EPC.x8F, new ObjectData((byte) 0x42))) {
				setOperationPowerSaving(false);
				rs= true;
			} else {
				rs = false;
			}
		}
		return rs;
	}
	public void setOperationPowerSaving(boolean operationPowerSaving) {
		this.operationPowerSaving = operationPowerSaving;
	}
	public String getOperationModeSetting() {
		return operationModeSetting;
	}
	public void setOperationModeSetting(String operationModeSetting) {
		if(!operationModeSetting.equalsIgnoreCase(getOperationModeSetting())) {
			this.operationModeSetting = operationModeSetting;
			notifyDataChanged(this, HomeAirConditioner.PROPERTY_HAS_OPERATION_MODE_SETTING);
		}
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
	public boolean setDeviceAirFlowRate(String airFlowrate) {
		boolean rs = false;
		if(airFlowrate.equals(getAirFlowRate())) {
			LOGGER.info(String.format("Airconditioner flowrate is already in %s nothing to do", airFlowrate));
			rs = true;
		} else {
			if(executeCommand(EPC.xA0, EchonetDataConverter.dataFromAirConditionerFlowRate(airFlowrate.trim()))) {
				setAirFlowRate(airFlowrate);
				rs= true;
			} else {
				rs = false;
			}
		}
		return rs;
		
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
	/*
	public NodeProfileObject getProfile() {
		return profile;
	}
	public void setProfile(NodeProfileObject profile) {
		this.profile = profile;
	}
	*/
	private void getData(Service service){
		LinkedList<EPC> epcs = new LinkedList<EPC>();
		epcs.add(EPC.x80);
		epcs.add(EPC.x8F);
		epcs.add(EPC.xB0);
		epcs.add(EPC.xB3);
		epcs.add(EPC.xBB);
		epcs.add(EPC.xA0);
		try {
			service.doGet(node,eoj,epcs,5000, new GetListener() {
				@Override
			    public void receive(GetResult result, ResultFrame resultFrame, ResultData resultData) {
					if (resultData.isEmpty()) {
						return;
					}
					
					switch (resultData.getEPC()) {
					case x80:
						if(EchonetDataConverter.dataToInteger(resultData) == 48) {
							setOperationStatus(true);
						} else {
							setOperationStatus(false);
						}
						String setOperationStatusLog = String.format("AirConditioner:%s {EPC:0x80, EDT: 0x%02X}=={OperationStatus:%s}",
								 getNode().getNodeInfo().toString(),resultData.toBytes()[0],isOperationStatus());
						break;
					case x8F:
						if (EchonetDataConverter.dataToInteger(resultData) == 65) {
							
							String setOperationPowerSavingLog= String.format("AirConditioner:"+node.getNodeInfo().toString()+ 
									" {EPC:0x8F, EDT: 0x%02X}=={OperationPowerSaving:True}",resultData.toBytes()[0]);
							setOperationPowerSaving(true);
						} else {
							setOperationPowerSaving(false);
							String setOperationPowerSavingLog= String.format("AirConditioner:"+node.getNodeInfo().toString()+ 
									" {EPC:0x8F, EDT: 0x%02X}=={OperationPowerSaving:False}",resultData.toBytes()[0]);
						}
						break;
					case xB0:
						setOperationModeSetting(EchonetDataConverter.dataToAirConditionerOperationMode(resultData));
						String setOperationModeSettingLog = String.format("AirConditioner:"+node.getNodeInfo().toString()+ 
								" {EPC:0xB0, EDT: 0x%02X}=={OperationMode:%s}",resultData.toBytes()[0],getOperationModeSetting());
						break;
					case xB3:
						setCurrentSettingTemperature(EchonetDataConverter.dataToInteger(resultData));
						String setCurrentSettingTemperatureLog = String.format("AirConditioner:"+node.getNodeInfo().toString()+ 
								" {EPC:0xB3, EDT: 0x%02X}=={CurrentSettingTemperature:%d}",resultData.toBytes()[0],getCurrentSettingTemperature());
						break;
					case xBB:
						setRoomTemperature(EchonetDataConverter.dataToInteger(resultData));
						String setRoomTemperatureLog = String.format("AirConditioner:"+node.getNodeInfo().toString()+ 
								" {EPC:0xBB, EDT: 0x%02X}=={RoomTemperature:%d}",resultData.toBytes()[0],getRoomTemperature());
						break;
					case xA0:
						setAirFlowRate(EchonetDataConverter.dataToAirConditionerFlowRate(resultData));
						String setAirFlowRateLog = String.format("AirConditioner:"+node.getNodeInfo().toString()+ 
								" {EPC:0xA0, EDT: 0x%02X}=={AirFlowRate:%s}",resultData.toBytes()[0],getAirFlowRate());
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
	public void ParseDataFromEOJ(Service service) {
		timer = new Timer(true);
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				getData(service);
			}
		}, 3000, 20000);	
	}
	@Override
	public String TouAALReponse() {
		return String.format("%s;%s;%s;%d;%d;%s",isOperationStatus(), isOperationPowerSaving(),
				              getOperationModeSetting(), getCurrentSettingTemperature(), getRoomTemperature(),getAirFlowRate());
	}
	
	private boolean executeCommand(EPC epc, ObjectData data) {
		boolean rs = false;
		Activator.echonetService.registerRemoteEOJ(this.node, this.eoj);
		RemoteObject remoteObject = Activator.echonetService.getRemoteObject(node, eoj);
		LOGGER.info(String.format("Execute command [IP:%s, EOJ:%s, Data:%s]",this.node.getNodeInfo().toString(),this.eoj,data));
		try {
			if (remoteObject.setData(epc, data)) {
				rs= true;
				LOGGER.info(String.format("Completed: [IP:%s, EOJ:%s, Data:%s]",this.node.getNodeInfo().toString(),this.eoj,data));
			}
		} catch (EchonetObjectException e) {
			LOGGER.error("Can not find object: " +e.toString());
			rs= false;
		}
		return rs;
	}
	public boolean setDeviceLocation(String location) {
		boolean rs = false;
		if(location.equals(getInstallLocation())) {
			LOGGER.info(String.format("Airconditioner is already at %s nothing to do", location));
			rs = true;
		} else {
			if(executeCommand(EPC.x81, EchonetDataConverter.installLocationtoDataObj(location))) {
				refreshInstallLocation(location);
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
			if(executeCommand(EPC.xB0, EchonetDataConverter.dataFromAirConditionerOperationMode(mode.trim()))) {
				setOperationMode(mode);
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
			if(executeCommand(EPC.xB3, new ObjectData(new Integer(temperature).byteValue()))) {
				setCurrentSettingTemperature(temperature);
				rs= true;
			} else {
				rs = false;
			}
		}
		return rs;
	}
	
}
