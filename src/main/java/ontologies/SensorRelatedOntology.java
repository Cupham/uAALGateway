package ontologies;

import mainpackage.CaressesOntology;

public class SensorRelatedOntology extends EchonetDevice{
	
	public static final String MY_URI = CaressesOntology.NAMESPACE +"echonet_sensor_related_ontology";	
	public SensorRelatedOntology(String URI) {
		super(URI);
		// TODO Auto-generated constructor stub
	}
	
	public String getClassURI(){
		return MY_URI;
	}
}
