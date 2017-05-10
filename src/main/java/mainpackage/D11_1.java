package mainpackage;

public class D11_1 extends D11 {
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "D11.1";
	
	// : Data Properties
	public static final String PROPERTY_HAS_D11_1_DESCRIPTION = CaressesOntology.NAMESPACE + "has_D11.1_description"; 
	
	
	public D11_1(String uri){	
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}

	public void setMessage(String message){
		changeProperty(PROPERTY_HAS_D11_1_DESCRIPTION, message);
		
	}
	
	public String getMessage(){
		String msg = (String) getProperty(PROPERTY_HAS_D11_1_DESCRIPTION);
		return (msg == null) ? "" : msg;
		
	}
	
	public void setInput(Cahrim cahrim_individual){
		setProperty(PROPERTY_IS_CAHRIM_INPUT, cahrim_individual);
	}
}
