package mainpackage;

public class D8 extends DataMessage {
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "D8";

	// : Object Properties
	public static final String PROPERTY_IS_CKB_INPUT = CaressesOntology.NAMESPACE + "is_CKB_input";
		
	public D8(String uri){	
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}

}
