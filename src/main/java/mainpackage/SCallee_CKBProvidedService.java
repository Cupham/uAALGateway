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

public class SCallee_CKBProvidedService extends DataMessageService {

	public static ServiceProfile[] profiles = new ServiceProfile[14];
	private static Hashtable serverLevelRestrictions = new Hashtable();
	public static final String NAMESPACE = "http://CARESSESuniversAALskeleton.org/Callee_CKB.owl#";
	public static final String MY_URI    = NAMESPACE + "Data_Message_Service";
	
	// : Services
	
	static final String SERVICE_D1_1 = NAMESPACE + "get_D1.1";
	static final String SERVICE_D1_2 = NAMESPACE + "get_D1.2";
	static final String SERVICE_D2_1 = NAMESPACE + "get_D2.1";
	static final String SERVICE_D2_2 = NAMESPACE + "get_D2.2";
	static final String SERVICE_D2_3 = NAMESPACE + "get_D2.3";
	static final String SERVICE_D2_4 = NAMESPACE + "get_D2.4";
	static final String SERVICE_D3_1 = NAMESPACE + "get_D3.1";
	static final String SERVICE_D3_2 = NAMESPACE + "get_D3.2";
	static final String SERVICE_D4_1 = NAMESPACE + "get_D4.1";
	static final String SERVICE_D4_2 = NAMESPACE + "get_D4.2";
	static final String SERVICE_D11_1 = NAMESPACE + "get_D11.1";
	static final String SERVICE_D11_2 = NAMESPACE + "get_D11.2";
	static final String SERVICE_D11_3 = NAMESPACE + "get_D11.3";
	static final String SERVICE_D11_4 = NAMESPACE + "get_D11.4";
	
	// : Outputs
	
	static final String OUTPUT_D1_1 = NAMESPACE + "output_D1.1";
	static final String OUTPUT_D1_2 = NAMESPACE + "output_D1.2";
	static final String OUTPUT_D2_1 = NAMESPACE + "output_D2.1";
	static final String OUTPUT_D2_2 = NAMESPACE + "output_D2.2";
	static final String OUTPUT_D2_3 = NAMESPACE + "output_D2.3";
	static final String OUTPUT_D2_4 = NAMESPACE + "output_D2.4";
	static final String OUTPUT_D3_1 = NAMESPACE + "output_D3.1";
	static final String OUTPUT_D3_2 = NAMESPACE + "output_D3.2";
	static final String OUTPUT_D4_1 = NAMESPACE + "output_D4.1";
	static final String OUTPUT_D4_2 = NAMESPACE + "output_D4.2";
	static final String OUTPUT_D11_1 = NAMESPACE + "output_D11.1";
	static final String OUTPUT_D11_2 = NAMESPACE + "output_D11.2";
	static final String OUTPUT_D11_3 = NAMESPACE + "output_D11.3";
	static final String OUTPUT_D11_4 = NAMESPACE + "output_D11.4";
	
	private SCallee_CKBProvidedService(String uri){
		super(uri);
//		System.out.println(Component.component_ID + " CALLEE PROVIDED SERVICES\n");

	}
	
	public String getClassURI(){
		return MY_URI;
	}
	
	static{
		OntologyManagement.getInstance().register(Activator.context, new SimpleOntology(MY_URI, DataMessageService.MY_URI, new ResourceFactory(){
			public Resource createInstance(String classURI, String instanceURI, int factoryIndex){
				return new SCallee_CKBProvidedService(instanceURI);
			}
		}));
		
		String[] ppD1_1 = new String[]{DataMessageService.PROP_PROVIDES_D1_1_MESSAGE};
		String[] ppD1_2 = new String[]{DataMessageService.PROP_PROVIDES_D1_2_MESSAGE};
		String[] ppD2_1 = new String[]{DataMessageService.PROP_PROVIDES_D2_1_MESSAGE};
		String[] ppD2_2 = new String[]{DataMessageService.PROP_PROVIDES_D2_2_MESSAGE};
		String[] ppD2_3 = new String[]{DataMessageService.PROP_PROVIDES_D2_3_MESSAGE};
		String[] ppD2_4 = new String[]{DataMessageService.PROP_PROVIDES_D2_4_MESSAGE};
		String[] ppD3_1 = new String[]{DataMessageService.PROP_PROVIDES_D3_1_MESSAGE};
		String[] ppD3_2 = new String[]{DataMessageService.PROP_PROVIDES_D3_2_MESSAGE};
		String[] ppD4_1 = new String[]{DataMessageService.PROP_PROVIDES_D4_1_MESSAGE};
		String[] ppD4_2 = new String[]{DataMessageService.PROP_PROVIDES_D4_2_MESSAGE};
		String[] ppD11_1 = new String[]{DataMessageService.PROP_PROVIDES_D11_1_MESSAGE};
		String[] ppD11_2 = new String[]{DataMessageService.PROP_PROVIDES_D11_2_MESSAGE};
		String[] ppD11_3 = new String[]{DataMessageService.PROP_PROVIDES_D11_3_MESSAGE};
		String[] ppD11_4 = new String[]{DataMessageService.PROP_PROVIDES_D11_4_MESSAGE};

		
		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D1_1_MESSAGE).copy(), ppD1_1, serverLevelRestrictions);
		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D1_2_MESSAGE).copy(), ppD1_2, serverLevelRestrictions);
		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D2_1_MESSAGE).copy(), ppD2_1, serverLevelRestrictions);
		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D2_2_MESSAGE).copy(), ppD2_2, serverLevelRestrictions);
		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D2_3_MESSAGE).copy(), ppD2_3, serverLevelRestrictions);
		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D2_4_MESSAGE).copy(), ppD2_4, serverLevelRestrictions);
		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D3_1_MESSAGE).copy(), ppD3_1, serverLevelRestrictions);
		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D3_2_MESSAGE).copy(), ppD3_2, serverLevelRestrictions);
		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D4_1_MESSAGE).copy(), ppD4_1, serverLevelRestrictions);
		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D4_2_MESSAGE).copy(), ppD4_2, serverLevelRestrictions);
		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D11_1_MESSAGE).copy(), ppD11_1, serverLevelRestrictions);
		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D11_2_MESSAGE).copy(), ppD11_2, serverLevelRestrictions);
		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D11_3_MESSAGE).copy(), ppD11_3, serverLevelRestrictions);
		addRestriction((MergedRestriction)DataMessageService.getClassRestrictionsOnProperty(
				DataMessageService.MY_URI, DataMessageService.PROP_PROVIDES_D11_4_MESSAGE).copy(), ppD11_4, serverLevelRestrictions);

		
		SCallee_CKBProvidedService get_D1_1_msg = new SCallee_CKBProvidedService(SERVICE_D1_1);
		get_D1_1_msg.addOutput(OUTPUT_D1_1, D1_1.MY_URI, 1, 1, ppD1_1);
		profiles[0] = get_D1_1_msg.myProfile;

		SCallee_CKBProvidedService get_D1_2_msg = new SCallee_CKBProvidedService(SERVICE_D1_2);
		get_D1_2_msg.addOutput(OUTPUT_D1_2, D1_2.MY_URI, 1, 1, ppD1_2);
		profiles[1] = get_D1_2_msg.myProfile;

		SCallee_CKBProvidedService get_D2_1_msg = new SCallee_CKBProvidedService(SERVICE_D2_1);
		get_D2_1_msg.addOutput(OUTPUT_D2_1, D2_1.MY_URI, 1, 1, ppD2_1);
		profiles[2] = get_D2_1_msg.myProfile;
		
		SCallee_CKBProvidedService get_D2_2_msg = new SCallee_CKBProvidedService(SERVICE_D2_2);
		get_D2_2_msg.addOutput(OUTPUT_D2_2, D2_2.MY_URI, 1, 1, ppD2_2);
		profiles[3] = get_D2_2_msg.myProfile;
		
		SCallee_CKBProvidedService get_D2_3_msg = new SCallee_CKBProvidedService(SERVICE_D2_3);
		get_D2_3_msg.addOutput(OUTPUT_D2_3, D2_3.MY_URI, 1, 1, ppD2_3);
		profiles[4] = get_D2_3_msg.myProfile;
		
		SCallee_CKBProvidedService get_D2_4_msg = new SCallee_CKBProvidedService(SERVICE_D2_4);
		get_D2_4_msg.addOutput(OUTPUT_D2_4, D2_4.MY_URI, 1, 1, ppD2_4);
		profiles[5] = get_D2_4_msg.myProfile;
		
		SCallee_CKBProvidedService get_D3_1_msg = new SCallee_CKBProvidedService(SERVICE_D3_1);
		get_D3_1_msg.addOutput(OUTPUT_D3_1, D3_1.MY_URI, 1, 1, ppD3_1);
		profiles[6] = get_D3_1_msg.myProfile;

		SCallee_CKBProvidedService get_D3_2_msg = new SCallee_CKBProvidedService(SERVICE_D3_2);
		get_D3_2_msg.addOutput(OUTPUT_D3_2, D3_2.MY_URI, 1, 1, ppD3_2);
		profiles[7] = get_D3_2_msg.myProfile;

		SCallee_CKBProvidedService get_D4_1_msg = new SCallee_CKBProvidedService(SERVICE_D4_1);
		get_D4_1_msg.addOutput(OUTPUT_D4_1, D4_1.MY_URI, 1, 1, ppD4_1);
		profiles[8] = get_D4_1_msg.myProfile;
		
		SCallee_CKBProvidedService get_D4_2_msg = new SCallee_CKBProvidedService(SERVICE_D4_2);
		get_D4_2_msg.addOutput(OUTPUT_D4_2, D4_2.MY_URI, 1, 1, ppD4_2);
		profiles[9] = get_D4_2_msg.myProfile;
		
		SCallee_CKBProvidedService get_D11_1_msg = new SCallee_CKBProvidedService(SERVICE_D11_1);
		get_D11_1_msg.addOutput(OUTPUT_D11_1, D11_1.MY_URI, 1, 1, ppD11_1);
		profiles[10] = get_D11_1_msg.myProfile;

		SCallee_CKBProvidedService get_D11_2_msg = new SCallee_CKBProvidedService(SERVICE_D11_2);
		get_D11_2_msg.addOutput(OUTPUT_D11_2, D11_2.MY_URI, 1, 1, ppD11_2);
		profiles[11] = get_D11_2_msg.myProfile;
		
		SCallee_CKBProvidedService get_D11_3_msg = new SCallee_CKBProvidedService(SERVICE_D11_3);
		get_D11_3_msg.addOutput(OUTPUT_D11_3, D11_3.MY_URI, 1, 1, ppD11_3);
		profiles[12] = get_D11_3_msg.myProfile;
		
		SCallee_CKBProvidedService get_D11_4_msg = new SCallee_CKBProvidedService(SERVICE_D11_4);
		get_D11_4_msg.addOutput(OUTPUT_D11_4, D11_4.MY_URI, 1, 1, ppD11_4);
		profiles[13] = get_D11_4_msg.myProfile;
		
	}

}
