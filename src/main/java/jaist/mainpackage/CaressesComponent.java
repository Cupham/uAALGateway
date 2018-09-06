package jaist.mainpackage;

import org.universAAL.ontology.phThing.Device;

public class CaressesComponent extends Device {
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "Caresses_Component";
	
	public CaressesComponent(String uri){	
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}


}