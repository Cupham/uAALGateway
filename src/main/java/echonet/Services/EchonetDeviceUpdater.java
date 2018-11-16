package echonet.Services;

import java.net.SocketException;

import echowand.common.EOJ;
import echowand.common.EPC;
import echowand.logic.TooManyObjectsException;
import echowand.net.Node;
import echowand.net.SubnetException;
import echowand.object.EchonetObjectException;
import echowand.object.ObjectData;
import echowand.object.RemoteObject;
import echowand.service.ObjectNotFoundException;
import echowand.service.Service;

public class EchonetDeviceUpdater {
	private Service service;
	
	public EchonetDeviceUpdater(Service service) {
		this.service = service;
	}
//	public synchronized boolean updateDeviceAttribute(String nodeIP, EOJ eoj, EPC epc, ObjectData value)
//			throws SocketException, SubnetException, TooManyObjectsException, EchonetObjectException,
//			ObjectNotFoundException, InterruptedException {
//
//		System.out.println(String.format("[UPDATE] Start updating device: IP: %s" + ", EOJ:%s, EPC:%s, Value: %s",
//				nodeIP, eoj.toString(), epc.toString(), value.toString()));
//		long startTime = System.currentTimeMillis();
//		// update execute
//		Node node = service.getRemoteNode(nodeIP);
//		service.registerRemoteEOJ(node, eoj);
//        
//        RemoteObject remoteObject = service.getRemoteObject(node, eoj);
//		if (remoteObject.setData(epc, value)) {
//			long updateTime = System.currentTimeMillis() - startTime;
//			System.out.println("[UPDATE] Time update device: " + updateTime + " ms");
//			return true;
//		}
//		return false;
//	}
//	
//	public boolean setDevice(String nodeIP, EOJ eoj, EPC epc, ObjectData value)
//			throws SocketException, SubnetException, TooManyObjectsException, EchonetObjectException,
//			ObjectNotFoundException, InterruptedException {
//
//		System.out.println(String.format("[UPDATE] Start updating device: IP: %s" + ", EOJ:%s, EPC:%s, Value: %s",
//				nodeIP, eoj.toString(), epc.toString(), value.toString()));
//		long startTime = System.currentTimeMillis();
//		// update execute
//		Node node = service.getRemoteNode(nodeIP);
//		if (service.setRemoteData(node, eoj, epc, value)) {
//			long updateTime = System.currentTimeMillis() - startTime;
//			System.out.println("[UPDATE] Time update device: " + updateTime + " ms");
//			return true;
//		}
//		return false;
//	}

}
