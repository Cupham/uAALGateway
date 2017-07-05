package old;

import javax.xml.ws.handler.MessageContext.Scope;

import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.owl.OntologyManagement;

public class SubcriptionHandler {
	public SubcriptionHandler() {
		
	}
	public static void handleSubcribedMessage(MySensor sensor) {
		System.out.println("Received Event");
		String location = sensor.getProperty(MySensor.PROP_LOCALTION).toString();
		if(!location.equals("Living room")) {
			sensor.changeProperty(MySensor.PROP_LOCALTION, "Living room");
			ContextEvent ce = new ContextEvent(sensor, MySensor.MY_URI);
			//Activator.pub.publishContextEvent(ce);	
		}
	}
}
