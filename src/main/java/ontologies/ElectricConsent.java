package ontologies;

import mainpackage.CaressesOntology;
import mainpackage.SmartFacility;

public class ElectricConsent extends SensorRelatedOntology{
	public static final String MY_URI = CaressesOntology.NAMESPACE + "echonet_consent";
	public static final String PROPERTY_HAS_OPERATION_STATUS = CaressesOntology.NAMESPACE + "echonet_consent_has_operation_status";
	
	public ElectricConsent() {
		super();
	}
	public ElectricConsent(String URI) {
		super(URI);
		setProperty(PROPERTY_HAS_CLASS_CODE, (byte)0x22);
		
	}
	public String getClassURI(){
		return MY_URI;
	}
	public void setInput(SmartFacility AALEnvironment_individual){
		setProperty(PROPERTY_IS_SMART_FACILITY_COMPONENT, AALEnvironment_individual);
	}
	

	public boolean getStatus(){
		Boolean data = (Boolean) getProperty(PROPERTY_HAS_OPERATION_STATUS);
		return (data == null) ? null : data.booleanValue(); 
	}
	public void setStatus(boolean stt) {
		changeProperty(PROPERTY_HAS_OPERATION_STATUS, new Boolean(stt));	
	}

	@Override
	public int getPropSerializationType(String propURI) {
		return super.getPropSerializationType(propURI);
	}

}
