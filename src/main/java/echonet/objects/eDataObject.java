package echonet.objects;

import echowand.object.EchonetObjectException;
import echowand.object.RemoteObject;

/**
 * @author Cu Pham
 *
 */
public abstract class eDataObject extends eSuperClass{


	

	public eDataObject() {
		setOperationStatus(false);
	}
	
	/**
	 * @param operationStatus
	 */
	public eDataObject(boolean operationStatus) {
		setOperationStatus(operationStatus);
	}

	/**
	 * TODO: parse this device data from remote object
	 * @param rObj
	 * @throws EchonetObjectException 
	 */
	public abstract void ParseDataFromRemoteObject(RemoteObject rObj) throws EchonetObjectException;
	
	/**
	 * TODO: parse this data to string
	 * @return
	 */
	public abstract String ToString();
	
}