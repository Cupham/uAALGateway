package old;

import echonet.objects.eAirConditioner;
import mainpackage.CaressesOntology;
import mainpackage.SmartFacility;

public class HomeAirConditioner_old extends EchonetAirConditioner{
	public static final String MY_URI = CaressesOntology.NAMESPACE + "HomeAirconditioner";	
	// : Data Properties
	public static final String PROPERTY_HAS_HOME_AIRCONDITIONER_DESCRIPTION = CaressesOntology.NAMESPACE + 
			"has_Home_AirConditioner_description";
	
	public static final String PROPERTY_HAS_CURRENT_TEMPERATURE = CaressesOntology.NAMESPACE + "has_CurrentSetting_Temperature";	
	public static final String PROPERTY_HAS_LOCATION = CaressesOntology.NAMESPACE + "has_AirConditioner_Location";
	
	public HomeAirConditioner_old(String uri) {
		super(uri);
		changeProperty(MY_URI, uri);
	}
	public String getClassURI(){
		return MY_URI;
	}
	public boolean setMessage(String message){
		boolean rs = false;
		changeProperty(PROPERTY_HAS_HOME_AIRCONDITIONER_DESCRIPTION, message);
		eAirConditioner dev = SerializeUtils.airConditionerFromMessage(message);
		
		if(null != dev)
		{
			changeProperty(PROPERTY_HAS_CURRENT_TEMPERATURE, dev.getCurrentSettingTemperature());
			changeProperty(EchonetAirConditioner.PROPERTY_OPERATION_STATUS, dev.isOperationStatus());
			changeProperty(PROPERTY_HAS_LOCATION, dev.getInstallLocation());
			changeProperty(EchonetAirConditioner.PROPERTY_HAS_IP, dev.getDeviceIP());		
			rs = true;
		} 
		
		return rs;	
	}
	
	public String getMessage(){
		String msg = (String) getProperty(PROPERTY_HAS_HOME_AIRCONDITIONER_DESCRIPTION);
		return (msg == null) ? "" : msg;
		
	}
	
	public void setInput(SmartFacility AALEnvironment_individual){
		setProperty(PROPERTY_IS_AALENVIRONMENT_INPUT, AALEnvironment_individual);
	}
	@Override
	public int getPropSerializationType(String propURI) {
		return super.getPropSerializationType(propURI);
	}

}
