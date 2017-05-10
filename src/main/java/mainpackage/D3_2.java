package mainpackage;

public class D3_2 extends D3 {
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "D3.2";
	
	// : Data Properties
	public static final String PROPERTY_HAS_D3_2_DESCRIPTION = CaressesOntology.NAMESPACE + "has_D3.2_description"; 

	
	public D3_2(String uri){	
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}
	
	public void setMessage(String message){
		changeProperty(PROPERTY_HAS_D3_2_DESCRIPTION, message);
		
	}
	
	public String getMessage(){
		String msg = (String) getProperty(PROPERTY_HAS_D3_2_DESCRIPTION);
		return (msg == null) ? "" : msg;
		
	}
	
	public void setInput(Cspem cspem_individual){
		setProperty(PROPERTY_IS_CSPEM_INPUT, cspem_individual);
	}

}
