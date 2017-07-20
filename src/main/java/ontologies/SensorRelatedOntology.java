package ontologies;

import mainpackage.CaressesOntology;

public class SensorRelatedOntology extends EchonetDevice{
	
	public static final String MY_URI = CaressesOntology.NAMESPACE +"echonet_sensor_related_ontology";	
	public SensorRelatedOntology() {
		super();
		setClassGroupCode((byte)0x00);
	}
	public SensorRelatedOntology(String URI) {
		super(URI);
		setClassGroupCode((byte)0x00);
	}
	
	public String getClassURI(){
		return MY_URI;
	}
}
