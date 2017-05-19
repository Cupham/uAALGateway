package mainpackage;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.container.osgi.uAALBundleContainer;
import org.universAAL.middleware.owl.OntologyManagement;
import org.universAAL.ontology.phThing.Device;

import services.ScanEchonetDevice;
import utils.SerializeUtils;

import echonet.objects.EchonetLiteDevice;
import echonet.objects.eTemperatureSensor;
import echowand.logic.TooManyObjectsException;
import echowand.net.Inet4Subnet;
import echowand.net.SubnetException;
import echowand.object.EchonetObjectException;
import echowand.service.Core;
import echowand.service.Service;



public class Activator implements BundleActivator {
	
	private CaressesOntology caresses_ontology = new CaressesOntology();
	
	public static BundleContext osgiContext = null;
	public static ModuleContext context = null;	
	
	public static Core echonetCore;
	public static Service echonetService = null;

//	public static SCallee scallee = null;
//	public static SCaller scaller = null;
	
	public static CSubscriber csubscriber = null;
	public static CPublisher  cpublisher  = null;
	public static TemperatureServiceCallee serviceCallee = null;
	
	public static SocketPublisher socketpublisher = null;
		
	// : Declare one individual for each CaressesComponent sub-class
	
	protected static Cahrim i_cahrim;
	protected static Ckb i_ckb;
	protected static Cspem i_cspem;
	protected static AALEnvironment i_AALEnv;
	
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
	protected static TemperatureSensor i_TemperatureSensor;
	protected static ArrayList<TemperatureSensor> eSensorList;

	public void start(BundleContext bcontext) throws Exception {
		
		// : Set to which component this bundle refer (Component.CAHRIM, Component.CKB, Component.CSPEM)
		//Component.setThisComponentAs(Component.CKB); 
		Component.setThisComponentAs(Component.AALENVIRONMENT);
		System.out.println(Component.component_ID + " ACTIVATOR: Application started\n");
		
		// initialize echonet interface
		
		initialEchonetInterface();
		
		Activator.osgiContext = bcontext;
		Activator.context = uAALBundleContainer.THE_CONTAINER.registerModule(new Object[] { bcontext });
		
		
		OntologyManagement.getInstance().register(context, caresses_ontology);
		
		eSensorList = new ArrayList<TemperatureSensor>();
//		scallee     = new SCallee(context);
//		scaller     = new SCaller(context);
		//csubscriber = new CSubscriber(context);
		cpublisher  = new CPublisher(context);
		serviceCallee = new TemperatureServiceCallee(context);
		
		i_cahrim = new Cahrim(CaressesOntology.NAMESPACE + "I_CAHRIM");
		i_ckb    = new Ckb(CaressesOntology.NAMESPACE    + "I_CKB");
		i_cspem  = new Cspem(CaressesOntology.NAMESPACE  + "I_CSPEM");
		i_AALEnv =  new AALEnvironment(CaressesOntology.NAMESPACE + "I_AALEnvironment");
		
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
		//i_TemperatureSensor = new TemperatureSensor(CaressesOntology.NAMESPACE +"I_TemperatureSensor");
		setObjectPropertiesRelations();
		
		getHomeResource();
		
		serviceCallee = new TemperatureServiceCallee(context);
		
		//socketpublisher = new SocketPublisher(context);
		//socketpublisher.startServer();
				
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
		
		i_AALEnv.setOutput(i_TemperatureSensor);
		
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
			//NetworkInterface nif = NetworkInterface.getByName("eno1");
			NetworkInterface nif = NetworkInterface.getByName("enx00018ebb0b5a");
			echonetCore = new Core(Inet4Subnet.startSubnet(nif));
			echonetCore.startService();
			echonetService = new Service(echonetCore);
			isSuccessed = true;
		}
		return isSuccessed;
	}
	public void getHomeResource() {
		ArrayList<EchonetLiteDevice> echonetDeviceList = new ArrayList<EchonetLiteDevice>();
		ScanEchonetDevice deviceScanner = new ScanEchonetDevice(Activator.echonetService);
		ArrayList<eTemperatureSensor> sensorList = new ArrayList<eTemperatureSensor>();
		try {
			echonetDeviceList = deviceScanner.scanEDevices();
			
			if(echonetDeviceList.size() == 0) {
				System.out.println("INFO: There is no device in iHouse");
			} else {
				for (EchonetLiteDevice dev : echonetDeviceList) {				
					eTemperatureSensor temperatureSensor =SerializeUtils.temperatureSensorFromEDataObjects(dev.getDataObjList());
					if(temperatureSensor != null) {
						temperatureSensor.setProfile(dev.getProfileObj());
						sensorList.add(temperatureSensor);					
					}		
				}
				
				if(sensorList.size()>0) {
					for(int i=0; i< sensorList.size();i++) {
						Activator.i_TemperatureSensor = new TemperatureSensor(CaressesOntology.NAMESPACE +"I_TemperatureSensor"+sensorList.get(i).getProfile().getDeviceIP());
						String msg = SerializeUtils.messageFromTemperatureSensor(sensorList.get(i));
						Activator.i_TemperatureSensor.changeProperty(TemperatureSensor.PROPERTY_HAS_TEMPERATURE_SENSOR_DESCRIPTION, msg);
						Activator.eSensorList.add(Activator.i_TemperatureSensor);
					}
					 
				} else {
					System.out.println("INFO: There is no temperature sensor in iHouse");
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
			System.out.println(Activator.eSensorList.size() + " temperature sensor");
		}
	}

	public void stop(BundleContext arg0) throws Exception {
		
//		scallee.close();
//		scaller.close();
		csubscriber.close();
		cpublisher.close();
		socketpublisher.close();
		
		System.out.println(Component.component_ID + " ACTIVATOR: Application stopped\n");
	
	}

}
