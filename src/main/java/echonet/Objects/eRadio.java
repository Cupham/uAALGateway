package echonet.Objects;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.universAAL.ontology.echonetontology.managementOperationRelatedDevices.Switch;

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

public class eRadio extends eDataObject{
	private static final Logger LOGGER = Logger.getLogger(eRadio.class.getName());
	private boolean operationStatus;
	private Timer timer;
	

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
	}
	



	
	public boolean isOperationStatus() {
		return operationStatus;
	}
	public void setOperationStatus(boolean operationStatus) {
		if(operationStatus != isOperationStatus()) {
			this.operationStatus = operationStatus;
			notifyDataChanged(this, Switch.PROPERTY_HAS_OPERATION_STATUS);
		}
		
	}
	private void getData(Service service){
		try {
			service.doGet(node, eoj, EPC.x80, 5000, new GetListener() {
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
						String setOperationStatusLog = String.format("Radio:%s {EPC:0x80, EDT: 0x%02X}=={OperationStatus:%s}",
								 getNode().getNodeInfo().toString(),resultData.toBytes()[0],isOperationStatus());	
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
		return String.format("%s", isOperationStatus());
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
	public boolean setOn() {
		boolean rs = false;
		if(isOperationStatus()) {
			LOGGER.info("Radio is already ON! nothing to do");
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
			LOGGER.info("Radio is already OFF! nothing to do");
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
}
