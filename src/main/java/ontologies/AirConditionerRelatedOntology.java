package ontologies;

import mainpackage.CaressesOntology;

public class AirConditionerRelatedOntology extends EchonetDevice{
	public static final String MY_URI = CaressesOntology.NAMESPACE +"echonet_sensor_related_ontology";	
	public AirConditionerRelatedOntology() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AirConditionerRelatedOntology(String URI) {
		super(URI);
		// TODO Auto-generated constructor stub
	}
	
	public String getClassURI(){
		return MY_URI;
	}
}
