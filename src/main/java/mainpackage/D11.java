package mainpackage;

public class D11 extends DataMessage {
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "D11";

	// : Object Properties
	public static final String PROPERTY_IS_CAHRIM_INPUT = CaressesOntology.NAMESPACE + "is_CAHRIM_input";
		
	public D11(String uri){	
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}

}
