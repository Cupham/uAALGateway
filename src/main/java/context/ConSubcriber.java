package context;


/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_7 */
import ont.MySensor;
import ont.MySensorOntology;

import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.context.ContextEventPattern;
import org.universAAL.middleware.context.ContextSubscriber;
import org.universAAL.middleware.owl.MergedRestriction;
import org.universAAL.ontology.device.LightController;
import org.universAAL.ontology.phThing.Device;

import utils.Constants;
import utils.EchonetDataConverter;

public class ConSubcriber extends ContextSubscriber {

	public ConSubcriber(ModuleContext context) {
		super(context, getPermanentSubscriptions());
		System.out.println(" Init subcriber with ID: " + context.getID());
	}

	private static ContextEventPattern[] getPermanentSubscriptions() {
		ContextEventPattern cep = new ContextEventPattern();
		cep.addRestriction(MergedRestriction.getAllValuesRestriction(ContextEvent.PROP_RDF_SUBJECT,MySensor.MY_URI));
		System.out.println(" SUBCRIBER: Added event pattern ");
		
		return new ContextEventPattern[] {cep};
	}

	public void communicationChannelBroken() {
		System.out.println("Connection Lost");

	}

	public void handleContextEvent(ContextEvent event) {
		String subject = event.getSubjectURI().substring(MySensorOntology.NAMESPACE.length());
		String predicate = event.getRDFPredicate().toString();
		Object object = event.getRDFObject();
		String deviceIP = EchonetDataConverter.getIPAddr(subject);
		if(subject.contains(Constants.TEMPERATURE_EVENT)) {
			MySensor sensor = (MySensor) event.getRDFSubject();
			String location = sensor.getProperty(MySensor.PROP_LOCALTION).toString();
			String boperationStatus = sensor.getProperty(MySensor.PROP_OPERATION_STATUS).toString();
			boolean operationStatus = Boolean.parseBoolean(boperationStatus);
			String fvalue = sensor.getProperty(MySensor.PROP_VALUE).toString();
			float value = Float.parseFloat(fvalue);
			System.out.println("IP: " + deviceIP  +" Location:" + location + " isON: " + operationStatus + " Temperature: " + value);	
			SubcriptionHandler.handleSubcribedMessage(sensor);
		}
		

	}

}
