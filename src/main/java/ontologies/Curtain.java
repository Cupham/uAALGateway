package ontologies;

import mainpackage.CaressesOntology;
import mainpackage.SmartFacility;

public class Curtain extends ManagementOperationRelatedOntology{
	public static final String MY_URI = CaressesOntology.NAMESPACE + "echonet_curtain";
	public static final String PROPERTY_HAS_STATUS = CaressesOntology.NAMESPACE + "echonet_curtain_has_status";
	
	public Curtain() {
		super();
	}
	public Curtain(String URI) {
		super(URI);
		setProperty(PROPERTY_HAS_CLASS_CODE, (byte)0xfd);
		
	}
	public String getClassURI(){
		return MY_URI;
	}
	public void setInput(SmartFacility AALEnvironment_individual){
		setProperty(PROPERTY_IS_SMART_FACILITY_COMPONENT, AALEnvironment_individual);
	}
	

	public boolean getStatus(){
		Boolean data = (Boolean) getProperty(PROPERTY_HAS_STATUS);
		return (data == null) ? null : data.booleanValue(); 
	}
	public void setStatus(boolean stt) {
		changeProperty(PROPERTY_HAS_STATUS, new Boolean(stt));	
	}

	@Override
	public int getPropSerializationType(String propURI) {
		return super.getPropSerializationType(propURI);
	}

}
