package mainpackage;


/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_6#Publishing_context_events */
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.context.ContextEventPattern;
import org.universAAL.middleware.context.ContextPublisher;
import org.universAAL.middleware.context.DefaultContextPublisher;
import org.universAAL.middleware.context.owl.ContextProvider;
import org.universAAL.middleware.context.owl.ContextProviderType;

import ontologies.HomeAirConditioner;
import ontologies.TemperatureSensor;


public class CPublisher {

	private ContextPublisher myContextPublisher;
	
	protected CPublisher(ModuleContext context) {
		
		ContextProvider myContextProvider = new ContextProvider(CaressesOntology.NAMESPACE + "myContextProvider");
		myContextProvider.setType(ContextProviderType.gauge);
		
		myContextProvider.setProvidedEvents(new ContextEventPattern[] {new ContextEventPattern()});
		
		myContextPublisher = new DefaultContextPublisher(context, myContextProvider);
		System.out.println("Initialized ContextPublisher Successfully");
		
	}
	public void publishCE(ContextEvent ce)  {
		if(ce != null) {
			myContextPublisher.publish(ce);
			System.out.println("Published: " +ce.getRDFSubject().toString());
		} else {
			System.out.println("it is null");
		}
	}
	public String publishHomeResource() {
		String answer = "Message type was valid. Message has been published!";
		boolean published = true;
		if(Activator.temperatureSensorOntologies != null) {
			for(TemperatureSensor temp : Activator.temperatureSensorOntologies) {
				ContextEvent event_temp = new ContextEvent(temp, TemperatureSensor.MY_URI);
				myContextPublisher.publish(event_temp);
			}
			published = true;
		} else {
			answer = "ERROR: There is no Temperature Sensor!";
			published = false;
		}
		
		if(Activator.homeAirconditionerOntologies != null) {
			for(HomeAirConditioner airCon : Activator.homeAirconditionerOntologies) {
				ContextEvent event_airCon = new ContextEvent(airCon, HomeAirConditioner.MY_URI);
				myContextPublisher.publish(event_airCon);
			}
			published = true;
		} else {
			answer = "ERROR: There is no Home Airconditioner!";
			if(published) {
				
			} else {
				published = false;
			}
		}
		if (published == true){
			System.out.println(Component.component_ID + " PUBLISHER: I've just published TemperatureSensor and HomeAirconditioner resources");
		}
					
		return answer;
	}
	public String publishContextEvent(String message_type_ID, String message){
		
		String answer = "Message type was valid. Message has been published!";
		boolean published = true;
		
		// : If the bundle is CAHRIM allow publishing only its outputs
		
		if (Component.is_Cahrim){
			
			switch (message_type_ID){
				case "D5.1":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D5_1 = new ContextEvent(Activator.i_D5_1, D5_1.PROPERTY_HAS_D5_1_DESCRIPTION);
					myContextPublisher.publish(event_D5_1);
					break;
				case "D5.2":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D5_2 = new ContextEvent(Activator.i_D5_2, D5_2.PROPERTY_HAS_D5_2_DESCRIPTION);
					myContextPublisher.publish(event_D5_2);
					break;
				case "D6.1":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D6_1 = new ContextEvent(Activator.i_D6_1, D6_1.PROPERTY_HAS_D6_1_DESCRIPTION);
					myContextPublisher.publish(event_D6_1);
					break;
				case "D6.2":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D6_2 = new ContextEvent(Activator.i_D6_2, D6_2.PROPERTY_HAS_D6_2_DESCRIPTION);
					myContextPublisher.publish(event_D6_2);
					break;
				case "D6.3":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D6_3 = new ContextEvent(Activator.i_D6_3, D6_3.PROPERTY_HAS_D6_3_DESCRIPTION);
					myContextPublisher.publish(event_D6_3);
					break;
				case "D8.1":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D8_1 = new ContextEvent(Activator.i_D8_1, D8_1.PROPERTY_HAS_D8_1_DESCRIPTION);
					myContextPublisher.publish(event_D8_1);
					break;
				case "D8.2":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D8_2 = new ContextEvent(Activator.i_D8_2, D8_2.PROPERTY_HAS_D8_2_DESCRIPTION);
					myContextPublisher.publish(event_D8_2);
					break;
				default:
					published = false;
					answer = "ERROR: invalid message type! CAHRIM can publish only D5, D6, D8 messages!";
			}
			
		// : If the bundle is CKB allow publishing only its outputs
			
		} else if (Component.is_Ckb){
			
			switch (message_type_ID){
				case "D1.1":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D1_1 = new ContextEvent(Activator.i_D1_1, D1_1.PROPERTY_HAS_D1_1_DESCRIPTION);
					myContextPublisher.publish(event_D1_1);
					break;
				case "D1.2":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D1_2 = new ContextEvent(Activator.i_D1_2, D1_2.PROPERTY_HAS_D1_2_DESCRIPTION);
					myContextPublisher.publish(event_D1_2);
					break;
				case "D2.1":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D2_1 = new ContextEvent(Activator.i_D2_1, D2_1.PROPERTY_HAS_D2_1_DESCRIPTION);
					myContextPublisher.publish(event_D2_1);
					break;
				case "D2.2":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D2_2 = new ContextEvent(Activator.i_D2_2, D2_2.PROPERTY_HAS_D2_2_DESCRIPTION);
					myContextPublisher.publish(event_D2_2);
					break;
				case "D2.3":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D2_3 = new ContextEvent(Activator.i_D2_3, D2_3.PROPERTY_HAS_D2_3_DESCRIPTION);
					myContextPublisher.publish(event_D2_3);
					break;
				case "D2.4":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D2_4 = new ContextEvent(Activator.i_D2_4, D2_4.PROPERTY_HAS_D2_4_DESCRIPTION);
					myContextPublisher.publish(event_D2_4);
					break;
				case "D3.1":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D3_1 = new ContextEvent(Activator.i_D3_1, D3_1.PROPERTY_HAS_D3_1_DESCRIPTION);
					myContextPublisher.publish(event_D3_1);
					break;
				case "D3.2":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D3_2 = new ContextEvent(Activator.i_D3_2, D3_2.PROPERTY_HAS_D3_2_DESCRIPTION);
					myContextPublisher.publish(event_D3_2);
					break;
				case "D4.1":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D4_1 = new ContextEvent(Activator.i_D4_1, D4_1.PROPERTY_HAS_D4_1_DESCRIPTION);
					myContextPublisher.publish(event_D4_1);
					break;
				case "D4.2":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D4_2 = new ContextEvent(Activator.i_D4_2, D4_2.PROPERTY_HAS_D4_2_DESCRIPTION);
					myContextPublisher.publish(event_D4_2);
					break;
				case "D11.1":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D11_1 = new ContextEvent(Activator.i_D11_1, D11_1.PROPERTY_HAS_D11_1_DESCRIPTION);
					myContextPublisher.publish(event_D11_1);
					break;
				case "D11.2":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D11_2 = new ContextEvent(Activator.i_D11_2, D11_2.PROPERTY_HAS_D11_2_DESCRIPTION);
					myContextPublisher.publish(event_D11_2);
					break;
				case "D11.3":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D11_3 = new ContextEvent(Activator.i_D11_3, D11_3.PROPERTY_HAS_D11_3_DESCRIPTION);
					myContextPublisher.publish(event_D11_3);
					break;
				case "D11.4":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D11_4 = new ContextEvent(Activator.i_D11_4, D11_4.PROPERTY_HAS_D11_4_DESCRIPTION);
					myContextPublisher.publish(event_D11_4);
					break;
				default:
					published = false;
					answer = "ERROR: invalid message type! CKB can publish only D1, D2, D3, D4, D11 messages!";
			}
			
		// : If the bundle is CSPEM allow publishing only its outputs
			
		} else if (Component.is_Cspem){
			
			switch (message_type_ID){
			
				case "D7.1":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D7_1 = new ContextEvent(Activator.i_D7_1, D7_1.PROPERTY_HAS_D7_1_DESCRIPTION);
					myContextPublisher.publish(event_D7_1);
					break;
				case "D7.2":
					OntologyUpdater.updateOntology(message_type_ID, message);
					ContextEvent event_D7_2 = new ContextEvent(Activator.i_D7_2, D7_2.PROPERTY_HAS_D7_2_DESCRIPTION);
					myContextPublisher.publish(event_D7_2);
					break;
				default:
					published = false;
					answer = "ERROR: invalid message type! CSPEM can publish only D7 messages!";
			}		
		} else if(Component.is_AALEnviroment) {
			switch (message_type_ID) {
			case "TemperatureSensor":
				OntologyUpdater.updateOntology(message_type_ID, message);
				ContextEvent event_TemperatureSensor = new ContextEvent(Activator.i_TemperatureSensor,TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE);
				myContextPublisher.publish(event_TemperatureSensor);
				break;
			default:
				published = false;
				answer = "ERROR: invalid message type! AALEnvironmennt can publish only EchonetSensor messages!";
				break;
			}
		}
		
		if (published == true){
			System.out.println(String.format(Component.component_ID + " PUBLISHER: I've just published a message of type %s \n", message_type_ID));
		}
					
		return answer;
	}


	public void communicationChannelBroken() {
		System.out.println(Component.component_ID + " PUBLISHER: Lost connection with the bus\n");

	}
	
	public void close(){
		myContextPublisher.close();

	}


}
