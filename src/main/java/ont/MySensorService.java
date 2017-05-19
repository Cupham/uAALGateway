package ont;


import mainpackage.CaressesOntology;

import org.universAAL.middleware.service.owl.Service;
import org.universAAL.middleware.service.owls.profile.ServiceProfile;

public class MySensorService extends Service{
	
	public static final String MY_URI=CaressesOntology.NAMESPACE +"MySensorService";
	public static final String PROP_CONTROLS = CaressesOntology.NAMESPACE + "controls";
	
	
	
	public MySensorService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MySensorService(String uri) {
		super(uri);
		// TODO Auto-generated constructor stub
	}
	public String getClassURI() {
		return MY_URI;
	}

	@Override
	public int getPropSerializationType(String propURI) {
		if(PROP_CONTROLS.equals(propURI)) {
			return PROP_SERIALIZATION_FULL;
		} else {
			return super.getPropSerializationType(propURI);
		}
	}
	@Override
	public boolean isWellFormed() {
		// TODO Auto-generated method stub
		return true;
	}
	

}
