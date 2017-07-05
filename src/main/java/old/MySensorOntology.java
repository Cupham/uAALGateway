package old;


import org.universAAL.middleware.owl.DataRepOntology;
import org.universAAL.middleware.owl.FloatRestriction;
import org.universAAL.middleware.owl.MergedRestriction;
import org.universAAL.middleware.owl.OntClassInfoSetup;
import org.universAAL.middleware.owl.Ontology;
import org.universAAL.middleware.rdf.Resource;
import org.universAAL.middleware.service.owl.Service;
import org.universAAL.middleware.service.owl.ServiceBusOntology;
import org.universAAL.ontology.DeviceFactory;
import org.universAAL.ontology.device.Sensor;


public class MySensorOntology extends Ontology {
	
	//Global unique namespace
	public static String NAMESPACE="http://ontology.universaal.org/MySenSorOntology.owl#";	
	// init factory
	private static MySenSorFactory factory = new MySenSorFactory();
	
	public MySensorOntology(String ontURI) {
		super(ontURI);
		// TODO Auto-generated constructor stub
	}
	public MySensorOntology(){
		super(NAMESPACE);
	}
	@Override
	public void create() {
		Resource r = getInfo();
		r.setResourceComment("My first sensor ontology ever");
		r.setResourceLabel("MySensor");
		addImport(DataRepOntology.NAMESPACE);
		addImport(ServiceBusOntology.NAMESPACE);
		OntClassInfoSetup oci;
		// Load Sensor\
		oci = createNewOntClassInfo(MySensor.MY_URI,factory,factory.MYSENSOR);
		oci.setResourceComment("The ontology for my own MySensor");
		oci.setResourceLabel("MySensor");
		oci.addSuperClass(Sensor.MY_URI);
		oci.addDatatypeProperty(MySensor.PROP_VALUE).setFunctional();
		oci.addDatatypeProperty(MySensor.PROP_LOCALTION).setFunctional();
		oci.addDatatypeProperty(MySensor.PROP_OPERATION_STATUS).setFunctional();
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(
				MySensor.PROP_VALUE,new FloatRestriction(-80,true,100,true)));
		// Load Sensor Service
		
		oci = createNewOntClassInfo(MySensorService.MY_URI, factory,factory.MYSENSORSERVICE);
		oci.setResourceComment("The ontology for my sensor's services");
		oci.setResourceLabel("MySensorService");
		oci.addSuperClass(Service.MY_URI);
		
		oci.addObjectProperty(MySensorService.PROP_CONTROLS).setFunctional();
		oci.addRestriction(MergedRestriction.getAllValuesRestriction(MySensorService.PROP_CONTROLS, MySensor.MY_URI));	
	}
	

}
