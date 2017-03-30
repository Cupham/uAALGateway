package echonet.objects;

import echowand.object.EchonetObjectException;
import echowand.object.RemoteObject;

/**
 * @author Cu Pham
 *
 */
public abstract class eDataObject {

	/**
	 * Class group code
	 */
	protected byte groupCode;
	/**
	 * Class code
	 */
	protected byte classCode;
	/**
	 * Instance code
	 */
	protected byte instanceCode;
	/**
	 * EPC: 0x80
	 * ON:0x30, OFF: 0x31
	 */
	protected boolean operationStatus;

	/**
	 * @return the operationStatus
	 */
	public boolean isOperationStatus() {
		return operationStatus;
	}

	/**
	 * @param operationStatus the operationStatus to set
	 */
	public void setOperationStatus(boolean operationStatus) {
		this.operationStatus = operationStatus;
	}

	/**
	 * @return the groupCode
	 */
	public byte getGroupCode() {
		return groupCode;
	}

	/**
	 * @return the classCode
	 */
	public byte getClassCode() {
		return classCode;
	}

	/**
	 * @return the instanceCode
	 */
	public byte getInstanceCode() {
		return instanceCode;
	}

	public eDataObject() {
		this.operationStatus = false;
	}
	
	/**
	 * @param operationStatus
	 */
	public eDataObject(boolean operationStatus) {
		this.operationStatus = operationStatus;
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