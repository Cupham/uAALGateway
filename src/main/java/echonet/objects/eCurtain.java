package echonet.objects;

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
import ontologies.Curtain;
import utils.EchonetDataConverter;

public class eCurtain extends eDataObject{
	private boolean status;
	Timer timer;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean operationStatus) {
		if(operationStatus != isStatus()) {
			System.out.println("Status changed\n\n\n\n");
			this.status = operationStatus;
			notifyDataChanged(this, Curtain.PROPERTY_HAS_STATUS);
		}
	}
	public eCurtain() {
		super();
		this.groupCode= (byte)0x05;
		this.classCode=(byte)0xfd;
	}
	public eCurtain(EOJ eoj, Node node) {
		super(node,eoj);
		this.groupCode= (byte) 0x05;
		this.classCode = (byte) 0xfd;
		this.instanceCode = eoj.getInstanceCode();
	}
	
	@Override
	public String ToString() {
		StringBuilder rs = new StringBuilder();
		rs.append("Status: " + (this.isOperationStatus() ? "OPEN" : "CLOSED"));
		return null;
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
						System.out.println(String.format("Curtain:%s {EPC:0x80, EDT: 0x%02X}=={Status:%s}",
								 getNode().getNodeInfo().toString(),resultData.toBytes()[0],isStatus()? "OPEN":"CLOSE"));	
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
		}, 0, 10000);	
	}


}
