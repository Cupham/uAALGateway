package ontologies;

import mainpackage.CaressesOntology;

public class CookingHouseholdRelatedOntology extends EchonetDevice{
	public static final String MY_URI = CaressesOntology.NAMESPACE +"echonet_cooking_household_related_ontology";	
	public CookingHouseholdRelatedOntology() {
		super();
		setClassGroupCode((byte)0x03);
		// TODO Auto-generated constructor stub
	}
	public CookingHouseholdRelatedOntology(String URI) {
		super(URI);
		setClassGroupCode((byte)0x03);
		// TODO Auto-generated constructor stub
	}
}
