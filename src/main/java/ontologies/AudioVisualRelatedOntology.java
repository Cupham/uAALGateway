package ontologies;

import mainpackage.CaressesOntology;

public class AudioVisualRelatedOntology extends EchonetDevice{
	public static final String MY_URI = CaressesOntology.NAMESPACE +"echonet_audio_visual_related_ontology";	
	public AudioVisualRelatedOntology() {
		super();
		setClassGroupCode((byte)0x06);
		// TODO Auto-generated constructor stub
	}
	public AudioVisualRelatedOntology(String URI) {
		super(URI);
		setClassGroupCode((byte)0x06);
		// TODO Auto-generated constructor stub
	}
}
