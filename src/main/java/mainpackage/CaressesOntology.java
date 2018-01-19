package mainpackage;

import java.util.concurrent.ConcurrentHashMap;

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
import ontologies.Curtain;
import ontologies.EchonetDevice;
import ontologies.ElectricConsent;
import ontologies.HealthRelatedOntology;
import ontologies.HomeAirConditioner;
import ontologies.HousingFacilitiesRelatedOntology;
import ontologies.Lighting;
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

			
			// : load AALEnvironment
			oci = createNewOntClassInfo(SmartFacility.MY_URI,factory,factory.SMART_FACILITY);
			oci.setResourceComment("iHouse class");
			oci.setResourceLabel("iHouse");
			oci.addSuperClass(CaressesComponent.MY_URI);
			oci.addObjectProperty(SmartFacility.PROPERTY_HAS_SMART_FACILITY_OUTPUT);
					// : Load EchonetDevice
		oci = createNewOntClassInfo(EchonetDevice.MY_URI, factory, factory.ECHONET_DEVICE);
		oci.setResourceComment("Echonet Device class");
		oci.setResourceLabel("EchonetDevice");
		oci.addSuperClass(Device.MY_URI);	
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_CLASS_CODE);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_CLASS_GROUP_CODE);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_INSTANCE_CODE);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_IP_ADDRESS);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_IS_SMART_FACILITY_COMPONENT);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_OPERATION_STATUS);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_BUSINESS_FACILITY_CODE);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_CUMULATIVE_OPERATING_TIME);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_CURRENT_DATE_SETTING);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_CURRENT_LIMIT_SETTING);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_CURRENT_TIME_SETTING);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_FAULT_DESCRIPTION);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_FAULT_STATUS);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_IDENTIFICATION_NUMBER);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_INSTALLATION_LOCATION);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_MANUFACTURER_CODE);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_MANUFACTURER_FAULT_CODE);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_MEASURED_CUMULATIVE_POWER_CONSUMPTION);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_MEASURED_INSTANTANEOUS_POWER_CONSUMPTION);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_POWER_LIMIT_SETTING);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_POWER_SAVING_OPERATION_SETTING);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_PRODUCT_CODE);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_PRODUCTION_DATE);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_PRODUCTION_NUMBER);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_HAS_REMOTE_CONTROL_SETTING);
		oci.addDatatypeProperty(EchonetDevice.PROPERTY_STANDARD_VERSION_INFORMATION);
		
			// : Load SensorRelatedOntoloty
			oci = createNewOntClassInfo(SensorRelatedOntology.MY_URI, factory, factory.SENSOR_RELATED_ONT);
			oci.setResourceComment("Echonet Sensor Related classes");
			oci.setResourceLabel("EchonetSensorRelated");
			oci.addSuperClass(EchonetDevice.MY_URI);	
			oci.addDatatypeProperty(SensorRelatedOntology.MY_URI);
			
			// : Load AirConditionerRelatedOntology		
			oci = createNewOntClassInfo(AirConditionerRelatedOntology.MY_URI, factory,factory.AIRCONDITIONER_RELATED_ONT);
			oci.setResourceComment("Echonet Air-Conditioner Related class");
			oci.setResourceLabel("EchonetAirConditionerRelated");
			oci.addSuperClass(EchonetDevice.MY_URI);	
			oci.addDatatypeProperty(AirConditionerRelatedOntology.MY_URI);
			
			// : Load AudioVisualRelatedOntology		
			oci = createNewOntClassInfo(AudioVisualRelatedOntology.MY_URI, factory,factory.AUDIO_VISUAL_RELARED_ONT);
			oci.setResourceComment("Audio Visual Related class");
			oci.setResourceLabel("AudioVisualRelated");
			oci.addSuperClass(EchonetDevice.MY_URI);	
			oci.addDatatypeProperty(AudioVisualRelatedOntology.MY_URI);
			
			// : Load CookingHouseholdRelatedOntology		
			oci = createNewOntClassInfo(CookingHouseholdRelatedOntology.MY_URI, factory,factory.COOKING_HOUSEHOLD_RELATED_ONT);
			oci.setResourceComment("Cooking Household Related class");
			oci.setResourceLabel("CookingHouseholdRelated");
			oci.addSuperClass(EchonetDevice.MY_URI);	
			oci.addDatatypeProperty(CookingHouseholdRelatedOntology.MY_URI);
			
			// : Load HealthRelatedOntology		
			oci = createNewOntClassInfo(HealthRelatedOntology.MY_URI, factory,factory.HEALTH_RELATED_ONT);
			oci.setResourceComment("Health Related class");
			oci.setResourceLabel("HealthRelated");
			oci.addSuperClass(EchonetDevice.MY_URI);	
			oci.addDatatypeProperty(HealthRelatedOntology.MY_URI);
			
			// : Load HousingFacilitiesRelatedOntology		
			oci = createNewOntClassInfo(HousingFacilitiesRelatedOntology.MY_URI, factory,factory.HOUSING_FACILITY_RELARED_ONT);
			oci.setResourceComment("Housing Facilities Related class");
			oci.setResourceLabel("HousingFacilitiesRelated");
			oci.addSuperClass(EchonetDevice.MY_URI);	
			oci.addDatatypeProperty(HousingFacilitiesRelatedOntology.MY_URI);
			
			// : Load ManagementOperationRelatedOntology		
			oci = createNewOntClassInfo(ManagementOperationRelatedOntology.MY_URI, factory,factory.MANAGEMENT_OPERATION_RELATED_ONT);
			oci.setResourceComment("Management Operation Related class");
			oci.setResourceLabel("ManagementOperationRelated");
			oci.addSuperClass(EchonetDevice.MY_URI);	
			oci.addDatatypeProperty(ManagementOperationRelatedOntology.MY_URI);
			
				//Load Temperature Sensor
				oci = createNewOntClassInfo(TemperatureSensor.MY_URI, factory, factory.TEMPERATURE_SENSOR);
				oci.setResourceComment("Echonet Temperature Sensor Ontology");
				oci.setResourceLabel("EchonetTemperatureSensor");
				oci.addSuperClass(SensorRelatedOntology.MY_URI);	
				oci.addDatatypeProperty(TemperatureSensor.PROPERTY_HAS_MEASURED_TEMPERATURE_VALUE);	
				oci.addDatatypeProperty(TemperatureSensor.PROPERTY_HAS_OPERATION_STATUS);
				
				//Load Home Airconditioner
				oci = createNewOntClassInfo(HomeAirConditioner.MY_URI, factory, factory.HOME_AIRCONDITIONER);
				oci.setResourceComment("Echonet Home Airconditioner  Ontology");
				oci.setResourceLabel("EchonetHomeAirconditioner");
				oci.addSuperClass(AirConditionerRelatedOntology.MY_URI);	
				oci.addDatatypeProperty(HomeAirConditioner.PROPERTY_HAS_OPERATION_STATUS);
				oci.addDatatypeProperty(HomeAirConditioner.PROPERTY_HAS_OPERATION_POWER_SAVING);
				oci.addDatatypeProperty(HomeAirConditioner.PROPERTY_HAS_AIR_FLOW_RATE_SETTING);
				oci.addDatatypeProperty(HomeAirConditioner.PROPERTY_HAS_OPERATION_MODE_SETTING);
				oci.addDatatypeProperty(HomeAirConditioner.PROPERTY_HAS_MEASURED_ROOM_TEMPERATURE_VALUE);
				oci.addDatatypeProperty(HomeAirConditioner.PROPERTY_HAS_SETTING_TEMPERATURE_VALUE);
				
				//Load Lighting
				oci = createNewOntClassInfo(Lighting.MY_URI, factory, factory.LIGHTING);
				oci.setResourceComment("Echonet Lighting Ontology");
				oci.setResourceLabel("EchonetLighting");
				oci.addSuperClass(HousingFacilitiesRelatedOntology.MY_URI);	
				oci.addDatatypeProperty(Lighting.PROPERTY_HAS_OPERATION_STATUS);
				oci.addDatatypeProperty(Lighting.PROPERTY_HAS_ILLUMINATION_LEVEL);
				
				//Load Lighting
				oci = createNewOntClassInfo(Curtain.MY_URI, factory, factory.CURTAIN);
				oci.setResourceComment("Echonet Curtain Ontology");
				oci.setResourceLabel("EchonetCurtain");
				oci.addSuperClass(ManagementOperationRelatedOntology.MY_URI);	
				oci.addDatatypeProperty(Curtain.PROPERTY_HAS_STATUS);
				//Load Consent
				oci = createNewOntClassInfo(ElectricConsent.MY_URI, factory, factory.CONSENT);
				oci.setResourceComment("Echonet Electric Consent Ontology");
				oci.setResourceLabel("EchonetElectricConsent");
				oci.addSuperClass(SensorRelatedOntology.MY_URI);	
				oci.addDatatypeProperty(ElectricConsent.PROPERTY_HAS_OPERATION_STATUS);
				
				
		// Load Echonet Service
		oci = createNewOntClassInfo(EchonetService.MY_URI, factory, factory.ECHONET_SERVICE);
		oci.setResourceComment("Echonet service");
		oci.setResourceLabel("EchonetService");
		oci.addSuperClass(Service.MY_URI);
		oci.addObjectProperty(EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE).setFunctional();
		oci.addObjectProperty(EchonetService.PROP_PROVIDES_TEMPERATURE_SENSOR_SERVICE).setFunctional();
				
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(EchonetService.PROP_PROVIDES_TEMPERATURE_SENSOR_SERVICE, TemperatureSensor.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(EchonetService.PROP_PROVIDES_AIRCONDTIONER_SERVICE, HomeAirConditioner.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(EchonetService.PROP_PROVIDES_LIGHTING_SERVICE, Lighting.MY_URI));	
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(EchonetService.PROP_PROVIDES_CURTAIN_SERVICE, Curtain.MY_URI));
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(EchonetService.PROP_PROVIDES_CURTAIN_SERVICE, ElectricConsent.MY_URI));
		// end echonet service
				
	}
}
