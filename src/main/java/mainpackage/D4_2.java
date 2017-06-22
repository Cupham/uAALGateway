package mainpackage;

public class D4_2 extends D4 {
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "D4.2";
	
	// : Data Properties
	public static final String PROPERTY_HAS_D4_2_DESCRIPTION = CaressesOntology.NAMESPACE + "has_D4.2_description"; 
	
	public D4_2(String uri){	
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}
	
	public void setMessage(String message){
		changeProperty(PROPERTY_HAS_D4_2_DESCRIPTION, message);
		
	}
	
	public String getMessage(){
		String msg = (String) getProperty(PROPERTY_HAS_D4_2_DESCRIPTION);
		return (msg == null) ? "EMPTY_MSG" : msg;
		
	}
	
	public void setInput(Cspem cspem_individual){
		setProperty(PROPERTY_IS_CSPEM_INPUT, cspem_individual);
	}

}
