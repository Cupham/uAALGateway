	package jaist.ServiceBus;


import java.net.SocketException;
import java.util.ArrayList;

/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_6#Providing_services_on_the_bus */
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.service.CallStatus;
import org.universAAL.middleware.service.ServiceCall;
import org.universAAL.middleware.service.ServiceCallee;
import org.universAAL.middleware.service.ServiceResponse;
import org.universAAL.middleware.service.owls.process.ProcessOutput;
import org.universAAL.ontology.echonetontology.airconditionerRelatedDevices.HomeAirConditioner;
import org.universAAL.ontology.echonetontology.housingFacilitiesRelatedDevices.GeneralLighting;
import org.universAAL.ontology.echonetontology.managementOperationRelatedDevices.Switch;
import org.universAAL.ontology.echonetontology.sensorRelatedDevices.TemperatureSensor;
import org.universAAL.ontology.echonetontology.values.EchonetDeviceGroupCodeValue;

import echowand.common.EOJ;
import echowand.common.EPC;
import echowand.logic.TooManyObjectsException;
import echowand.net.SubnetException;
import echowand.object.EchonetObjectException;
import echowand.object.ObjectData;
import echowand.service.ObjectNotFoundException;
import jaist.mainpackage.Activator;
import jaist.utils.EchonetDataConverter;

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
		System.out.println("Called received " + call.toString());
		if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_GET_TEMPERATURE_SENSORS)) {
			sr = getTemperatureSensors();
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
			System.out.println("lalalal");
			HomeAirConditioner inputData = (HomeAirConditioner)call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_AIRCONDITIONER_URI);
			Object inputMode = call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_AIRCONDITIONER_OPERATION_MODE);
			System.out.println(null == inputData);
			System.out.println(inputData.getProperty(HomeAirConditioner.PROPERTY_HAS_NODE_IP_ADDRESS));
			try {
				sr = setAirconditionerOperationMode(inputData, "cooling");
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
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_GET_LIGHTING_DEVICES)) {
			sr = getLightings();
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_TURN_ON_LIGHTING_DEVICE)) {
			GeneralLighting inputData = (GeneralLighting) call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_LIGHTING_URI);
			System.out.println(inputData.toString());
			try {
				sr = switchLighting(inputData, true);
			} catch (SocketException | SubnetException | TooManyObjectsException | EchonetObjectException
					| ObjectNotFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_TURN_OFF_LIGHTING_DEVICE)) {
			GeneralLighting inputData = (GeneralLighting) call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_LIGHTING_URI);
			try {
				sr = switchLighting(inputData, false);
			} catch (SocketException | SubnetException | TooManyObjectsException | EchonetObjectException
					| ObjectNotFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_SET_LIGHTING_ILLUMINATION_LEVEL)) {
			GeneralLighting inputData = (GeneralLighting) call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_LIGHTING_URI);
			Object level = call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_LIGHTING_ILLUMINATION_LEVEL);
			try {
				sr = setLightingIlluminationLevel(inputData, Integer.parseInt(level.toString()));
			} catch (SocketException | SubnetException | TooManyObjectsException | EchonetObjectException
					| ObjectNotFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_GET_CURTAIN_CONTROLLERS)) {
			sr = getCurtains();
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_OPEN_CURTAIN)) {
			Curtain inputData = (Curtain) call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_CURTAIN_URI);
			try {
				sr = switchCurtain(inputData, true);
			} catch (SocketException | SubnetException | TooManyObjectsException | EchonetObjectException
					| ObjectNotFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_CLOSE_CURTAIN)) {
			Curtain inputData = (Curtain) call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_CURTAIN_URI);
			try {
				sr = switchCurtain(inputData, false);
			} catch (SocketException | SubnetException | TooManyObjectsException | EchonetObjectException
					| ObjectNotFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}*/
		else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_GET_CONSENTS)) {
			sr = getConsents();
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_TURN_ON_CONSENTS)) {
			Switch inputData = (Switch) call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_CONSENT_URI);
			try {
				sr = switchConsent(inputData, true);
			} catch (SocketException | SubnetException | TooManyObjectsException | EchonetObjectException
					| ObjectNotFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_TURN_OFF_CONSENTS)) {
			Switch inputData = (Switch) call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_CONSENT_URI);
			try {
				sr = switchConsent(inputData, false);
			} catch (SocketException | SubnetException | TooManyObjectsException | EchonetObjectException
					| ObjectNotFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("The required service is not yet supported!!");
		}
		return sr;
	}
	
	private ServiceResponse getTemperatureSensors() {
		ServiceResponse sr = null;
		System.out.println("SCallee_SmartEnvironment:	Returning all temperature sensors in iHouse");
		if(Activator.temperatureSensorOntologies.size() != 0) {
			sr = new ServiceResponse(CallStatus.succeeded);
			ArrayList<TemperatureSensor> tempSS =  new ArrayList<TemperatureSensor>(Activator.temperatureSensorOntologies.values());
			sr.addOutput(new ProcessOutput(
					SCallee_SmartEnvironmentProvidedService.OUTPUT_TEMPERATURE_SENSORS
					,tempSS));
			System.out.println("SCallee_SmartEnvironment:	Returned " +Activator.temperatureSensorOntologies.size() 
					+" temperature sensors");
		} else {
			sr = new ServiceResponse(CallStatus.denied);
			System.out.println("SCallee_SmartEnvironment:	Can not get temperature sensors from iHouse");
		}	
		return sr;
	}
	private ServiceResponse setLocationTemperatureSensor(TemperatureSensor sensor, String location) throws SocketException, SubnetException, TooManyObjectsException, EchonetObjectException, ObjectNotFoundException, InterruptedException {
		ServiceResponse sr = null;
		ObjectData data = null;
		data = EchonetDataConverter.installLocationtoDataObj(location.trim());
		if(sensor != null && data !=null) {
			String ip = (String) sensor.getProperty(TemperatureSensor.PROPERTY_HAS_NODE_IP_ADDRESS);				
			byte groupCode = EchonetDataConverter.byteFromGroupCode((EchonetDeviceGroupCodeValue)sensor.getProperty(TemperatureSensor.PROPERTY_HAS_GROUP_CODE));
			byte classCode = Byte.parseByte((String)sensor.getProperty(TemperatureSensor.PROPERTY_HAS_CLASS_CODE));
			byte instanceCode =  Byte.parseByte((String)sensor.getProperty(TemperatureSensor.PROPERTY_HAS_INSTANCE_CODE));

			System.out.println("SCallee_SmartEnvironment:	Change location of the temperature sensor with IP: " + ip 
			+ "by a new value: " + location);
	
			EOJ eoj = new EOJ(groupCode,classCode,instanceCode);
			boolean rs = Activator.deviceUpdater.updateDeviceAttribute(ip, eoj,EPC.x81 , data);
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
			ArrayList<HomeAirConditioner> tempSS =  new ArrayList<HomeAirConditioner>(Activator.homeAirconditionerOntologies.values());
			sr = new ServiceResponse(CallStatus.succeeded);
			sr.addOutput(new ProcessOutput(
					SCallee_SmartEnvironmentProvidedService.OUTPUT_AIRCONDTIONERS
					,tempSS));
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
			String ip = (String) aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_NODE_IP_ADDRESS);				
			byte groupCode = EchonetDataConverter.byteFromGroupCode((EchonetDeviceGroupCodeValue)aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_GROUP_CODE));
			byte classCode = Byte.parseByte((String)aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_CLASS_CODE));
			byte instanceCode =  Byte.parseByte((String)aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_INSTANCE_CODE));

			System.out.println("SCallee_SmartEnvironment:	Turning ON the airconditioner with IP: " + ip);	
			EOJ eoj = new EOJ(groupCode,classCode,instanceCode);
			ObjectData data = new ObjectData((byte) 0x30);
			boolean rs = Activator.deviceUpdater.updateDeviceAttribute(ip, eoj,EPC.x80 , data);
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
			String ip = (String) aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_NODE_IP_ADDRESS);				
			byte groupCode = EchonetDataConverter.byteFromGroupCode((EchonetDeviceGroupCodeValue)aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_GROUP_CODE));
			byte classCode = Byte.parseByte((String)aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_CLASS_CODE));
			byte instanceCode =  Byte.parseByte((String)aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_INSTANCE_CODE));

			System.out.println("SCallee_SmartEnvironment:	Turning OFF the airconditioner with IP: " + ip);	
			EOJ eoj = new EOJ(groupCode,classCode,instanceCode);
			ObjectData data = new ObjectData((byte) 0x31);
			boolean rs = Activator.deviceUpdater.updateDeviceAttribute(ip, eoj,EPC.x80 , data);
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
			String ip = (String) aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_NODE_IP_ADDRESS);				
			byte groupCode = EchonetDataConverter.byteFromGroupCode((EchonetDeviceGroupCodeValue)aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_GROUP_CODE));
			byte classCode = Byte.parseByte((String)aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_CLASS_CODE));
			byte instanceCode =  Byte.parseByte((String)aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_INSTANCE_CODE));
			System.out.println("SCallee_SmartEnvironment:	Change location of the airconditioner with IP: " + ip 
			+ "by a new value: " + location);
	
			EOJ eoj = new EOJ(groupCode,classCode,instanceCode);
			boolean rs = Activator.deviceUpdater.updateDeviceAttribute(ip, eoj,EPC.x81 , data);
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
			String ip = (String) aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_NODE_IP_ADDRESS);				
			byte groupCode = EchonetDataConverter.byteFromGroupCode((EchonetDeviceGroupCodeValue)aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_GROUP_CODE));
			byte classCode = Byte.parseByte((String)aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_CLASS_CODE));
			byte instanceCode =  Byte.parseByte((String)aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_INSTANCE_CODE));
	
			System.out.println("SCallee_SmartEnvironment:	set operation power-saving of the airconditioner with IP: " + ip 
			+ "by a new value: " + status.booleanValue());
	
			EOJ eoj = new EOJ(groupCode,classCode,instanceCode);
			boolean rs = Activator.deviceUpdater.updateDeviceAttribute(ip, eoj,EPC.x8F , data);
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
			String ip = (String) aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_NODE_IP_ADDRESS);				
			//byte groupCode = EchonetDataConverter.byteFromGroupCode((EchonetDeviceGroupCodeValue)aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_GROUP_CODE));
			//byte classCode = Byte.parseByte((String)aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_CLASS_CODE));
			byte groupCode = (byte) 0x01;
			byte classCode = (byte) 0x30;
			byte instanceCode =  Byte.parseByte((String)aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_INSTANCE_CODE));
	
			System.out.println("SCallee_SmartEnvironment:	Change operation mode of the airconditioner with IP: " + ip 
			+ "by a new value: " + mode);
	
			EOJ eoj = new EOJ(groupCode,classCode,instanceCode);
			boolean rs = Activator.deviceUpdater.updateDeviceAttribute(ip, eoj,EPC.xB0 , data);
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
			String ip = (String) aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_NODE_IP_ADDRESS);				
			byte groupCode = EchonetDataConverter.byteFromGroupCode((EchonetDeviceGroupCodeValue)aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_GROUP_CODE));
			byte classCode = Byte.parseByte((String)aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_CLASS_CODE));
			byte instanceCode =  Byte.parseByte((String)aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_INSTANCE_CODE));
	
			System.out.println("SCallee_SmartEnvironment:	Change air flow rate of the airconditioner with IP: " + ip 
			+ "by a new value: " + flowrate);
	
			EOJ eoj = new EOJ(groupCode,classCode,instanceCode);
			boolean rs = Activator.deviceUpdater.updateDeviceAttribute(ip, eoj,EPC.xA0 , data);
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
			String ip = (String) aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_NODE_IP_ADDRESS);				
			byte groupCode = EchonetDataConverter.byteFromGroupCode((EchonetDeviceGroupCodeValue)aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_GROUP_CODE));
			byte classCode = Byte.parseByte((String)aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_CLASS_CODE));
			byte instanceCode =  Byte.parseByte((String)aircon.getProperty(HomeAirConditioner.PROPERTY_HAS_INSTANCE_CODE));
	
			System.out.println("SCallee_SmartEnvironment:	Change temperature of the airconditioner with IP: " + ip 
			+ "by a new value: " + temperature);
	
			EOJ eoj = new EOJ(groupCode,classCode,instanceCode);
			boolean rs = Activator.deviceUpdater.updateDeviceAttribute(ip, eoj,EPC.xA0 , data);
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
	
	private ServiceResponse getLightings() {
		ServiceResponse sr = null;
		System.out.println("SCallee_SmartEnvironment:	Returning all lighting devices in iHouse");
		if(Activator.lightingOntologies.size() != 0) {
			sr = new ServiceResponse(CallStatus.succeeded);
			ArrayList<GeneralLighting> lightings =  new ArrayList<GeneralLighting>(Activator.lightingOntologies.values());
			sr.addOutput(new ProcessOutput(
					SCallee_SmartEnvironmentProvidedService.OUTPUT_LIGHTINGS
					,lightings));
			System.out.println("SCallee_SmartEnvironment:	Returned " +Activator.lightingOntologies.size() 
					+" lighting devices");
		} else {
			sr = new ServiceResponse(CallStatus.denied);
			System.out.println("SCallee_SmartEnvironment:	Can not get lighting devices from iHouse");
		}	
		return sr;
	}
	private ServiceResponse switchLighting(GeneralLighting lighting, boolean status) throws SocketException, SubnetException, TooManyObjectsException, EchonetObjectException, ObjectNotFoundException, InterruptedException {
		ServiceResponse sr = null;
		if(lighting != null) {
			String ip = (String) lighting.getProperty(GeneralLighting.PROPERTY_HAS_NODE_IP_ADDRESS);				
			byte groupCode = EchonetDataConverter.byteFromGroupCode((EchonetDeviceGroupCodeValue)lighting.getProperty(GeneralLighting.PROPERTY_HAS_GROUP_CODE));
			byte classCode = Byte.parseByte((String)lighting.getProperty(GeneralLighting.PROPERTY_HAS_CLASS_CODE));
			byte instanceCode =  Byte.parseByte((String)lighting.getProperty(GeneralLighting.PROPERTY_HAS_INSTANCE_CODE));
			EOJ eoj = new EOJ(groupCode,classCode,instanceCode);
			if(status) {
				
				System.out.println("SCallee_SmartEnvironment:	Turning ON the lighting device with IP: " + ip);	
				
				ObjectData data = new ObjectData((byte) 0x30);
				boolean rs = Activator.deviceUpdater.updateDeviceAttribute(ip, eoj,EPC.x80 , data);
				if(rs) {
					sr = new ServiceResponse(CallStatus.succeeded);
					System.out.println("SCallee_SmartEnvironment:	Turned ON the lighting device successfully");
				} else {
					sr = new ServiceResponse(CallStatus.denied);
					System.out.println("SCallee_SmartEnvironment:	Can not turn ON the specific device");
				}
			} else if(!status){ 
				System.out.println("SCallee_SmartEnvironment:	Turning OFF the lighting device with IP: " + ip);	
							ObjectData data = new ObjectData((byte) 0x31);
				boolean rs = Activator.deviceUpdater.updateDeviceAttribute(ip, eoj,EPC.x80 , data);
				if(rs) {
					sr = new ServiceResponse(CallStatus.succeeded);
					System.out.println("SCallee_SmartEnvironment:	Turned OFF the lighting device successfully");
				} else {
					sr = new ServiceResponse(CallStatus.denied);
					System.out.println("SCallee_SmartEnvironment:	Can not turn OFF the specific device");
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	What do you want to do with device: " + ip);
			}
			
		} else {
			System.out.println("SCallee_SmartEnvironment:	Error input required!");
			
		}	
		return sr;
	}
	
	private ServiceResponse setLightingIlluminationLevel(GeneralLighting lighting, int level) throws SocketException, SubnetException, TooManyObjectsException, EchonetObjectException, ObjectNotFoundException, InterruptedException {
		ServiceResponse sr = null;
		ObjectData data = null;
		Integer illuminationLevel = new Integer(level);
		if(lighting != null && illuminationLevel !=null) {
			String ip = (String) lighting.getProperty(GeneralLighting.PROPERTY_HAS_NODE_IP_ADDRESS);				
			byte groupCode = EchonetDataConverter.byteFromGroupCode((EchonetDeviceGroupCodeValue)lighting.getProperty(GeneralLighting.PROPERTY_HAS_GROUP_CODE));
			byte classCode = Byte.parseByte((String)lighting.getProperty(GeneralLighting.PROPERTY_HAS_CLASS_CODE));
			byte instanceCode =  Byte.parseByte((String)lighting.getProperty(GeneralLighting.PROPERTY_HAS_INSTANCE_CODE));
			EOJ eoj = new EOJ(groupCode,classCode,instanceCode);

			System.out.println("SCallee_SmartEnvironment:	set illumination value of lighting device with IP: " + ip 
			+ "to: " + illuminationLevel.intValue() + "%");
			data = new ObjectData(illuminationLevel.byteValue());
			boolean rs = Activator.deviceUpdater.updateDeviceAttribute(ip, eoj,EPC.xB0 , data);
			if(rs) {
				sr = new ServiceResponse(CallStatus.succeeded);
				System.out.println("SCallee_SmartEnvironment:	set illumination value successfully");
			} else {
				sr = new ServiceResponse(CallStatus.denied);
				System.out.println("SCallee_SmartEnvironment:	Can not set illumination level for this device");
			}
		} else {
			System.out.println("SCallee_SmartEnvironment:	Error input required!");
			
		}	
		return sr;
	}
	
	private ServiceResponse getConsents() {
		ServiceResponse sr = null;
		System.out.println("SCallee_SmartEnvironment:	Returning all consents in iHouse");
		if(Activator.consentOntologies.size() != 0) {
			sr = new ServiceResponse(CallStatus.succeeded);
			ArrayList<Switch> consents =  new ArrayList<Switch>(Activator.consentOntologies.values());
			sr.addOutput(new ProcessOutput(
					SCallee_SmartEnvironmentProvidedService.OUTPUT_CONSENTS
					,consents));
			System.out.println("SCallee_SmartEnvironment:	Returned " +Activator.consentOntologies.size() 
					+" consents");
		} else {
			sr = new ServiceResponse(CallStatus.denied);
			System.out.println("SCallee_SmartEnvironment:	Can not get consent from iHouse");
		}	
		return sr;
	}
	private ServiceResponse switchConsent(Switch dev, boolean status) throws SocketException, SubnetException, TooManyObjectsException, EchonetObjectException, ObjectNotFoundException, InterruptedException {
		ServiceResponse sr = null;
		if(dev != null) {
			String ip = (String) dev.getProperty(GeneralLighting.PROPERTY_HAS_NODE_IP_ADDRESS);				
			byte groupCode = EchonetDataConverter.byteFromGroupCode((EchonetDeviceGroupCodeValue)dev.getProperty(Switch.PROPERTY_HAS_GROUP_CODE));
			byte classCode = Byte.parseByte((String)dev.getProperty(Switch.PROPERTY_HAS_CLASS_CODE));
			byte instanceCode =  Byte.parseByte((String)dev.getProperty(Switch.PROPERTY_HAS_INSTANCE_CODE));
			EOJ eoj = new EOJ(groupCode,classCode,instanceCode);
			if(status) {
				

				System.out.println("SCallee_SmartEnvironment:	Turn ON the electric consent with IP: " + ip);	

				ObjectData data = new ObjectData((byte) 0x30);
				boolean rs = Activator.deviceUpdater.updateDeviceAttribute(ip, eoj,EPC.x80 , data);
				if(rs) {
					sr = new ServiceResponse(CallStatus.succeeded);
					System.out.println("SCallee_SmartEnvironment:	Turn ON the electric consent successfully");
				} else {
					sr = new ServiceResponse(CallStatus.denied);
					System.out.println("SCallee_SmartEnvironment:	Can not turn ON this electric consent");
				}
			} else if(!status){ 
				System.out.println("SCallee_SmartEnvironment:	Turn OFF the electric consent with IP: " + ip);	
			
				ObjectData data = new ObjectData((byte) 0x31);
				boolean rs = Activator.deviceUpdater.updateDeviceAttribute(ip, eoj,EPC.x80 , data);
				if(rs) {
					sr = new ServiceResponse(CallStatus.succeeded);
					System.out.println("SCallee_SmartEnvironment:	Turn OFF the electric consent successfully");
				} else {
					sr = new ServiceResponse(CallStatus.denied);
					System.out.println("SCallee_SmartEnvironment:	Can not turn OFF this electric consent");
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	What do you want to do with device: " + ip);
			}
			
		} else {
			System.out.println("SCallee_SmartEnvironment:	Error input required!");
			
		}	
		return sr;
	}
	
	
}
