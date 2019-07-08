package echonet.Services;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.print.attribute.DocAttributeSet;

import echowand.common.Data;
import echowand.common.EOJ;
import echowand.common.EPC;
import echowand.net.Node;
import echowand.net.SubnetException;
import echowand.object.EchonetObjectException;
import echowand.object.ObjectData;
import echowand.object.RemoteObject;
import echowand.service.Service;
import echowand.service.result.SetListener;
import echowand.util.Pair;

public class CommandExcutor {
	Logger LOGGER = Logger.getLogger(CommandExcutor.class.getName());
	private Service service;
	
	public CommandExcutor() {
		
	}
	public CommandExcutor(Service service) {
		this.service = service;
		
	}
	public boolean executeCommand(Node node, EOJ eoj, EPC epc, ObjectData data) {
		boolean rs = false;
		service.registerRemoteEOJ(node, eoj);
		RemoteObject remoteObject = service.getRemoteObject(node, eoj);
		System.out.println(String.format("Execute command [IP:%s, EOJ:%s, Data:%s]",node.getNodeInfo().toString(),eoj,data));
		LOGGER.info(String.format("Execute command [IP:%s, EOJ:%s, Data:%s]",node.getNodeInfo().toString(),eoj,data));
		try {
			if (remoteObject.setData(epc, data)) {
				rs= true;
				System.out.println(String.format("Completed: [IP:%s, EOJ:%s, Data:%s]",node.getNodeInfo().toString(),eoj,data));
				LOGGER.info(String.format("Completed: [IP:%s, EOJ:%s, Data:%s]",node.getNodeInfo().toString(),eoj,data));
			}
		} catch (EchonetObjectException e) {
			System.out.println("Can not find object: " +e.toString());
			LOGGER.log(Level.SEVERE, "Can not find object: " +e.toString());
			rs= false;
		}
		return rs;
	
	}
	
	public boolean executeCommand1(Node node, EOJ eoj, EPC epc, ObjectData data) {
		boolean rs = false;
		try {
			service.doSet(node, eoj, epc, data.getData(), 5000);
			System.out.println(String.format("Completed: [IP:%s, EOJ:%s, Data:%s]",node.getNodeInfo().toString(),data));
			rs = true;
		} catch (SubnetException e) {
			System.out.println("Can not find object: " +e.toString());
			e.printStackTrace();
		}
		
		return rs;
	
	}

}
