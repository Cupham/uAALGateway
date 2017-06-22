package echonet.objects;


import utils.EchonetDataConverter;
import echowand.common.EPC;
import echowand.common.PropertyMap;
import echowand.object.EchonetObjectException;
import echowand.object.ObjectData;
import echowand.object.RemoteObject;

public class eTemperatureSensor extends eDataObject{
	/**
	 * EPC: 0xE0 Measured temperature value in units of 0.1 Celcius Value
	 * between: 0xF554–0x7FFE (-2732–32766)~(-273.2–3276.6 Celcius)
	 */
	private float temperature;
	

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
		float temperatureValue = 0;
		this.instanceCode = rObj.getEOJ().getInstanceCode();

		if (rObj.isGettable(EPC.xE0)) { // temperature
			ObjectData data =rObj.getData(EPC.xE0);
					
			temperatureValue = EchonetDataConverter.dataToFloat(data)/10;
			System.out.println(String.format("   			{EPC:0xE0, EDT: 0x%02X%02X}=={Temperature:%.2f}",data.toBytes()[0],data.toBytes()[1],temperatureValue));
			//System.out.println("   			{EPC:0xE0, EDT:"+data.toBytes()[0]+"}=={Temperature:"+temperatureValue+"}");
		}
		this.temperature = temperatureValue;
		return;	
	}

	@Override
	public String ToString() {
		StringBuilder rs = new StringBuilder();
		rs.append("Instance: " +
				String.format("%02x", this.getInstanceCode())+"\r\n");
		rs.append("\tStatus: "+((isOperationStatus())?"ON":"OFF")+"\r\n");
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
