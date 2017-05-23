package services;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import echonet.objects.EchonetLiteDevice;
import echonet.objects.NodeProfileObject;
import echonet.objects.eSuperClass;
import echowand.common.EOJ;
import echowand.common.EPC;
import echowand.common.PropertyMap;
import echowand.logic.TooManyObjectsException;
import echowand.net.Node;
import echowand.net.SubnetException;
import echowand.object.EchonetObjectException;
import echowand.object.ObjectData;
import echowand.object.RemoteObject;
import echowand.service.Service;
import echowand.service.result.GetResult;
import echowand.service.result.ResultData;
import echowand.service.result.UpdateRemoteInfoResult;

public class ScanEchonetDevice {
	private Service service;
	
	public ScanEchonetDevice(Service ser) {
		this.service = ser;
	}
	
	public static EchonetLiteDevice getDeviceResources(Service service, Node node) 
			throws EchonetObjectException, SubnetException {

		/*
		// GetResult getResult = service.doGet(service.getGroupNode(), new EOJ("0ef001"), EPC.xD6, 5000);
		
		try {
			service.doUpdateRemoteInfo(5000).join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (Node node1 : service.getRemoteNodes()) {
			for (EOJ eoj: service.getRemoteEOJs(node)) {
				System.out.println(node1 + " " + eoj);
			}
		}

		
		GetResult getResult = service.doGet(service.getGroupNode(), new EOJ("001100"), EPC.xE0, 5000);
		
		try {
			getResult.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (ResultData resultData : getResult.getDataList()) {
			System.out.println(resultData.node + ": " + resultData.eoj + " " + resultData.data);
		}
		*/
		
		EchonetLiteDevice eDevice = new EchonetLiteDevice();
		List<EOJ> eojs = service.getRemoteEOJs(node);
		for(EOJ eoj : eojs) {
			RemoteObject remoteobject = service.getRemoteObject(node, eoj);
			if (eoj.isNodeProfileObject()) {
				/*
				Node node = remoteobject.getNode();
				EOJ eoj = remoteobject.getEOJ();
				service.doGet(node, eoj, Arrays.asList(EPC.x80, EPC.x81, EPC.xE0), timeout);
				*/
				
				NodeProfileObject profileObj = new NodeProfileObject(node.toString(),node.getNodeInfo().toString());
				
				try {
					profileObj.ParseProfileObjectFromEPC(remoteobject);
				} catch (EchonetObjectException ex) {
					ex.printStackTrace();
				}
				/*
				
				ObjectData data = remoteobject.getData(EPC.x9F);
				
				PropertyMap propertyMap = new PropertyMap(data.toBytes());
				for (int i=0x80; i<=0xff; i++) {
					EPC epc = EPC.fromByte((byte)i);
					if (propertyMap.isSet(epc)) {
						profileObj.ParseProfileObjectFromEPC(remoteobject);
					}
				}
				*/
					
				/*
				for (int i = 0x80; i < 0xff; i++) {
					EPC epc = EPC.fromByte((byte) i);
						if (remoteobject.isGettable(epc)) {
							profileObj.ParseProfileObjectFromEPC(remoteobject);
						}
				}
				*/
				
				eDevice.setProfileObj(profileObj);
			} else if (eoj.isDeviceObject()) {
				try {
					eDevice.addDataObject(eoj, remoteobject);
				} catch (EchonetObjectException ex) {
					ex.printStackTrace();
				}
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

		UpdateRemoteInfoResult remoteResult = service.doUpdateRemoteInfo(5000);
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
