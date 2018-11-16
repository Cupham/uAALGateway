package echonet.Objects;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.universAAL.ontology.device.CurtainController;

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

public class eSwitch extends eDataObject{
	private static final Logger LOGGER = Logger.getLogger(eSwitch.class.getName());
	private boolean status;
	Timer timer;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean operationStatus) {
		if(operationStatus != isStatus()) {
			this.status = operationStatus;
		}
	}
	public eSwitch() {
		super();
		this.groupCode= (byte)0x05;
		this.classCode=(byte)0xfd;
	}
	public eSwitch(EOJ eoj, Node node) {
		super(node,eoj);
		this.groupCode= (byte) 0x05;
		this.classCode = (byte) 0xfd;
		this.instanceCode = eoj.getInstanceCode();
	}
	
	
	public void getData(Service service){
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
						if(eoj.getInstanceCode() == (byte)0x01 && EchonetDataConverter.dataToInteger(resultData) == 48) {
							setStatus(true);
						} else if(eoj.getInstanceCode() == (byte)0x02 && EchonetDataConverter.dataToInteger(resultData) == 48) {
							setStatus(false);
						} else if(EchonetDataConverter.dataToInteger(resultData) != 48) {
							setStatus(false);
						}
						String setStatusLog = String.format("Switch:%s {EPC:0x80, EDT: 0x%02X}=={Status:%s}",
								 getNode().getNodeInfo().toString(),resultData.toBytes()[0],isStatus()? "ON":"OFF");	
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
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean executeCommand(EPC epc,EOJ eoj, ObjectData data) {
		boolean rs = false;
		Activator.echonetService.registerRemoteEOJ(this.node, eoj);
		RemoteObject remoteObject = Activator.echonetService.getRemoteObject(node, eoj);
		LOGGER.info(String.format("Execute command [IP:%s, EOJ:%s, Data:%s]",this.node.getNodeInfo().toString(),eoj,data));
		try {
			if (remoteObject.setData(epc, data)) {
				rs= true;
				LOGGER.info(String.format("Completed: [IP:%s, EOJ:%s, Data:%s]",this.node.getNodeInfo().toString(),eoj,data));
			}
		} catch (EchonetObjectException e) {
			LOGGER.error("Can not find object: " +e.toString());
			rs= false;
		}
		return rs;
	}
	public boolean setOn() {
		boolean rs = false;
		if(getOperationStatus()) {
			LOGGER.info("SWITCH is already ON! nothing to do");
			rs = true;
		} else {
			EOJ eoj = new EOJ((byte)0x05,(byte)0xfd,(byte)0x01);
			if(executeCommand(EPC.x80, eoj,new ObjectData((byte) 0x30))) {
				refreshOperationStatus(true);
				rs= true;
			} else {
				rs = false;
			}
		}
		return rs;
	}
	public boolean close() {
		boolean rs = false;
		if(!getOperationStatus()) {
			LOGGER.info("SWITCH is already OFF! nothing to do");
			rs = true;
		} else {
			EOJ eoj = new EOJ((byte)0x05,(byte)0xfd,(byte)0x02);
			if(executeCommand(EPC.x80,eoj, new ObjectData((byte) 0x30))) {
				refreshOperationStatus(false);
				rs= true;
			} else {
				rs = false;
			}
		}
		return rs;
	}


}