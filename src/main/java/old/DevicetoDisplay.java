package old;

public class DevicetoDisplay {
	private String deviceID;
	private String deviceType;
	private String deviceIcon;
	private String deviceLocation;
	private boolean isON;
	private String deviceData;
	
	
	public DevicetoDisplay() {
		super();
	}
	public DevicetoDisplay(String deviceID, String deviceType,
			String deviceIcon, String deviceLocation, boolean isON,
			String deviceData) {
		super();
		this.deviceID = deviceID;
		this.deviceType = deviceType;
		this.deviceIcon = deviceIcon;
		this.deviceLocation = deviceLocation;
		this.isON = isON;
		this.deviceData = deviceData;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceIcon() {
		return deviceIcon;
	}
	public void setDeviceIcon(String deviceIcon) {
		this.deviceIcon = deviceIcon;
	}
	public String getDeviceLocation() {
		return deviceLocation;
	}
	public void setDeviceLocation(String deviceLocation) {
		this.deviceLocation = deviceLocation;
	}
	public boolean isON() {
		return isON;
	}
	public void setON(boolean isON) {
		this.isON = isON;
	}
	public String getDeviceData() {
		return deviceData;
	}
	public void setDeviceData(String deviceData) {
		this.deviceData = deviceData;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return deviceID + "-" + deviceType +"-" + deviceIcon + 
			   "-" + deviceLocation + "-" + isON + "-" + deviceData;
	}
	
	
}
