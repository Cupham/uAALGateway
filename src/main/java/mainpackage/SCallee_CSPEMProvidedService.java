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

public class SCallee_CSPEMProvidedService extends DataMessageService {

	public static ServiceProfile[] profiles = new ServiceProfile[2];
	private static Hashtable serverLevelRestrictions = new Hashtable();

	public static final String NAMESPACE = "http://CARESSESuniversAALskeleton.org/Callee_CSPEM.owl#";
	public static final String MY_URI    = NAMESPACE + "Data_Message_Service";
	
	// : Services

	static final String SERVICE_D7_1 = NAMESPACE + "get_D7.1";
	static final String SERVICE_D7_2 = NAMESPACE + "get_D7.2";
	
	// : Outputs

	static final String OUTPUT_D7_1 = NAMESPACE + "output_D7.1";
	static final String OUTPUT_D7_2 = NAMESPACE + "output_D7.2";
	
	private SCallee_CSPEMProvidedService(String uri){
		super(uri);
//		System.out.println(Component.component_ID + " CALLEE PROVIDED SERVICES\n");
	}
	
	public String getClassURI(){
		return MY_URI;
	}
	
	static{
		OntologyManagement.getInstance().register(Activator.context, new SimpleOntology(MY_URI, DataMessageService.MY_URI, new ResourceFactory(){
			public Resource createInstance(String classURI, String instanceURI, int factoryIndex){
				return new SCallee_CSPEMProvidedService(instanceURI);
			}
		}));
		

		String[] ppD7_1 = new String[]{DataMessageService.PROP_PROVIDES_D7_1_MESSAGE};
		String[] ppD7_2 = new String[]{DataMessageService.PROP_PROVIDES_D7_2_MESSAGE};

		
		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D7_1_MESSAGE).copy(), ppD7_1, serverLevelRestrictions);
		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D7_2_MESSAGE).copy(), ppD7_2, serverLevelRestrictions);

		
		SCallee_CSPEMProvidedService get_D7_1_msg = new SCallee_CSPEMProvidedService(SERVICE_D7_1);
		get_D7_1_msg.addOutput(OUTPUT_D7_1, D7_1.MY_URI, 1, 1, ppD7_1);
		profiles[0] = get_D7_1_msg.myProfile;
		
		SCallee_CSPEMProvidedService get_D7_2_msg = new SCallee_CSPEMProvidedService(SERVICE_D7_2);
		get_D7_2_msg.addOutput(OUTPUT_D7_2, D7_2.MY_URI, 1, 1, ppD7_2);
		profiles[1] = get_D7_2_msg.myProfile;
		
	}

}
