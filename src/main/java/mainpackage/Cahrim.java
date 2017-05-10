package mainpackage;

public class Cahrim extends CaressesComponent {
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "CAHRIM";
	
	public static final String PROPERTY_HAS_CAHRIM_OUTPUT = CaressesOntology.NAMESPACE + "has_CAHRIM_output"; 
	
	public Cahrim(String uri){	
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}
	
	public String setOutput(DataMessage message_individual){
		// ! message_individual can only be an instance of D5, D6, D8 or D10
		if ((message_individual instanceof D5) || (message_individual instanceof D6)|| (message_individual instanceof D8) || 
				(message_individual instanceof D10)){
			setProperty(PROPERTY_HAS_CAHRIM_OUTPUT, message_individual);	
			return "Right output assignment!";
		} else {
			return "ERROR: CAHRIM has only outputs of type D5, D6, D8 or D10!";
		}
	}
	
//	public DataMessage getOutput(){
//		DataMessage msg_individual = (DataMessage) getProperty(PROPERTY_HAS_CAHRIM_OUTPUT);
//		return (msg_individual == null) ? "" : msg_individual;
//		
//	}
}
