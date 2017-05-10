package mainpackage;

public class Cspem extends CaressesComponent {
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "CSPEM";
	
	public static final String PROPERTY_HAS_CSPEM_OUTPUT = CaressesOntology.NAMESPACE + "has_CSPEM_output"; 
	
	public Cspem(String uri){	
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}
	
	public String setOutput(DataMessage message_individual){
		// ! message_individual can only be an instance of D7 or D9
		if ((message_individual instanceof D7) || (message_individual instanceof D9)){
			setProperty(PROPERTY_HAS_CSPEM_OUTPUT, message_individual);	
			return "Right output assignment!";
		} else {
			return "ERROR: CSPEM has only outputs of type D7 or D9!";
		}
	}
	
//	public DataMessage getOutput(){
//		DataMessage msg_individual = (DataMessage) getProperty(PROPERTY_HAS_CSPEM_OUTPUT);
//		return (msg_individual == null) ? "" : msg_individual;
//		
//	}

}
