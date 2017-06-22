package mainpackage;

/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_5#Ontologies_in_universAAL */
import org.universAAL.middleware.service.owls.profile.ServiceProfile;
import org.universAAL.middleware.owl.MergedRestriction;
import org.universAAL.middleware.owl.OntologyManagement;
import org.universAAL.middleware.owl.SimpleOntology;
import org.universAAL.middleware.rdf.Resource;
import org.universAAL.middleware.rdf.ResourceFactory;
import org.universAAL.middleware.rdf.TypeMapper;
import org.universAAL.middleware.service.owls.profile.ServiceProfile;
import java.util.Hashtable;

public class SCallee_CAHRIMProvidedService extends DataMessageService {

	public static ServiceProfile[] profiles = new ServiceProfile[7];
	private static Hashtable serverLevelRestrictions = new Hashtable();
	
	public static final String NAMESPACE = "http://CARESSESuniversAALskeleton.org/Callee_CAHRIM.owl#";
	public static final String MY_URI    = NAMESPACE + "Data_Message_Service";
	
	// : Services

	static final String SERVICE_D5_1 = NAMESPACE + "get_D5.1";
	static final String SERVICE_D5_2 = NAMESPACE + "get_D5.2";
	static final String SERVICE_D6_1 = NAMESPACE + "get_D6.1";
	static final String SERVICE_D6_2 = NAMESPACE + "get_D6.2";
	static final String SERVICE_D6_3 = NAMESPACE + "get_D6.3";
	static final String SERVICE_D8_1 = NAMESPACE + "get_D8.1";
	static final String SERVICE_D8_2 = NAMESPACE + "get_D8.2";
	
	// : Outputs
	
	static final String OUTPUT_D5_1 = NAMESPACE + "output_D5.1";
	static final String OUTPUT_D5_2 = NAMESPACE + "output_D5.2";
	static final String OUTPUT_D6_1 = NAMESPACE + "output_D6.1";
	static final String OUTPUT_D6_2 = NAMESPACE + "output_D6.2";
	static final String OUTPUT_D6_3 = NAMESPACE + "output_D6.3";
	static final String OUTPUT_D8_1 = NAMESPACE + "output_D8.1";
	static final String OUTPUT_D8_2 = NAMESPACE + "output_D8.2";

	
	private SCallee_CAHRIMProvidedService(String uri){
		super(uri);
//		System.out.println(Component.component_ID + " CALLEE PROVIDED SERVICES\n");
	}
	
	public String getClassURI(){
		return MY_URI;
	}
	
	static{
		OntologyManagement.getInstance().register(Activator.context, new SimpleOntology(MY_URI, DataMessageService.MY_URI, new ResourceFactory(){
			public Resource createInstance(String classURI, String instanceURI, int factoryIndex){
				return new SCallee_CAHRIMProvidedService(instanceURI);
			}
		}));

		String[] ppD5_1 = new String[]{DataMessageService.PROP_PROVIDES_D5_1_MESSAGE};
		String[] ppD5_2 = new String[]{DataMessageService.PROP_PROVIDES_D5_2_MESSAGE};
		String[] ppD6_1 = new String[]{DataMessageService.PROP_PROVIDES_D6_1_MESSAGE};
		String[] ppD6_2 = new String[]{DataMessageService.PROP_PROVIDES_D6_2_MESSAGE};
		String[] ppD6_3 = new String[]{DataMessageService.PROP_PROVIDES_D6_3_MESSAGE};
		String[] ppD8_1 = new String[]{DataMessageService.PROP_PROVIDES_D8_1_MESSAGE};
		String[] ppD8_2 = new String[]{DataMessageService.PROP_PROVIDES_D8_2_MESSAGE};


		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D5_1_MESSAGE).copy(), ppD5_1, serverLevelRestrictions);
		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D5_2_MESSAGE).copy(), ppD5_2, serverLevelRestrictions);
		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D6_1_MESSAGE).copy(), ppD6_1, serverLevelRestrictions);
		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D6_2_MESSAGE).copy(), ppD6_2, serverLevelRestrictions);
		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D6_3_MESSAGE).copy(), ppD6_3, serverLevelRestrictions);
		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D8_1_MESSAGE).copy(), ppD8_1, serverLevelRestrictions);
		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D8_2_MESSAGE).copy(), ppD8_2, serverLevelRestrictions);

		
		SCallee_CAHRIMProvidedService get_D5_1_msg = new SCallee_CAHRIMProvidedService(SERVICE_D5_1);
		get_D5_1_msg.addOutput(OUTPUT_D5_1, D5_1.MY_URI, 1, 1, ppD5_1);
		profiles[0] = get_D5_1_msg.myProfile;
		
		SCallee_CAHRIMProvidedService get_D5_2_msg = new SCallee_CAHRIMProvidedService(SERVICE_D5_2);
		get_D5_2_msg.addOutput(OUTPUT_D5_2, D5_2.MY_URI, 1, 1, ppD5_2);
		profiles[1] = get_D5_2_msg.myProfile;
		
		SCallee_CAHRIMProvidedService get_D6_1_msg = new SCallee_CAHRIMProvidedService(SERVICE_D6_1);
		get_D6_1_msg.addOutput(OUTPUT_D6_1, D6_1.MY_URI, 1, 1, ppD6_1);
		profiles[2] = get_D6_1_msg.myProfile;

		SCallee_CAHRIMProvidedService get_D6_2_msg = new SCallee_CAHRIMProvidedService(SERVICE_D6_2);
		get_D6_2_msg.addOutput(OUTPUT_D6_2, D6_2.MY_URI, 1, 1, ppD6_2);
		profiles[3] = get_D6_2_msg.myProfile;

		SCallee_CAHRIMProvidedService get_D6_3_msg = new SCallee_CAHRIMProvidedService(SERVICE_D6_3);
		get_D6_3_msg.addOutput(OUTPUT_D6_3, D6_3.MY_URI, 1, 1, ppD6_3);
		profiles[4] = get_D6_3_msg.myProfile;

		SCallee_CAHRIMProvidedService get_D8_1_msg = new SCallee_CAHRIMProvidedService(SERVICE_D8_1);
		get_D8_1_msg.addOutput(OUTPUT_D8_1, D8_1.MY_URI, 1, 1, ppD8_1);
		profiles[5] = get_D8_1_msg.myProfile;
		
		SCallee_CAHRIMProvidedService get_D8_2_msg = new SCallee_CAHRIMProvidedService(SERVICE_D8_2);
		get_D8_2_msg.addOutput(OUTPUT_D8_2, D8_2.MY_URI, 1, 1, ppD8_2);
		profiles[6] = get_D8_2_msg.myProfile;
		
	}

}
