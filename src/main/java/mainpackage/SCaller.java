package mainpackage;


/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_7 */
import java.util.List;

import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.service.CallStatus;
import org.universAAL.middleware.service.DefaultServiceCaller;
import org.universAAL.middleware.service.ServiceCaller;
import org.universAAL.middleware.service.ServiceRequest;
import org.universAAL.middleware.service.ServiceResponse;

public class SCaller extends ServiceCaller {
	
	private static ServiceCaller caller;
	private static final String CALLER_NAMESPACE = "http://CARESSESuniversAALskeleton.org/Caller.owl#";
	private static final String OUTPUT_D1_1 = CALLER_NAMESPACE + "output_D1.1";
	private static final String OUTPUT_D1_2 = CALLER_NAMESPACE + "output_D1.2";
	private static final String OUTPUT_D2_1 = CALLER_NAMESPACE + "output_D2.1";
	private static final String OUTPUT_D2_2 = CALLER_NAMESPACE + "output_D2.2";
	private static final String OUTPUT_D2_3 = CALLER_NAMESPACE + "output_D2.3";
	private static final String OUTPUT_D2_4 = CALLER_NAMESPACE + "output_D2.4";
	private static final String OUTPUT_D3_1 = CALLER_NAMESPACE + "output_D3.1";
	private static final String OUTPUT_D3_2 = CALLER_NAMESPACE + "output_D3.2";
	private static final String OUTPUT_D4_1 = CALLER_NAMESPACE + "output_D4.1";
	private static final String OUTPUT_D4_2 = CALLER_NAMESPACE + "output_D4.2";
	private static final String OUTPUT_D5_1 = CALLER_NAMESPACE + "output_D5.1";
	private static final String OUTPUT_D5_2 = CALLER_NAMESPACE + "output_D5.2";
	private static final String OUTPUT_D6_1 = CALLER_NAMESPACE + "output_D6.1";
	private static final String OUTPUT_D6_2 = CALLER_NAMESPACE + "output_D6.2";
	private static final String OUTPUT_D6_3 = CALLER_NAMESPACE + "output_D6.3";
	private static final String OUTPUT_D7_1 = CALLER_NAMESPACE + "output_D7.1";
	private static final String OUTPUT_D7_2 = CALLER_NAMESPACE + "output_D7.2";
	private static final String OUTPUT_D8_1 = CALLER_NAMESPACE + "output_D8.1";
	private static final String OUTPUT_D8_2 = CALLER_NAMESPACE + "output_D8.2";
	private static final String OUTPUT_D11_1 = CALLER_NAMESPACE + "output_D11.1";
	private static final String OUTPUT_D11_2 = CALLER_NAMESPACE + "output_D11.2";
	private static final String OUTPUT_D11_3 = CALLER_NAMESPACE + "output_D11.3";
	private static final String OUTPUT_D11_4 = CALLER_NAMESPACE + "output_D11.4";

	protected SCaller(ModuleContext context) {
		super(context);
		caller = new DefaultServiceCaller(context);
//		System.out.println(Component.component_ID + " CALLER\n"); System.out.flush();
	}

	
	// : Requests
	
	public ServiceRequest getDataMessageRequest(String message_type_ID){
		
		ServiceRequest dataMessageRequest = new ServiceRequest(new DataMessageService(), null);
		
		String output = CALLER_NAMESPACE + "output_" + message_type_ID;
		String[] service_property = new String[]{String.format(CaressesOntology.NAMESPACE + "provides_%s_message", message_type_ID)};
		
		dataMessageRequest.addRequiredOutput(output, service_property);

		return dataMessageRequest;
	}
	
	private boolean isIn(String word, String[] inoutputs){
		
		for (int i = 0; i < inoutputs.length; i++){
			if (inoutputs[i].equals(word)){
				return true;
			}
		}
		
		return false;
	}
	
	// : Methods
	
	public String requestMessage(String message_type_ID){
		
		boolean request_allowed = false;	
		
		System.out.println(String.format(Component.component_ID + " CALLER: Requesting a message of type %s \n", message_type_ID));
		
		if (Component.is_Cahrim){
			
			if (isIn(message_type_ID, Activator.cahrim_inputs)){
				request_allowed = true;
			}
			
		} else if (Component.is_Ckb){
			
			if (isIn(message_type_ID, Activator.ckb_inputs)){
				request_allowed = true;
			}
			
		} else if (Component.is_Cspem){
			
			if (isIn(message_type_ID, Activator.cspem_inputs)){
				request_allowed = true;
			}
			
		}
		
		if (request_allowed){
			
			ServiceResponse sr = caller.call(getDataMessageRequest(message_type_ID));
			System.out.println(Component.component_ID + " CALLER: Call status: " + sr.getCallStatus() + "\n");
			
			if ((sr.getCallStatus() == CallStatus.succeeded)){
				List<Object> outputs = sr.getOutput(CALLER_NAMESPACE + "output_" + message_type_ID);
				OntologyUpdater.updateOntology(message_type_ID, (String) outputs.get(0));
				String service_response_message = "provided#" + message_type_ID + "#" + outputs.get(0);
				return service_response_message;
			} else {
				return "INFO: No service found!";
			}
			
		} else {
			return String.format("ERROR: invalid message type! %s cannot request this type of message!", Component.component_ID);	
		}
		
	}
	
	// : Response handling
	
	public void communicationChannelBroken() {
		System.out.println(Component.component_ID + " CALLER: Lost connection with the bus\n");

	}

	public void handleResponse(String reqID, ServiceResponse response) {
		// TODO Auto-generated method stub

	}

}
