package jaist.echonet.objects;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import org.universAAL.ontology.echonetontology.housingFacilitiesRelatedDevices.GeneralLighting;

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

public class eLighting extends eDataObject{
	
	private int illuminateLevel;
	private boolean operationStatus;
	Timer timer;
	
	
	public int getIlluminateLevel() {
		return illuminateLevel;
	}
	public void setIlluminateLevel(int illuminateLevel) {
		if(this.getIlluminateLevel() != illuminateLevel) {
			this.illuminateLevel = illuminateLevel;
			notifyDataChanged(this, GeneralLighting.PROPERTY_HAS_ILLUMINANCE_LEVEL);
		}
	}
	
	public boolean isOperationStatus() {
		return operationStatus;
	}
	public void setOperationStatus(boolean operationStatus) {
		if(operationStatus != isOperationStatus()) {
			this.operationStatus = operationStatus;
			notifyDataChanged(this, GeneralLighting.PROPERTY_HAS_OPERATION_STATUS);
		}
	}
	public eLighting() {
		super();
		this.groupCode= (byte)0x02;
		this.classCode=(byte)0x90;
	}
	public eLighting(EOJ eoj, Node node) {
		super(node,eoj);
		this.groupCode= (byte) 0x02;
		this.classCode = (byte) 0x90;
		this.instanceCode = eoj.getInstanceCode();
	}
	
	@Override
	public String ToString() {
		StringBuilder rs = new StringBuilder();
		rs.append("Operation Status: " + (this.isOperationStatus() ? "ON" : "OFF"));
		rs.append("Illuminance level: " + this.illuminateLevel + " %");
		return null;
	}
	public void getData(Service service){
		LinkedList<EPC> epcs = new LinkedList<EPC>();
		epcs.add(EPC.x80);
		epcs.add(EPC.xB0);
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
						String setOperationStatusLog= String.format("Lighting:%s {EPC:0x80, EDT: 0x%02X}=={OperationStatus:%s}",
								 getNode().getNodeInfo().toString(),resultData.toBytes()[0],isOperationStatus());	
						break;
					case xB0:
						int level = EchonetDataConverter.dataToInteger(resultData);
						setIlluminateLevel(level);
						String setIlluminateLevelLog = String.format("Lighting:%s {EPC:0xB0, EDT: 0x%02X}=={Illumination Level = :%d}",
								 getNode().getNodeInfo().toString(),resultData.toBytes()[0],getIlluminateLevel());	
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
