package jaist.mainpackage;

import org.universAAL.middleware.rdf.Resource;
import org.universAAL.middleware.rdf.ResourceFactory;

public class CaressesFactory implements ResourceFactory {
	
	public static final int CARESSES_COMPONENT = 0;
	
	public static final int SMART_FACILITY     = 100;
	
	
	
	
	public Resource createInstance(String classURI, String instanceURI, int factoryIndex){
		switch(factoryIndex){
			case CARESSES_COMPONENT:
				return new CaressesComponent(instanceURI);
			case SMART_FACILITY:
				return new SmartFacility(instanceURI);

		}
		
		return null;
	}

}
