package context;


/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_6#Publishing_context_events */
import mainpackage.Activator;
import ont.MySensor;
import ont.OntologyUpdater;

import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.context.ContextEventPattern;
import org.universAAL.middleware.context.ContextPublisher;
import org.universAAL.middleware.context.DefaultContextPublisher;
import org.universAAL.middleware.context.owl.ContextProvider;
import org.universAAL.middleware.context.owl.ContextProviderType;

import utils.CEventData;
import utils.Constants;


public class CPublisher{
	public static final String NAMESPACE = "http://CARESSESuniversAALskeleton.org/CaressesOntology.owl#";
	private ContextPublisher myContextPublisher;
	
	public CPublisher(ModuleContext context) {
		System.out.println("Initiating Publisher");
		ContextProvider myContextProvider = new ContextProvider(NAMESPACE+ "contextProvider");
		myContextProvider.setType(ContextProviderType.gauge);
		myContextProvider.setProvidedEvents(new ContextEventPattern[] {new ContextEventPattern()});
		myContextPublisher = new DefaultContextPublisher(context, myContextProvider);
		}
	
	public void publishContextEvent(ContextEvent ce) {
		// Variable for debugging purposes
		long startTime = System.currentTimeMillis();
		CEventData eventData = new CEventData(ce);	
		myContextPublisher.publish(ce);
		
		if (eventData != null){
			System.out.println("Finish publishing event: ");
			System.out.println(eventData.toString());
			System.out.println("in " + (System.currentTimeMillis() - startTime) + " ms."); 
		}
		
	}
	public void communicationChannelBroken() {
		System.out.println("Connection Lost!!");
	}
	public void close(){
		myContextPublisher.close();

	}

}
