package echonet.Objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.ontology.echonetontology.audiovisualRelatedDevices.Audio;
import org.universAAL.ontology.echonetontology.managementOperationRelatedDevices.Switch;
import org.universAAL.ontology.echonetontology.sensorRelatedDevices.ElectricEnergySensor;
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
import echowand.object.RemoteObject;
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

public class eRadio extends eDataObject{
	private static final Logger LOGGER = Logger.getLogger(eRadio.class.getName());
	private Timer timer;
	private Audio radio;

	public eRadio() {
		super();
		this.groupCode= (byte) 0x00;
		this.classCode = (byte) 0x22;
	}
	public eRadio(byte instanceCode) {
		super();
		this.groupCode= (byte) 0x00;
		this.classCode = (byte) 0x22;
		this.instanceCode = instanceCode;
	}
	public eRadio(EOJ eoj, Node node) {
		super(node, eoj);
		this.groupCode= (byte) 0x00;
		this.classCode = (byte) 0x22;
		this.instanceCode = eoj.getInstanceCode();
		radio = new Audio(Audio.MY_URI+getDeviceID());
		Activator.cPublisher.publicContextEvent(new ContextEvent(ContextEvent.CONTEXT_EVENT_URI_PREFIX+getDeviceID()));
	}

	// Provided Services
				public boolean setDeviceLocation(String location) {
					boolean rs = false;
					if(location.equals(getInstallLocation())) {
						LOGGER.info(String.format("Radio is already in %s nothing to do", location));
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
						LOGGER.info("Radio is already ON! nothing to do");
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
						LOGGER.info("Radio is already OFF! nothing to do");
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
				
			// Device Property Monitoring
			
			public void refreshOperationStatus(boolean operationStatus) {
				if(this.getOperationStatus() != operationStatus) {
					this.operationStatus = operationStatus;
					
					if(operationStatus) {
						radio.setOperationStatus(OperationStatusValue.On);
					} else {
						radio.setOperationStatus(OperationStatusValue.Off);
					}
					Activator.cPublisher.publicContextEvent(new ContextEvent(radio,Audio.PROPERTY_HAS_OPERATION_STATUS));
				}
				
			}
			public void refreshInstallLocation(String installLocation) {
				if(!installLocation.equals(this.getInstallLocation())) {
					this.installLocation = installLocation;
					radio.setInstallationLocation(EchonetDataConverter.installationLocationToEnum(installLocation));
					Activator.cPublisher.publicContextEvent(new ContextEvent(radio,Audio.PROPERTY_HAS_INSTALLATION_LOCATION));
				}
			}
			public void refreshStandardVersionInfo(String standardVersionInfo) {
				if(!standardVersionInfo.equals(this.getStandardVersionInfo())) {
					this.standardVersionInfo = standardVersionInfo;
					radio.setStandardVersionInformation(standardVersionInfo);
					Activator.cPublisher.publicContextEvent(new ContextEvent(radio,Audio.PROPERTY_STANDARD_VERSION_INFORMATION));
				}		
			}
			public void refreshIdentificationNumber(String identificationNumber) {
				if(!identificationNumber.equals(this.getIdentificationNumber())) {
					this.identificationNumber = identificationNumber;
					radio.setIdentificationNumber(EchonetDataConverter.stringToIdentificationNumber(identificationNumber));
					Activator.cPublisher.publicContextEvent(new ContextEvent(radio,Audio.PROPERTY_HAS_IDENTIFICATION_NUMBER));
				}
				
			}
			public void refreshInstantaneousPower(short instantaneousPower) {
				if(this.getInstantaneousPower() != instantaneousPower) {
					this.instantaneousPower = instantaneousPower;
					radio.setMeasuredInstantaneousPowerConsumption(new Integer(instantaneousPower));
					Activator.cPublisher.publicContextEvent(new ContextEvent(radio,Audio.PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION));
				}
				
			}
			public void refreshCumulativePower(long cumulativePower) {
				if(this.getCumulativePower() != cumulativePower) {
					this.cumulativePower = cumulativePower;
					radio.setMeasuredCumulativePowerConsumption(new Float(cumulativePower));
					Activator.cPublisher.publicContextEvent(new ContextEvent(radio,Audio.PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION));
				}	
			}
			public void refreshManufactureerFaultCode(String manufactureerFaultCode) {
				if(!manufactureerFaultCode.equals(this.getManufacturerFaultCode())) {
					this.manufacturerFaultCode = manufactureerFaultCode;
					radio.setManufacturerFaultCode(manufactureerFaultCode);
					Activator.cPublisher.publicContextEvent(new ContextEvent(radio,Audio.PROPERTY_HAS_MANUFACTURER_FAULT_CODE));
				}	
			}
			public void refreshCurrentLimitSetting(int currentLimitSetting) {
				if(this.getCurrentLimitSetting()!=currentLimitSetting) {
					this.currentLimitSetting = currentLimitSetting;
					radio.setCurrentLimitSetting(new Integer(currentLimitSetting));
					Activator.cPublisher.publicContextEvent(new ContextEvent(radio,Audio.PROPERTY_HAS_CURRENT_LIMIT_SETTING));
				}	
			}
			public void refreshFaultStatus(boolean faultStatus) {
				if(this.isFaultStatus() != faultStatus) {
					this.faultStatus = faultStatus;
					if(faultStatus) {
						radio.setFaultStatus(OccurenceStatusValue.OccurenceStatusFound);
					} else {
						radio.setFaultStatus(OccurenceStatusValue.OccurenceStatusNotFound);
					}	
					Activator.cPublisher.publicContextEvent(new ContextEvent(radio,Audio.PROPERTY_HAS_FAULT_STATUS));
				}	
			}
			public void refreshFaultDescription(String faultDescription) {
				if(!faultDescription.equals(this.getFaultDescription())) {
					this.faultDescription = faultDescription;
					radio.setFaultDesciptionValue(EchonetDataConverter.stringToFaultDescription(faultDescription));
					Activator.cPublisher.publicContextEvent(new ContextEvent(radio,Audio.PROPERTY_HAS_FAULT_DESCRIPTION));
				}
			}
			public void refreshManufacturerCode(String manufacturerCode) {
				if(!manufacturerCode.equals(this.getManufacturerCode())) {
					this.manufacturerCode = manufacturerCode;
					radio.setManufacturerCode(manufacturerCode);
					Activator.cPublisher.publicContextEvent(new ContextEvent(radio,Audio.PROPERTY_HAS_MANUFACTURER_CODE));
				}		
			}
			public void refreshBusinessFacilityCode(String businessFacilityCode) {
				if(!businessFacilityCode.equals(this.getBusinessFacilityCode())) {
					this.businessFacilityCode = businessFacilityCode;
					radio.setBusinessFacilityCode(businessFacilityCode);
					Activator.cPublisher.publicContextEvent(new ContextEvent(radio,Audio.PROPERTY_HAS_BUSINESS_FACILITY_CODE));
				}	
			}
			public void refreshProductCode(String productCode) {
				if(!productCode.equals(this.getProductCode())) {
					this.productCode = productCode;
					radio.setProductCode(productCode);
					Activator.cPublisher.publicContextEvent(new ContextEvent(radio,Audio.PROPERTY_HAS_PRODUCT_CODE));
				}	
			}
			public void refreshProductNumber(String productNumber) {
				if(!productNumber.equals(this.getProductNumber())) {
					this.productNumber = productNumber;
					radio.setProductionNumber(productNumber);
					Activator.cPublisher.publicContextEvent(new ContextEvent(radio,Audio.PROPERTY_HAS_PRODUCTION_NUMBER));
				}	
			}
			public void refreshProductDate(Date productDate) {
				if(!productDate.equals(this.getProductDate())) {
					this.productDate = productDate;
					radio.setProductionString(productDate.toString());
					Activator.cPublisher.publicContextEvent(new ContextEvent(radio,Audio.PROPERTY_HAS_PRODUCTION_DATE));
				}
				
			}
			public void refreshPowerSaving(boolean powerSaving) {
				if(this.getPowerSaving()!=powerSaving) {
					this.powerSaving = powerSaving;
					if(powerSaving) {
						radio.setPowerSavingOperationSetting(OperationModeSettingValue.PowerSavingMode);
					} else {
						radio.setPowerSavingOperationSetting(OperationModeSettingValue.NormalMode);
					}
					Activator.cPublisher.publicContextEvent(new ContextEvent(radio,Audio.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING));
				}
				
			}
			public void refreshThroughPublicNetwork(boolean throughPublicNetwork) {
				if(this.getThroughPublicNetwork()!=throughPublicNetwork) {
					this.throughPublicNetwork = throughPublicNetwork;
					if(throughPublicNetwork) {
						radio.setRemoteControlSetting(RemoteControlSettingValue.ThroughPublicNetwork);
					} else {
						radio.setRemoteControlSetting(RemoteControlSettingValue.NotThroughPublicNetwork);
					}
					Activator.cPublisher.publicContextEvent(new ContextEvent(radio,Audio.PROPERTY_HAS_REMOTE_CONTROL_SETTING));
				}
				
			}
			public void refreshCurrentTimeSetting(String currentTimeSetting) {
				if(!currentTimeSetting.equals(this.getCurrentTimeSetting())) {
					this.currentTimeSetting = currentTimeSetting;
					radio.setCurrentTimeSetting(currentTimeSetting);
					Activator.cPublisher.publicContextEvent(new ContextEvent(radio,Audio.PROPERTY_HAS_CURRENT_TIME_SETTING));
				}
			}
			public void refreshCurrentDateSetting(Date currentDateSetting) {
				if(!currentDateSetting.equals(this.getCurrentDateSetting())) {
					this.currentDateSetting = currentDateSetting;
					radio.setCurrentStringSetting(currentDateSetting.toString());
					Activator.cPublisher.publicContextEvent(new ContextEvent(radio,Audio.PROPERTY_HAS_CURRENT_DATE_SETTING));
				}	
			}
			public void refreshPowerLimit(int powerLimit) {
				if(this.getPowerLimit()!=powerLimit) {
					this.powerLimit = powerLimit;
					radio.setPowerLimitSetting(new Integer(powerLimit));
					Activator.cPublisher.publicContextEvent(new ContextEvent(radio,Audio.PROPERTY_HAS_POWER_LIMIT_SETTING));
				}
			}
			public void refreshCumulativeTime(String cumulativeTime) {
				if(!cumulativeTime.equals(this.getCumulativeTime())) {
					this.cumulativeTime = cumulativeTime;
					radio.setCumulativeOperatingTime(cumulativeTime);
					Activator.cPublisher.publicContextEvent(new ContextEvent(radio,Audio.PROPERTY_HAS_CUMULATIVE_OPERATING_TIME));
				}
			}
		
		public void getData(Service service){
			ArrayList<EPC> epcs = new ArrayList<EPC>();
			epcs.addAll(SampleConstants.defaultEPCs);
			epcs.add(EPC.xE0);
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
		return String.format("%s", getOperationStatus()?"ON":"OFF");
	}
}
