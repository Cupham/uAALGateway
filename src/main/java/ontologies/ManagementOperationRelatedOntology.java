package ontologies;

import mainpackage.CaressesOntology;

public class ManagementOperationRelatedOntology extends EchonetDevice{
	public static final String MY_URI = CaressesOntology.NAMESPACE +"echonet_management_operation_related_ontology";	
	public ManagementOperationRelatedOntology() {
		super();
		setClassGroupCode((byte)0x05);
		// TODO Auto-generated constructor stub
	}
	public ManagementOperationRelatedOntology(String URI) {
		super(URI);
		setClassGroupCode((byte)0x05);
		// TODO Auto-generated constructor stub
	}
}
