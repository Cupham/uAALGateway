package ontologies;

import mainpackage.CaressesOntology;

public class HealthRelatedOntology extends EchonetDevice{
	public static final String MY_URI = CaressesOntology.NAMESPACE +"echonet_health_related_ontology";	
	public HealthRelatedOntology( ) {
		super();
		setClassGroupCode((byte)0x04);
		// TODO Auto-generated constructor stub
	}
	public HealthRelatedOntology(String URI) {
		super(URI);
		setClassGroupCode((byte)0x04);
		// TODO Auto-generated constructor stub
	}
}
