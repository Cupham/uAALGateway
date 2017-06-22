package mainpackage;

public class D7_1 extends D7 {
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "D7.1";
	
	// : Data Properties
	public static final String PROPERTY_HAS_D7_1_DESCRIPTION = CaressesOntology.NAMESPACE + "has_D7.1_description"; 
	
	public D7_1(String uri){	
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}
	
	public void setMessage(String message){
		changeProperty(PROPERTY_HAS_D7_1_DESCRIPTION, message);
		
	}
	
	public String getMessage(){
		String msg = (String) getProperty(PROPERTY_HAS_D7_1_DESCRIPTION);
		return (msg == null) ? "EMPTY_MSG" : msg;
		
	}
	
	public void setInput(Cahrim cahrim_individual){
		setProperty(PROPERTY_IS_CAHRIM_INPUT, cahrim_individual);
	}

}
