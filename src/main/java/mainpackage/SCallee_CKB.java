package mainpackage;


/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_6#Providing_services_on_the_bus */
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.service.CallStatus;
import org.universAAL.middleware.service.ServiceCall;
import org.universAAL.middleware.service.ServiceCallee;
import org.universAAL.middleware.service.ServiceResponse;
import org.universAAL.middleware.service.owls.process.ProcessOutput;
import org.universAAL.middleware.service.owls.profile.ServiceProfile;

import java.io.IOException;

public class SCallee_CKB extends ServiceCallee {

	protected SCallee_CKB(ModuleContext context) {
		super(context, SCallee_CKBProvidedService.profiles);
//		System.out.println(Component.component_ID + " CALLEE\n");
	}

	// : Service Responses
	
	private ServiceResponse getDataMessage(String output, DataMessage individual){
		ServiceResponse sr = new ServiceResponse(CallStatus.succeeded);
		sr.addOutput(new ProcessOutput(output, individual.getMessage()));
		return sr;
	}
	
	@Override
	public ServiceResponse handleCall(ServiceCall call) {
		String operation = call.getProcessURI();
		System.out.println(Component.component_ID + " CALLEE: The process URI is " + operation + "\n");
		
		if(operation.startsWith(SCallee_CKBProvidedService.SERVICE_D1_1)){
			if(Activator.using_socket){
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D1.1");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CKBProvidedService.OUTPUT_D1_1, Activator.i_D1_1);
		}

		if(operation.startsWith(SCallee_CKBProvidedService.SERVICE_D1_2)){
			if(Activator.using_socket){
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D1.2");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CKBProvidedService.OUTPUT_D1_2, Activator.i_D1_2);
		}

		if(operation.startsWith(SCallee_CKBProvidedService.SERVICE_D2_1)){
			if(Activator.using_socket){
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D2.1");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CKBProvidedService.OUTPUT_D2_1, Activator.i_D2_1);
		}

		if(operation.startsWith(SCallee_CKBProvidedService.SERVICE_D2_2)){
			if(Activator.using_socket){
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D2.2");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CKBProvidedService.OUTPUT_D2_2, Activator.i_D2_2);
		}

		if(operation.startsWith(SCallee_CKBProvidedService.SERVICE_D2_3)){
			if(Activator.using_socket){
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D2.3");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CKBProvidedService.OUTPUT_D2_3, Activator.i_D2_3);
		}

		if(operation.startsWith(SCallee_CKBProvidedService.SERVICE_D2_4)){
			if(Activator.using_socket){
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D2.4");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CKBProvidedService.OUTPUT_D2_4, Activator.i_D2_4);
		}

		if(operation.startsWith(SCallee_CKBProvidedService.SERVICE_D3_1)){
			if(Activator.using_socket){
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D3.1");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CKBProvidedService.OUTPUT_D3_1, Activator.i_D3_1);
		}

		if(operation.startsWith(SCallee_CKBProvidedService.SERVICE_D3_2)){
			if(Activator.using_socket){
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D3.2");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CKBProvidedService.OUTPUT_D3_2, Activator.i_D3_2);
		}

		if(operation.startsWith(SCallee_CKBProvidedService.SERVICE_D4_1)){
			if(Activator.using_socket){
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D4.1");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CKBProvidedService.OUTPUT_D4_1, Activator.i_D4_1);
		}

		if(operation.startsWith(SCallee_CKBProvidedService.SERVICE_D4_2)){
			if(Activator.using_socket){
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D4.2");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CKBProvidedService.OUTPUT_D4_2, Activator.i_D4_2);
		}

		if(operation.startsWith(SCallee_CKBProvidedService.SERVICE_D11_1)){
			if(Activator.using_socket){
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D11.1");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CKBProvidedService.OUTPUT_D11_1, Activator.i_D11_1);
		}

		if(operation.startsWith(SCallee_CKBProvidedService.SERVICE_D11_2)){
			if(Activator.using_socket){
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D11.2");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CKBProvidedService.OUTPUT_D11_2, Activator.i_D11_2);
		}

		if(operation.startsWith(SCallee_CKBProvidedService.SERVICE_D11_3)){
			if(Activator.using_socket){
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D11.3");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CKBProvidedService.OUTPUT_D11_3, Activator.i_D11_3);
		}

		if(operation.startsWith(SCallee_CKBProvidedService.SERVICE_D11_4)){
			if(Activator.using_socket){
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D11.4");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CKBProvidedService.OUTPUT_D11_4, Activator.i_D11_4);
		}
	
		return null;
	}

	public void communicationChannelBroken() {
		System.out.println(Component.component_ID + " CALLEE: Lost connection with the bus\n");

	}

}
