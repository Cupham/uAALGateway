package mainpackage;

public class D2_3 extends D2 {
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "D2.3";
	
	// : Data Properties
	public static final String PROPERTY_HAS_D2_3_DESCRIPTION = CaressesOntology.NAMESPACE + "has_D2.3_description"; 
		
		
	public D2_3(String uri){	
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}
	
	public void setMessage(String message){
		changeProperty(PROPERTY_HAS_D2_3_DESCRIPTION, message);
		
	}
	
	public String getMessage(){
		String msg = (String) getProperty(PROPERTY_HAS_D2_3_DESCRIPTION);
		return (msg == null) ? "" : msg;
		
	}
	
	public void setInput(Cspem cspem_individual){
		setProperty(PROPERTY_IS_CSPEM_INPUT, cspem_individual);
	}

}
