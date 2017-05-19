package mainpackage;


/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_6#Providing_services_on_the_bus */
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.service.CallStatus;
import org.universAAL.middleware.service.ServiceCall;
import org.universAAL.middleware.service.ServiceCallee;
import org.universAAL.middleware.service.ServiceResponse;
import org.universAAL.middleware.service.owls.process.ProcessOutput;
import org.universAAL.middleware.service.owls.profile.ServiceProfile;

public class TemperatureServiceCallee extends ServiceCallee {
	
	static final String SENSOR_URI_PREFIX = TemperatureServiceCalleeProvidedService.TEMPERATURE_SERVER_NAMESPACE
    		+ "ControlledTemperatureSensors";
    
	private static final ServiceResponse invalidInput = new ServiceResponse(
		    CallStatus.serviceSpecificFailure);
	    static {
		invalidInput.addOutput(new ProcessOutput(
			ServiceResponse.PROP_SERVICE_SPECIFIC_ERROR, "Invalid input!"));
	}
		protected TemperatureServiceCallee(ModuleContext context) {
			super(context, TemperatureServiceCalleeProvidedService.profiles);
			// TODO Auto-generated constructor stub
		}

		public void communicationChannelBroken() {

		}
		
	    public ArrayList<TemperatureSensor> getAllTemperatureSensor() {
	    	if(Activator.eSensorList != null) {
	    		return Activator.eSensorList;
	    	} else {
	    		return null;
	    	}
	    }
	   
	private ServiceResponse getControlledTemperatureSensor() {
		ServiceResponse sr = new ServiceResponse(CallStatus.succeeded);
		if(Activator.eSensorList != null) {
			sr.addOutput(new ProcessOutput(
					TemperatureServiceCalleeProvidedService.OUTPUT_CONTROLLED_TEMPERATURE_SENSORS
					,Activator.eSensorList));
		}
		return sr;
	}
	    


	public ServiceResponse handleCall(ServiceCall call) {
		System.out.println("Received a call : " + call.toString());
		if(call == null)
			return null;
		
		String operation = call.getProcessURI();
		if(operation == null)
			return null;
		if(operation.startsWith(TemperatureServiceCalleeProvidedService.GET_CONTROLLED_TEMPERATURE_SENSORS)) {
			System.out.println("Executing a call ");
			return getControlledTemperatureSensor();
		}
		return null;
	}

}
