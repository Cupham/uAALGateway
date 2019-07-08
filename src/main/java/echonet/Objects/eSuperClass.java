package echonet.Objects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.universAAL.ontology.echonetontology.EchonetSuperDevice;

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
import utils.SampleConstants;
public class eSuperClass {
	private static final Logger LOGGER = Logger.getLogger(eSuperClass.class.getName());
	private Timer timer;
	private String deviceID;
	/**
	 * Device IP
	 */
	public Node node;
	/**
	 * Class group code
	 */
	public byte groupCode;
	/**
	 * Class code
	 */
	public byte classCode;
	/**
	 * Instance code
	 */
	public byte instanceCode;
	
	public EOJ eoj;

	/**
	 * EPC: 0x80 ON: 0x30, OFF: 0x31
	 */
	public boolean operationStatus;
	/**
	 * EPC: 0x81
	 */
	public String installLocation;
	/**
	 * EPC: 0x82 the release number of the corresponding Appendix with fixed 4
	 * bytes
	 */
	public String standardVersionInfo;
	/**
	 * EPC: 0x83 Unique identification number fixed 9 bytes
	 */
	public String identificationNumber;
	/**
	 * EPC: 0x84 Instantaneous power consumption of the device in watts Value
	 * between: 0x0000–0xFFFD(0–65533W)
	 */
	public short instantaneousPower;
	/**
	 * EPC: 0x85 Cumulative power consumption of the device in increments of
	 * 0.001kWh Value between: 0x00000000–0x3B9AC9FF (0–999,999.999kWh)
	 */
	public long cumulativePower;
	/**
	 * EPC: 0x86 Manufacturer-defined fault code
	 */
	public String manufacturerFaultCode;
	/**
	 * EPC: 0x87 Current limit setting Value betwee: 0x00–0x64 (=0–100%)
	 */
	public int currentLimitSetting;
	/**
	 * EPC: 0x88 whether a fault (e.g. a sensor trouble) has occurred or not.
	 * Fault occurred: 0x41, No fault has occurred: 0x42
	 */
	public boolean faultStatus;
	/**
	 * EPC: 0x89 Describes the fault
	 */
	public String faultDescription;
	/**
	 * EPC:0x8A 3-byte manufacturer code unsigned (Defined by the ECHONET
	 * Consortium)
	 */
	public String manufacturerCode;
	/**
	 * EPC: 0x8B 3-byte business facility code (Defined by each manufacturer)
	 */
	public String businessFacilityCode;
	/**
	 * EPC: 0x8C Identifies the product using ASCII code (Defined by each
	 * manufacturer)
	 */
	public String productCode;
	/**
	 * EPC: 0x8D Production number using ASCII code (Defined by each
	 * manufacturer)
	 */
	public String productNumber;
	/**
	 * EPC: 0x8E 4-byte production date code, YYMD format
	 */
	public Date productDate;
	/**
	 * EPC: 0x8F Device is operating in power-saving mode Operating in
	 * power-saving mode: 0x41, Operating in normal operation mode: 0x42
	 */
	public boolean powerSaving;
	/**
	 * EPC: 0x93 Whether remote control is through a public network or not Not
	 * through a public network: 0x41 Through a public network: 0x42
	 */
	public boolean throughPublicNetwork;
	/**
	 * EPC: 0x97 Current time (HH: MM format), 0x00–0x17: 0x00–0x3B (=0–23):
	 * (=0–59)
	 */
	public String currentTimeSetting;
	/**
	 * EPC: 0x98 Current date (YYYY: MM: DD format),1–0x270F : 1–0x0C : 1–0x1F
	 * (=1–9999) : (=1–12) : (=1–31)
	 */
	public Date currentDateSetting;
	/**
	 * EPC: 0x99 Power limit setting in watts 0x0000–0xFFFF(0–65535W)
	 */
	public int powerLimit;
	/**
	 * EPC: 0x9A Cumulative operating time
	 */
	public String cumulativeTime;
	public eSuperClass() {
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param ip
	 * @param name
	 */
	public eSuperClass(Node node, EOJ eoj) {
		this.node = node;
		this.eoj = eoj;
		this.deviceID = node.getNodeInfo().toString().replace(".", "_")+"_"+eoj.getInstanceCode();
	}
	private void getData(Service service){
		LinkedList<EPC> epcs = new LinkedList<EPC>();
		epcs.addAll(SampleConstants.defaultEPCs);
		
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
							rsLocation = " The installation location has not been set";
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
			LOGGER.error(e.toString());
		}
	}

	public void ParseProfileObjectFromEPC(Service service) {
		timer = new Timer(true);
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				getData(service);
			}
		},SampleConstants.getDelayInterval(), SampleConstants.getRefreshInterval());	
	}
	
	
	

	@Override
	public String toString() {
		StringBuilder rs = new StringBuilder();
		rs.append(" Device IP: " + this.node.toString() + ",");
		rs.append(" Device ID: " + this.deviceID + ",");
		rs.append(" Operation status: " + ((this.operationStatus) ? "ON" : "OFF") + ",");
		rs.append(" Installation location: " + this.installLocation + ",");
		rs.append(" Standard version information: " + this.standardVersionInfo + ",");
		rs.append(" Identify number: " + this.identificationNumber + ",");
		rs.append(" Measure instantaneous power consumption: " + this.instantaneousPower + "W,");
		rs.append(" Measured cumulative power consumption: " + (this.cumulativePower / 1000) + "kWh,");
		rs.append(" Manufacturer’s fault code: " + this.manufacturerFaultCode + ",");
		rs.append(" Current limit setting: " + this.currentLimitSetting + "%,");
		rs.append(" Fault status: " + ((this.faultStatus) ? "Fault occurred" : "No fault has occurred") + ",");
		rs.append(" Fault description: " + this.faultDescription + ",");
		rs.append(" Manufacturer code: " + this.manufacturerCode + ",");
		rs.append(" Business facility code: " + this.businessFacilityCode + ",");
		rs.append(" Product code: " + this.productCode + ",");
		rs.append(" Production number: " + this.productNumber + ",");
		rs.append(" Production date: "
				+ ((this.productDate != null) ? (new SimpleDateFormat("yyyy-MM-dd").format(this.productDate)) : "NULL")
				+ ",");
		rs.append(" Power-saving operation setting: "
				+ ((this.powerSaving) ? "Power saving mode" : "Normal operation mode") + ",");
		rs.append(" Remote control setting: "
				+ ((this.throughPublicNetwork) ? "Not through a public network" : "Through a public network") + ",");
		rs.append(" Current time setting: " + this.currentTimeSetting + ",");
		rs.append(" Current date setting: " + ((this.currentDateSetting != null)
				? (new SimpleDateFormat("yyyy-MM-dd").format(this.currentDateSetting)) : "NULL") + ",");
		rs.append(" Power limit setting: " + this.powerLimit + "W,");
		rs.append(" Cumulative operating time: " + this.cumulativeTime);
		return rs.toString();
	}

	
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceName) {
		this.deviceID = deviceName;
	}
	
	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public byte getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(byte groupCode) {
		this.groupCode = groupCode;
	}
	public byte getClassCode() {
		return classCode;
	}
	public void setClassCode(byte classCode) {
		this.classCode = classCode;
	}
	public byte getInstanceCode() {
		return instanceCode;
	}
	public void setInstanceCode(byte instanceCode) {
		this.instanceCode = instanceCode;
	}
	public boolean getOperationStatus() {
		return operationStatus;
	}
	public void refreshOperationStatus(boolean operationStatus) {
		if(this.getOperationStatus() != operationStatus) {
			this.operationStatus = operationStatus;
		}
		
	}
	public String getInstallLocation() {
		return installLocation;
	}
	public void refreshInstallLocation(String installLocation) {
		if(!installLocation.equals(this.getInstallLocation())) {
			this.installLocation = installLocation;
		}
		
	}
	public String getStandardVersionInfo() {
		return standardVersionInfo;
	}
	public void refreshStandardVersionInfo(String standardVersionInfo) {
		if(!standardVersionInfo.equals(this.getStandardVersionInfo())) {
			this.standardVersionInfo = standardVersionInfo;
		}		
	}
	public String getIdentificationNumber() {
		return identificationNumber;
	}
	public void refreshIdentificationNumber(String identificationNumber) {
		if(!identificationNumber.equals(this.getIdentificationNumber())) {
			this.identificationNumber = identificationNumber;
		}
		
	}
	public short getInstantaneousPower() {
		return instantaneousPower;
	}
	public void refreshInstantaneousPower(short instantaneousPower) {
		if(this.getInstantaneousPower() != instantaneousPower) {
			this.instantaneousPower = instantaneousPower;
		}
		
	}
	public long getCumulativePower() {
		return cumulativePower;
	}
	public void refreshCumulativePower(long cumulativePower) {
		if(this.getCumulativePower() != cumulativePower) {
			this.cumulativePower = cumulativePower;
		}
		
	}
	public String getManufacturerFaultCode() {
		return manufacturerFaultCode;
	}
	public void refreshManufactureerFaultCode(String manufactureerFaultCode) {
		if(!manufactureerFaultCode.equals(this.getManufacturerFaultCode())) {
			this.manufacturerFaultCode = manufactureerFaultCode;
		}
		
	}
	public int getCurrentLimitSetting() {
		return currentLimitSetting;
	}
	public void refreshCurrentLimitSetting(int currentLimitSetting) {
		if(this.getCurrentLimitSetting()!=currentLimitSetting) {
			this.currentLimitSetting = currentLimitSetting;
		}
		
	}
	public boolean isFaultStatus() {
		return faultStatus;
	}
	public void refreshFaultStatus(boolean faultStatus) {
		if(this.isFaultStatus() != faultStatus) {
			this.faultStatus = faultStatus;
		}
		
	}
	public String getFaultDescription() {
		return faultDescription;
	}
	public void refreshFaultDescription(String faultDescription) {
		if(!faultDescription.equals(this.getFaultDescription())) {
			this.faultDescription = faultDescription;
		}
		
	}
	public String getManufacturerCode() {
		return manufacturerCode;
	}
	public void refreshManufacturerCode(String manufacturerCode) {
		if(!manufacturerCode.equals(this.getManufacturerCode())) {
			this.manufacturerCode = manufacturerCode;
		}		
	}
	public String getBusinessFacilityCode() {
		return businessFacilityCode;
	}
	public void refreshBusinessFacilityCode(String businessFacilityCode) {
		if(!businessFacilityCode.equals(this.getBusinessFacilityCode())) {
			this.businessFacilityCode = businessFacilityCode;
		}	
	}
	public String getProductCode() {
		return productCode;
	}
	public void refreshProductCode(String productCode) {
		if(!productCode.equals(this.getProductCode())) {
			this.productCode = productCode;
		}	
	}
	public String getProductNumber() {
		return productNumber;
	}
	public void refreshProductNumber(String productNumber) {
		if(!productNumber.equals(this.getProductNumber())) {
			this.productNumber = productNumber;
		}
		
	}
	public Date getProductDate() {
		return productDate;
	}
	public void refreshProductDate(Date productDate) {
		if(!productDate.equals(this.getProductDate())) {
			this.productDate = productDate;
		}
		
	}
	public boolean getPowerSaving() {
		return powerSaving;
	}
	public void refreshPowerSaving(boolean powerSaving) {
		if(this.getPowerSaving()!=powerSaving) {
			this.powerSaving = powerSaving;
		}
		
	}
	public boolean getThroughPublicNetwork() {
		return throughPublicNetwork;
	}
	public void refreshThroughPublicNetwork(boolean throughPublicNetwork) {
		if(this.getThroughPublicNetwork()!=throughPublicNetwork) {
			this.throughPublicNetwork = throughPublicNetwork;
		}
		
	}
	public String getCurrentTimeSetting() {
		return currentTimeSetting;
	}
	public void refreshCurrentTimeSetting(String currentTimeSetting) {
		if(!currentTimeSetting.equals(this.getCurrentTimeSetting())) {
			this.currentTimeSetting = currentTimeSetting;
		}
		
	}
	public Date getCurrentDateSetting() {
		return currentDateSetting;
	}
	public void refreshCurrentDateSetting(Date currentDateSetting) {
		if(!currentDateSetting.equals(this.getCurrentDateSetting())) {
			this.currentDateSetting = currentDateSetting;
		}	
	}
	public int getPowerLimit() {
		return powerLimit;
	}
	public void refreshPowerLimit(int powerLimit) {
		if(this.getPowerLimit()!=powerLimit) {
			this.powerLimit = powerLimit;
		}
		
	}
	public String getCumulativeTime() {
		return cumulativeTime;
	}
	public void refreshCumulativeTime(String cumulativeTime) {
		if(!cumulativeTime.equals(this.getCumulativeTime())) {
			this.cumulativeTime = cumulativeTime;
		}
	}

	public EOJ getEoj() {
		return eoj;
	}

	public void setEoj(EOJ eoj) {
		this.eoj = eoj;
	}
	
	// Command executor
	private boolean executeCommand(EPC epc, ObjectData data) {
		boolean rs = false;
		Activator.echonetService.registerRemoteEOJ(this.node, this.eoj);
		RemoteObject remoteObject = Activator.echonetService.getRemoteObject(node, eoj);
		LOGGER.debug(String.format("Execute command [IP:%s, EOJ:%s, Data:%s]",this.node.getNodeInfo().toString(),this.eoj,data));
		try {
			if (remoteObject.setData(epc, data)) {
				rs= true;
				LOGGER.debug(String.format("Completed: [IP:%s, EOJ:%s, Data:%s]",this.node.getNodeInfo().toString(),this.eoj,data));
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
			LOGGER.info(String.format("Location is already set to %s nothing to do", location));
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
	public boolean setCurrentLimitSetting(int limitSetting) {
		boolean rs = false;
		if(getCurrentLimitSetting() == limitSetting) {
			LOGGER.info(String.format("LimitSetting is already set to %d ! nothing to do", limitSetting));
			rs = true;
		} else {
			if(executeCommand(EPC.x87, new ObjectData(new Integer(limitSetting).byteValue()))) {
				refreshCurrentLimitSetting(limitSetting);
				rs= true;
			} else {
				rs = false;
			}
		}
		return rs;
	}
	public boolean setpowerSavingMode() {
		boolean rs = false;
		if(getPowerSaving()) {
			LOGGER.info("It is operating in power-saving mode! nothing to do");
			rs = true;
		} else {
			if(executeCommand(EPC.x8F, new ObjectData((byte) 0x41))) {
				refreshPowerSaving(true);
				rs= true;
			} else {
				rs = false;
			}
		}
		return rs;
	}
	
	public boolean turnOffpowerSavingMode() {
		boolean rs = false;
		if(!getPowerSaving()) {
			LOGGER.info("It is not operating in power-saving mode! nothing to do");
			rs = true;
		} else {
			if(executeCommand(EPC.x8F, new ObjectData((byte) 0x42))) {
				refreshPowerSaving(false);
				rs= true;
			} else {
				rs = false;
			}
		}
		return rs;
	}
	
	public boolean setThroughPublicNetwork() {
		boolean rs = false;
		if(getThroughPublicNetwork()) {
			LOGGER.info("It is accessed via public network! nothing to do");
			rs = true;
		} else {
			if(executeCommand(EPC.x93, new ObjectData((byte) 0x42))) {
				refreshThroughPublicNetwork(true);
				rs= true;
			} else {
				rs = false;
			}
		}
		return rs;
	}
	
	public boolean setNotThroughPublicNetwork() {
		boolean rs = false;
		if(!getThroughPublicNetwork()) {
			LOGGER.info("It is not accessed via public network! nothing to do");
			rs = true;
		} else {
			if(executeCommand(EPC.x93, new ObjectData((byte) 0x41))) {
				refreshThroughPublicNetwork(false);
				rs= true;
			} else {
				rs = false;
			}
		}
		return rs;
	}
	
	public boolean setPowerLimitSetting(int powerLimitSetting) {
		boolean rs = false;
		if(getPowerLimit() == powerLimitSetting) {
			LOGGER.info(String.format("PowerLimitSetting is already set to %d ! nothing to do", powerLimitSetting));
			rs = true;
		} else {
			if(executeCommand(EPC.x99, new ObjectData(new Integer(powerLimitSetting).byteValue()))) {
				refreshPowerLimit(powerLimitSetting);
				rs= true;
			} else {
				rs = false;
			}
		}
		return rs;
	}
	

}
