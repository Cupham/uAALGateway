package main;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArraySet;



import org.universAAL.middleware.context.ContextEvent;
import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.container.osgi.uAALBundleContainer;
import org.universAAL.middleware.owl.OntologyManagement;
import org.universAAL.ontology.echonetontology.EchonetOntology;
import org.universAAL.ontology.echonetontology.airconditionerRelatedDevices.HomeAirConditioner;
import org.universAAL.ontology.echonetontology.housingFacilitiesRelatedDevices.GeneralLighting;
import org.universAAL.ontology.echonetontology.managementOperationRelatedDevices.Switch;
import org.universAAL.ontology.echonetontology.sensorRelatedDevices.TemperatureSensor;

import echonet.Objects.EchonetLiteDevice;
import echonet.Objects.NodeProfileObject;
import echonet.Objects.eAirConditioner;
import echonet.Objects.eCurtain;
import echonet.Objects.eDataObject;
import echonet.Objects.eGeneralLighting;
import echonet.Objects.eRadio;
import echonet.Objects.eTV;
import echonet.Objects.eTemperatureSensor;
import echonet.Objects.eWindow;
import echonet.Services.CommandExcutor;
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
import uAAL.ContextBus.CPublisher;
import uAAL.ServiceBus.SCallee_SmartEnvironment;

import utils.SetUtilities;



public class Activator implements BundleActivator {
	private static final Logger LOGGER = Logger.getLogger(Activator.class.getName());
	
	private CaressesOntology caresses_ontology = new CaressesOntology();
	public static  EchonetOntology echonet_ontology = new EchonetOntology();
	public static BundleContext osgiContext = null;
	public static ModuleContext context = null;	
	public static boolean enable_ContextBus;
	private String NIF = "eno1";

	public static Core echonetCore;
	public static Service echonetService = null;
	public static CommandExcutor commandExecutor = null;

	public static SCallee_SmartEnvironment scalle_SF = null;
	
	public static CPublisher cPublisher = null;
	// : Declare one individual for each CaressesComponent sub-class

	protected static SmartFacility i_SmartFacility;
		// : Declare a variable to set or not the use of socket communication

	protected static boolean using_socket;
	// : Declare one individual for each DataMessage sub-sub-class
	public static int counter = 0;
	public static TemperatureSensor i_TemperatureSensor;
	public static HomeAirConditioner i_HomeAirConditoner;
	public static GeneralLighting i_Lighting;
	public static Switch i_Consent;
	
	//public static ArrayList<TemperatureSensor> temperatureSensorOntologies;
	public static HashMap<String, TemperatureSensor> temperatureSensorOntologies;
	public static HashMap<String, HomeAirConditioner> homeAirconditionerOntologies;
	public static HashMap<String, GeneralLighting> lightingOntologies;
	public static HashMap<String, Switch> consentOntologies;
	public static ArrayList<EchonetLiteDevice> echonetDevices;
	private Timer timer;
	public static Set<String> changedOntologies;
	public void start(BundleContext bcontext) throws Exception {
		
		// : Set to which component this bundle refer (Component.CAHRIM, Component.CKB, Component.CSPEM)
		Component.setThisComponentAs(Component.SMART_FACILITY);
		enable_ContextBus = false;
		//Component.setThisComponentAs(Component.CKB); 
		Activator.osgiContext = bcontext;
		Activator.context = uAALBundleContainer.THE_CONTAINER.registerModule(new Object[] { bcontext });
		LOGGER.info("Application Started");
		// initialize echonet interface
		
		
		OntologyManagement.getInstance().register(context, caresses_ontology);
		OntologyManagement.getInstance().register(context, echonet_ontology);
		changedOntologies = new CopyOnWriteArraySet<String>();
		temperatureSensorOntologies = new HashMap<String,TemperatureSensor>();
		homeAirconditionerOntologies = new HashMap<String,HomeAirConditioner>();
		lightingOntologies = new HashMap<String,GeneralLighting>();
		consentOntologies =  new HashMap<String, Switch>();
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
				counter+=1;
				System.out.println("------------------RUN: " + counter + ", Number of Device : " + echonetDevices.size()+" ------------------");
				//publishChangedOntology();
				printtheList();
			}
		}, 2000, 60000);	 
	}

	public void setObjectPropertiesRelations(){
		
		i_SmartFacility.setOutput(i_TemperatureSensor);
		i_SmartFacility.setOutput(i_HomeAirConditoner);	
		i_SmartFacility.setOutput(i_Lighting);	
		i_SmartFacility.setOutput(i_Consent);
		
	}
	public void printtheList() {
		for(EchonetLiteDevice dev: Activator.echonetDevices) {
			for(eDataObject obj: dev.getDataObjList()) {
				if(obj.getClass().equals(eGeneralLighting.class)) {
					eGeneralLighting light = (eGeneralLighting) obj;
					System.out.println("Light: " +light.getNode().toString() + " " + light.getInstallLocation());
				} else if(obj.getClass().equals(eTemperatureSensor.class)) {
					eTemperatureSensor light = (eTemperatureSensor) obj;
					System.out.println("Temperature Sensor: " +light.getNode().toString() + " " + light.getInstallLocation());
				}else if(obj.getClass().equals(eAirConditioner.class)) {
					eAirConditioner light = (eAirConditioner) obj;
					System.out.println("HomeAirConditioner: " +light.getNode().toString() + " " + light.getInstallLocation());
				}else if(obj.getClass().equals(eCurtain.class)) {
					eCurtain light = (eCurtain) obj;
					System.out.println("eCurtain: " +light.getNode().toString() + " " + light.getInstallLocation());
				}else if(obj.getClass().equals(eWindow.class)) {
					eWindow light = (eWindow) obj;
					System.out.println("eWindow: " +light.getNode().toString() + " " + light.getInstallLocation());
				}else if(obj.getClass().equals(eTV.class)) {
					eTV light = (eTV) obj;
					System.out.println("eTV: " +light.getNode().toString() + " " + light.getInstallLocation());
				}else if(obj.getClass().equals(eRadio.class)) {
					eRadio light = (eRadio) obj;
					System.out.println("eRadio: " +light.getNode().toString() + " " + light.getInstallLocation());
				}
				
			}
		}
	}
	
	public static int count_node = 0;
	public static int count_EOJ = 0;
	public static int count_profile_object = 0;
	public boolean initialEchonetInterface() throws SocketException, SubnetException, TooManyObjectsException {
		boolean isSuccessed = false;
		
		if(echonetService == null) {
			
			NetworkInterface nif = NetworkInterface.getByName(NIF);
			echonetCore = new Core(Inet4Subnet.startSubnet(nif));
			echonetCore.startService();
			echonetService = new Service(echonetCore);
			// deviceScanner = new EchonetDeviceScanner(Activator.echonetService);
			commandExecutor = new CommandExcutor(echonetService);
			
			Monitor monitor = new Monitor(echonetCore);
			monitor.addMonitorListener(new MonitorListener() {
	            @Override
	            public void detectEOJsJoined(Monitor monitor, Node node, List<EOJ> eojs) {
	            	count_node++;
	            	System.out.println("initialEchonetInterface: detectEOJsJoined: " + node + " " + eojs);
	                EchonetLiteDevice eDevice = new EchonetLiteDevice(node);
	                NodeProfileObject profile = null;        
	                for(EOJ eoj :  eojs) {        	       
	            	    if(eoj.isProfileObject()) {
	            	    	count_profile_object ++;
	            	    	System.out.println("Timelog: count_profile_object " + count_profile_object + " " + System.currentTimeMillis() + "                                                 \n");
	                		profile = new NodeProfileObject(node, eoj);
	                		profile.ParseProfileObjectFromEPC(echonetService);
	                		eDevice.setProfileObj(profile);
	                	} else if(eoj.isDeviceObject()) {
	                		try {
	                			count_EOJ += 1;
								eDevice.parseDataObject(eoj,node,echonetService);
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
	public void stop(BundleContext arg0) throws Exception {
		scalle_SF.close();
		System.out.println(Component.component_ID + " ACTIVATOR: Application stopped\n");
	
	}

}
