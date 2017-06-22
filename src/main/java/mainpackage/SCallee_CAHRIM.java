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

public class SCallee_CAHRIM extends ServiceCallee {

	protected SCallee_CAHRIM(ModuleContext context) {
		super(context, SCallee_CAHRIMProvidedService.profiles);
//		System.out.println(Component.component_ID + " CALLEE\n");
	}
	
	// : Service Responses
	
	private ServiceResponse getDataMessage(String output, String message){
		ServiceResponse sr = new ServiceResponse(CallStatus.succeeded);
		sr.addOutput(new ProcessOutput(output, message));
		return sr;
	}
	
	@Override
	public ServiceResponse handleCall(ServiceCall call) {
		String operation = call.getProcessURI();
		System.out.println(Component.component_ID + " CALLEE: The process URI is " + operation + "\n");

		if(operation.startsWith(SCallee_CAHRIMProvidedService.SERVICE_D5_1)){
			if(Activator.using_socket){
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D5.1");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CAHRIMProvidedService.OUTPUT_D5_1, Activator.i_D5_1.getMessage());
		}

		if(operation.startsWith(SCallee_CAHRIMProvidedService.SERVICE_D5_2)){
			if(Activator.using_socket) {
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D5.2");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CAHRIMProvidedService.OUTPUT_D5_2, Activator.i_D5_2.getMessage());
		}

		if(operation.startsWith(SCallee_CAHRIMProvidedService.SERVICE_D6_1)){
			if(Activator.using_socket){
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D6.1");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CAHRIMProvidedService.OUTPUT_D6_1, Activator.i_D6_1.getMessage());
		}

		if(operation.startsWith(SCallee_CAHRIMProvidedService.SERVICE_D6_2)){
			if(Activator.using_socket){
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D6.2");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CAHRIMProvidedService.OUTPUT_D6_2, Activator.i_D6_2.getMessage());
		}

		if(operation.startsWith(SCallee_CAHRIMProvidedService.SERVICE_D6_3)){
			if(Activator.using_socket){
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D6.3");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CAHRIMProvidedService.OUTPUT_D6_3, Activator.i_D6_3.getMessage());
		}

		if(operation.startsWith(SCallee_CAHRIMProvidedService.SERVICE_D8_1)){
			if(Activator.using_socket){
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D8.1");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CAHRIMProvidedService.OUTPUT_D8_1, Activator.i_D8_1.getMessage());
		}

		if(operation.startsWith(SCallee_CAHRIMProvidedService.SERVICE_D8_2)){
			if(Activator.using_socket){
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D8.2");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CAHRIMProvidedService.OUTPUT_D8_2, Activator.i_D8_2.getMessage());
		}
	
		return null;
	}

	public void communicationChannelBroken() {
		System.out.println(Component.component_ID + " CALLEE: Lost connection with the bus\n");

	}

}
