package ontologies;

import org.universAAL.ontology.phThing.Device;

import mainpackage.CaressesOntology;

public class EchonetDevice extends Device{
	public static final String MY_URI = CaressesOntology.NAMESPACE + "echonet_device";
	public static final String PROPERTY_IS_SMART_FACILITY_COMPONENT = CaressesOntology.NAMESPACE + "is_smart_facility_component";
	public static final String PROPERTY_HAS_OPERATION_STATUS = CaressesOntology.NAMESPACE + "echonet_device_has_operation_status";
	public static final String PROPERTY_HAS_IP_ADDRESS = CaressesOntology.NAMESPACE + "echonet_device_has_IP_address";
	public static final String PROPERTY_HAS_DATA_OBJECT = CaressesOntology.NAMESPACE + "echonet_device_has_data_object";
	public static final String PROPERTY_HAS_PROFILE_OBJECT = CaressesOntology.NAMESPACE + "echonet_device_has_profile_object";
	
	public EchonetDevice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EchonetDevice(String uri) {
		super(uri);
		// TODO Auto-generated constructor stub
	}
	

	
	
}
