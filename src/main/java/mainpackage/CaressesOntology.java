package mainpackage;

import org.universAAL.middleware.owl.DataRepOntology;
import org.universAAL.middleware.owl.MergedRestriction;
import org.universAAL.middleware.owl.OntClassInfoSetup;
import org.universAAL.middleware.owl.Ontology;
import org.universAAL.middleware.rdf.Resource;
import org.universAAL.middleware.service.owl.Service;
import org.universAAL.middleware.service.owl.ServiceBusOntology;
import org.universAAL.ontology.phThing.Device;

public class CaressesOntology extends Ontology{
	
	public static final String NAMESPACE = "http://CARESSESuniversAALskeleton.org/CaressesOntology.owl#";
	private static CaressesFactory factory = new CaressesFactory();
	
	public CaressesOntology(String ontURI){
		super(ontURI);
	}
	
	public CaressesOntology(){
		super(NAMESPACE);
	}
	
	public void create(){
		Resource r = getInfo();
		r.setResourceComment("Ontology of the universAAL skeleton");
		r.setResourceLabel("UniversAAL skeleton ontology");
		addImport(DataRepOntology.NAMESPACE);
		addImport(ServiceBusOntology.NAMESPACE);
		
		OntClassInfoSetup oci;
		
		// : Load CaressesComponent
		oci = createNewOntClassInfo(CaressesComponent.MY_URI, factory, factory.CARESSES_COMPONENT);
		oci.setResourceComment("Caresses Component class");
		oci.setResourceLabel("CaressesComponent");
		oci.addSuperClass(Device.MY_URI);

			// : Load Ckb
			oci = createNewOntClassInfo(Ckb.MY_URI, factory, factory.CKB);
			oci.setResourceComment("Ckb class");
			oci.setResourceLabel("Ckb");
			oci.addSuperClass(CaressesComponent.MY_URI);
			oci.addObjectProperty(Ckb.PROPERTY_HAS_CKB_OUTPUT);
	
			// : Load Cspem
			oci = createNewOntClassInfo(Cspem.MY_URI, factory, factory.CSPEM);
			oci.setResourceComment("Cspem class");
			oci.setResourceLabel("Cspem");
			oci.addSuperClass(CaressesComponent.MY_URI);
			oci.addObjectProperty(Cspem.PROPERTY_HAS_CSPEM_OUTPUT);
			
			// : Load Cahrim
			oci = createNewOntClassInfo(Cahrim.MY_URI, factory, factory.CAHRIM);
			oci.setResourceComment("Cahrim class");
			oci.setResourceLabel("Cahrim");
			oci.addSuperClass(CaressesComponent.MY_URI);
			oci.addObjectProperty(Cahrim.PROPERTY_HAS_CAHRIM_OUTPUT);
			
			// : load AALEnvironment
			oci = createNewOntClassInfo(AALEnvironment.MY_URI,factory,factory.AALEnvironment);
			oci.setResourceComment("iHouse class");
			oci.setResourceLabel("iHouse");
			oci.addSuperClass(CaressesComponent.MY_URI);
			oci.addObjectProperty(AALEnvironment.PROPERTY_HAS_AALENVIRONMENT_OUTPUT);
		// : Load DataMessage
		oci = createNewOntClassInfo(DataMessage.MY_URI, factory, factory.DATA_MESSAGE);
		oci.setResourceComment("Data Message class");
		oci.setResourceLabel("DataMessage");
		oci.addSuperClass(Device.MY_URI);
		
			// : Load D1
			oci = createNewOntClassInfo(D1.MY_URI, factory, factory.D1);
			oci.setResourceComment("D1 class");
			oci.setResourceLabel("D1");
			oci.addSuperClass(DataMessage.MY_URI);
			oci.addObjectProperty(D1.PROPERTY_IS_CSPEM_INPUT);
			
				// : Load D1_1
				oci = createNewOntClassInfo(D1_1.MY_URI, factory, factory.D1_1);
				oci.setResourceComment("D1_1 class");
				oci.setResourceLabel("D1_1");
				oci.addSuperClass(D1.MY_URI);
				oci.addDatatypeProperty(D1_1.PROPERTY_HAS_D1_1_DESCRIPTION);
				
				// : Load D1_2
				oci = createNewOntClassInfo(D1_2.MY_URI, factory, factory.D1_2);
				oci.setResourceComment("D1_2 class");
				oci.setResourceLabel("D1_2");
				oci.addSuperClass(D1.MY_URI);
				oci.addDatatypeProperty(D1_2.PROPERTY_HAS_D1_2_DESCRIPTION);
		
			// : Load D2
			oci = createNewOntClassInfo(D2.MY_URI, factory, factory.D2);
			oci.setResourceComment("D2 class");
			oci.setResourceLabel("D2");
			oci.addSuperClass(DataMessage.MY_URI);	
			oci.addObjectProperty(D2.PROPERTY_IS_CSPEM_INPUT);
			
				// : Load D2_1
				oci = createNewOntClassInfo(D2_1.MY_URI, factory, factory.D2_1);
				oci.setResourceComment("D2_1 class");
				oci.setResourceLabel("D2_1");
				oci.addSuperClass(D2.MY_URI);
				oci.addDatatypeProperty(D2_1.PROPERTY_HAS_D2_1_DESCRIPTION);
				
				// : Load D2_2
				oci = createNewOntClassInfo(D2_2.MY_URI, factory, factory.D2_2);
				oci.setResourceComment("D2_2 class");
				oci.setResourceLabel("D2_2");
				oci.addSuperClass(D2.MY_URI);
				oci.addDatatypeProperty(D2_2.PROPERTY_HAS_D2_2_DESCRIPTION);
				
				// : Load D2_3
				oci = createNewOntClassInfo(D2_3.MY_URI, factory, factory.D2_3);
				oci.setResourceComment("D2_3 class");
				oci.setResourceLabel("D2_3");
				oci.addSuperClass(D2.MY_URI);
				oci.addDatatypeProperty(D2_3.PROPERTY_HAS_D2_3_DESCRIPTION);
				
				// : Load D2_4
				oci = createNewOntClassInfo(D2_4.MY_URI, factory, factory.D2_4);
				oci.setResourceComment("D2_4 class");
				oci.setResourceLabel("D2_4");
				oci.addSuperClass(D2.MY_URI);
				oci.addDatatypeProperty(D2_4.PROPERTY_HAS_D2_4_DESCRIPTION);
			
			// : Load D3
			oci = createNewOntClassInfo(D3.MY_URI, factory, factory.D3);
			oci.setResourceComment("D3 class");
			oci.setResourceLabel("D3");
			oci.addSuperClass(DataMessage.MY_URI);
			oci.addObjectProperty(D3.PROPERTY_IS_CSPEM_INPUT);
			
				// : Load D3_1
				oci = createNewOntClassInfo(D3_1.MY_URI, factory, factory.D3_1);
				oci.setResourceComment("D3_1 class");
				oci.setResourceLabel("D3_1");
				oci.addSuperClass(D3.MY_URI);
				oci.addDatatypeProperty(D3_1.PROPERTY_HAS_D3_1_DESCRIPTION);
				
				// : Load D3_2
				oci = createNewOntClassInfo(D3_2.MY_URI, factory, factory.D3_2);
				oci.setResourceComment("D3_2 class");
				oci.setResourceLabel("D3_2");
				oci.addSuperClass(D3.MY_URI);
				oci.addDatatypeProperty(D3_2.PROPERTY_HAS_D3_2_DESCRIPTION);

			// : Load D4
			oci = createNewOntClassInfo(D4.MY_URI, factory, factory.D4);
			oci.setResourceComment("D4 class");
			oci.setResourceLabel("D4");
			oci.addSuperClass(DataMessage.MY_URI);	
			oci.addObjectProperty(D4.PROPERTY_IS_CSPEM_INPUT);
			
				// : Load D4_1
				oci = createNewOntClassInfo(D4_1.MY_URI, factory, factory.D4_1);
				oci.setResourceComment("D4_1 class");
				oci.setResourceLabel("D4_1");
				oci.addSuperClass(D4.MY_URI);
				oci.addDatatypeProperty(D4_1.PROPERTY_HAS_D4_1_DESCRIPTION);
				
				// : Load D4_2
				oci = createNewOntClassInfo(D4_2.MY_URI, factory, factory.D4_2);
				oci.setResourceComment("D4_2 class");
				oci.setResourceLabel("D4_2");
				oci.addSuperClass(D4.MY_URI);
				oci.addDatatypeProperty(D4_2.PROPERTY_HAS_D4_2_DESCRIPTION);

			// : Load D5
			oci = createNewOntClassInfo(D5.MY_URI, factory, factory.D5);
			oci.setResourceComment("D5 class");
			oci.setResourceLabel("D5");
			oci.addSuperClass(DataMessage.MY_URI);	
			oci.addObjectProperty(D5.PROPERTY_IS_CKB_INPUT);
			oci.addObjectProperty(D5.PROPERTY_IS_CSPEM_INPUT);
			
				// : Load D5_1
				oci = createNewOntClassInfo(D5_1.MY_URI, factory, factory.D5_1);
				oci.setResourceComment("D5_1 class");
				oci.setResourceLabel("D5_1");
				oci.addSuperClass(D5.MY_URI);
				oci.addDatatypeProperty(D5_1.PROPERTY_HAS_D5_1_DESCRIPTION);
				
				// : Load D5_2
				oci = createNewOntClassInfo(D5_2.MY_URI, factory, factory.D5_2);
				oci.setResourceComment("D5_2 class");
				oci.setResourceLabel("D5_2");
				oci.addSuperClass(D5.MY_URI);
				oci.addDatatypeProperty(D5_2.PROPERTY_HAS_D5_2_DESCRIPTION);
			
			// : Load D6
			oci = createNewOntClassInfo(D6.MY_URI, factory, factory.D6);
			oci.setResourceComment("D6 class");
			oci.setResourceLabel("D6");
			oci.addSuperClass(DataMessage.MY_URI);	
			oci.addObjectProperty(D6.PROPERTY_IS_CSPEM_INPUT);
			
				// : Load D6_1
				oci = createNewOntClassInfo(D6_1.MY_URI, factory, factory.D6_1);
				oci.setResourceComment("D6_1 class");
				oci.setResourceLabel("D6_1");
				oci.addSuperClass(D6.MY_URI);
				oci.addDatatypeProperty(D6_1.PROPERTY_HAS_D6_1_DESCRIPTION);
				
				// : Load D6_2
				oci = createNewOntClassInfo(D6_2.MY_URI, factory, factory.D6_2);
				oci.setResourceComment("D6_2 class");
				oci.setResourceLabel("D6_2");
				oci.addSuperClass(D6.MY_URI);
				oci.addDatatypeProperty(D6_2.PROPERTY_HAS_D6_2_DESCRIPTION);
				
				// : Load D6_3
				oci = createNewOntClassInfo(D6_3.MY_URI, factory, factory.D6_3);
				oci.setResourceComment("D6_3 class");
				oci.setResourceLabel("D6_3");
				oci.addSuperClass(D6.MY_URI);
				oci.addDatatypeProperty(D6_3.PROPERTY_HAS_D6_3_DESCRIPTION);
			
			// : Load D7
			oci = createNewOntClassInfo(D7.MY_URI, factory, factory.D7);
			oci.setResourceComment("D7 class");
			oci.setResourceLabel("D7");
			oci.addSuperClass(DataMessage.MY_URI);	
			oci.addObjectProperty(D7.PROPERTY_IS_CAHRIM_INPUT);
			
				// : Load D7_1
				oci = createNewOntClassInfo(D7_1.MY_URI, factory, factory.D7_1);
				oci.setResourceComment("D7_1 class");
				oci.setResourceLabel("D7_1");
				oci.addSuperClass(D7.MY_URI);
				oci.addDatatypeProperty(D7_1.PROPERTY_HAS_D7_1_DESCRIPTION);
				
				// : Load D7_2
				oci = createNewOntClassInfo(D7_2.MY_URI, factory, factory.D7_2);
				oci.setResourceComment("D7_2 class");
				oci.setResourceLabel("D7_2");
				oci.addSuperClass(D7.MY_URI);
				oci.addDatatypeProperty(D7_2.PROPERTY_HAS_D7_2_DESCRIPTION);
			
			// : Load D8
			oci = createNewOntClassInfo(D8.MY_URI, factory, factory.D8);
			oci.setResourceComment("D8 class");
			oci.setResourceLabel("D8");
			oci.addSuperClass(DataMessage.MY_URI);
			oci.addObjectProperty(D8.PROPERTY_IS_CKB_INPUT);
			
				// : Load D8_1
				oci = createNewOntClassInfo(D8_1.MY_URI, factory, factory.D8_1);
				oci.setResourceComment("D8_1 class");
				oci.setResourceLabel("D8_1");
				oci.addSuperClass(D8.MY_URI);
				oci.addDatatypeProperty(D8_1.PROPERTY_HAS_D8_1_DESCRIPTION);
				
				// : Load D8_2
				oci = createNewOntClassInfo(D8_2.MY_URI, factory, factory.D8_2);
				oci.setResourceComment("D8_2 class");
				oci.setResourceLabel("D8_2");
				oci.addSuperClass(D8.MY_URI);
				oci.addDatatypeProperty(D8_2.PROPERTY_HAS_D8_2_DESCRIPTION);
			
			// : Load D9
			oci = createNewOntClassInfo(D9.MY_URI, factory, factory.D9);
			oci.setResourceComment("D9 class");
			oci.setResourceLabel("D9");
			oci.addSuperClass(DataMessage.MY_URI);	
			
			// : Load D10
			oci = createNewOntClassInfo(D10.MY_URI, factory, factory.D10);
			oci.setResourceComment("D10 class");
			oci.setResourceLabel("D10");
			oci.addSuperClass(DataMessage.MY_URI);	
			
			// : Load D11
			oci = createNewOntClassInfo(D11.MY_URI, factory, factory.D11);
			oci.setResourceComment("D11 class");
			oci.setResourceLabel("D11");
			oci.addSuperClass(DataMessage.MY_URI);	
			oci.addObjectProperty(D11.PROPERTY_IS_CAHRIM_INPUT);
			
				// : Load D11_1
				oci = createNewOntClassInfo(D11_1.MY_URI, factory, factory.D11_1);
				oci.setResourceComment("D11_1 class");
				oci.setResourceLabel("D11_1");
				oci.addSuperClass(D11.MY_URI);
				oci.addDatatypeProperty(D11_1.PROPERTY_HAS_D11_1_DESCRIPTION);
				
				// : Load D11_2
				oci = createNewOntClassInfo(D11_2.MY_URI, factory, factory.D11_2);
				oci.setResourceComment("D11_2 class");
				oci.setResourceLabel("D11_2");
				oci.addSuperClass(D11.MY_URI);
				oci.addDatatypeProperty(D11_2.PROPERTY_HAS_D11_2_DESCRIPTION);
				
				// : Load D11_3
				oci = createNewOntClassInfo(D11_3.MY_URI, factory, factory.D11_3);
				oci.setResourceComment("D11_3 class");
				oci.setResourceLabel("D11_3");
				oci.addSuperClass(D11.MY_URI);
				oci.addDatatypeProperty(D11_3.PROPERTY_HAS_D11_3_DESCRIPTION);
				
				// : Load D11_4
				oci = createNewOntClassInfo(D11_4.MY_URI, factory, factory.D11_4);
				oci.setResourceComment("D11_4 class");
				oci.setResourceLabel("D11_4");
				oci.addSuperClass(D11.MY_URI);
				oci.addDatatypeProperty(D11_4.PROPERTY_HAS_D11_4_DESCRIPTION);
			// : Load DataMessageService
		oci = createNewOntClassInfo(DataMessageService.MY_URI, factory, factory.DATA_MESSAGE_SERVICE);
		oci.setResourceComment("Data message service");
		oci.setResourceLabel("DataMessageService");
		oci.addSuperClass(Service.MY_URI);
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D1_1_MESSAGE).setFunctional();
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D1_2_MESSAGE).setFunctional();
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D2_1_MESSAGE).setFunctional();
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D2_2_MESSAGE).setFunctional();
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D2_3_MESSAGE).setFunctional();
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D2_4_MESSAGE).setFunctional();
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D3_1_MESSAGE).setFunctional();
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D3_2_MESSAGE).setFunctional();
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D4_1_MESSAGE).setFunctional();
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D4_2_MESSAGE).setFunctional();
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D5_1_MESSAGE).setFunctional();
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D5_2_MESSAGE).setFunctional();
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D6_1_MESSAGE).setFunctional();
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D6_2_MESSAGE).setFunctional();
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D6_3_MESSAGE).setFunctional();
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D7_1_MESSAGE).setFunctional();
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D7_2_MESSAGE).setFunctional();
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D8_1_MESSAGE).setFunctional();
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D8_2_MESSAGE).setFunctional();
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D11_1_MESSAGE).setFunctional();
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D11_2_MESSAGE).setFunctional();
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D11_3_MESSAGE).setFunctional();
		oci.addObjectProperty(DataMessageService.PROP_PROVIDES_D11_4_MESSAGE).setFunctional();
		
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D1_1_MESSAGE, D1_1.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D1_2_MESSAGE, D1_2.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D2_1_MESSAGE, D2_1.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D2_2_MESSAGE, D2_2.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D2_3_MESSAGE, D2_3.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D2_4_MESSAGE, D2_4.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D3_1_MESSAGE, D3_1.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D3_2_MESSAGE, D3_2.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D4_1_MESSAGE, D4_1.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D4_2_MESSAGE, D4_2.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D5_1_MESSAGE, D5_1.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D5_2_MESSAGE, D5_2.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D6_1_MESSAGE, D6_1.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D6_2_MESSAGE, D6_2.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D6_3_MESSAGE, D6_3.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D7_1_MESSAGE, D7_1.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D7_2_MESSAGE, D7_2.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D8_1_MESSAGE, D8_1.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D8_2_MESSAGE, D8_2.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D11_1_MESSAGE, D11_1.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D11_2_MESSAGE, D11_2.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D11_3_MESSAGE, D11_3.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(DataMessageService.PROP_PROVIDES_D11_4_MESSAGE, D11_4.MY_URI));

			// : Load EchonetSensor
			oci = createNewOntClassInfo(EchonetSensor.MY_URI, factory, factory.EchonetSensor);
			oci.setResourceComment("Echonet Sensor class");
			oci.setResourceLabel("Echonet Sensor");
			oci.addSuperClass(DataMessage.MY_URI);	
			oci.addObjectProperty(EchonetSensor.PROPERTY_IS_AALENVIRONMENT_INPUT);
				
				// : Load TemperatureSensor
				oci = createNewOntClassInfo(TemperatureSensor.MY_URI, factory, factory.TemperatureSensor);
				oci.setResourceComment("Temperature Sensor class");
				oci.setResourceLabel("Temperature Sensor");
				oci.addSuperClass(EchonetSensor.MY_URI);	
				oci.addObjectProperty(TemperatureSensor.PROPERTY_HAS_TEMPERATURE_SENSOR_DESCRIPTION);	
			
			oci = createNewOntClassInfo(EchonetAirConditioner.MY_URI, factory,factory.EchonetAirconditioner);
			oci.setResourceComment("Echonet Air-Conditioner class");
			oci.setResourceLabel("Echonet Air-Conditioner");
			oci.addSuperClass(DataMessage.MY_URI);	
			oci.addObjectProperty(EchonetAirConditioner.PROPERTY_IS_AALENVIRONMENT_INPUT);
			
				oci = createNewOntClassInfo(HomeAirConditioner.MY_URI, factory, factory.HomeAirconditioner);
				oci.setResourceComment("Echonet Home Air-Conditioner class");
				oci.setResourceLabel("Home Air-Conditioner");
				oci.addSuperClass(EchonetAirConditioner.MY_URI);	
				oci.addObjectProperty(HomeAirConditioner.PROPERTY_HAS_HOME_AIRCONDITIONER_DESCRIPTION);	
		}

}
