package mainpackage;

public class D10 extends DataMessage {
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "D10";
	
	public D10(String uri){	
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}

}

