package mainpackage;

import org.universAAL.ontology.phThing.Device;

public class DataMessage extends Device {
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "Data_Message";
	
	public DataMessage(String uri){	
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}

}
