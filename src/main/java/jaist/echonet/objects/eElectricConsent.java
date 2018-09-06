package jaist.echonet.objects;

import java.util.Timer;
import java.util.TimerTask;

import org.universAAL.ontology.echonetontology.managementOperationRelatedDevices.Switch;

import echowand.common.EOJ;
import echowand.common.EPC;
import echowand.net.Node;
import echowand.net.SubnetException;
import echowand.service.Service;
import echowand.service.result.GetListener;
import echowand.service.result.GetResult;
import echowand.service.result.ResultData;
import echowand.service.result.ResultFrame;
import jaist.utils.EchonetDataConverter;

public class eElectricConsent extends eDataObject{
	private boolean operationStatus;
	private Timer timer;
	

	public eElectricConsent() {
		super();
		this.groupCode= (byte) 0x00;
		this.classCode = (byte) 0x22;
	}
	public eElectricConsent(byte instanceCode) {
		super();
		this.groupCode= (byte) 0x00;
		this.classCode = (byte) 0x22;
		this.instanceCode = instanceCode;
	}
	public eElectricConsent(EOJ eoj, Node node) {
		super(node, eoj);
		this.groupCode= (byte) 0x00;
		this.classCode = (byte) 0x22;
		this.instanceCode = eoj.getInstanceCode();
	}
	

	@Override
	public String ToString() {
		StringBuilder rs = new StringBuilder();
		rs.append("Instance: " +
				String.format("%02x", this.getInstanceCode())+"\r\n");
		rs.append("\tStatus: "+((isOperationStatus())?"ON":"OFF")+"\r\n");
		rs.append("\tInstallation Location: "+this.getInstallLocation());
		return rs.toString();
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
						String setOperationStatusLog = String.format("Electric Consent:%s {EPC:0x80, EDT: 0x%02X}=={OperationStatus:%s}",
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
		}, 3000, 10000);	
	}
}
