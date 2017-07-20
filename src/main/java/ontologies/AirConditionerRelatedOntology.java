package ontologies;

import mainpackage.CaressesOntology;

public class AirConditionerRelatedOntology extends EchonetDevice{
	public static final String MY_URI = CaressesOntology.NAMESPACE +"echonet_sensor_related_ontology";	
	public AirConditionerRelatedOntology() {
		super();
		setClassGroupCode((byte)0x01);
		// TODO Auto-generated constructor stub
	}
	
	public AirConditionerRelatedOntology(String URI) {
		super(URI);
		setClassGroupCode((byte)0x01);
		// TODO Auto-generated constructor stub
	}
	
	public String getClassURI(){
		return MY_URI;
	}
}
