package uAAL.ContextBus;


import org.apache.log4j.Logger;
/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_6#Publishing_context_events */
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.context.ContextEventPattern;
import org.universAAL.middleware.context.ContextPublisher;
import org.universAAL.middleware.context.DefaultContextPublisher;
import org.universAAL.middleware.context.owl.ContextProvider;
import org.universAAL.middleware.context.owl.ContextProviderType;
import org.universAAL.middleware.owl.MergedRestriction;
import org.universAAL.ontology.echonetontology.EchonetSuperDevice;
import org.universAAL.ontology.echonetontology.sensorRelatedDevices.TemperatureSensor;

import main.Activator;
import main.CaressesOntology;

public class CPublisher {
	private static final Logger LOGGER = Logger.getLogger(CPublisher.class.getName());
	private ContextPublisher contextPublisher;
	static int counter ;

	public CPublisher(ModuleContext context) {
		ContextProvider cProvider = new ContextProvider(Activator.echonet_ontology.NAMESPACE+"EchonetResourcePublisher");
		
		cProvider.setType(ContextProviderType.gauge);
		//ContextEventPattern temperatureSensorEvent = new ContextEventPattern();
		//temperatureSensorEvent.addRestriction(MergedRestriction.getAllValuesRestriction(ContextEvent.PROP_RDF_SUBJECT, TemperatureSensor.MY_URI));
		ContextEventPattern echonet = new ContextEventPattern();
		echonet.addRestriction(MergedRestriction.getAllValuesRestriction(ContextEvent.PROP_RDF_SUBJECT, EchonetSuperDevice.MY_URI));
	
		cProvider.setProvidedEvents(new ContextEventPattern[] {echonet});
		
		contextPublisher = new DefaultContextPublisher(context, cProvider);
		if(contextPublisher != null) {
			LOGGER.info("Initialize context publisher successfully");
		} else {
			LOGGER.error("Something went wrong with context publisher");
		}
		counter = 0;
	}
	public boolean publicContextEvent(ContextEvent ce) {
		boolean rs = false;
		if(Activator.enable_ContextBus) {		
			if(contextPublisher != null) {	
				contextPublisher.publish(ce);
				//counter++;
				//System.out.println("Published: " + counter + " URI: "+ ce.getURI().substring(ContextEvent.CONTEXT_EVENT_URI_PREFIX.length()));
				printCEDetail(ce);
				rs = true;
			}
		} else {
			// do nothing
		}
		return rs;	
	}

	public void communicationChannelBroken() {
		LOGGER.error(" PUBLISHER: Lost connection with the bus\n");

	}
	public static void printCEDetail(ContextEvent ce) {
		StringBuilder msg = new StringBuilder();
		counter++;
		msg.append("Published " + counter);
		msg.append("   *********************\n");
		msg.append("       SUBJECT URI: " + ce.getSubjectURI()+ "\n");
		msg.append("       SUBJECT TYPE URI: " + ce.getSubjectTypeURI() + "\n");
		msg.append("       PREDICATE: " + ce.getRDFPredicate() + "\n");
		msg.append("       OBJECT: " + ce.getRDFObject() + "\n");
		msg.append("   *********************");
		System.out.println(msg.toString());
	}

}
