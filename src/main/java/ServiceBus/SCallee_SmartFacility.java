package ServiceBus;


/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_6#Providing_services_on_the_bus */
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.service.CallStatus;
import org.universAAL.middleware.service.ServiceCall;
import org.universAAL.middleware.service.ServiceCallee;
import org.universAAL.middleware.service.ServiceResponse;
import org.universAAL.middleware.service.owls.process.ProcessOutput;
import mainpackage.Activator;
import old.TemperatureSensor_odd;

public class SCallee_SmartFacility extends ServiceCallee {

	public SCallee_SmartFacility(ModuleContext context) {
		super(context, SCallee_SmartFacilityProvidedService.profiles);
		System.out.println("Initialized ServiceCallee Successfully");
	}

	public void communicationChannelBroken() {
		// TODO Auto-generated method stub

	}

	public ServiceResponse handleCall(ServiceCall call) {
		String operation = call.getProcessURI();
		
		if(operation == null)
			return null;
		
		if(operation.startsWith(SCallee_SmartFacilityProvidedService.SERVICE_GET_TEMPERATURE_SENSORS)) {
			System.out.println("Executing a call to return TemperatureSensors");
			return getTemperatureSensors();
		} else {
			return null;
		}
	}
	
	public ServiceResponse getTemperatureSensors() {
		ServiceResponse sr = new ServiceResponse(CallStatus.succeeded);
		System.out.println("	Getting RDF Objects from uAAL Objects (TemperatureSensor)");
		if(Activator.temperatureSensorOntologies != null) {
			sr.addOutput(new ProcessOutput(
					SCallee_SmartFacilityProvidedService.OUTPUT_TEMPERATURE_SENSORS
					,Activator.temperatureSensorOntologies));
		}
		//System.out.println("	RDFObjectList<TemperatureSensor>.Size="+Activator.eSensorList.size());
		System.out.println("	RDFObjectList<TemperatureSensor>.get(0):");
		System.out.println("		URI:"+Activator.temperatureSensorOntologies.get(0).getProperty(TemperatureSensor_odd.MY_URI));
		//System.out.println("		Message:"+Activator.temperatureSensorOntologies.get(0).getMessage());
		
		return sr;
	}

}
