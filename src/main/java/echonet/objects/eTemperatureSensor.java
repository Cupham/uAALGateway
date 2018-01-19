package echonet.objects;


import utils.EchonetDataConverter;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import echowand.common.EOJ;
import echowand.common.EPC;
import echowand.net.Node;
import echowand.net.SubnetException;
import echowand.service.Service;
import echowand.service.result.GetListener;
import echowand.service.result.GetResult;
import echowand.service.result.ResultData;
import echowand.service.result.ResultFrame;
import ontologies.TemperatureSensor;

public class eTemperatureSensor extends eDataObject{
	/**
	 * EPC: 0xE0 Measured temperature value in units of 0.1 Celcius Value
	 * between: 0xF554–0x7FFE (-2732–32766)~(-273.2–3276.6 Celcius)
	 */
	private float temperature;
	private boolean operationStatus;
	private Timer timer;
	

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
	public eTemperatureSensor(EOJ eoj, Node node) {
		super(node, eoj);
		this.groupCode= (byte) 0x00;
		this.classCode = (byte) 0x11;
		this.instanceCode = eoj.getInstanceCode();
	}
	
	public eTemperatureSensor(byte groupCode, byte classCode) {
		super();
		this.groupCode= groupCode;
		this.classCode = classCode;
	}
	
	

	@Override
	public String ToString() {
		StringBuilder rs = new StringBuilder();
		rs.append("Instance: " +
				String.format("%02x", this.getInstanceCode())+"\r\n");
		rs.append("\tStatus: "+((isOperationStatus())?"ON":"OFF")+"\r\n");
		rs.append("\tTemperature: "+this.getTemperature()+"*C"+"\r\n");
		rs.append("\tInstallation Location: "+this.getInstallLocation());
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
		if(this.getTemperature() != temperature) {
			System.out.println("tem changed\n\n\n\n");
			this.temperature = temperature;
			notifyDataChanged(this, TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE);
		}
		
	}
	
	
	public boolean isOperationStatus() {
		return operationStatus;
	}
	public void setOperationStatus(boolean operationStatus) {
		if(this.isOperationStatus() != operationStatus) {
			this.operationStatus = operationStatus;
			notifyDataChanged(this, TemperatureSensor.PROPERTY_HAS_OPERATION_STATUS);
		}
		
	}
	private void getData(Service service){
		LinkedList<EPC> epcs = new LinkedList<EPC>();
		epcs.add(EPC.x80);
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
							setOperationStatus(true);
						} else {
							setOperationStatus(false);
						}
						System.out.println(String.format("Temperature Sensor:%s {EPC:0x80, EDT: 0x%02X}=={OperationStatus:%s}",
								 getNode().getNodeInfo().toString(),resultData.toBytes()[0],isOperationStatus()));	
						break;
					case xE0:
						float temperatureValue = EchonetDataConverter.dataToFloat(resultData)/10;
						setTemperature(temperatureValue);
						System.out.println(String.format("Temperature Sensor:%s {EPC:0xE0, EDT: 0x%02X%02X}=={Temperature:%.2f}",
								 getNode().getNodeInfo().toString(),resultData.toBytes()[0],resultData.toBytes()[1],getTemperature()));	
						break;
						
					default:
						System.out.println("Something happended!!");
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
		timer = new Timer(true);
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				getData(service);
			}
		}, 3000, 10000);	
	}
	
}
