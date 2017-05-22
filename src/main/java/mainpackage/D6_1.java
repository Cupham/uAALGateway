package mainpackage;

public class D6_1 extends D6 {
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "D6.1";
	
	// : Data Properties
	public static final String PROPERTY_HAS_D6_1_DESCRIPTION = CaressesOntology.NAMESPACE + "has_D6.1_description"; 
	
	public D6_1(String uri){	
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}
	
	public void setMessage(String message){
		changeProperty(PROPERTY_HAS_D6_1_DESCRIPTION, message);
		
	}
	
	public String getMessage(){
		String msg = (String) getProperty(PROPERTY_HAS_D6_1_DESCRIPTION);
		return (msg == null) ? "" : msg;
		
	}
	
	public void setInput(Cspem cspem_individual){
		setProperty(PROPERTY_IS_CSPEM_INPUT, cspem_individual);
	}

}