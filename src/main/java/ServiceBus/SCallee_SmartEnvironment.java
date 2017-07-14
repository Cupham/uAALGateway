package ServiceBus;


import java.awt.List;
import java.util.ArrayList;

/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_6#Providing_services_on_the_bus */
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.service.CallStatus;
import org.universAAL.middleware.service.ServiceCall;
import org.universAAL.middleware.service.ServiceCallee;
import org.universAAL.middleware.service.ServiceResponse;
import org.universAAL.middleware.service.owls.process.ProcessOutput;
import mainpackage.Activator;
import ontologies.TemperatureSensor;

public class SCallee_SmartEnvironment extends ServiceCallee {

	public SCallee_SmartEnvironment(ModuleContext context) {
		super(context, SCallee_SmartEnvironmentProvidedService.profiles);
		System.out.println("Initialized ServiceCallee Successfully");
	}

	public void communicationChannelBroken() {
		// TODO Auto-generated method stub

	}

	public ServiceResponse handleCall(ServiceCall call) {
		String operation = call.getProcessURI();
		
		if(operation == null)
			return null;
		
		if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_GET_TEMPERATURE_SENSORS)) {
			System.out.println("Service Callee: Return all temperature sensors");
			return getTemperatureSensors();
		} else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_TURN_ON_TEMPERATURE_SENSOR)){
			Object inputURL = call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_TEMPERATURE_SENSOR_URI);
			
			System.out.println("Turning ON a temperature sensor with URI: " + inputURL.toString());
			return null;
		} else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_TURN_OFF_TEMPERATURE_SENSOR)){
			Object inputURL = call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_TEMPERATURE_SENSOR_URI);
			
			System.out.println("Turning OFF a temperature sensor with URI: " + inputURL.toString());
			return null;
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_SET_TEMPERATURE_SENSOR_LOCATION)){
			Object inputURL = call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_TEMPERATURE_SENSOR_URI);
			Object inputLocation = call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_TEMPERATURE_SENSOR_LOCATION);
			System.out.println("Setting location of temperature sensor with URI: " + inputURL.toString());
			System.out.println("Location" + inputLocation);
			return null;
		}{
			return null;
		}
	}
	
	public ServiceResponse getTemperatureSensors() {
		ServiceResponse sr = null;
		System.out.println("	Getting RDF Objects from uAAL Objects (TemperatureSensor)");
		if(Activator.temperatureSensorOntologies != null) {
			System.out.println("	RDFObjectList<TemperatureSensor>.Size="+Activator.temperatureSensorOntologies.size());
			System.out.println("	RDFObjectList<TemperatureSensor>.get(0)aaaaa:");
			System.out.println("		URI:"+Activator.temperatureSensorOntologies.get(0).getProperty(TemperatureSensor.MY_URI));
			System.out.println("		OP:"+Activator.temperatureSensorOntologies.get(0).getOperationStatus());
			System.out.println("		LOC:"+Activator.temperatureSensorOntologies.get(0).getMesuredTemperatureValue());
			

			sr = new ServiceResponse(CallStatus.succeeded);
			sr.addOutput(new ProcessOutput(
					SCallee_SmartEnvironmentProvidedService.OUTPUT_TEMPERATURE_SENSORS
					,Activator.temperatureSensorOntologies));
		} else {
			sr = new ServiceResponse(CallStatus.denied);
		}
		//System.out.println("		Message:"+Activator.temperatureSensorOntologies.get(0).getMessage());
		
		return sr;
	}
	
	public ServiceResponse getTemperature() {
		ServiceResponse sr = null;
		System.out.println("	Getting RDF Objects from uAAL Objects (TemperatureSensor)");
		if(Activator.temperatureSensorOntologies != null) {
			System.out.println("	RDFObjectList<TemperatureSensor>.Size="+Activator.temperatureSensorOntologies.size());
			System.out.println("	RDFObjectList<TemperatureSensor>.get(0)aaaaa:");
			System.out.println("		URI:"+Activator.temperatureSensorOntologies.get(0).getClassURI());
			System.out.println("		OP:"+Activator.temperatureSensorOntologies.get(0).getOperationStatus());
			System.out.println("		LOC:"+Activator.temperatureSensorOntologies.get(0).getMesuredTemperatureValue());
			ArrayList<Float> respData = new ArrayList<Float>();
			for(int i =0; i <Activator.temperatureSensorOntologies.size();i++) {
				respData.add(Activator.temperatureSensorOntologies.get(i).getMesuredTemperatureValue());
				;
			}
			sr = new ServiceResponse(CallStatus.succeeded);
			sr.addOutput(new ProcessOutput(
					SCallee_SmartEnvironmentProvidedService.OUTPUT_TEMPERATURE_SENSOR_TEMPERATURE
					,respData));
		} else {
			sr = new ServiceResponse(CallStatus.denied);
		}
		//System.out.println("		Message:"+Activator.temperatureSensorOntologies.get(0).getMessage());
		
		return sr;
	}

}
