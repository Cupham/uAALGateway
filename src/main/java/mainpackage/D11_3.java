package mainpackage;

public class D11_3 extends D11 {
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "D11.3";
	
	// : Data Properties
	public static final String PROPERTY_HAS_D11_3_DESCRIPTION = CaressesOntology.NAMESPACE + "has_D11.3_description"; 
	
	public D11_3(String uri){	
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}

	public void setMessage(String message){
		changeProperty(PROPERTY_HAS_D11_3_DESCRIPTION, message);
		
	}
	
	public String getMessage(){
		String msg = (String) getProperty(PROPERTY_HAS_D11_3_DESCRIPTION);
		return (msg == null) ? "EMPTY_MSG" : msg;
		
	}
	
	public void setInput(Cahrim cahrim_individual){
		setProperty(PROPERTY_IS_CAHRIM_INPUT, cahrim_individual);
	}
}
