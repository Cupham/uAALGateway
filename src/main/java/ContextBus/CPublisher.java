package ContextBus;


/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_6#Publishing_context_events */
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.context.ContextEventPattern;
import org.universAAL.middleware.context.ContextPublisher;
import org.universAAL.middleware.context.DefaultContextPublisher;
import org.universAAL.middleware.context.owl.ContextProvider;
import org.universAAL.middleware.context.owl.ContextProviderType;
import org.universAAL.middleware.interfaces.configuration.scope.Scope;
import org.universAAL.middleware.owl.MergedRestriction;
import org.universAAL.middleware.rdf.ScopedResource;

import mainpackage.CaressesOntology;
import ontologies.EchonetDevice;

public class CPublisher {
	private ContextPublisher contextPublisher;
	int counter ;

	public CPublisher(ModuleContext context) {
		ContextProvider cProvider = new ContextProvider(CaressesOntology.NAMESPACE+"EchonetResourcePublisher");
		cProvider.setType(ContextProviderType.gauge);
		ContextEventPattern echonet = new ContextEventPattern();
		echonet.addRestriction(MergedRestriction.getAllValuesRestriction(ContextEvent.PROP_RDF_SUBJECT, EchonetDevice.MY_URI));
		cProvider.setProvidedEvents(new ContextEventPattern[] {echonet});
		contextPublisher = new DefaultContextPublisher(context, cProvider);
		if(contextPublisher != null) {
			System.out.println("Initialize context publisher successfully");
		} else {
			System.out.println("Something went wrong with context publisher");
		}
		counter = 0;
	}
	public boolean publicContextEvent(ContextEvent ce) {
		boolean rs = false;
		if(contextPublisher != null) {	
			contextPublisher.publish(ce);
			counter++;
			System.out.println("Published: " + counter);
			printCEDetail(ce);
			rs = true;
		}
		return rs;	
	}

	public void communicationChannelBroken() {
		System.out.println(" PUBLISHER: Lost connection with the bus\n");

	}
	public static void printCEDetail(ContextEvent ce) {
		StringBuilder msg = new StringBuilder();
		msg.append("   *********************\n");
		msg.append("       SUBJECT URI: " + ce.getSubjectURI()+ "\n");
		msg.append("       SUBJECT TYPE URI: " + ce.getSubjectTypeURI() + "\n");
		msg.append("       PREDICATE: " + ce.getRDFPredicate() + "\n");
		msg.append("       OBJECT: " + ce.getRDFObject() + "\n");
		msg.append("   *********************");
		System.out.println(msg.toString());
	}

}
