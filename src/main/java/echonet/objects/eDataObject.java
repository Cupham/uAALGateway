package echonet.objects;

import echowand.common.EOJ;
import echowand.net.Node;
import echowand.object.EchonetObjectException;
import echowand.object.RemoteObject;
import echowand.service.Service;

/**
 * @author Cu Pham
 *
 */
public abstract class eDataObject extends eSuperClass{
	public eDataObject() {
		super();
	}
	public eDataObject(Node node, EOJ eoj) {
		super(node,eoj);
	}

	/**
	 * TODO: get data from EPC
	 * @param rObj
	 * @throws EchonetObjectException 
	 */
	public abstract void ParseDataFromEOJ(Service service);
	
	/**
	 * TODO: parse this data to string
	 * @return
	 */
	public abstract String ToString();
	
}