package ontologies;

import mainpackage.CaressesOntology;
import mainpackage.SmartFacility;

public class Lighting extends HousingFacilitiesRelatedOntology{
	public static final String MY_URI = CaressesOntology.NAMESPACE + "echonet_lighting";
	public static final String PROPERTY_HAS_OPERATION_STATUS = CaressesOntology.NAMESPACE + "echonet_lighting_has_operation_status";
	public static final String PROPERTY_HAS_ILLUMINATION_LEVEL = CaressesOntology.NAMESPACE + "echonet_lighting_has_illumination_level";
	
	public Lighting() {
		super();
	}
	public Lighting(String URI) {
		super(URI);
		setProperty(PROPERTY_HAS_CLASS_CODE, (byte)0x90);
		
	}
	public String getClassURI(){
		return MY_URI;
	}
	public void setInput(SmartFacility AALEnvironment_individual){
		setProperty(PROPERTY_IS_SMART_FACILITY_COMPONENT, AALEnvironment_individual);
	}
	

	public boolean getOperationStatus(){
		Boolean data = (Boolean) getProperty(PROPERTY_HAS_OPERATION_STATUS);
		return (data == null) ? null : data.booleanValue(); 
	}
	public void setOperationStatus(boolean stt) {
		changeProperty(PROPERTY_HAS_OPERATION_STATUS, new Boolean(stt));	
	}

	public float getIlluminationLevel(){
		Integer data =  (Integer) getProperty(PROPERTY_HAS_ILLUMINATION_LEVEL);	
		return (data ==null) ? null : data.intValue();
	}
	public void setIlluminationLevel(int iLevel) {
		changeProperty(PROPERTY_HAS_ILLUMINATION_LEVEL, new Integer(iLevel));
	}

	@Override
	public int getPropSerializationType(String propURI) {
		return super.getPropSerializationType(propURI);
	}

}
