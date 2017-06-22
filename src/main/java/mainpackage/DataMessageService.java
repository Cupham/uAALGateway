package mainpackage;

import org.universAAL.middleware.service.owl.Service;

public class DataMessageService extends Service{
	
	public static final String MY_URI= CaressesOntology.NAMESPACE + "Data_Message_Service";
	
	public static final String PROP_PROVIDES_D1_1_MESSAGE = CaressesOntology.NAMESPACE + "provides_D1.1_message";
	public static final String PROP_PROVIDES_D1_2_MESSAGE = CaressesOntology.NAMESPACE + "provides_D1.2_message";
	public static final String PROP_PROVIDES_D2_1_MESSAGE = CaressesOntology.NAMESPACE + "provides_D2.1_message";
	public static final String PROP_PROVIDES_D2_2_MESSAGE = CaressesOntology.NAMESPACE + "provides_D2.2_message";
	public static final String PROP_PROVIDES_D2_3_MESSAGE = CaressesOntology.NAMESPACE + "provides_D2.3_message";
	public static final String PROP_PROVIDES_D2_4_MESSAGE = CaressesOntology.NAMESPACE + "provides_D2.4_message";
	public static final String PROP_PROVIDES_D3_1_MESSAGE = CaressesOntology.NAMESPACE + "provides_D3.1_message";
	public static final String PROP_PROVIDES_D3_2_MESSAGE = CaressesOntology.NAMESPACE + "provides_D3.2_message";
	public static final String PROP_PROVIDES_D4_1_MESSAGE = CaressesOntology.NAMESPACE + "provides_D4.1_message";
	public static final String PROP_PROVIDES_D4_2_MESSAGE = CaressesOntology.NAMESPACE + "provides_D4.2_message";
	public static final String PROP_PROVIDES_D5_1_MESSAGE = CaressesOntology.NAMESPACE + "provides_D5.1_message";
	public static final String PROP_PROVIDES_D5_2_MESSAGE = CaressesOntology.NAMESPACE + "provides_D5.2_message";
	public static final String PROP_PROVIDES_D6_1_MESSAGE = CaressesOntology.NAMESPACE + "provides_D6.1_message";
	public static final String PROP_PROVIDES_D6_2_MESSAGE = CaressesOntology.NAMESPACE + "provides_D6.2_message";
	public static final String PROP_PROVIDES_D6_3_MESSAGE = CaressesOntology.NAMESPACE + "provides_D6.3_message";
	public static final String PROP_PROVIDES_D7_1_MESSAGE = CaressesOntology.NAMESPACE + "provides_D7.1_message";
	public static final String PROP_PROVIDES_D7_2_MESSAGE = CaressesOntology.NAMESPACE + "provides_D7.2_message";
	public static final String PROP_PROVIDES_D8_1_MESSAGE = CaressesOntology.NAMESPACE + "provides_D8.1_message";
	public static final String PROP_PROVIDES_D8_2_MESSAGE = CaressesOntology.NAMESPACE + "provides_D8.2_message";
	public static final String PROP_PROVIDES_D11_1_MESSAGE = CaressesOntology.NAMESPACE + "provides_D11.1_message";
	public static final String PROP_PROVIDES_D11_2_MESSAGE = CaressesOntology.NAMESPACE + "provides_D11.2_message";
	public static final String PROP_PROVIDES_D11_3_MESSAGE = CaressesOntology.NAMESPACE + "provides_D11.3_message";
	public static final String PROP_PROVIDES_D11_4_MESSAGE = CaressesOntology.NAMESPACE + "provides_D11.4_message";

	
	public DataMessageService(){
		super();
	}
	
	public DataMessageService(String uri){
		super(uri);
	}
	
	public String getClassURI(){
		return MY_URI;
	}
	
	public int getPropSerializationType(String propURI){

		if ((PROP_PROVIDES_D1_1_MESSAGE.equals(propURI)) || (PROP_PROVIDES_D1_2_MESSAGE.equals(propURI))
				|| (PROP_PROVIDES_D2_1_MESSAGE.equals(propURI))  || (PROP_PROVIDES_D2_2_MESSAGE.equals(propURI))
				|| (PROP_PROVIDES_D2_3_MESSAGE.equals(propURI))  || (PROP_PROVIDES_D2_4_MESSAGE.equals(propURI))
				|| (PROP_PROVIDES_D3_1_MESSAGE.equals(propURI))  || (PROP_PROVIDES_D3_2_MESSAGE.equals(propURI))
				|| (PROP_PROVIDES_D4_1_MESSAGE.equals(propURI))  || (PROP_PROVIDES_D4_2_MESSAGE.equals(propURI))
				|| (PROP_PROVIDES_D5_1_MESSAGE.equals(propURI))  || (PROP_PROVIDES_D5_2_MESSAGE.equals(propURI))
				|| (PROP_PROVIDES_D6_1_MESSAGE.equals(propURI))  || (PROP_PROVIDES_D6_2_MESSAGE.equals(propURI))
				|| (PROP_PROVIDES_D6_3_MESSAGE.equals(propURI))  || (PROP_PROVIDES_D7_1_MESSAGE.equals(propURI))
				|| (PROP_PROVIDES_D7_2_MESSAGE.equals(propURI))  || (PROP_PROVIDES_D8_1_MESSAGE.equals(propURI))
				|| (PROP_PROVIDES_D8_2_MESSAGE.equals(propURI))  || (PROP_PROVIDES_D11_1_MESSAGE.equals(propURI))
				|| (PROP_PROVIDES_D11_2_MESSAGE.equals(propURI)) || (PROP_PROVIDES_D11_3_MESSAGE.equals(propURI))
				|| (PROP_PROVIDES_D11_4_MESSAGE.equals(propURI))){
			return PROP_SERIALIZATION_FULL;
		} else {
			return super.getPropSerializationType(propURI);
		}

	}
	
	public boolean isWellFormed(){
		return true;
	}

}