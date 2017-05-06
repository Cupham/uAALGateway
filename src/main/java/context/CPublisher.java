package context;


/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_6#Publishing_context_events */
import ont.MySensor;

import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.context.ContextEventPattern;
import org.universAAL.middleware.context.ContextPublisher;
import org.universAAL.middleware.context.DefaultContextPublisher;
import org.universAAL.middleware.context.owl.ContextProvider;
import org.universAAL.middleware.context.owl.ContextProviderType;


public class CPublisher{
	public static final String NAMESPACE = "http://CARESSESuniversAALskeleton.org/CaressesOntology.owl#";
	private ContextPublisher myContextPublisher;
	
	protected CPublisher(ModuleContext context) {
		ContextProvider myContextProvider = new ContextProvider(NAMESPACE+ "contextProvider");
		myContextProvider.setType(ContextProviderType.gauge);
		myContextProvider.setProvidedEvents(new ContextEventPattern[] {new ContextEventPattern()});
		myContextPublisher = new DefaultContextPublisher(context, myContextProvider);
		}
	
	public boolean publishContextEvent(String data_type , Object data) {
		boolean published = true;
		switch (data_type) {
		case "Temperature":
			MySensor tempSensor = (MySensor) data;
			ContextEvent temperatureEvent = new ContextEvent(tempSensor,MySensor.MY_URI);
			break;

		default:
			break;
		}
		
		
		
		
		return published;
		
	}
	public void communicationChannelBroken() {
		System.out.println("Connection Lost!!");
	}

}
