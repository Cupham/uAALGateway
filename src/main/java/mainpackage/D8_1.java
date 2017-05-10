package mainpackage;

public class D8_1 extends D8 {
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "D8.1";
	
	// : Data Properties
	public static final String PROPERTY_HAS_D8_1_DESCRIPTION = CaressesOntology.NAMESPACE + "has_D8.1_description"; 
	
	public D8_1(String uri){	
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}
	
	public void setMessage(String message){
		changeProperty(PROPERTY_HAS_D8_1_DESCRIPTION, message);
		
	}
	
	public String getMessage(){
		String msg = (String) getProperty(PROPERTY_HAS_D8_1_DESCRIPTION);
		return (msg == null) ? "" : msg;
		
	}
	
	public void setInput(Ckb ckb_individual){
		setProperty(PROPERTY_IS_CKB_INPUT, ckb_individual);
	}

}
