package mainpackage;

public class D6 extends DataMessage {
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "D6";

	// : Object Properties
	public static final String PROPERTY_IS_CSPEM_INPUT = CaressesOntology.NAMESPACE + "is_CSPEM_input";
	
		
	public D6(String uri){	
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}

}
