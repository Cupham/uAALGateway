package mainpackage;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.container.osgi.uAALBundleContainer;
import org.universAAL.middleware.container.utils.LogUtils;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.interfaces.configuration.scope.Scope;
import org.universAAL.middleware.interfaces.mpa.model.Bundle;
import org.universAAL.middleware.owl.OntologyManagement;


import ContextBus.CPublisher;
import ServiceBus.SCallee_SmartEnvironment;
import echonet.objects.EchonetLiteDevice;
import echonet.objects.HomeResourceObserver;
import echonet.objects.NodeProfileObject;
import echonet.objects.eAirConditioner;
import echonet.objects.eDataObject;
import echonet.objects.eTemperatureSensor;
import echowand.common.EOJ;
import echowand.logic.TooManyObjectsException;
import echowand.monitor.Monitor;
import echowand.monitor.MonitorListener;
import echowand.net.Inet4Subnet;
import echowand.net.Node;
import echowand.net.SubnetException;
import echowand.object.EchonetObjectException;
import echowand.service.Core;
import echowand.service.Service;
import homegateway.services.EchonetDeviceScanner;
import homegateway.services.EchonetDeviceUpdater;
import ontologies.Curtain;
import ontologies.ElectricConsent;
import ontologies.HomeAirConditioner;
import ontologies.Lighting;
import ontologies.TemperatureSensor;
import utils.OntologyHelper;
import utils.SetUtilities;



public class Activator implements BundleActivator {
	
	private CaressesOntology caresses_ontology = new CaressesOntology();
	
	public static BundleContext osgiContext = null;
	public static ModuleContext context = null;	
	
	public static Core echonetCore;
	public static Service echonetService = null;
	public static EchonetDeviceUpdater deviceUpdater = null;
	public static EchonetDeviceScanner deviceScanner = null;

	public static SCallee_SmartEnvironment scalle_SF = null;
	
	public static CPublisher cPublisher = null;
	// : Declare one individual for each CaressesComponent sub-class

	protected static SmartFacility i_SmartFacility;
		// : Declare a variable to set or not the use of socket communication

	protected static boolean using_socket;
	// : Declare one individual for each DataMessage sub-sub-class
	
	public static TemperatureSensor i_TemperatureSensor;
	public static HomeAirConditioner i_HomeAirConditoner;
	public static Lighting i_Lighting;
	public static Curtain i_Curtain;
	public static ElectricConsent i_Consent;
	
	//public static ArrayList<TemperatureSensor> temperatureSensorOntologies;
	public static HashMap<String, TemperatureSensor> temperatureSensorOntologies;
	public static HashMap<String, HomeAirConditioner> homeAirconditionerOntologies;
	public static HashMap<String, Lighting> lightingOntologies;
	public static HashMap<String, Curtain> curtainOntologies;
	public static HashMap<String, ElectricConsent> consentOntologies;
	public static ArrayList<EchonetLiteDevice> echonetDevices;
	private Timer timer;
	public static Set<String> changedOntologies;
	public void start(BundleContext bcontext) throws Exception {
		
		// : Set to which component this bundle refer (Component.CAHRIM, Component.CKB, Component.CSPEM)
		Component.setThisComponentAs(Component.SMART_FACILITY);
		//Component.setThisComponentAs(Component.CKB); 
		Activator.osgiContext = bcontext;
		System.out.println("START" + Component.component_ID + " ACTIVATOR: Application started\n");
		Activator.context = uAALBundleContainer.THE_CONTAINER.registerModule(new Object[] { bcontext });
		// initialize echonet interface
		
		OntologyManagement.getInstance().register(context, caresses_ontology);
		changedOntologies = new CopyOnWriteArraySet<String>();
		temperatureSensorOntologies = new HashMap<String,TemperatureSensor>();
		homeAirconditionerOntologies = new HashMap<String,HomeAirConditioner>();
		lightingOntologies = new HashMap<String,Lighting>();
		curtainOntologies = new HashMap<String,Curtain>();
		consentOntologies =  new HashMap<String, ElectricConsent>();
		echonetDevices = new ArrayList<EchonetLiteDevice>();
		
		i_SmartFacility =  new SmartFacility(CaressesOntology.NAMESPACE + "I_SmartFacility");
		//serviceCallee = new SCallee_SmartFacility(context);
		cPublisher = new CPublisher(Activator.context);
		
		setObjectPropertiesRelations();
		//getHomeResource();
		
		//System.out.println(cpublisher.publishHomeResource());
		scalle_SF = new SCallee_SmartEnvironment(context);
		timer = new Timer(true);
		initialEchonetInterface();
		
		

		
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				publishChangedOntology();
			}
		}, 3000, 20000);	 
	}

	public void setObjectPropertiesRelations(){
		
		i_SmartFacility.setOutput(i_TemperatureSensor);
		i_SmartFacility.setOutput(i_HomeAirConditoner);	
		i_SmartFacility.setOutput(i_Lighting);	
		i_SmartFacility.setOutput(i_Curtain);
		i_SmartFacility.setOutput(i_Consent);
		
	}
	
	public void publishChangedOntology() {
		if(changedOntologies.size() == 0) {
			System.out.println("Ontology is up to date");
		} else {
			for(String URI :changedOntologies) {
				if(URI.contains("I_Temperature")) {
					Activator.i_TemperatureSensor = temperatureSensorOntologies.get(URI);
					ContextEvent sensor  = new ContextEvent(Activator.i_TemperatureSensor, TemperatureSensor.MY_URI);
					cPublisher.publicContextEvent(sensor);
					SetUtilities.removeIfExistFromSet(Activator.changedOntologies, URI);
				} else if(URI.contains("I_Airconditioner")) {
					Activator.i_HomeAirConditoner = homeAirconditionerOntologies.get(URI);
					ContextEvent aircon  = new ContextEvent(Activator.i_HomeAirConditoner, HomeAirConditioner.MY_URI);
					cPublisher.publicContextEvent(aircon);
					SetUtilities.removeIfExistFromSet(Activator.changedOntologies, URI);
				} else if(URI.contains("I_Lighting")) {
					Activator.i_Lighting = lightingOntologies.get(URI);
					ContextEvent lighting  = new ContextEvent(Activator.i_Lighting, Lighting.MY_URI);
					cPublisher.publicContextEvent(lighting);
					SetUtilities.removeIfExistFromSet(Activator.changedOntologies, URI);
				}else if(URI.contains("I_Curtain")) {
					Activator.i_Curtain = curtainOntologies.get(URI);
					ContextEvent curtain  = new ContextEvent(Activator.i_Curtain, Curtain.MY_URI);
					cPublisher.publicContextEvent(curtain);
					SetUtilities.removeIfExistFromSet(Activator.changedOntologies, URI);
				}else if(URI.contains("I_Consent")) {
					Activator.i_Consent = consentOntologies.get(URI);
					ContextEvent consent  = new ContextEvent(Activator.i_Consent, ElectricConsent.MY_URI);
					cPublisher.publicContextEvent(consent);
					SetUtilities.removeIfExistFromSet(Activator.changedOntologies, URI);
				}
			}
		}
	}
	public boolean initialEchonetInterface() throws SocketException, SubnetException, TooManyObjectsException {
		boolean isSuccessed = false;
		
		if(echonetService == null) {
			NetworkInterface nif = NetworkInterface.getByName("vpn_ihouse");
			echonetCore = new Core(Inet4Subnet.startSubnet(nif));
			echonetCore.startService();
			echonetService = new Service(echonetCore);
			// deviceScanner = new EchonetDeviceScanner(Activator.echonetService);
			deviceUpdater = new EchonetDeviceUpdater(echonetService);
			
			Monitor monitor = new Monitor(echonetCore);
			monitor.addMonitorListener(new MonitorListener() {
	            @Override
	            public void detectEOJsJoined(Monitor monitor, Node node, List<EOJ> eojs) {
	            	System.out.println("initialEchonetInterface: detectEOJsJoined: " + node + " " + eojs);
	               // System.out.println("detectEOJsJoined: " + node + " " + eojs);
	                EchonetLiteDevice eDevice = new EchonetLiteDevice(node);
	                NodeProfileObject profile = null;
	                for(EOJ eoj :  eojs) {
	                	if(eoj.isProfileObject()) {
	                		profile = new NodeProfileObject(node, eoj);
	                		profile.ParseProfileObjectFromEPC(echonetService);
	                		eDevice.setProfileObj(profile);
	                	} else if(eoj.isDeviceObject()) {
	                		try {
								eDevice.parseDataObject(eoj,node,echonetService);
								new HomeResourceObserver(eDevice);
							} catch (EchonetObjectException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                	}
	                }
	                echonetDevices.add(eDevice);              
	            }

	            @Override
	            public void detectEOJsExpired(Monitor monitor, Node node, List<EOJ> eojs) {
	            	System.out.println("initialEchonetInterface: detectEOJsExpired: " + node + " " + eojs);
	            }
			});
			monitor.start();
			isSuccessed = true;
		} 
		
		if(isSuccessed) {
			System.out.println("Initilized ECHONET API successfully!");
		}
		return isSuccessed;
	}
	/*
	public void getHomeResource() {
		System.out.println("Getting Device Resources...");
		ArrayList<eTemperatureSensor> sensorList = new ArrayList<eTemperatureSensor>();
		ArrayList<eAirConditioner> airConditionerList =  new ArrayList<eAirConditioner>();
		try {
			echonetDevices = deviceScanner.scanEDevices();
			
			if(echonetDevices.size() == 0) {
				System.out.println("	There is no device in iHouse");
			} else {
				for (EchonetLiteDevice dev : echonetDevices) {	
					for(eDataObject dataObj : dev.getDataObjList()) {
						if(dataObj.getClass().equals(eTemperatureSensor.class)) {
							eTemperatureSensor tempSensor = (eTemperatureSensor) dataObj;
							if(tempSensor != null) {
								tempSensor.setDeviceIP(dev.getProfileObj().getDeviceIP());
								sensorList.add(tempSensor);								
								}
						} else if(dataObj.getClass().equals(eAirConditioner.class)){
							eAirConditioner airCondtioner = (eAirConditioner) dataObj;
							if(airCondtioner != null) {
								airCondtioner.setDeviceIP(dev.getProfileObj().getDeviceIP());
								airConditionerList.add(airCondtioner);
							}
								
						}
					}
				}	
			}
				
			// Create Ontology for uAAL Middleware from categorized objects.
			
			//TemperatureSensor Ontology
			if(sensorList.size() == 0) {
				System.out.println("ERR: There is no Echonet Temperature Sensor in the home network");
			} else {
				for(eTemperatureSensor temperatureSensor : sensorList) {
					String uriSuffix =  temperatureSensor.getDeviceIP() + "_"+temperatureSensor.getInstanceCode(); 
					System.out.println("Translating uAAL objects(TemperatureSensor "+temperatureSensor.getInstanceCode()+") with IP "+temperatureSensor.getDeviceIP() +" to uAAL objects...");
					Activator.i_TemperatureSensor = new TemperatureSensor(CaressesOntology.NAMESPACE +"I_TemperatureSensor"+uriSuffix);
					OntologyHelper.initTemperatureSensorOntology(temperatureSensor, Activator.i_TemperatureSensor);	
					Activator.temperatureSensorOntologies.add(Activator.i_TemperatureSensor);
				   // ContextEvent sen  = new ContextEvent(Activator.i_TemperatureSensor, TemperatureSensor.MY_URI);
					//cpublisher.publishCE(sen);
					
				}
			}
			
			//Home Airconditioner Ontology
			if(airConditionerList.size() == 0) {
				System.out.println("ERR: There is no Airconditioner in the home network");
			} else {
				for( eAirConditioner airconditioner : airConditionerList) {
					String uriSuffix =  airconditioner.getDeviceIP() + "_"+airconditioner.getInstanceCode(); 
					System.out.println("Translating uAAL objects(Airconditioner "+airconditioner.getInstanceCode()+") with IP "+airconditioner.getDeviceIP() +" to uAAL objects...");
					Activator.i_HomeAirConditoner = new HomeAirConditioner(CaressesOntology.NAMESPACE +"I_Airconditioner"+uriSuffix);
					OntologyHelper.initHomeAirconditionerOntology(airconditioner, Activator.i_HomeAirConditoner);
					Activator.homeAirconditionerOntologies.add(Activator.i_HomeAirConditoner);
					//ContextEvent air  = new ContextEvent(Activator.i_HomeAirConditoner, HomeAirConditioner.MY_URI);
					//cpublisher.publishCE(air);
					
				}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SubnetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TooManyObjectsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EchonetObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println(Activator.temperatureSensorOntologies.size() + " temperature sensor");		
			System.out.println(Activator.homeAirconditionerOntologies.size() + " AirConditioner");
		}
	}
	*/
	public void stop(BundleContext arg0) throws Exception {
		scalle_SF.close();
		System.out.println(Component.component_ID + " ACTIVATOR: Application stopped\n");
	
	}

}
