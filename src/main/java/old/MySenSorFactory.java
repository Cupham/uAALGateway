package old;

import org.universAAL.middleware.rdf.Resource;
import org.universAAL.middleware.rdf.ResourceFactory;


public class MySenSorFactory implements ResourceFactory{
	
	public static final int MYSENSOR=0;
	public static final int MYSENSORSERVICE=1;
	
	public Resource createInstance(String classURI, String instanceURI, int index) {
		switch(index) {
			case MYSENSOR:
				return new MySensor(instanceURI);
			case MYSENSORSERVICE:
				return new MySensorService(instanceURI);
		}
		return null;
	}

}
