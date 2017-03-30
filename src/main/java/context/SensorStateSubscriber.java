package context;


/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_7 */
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.context.ContextEventPattern;
import org.universAAL.middleware.context.ContextSubscriber;
import org.universAAL.middleware.owl.MergedRestriction;

import sensors.MySensor;

public class SensorStateSubscriber extends ContextSubscriber {


	public SensorStateSubscriber(ModuleContext context) {
		super(context, getPermanentSubscriptions());
		// TODO Auto-generated constructor stub
		System.out.println(" Init subcriber with ID: " + context.getID());
	}

	private static ContextEventPattern[] getPermanentSubscriptions() {
		
		ContextEventPattern cep = new ContextEventPattern();
		cep.addRestriction(MergedRestriction.getAllValuesRestriction(ContextEvent.PROP_RDF_SUBJECT,MySensor.MY_URI));
		System.out.println(" SUBCRIBER: Added event pattern ");
		
		return new ContextEventPattern[] {cep};
	}
	
	public void communicationChannelBroken() {
		// TODO Auto-generated method stub

	}
	

	public void handleContextEvent(ContextEvent event) {
		// TODO Auto-generated method stub
		System.out.println("Received a context event with {SUBJECT:" +event.getSubjectURI()+
				"PREDICATE:"+event.getRDFPredicate()+ "}");
		System.out.println("-----------------------------------------");
		System.out.println(event.toString());
		System.out.println("-----------------------------------------");
		System.out.println(event.getRDFObject().toString());

	}

}
