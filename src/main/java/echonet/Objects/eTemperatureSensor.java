package echonet.Objects;


import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.ontology.echonetontology.EchonetSuperDevice;
import org.universAAL.ontology.echonetontology.sensorRelatedDevices.TemperatureSensor;

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
import uAAL.ContextBus.CPublisher;
import utils.EchonetDataConverter;

public class eTemperatureSensor extends eDataObject{
	private static final Logger LOGGER = Logger.getLogger(eTemperatureSensor.class.getName());
	/**
	 * EPC: 0xE0 Measured temperature value in units of 0.1 Celcius Value
	 * between: 0xF554–0x7FFE (-2732–32766)~(-273.2–3276.6 Celcius)
	 */
	private float temperature;
	private Timer timer;
	private TemperatureSensor sensor;
	

	public eTemperatureSensor(EOJ eoj, Node node) {
		super(node, eoj);
		this.groupCode= (byte) 0x00;
		this.classCode = (byte) 0x11;
		this.instanceCode = eoj.getInstanceCode();
		sensor = new TemperatureSensor(TemperatureSensor.MY_URI+getDeviceID());
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
	public void refreshTemperature(float temperature) {
		if(this.getTemperature() != temperature) {
			this.temperature = temperature;
			sensor.changeProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE, new Float(temperature));
			Activator.cPublisher.publicContextEvent(new ContextEvent(sensor,TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE));
		}
		
	}
	
	
	public boolean isOperationStatus() {
		return isOperationStatus();
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
							refreshOperationStatus(true);
						} else {
							refreshOperationStatus(false);
						}
						LOGGER.info(String.format("Temperature Sensor:%s {EPC:0x80, EDT: 0x%02X}=={OperationStatus:%s}",
								 getNode().getNodeInfo().toString(),resultData.toBytes()[0],isOperationStatus()));	
						break;
					case xE0:
						float temperatureValue = EchonetDataConverter.dataToFloat(resultData)/10;
						refreshTemperature(temperatureValue);
						LOGGER.info(String.format("Temperature Sensor:%s {EPC:0xE0, EDT: 0x%02X%02X}=={Temperature:%.2f}",
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
		}, 0, 5000);	
	}
	@Override
	public String TouAALReponse() {
		return String.format("%.2f",getTemperature());
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
			LOGGER.info(String.format("Temperature Sensor is already in %s nothing to do", location));
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
}
