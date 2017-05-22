package echonet.objects;


import utils.EchonetDataConverter;
import echowand.common.EPC;
import echowand.object.EchonetObjectException;
import echowand.object.RemoteObject;

public class eTemperatureSensor extends eDataObject{
	/**
	 * EPC: 0xE0 Measured temperature value in units of 0.1 Celcius Value
	 * between: 0xF554–0x7FFE (-2732–32766)~(-273.2–3276.6 Celcius)
	 */
	private float temperature;
	private eProfileObject profile;
	
	public eProfileObject getProfile() {
		return profile;
	}
	public void setProfile(eProfileObject profile) {
		this.profile = profile;
	}
	public eTemperatureSensor() {
		super();
		this.groupCode= (byte) 0x00;
		this.classCode = (byte) 0x11;
	}
	public eTemperatureSensor(byte instanceCode) {
		super();
		this.groupCode= (byte) 0x00;
		this.classCode = (byte) 0x11;
		this.instanceCode = instanceCode;
	}
	
	public eTemperatureSensor(byte groupCode, byte classCode) {
		super();
		this.groupCode= groupCode;
		this.classCode = classCode;
	}
	
	public eTemperatureSensor(boolean operationStatus, float temp) {
		super(operationStatus);
		this.groupCode = (byte) 0x00;
		this.classCode = (byte) 0x11;
		this.temperature = temp;
	}
	
	
	@Override
	public void ParseDataFromRemoteObject(RemoteObject rObj)
			throws EchonetObjectException {
		
		boolean status = true;
		float temperatureValue = 0;
		this.instanceCode = rObj.getEOJ().getInstanceCode();
		if (rObj.contains(EPC.x80)) { // operation status
			status = (EchonetDataConverter.dataToInteger(rObj.getData(EPC.x80)) == 48) ? true : false;
		}

		if (rObj.contains(EPC.xE0)) { // temperature
			temperatureValue = EchonetDataConverter.dataToFloat(rObj.getData(EPC.xE0))/10;
		}
		this.operationStatus = status;
		this.temperature = temperatureValue;
		return;	
	}

	@Override
	public String ToString() {
		StringBuilder rs = new StringBuilder();
		rs.append("Instance: " +
				String.format("%02x", this.getInstanceCode())+"\r\n");
		rs.append("\tStatus: "+((this.operationStatus)?"ON":"OFF")+"\r\n");
		rs.append("\tTemperature: "+this.temperature+"*C");
		return rs.toString();
	}

	/**
	 * @return the temperature
	 */
	public float getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature
	 * the temperature to set
	 */
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

}
