package ontologies;

import mainpackage.CaressesOntology;

public class HousingFacilitiesRelatedOntology extends EchonetDevice{
	public static final String MY_URI = CaressesOntology.NAMESPACE +"echonet_housing_facilities_related_ontology";	
	public HousingFacilitiesRelatedOntology() {
		super();
		setClassGroupCode((byte)0x02);
		// TODO Auto-generated constructor stub
	}
	public HousingFacilitiesRelatedOntology(String URI) {
		super(URI);
		setClassGroupCode((byte)0x02);
		// TODO Auto-generated constructor stub
	}
}
