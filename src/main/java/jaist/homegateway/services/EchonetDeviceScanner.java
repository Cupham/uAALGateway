package jaist.homegateway.services;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import jaist.echonet.objects.EchonetLiteDevice;
import jaist.echonet.objects.NodeProfileObject;
import jaist.echonet.objects.eSuperClass;

public class EchonetDeviceScanner {
	private Service service;
	
	public EchonetDeviceScanner(Service service) {
		this.service = service;
	}
	/*
	public static EchonetLiteDevice getDeviceResources(Service service, Node node) 
			throws EchonetObjectException, SubnetException {

		
		EchonetLiteDevice eDevice = new EchonetLiteDevice();
		List<EOJ> eojs = service.getRemoteEOJs(node);
		for(EOJ eoj : eojs) {
			RemoteObject remoteobject = service.getRemoteObject(node, eoj);
			if (eoj.isNodeProfileObject()) {
				// TODO: Replace remoteObject by doGet to reduce the time.

				
				NodeProfileObject profileObj = new NodeProfileObject(node,node.getNodeInfo().toString());
				
				try {
					System.out.println("   		Translating ECHONET Profile object...");
					profileObj.ParseProfileObjectFromEPC(remoteobject);
				} catch (EchonetObjectException ex) {
					ex.printStackTrace();
				}			
				eDevice.setProfileObj(profileObj);
			} else if (eoj.isDeviceObject()) {
				try {
					System.out.println("   		Translating ECHONET Device object...");
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
	*/
	public ArrayList<EchonetLiteDevice> scanEDevices() throws SocketException, SubnetException,
	TooManyObjectsException, InterruptedException, EchonetObjectException {
		long startTime = System.currentTimeMillis();
		ArrayList<EchonetLiteDevice> deviceList = new ArrayList<EchonetLiteDevice>();

		UpdateRemoteInfoResult remoteResult = service.doUpdateRemoteInfo(5000);
		remoteResult.join();

		List<Node> nodes = service.getRemoteNodes(); // list device object
		System.out.println("	Get all ECHONET Lite device resources in iHouse. (" + nodes.size() + 
				" device(s).) " + "within " + (System.currentTimeMillis() - startTime) + " ms.");
		
		long startTime1 = System.currentTimeMillis();
		System.out.println("   		Translating ECHONET Lite Frame to ECHONET objects...");
		/********************************************
		 * Parse device (node)
		 ********************************************/
		for (Node node : nodes) {
			//deviceList.add(getDeviceResources(this.service,node));
		}
		System.out.println("   Finish translating ECHONET Lite frame to ECHONET objects within "+
		(System.currentTimeMillis() - startTime1) + " ms.");
		return deviceList;
	}
}
