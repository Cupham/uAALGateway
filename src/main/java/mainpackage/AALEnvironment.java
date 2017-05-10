package mainpackage;

public class AALEnvironment extends CaressesComponent{
	public static final String MY_URI = CaressesOntology.NAMESPACE + "AALEnvironment";
	public static final String PROPERTY_HAS_AALENVIRONMENT_OUTPUT = CaressesOntology.NAMESPACE + "has_AALEnvironment_output"; 
	
	public AALEnvironment(String uri){	
		super(uri);
	}
	public String setOutput(DataMessage message_individual){
		// ! message_individual can only be echonet sensor
		if (message_individual instanceof EchonetSensor){
			setProperty(PROPERTY_HAS_AALENVIRONMENT_OUTPUT, message_individual);	
			return "Right output assignment!";
		} else {
			return "ERROR: AALEnvironment has only outputs of type EchonetSensor!";
		}
	}
	
}
