package mainpackage;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.container.osgi.uAALBundleContainer;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.owl.OntologyManagement;
import org.universAAL.ontology.location.indoor.Home;
import org.universAAL.ontology.phThing.Device;
import org.universAAL.ontology.profile.HWSubProfile;

import ServiceBus.SCallee_SmartEnvironment;
import echonet.objects.EchonetLiteDevice;
import echonet.objects.eAirConditioner;
import echonet.objects.eDataObject;
import echonet.objects.eTemperatureSensor;
import echowand.logic.TooManyObjectsException;
import echowand.net.Inet4Subnet;
import echowand.net.SubnetException;
import echowand.object.EchonetObjectException;
import echowand.service.Core;
import echowand.service.Service;
import homegateway.services.EchonetDeviceScanner;
import homegateway.services.EchonetDeviceUpdater;
import ontologies.HomeAirConditioner;
import ontologies.OntologyHelper;
import ontologies.TemperatureSensor;



public class Activator implements BundleActivator {
	
	private CaressesOntology caresses_ontology = new CaressesOntology();
	
	public static BundleContext osgiContext = null;
	public static ModuleContext context = null;	
	
	public static Core echonetCore;
	public static Service echonetService = null;
	public static EchonetDeviceUpdater deviceUpdater = null;

	public static SCallee_CAHRIM scallee_CAHRIM;
	public static SCallee_CKB    scallee_CKB;
	public static SCallee_CSPEM  scallee_CSPEM;
	public static SCallee_SmartEnvironment scalle_SF = null;
	public static SCaller scaller;
	
	public static CSubscriber csubscriber = null;
	public static CPublisher  cpublisher  = null;
	
	
	public static SocketPublisher socketpublisher = null;
		
	// : Declare one individual for each CaressesComponent sub-class
	
	protected static Cahrim i_cahrim;
	protected static Ckb i_ckb;
	protected static Cspem i_cspem;
	protected static SmartFacility i_SmartFacility;
		// : Declare a variable to set or not the use of socket communication

	protected static boolean using_socket;
	// : Declare one individual for each DataMessage sub-sub-class
	
	protected static D1_1 i_D1_1;
	protected static D1_2 i_D1_2;
	protected static D2_1 i_D2_1;
	protected static D2_2 i_D2_2;
	protected static D2_3 i_D2_3;
	protected static D2_4 i_D2_4;
	protected static D3_1 i_D3_1;
	protected static D3_2 i_D3_2;
	protected static D4_1 i_D4_1;
	protected static D4_2 i_D4_2;
	protected static D5_1 i_D5_1;
	protected static D5_2 i_D5_2;
	protected static D6_1 i_D6_1;
	protected static D6_2 i_D6_2;
	protected static D6_3 i_D6_3;
	protected static D7_1 i_D7_1;
	protected static D7_2 i_D7_2;
	protected static D8_1 i_D8_1;
	protected static D8_2 i_D8_2;
	protected static D11_1 i_D11_1;
	protected static D11_2 i_D11_2;
	protected static D11_3 i_D11_3;
	protected static D11_4 i_D11_4;
	public static TemperatureSensor i_TemperatureSensor;
	public static HomeAirConditioner i_HomeAirConditoner;
	
	public static ArrayList<TemperatureSensor> temperatureSensorOntologies;
	public static ArrayList<HomeAirConditioner> homeAirconditionerOntologies;
	public static ArrayList<EchonetLiteDevice> echonetDevices;
	
	protected static String[] cahrim_inputs = {"D7.1", "D7.2", "D11.1", "D11.2", "D11.3", "D11.4"};
	protected static String[] ckb_inputs    = {"D5.1", "D5.2", "D8.1", "D8.2"};
	protected static String[] cspem_inputs  = {"D1.1", "D1.2", "D2.1", "D2.2", "D2.3", "D2.4", "D3.1", "D3.2", "D4.1", "D4.2", "D5.1", "D5.2", "D6.1", "D6.2", "D6.3"};
	protected static String[] cahrim_outputs = {"D5.1", "D5.2", "D6.1", "D6.2", "D6.3", "D8.1", "D8.2", "D10"};
	protected static String[] ckb_outputs    = {"D1.1", "D1.2", "D2.1", "D2.2", "D2.3", "D2.4", "D3.1", "D3.2", "D4.1", "D4.2", "D11.1", "D11.2", "D11.3", "D11.4"};
	protected static String[] cspem_outputs  = {"D7.1", "D7.2", "D9"};
	

	public void start(BundleContext bcontext) throws Exception {
		
		// : Set to which component this bundle refer (Component.CAHRIM, Component.CKB, Component.CSPEM)
		using_socket = false;		
		//Component.setThisComponentAs(Component.CKB); 
		Component.setThisComponentAs(Component.SMART_FACILITY);
		System.out.println(Component.component_ID + " ACTIVATOR: Application started\n");
		
		// initialize echonet interface
		
		initialEchonetInterface();
		
		Activator.osgiContext = bcontext;
		Activator.context = uAALBundleContainer.THE_CONTAINER.registerModule(new Object[] { bcontext });
		
		
		OntologyManagement.getInstance().register(context, caresses_ontology);

		if (Component.is_Cahrim)
			scallee_CAHRIM = new SCallee_CAHRIM(context);
		if (Component.is_Ckb)
			scallee_CKB = new SCallee_CKB(context);
		if (Component.is_Cspem)
			scallee_CSPEM = new SCallee_CSPEM(context);
		
		temperatureSensorOntologies = new ArrayList<TemperatureSensor>();
		homeAirconditionerOntologies = new ArrayList<HomeAirConditioner>();
		echonetDevices = new ArrayList<EchonetLiteDevice>();
		
		scaller     = new SCaller(context);
//		scallee     = new SCallee(context);
//		scaller     = new SCaller(context);
		csubscriber = new CSubscriber(context);
		cpublisher  = new CPublisher(context);
		
		//serviceCallee = new SCallee_SmartFacility(context);
		
		i_cahrim = new Cahrim(CaressesOntology.NAMESPACE + "I_CAHRIM");
		i_ckb    = new Ckb(CaressesOntology.NAMESPACE    + "I_CKB");
		i_cspem  = new Cspem(CaressesOntology.NAMESPACE  + "I_CSPEM");
		i_SmartFacility =  new SmartFacility(CaressesOntology.NAMESPACE + "I_SmartFacility");
		
		i_D1_1 = new D1_1(CaressesOntology.NAMESPACE + "I_D1.1");
		i_D1_2 = new D1_2(CaressesOntology.NAMESPACE + "I_D1.2");
		i_D2_1 = new D2_1(CaressesOntology.NAMESPACE + "I_D2.1");
		i_D2_2 = new D2_2(CaressesOntology.NAMESPACE + "I_D2.2");
		i_D2_3 = new D2_3(CaressesOntology.NAMESPACE + "I_D2.3");
		i_D2_4 = new D2_4(CaressesOntology.NAMESPACE + "I_D2.4");
		i_D3_1 = new D3_1(CaressesOntology.NAMESPACE + "I_D3.1");
		i_D3_2 = new D3_2(CaressesOntology.NAMESPACE + "I_D3.2");
		i_D4_1 = new D4_1(CaressesOntology.NAMESPACE + "I_D4.1");
		i_D4_2 = new D4_2(CaressesOntology.NAMESPACE + "I_D4.2");
		i_D5_1 = new D5_1(CaressesOntology.NAMESPACE + "I_D5.1");
		i_D5_2 = new D5_2(CaressesOntology.NAMESPACE + "I_D5.2");
		i_D6_1 = new D6_1(CaressesOntology.NAMESPACE + "I_D6.1");
		i_D6_2 = new D6_2(CaressesOntology.NAMESPACE + "I_D6.2");
		i_D6_3 = new D6_3(CaressesOntology.NAMESPACE + "I_D6.3");
		i_D7_1 = new D7_1(CaressesOntology.NAMESPACE + "I_D7.1");
		i_D7_2 = new D7_2(CaressesOntology.NAMESPACE + "I_D7.2");
		i_D8_1 = new D8_1(CaressesOntology.NAMESPACE + "I_D8.1");
		i_D8_2 = new D8_2(CaressesOntology.NAMESPACE + "I_D8.2");
		i_D11_1 = new D11_1(CaressesOntology.NAMESPACE + "I_D11.1");
		i_D11_2 = new D11_2(CaressesOntology.NAMESPACE + "I_D11.2");
		i_D11_3 = new D11_3(CaressesOntology.NAMESPACE + "I_D11.3");
		i_D11_4 = new D11_4(CaressesOntology.NAMESPACE + "I_D11.4");
		setObjectPropertiesRelations();
		
		getHomeResource();
		
		//System.out.println(cpublisher.publishHomeResource());
		scalle_SF = new SCallee_SmartEnvironment(context);
		/*
		if (using_socket){
			socketpublisher = new SocketPublisher(context);
			socketpublisher.startServer();
		} else {
			MainClass main_class = new MainClass();
			Thread t = new Thread(main_class);
			t.start();
		}
		*/
				
	}
	
	public void setObjectPropertiesRelations(){
		
		i_cahrim.setOutput(i_D5_1);
		i_cahrim.setOutput(i_D5_2);
		i_cahrim.setOutput(i_D6_1);
		i_cahrim.setOutput(i_D6_2);
		i_cahrim.setOutput(i_D6_3);
		i_cahrim.setOutput(i_D8_1);
		i_cahrim.setOutput(i_D8_2);
		
		i_ckb.setOutput(i_D1_1);
		i_ckb.setOutput(i_D1_2);
		i_ckb.setOutput(i_D2_1);
		i_ckb.setOutput(i_D2_2);
		i_ckb.setOutput(i_D2_3);
		i_ckb.setOutput(i_D2_4);
		i_ckb.setOutput(i_D3_1);
		i_ckb.setOutput(i_D3_2);
		i_ckb.setOutput(i_D4_1);
		i_ckb.setOutput(i_D4_2);
		i_ckb.setOutput(i_D11_1);
		i_ckb.setOutput(i_D11_2);
		i_ckb.setOutput(i_D11_3);
		i_ckb.setOutput(i_D11_4);
		
		i_cspem.setOutput(i_D7_1);
		i_cspem.setOutput(i_D7_2);		
		i_SmartFacility.setOutput(i_TemperatureSensor);
		i_SmartFacility.setOutput(i_HomeAirConditoner);
		
		i_D1_1.setInput(i_cspem);
		i_D1_2.setInput(i_cspem);
		i_D2_1.setInput(i_cspem);
		i_D2_2.setInput(i_cspem);
		i_D2_3.setInput(i_cspem);
		i_D2_4.setInput(i_cspem);
		i_D3_1.setInput(i_cspem);
		i_D3_2.setInput(i_cspem);
		i_D4_1.setInput(i_cspem);
		i_D4_2.setInput(i_cspem);
		i_D5_1.setInput(i_cspem);
		i_D5_1.setInput(i_ckb);
		i_D5_2.setInput(i_cspem);
		i_D5_2.setInput(i_ckb);
		i_D6_1.setInput(i_cspem);
		i_D6_2.setInput(i_cspem);
		i_D6_3.setInput(i_cspem);
		i_D7_1.setInput(i_cahrim);
		i_D7_2.setInput(i_cahrim);
		i_D8_1.setInput(i_ckb);
		i_D8_2.setInput(i_ckb);
		i_D11_1.setInput(i_cahrim);
		i_D11_2.setInput(i_cahrim);
		i_D11_3.setInput(i_cahrim);
		i_D11_4.setInput(i_cahrim);
		//i_TemperatureSensor.setInput(i_AALEnv);
	}
	
	public boolean initialEchonetInterface() throws SocketException, SubnetException, TooManyObjectsException {
		boolean isSuccessed = false;
		
		if(echonetService == null) {
			NetworkInterface nif = NetworkInterface.getByName("eno1");
			//NetworkInterface nif = NetworkInterface.getByName("enx00018ebb0b5a");
			echonetCore = new Core(Inet4Subnet.startSubnet(nif));
			echonetCore.startService();
			echonetService = new Service(echonetCore);
			deviceUpdater = new EchonetDeviceUpdater(echonetService);
			isSuccessed = true;
		} 
		if(isSuccessed) {
			System.out.println("Initilized ECHONET API successfully!");
		}
		return isSuccessed;
	}
	public void getHomeResource() {
		System.out.println("Getting Device Resources...");
		EchonetDeviceScanner deviceScanner = new EchonetDeviceScanner(Activator.echonetService);
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
					//ContextEvent temp  = new ContextEvent(, TemperatureSensor.MY_URI);
					cpublisher.publishCE(Activator.i_TemperatureSensor);
					
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
					//System.out.println("Published: " + Activator.i_HomeAirConditoner.getProperty(TemperatureSensor.MY_URI));
					
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
			System.out.println("	RDFObjectList<TemperatureSensor>.get(0)ne :");
			System.out.println("		URI:"+Activator.temperatureSensorOntologies.get(0).getURI());
			System.out.println("		OP:"+Activator.temperatureSensorOntologies.get(0).getOperationStatus());
			System.out.println("		LOC:"+Activator.temperatureSensorOntologies.get(0).getMesuredTemperatureValue());
			
			System.out.println(Activator.homeAirconditionerOntologies.size() + " AirConditioner");
			System.out.println("	RDFObjectList<homeAirconditionerOntologies>.get(0)aaaaa:");
			System.out.println("		URI:"+Activator.homeAirconditionerOntologies.get(0).getClassURI());
			System.out.println("		OP:"+Activator.homeAirconditionerOntologies.get(0).getAirFlowRateSetting());
		}
	}

	public void stop(BundleContext arg0) throws Exception {
		
		scallee_CAHRIM.close();
		scallee_CKB.close();
		scallee_CSPEM.close();
		scaller.close();
		csubscriber.close();
		cpublisher.close();
		socketpublisher.close();
		
		System.out.println(Component.component_ID + " ACTIVATOR: Application stopped\n");
	
	}

}
