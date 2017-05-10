package mainpackage;

public class D9 extends DataMessage {
	
	public static final String MY_URI = CaressesOntology.NAMESPACE + "D9";
	
	public D9(String uri){	
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}

}
