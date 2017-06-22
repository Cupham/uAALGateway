package mainpackage;

public class D1_2 extends D1 {
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "D1.2";
	
	// : Data Properties
	public static final String PROPERTY_HAS_D1_2_DESCRIPTION = CaressesOntology.NAMESPACE + "has_D1.2_description"; 
	

	public D1_2(String uri){	
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}
	
	public void setMessage(String message){
		changeProperty(PROPERTY_HAS_D1_2_DESCRIPTION, message);
		
	}
	
	public String getMessage(){
		String msg = (String) getProperty(PROPERTY_HAS_D1_2_DESCRIPTION);
		return (msg == null) ? "EMPTY_MSG" : msg;
		
	}
	
	public void setInput(Cspem cspem_individual){
		setProperty(PROPERTY_IS_CSPEM_INPUT, cspem_individual);
	}

}
