package echonet.objects;

import echowand.common.EPC;
import echowand.common.PropertyMap;
import echowand.object.EchonetObjectException;
import echowand.object.ObjectData;
import echowand.object.RemoteObject;
import utils.EchonetDataConverter;

public class eLighting extends eDataObject{
	
	private int illuminateLevel;
	private String lightColorSetting;
	private NodeProfileObject profile;
	
	
	public int getIlluminateLevel() {
		return illuminateLevel;
	}
	public void setIlluminateLevel(int illuminateLevel) {
		this.illuminateLevel = illuminateLevel;
	}
	public String getLightColorSetting() {
		return lightColorSetting;
	}
	public void setLightColorSetting(String lightColorSetting) {
		this.lightColorSetting = lightColorSetting;
	}
	public eLighting() {
		super();
		this.groupCode= (byte)0x02;
		this.classCode=(byte)0x90;
	}
	@Override
	public void ParseDataFromRemoteObject(RemoteObject rObj) throws EchonetObjectException {
		ObjectData data = rObj.getData(EPC.x9F);
		PropertyMap propertyMap = new PropertyMap(data.toBytes());
		this.instanceCode = rObj.getEOJ().getInstanceCode();
		if(propertyMap.isSet(EPC.xB0)) {
			this.illuminateLevel = EchonetDataConverter.dataToInteger(rObj.getData(EPC.xB0));
		}
		
		
	}

	@Override
	public String ToString() {
		StringBuilder rs = new StringBuilder();
		rs.append("Operation Status: " + (this.isOperationStatus() ? "ON" : "OFF"));
		rs.append("Illuminance level: " + this.illuminateLevel + " %");
		return null;
	}
	public NodeProfileObject getProfile() {
		return profile;
	}
	public void setProfile(NodeProfileObject profile) {
		this.profile = profile;
	}

}
