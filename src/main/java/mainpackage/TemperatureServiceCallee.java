package mainpackage;


import java.net.SocketException;
/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_6#Providing_services_on_the_bus */
import java.util.ArrayList;

import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.service.CallStatus;
import org.universAAL.middleware.service.ServiceCall;
import org.universAAL.middleware.service.ServiceCallee;
import org.universAAL.middleware.service.ServiceRequest;
import org.universAAL.middleware.service.ServiceResponse;
import org.universAAL.middleware.service.owls.process.ProcessOutput;
import org.universAAL.ontology.phThing.DeviceService;

import echowand.common.EOJ;
import echowand.common.EPC;
import echowand.logic.TooManyObjectsException;
import echowand.net.SubnetException;
import echowand.object.EchonetObjectException;
import echowand.object.ObjectData;
import echowand.service.ObjectNotFoundException;


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
			System.out.println("Initialized ServiceCallee Successfully");
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
		System.out.println("	Getting RDF Objects from uAAL Objects (TemperatureSensor)");
		if(Activator.eSensorList != null) {
			sr.addOutput(new ProcessOutput(
					TemperatureServiceCalleeProvidedService.OUTPUT_CONTROLLED_TEMPERATURE_SENSORS
					,Activator.eSensorList));
		}
		//System.out.println("	RDFObjectList<TemperatureSensor>.Size="+Activator.eSensorList.size());
		System.out.println("	RDFObjectList<TemperatureSensor>.get(0):");
		System.out.println("		URI:"+Activator.eSensorList.get(0).getProperty(TemperatureSensor.MY_URI));
		System.out.println("		Message:"+Activator.eSensorList.get(0).getMessage());
		
		return sr;
	}
	private ServiceResponse turnOnAirConditioner(String nodeIP, EOJ eoj, EPC epc, ObjectData value) {
		ServiceResponse sr =null;
		try {
			if(Activator.deviceUpdater.updateDeviceAttribute(nodeIP, eoj, epc, value)) {
				 sr = new ServiceResponse(CallStatus.succeeded);
			} else {
				 sr = new ServiceResponse(CallStatus.denied);
			}
		} catch (SocketException | SubnetException | TooManyObjectsException | EchonetObjectException
				| ObjectNotFoundException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sr;
	}
	private ServiceResponse getControlledAirConditioner() {
		ServiceResponse sr = new ServiceResponse(CallStatus.succeeded);
		System.out.println("	Getting RDF Objects from uAAL Objects (Airconditioner)");
		if(Activator.eAirConditionerList != null) {
			sr.addOutput(new ProcessOutput(
					TemperatureServiceCalleeProvidedService.OUTPUT_CONTROLLED_AIRCONDTIONER,
					Activator.eAirConditionerList));
		}
		System.out.println("	RDFObjectList<Airconditioner>.Size="+Activator.eAirConditionerList.size());
		System.out.println("	RDFObjectList<Airconditioner>.get(0):");
		System.out.println("		ClassURI:"+Activator.eAirConditionerList.get(0).getClassURI());
		System.out.println("		URI:"+Activator.eAirConditionerList.get(0).getProperty(TemperatureSensor.MY_URI));
		System.out.println("		Message:"+Activator.eAirConditionerList.get(0).getMessage());
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
			System.out.println("Executing a call to return TemperatureSensors");
			return getControlledTemperatureSensor();
		} if(operation.startsWith(TemperatureServiceCalleeProvidedService.GET_CONTROLLED_AIRCONDTIONER)) {
			System.out.println("Executing a call to return Airconditioner");
			System.out.println(call.toString());
			return getControlledAirConditioner();
		} if(operation.startsWith(TemperatureServiceCalleeProvidedService.SET_AIRCONDTIONER_TEMPERATURE)) {
			Object input =call.getInputValue(TemperatureServiceCalleeProvidedService.AIRCONDITIONER_URI);
			System.out.println("Seta air-con");
			System.out.println(input.toString());
			System.out.println(call.getInputValue(TemperatureServiceCalleeProvidedService.AIRCONDITIONER_STATUS));
			System.out.println(call.getInputValue(DeviceService.PROP_CONTROLS));
			System.out.println(call.getInputValue(TemperatureServiceCalleeProvidedService.PROP_CONTROLS)+"");
			System.out.println(call.getInputValue(HomeAirConditioner.PROPERTY_OPERATION_STATUS)+"");
		}
		return null;
	}

}
