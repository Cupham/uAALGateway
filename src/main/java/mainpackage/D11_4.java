package mainpackage;

public class D11_4 extends D11 {
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "D11.4";
	
	// : Data Properties
	public static final String PROPERTY_HAS_D11_4_DESCRIPTION = CaressesOntology.NAMESPACE + "has_D11.4_description"; 
	
	public D11_4(String uri){	
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}
	
	public void setMessage(String message){
		changeProperty(PROPERTY_HAS_D11_4_DESCRIPTION, message);
		
	}
	
	public String getMessage(){
		String msg = (String) getProperty(PROPERTY_HAS_D11_4_DESCRIPTION);
		return (msg == null) ? "EMPTY_MSG" : msg;
		
	}
	
	public void setInput(Cahrim cahrim_individual){
		setProperty(PROPERTY_IS_CAHRIM_INPUT, cahrim_individual);
	}

}
