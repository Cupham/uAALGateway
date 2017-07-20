package mainpackage;


import java.io.IOException;

/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_7 */
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.context.ContextEventPattern;
import org.universAAL.middleware.context.ContextSubscriber;
import org.universAAL.middleware.owl.MergedRestriction;

import ontologies.SensorRelatedOntology;
import ontologies.TemperatureSensor;


public class CSubscriber extends ContextSubscriber {
	Example example;
	protected CSubscriber(ModuleContext context) {
		super(context, getPermanentSubscriptions());
		System.out.println("Initialized ContextSubcriber Successfully");
	}

	private static ContextEventPattern[] getPermanentSubscriptions() {
		
		// : If the bundle is CAHRIM subscribe only to its inputs
		
		if (Component.is_Cahrim){
			
			ContextEventPattern cep_D7  = new ContextEventPattern();
			ContextEventPattern cep_D11 = new ContextEventPattern();
			
			cep_D7.addRestriction(MergedRestriction.getAllValuesRestriction(ContextEvent.PROP_RDF_SUBJECT, D7.MY_URI));
			cep_D11.addRestriction(MergedRestriction.getAllValuesRestriction(ContextEvent.PROP_RDF_SUBJECT, D11.MY_URI));
			
			return new ContextEventPattern[]{cep_D7, cep_D11};
				
		// : If the bundle is CKB subscribe only to its inputs
			
		} else if (Component.is_Ckb){
			
			ContextEventPattern cep_D5  = new ContextEventPattern();
			ContextEventPattern cep_D8  = new ContextEventPattern();
			
			cep_D5.addRestriction(MergedRestriction.getAllValuesRestriction(ContextEvent.PROP_RDF_SUBJECT, D5.MY_URI));
			cep_D8.addRestriction(MergedRestriction.getAllValuesRestriction(ContextEvent.PROP_RDF_SUBJECT, D8.MY_URI));
			
			return new ContextEventPattern[]{cep_D5, cep_D8};
			
		// : If the bundle is CSPEM subscribe only to its inputs
			
		} else if (Component.is_Cspem){
			
			ContextEventPattern cep_D1  = new ContextEventPattern();
			ContextEventPattern cep_D2  = new ContextEventPattern();
			ContextEventPattern cep_D3  = new ContextEventPattern();
			ContextEventPattern cep_D4  = new ContextEventPattern();
			ContextEventPattern cep_D5  = new ContextEventPattern();
			ContextEventPattern cep_D6  = new ContextEventPattern();
			
			cep_D1.addRestriction(MergedRestriction.getAllValuesRestriction(ContextEvent.PROP_RDF_SUBJECT, D1.MY_URI));
			cep_D2.addRestriction(MergedRestriction.getAllValuesRestriction(ContextEvent.PROP_RDF_SUBJECT, D2.MY_URI));
			cep_D3.addRestriction(MergedRestriction.getAllValuesRestriction(ContextEvent.PROP_RDF_SUBJECT, D3.MY_URI));
			cep_D4.addRestriction(MergedRestriction.getAllValuesRestriction(ContextEvent.PROP_RDF_SUBJECT, D4.MY_URI));
			cep_D5.addRestriction(MergedRestriction.getAllValuesRestriction(ContextEvent.PROP_RDF_SUBJECT, D5.MY_URI));
			cep_D6.addRestriction(MergedRestriction.getAllValuesRestriction(ContextEvent.PROP_RDF_SUBJECT, D6.MY_URI));
			
			return new ContextEventPattern[]{cep_D1, cep_D2, cep_D3, cep_D4, cep_D5, cep_D6};
		} else if(Component.is_AALEnviroment) {
			ContextEventPattern cep_Echonet_Sensor = new ContextEventPattern();
			
			cep_Echonet_Sensor.addRestriction(MergedRestriction.getAllValuesRestriction(ContextEvent.PROP_RDF_SUBJECT, SensorRelatedOntology.MY_URI));
			
			return new ContextEventPattern[] {cep_Echonet_Sensor};
		}
		else {
		
		return new ContextEventPattern[]{};
		}	
		
	}

	public void communicationChannelBroken() {
		System.out.println(Component.component_ID + " SUBSCRIBER: Lost connection with the bus\n");

	}

	public void handleContextEvent(ContextEvent event) {
		
		String theSubject   = event.getSubjectURI().substring(CaressesOntology.NAMESPACE.length());
		String thePredicate = event.getRDFPredicate().substring(CaressesOntology.NAMESPACE.length());
		Object theObject    = event.getRDFObject();
		String theSubjectClass = theSubject.substring(2);
		System.out.println(event.getRDFSubject().toString());
		/*
		if(theSubjectClass.equals("D1.1")){
			if(event.getRDFPredicate().equals(D1_1.PROPERTY_HAS_D1_1_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		}
		if(theSubjectClass.equals("D1.2")){
			if(event.getRDFPredicate().equals(D1_2.PROPERTY_HAS_D1_2_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		}
		if(theSubjectClass.equals("D2.1")){
			if(event.getRDFPredicate().equals(D2_1.PROPERTY_HAS_D2_1_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		}
		if(theSubjectClass.equals("D2.2")){
			if(event.getRDFPredicate().equals(D2_2.PROPERTY_HAS_D2_2_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		}
		if(theSubjectClass.equals("D2.3")){
			if(event.getRDFPredicate().equals(D2_3.PROPERTY_HAS_D2_3_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		}
		if(theSubjectClass.equals("D2.4")){
			if(event.getRDFPredicate().equals(D2_4.PROPERTY_HAS_D2_4_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		}
		if(theSubjectClass.equals("D3.1")){
			if(event.getRDFPredicate().equals(D3_1.PROPERTY_HAS_D3_1_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		}
		if(theSubjectClass.equals("D3.2")){
			if(event.getRDFPredicate().equals(D3_2.PROPERTY_HAS_D3_2_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		}
		if(theSubjectClass.equals("D4.1")){
			if(event.getRDFPredicate().equals(D4_1.PROPERTY_HAS_D4_1_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		}
		if(theSubjectClass.equals("D4.2")){
			if(event.getRDFPredicate().equals(D4_2.PROPERTY_HAS_D4_2_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		}
		if(theSubjectClass.equals("D5.1")){
			if(event.getRDFPredicate().equals(D5_1.PROPERTY_HAS_D5_1_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		}
		if(theSubjectClass.equals("D5.2")){
			if(event.getRDFPredicate().equals(D5_2.PROPERTY_HAS_D5_2_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		}
		if(theSubjectClass.equals("D6.1")){
			if(event.getRDFPredicate().equals(D6_1.PROPERTY_HAS_D6_1_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		}
		if(theSubjectClass.equals("D6.2")){
			if(event.getRDFPredicate().equals(D6_2.PROPERTY_HAS_D6_2_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		}
		if(theSubjectClass.equals("D6.3")){
			if(event.getRDFPredicate().equals(D6_3.PROPERTY_HAS_D6_3_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		}
		if(theSubjectClass.equals("D7.1")){
			if(event.getRDFPredicate().equals(D7_1.PROPERTY_HAS_D7_1_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		}
		if(theSubjectClass.equals("D7.2")){
			if(event.getRDFPredicate().equals(D7_2.PROPERTY_HAS_D7_2_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		}
		if(theSubjectClass.equals("D8.1")){
			if(event.getRDFPredicate().equals(D8_1.PROPERTY_HAS_D8_1_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		}
		if(theSubjectClass.equals("D8.2")){
			if(event.getRDFPredicate().equals(D8_2.PROPERTY_HAS_D8_2_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		}
		if(theSubjectClass.equals("D11.1")){
			if(event.getRDFPredicate().equals(D11_1.PROPERTY_HAS_D11_1_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		}
		if(theSubjectClass.equals("D11.2")){
			if(event.getRDFPredicate().equals(D11_2.PROPERTY_HAS_D11_2_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		}
		if(theSubjectClass.equals("D11.3")){
			if(event.getRDFPredicate().equals(D11_3.PROPERTY_HAS_D11_3_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		}
		if(theSubjectClass.equals("D11.4")){
			if(event.getRDFPredicate().equals(D11_4.PROPERTY_HAS_D11_4_DESCRIPTION)){
				
				OntologyUpdater.updateOntology(theSubjectClass, (String) theObject);
			}
		} if(theSubjectClass.contains("TemperatureSensor")) {	
			theSubjectClass = "TemperatureSensor";
			if(event.getRDFPredicate().equals(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE)) {
				for(int i =0;i<Activator.temperatureSensorOntologies.size();i++) {
					String publisher_response = Activator.cpublisher.publishContextEvent("TemperatureSensor", 
							Activator.temperatureSensorOntologies.get(i).getProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE).toString());
					System.out.println("INFO: " + publisher_response + "\n");
				}
			}
		}
		SubscriptionHandler.handleSubscribedMessage(theSubjectClass);
	    
		try {
			ConnectionHandler.send(theSubjectClass, (String) theObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

}
