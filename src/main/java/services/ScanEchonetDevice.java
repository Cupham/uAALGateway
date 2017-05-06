package services;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import echonet.objects.EchonetLiteDevice;
import echonet.objects.eProfileObject;
import echowand.common.EOJ;
import echowand.common.EPC;
import echowand.logic.TooManyObjectsException;
import echowand.net.Node;
import echowand.net.SubnetException;
import echowand.object.EchonetObjectException;
import echowand.object.RemoteObject;
import echowand.service.Service;
import echowand.service.result.UpdateRemoteInfoResult;

public class ScanEchonetDevice {
	private Service service;
	
	public ScanEchonetDevice(Service ser) {
		this.service = ser;
	}
	
	public static EchonetLiteDevice getDeviceResources(Service service, Node node) 
			throws EchonetObjectException {
		
		EchonetLiteDevice eDevice = new EchonetLiteDevice();
		List<EOJ> eojs = service.getRemoteEOJs(node);
		for(EOJ eoj : eojs) {
			RemoteObject remoteobject = service.getRemoteObject(node, eoj);
			if (eoj.isNodeProfileObject()) {
				eProfileObject profileObj = new eProfileObject(node.toString(),node.getNodeInfo().toString());
	
				for (int i = 0x80; i < 0xff; i++) {
					EPC epc = EPC.fromByte((byte) i);
						if (remoteobject.isGettable(epc)) {
							profileObj.ParseProfileObjectFromEPC(remoteobject);
						}
				}
				eDevice.setProfileObj(profileObj);
			} else if (eoj.isDeviceObject()) {
				eDevice.addDataObject(eoj, remoteobject);
			} else {
				System.out.println("Unknow Echonet Object!!");
			}		
		}
		return eDevice;
	}
	
	public ArrayList<EchonetLiteDevice> scanEDevices() throws SocketException, SubnetException,
	TooManyObjectsException, InterruptedException, EchonetObjectException {
		long startTime = System.currentTimeMillis();
		ArrayList<EchonetLiteDevice> deviceList = new ArrayList<EchonetLiteDevice>();

		UpdateRemoteInfoResult remoteResult = service.doUpdateRemoteInfo(3000);
		remoteResult.join();

		List<Node> nodes = service.getRemoteNodes(); // list device object
		System.out.println("Get all ECHONET Lite device resources in the home network. (" + nodes.size() + 
				" device(s).) " + "within" + (System.currentTimeMillis() - startTime) + " ms.");
		
		long startTime1 = System.currentTimeMillis();
		System.out.println("   Translating ECHONET Lite Frame to uAAL Object...");
		/********************************************
		 * Parse device (node)
		 ********************************************/
		for (Node node : nodes) {
			deviceList.add(getDeviceResources(this.service,node));
		}
		System.out.println("   Finish translating ECHONET Lite to uAAL Object within "+
		(System.currentTimeMillis() - startTime1) + " ms.");
		return deviceList;
	}
}
