package mainpackage;

import org.universAAL.middleware.owl.DataRepOntology;
import org.universAAL.middleware.owl.MergedRestriction;
import org.universAAL.middleware.owl.OntClassInfoSetup;
import org.universAAL.middleware.owl.Ontology;
import org.universAAL.middleware.rdf.Resource;
import org.universAAL.middleware.service.owl.Service;
import org.universAAL.middleware.service.owl.ServiceBusOntology;
import org.universAAL.ontology.phThing.Device;

import ontologies.AirConditionerRelatedOntology;
import ontologies.AudioVisualRelatedOntology;
import ontologies.CookingHouseholdRelatedOntology;
import ontologies.EchonetDevice;
import ontologies.HealthRelatedOntology;
import ontologies.HomeAirConditioner;
import ontologies.HousingFacilitiesRelatedOntology;
import ontologies.ManagementOperationRelatedOntology;
import ontologies.SensorRelatedOntology;
import ontologies.TemperatureSensor;

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
			oci = createNewOntClassInfo(SmartFacility.MY_URI,factory,factory.SMART_FACILITY);
			oci.setResourceComment("iHouse class");
			oci.setResourceLabel("iHouse");
			oci.addSuperClass(CaressesComponent.MY_URI);
			oci.addObjectProperty(SmartFacility.PROPERTY_HAS_SMART_FACILITY_OUTPUT);
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
		
		
					// : Load EchonetDevice
		oci = createNewOntClassInfo(EchonetDevice.MY_URI, factory, factory.ECHONET_DEVICE);
		oci.setResourceComment("Echonet Device class");
		oci.setResourceLabel("EchonetDevice");
		oci.addSuperClass(Device.MY_URI);	
		oci.addObjectProperty(EchonetDevice.PROPERTY_HAS_IP_ADDRESS);
		oci.addObjectProperty(EchonetDevice.PROPERTY_IS_SMART_FACILITY_COMPONENT);
		oci.addObjectProperty(EchonetDevice.PROPERTY_HAS_OPERATION_STATUS);
		oci.addObjectProperty(EchonetDevice.PROPERTY_HAS_BUSINESS_FACILITY_CODE);
		oci.addObjectProperty(EchonetDevice.PROPERTY_HAS_CUMULATIVE_OPERATING_TIME);
		oci.addObjectProperty(EchonetDevice.PROPERTY_HAS_CURRENT_DATE_SETTING);
		oci.addObjectProperty(EchonetDevice.PROPERTY_HAS_CURRENT_LIMIT_SETTING);
		oci.addObjectProperty(EchonetDevice.PROPERTY_HAS_CURRENT_TIME_SETTING);
		oci.addObjectProperty(EchonetDevice.PROPERTY_HAS_FAULT_DESCRIPTION);
		oci.addObjectProperty(EchonetDevice.PROPERTY_HAS_FAULT_STATUS);
		oci.addObjectProperty(EchonetDevice.PROPERTY_HAS_IDENTIFICATION_NUMBER);
		oci.addObjectProperty(EchonetDevice.PROPERTY_HAS_INSTALLATION_LOCATION);
		oci.addObjectProperty(EchonetDevice.PROPERTY_HAS_MANUFACTURER_CODE);
		oci.addObjectProperty(EchonetDevice.PROPERTY_HAS_MANUFACTURER_FAULT_CODE);
		oci.addObjectProperty(EchonetDevice.PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION);
		oci.addObjectProperty(EchonetDevice.PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION);
		oci.addObjectProperty(EchonetDevice.PROPERTY_HAS_POWER_LIMIT_SETTING);
		oci.addObjectProperty(EchonetDevice.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING);
		oci.addObjectProperty(EchonetDevice.PROPERTY_HAS_PRODUCT_CODE);
		oci.addObjectProperty(EchonetDevice.PROPERTY_HAS_PRODUCTION_DATE);
		oci.addObjectProperty(EchonetDevice.PROPERTY_HAS_PRODUCTION_NUMBER);
		oci.addObjectProperty(EchonetDevice.PROPERTY_HAS_REMOTE_CONTROL_SETTING);
		oci.addObjectProperty(EchonetDevice.PROPERTY_STANDARD_VERSION_INFORMATION);
		
			// : Load SensorRelatedOntoloty
			oci = createNewOntClassInfo(SensorRelatedOntology.MY_URI, factory, factory.SENSOR_RELATED_ONT);
			oci.setResourceComment("Echonet Sensor Related classes");
			oci.setResourceLabel("EchonetSensorRelated");
			oci.addSuperClass(EchonetDevice.MY_URI);	
			
			// : Load AirConditionerRelatedOntology		
			oci = createNewOntClassInfo(AirConditionerRelatedOntology.MY_URI, factory,factory.AIRCONDITIONER_RELATED_ONT);
			oci.setResourceComment("Echonet Air-Conditioner Related class");
			oci.setResourceLabel("EchonetAirConditionerRelated");
			oci.addSuperClass(EchonetDevice.MY_URI);	
			
			// : Load AudioVisualRelatedOntology		
			oci = createNewOntClassInfo(AudioVisualRelatedOntology.MY_URI, factory,factory.AUDIO_VISUAL_RELARED_ONT);
			oci.setResourceComment("Audio Visual Related class");
			oci.setResourceLabel("AudioVisualRelated");
			oci.addSuperClass(EchonetDevice.MY_URI);	
			oci.addObjectProperty(AudioVisualRelatedOntology.MY_URI);
			
			// : Load CookingHouseholdRelatedOntology		
			oci = createNewOntClassInfo(CookingHouseholdRelatedOntology.MY_URI, factory,factory.COOKING_HOUSEHOLD_RELATED_ONT);
			oci.setResourceComment("Cooking Household Related class");
			oci.setResourceLabel("CookingHouseholdRelated");
			oci.addSuperClass(EchonetDevice.MY_URI);	
			oci.addObjectProperty(CookingHouseholdRelatedOntology.MY_URI);
			
			// : Load HealthRelatedOntology		
			oci = createNewOntClassInfo(HealthRelatedOntology.MY_URI, factory,factory.HEALTH_RELATED_ONT);
			oci.setResourceComment("Health Related class");
			oci.setResourceLabel("HealthRelated");
			oci.addSuperClass(EchonetDevice.MY_URI);	

			// : Load HousingFacilitiesRelatedOntology		
			oci = createNewOntClassInfo(HousingFacilitiesRelatedOntology.MY_URI, factory,factory.HOUSING_FACILITY_RELARED_ONT);
			oci.setResourceComment("Housing Facilities Related class");
			oci.setResourceLabel("HousingFacilitiesRelated");
			oci.addSuperClass(EchonetDevice.MY_URI);	
			oci.addObjectProperty(HousingFacilitiesRelatedOntology.MY_URI);
			
			// : Load ManagementOperationRelatedOntology		
			oci = createNewOntClassInfo(ManagementOperationRelatedOntology.MY_URI, factory,factory.MANAGEMENT_OPERATION_RELATED_ONT);
			oci.setResourceComment("Management Operation Related class");
			oci.setResourceLabel("ManagementOperationRelated");
			oci.addSuperClass(EchonetDevice.MY_URI);	
			oci.addObjectProperty(ManagementOperationRelatedOntology.MY_URI);
			
				//Load Temperature Sensor
				oci = createNewOntClassInfo(TemperatureSensor.MY_URI, factory, factory.TEMPERATURE_SENSOR);
				oci.setResourceComment("Echonet Temperature Sensor Ontology");
				oci.setResourceLabel("EchonetTemperatureSensor");
				oci.addSuperClass(SensorRelatedOntology.MY_URI);	
				oci.addObjectProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE);	
				oci.addObjectProperty(TemperatureSensor.PROPERTY_HAS_OPERATION_STATUS);
				
				//Load Home Airconditioner
				oci = createNewOntClassInfo(HomeAirConditioner.MY_URI, factory, factory.HOME_AIRCONDITIONER);
				oci.setResourceComment("Echonet Home Airconditioner  Ontology");
				oci.setResourceLabel("EchonetHomeAirconditioner");
				oci.addSuperClass(AirConditionerRelatedOntology.MY_URI);	
				oci.addObjectProperty(HomeAirConditioner.PROPERTY_HAS_OPERATION_STATUS);
				oci.addObjectProperty(HomeAirConditioner.PROPERTY_HAS_OPERATION_POWER_SAVING);
				oci.addObjectProperty(HomeAirConditioner.PROPERTY_HAS_AIR_FLOW_RATE_SETTING);
				oci.addObjectProperty(HomeAirConditioner.PROPERTY_HAS_OPERATION_MODE_SETTING);
				oci.addObjectProperty(HomeAirConditioner.PROPERTY_HAS_MEASURED_ROOM_TEMPERATURE_VALUE);
				oci.addObjectProperty(HomeAirConditioner.PROPERTY_HAS_SETTING_TEMPERATURE_VALUE);
				
		// Load Echonet Service
		oci = createNewOntClassInfo(EchonetService.MY_URI, factory, factory.ECHONET_SERVICE);
		oci.setResourceComment("Echonet service");
		oci.setResourceLabel("EchonetService");
		oci.addSuperClass(Service.MY_URI);
		oci.addObjectProperty(EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE).setFunctional();
		oci.addObjectProperty(EchonetService.PROP_PROVIDES_TEMPERATURE_SENSOR_SERVICE).setFunctional();
				
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(EchonetService.PROP_PROVIDES_TEMPERATURE_SENSOR_SERVICE, TemperatureSensor.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE, HomeAirConditioner.MY_URI));
								
		// end echonet service
				

		}

}
