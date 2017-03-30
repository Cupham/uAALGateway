package context;


/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_6#Publishing_context_events */
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.context.ContextEventPattern;
import org.universAAL.middleware.context.ContextPublisher;
import org.universAAL.middleware.context.DefaultContextPublisher;
import org.universAAL.middleware.context.owl.ContextProvider;
import org.universAAL.middleware.context.owl.ContextProviderType;

import sensors.MySensor;
import utils.EchonetConnect;

public class SensorPublisher {
	
	private ContextPublisher mySensorContextPublisher;

	public SensorPublisher(ModuleContext context) {
		ContextProvider myContextProvider = new ContextProvider(
				"http://ontology.universaal.org/MySenSorOntology.owl#MySensorContextProvider");
		myContextProvider.setType(ContextProviderType.gauge);
		myContextProvider.setProvidedEvents(new ContextEventPattern[] {
				new ContextEventPattern()
		});
		mySensorContextPublisher = new DefaultContextPublisher(context, myContextProvider);
		
		System.out.println("Init a publisher with ID: " + mySensorContextPublisher.getMyID());
	}
	
	public void publishContextEvent() {
		MySensor sensor = new MySensor("http://ontology.universaal.org/MySenSorOntology.owl#mysensor1");
		float temp = EchonetConnect.getTemp();
		//float temp = 5;
		sensor.setMySensorValue(temp);
		ContextEvent mySensorContextEvent = new ContextEvent(sensor, MySensor.PROP_VALUE);
		
		mySensorContextPublisher.publish(mySensorContextEvent);
		System.out.println("Publisher ID: " + mySensorContextPublisher.getMyID() 
				+" Published  an event with URI: "+mySensorContextEvent.getURI());
	}

	
	public void close() {
		System.out.println("Closing a publisher with ID: " + mySensorContextPublisher.getMyID());
		mySensorContextPublisher.close();
		
	}

	public void communicationChannelBroken() {
		// TODO Auto-generated method stub
		System.out.println("There is a problem with connection!!");
	}



}
