package context;


/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_7 */
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingUtilities;

import ont.MySensor;

import org.apache.log4j.Logger;
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.context.ContextEventPattern;
import org.universAAL.middleware.context.ContextSubscriber;
import org.universAAL.middleware.owl.MergedRestriction;

import services.guiClient;

public class SensorStateSubscriber extends ContextSubscriber {
	
	static final Logger logger = Logger.getLogger(SensorStateSubscriber.class);
	StringBuilder logs = new StringBuilder();

	
	ArrayList<MySensor> sensors = new ArrayList<>();
	
	public SensorStateSubscriber(ModuleContext context) {	
		super(context, getPermanentSubscriptions());
		Long startTime = System.currentTimeMillis();
		// TODO Auto-generated constructor stub
		logger.info("A subcriber has been subcribed to Context: " +context.getID() + 
				"within: " + (System.currentTimeMillis()-startTime) + " ms.");
		System.out.println(" Init subcriber with ID: " + context.getID());
	}

	private static ContextEventPattern[] getPermanentSubscriptions() {
		Long startTime = System.currentTimeMillis();
		ContextEventPattern cep = new ContextEventPattern();
		cep.addRestriction(MergedRestriction.getAllValuesRestriction(ContextEvent.PROP_RDF_SUBJECT,MySensor.MY_URI));
		logger.info("Subcriber pattern has been added: " + cep.toString() + 
				"within: " + (System.currentTimeMillis()-startTime) + " ms.");
		System.out.println(" SUBCRIBER: Added event pattern ");
		
		return new ContextEventPattern[] {cep};
	}
	
	public void communicationChannelBroken() {
		// TODO Auto-generated method stub

	}
	

	public void handleContextEvent(ContextEvent event) {
		String ip = null;
		String location = "NULL";
		boolean operationStatus = false;
		float value = 0;
		// TODO Auto-generated method stub
		Long startTime = System.currentTimeMillis();
		String IPADDRESS_PATTERN = 
		        "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
		String ipString = event.getSubjectURI();
		System.out.println("Received Event with SUBJECT: " + ipString);
		//System.out.println("SUBJECT: " + event.getSubjectURI() +  "SUBJECT TYPE: " + event.getSubjectTypeURI()
		//		+ "PREDICATE: " + event.getRDFPredicate() + "OBJECT " + event.getRDFObject());

		MySensor sensor = (MySensor) event.getRDFSubject();
		sensors.add(sensor);
		Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
		Matcher matcher = pattern.matcher(ipString);

		if (matcher.find()) {
			ip = matcher.group();
		} 
		location = sensor.getProperty(MySensor.PROP_LOCALTION).toString();
		String boperationStatus = sensor.getProperty(MySensor.PROP_OPERATION_STATUS).toString();
		operationStatus = Boolean.parseBoolean(boperationStatus);
		String fvalue = sensor.getProperty(MySensor.PROP_VALUE).toString();
		value = Float.parseFloat(fvalue);
		System.out.println("IP: " + ip  +" Location:" + location + " isON: " + operationStatus + " Temperature: " + value);
		
	}
	public void updateDisplay(guiClient GUIInstance, String name, String ip, String location, 
			boolean status) {
		//GUIInstance.createAndShowGUI(name, ip, location, status, temp, panel)
	}

}
