	package ServiceBus;


import java.net.SocketException;
import java.nio.ByteBuffer;

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
import ontologies.HomeAirConditioner;
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
			sr = getTemperatureSensors();
		} else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_TURN_ON_TEMPERATURE_SENSOR)){
			TemperatureSensor inputData = (TemperatureSensor) call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_TEMPERATURE_SENSOR_URI);
			try {
				sr = turnONTemperatureSensor(inputData);
			} catch (SocketException | SubnetException | TooManyObjectsException | EchonetObjectException
					| ObjectNotFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_TURN_OFF_TEMPERATURE_SENSOR)){
			TemperatureSensor inputData = (TemperatureSensor) call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_TEMPERATURE_SENSOR_URI);
			try {
				sr = turnOFFTemperatureSensor(inputData);
			} catch (SocketException | SubnetException | TooManyObjectsException | EchonetObjectException
						| ObjectNotFoundException | InterruptedException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_SET_TEMPERATURE_SENSOR_LOCATION)){
			TemperatureSensor inputData = (TemperatureSensor)call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_TEMPERATURE_SENSOR_URI);
			Object inputLocation = call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_TEMPERATURE_SENSOR_LOCATION);
			try {
				sr = setLocationTemperatureSensor(inputData, inputLocation.toString());
			} catch (SocketException | SubnetException | TooManyObjectsException | EchonetObjectException
					| ObjectNotFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_GET_AIRCONDITIONERS)){
			sr=getAirconditioners();
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_TURN_ON_AIRCONDITIONER)){
			HomeAirConditioner inputData = (HomeAirConditioner) call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_AIRCONDITIONER_URI);
			try {
				sr = turnONAirconditioner(inputData);
			} catch (SocketException | SubnetException | TooManyObjectsException | EchonetObjectException
					| ObjectNotFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_TURN_OFF_AIRCONDITIONER)){
			HomeAirConditioner inputData = (HomeAirConditioner) call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_AIRCONDITIONER_URI);
			try {
				sr = turnOFFAirconditioner(inputData);
			} catch (SocketException | SubnetException | TooManyObjectsException | EchonetObjectException
					| ObjectNotFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_SET_AIRCONDITIONER_OPERATION_POWER_SAVING)){
			HomeAirConditioner inputData = (HomeAirConditioner)call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_AIRCONDITIONER_URI);
			Boolean status = Boolean.parseBoolean(call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_AIRCONDITIONER_POWER_SAVING_OPTION).toString());
			try {
				sr = setAirconditionerOperationPowerSaving(inputData, status);
			} catch (SocketException | SubnetException | TooManyObjectsException | EchonetObjectException
					| ObjectNotFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_SET_AIRCONDITIONER_OPERATION_MODE)){
			HomeAirConditioner inputData = (HomeAirConditioner)call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_AIRCONDITIONER_URI);
			Object inputMode = call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_AIRCONDITIONER_OPERATION_MODE);
			try {
				sr = setAirconditionerOperationMode(inputData, inputMode.toString());
			} catch (SocketException | SubnetException | TooManyObjectsException | EchonetObjectException
					| ObjectNotFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_SET_AIRCONDITIONER_LOCATION)){
			HomeAirConditioner inputData = (HomeAirConditioner)call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_AIRCONDITIONER_URI);
			Object inputLocation = call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_AIRCONDITIONER_LOCATION);
			try {
				sr = setLocationAirconditioner(inputData, inputLocation.toString());
			} catch (SocketException | SubnetException | TooManyObjectsException | EchonetObjectException
					| ObjectNotFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_SET_AIRCONDITIONER_TEMPERATURE)){
			HomeAirConditioner inputData = (HomeAirConditioner)call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_AIRCONDITIONER_URI);
			Integer temp = Integer.parseInt(call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_AIRCONDITIONER_TEMPERATURE).toString());
			try {
				sr = setAirconditionerTemperature(inputData, temp.intValue());
			} catch (SocketException | SubnetException | TooManyObjectsException | EchonetObjectException
					| ObjectNotFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_SET_AIRCONDITIONER_AIR_FLOW_RATE)){
			HomeAirConditioner inputData = (HomeAirConditioner)call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_AIRCONDITIONER_URI);
			Object airFlowRate = call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_AIRCONDITIONER_AIR_FLOW_RATE);
			try {
				sr = setAirconditionerAirflowrate(inputData, airFlowRate.toString());
			} catch (SocketException | SubnetException | TooManyObjectsException | EchonetObjectException
					| ObjectNotFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else {
			System.out.println("The required service is not yet supported!!");
		}
		return sr;
	}
	
	private ServiceResponse getTemperatureSensors() {
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
	private ServiceResponse turnONTemperatureSensor(TemperatureSensor sensor) throws SocketException, SubnetException, TooManyObjectsException, EchonetObjectException, ObjectNotFoundException, InterruptedException {
		ServiceResponse sr = null;
		if(sensor != null) {
			System.out.println("SCallee_SmartEnvironment:	Turning ON the temperature sensor with IP: " + sensor.getIPAddress());	
			EOJ eoj = new EOJ(sensor.getClassGroupCode(), sensor.getClassCode(), sensor.getInstanceCode());
			ObjectData data = new ObjectData((byte) 0x30);
			boolean rs = Activator.deviceUpdater.updateDeviceAttribute(sensor.getIPAddress(), eoj,EPC.x80 , data);
			if(rs) {
				sr = new ServiceResponse(CallStatus.succeeded);
				System.out.println("SCallee_SmartEnvironment:	Turned ON the temperature sensor successfully");
			} else {
				sr = new ServiceResponse(CallStatus.denied);
				System.out.println("SCallee_SmartEnvironment:	Can not turn ON the specific device");
			}
		} else {
			System.out.println("SCallee_SmartEnvironment:	Error input required!");
			
		}	
		return sr;
	}
	private ServiceResponse turnOFFTemperatureSensor(TemperatureSensor sensor) throws SocketException, SubnetException, TooManyObjectsException, EchonetObjectException, ObjectNotFoundException, InterruptedException {
		ServiceResponse sr = null;
		if(sensor != null) {
			System.out.println("SCallee_SmartEnvironment:	Turning OFF the temperature sensor with with IP: " + sensor.getIPAddress());			
			EOJ eoj = new EOJ(sensor.getClassGroupCode(), sensor.getClassCode(), sensor.getInstanceCode());
			ObjectData data = new ObjectData((byte) 0x31);
			boolean rs = Activator.deviceUpdater.updateDeviceAttribute(sensor.getIPAddress(), eoj,EPC.x80 , data);
			if(rs) {
				sr = new ServiceResponse(CallStatus.succeeded);
				System.out.println("SCallee_SmartEnvironment:	Turned OFF the temperature sensor successfully");
			} else {
				sr = new ServiceResponse(CallStatus.denied);
				System.out.println("SCallee_SmartEnvironment:	Can not turn OFF the specific device");
			}
		} else {
			System.out.println("SCallee_SmartEnvironment:	Error input required!");		
		}	
		return sr;
	}
	private ServiceResponse setLocationTemperatureSensor(TemperatureSensor sensor, String location) throws SocketException, SubnetException, TooManyObjectsException, EchonetObjectException, ObjectNotFoundException, InterruptedException {
		ServiceResponse sr = null;
		ObjectData data = null;
		data = EchonetDataConverter.installLocationtoDataObj(location.trim());
		if(sensor != null && data !=null) {
			System.out.println("SCallee_SmartEnvironment:	Change location of the temperature sensor with IP: " + sensor.getIPAddress() 
			+ "by a new value: " + location);
	
			EOJ eoj = new EOJ(sensor.getClassGroupCode(), sensor.getClassCode(), sensor.getInstanceCode());
			boolean rs = Activator.deviceUpdater.updateDeviceAttribute(sensor.getIPAddress(), eoj,EPC.x81 , data);
			if(rs) {
				sr = new ServiceResponse(CallStatus.succeeded);
				System.out.println("SCallee_SmartEnvironment:	Set location of the temperature sensor successfully");
			} else {
				sr = new ServiceResponse(CallStatus.denied);
				System.out.println("SCallee_SmartEnvironment:	Can not set location for this device");
			}
		} else {
			System.out.println("SCallee_SmartEnvironment:	Error input required!");
			
		}	
		return sr;
	}
	
	// Airconditioner services
	private ServiceResponse getAirconditioners() {
		ServiceResponse sr = null;
		System.out.println("SCallee_SmartEnvironment:	Returning all airconditioners in iHouse");
		if(Activator.homeAirconditionerOntologies != null) {
			sr = new ServiceResponse(CallStatus.succeeded);
			sr.addOutput(new ProcessOutput(
					SCallee_SmartEnvironmentProvidedService.OUTPUT_AIRCONDTIONERS
					,Activator.homeAirconditionerOntologies));
			System.out.println("SCallee_SmartEnvironment:	Returned " +Activator.homeAirconditionerOntologies.size() 
					+" temperature sensors");
		} else {
			sr = new ServiceResponse(CallStatus.denied);
			System.out.println("SCallee_SmartEnvironment:	Can not get airconditioner from iHouse");
		}	
		return sr;
	}
	private ServiceResponse turnONAirconditioner(HomeAirConditioner aircon) throws SocketException, SubnetException, TooManyObjectsException, EchonetObjectException, ObjectNotFoundException, InterruptedException {
		ServiceResponse sr = null;
		if(aircon != null) {
			System.out.println("SCallee_SmartEnvironment:	Turning ON the airconditioner with IP: " + aircon.getIPAddress());	
			EOJ eoj = new EOJ(aircon.getClassGroupCode(), aircon.getClassCode(), aircon.getInstanceCode());
			ObjectData data = new ObjectData((byte) 0x30);
			boolean rs = Activator.deviceUpdater.updateDeviceAttribute(aircon.getIPAddress(), eoj,EPC.x80 , data);
			if(rs) {
				sr = new ServiceResponse(CallStatus.succeeded);
				System.out.println("SCallee_SmartEnvironment:	Turned airconditioner ON successfully");
			} else {
				sr = new ServiceResponse(CallStatus.denied);
				System.out.println("SCallee_SmartEnvironment:	Can not turn ON the specific airconditioner");
			}
		} else {
			System.out.println("SCallee_SmartEnvironment:	Error input required!");
			
		}	
		return sr;
	}
	private ServiceResponse turnOFFAirconditioner(HomeAirConditioner aircon) throws SocketException, SubnetException, TooManyObjectsException, EchonetObjectException, ObjectNotFoundException, InterruptedException {
		ServiceResponse sr = null;
		if(aircon != null) {
			System.out.println("SCallee_SmartEnvironment:	Turning OFF the airconditioner with IP: " + aircon.getIPAddress());	
			EOJ eoj = new EOJ(aircon.getClassGroupCode(), aircon.getClassCode(), aircon.getInstanceCode());
			ObjectData data = new ObjectData((byte) 0x31);
			boolean rs = Activator.deviceUpdater.updateDeviceAttribute(aircon.getIPAddress(), eoj,EPC.x80 , data);
			if(rs) {
				sr = new ServiceResponse(CallStatus.succeeded);
				System.out.println("SCallee_SmartEnvironment:	Turned airconditioner OFF successfully");
			} else {
				sr = new ServiceResponse(CallStatus.denied);
				System.out.println("SCallee_SmartEnvironment:	Can not turn OFF the specific airconditioner");
			}
		} else {
			System.out.println("SCallee_SmartEnvironment:	Error input required!");
			
		}	
		return sr;
	}
	private ServiceResponse setLocationAirconditioner(HomeAirConditioner aircon, String location) throws SocketException, SubnetException, TooManyObjectsException, EchonetObjectException, ObjectNotFoundException, InterruptedException {
		ServiceResponse sr = null;
		ObjectData data = null;
		data = EchonetDataConverter.installLocationtoDataObj(location.trim());
		if(aircon != null && data !=null) {
			System.out.println("SCallee_SmartEnvironment:	Change location of the airconditioner with IP: " + aircon.getIPAddress() 
			+ "by a new value: " + location);
	
			EOJ eoj = new EOJ(aircon.getClassGroupCode(), aircon.getClassCode(), aircon.getInstanceCode());
			boolean rs = Activator.deviceUpdater.updateDeviceAttribute(aircon.getIPAddress(), eoj,EPC.x81 , data);
			if(rs) {
				sr = new ServiceResponse(CallStatus.succeeded);
				System.out.println("SCallee_SmartEnvironment:	Set location of the airconditioner successfully");
			} else {
				sr = new ServiceResponse(CallStatus.denied);
				System.out.println("SCallee_SmartEnvironment:	Can not set location for this device");
			}
		} else {
			System.out.println("SCallee_SmartEnvironment:	Error input required!");
			
		}	
		return sr;
	}

	private ServiceResponse setAirconditionerOperationPowerSaving(HomeAirConditioner aircon, Boolean status) throws SocketException, SubnetException, TooManyObjectsException, EchonetObjectException, ObjectNotFoundException, InterruptedException {
		ServiceResponse sr = null;
		ObjectData data = null;
		if(status) {
			data = new ObjectData((byte) 0x30); 
		} else {
			data = new ObjectData((byte) 0x31);
		}
		if(aircon != null && data !=null) {
			System.out.println("SCallee_SmartEnvironment:	set operation power-saving of the airconditioner with IP: " + aircon.getIPAddress() 
			+ "by a new value: " + status.booleanValue());
	
			EOJ eoj = new EOJ(aircon.getClassGroupCode(), aircon.getClassCode(), aircon.getInstanceCode());
			boolean rs = Activator.deviceUpdater.updateDeviceAttribute(aircon.getIPAddress(), eoj,EPC.x8F , data);
			if(rs) {
				sr = new ServiceResponse(CallStatus.succeeded);
				System.out.println("SCallee_SmartEnvironment:	set operation power-saving of the airconditioner successfully");
			} else {
				sr = new ServiceResponse(CallStatus.denied);
				System.out.println("SCallee_SmartEnvironment:	Can not set operation power-saving for this device");
			}
		} else {
			System.out.println("SCallee_SmartEnvironment:	Error input required!");
			
		}	
		return sr;
	}
	private ServiceResponse setAirconditionerOperationMode(HomeAirConditioner aircon, String mode) throws SocketException, SubnetException, TooManyObjectsException, EchonetObjectException, ObjectNotFoundException, InterruptedException {
		ServiceResponse sr = null;
		ObjectData data = null;
		data = EchonetDataConverter.dataFromAirConditionerOperationMode(mode.trim());
		if(aircon != null && data !=null) {
			System.out.println("SCallee_SmartEnvironment:	Change operation mode of the airconditioner with IP: " + aircon.getIPAddress() 
			+ "by a new value: " + mode);
	
			EOJ eoj = new EOJ(aircon.getClassGroupCode(), aircon.getClassCode(), aircon.getInstanceCode());
			boolean rs = Activator.deviceUpdater.updateDeviceAttribute(aircon.getIPAddress(), eoj,EPC.xB0 , data);
			if(rs) {
				sr = new ServiceResponse(CallStatus.succeeded);
				System.out.println("SCallee_SmartEnvironment:	Change operation mode of the airconditioner successfully");
			} else {
				sr = new ServiceResponse(CallStatus.denied);
				System.out.println("SCallee_SmartEnvironment:	Can not change operation mode for this device");
			}
		} else {
			System.out.println("SCallee_SmartEnvironment:	Error input required!");
			
		}	
		return sr;
	}
	private ServiceResponse setAirconditionerAirflowrate(HomeAirConditioner aircon, String flowrate) throws SocketException, SubnetException, TooManyObjectsException, EchonetObjectException, ObjectNotFoundException, InterruptedException {
		ServiceResponse sr = null;
		ObjectData data = null;
		data = EchonetDataConverter.dataFromAirConditionerFlowRate(flowrate.trim());
		if(aircon != null && data !=null) {
			System.out.println("SCallee_SmartEnvironment:	Change air flow rate of the airconditioner with IP: " + aircon.getIPAddress() 
			+ "by a new value: " + flowrate);
	
			EOJ eoj = new EOJ(aircon.getClassGroupCode(), aircon.getClassCode(), aircon.getInstanceCode());
			boolean rs = Activator.deviceUpdater.updateDeviceAttribute(aircon.getIPAddress(), eoj,EPC.xA0 , data);
			if(rs) {
				sr = new ServiceResponse(CallStatus.succeeded);
				System.out.println("SCallee_SmartEnvironment:	Change air flow rate of the airconditioner successfully");
			} else {
				sr = new ServiceResponse(CallStatus.denied);
				System.out.println("SCallee_SmartEnvironment:	Can not change air flow rate for this device");
			}
		} else {
			System.out.println("SCallee_SmartEnvironment:	Error input required!");
			
		}	
		return sr;
	}
	private ServiceResponse setAirconditionerTemperature(HomeAirConditioner aircon, int temperature) throws SocketException, SubnetException, TooManyObjectsException, EchonetObjectException, ObjectNotFoundException, InterruptedException {
		ServiceResponse sr = null;
		ObjectData data = new ObjectData((byte) temperature);
		 
		if(aircon != null && data !=null) {
			System.out.println("SCallee_SmartEnvironment:	Change temperature of the airconditioner with IP: " + aircon.getIPAddress() 
			+ "by a new value: " + temperature);
	
			EOJ eoj = new EOJ(aircon.getClassGroupCode(), aircon.getClassCode(), aircon.getInstanceCode());
			boolean rs = Activator.deviceUpdater.updateDeviceAttribute(aircon.getIPAddress(), eoj,EPC.xA0 , data);
			if(rs) {
				sr = new ServiceResponse(CallStatus.succeeded);
				System.out.println("SCallee_SmartEnvironment:	Change temperature of the airconditioner successfully");
			} else {
				sr = new ServiceResponse(CallStatus.denied);
				System.out.println("SCallee_SmartEnvironment:	Can not change temperature for this device");
			}
		} else {
			System.out.println("SCallee_SmartEnvironment:	Error input required!");
			
		}	
		return sr;
	}
	
}
