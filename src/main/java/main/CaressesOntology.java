package main;

import java.util.concurrent.ConcurrentHashMap;

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

			
			// : load AALEnvironment
			oci = createNewOntClassInfo(SmartFacility.MY_URI,factory,factory.SMART_FACILITY);
			oci.setResourceComment("iHouse class");
			oci.setResourceLabel("iHouse");
			oci.addSuperClass(CaressesComponent.MY_URI);
			oci.addObjectProperty(SmartFacility.PROPERTY_HAS_SMART_FACILITY_OUTPUT);
				
	}
}
