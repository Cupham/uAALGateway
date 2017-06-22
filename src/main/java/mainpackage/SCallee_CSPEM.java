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

public class SCallee_CSPEM extends ServiceCallee {

	protected SCallee_CSPEM(ModuleContext context) {
		super(context, SCallee_CSPEMProvidedService.profiles);
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
		
		if(operation.startsWith(SCallee_CSPEMProvidedService.SERVICE_D7_1)){

			if(Activator.using_socket){
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D7.1");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CSPEMProvidedService.OUTPUT_D7_1, Activator.i_D7_1);
		}

		if(operation.startsWith(SCallee_CSPEMProvidedService.SERVICE_D7_2)){

			if(Activator.using_socket){
				try {
					ConnectionHandler.sendServiceRequestToClient("requested#D7.2");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return getDataMessage(SCallee_CSPEMProvidedService.OUTPUT_D7_2, Activator.i_D7_2);
		}
			
		return null;
	}

	public void communicationChannelBroken() {
		System.out.println(Component.component_ID + " CALLEE: Lost connection with the bus\n");

	}

}
