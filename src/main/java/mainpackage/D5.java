package mainpackage;

public class D5 extends DataMessage {
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "D5";

	// : Object Properties
	public static final String PROPERTY_IS_CSPEM_INPUT = CaressesOntology.NAMESPACE + "is_CSPEM_input";
	public static final String PROPERTY_IS_CKB_INPUT   = CaressesOntology.NAMESPACE + "is_CKB_input";
		
	public D5(String uri){	
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}

}
