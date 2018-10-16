package echonet.Objects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.universAAL.ontology.echonetontology.EchonetSuperDevice;

import echowand.common.EOJ;
import echowand.common.EPC;
import echowand.net.Node;
import echowand.net.SubnetException;
import echowand.object.EchonetObjectException;
import echowand.service.Service;
import echowand.service.result.GetListener;
import echowand.service.result.GetResult;
import echowand.service.result.ResultData;
import echowand.service.result.ResultFrame;
import utils.EchonetDataConverter;
public class eSuperClass {
	private Timer timer;
	private String deviceID;
	private List<DataChangeObserver> observers = new ArrayList<DataChangeObserver>();
	/**
	 * Device IP
	 */
	public Node node;
	/**
	 * Class group code
	 */
	protected byte groupCode;
	/**
	 * Class code
	 */
	protected byte classCode;
	/**
	 * Instance code
	 */
	protected byte instanceCode;
	
	public EOJ eoj;

	/**
	 * EPC: 0x80 ON: 0x30, OFF: 0x31
	 */
	private boolean operationStatus;
	/**
	 * EPC: 0x81
	 */
	private String installLocation;
	/**
	 * EPC: 0x82 the release number of the corresponding Appendix with fixed 4
	 * bytes
	 */
	private String standardVersionInfo;
	/**
	 * EPC: 0x83 Unique identification number fixed 9 bytes
	 */
	private String identificationNumber;
	/**
	 * EPC: 0x84 Instantaneous power consumption of the device in watts Value
	 * between: 0x0000–0xFFFD(0–65533W)
	 */
	private short instantaneousPower;
	/**
	 * EPC: 0x85 Cumulative power consumption of the device in increments of
	 * 0.001kWh Value between: 0x00000000–0x3B9AC9FF (0–999,999.999kWh)
	 */
	private long cumulativePower;
	/**
	 * EPC: 0x86 Manufacturer-defined fault code
	 */
	private String manufacturerFaultCode;
	/**
	 * EPC: 0x87 Current limit setting Value betwee: 0x00–0x64 (=0–100%)
	 */
	private int currentLimitSetting;
	/**
	 * EPC: 0x88 whether a fault (e.g. a sensor trouble) has occurred or not.
	 * Fault occurred: 0x41, No fault has occurred: 0x42
	 */
	private boolean faultStatus;
	/**
	 * EPC: 0x89 Describes the fault
	 */
	private String faultDescription;
	/**
	 * EPC:0x8A 3-byte manufacturer code unsigned (Defined by the ECHONET
	 * Consortium)
	 */
	private String manufacturerCode;
	/**
	 * EPC: 0x8B 3-byte business facility code (Defined by each manufacturer)
	 */
	private String businessFacilityCode;
	/**
	 * EPC: 0x8C Identifies the product using ASCII code (Defined by each
	 * manufacturer)
	 */
	private String productCode;
	/**
	 * EPC: 0x8D Production number using ASCII code (Defined by each
	 * manufacturer)
	 */
	private String productNumber;
	/**
	 * EPC: 0x8E 4-byte production date code, YYMD format
	 */
	private Date productDate;
	/**
	 * EPC: 0x8F Device is operating in power-saving mode Operating in
	 * power-saving mode: 0x41, Operating in normal operation mode: 0x42
	 */
	private boolean powerSaving;
	/**
	 * EPC: 0x93 Whether remote control is through a public network or not Not
	 * through a public network: 0x41 Through a public network: 0x42
	 */
	private boolean throughPublicNetwork;
	/**
	 * EPC: 0x97 Current time (HH: MM format), 0x00–0x17: 0x00–0x3B (=0–23):
	 * (=0–59)
	 */
	private String currentTimeSetting;
	/**
	 * EPC: 0x98 Current date (YYYY: MM: DD format),1–0x270F : 1–0x0C : 1–0x1F
	 * (=1–9999) : (=1–12) : (=1–31)
	 */
	private Date currentDateSetting;
	/**
	 * EPC: 0x99 Power limit setting in watts 0x0000–0xFFFF(0–65535W)
	 */
	private short powerLimit;
	/**
	 * EPC: 0x9A Cumulative operating time
	 */
	private String cumulativeTime;
	public eSuperClass() {
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param ip
	 * @param name
	 */
	public eSuperClass(Node node, String name) {		
		this.node = node;
		this.deviceID = name;
	}
	public eSuperClass(Node node, EOJ eoj) {
		this.node = node;
		this.eoj = eoj;
		this.deviceID = node.getNodeInfo().toString()+"_"+eoj.getInstanceCode();
	}
	private void getData(Service service){
		LinkedList<EPC> epcs = new LinkedList<EPC>();
		epcs.add(EPC.x80);
		epcs.add(EPC.x81);
		epcs.add(EPC.x82);	
		epcs.add(EPC.x83);
		epcs.add(EPC.x84);
		epcs.add(EPC.x85);
		epcs.add(EPC.x86);
		epcs.add(EPC.x87);
		epcs.add(EPC.x88);
		epcs.add(EPC.x89);
		epcs.add(EPC.x8A);
		epcs.add(EPC.x8B);
		epcs.add(EPC.x8C);
		epcs.add(EPC.x8D);
		epcs.add(EPC.x8E);
		epcs.add(EPC.x8F);
		epcs.add(EPC.x93);
		epcs.add(EPC.x97);
		epcs.add(EPC.x98);
		epcs.add(EPC.x99);
		epcs.add(EPC.x9A);
		
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
						String setOperationStatuslog = String.format("Node:%s@EOJ:%s {EPC:0x80, EDT: 0x%02X}=={OperationStatus:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],isOperationStatus());

						break;
					case x81:
						String rsLocation = EchonetDataConverter.dataToInstallLocation(resultData);	
						if (rsLocation == null) {
							rsLocation = " The installation location has not been set";
						}
						setInstallLocation(rsLocation);		
						String setInstallLocationLog = String.format("Node:%s@EOJ:%s {EPC:0x81, EDT: 0x%02X}=={InstallationLocation:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getInstallLocation());
						break;
					case x82:
						setStandardVersionInfo(EchonetDataConverter.dataToVersion(resultData));
						String setStandardVersionInfoLog =String.format("Node:%s@EOJ:%s {EPC:0x82, EDT: 0x%02X}=={Version Information:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getStandardVersionInfo());
						break;
					case x83:
						setIdentificationNumber(EchonetDataConverter.dataToIdentifiCationNumber(resultData));
						String setIdentificationNumber = String.format("Node:%s@EOJ:%s {EPC:0x83, EDT: 0x%02X}=={Identification Number:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getIdentificationNumber());
						break;
					case x84:
						setInstantaneousPower(EchonetDataConverter.dataToShort(resultData));
						String setInstantaneousPower = String.format("Node:%s@EOJ:%s {EPC:0x84, EDT: 0x%02X}=={Instantaneous Power Consumption:%d}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getInstantaneousPower());
						break;		
					case x85:
						setCumulativePower(EchonetDataConverter.dataToLong(resultData));
						String setCumulativePower = String.format("Node:%s@EOJ:%s {EPC:0x85, EDT: 0x%02X}=={Cumulative Power Consumption:%d}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getCumulativePower());
						break;
					case x86:
						setManufactureerFaultCode(EchonetDataConverter.dataToFaultCode(resultData));
						String setManufactureerFaultCode = String.format("Node:%s@EOJ:%s {EPC:0x86, EDT: 0x%02X}=={Manufacturer Fault Code:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getManufacturerFaultCode());
						break;
					case x87:
						setCurrentLimitSetting(EchonetDataConverter.dataToInteger(resultData));
						String setCurrentLimitSetting = String.format("Node:%s@EOJ:%s {EPC:0x87, EDT: 0x%02X}=={Current Limit Setting:%d}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getCurrentLimitSetting());
						break;		
					case x88:
						if(EchonetDataConverter.dataToInteger(resultData) == 65) {
							setFaultStatus(true);
						} else {
							setFaultStatus(false);
						}
						String setFaultStatus = String.format("Node:%s@EOJ:%s {EPC:0x88, EDT: 0x%02X}=={Fault Status:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],isFaultStatus());
						break;		
					case x89:
						if (isFaultStatus()) {
							try {
								setFaultDescription(EchonetDataConverter.getFaultDetail(resultData));
							} catch (EchonetObjectException e) {
								e.printStackTrace();
							}
						} else {
							setFaultDescription("NO FAULT");
						}
						String isFaultStatus = String.format("Node:%s@EOJ:%s {EPC:0x89, EDT: 0x%02X}=={Fault Description:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getFaultDescription());
						break;
					case x8A:
						setManufacturerCode(EchonetDataConverter.dataToString(resultData));
						String setManufacturerCode = String.format("Node:%s@EOJ:%s {EPC:0x8A, EDT: 0x%02X}=={Manufacturer Code:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getManufacturerCode());
						break;	
					case x8B:						
						setBusinessFacilityCode(EchonetDataConverter.dataToString(resultData));
						String setBusinessFacilityCode= String.format("Node:%s@EOJ:%s {EPC:0x8B, EDT: 0x%02X}=={Business Facility Code:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getBusinessFacilityCode());
						break;
					case x8C:
						setProductCode(EchonetDataConverter.dataToString(resultData));
						String setProductCode =String.format("Node:%s@EOJ:%s {EPC:0x8C, EDT: 0x%02X}=={Product Code:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getProductCode());
						break;
					case x8D:
						setProductNumber(EchonetDataConverter.dataToString(resultData));
						String setProductNumber = String.format("Node:%s@EOJ:%s {EPC:0x8D, EDT: 0x%02X}=={Product Number:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getProductNumber());
						break;		
					case x8E:
						setProductDate(EchonetDataConverter.dataToDate(resultData));
						String setProductDate = String.format("Node:%s@EOJ:%s {EPC:0x8E, EDT: 0x%02X}=={Production Date:%tA}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getProductDate());
						break;
					case x8F:				
						if(EchonetDataConverter.dataToInteger(resultData) == 65) {
							setPowerSaving(true);
						} else {
							setPowerSaving(false);
						}
						String setPowerSaving = String.format("Node:%s@EOJ:%s {EPC:0x8F, EDT: 0x%02X}=={PowerSaving Mode:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],isPowerSaving());
						break;
					case x93:
						if(EchonetDataConverter.dataToInteger(resultData) == 65) {
							setThroughPublicNetwork(true);
						} else {
							setThroughPublicNetwork(false);
						}
						String setThroughPublicNetwork = String.format("Node:%s@EOJ:%s {EPC:0x93, EDT: 0x%02X}=={isThrough Public Network:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],isThroughPublicNetwork());
						break;
					case x97:
						setCurrentTimeSetting(EchonetDataConverter.dataToTime(resultData));		
						String setCurrentTimeSetting = String.format("Node:%s@EOJ:%s {EPC:0x97, EDT: 0x%02X}=={Current Time Setting:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getCurrentTimeSetting());
						break;
					case x98:
						setCurrentDateSetting(EchonetDataConverter.dataToDate(resultData));
						String setCurrentDateSetting = String.format("Node:%s@EOJ:%s {EPC:0x98, EDT: 0x%02X}=={Current Date Setting:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getCurrentDateSetting());
						break;
					case x99:
						setPowerLimit(EchonetDataConverter.dataToShort(resultData));
						String setPowerLimit = String.format("Node:%s@EOJ:%s {EPC:0x99, EDT: 0x%02X}=={Power Limit:%d}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getPowerLimit());
						break;
					case x9A:
						setCumulativeTime(EchonetDataConverter.dataToCummalativeTime(resultData));
						String setCumulativeTime = String.format("Node:%s@EOJ:%s {EPC:0x9A, EDT: 0x%02X}=={Up Time:%s}",
								 getNode().getNodeInfo().toString(),eoj.toString(),resultData.toBytes()[0],getCumulativeTime());
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

	public void ParseProfileObjectFromEPC(Service service) {
		timer = new Timer(true);
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				getData(service);
			}
		}, 0, 30000);	
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof eSuperClass))
			return false;
		if (obj == this)
			return true;
		eSuperClass checkObj = (eSuperClass) obj;
		if (!compareObject(this.node, checkObj.node))
			return false;
		if (!compareObject(this.deviceID, checkObj.deviceID))
			return false;
		if (!compareObject(this.groupCode, checkObj.groupCode))
			return false;
		if (!compareObject(this.classCode, checkObj.classCode))
			return false;
		if (!compareObject(this.instanceCode, checkObj.instanceCode))
			return false;
		if (!compareObject(this.installLocation, checkObj.installLocation))
			return false;
		if (!compareObject(this.standardVersionInfo, checkObj.standardVersionInfo))
			return false;
		if (!compareObject(this.identificationNumber, checkObj.identificationNumber))
			return false;
		if (!compareObject(this.instantaneousPower, checkObj.instantaneousPower))
			return false;
		if (!compareObject(this.cumulativePower, checkObj.cumulativePower))
			return false;
		if (!compareObject(this.manufacturerFaultCode, checkObj.manufacturerFaultCode))
			return false;
		if (!compareObject(this.currentLimitSetting, checkObj.currentLimitSetting))
			return false;
		if (!compareObject(this.faultStatus, checkObj.faultStatus))
			return false;
		if (!compareObject(this.faultDescription, checkObj.faultDescription))
			return false;
		if (!compareObject(this.manufacturerCode, checkObj.manufacturerCode))
			return false;
		if (!compareObject(this.businessFacilityCode, checkObj.businessFacilityCode))
			return false;
		if (!compareObject(this.productCode, checkObj.productCode))
			return false;
		if (!compareObject(this.productNumber, checkObj.productNumber))
			return false;
		if (!compareObject(this.productDate, checkObj.productDate))
			return false;
		if (!compareObject(this.powerSaving, checkObj.powerSaving))
			return false;
		if (!compareObject(this.throughPublicNetwork, checkObj.throughPublicNetwork))
			return false;
		if (!compareObject(this.currentDateSetting, checkObj.currentDateSetting))
			return false;
		/*if (!compareObject(this.currentTimeSetting, checkObj.currentTimeSetting))
			return false;*/
		if (!compareObject(this.powerLimit, checkObj.powerLimit))
			return false;
		if (!compareObject(this.cumulativeTime, checkObj.cumulativeTime))
			return false;
		return true;
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

	@SuppressWarnings("deprecation")
	public <T> boolean compareObject(T obj1, T obj2) {

		if (obj1 == null && obj2 == null)
			return true;
		else if (obj1 == null && obj2 != null)
			return false;
		else if (obj1 != null && obj2 == null)
			return false;
		else {
			if (obj1.getClass() != obj2.getClass()) {
				return false;
			} else {
				if (obj1.getClass().isPrimitive()) {
					if (obj1 == obj2)
						return true;
					else {
						return false;
					}
				} else {
					if ((obj1 instanceof Date)) {
						Date date1 = (Date) obj1;
						Date date2 = (Date) obj2;

						return (date1.getDate() == date2.getDate() && date1.getMonth() == date2.getMonth()
								&& date1.getYear() == date2.getYear());
					} else {
						return obj1.equals(obj2);
					}
				}
			}
		}
	}
	
	public void attach(DataChangeObserver observer) {
		observers.add(observer);
	}
	public void notifyDataChanged(Object obj, String property) {
		for(DataChangeObserver ob : observers) {
			ob.dataUpdated(obj, property);
		}
	}
	public String getDeviceName() {
		return deviceID;
	}
	public void setDeviceName(String deviceName) {
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
	public boolean isOperationStatus() {
		return operationStatus;
	}
	public void setOperationStatus(boolean operationStatus) {
		if(this.isOperationStatus() != operationStatus) {
			this.operationStatus = operationStatus;
			notifyDataChanged(this, EchonetSuperDevice.PROPERTY_HAS_OPERATION_STATUS);
		}
		
	}
	public String getInstallLocation() {
		return installLocation;
	}
	public void setInstallLocation(String installLocation) {
		if(!installLocation.equals(this.getInstallLocation())) {
			this.installLocation = installLocation;
			notifyDataChanged(this, EchonetSuperDevice.PROPERTY_HAS_INSTALLATION_LOCATION);
		}
		
	}
	public String getStandardVersionInfo() {
		return standardVersionInfo;
	}
	public void setStandardVersionInfo(String standardVersionInfo) {
		if(!standardVersionInfo.equals(this.getStandardVersionInfo())) {
			this.standardVersionInfo = standardVersionInfo;
			notifyDataChanged(this, EchonetSuperDevice.PROPERTY_STANDARD_VERSION_INFORMATION);
		}		
	}
	public String getIdentificationNumber() {
		return identificationNumber;
	}
	public void setIdentificationNumber(String identificationNumber) {
		if(!identificationNumber.equals(this.getIdentificationNumber())) {
			this.identificationNumber = identificationNumber;
			notifyDataChanged(this, EchonetSuperDevice.PROPERTY_HAS_IDENTIFICATION_NUMBER);
		}
		
	}
	public short getInstantaneousPower() {
		return instantaneousPower;
	}
	public void setInstantaneousPower(short instantaneousPower) {
		if(this.getInstantaneousPower() != instantaneousPower) {
			this.instantaneousPower = instantaneousPower;
			notifyDataChanged(this, EchonetSuperDevice.PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION);
		}
		
	}
	public long getCumulativePower() {
		return cumulativePower;
	}
	public void setCumulativePower(long cumulativePower) {
		if(this.getCumulativePower() != cumulativePower) {
			this.cumulativePower = cumulativePower;
			notifyDataChanged(this, EchonetSuperDevice.PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION);
		}
		
	}
	public String getManufacturerFaultCode() {
		return manufacturerFaultCode;
	}
	public void setManufactureerFaultCode(String manufactureerFaultCode) {
		if(!manufactureerFaultCode.equals(this.getManufacturerFaultCode())) {
			this.manufacturerFaultCode = manufactureerFaultCode;
			notifyDataChanged(this, EchonetSuperDevice.PROPERTY_HAS_MANUFACTURER_FAULT_CODE);
		}
		
	}
	public int getCurrentLimitSetting() {
		return currentLimitSetting;
	}
	public void setCurrentLimitSetting(int currentLimitSetting) {
		if(this.getCurrentLimitSetting()!=currentLimitSetting) {
			this.currentLimitSetting = currentLimitSetting;
			notifyDataChanged(this, EchonetSuperDevice.PROPERTY_HAS_CURRENT_LIMIT_SETTING);
		}
		
	}
	public boolean isFaultStatus() {
		return faultStatus;
	}
	public void setFaultStatus(boolean faultStatus) {
		if(this.isFaultStatus() != faultStatus) {
			this.faultStatus = faultStatus;
			notifyDataChanged(this, EchonetSuperDevice.PROPERTY_HAS_FAULT_STATUS);
		}
		
	}
	public String getFaultDescription() {
		return faultDescription;
	}
	public void setFaultDescription(String faultDescription) {
		if(!faultDescription.equals(this.getFaultDescription())) {
			this.faultDescription = faultDescription;
			notifyDataChanged(this, EchonetSuperDevice.PROPERTY_HAS_FAULT_DESCRIPTION);
		}
		
	}
	public String getManufacturerCode() {
		return manufacturerCode;
	}
	public void setManufacturerCode(String manufacturerCode) {
		if(!manufacturerCode.equals(this.getManufacturerCode())) {
			this.manufacturerCode = manufacturerCode;
			notifyDataChanged(this, EchonetSuperDevice.PROPERTY_HAS_MANUFACTURER_CODE);
		}		
	}
	public String getBusinessFacilityCode() {
		return businessFacilityCode;
	}
	public void setBusinessFacilityCode(String businessFacilityCode) {
		if(!businessFacilityCode.equals(this.getBusinessFacilityCode())) {
			this.businessFacilityCode = businessFacilityCode;
			notifyDataChanged(this, EchonetSuperDevice.PROPERTY_HAS_BUSINESS_FACILITY_CODE);
		}	
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		if(!productCode.equals(this.getProductCode())) {
			this.productCode = productCode;
			notifyDataChanged(this, EchonetSuperDevice.PROPERTY_HAS_PRODUCT_CODE);
		}	
	}
	public String getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(String productNumber) {
		if(!productNumber.equals(this.getProductNumber())) {
			this.productNumber = productNumber;
			notifyDataChanged(this, EchonetSuperDevice.PROPERTY_HAS_PRODUCTION_NUMBER);
		}
		
	}
	public Date getProductDate() {
		return productDate;
	}
	public void setProductDate(Date productDate) {
		if(!productDate.equals(this.getProductDate())) {
			this.productDate = productDate;
			notifyDataChanged(this, EchonetSuperDevice.PROPERTY_HAS_PRODUCTION_DATE);
		}
		
	}
	public boolean isPowerSaving() {
		return powerSaving;
	}
	public void setPowerSaving(boolean powerSaving) {
		if(this.isPowerSaving()!=powerSaving) {
			this.powerSaving = powerSaving;
			notifyDataChanged(this, EchonetSuperDevice.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING);
		}
		
	}
	public boolean isThroughPublicNetwork() {
		return throughPublicNetwork;
	}
	public void setThroughPublicNetwork(boolean throughPublicNetwork) {
		if(this.isThroughPublicNetwork()!=throughPublicNetwork) {
			this.throughPublicNetwork = throughPublicNetwork;
			notifyDataChanged(this, EchonetSuperDevice.PROPERTY_HAS_REMOTE_CONTROL_SETTING);
		}
		
	}
	public String getCurrentTimeSetting() {
		return currentTimeSetting;
	}
	public void setCurrentTimeSetting(String currentTimeSetting) {
		if(!currentTimeSetting.equals(this.getCurrentTimeSetting())) {
			this.currentTimeSetting = currentTimeSetting;
			notifyDataChanged(this, EchonetSuperDevice.PROPERTY_HAS_CURRENT_TIME_SETTING);
		}
		
	}
	public Date getCurrentDateSetting() {
		return currentDateSetting;
	}
	public void setCurrentDateSetting(Date currentDateSetting) {
		if(!currentDateSetting.equals(this.getCurrentDateSetting())) {
			this.currentDateSetting = currentDateSetting;
			notifyDataChanged(this, EchonetSuperDevice.PROPERTY_HAS_CURRENT_DATE_SETTING);
		}	
	}
	public short getPowerLimit() {
		return powerLimit;
	}
	public void setPowerLimit(short powerLimit) {
		if(this.getPowerLimit()!=powerLimit) {
			this.powerLimit = powerLimit;
			notifyDataChanged(this, EchonetSuperDevice.PROPERTY_HAS_POWER_LIMIT_SETTING);
		}
		
	}
	public String getCumulativeTime() {
		return cumulativeTime;
	}
	public void setCumulativeTime(String cumulativeTime) {
		if(!cumulativeTime.equals(this.getCumulativeTime())) {
			this.cumulativeTime = cumulativeTime;
			notifyDataChanged(this, EchonetSuperDevice.PROPERTY_HAS_CUMULATIVE_OPERATING_TIME);
		}
	}

	public EOJ getEoj() {
		return eoj;
	}

	public void setEoj(EOJ eoj) {
		this.eoj = eoj;
	}
	

}
