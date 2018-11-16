	package uAAL.ServiceBus;



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
import org.universAAL.ontology.echonetontology.values.InstallationLocationValue;

import echonet.Objects.EchonetLiteDevice;
import echonet.Objects.eAirConditioner;
import echonet.Objects.eCurtain;
import echonet.Objects.eDataObject;
import echonet.Objects.eElectricConsent;
import echonet.Objects.eLighting;
import echonet.Objects.eRadio;
import echonet.Objects.eTV;
import echonet.Objects.eTemperatureSensor;
import echonet.Objects.eWindow;
import main.Activator;
import utils.EchonetDataConverter;
import utils.SampleConstants;

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
		InstallationLocationValue inputLocation = (InstallationLocationValue) call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_LOCATION);
		if(operation == null)
			return null;
		System.out.println("Called received " +operation  + "  " +EchonetDataConverter.eNumtoInstallationLocation(inputLocation));
		if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_GET_TEMPERATURE_SENSORS)) {
			ArrayList<eTemperatureSensor> sensors = getTemperatureSensors(inputLocation);
			if(sensors.size() != 0) {
				sr = new ServiceResponse(CallStatus.succeeded);
				System.out.println("SCallee_SmartEnvironment:	returned temperature value");
				sr.addOutput(new ProcessOutput(SCallee_SmartEnvironmentProvidedService.OUTPUT_TEMPERATURE_SENSORS,sensors.get(0).TouAALReponse()));
			} else {
				System.out.println("SCallee_SmartEnvironment:	Can not get temperature sensors from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_SET_TEMPERATURE_SENSOR_LOCATION)){
			String location = call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_TEMPERATURE_SENSOR_LOCATION).toString();
			ArrayList<eTemperatureSensor> sensors = getTemperatureSensors(inputLocation);
			if(sensors.size() != 0) {
				for(eTemperatureSensor sensor : sensors) {
					if(sensor.setDeviceLocation(location)) {
						sr = new ServiceResponse(CallStatus.succeeded);
						System.out.println("SCallee_SmartEnvironment:	set location successfully");
					} else {
						sr = new ServiceResponse(CallStatus.denied);
						System.out.println("SCallee_SmartEnvironment:	Can not set location for the specific temperaturesensor");
					}
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	Can not get temperature sensors from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}

		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_GET_AIRCONDITIONERS)){
			
			ArrayList<eAirConditioner> airconditioners = getAirconditioners(inputLocation);	
			if(airconditioners.size()!= 0) {
				sr = new ServiceResponse(CallStatus.succeeded);
				System.out.println("SCallee_SmartEnvironment:	returned airconditioner");
				sr.addOutput(new ProcessOutput(SCallee_SmartEnvironmentProvidedService.OUTPUT_AIRCONDTIONERS,airconditioners.get(0).TouAALReponse()));
			} else {
				System.out.println("SCallee_SmartEnvironment:	Can not get any airconditioner from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_TURN_ON_AIRCONDITIONER)){
			ArrayList<eAirConditioner> airconditioners = getAirconditioners(inputLocation);	
			if(airconditioners.size() != 0) {
				for(eAirConditioner airconditioner : airconditioners) {
					if(airconditioner.setOn()) {
						sr = new ServiceResponse(CallStatus.succeeded);
						System.out.println("SCallee_SmartEnvironment:	Turned airconditioner ON successfully");
					} else {
						sr = new ServiceResponse(CallStatus.denied);
						System.out.println("SCallee_SmartEnvironment:	Can not turn ON the specific airconditioner");
					}
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	can not locate the airconditioner from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
			
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_TURN_OFF_AIRCONDITIONER)){
			ArrayList<eAirConditioner> airconditioners = getAirconditioners(inputLocation);	
			if(airconditioners.size() != 0) {
				for(eAirConditioner airconditioner : airconditioners) {
					if(airconditioner.setOff()) {
						sr = new ServiceResponse(CallStatus.succeeded);
						System.out.println("SCallee_SmartEnvironment:	Turned airconditioner OFF successfully");
					} else {
						sr = new ServiceResponse(CallStatus.denied);
						System.out.println("SCallee_SmartEnvironment:	Can not turn OFF the specific airconditioner");
					}
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	can not locate the airconditioner from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
			
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_SET_AIRCONDITIONER_OPERATION_POWER_SAVING)){
			Boolean status = Boolean.parseBoolean(call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_AIRCONDITIONER_POWER_SAVING_OPTION).toString());
			ArrayList<eAirConditioner> airconditioners = getAirconditioners(inputLocation);	
			if(airconditioners.size() != 0) {
				for(eAirConditioner airconditioner : airconditioners) {
					if(airconditioner.setPowerSavingMode(status)) {
						sr = new ServiceResponse(CallStatus.succeeded);
						System.out.println("SCallee_SmartEnvironment:	set operation power-saving of the airconditioner successfully");
					} else {
						sr = new ServiceResponse(CallStatus.denied);
						System.out.println("SCallee_SmartEnvironment:	Can not set operation power-saving for this device");
					}
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	can not locate the airconditioner from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
			
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_SET_AIRCONDITIONER_OPERATION_MODE)){
			String inputMode = call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_AIRCONDITIONER_OPERATION_MODE).toString();
			ArrayList<eAirConditioner> airconditioners = getAirconditioners(inputLocation);	
			if(airconditioners.size() != 0) {
				for(eAirConditioner airconditioner : airconditioners) {
					if(airconditioner.setOperationMode(inputMode)) {
						sr = new ServiceResponse(CallStatus.succeeded);
						System.out.println("SCallee_SmartEnvironment:	set operation mode of the airconditioner successfully");
					} else {
						sr = new ServiceResponse(CallStatus.denied);
						System.out.println("SCallee_SmartEnvironment:	Can not set operation mode for this device");
					}
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	can not locate the airconditioner from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}			
			
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_SET_AIRCONDITIONER_LOCATION)){
			String location = call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_AIRCONDITIONER_LOCATION).toString();
			ArrayList<eAirConditioner> airconditioners = getAirconditioners(inputLocation);	
			if(airconditioners.size() != 0) {
				for(eAirConditioner airconditioner : airconditioners) {
					if(airconditioner.setDeviceLocation(location)) {
						sr = new ServiceResponse(CallStatus.succeeded);
						System.out.println("SCallee_SmartEnvironment:	Set location for airconditioner successfully");
					} else {
						sr = new ServiceResponse(CallStatus.denied);
						System.out.println("SCallee_SmartEnvironment:	Can not set location for the specific airconditioner");
					}
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	can not locate the airconditioner from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}		
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_SET_AIRCONDITIONER_TEMPERATURE)){
			Integer temp = Integer.parseInt(call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_AIRCONDITIONER_TEMPERATURE).toString());
			ArrayList<eAirConditioner> airconditioners = getAirconditioners(inputLocation);	
			if(airconditioners.size() != 0) {
				for(eAirConditioner airconditioner : airconditioners) {
					if(airconditioner.setSettingTemperature(temp.intValue())) {
						sr = new ServiceResponse(CallStatus.succeeded);
						System.out.println("SCallee_SmartEnvironment:	Set temperature for airconditioner successfully");
					} else {
						sr = new ServiceResponse(CallStatus.denied);
						System.out.println("SCallee_SmartEnvironment:	Can not set temperature for the specific airconditioner");
					}
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	can not locate the airconditioner from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}	
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_SET_AIRCONDITIONER_AIR_FLOW_RATE)){
			String airFlowRate = call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_AIRCONDITIONER_AIR_FLOW_RATE).toString();
			ArrayList<eAirConditioner> airconditioners = getAirconditioners(inputLocation);	
			if(airconditioners.size() != 0) {
				for(eAirConditioner airconditioner : airconditioners) {
					if(airconditioner.setDeviceAirFlowRate(airFlowRate)) {
						sr = new ServiceResponse(CallStatus.succeeded);
						System.out.println("SCallee_SmartEnvironment:	Set airflow rate for airconditioner successfully");
					} else {
						sr = new ServiceResponse(CallStatus.denied);
						System.out.println("SCallee_SmartEnvironment:	Can not set airflow rate for the specific airconditioner");
					}
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	can not locate the airconditioner from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_GET_LIGHTING_DEVICES)) {
			ArrayList<eLighting> lights = getLights(inputLocation);
			if(lights.size() != 0) {
				System.out.println("SCallee_SmartEnvironment:	returned light");
				sr = new ServiceResponse(CallStatus.succeeded);
				sr.addOutput(new ProcessOutput(SCallee_SmartEnvironmentProvidedService.OUTPUT_LIGHTINGS,lights.get(0).TouAALReponse()));
			} else {
				System.out.println("SCallee_SmartEnvironment:	Can not get any lighting device from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_TURN_ON_LIGHTING_DEVICE)) {
			ArrayList<eLighting> lights = getLights(inputLocation);
			if(lights.size() != 0) {
				for(eLighting light : lights) {
					if(light.setOn()) {
						sr = new ServiceResponse(CallStatus.succeeded);
						System.out.println("SCallee_SmartEnvironment:	Turned light ON successfully");
					} else {
						sr = new ServiceResponse(CallStatus.denied);
						System.out.println("SCallee_SmartEnvironment:	Can not turn ON the specific light");
					}	
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	can not locate any light from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
			
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_TURN_OFF_LIGHTING_DEVICE)) {
			ArrayList<eLighting> lights = getLights(inputLocation);
			if(lights.size() != 0) {
				for(eLighting light : lights) {
					if(light.setOff()) {
						sr = new ServiceResponse(CallStatus.succeeded);
						System.out.println("SCallee_SmartEnvironment:	Turned light OFF successfully");
					} else {
						sr = new ServiceResponse(CallStatus.denied);
						System.out.println("SCallee_SmartEnvironment:	Can not turn OFF the specific light");
					}
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	can not locate any light from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_SET_LIGHTING_ILLUMINATION_LEVEL)) {
			int brightness = Integer.parseInt(call.getInputValue(SCallee_SmartEnvironmentProvidedService.INPUT_LIGHTING_ILLUMINATION_LEVEL).toString());
			ArrayList<eLighting> lights = getLights(inputLocation);
			if(lights.size() != 0) {
				for(eLighting light : lights) {
					if(light.setDeviceBrightness(brightness)) {
						sr = new ServiceResponse(CallStatus.succeeded);
						System.out.println("SCallee_SmartEnvironment:	Set device brightness successfully");
					} else {
						sr = new ServiceResponse(CallStatus.denied);
						System.out.println("SCallee_SmartEnvironment:	Can not set the brightness of the specific light");
					}
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	can not locate any light from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
			
		} 
		else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_GET_CURTAIN_CONTROLLERS)) {
			ArrayList<eCurtain> curtains = getCurtains(inputLocation);
			if(curtains.size() != 0) {
				System.out.println("SCallee_SmartEnvironment:	returned curtain");
				sr = new ServiceResponse(CallStatus.succeeded);
				sr.addOutput(new ProcessOutput(SCallee_SmartEnvironmentProvidedService.OUTPUT_CURTAINS,curtains.get(0).TouAALReponse()));
			} else {
				System.out.println("SCallee_SmartEnvironment:	Can not get any curtain device from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_OPEN_CURTAIN)) {
			ArrayList<eCurtain> curtains = getCurtains(inputLocation);
			if(curtains.size() != 0) {
				for(eCurtain curtain: curtains) {
					if(curtain.open()) {
						sr = new ServiceResponse(CallStatus.succeeded);
						System.out.println("SCallee_SmartEnvironment:	Open Curtain successfully");
					} else {
						sr = new ServiceResponse(CallStatus.denied);
						System.out.println("SCallee_SmartEnvironment:	Can not open the specific curtain");
					}
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	can not locate any curtain from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
			
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_CLOSE_CURTAIN)) {
			ArrayList<eCurtain> curtains = getCurtains(inputLocation);
			if(curtains.size() != 0) {
				for(eCurtain curtain: curtains) {
					if(curtain.close()) {
						sr = new ServiceResponse(CallStatus.succeeded);
						System.out.println("SCallee_SmartEnvironment:	Close Curtain successfully");
					} else {
						sr = new ServiceResponse(CallStatus.denied);
						System.out.println("SCallee_SmartEnvironment:	Can not close the specific curtain");
					}
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	can not locate any curtain from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
		}
		else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_GET_CONSENTS)) {
			ArrayList<eElectricConsent> consents = getConsents(inputLocation);
			if(consents.size() != 0) { 
				System.out.println("SCallee_SmartEnvironment:	returned consent value");
				sr = new ServiceResponse(CallStatus.succeeded);
				sr.addOutput(new ProcessOutput(SCallee_SmartEnvironmentProvidedService.OUTPUT_CONSENTS,consents.get(0).TouAALReponse()));

			} else {
				System.out.println("SCallee_SmartEnvironment:	Can not get any consent from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_TURN_ON_CONSENTS)) {
			ArrayList<eElectricConsent> consents = getConsents(inputLocation);
			if(consents.size() != 0) { 
				for(eElectricConsent consent: consents) {
					if(consent.setOn()) {
						sr = new ServiceResponse(CallStatus.succeeded);
						System.out.println("SCallee_SmartEnvironment:	Turned consent ON successfully");
					} else {
						sr = new ServiceResponse(CallStatus.denied);
						System.out.println("SCallee_SmartEnvironment:	Can not switch ON the specific consent");
					}
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	can not locate any consent from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
			
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_TURN_OFF_CONSENTS)) {
			ArrayList<eElectricConsent> consents = getConsents(inputLocation);
			if(consents.size() != 0) { 
				for(eElectricConsent consent: consents) {
					if(consent.setOff()) {
						sr = new ServiceResponse(CallStatus.succeeded);
						System.out.println("SCallee_SmartEnvironment:	Turned consent OFF successfully");
					} else {
						sr = new ServiceResponse(CallStatus.denied);
						System.out.println("SCallee_SmartEnvironment:	Can not switch OFF the specific consent");
					}
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	can not locate any consent from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_GET_WINDOW)) {
			ArrayList<eWindow> windows = getWindows(inputLocation);
			if(windows.size() != 0) {
				System.out.println("SCallee_SmartEnvironment:	returned window value");
				sr = new ServiceResponse(CallStatus.succeeded);
				sr.addOutput(new ProcessOutput(SCallee_SmartEnvironmentProvidedService.OUTPUT_WINDOW,windows.get(0).TouAALReponse()));
			} else {
				System.out.println("SCallee_SmartEnvironment:	Can not get any window from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_OPEN_WINDOW)) {
			ArrayList<eWindow> windows = getWindows(inputLocation);
			if(windows.size() != 0) {
				for(eWindow window : windows) {
					if(window.open()) {
						sr = new ServiceResponse(CallStatus.succeeded);
						System.out.println("SCallee_SmartEnvironment:	Opening the window");
					} else {
						sr = new ServiceResponse(CallStatus.denied);
						System.out.println("SCallee_SmartEnvironment:	Can not open the window");
					}
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	can not locate any window from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
			
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_CLOSE_WINDOW)) {
			ArrayList<eWindow> windows = getWindows(inputLocation);
			if(windows.size() != 0) {
				for(eWindow window : windows) {
					if(window.close()) {
						sr = new ServiceResponse(CallStatus.succeeded);
						System.out.println("SCallee_SmartEnvironment:	Opening the window");
					} else {
						sr = new ServiceResponse(CallStatus.denied);
						System.out.println("SCallee_SmartEnvironment:	Can not open the window");
					}
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	can not locate any window from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
		}
		else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_GET_RADIO)) {
			ArrayList<eRadio> radios = getRadios(inputLocation);
			if(radios.size() != 0) {
				System.out.println("SCallee_SmartEnvironment:	returned radio value");
				sr = new ServiceResponse(CallStatus.succeeded);
				sr.addOutput(new ProcessOutput(SCallee_SmartEnvironmentProvidedService.OUTPUT_RADIO,radios.get(0).TouAALReponse()));
			} else {
				System.out.println("SCallee_SmartEnvironment:	Can not get any radio from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_TURN_ON_RADIO)) {
			ArrayList<eRadio> radios = getRadios(inputLocation);
			if(radios.size() != 0) {
				for(eRadio radio: radios) {
					if(radio.setOn()) {
						sr = new ServiceResponse(CallStatus.succeeded);
						System.out.println("SCallee_SmartEnvironment:	Turning ON radio");
					} else {
						sr = new ServiceResponse(CallStatus.denied);
						System.out.println("SCallee_SmartEnvironment:	Can not turn on the radio");
					}
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	can not locate any radio from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
			
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_TURN_OFF_RADIO)) {
			ArrayList<eRadio> radios = getRadios(inputLocation);
			if(radios.size() != 0) {
				for(eRadio radio: radios) {
					if(radio.setOff()) {
						sr = new ServiceResponse(CallStatus.succeeded);
						System.out.println("SCallee_SmartEnvironment:	Turning OFF radio");
					} else {
						sr = new ServiceResponse(CallStatus.denied);
						System.out.println("SCallee_SmartEnvironment:	Can not turn off the radio");
					}
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	can not locate any radio from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
		}
		
		else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_GET_TV)) {
			ArrayList<eTV> tvs = getTVs(inputLocation);
			if(tvs.size() != 0) {
				System.out.println("SCallee_SmartEnvironment:	returned TV value");
				sr = new ServiceResponse(CallStatus.succeeded);
				sr.addOutput(new ProcessOutput(SCallee_SmartEnvironmentProvidedService.OUTPUT_TV,tvs.get(0).TouAALReponse()));
			} else {
				System.out.println("SCallee_SmartEnvironment:	Can not get any tv from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_TURN_ON_TV)) {
			ArrayList<eTV> tvs = getTVs(inputLocation);
			if(tvs.size() != 0) {
				for(eTV tv: tvs) {
					if(tv.setOn()) {
						sr = new ServiceResponse(CallStatus.succeeded);
						System.out.println("SCallee_SmartEnvironment:	Turning ON tv");
					} else {
						sr = new ServiceResponse(CallStatus.denied);
						System.out.println("SCallee_SmartEnvironment:	Can not turn on the tv");
					}
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	can not locate any tv from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
			
		}else if(operation.startsWith(SCallee_SmartEnvironmentProvidedService.SERVICE_TURN_OFF_TV)) {
			ArrayList<eTV> tvs = getTVs(inputLocation);
			if(tvs.size() != 0) {
				for(eTV tv: tvs) {
					if(tv.setOff()) {
						sr = new ServiceResponse(CallStatus.succeeded);
						System.out.println("SCallee_SmartEnvironment:	Turning OFF tv");
					} else {
						sr = new ServiceResponse(CallStatus.denied);
						System.out.println("SCallee_SmartEnvironment:	Can not turn off the tv");
					}
				}
			} else {
				System.out.println("SCallee_SmartEnvironment:	can not locate any tv from iHouse");
				sr = new ServiceResponse(CallStatus.denied);
			}
		}
		else {
			System.out.println("The required service is not yet supported!!");
		}
		return sr;
	}
	private ArrayList<eTemperatureSensor> getTemperatureSensors(InstallationLocationValue inPutLocation) {
		String location = SampleConstants.LOCATION_UNDEFINED;
		if(inPutLocation!= null) {
			location = EchonetDataConverter.eNumtoInstallationLocation(inPutLocation);
		}
		ArrayList<eTemperatureSensor> list = new ArrayList<eTemperatureSensor>();
		for(EchonetLiteDevice dev: Activator.echonetDevices) {
			for(eDataObject obj: dev.getDataObjList()) {
				if(obj.getClass().equals(eTemperatureSensor.class)) {
					eTemperatureSensor ss = (eTemperatureSensor) obj;
					if(ss.getInstallLocation().equals(location)) {
						list.add(ss);
					} 
				}
			}
		}
		return list;
	}
	private ArrayList<eLighting> getLights(InstallationLocationValue inPutLocation) {
		String location = SampleConstants.LOCATION_UNDEFINED;
		if(inPutLocation!= null) {
			location = EchonetDataConverter.eNumtoInstallationLocation(inPutLocation);
		}
		ArrayList<eLighting> list = new ArrayList<eLighting>();
		for(EchonetLiteDevice dev: Activator.echonetDevices) {
			for(eDataObject obj: dev.getDataObjList()) {
				if(obj.getClass().equals(eLighting.class)) {
					eLighting light = (eLighting) obj;
					if(light.getInstallLocation().equals(location)) {
						list.add(light);
					} 
				}
			}
		}
		return list;
	}
	private ArrayList<eWindow> getWindows(InstallationLocationValue inPutLocation) {
		String location = SampleConstants.LOCATION_UNDEFINED;
		if(inPutLocation!= null) {
			location = EchonetDataConverter.eNumtoInstallationLocation(inPutLocation);
		}
		ArrayList<eWindow> list = new ArrayList<eWindow>();
		for(EchonetLiteDevice dev: Activator.echonetDevices) {
			for(eDataObject obj: dev.getDataObjList()) {
				if(obj.getClass().equals(eWindow.class)) {
					eWindow window = (eWindow) obj;
					if(window.getInstallLocation().equals(location)) {
						list.add(window);
					} 
				}
			}
		}
		return list;
	}
	
	// Airconditioner services
	private ArrayList<eAirConditioner> getAirconditioners(InstallationLocationValue inPutLocation) {
		String location = SampleConstants.LOCATION_UNDEFINED;
		if(inPutLocation!= null) {
			location = EchonetDataConverter.eNumtoInstallationLocation(inPutLocation);
		}
		ArrayList<eAirConditioner> list = new ArrayList<eAirConditioner>();
		for(EchonetLiteDevice dev: Activator.echonetDevices) {
			for(eDataObject obj: dev.getDataObjList()) {
				if(obj.getClass().equals(eAirConditioner.class)) {
					eAirConditioner aircon = (eAirConditioner) obj;
					if(aircon.getInstallLocation().equals(location)) {
						list.add(aircon);
					} 
				}
			}
		}
		return list;
	}
	
	private ArrayList<eElectricConsent> getConsents(InstallationLocationValue inPutLocation) {
		String location = SampleConstants.LOCATION_UNDEFINED;
		if(inPutLocation!= null) {
			location = EchonetDataConverter.eNumtoInstallationLocation(inPutLocation);
		}
		ArrayList<eElectricConsent> list = new ArrayList<eElectricConsent>();
		for(EchonetLiteDevice dev: Activator.echonetDevices) {
			for(eDataObject obj: dev.getDataObjList()) {
				if(obj.getClass().equals(eElectricConsent.class)) {
					eElectricConsent consent = (eElectricConsent) obj;
					if(consent.getInstallLocation().equals(location)) {
						list.add(consent);
					} 
				}
			}
		}
		return list;
	}
	private ArrayList<eRadio> getRadios(InstallationLocationValue inPutLocation) {
		String location = SampleConstants.LOCATION_UNDEFINED;
		if(inPutLocation!= null) {
			location = EchonetDataConverter.eNumtoInstallationLocation(inPutLocation);
		}
		ArrayList<eRadio> list = new ArrayList<eRadio>();
		for(EchonetLiteDevice dev: Activator.echonetDevices) {
			for(eDataObject obj: dev.getDataObjList()) {
				if(obj.getClass().equals(eRadio.class)) {
					eRadio radio = (eRadio) obj;
					if(radio.getInstallLocation().equals(location)) {
						list.add(radio);
					} 
				}
			}
		}
		return list;
	}
	
	private ArrayList<eTV> getTVs(InstallationLocationValue inPutLocation) {
		String location = SampleConstants.LOCATION_UNDEFINED;
		if(inPutLocation!= null) {
			location = EchonetDataConverter.eNumtoInstallationLocation(inPutLocation);
		}
		ArrayList<eTV> list = new ArrayList<eTV>();
		for(EchonetLiteDevice dev: Activator.echonetDevices) {
			for(eDataObject obj: dev.getDataObjList()) {
				if(obj.getClass().equals(eTV.class)) {
					eTV tv = (eTV) obj;
					if(tv.getInstallLocation().equals(location)) {
						list.add(tv);
					} 
				}
			}
		}
		return list;
	}
	
	private ArrayList<eCurtain> getCurtains(InstallationLocationValue inPutLocation) {
		String location = SampleConstants.LOCATION_UNDEFINED;
		if(inPutLocation!= null) {
			location = EchonetDataConverter.eNumtoInstallationLocation(inPutLocation);
		}
		ArrayList<eCurtain> list = new ArrayList<eCurtain>();
		for(EchonetLiteDevice dev: Activator.echonetDevices) {
			for(eDataObject obj: dev.getDataObjList()) {
				if(obj.getClass().equals(eCurtain.class)) {
					eCurtain curtain = (eCurtain) obj;
					if(curtain.getInstallLocation().equals(location)) {
						list.add(curtain);
					} 
				}
			}
		}
		return list;
	}
	
}
