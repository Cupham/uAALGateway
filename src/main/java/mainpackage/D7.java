package mainpackage;

public class D7 extends DataMessage {
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "D7";

	// : Object Properties
	public static final String PROPERTY_IS_CAHRIM_INPUT = CaressesOntology.NAMESPACE + "is_CAHRIM_input";
		
	public D7(String uri){	
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}

}
