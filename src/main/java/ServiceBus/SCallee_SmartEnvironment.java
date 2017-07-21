package ServiceBus;


import java.net.SocketException;


/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_6#Providing_services_on_the_bus */
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.service.CallStatus;
import org.universAAL.middleware.service.ServiceCall;
import org.universAAL.middleware.service.ServiceCallee;
import org.universAAL.middleware.service.ServiceResponse;
import org.universAAL.middleware.service.owls.process.ProcessOutput;
import echowand.common.EOJ;
import echowand.common.EPC;
import echowand.logic.TooManyObjectsException;
import echowand.net.SubnetException;
import echowand.object.EchonetObjectException;
import echowand.object.ObjectData;
import echowand.service.ObjectNotFoundException;
import mainpackage.Activator;
import ontologies.TemperatureSensor;
import utils.EchonetDataConverter;

public class SCallee_SmartEnvironment extends ServiceCallee {

	public SCallee_SmartEnvironment(ModuleContext context) {
		super(context, SCallee_SmartEnvironmentProvidedService.profiles);
		System.out.println("Initialized ServiceCallee Successfully");
	}

	public void communicationChannelBroken() {
		// TODO Auto-generated method stub

	}

	public ServiceResponse handleCall(ServiceCall call) {
		ServiceResponse sr = null;
		String operation = call.getProcessURI();
		
		if(operation == null)
			return null;
		
		if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_GET_TEMPERATURE_SENSORS)) {
			System.out.println("Service Callee: Return all temperature sensors");
			sr = getTemperatureSensors();
		} else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_TURN_ON_TEMPERATURE_SENSOR)){
			TemperatureSensor inputSS = (TemperatureSensor) call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_TEMPERATURE_SENSOR_URI);
			
			System.out.println("Turning ON a temperature sensor with URI: " + inputSS.toString());
			try {
				sr = turnONTemperatureSensor(inputSS);
			} catch (SocketException | SubnetException | TooManyObjectsException | EchonetObjectException
					| ObjectNotFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_TURN_OFF_TEMPERATURE_SENSOR)){
			TemperatureSensor inputSS = (TemperatureSensor) call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_TEMPERATURE_SENSOR_URI);
			System.out.println("Turning OFF a temperature sensor with URI: " + inputSS.toString());
			try {
				sr = turnOFFTemperatureSensor(inputSS);
			} catch (SocketException | SubnetException | TooManyObjectsException | EchonetObjectException
						| ObjectNotFoundException | InterruptedException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_SET_TEMPERATURE_SENSOR_LOCATION)){
			TemperatureSensor inputSS = (TemperatureSensor)call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_TEMPERATURE_SENSOR_URI);
			Object inputLocation = call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_TEMPERATURE_SENSOR_LOCATION);
			System.out.println("Setting location of temperature sensor with URI: " + inputSS.toString());
			System.out.println("Location" + inputLocation);
			try {
				sr = setLocationTemperatureSensor(inputSS, inputLocation.toString());
			} catch (SocketException | SubnetException | TooManyObjectsException | EchonetObjectException
					| ObjectNotFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else{
			
		}
		return sr;
	}
	
	public ServiceResponse getTemperatureSensors() {
		ServiceResponse sr = null;
		System.out.println("SCallee_SmartEnvironment:	Returning all temperature sensors in iHouse");
		if(Activator.temperatureSensorOntologies != null) {
			sr = new ServiceResponse(CallStatus.succeeded);
			sr.addOutput(new ProcessOutput(
					SCallee_SmartEnvironmentProvidedService.OUTPUT_TEMPERATURE_SENSORS
					,Activator.temperatureSensorOntologies));
			System.out.println("SCallee_SmartEnvironment:	Returned " +Activator.temperatureSensorOntologies.size() 
					+" temperature sensors");
		} else {
			sr = new ServiceResponse(CallStatus.denied);
			System.out.println("SCallee_SmartEnvironment:	Can not get temperature sensors from iHouse");
		}	
		return sr;
	}
	public ServiceResponse turnONTemperatureSensor(TemperatureSensor sensor) throws SocketException, SubnetException, TooManyObjectsException, EchonetObjectException, ObjectNotFoundException, InterruptedException {
		ServiceResponse sr = null;
		if(sensor != null) {
			System.out.println("SCallee_SmartEnvironment:	Turning ON a temperature sensor with IP: " + sensor.getIPAddress());
			System.out.println(sensor.getClassGroupCode() + " " + sensor.getClassCode() + "aadsd" + sensor.getInstanceCode());
			EOJ eoj = new EOJ(sensor.getClassGroupCode(), sensor.getClassCode(), sensor.getInstanceCode());
			System.out.println("EOJ: " + eoj.toString());
			ObjectData data = new ObjectData((byte) 0x30);
			boolean rs = Activator.deviceUpdater.updateDeviceAttribute(sensor.getIPAddress(), eoj,EPC.x80 , data);
			if(rs) {
				sr = new ServiceResponse(CallStatus.succeeded);
				System.out.println("SCallee_SmartEnvironment:	Turned ON successfully");
			} else {
				sr = new ServiceResponse(CallStatus.denied);
				System.out.println("SCallee_SmartEnvironment:	Can not turn ON specific device");
			}
		} else {
			System.out.println("SCallee_SmartEnvironment:	Error input required!");
			
		}	
		return sr;
	}
	
	public ServiceResponse turnOFFTemperatureSensor(TemperatureSensor sensor) throws SocketException, SubnetException, TooManyObjectsException, EchonetObjectException, ObjectNotFoundException, InterruptedException {
		ServiceResponse sr = null;
		if(sensor != null) {
			System.out.println("SCallee_SmartEnvironment:	Turning OFF a temperature sensor with with IP: " + sensor.getIPAddress()
			+sensor.getClassGroupCode() + sensor.getClassCode()+ sensor.getInstanceCode());
			
			EOJ eoj = new EOJ(sensor.getClassGroupCode(), sensor.getClassCode(), sensor.getInstanceCode());
			ObjectData data = new ObjectData((byte) 0x31);
			boolean rs = Activator.deviceUpdater.updateDeviceAttribute(sensor.getIPAddress(), eoj,EPC.x80 , data);
			if(rs) {
				sr = new ServiceResponse(CallStatus.succeeded);
				System.out.println("SCallee_SmartEnvironment:	Turned OFF successfully");
			} else {
				sr = new ServiceResponse(CallStatus.denied);
				System.out.println("SCallee_SmartEnvironment:	Can not turn OFF specific device");
			}
		} else {
			System.out.println("SCallee_SmartEnvironment:	Error input required!");
			
		}	
		return sr;
	}
	
	public ServiceResponse setLocationTemperatureSensor(TemperatureSensor sensor, String location) throws SocketException, SubnetException, TooManyObjectsException, EchonetObjectException, ObjectNotFoundException, InterruptedException {
		ServiceResponse sr = null;
		ObjectData data = null;
		data = EchonetDataConverter.installLocationtoDataObj(location.trim());
		if(sensor != null && data !=null) {
			System.out.println("SCallee_SmartEnvironment:	Change location of a temperature sensor with IP: " + sensor.getIPAddress() 
			+ "by a new value: " + location);
			
			EOJ eoj = new EOJ(sensor.getClassGroupCode(), sensor.getClassCode(), sensor.getInstanceCode());
			boolean rs = Activator.deviceUpdater.updateDeviceAttribute(sensor.getIPAddress(), eoj,EPC.x81 , data);
			if(rs) {
				sr = new ServiceResponse(CallStatus.succeeded);
				System.out.println("SCallee_SmartEnvironment:	Set location successfully");
			} else {
				sr = new ServiceResponse(CallStatus.denied);
				System.out.println("SCallee_SmartEnvironment:	Can not set location for this device");
			}
		} else {
			System.out.println("SCallee_SmartEnvironment:	Error input required!");
			
		}	
		return sr;
	}

}
