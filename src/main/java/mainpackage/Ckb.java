package mainpackage;

public class Ckb extends CaressesComponent {
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "CKB";
	
	public static final String PROPERTY_HAS_CKB_OUTPUT = CaressesOntology.NAMESPACE + "has_CKB_output"; 
	
	public Ckb(String uri){	
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}
	
	public String setOutput(DataMessage message_individual){
		// ! message_individual can only be an instance of D1, D2, D3, D4 or D11
		if ((message_individual instanceof D1) || (message_individual instanceof D2)|| (message_individual instanceof D3) || 
				(message_individual instanceof D4) || (message_individual instanceof D11)){
			setProperty(PROPERTY_HAS_CKB_OUTPUT, message_individual);	
			return "Right output assignment!";
		} else {
			return "ERROR: CKB has only outputs of type D1, D2, D3, D4 or D11!";
		}
	}
	
//	public DataMessage getOutput(){
//		DataMessage msg_individual = (DataMessage) getProperty(PROPERTY_HAS_CKB_OUTPUT);
//		return (msg_individual == null) ? "" : msg_individual;
//		
//	}

}
