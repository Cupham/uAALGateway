package mainpackage;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;

import ont.MySensor;
import ont.MySensorOntology;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.container.osgi.uAALBundleContainer;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.owl.Ontology;
import org.universAAL.middleware.owl.OntologyManagement;

import services.ScanEchonetDevice;
import utils.Constants;

import context.CPublisher;
import context.CSubcriber;
import context.SensorPublisher;
import context.SensorStateSubscriber;
import echonet.objects.EchonetLiteDevice;
import echonet.objects.eTemperatureSensor;
import echowand.logic.TooManyObjectsException;
import echowand.net.Inet4Subnet;
import echowand.net.SubnetException;
import echowand.object.EchonetObjectException;
import echowand.service.Core;
import echowand.service.Service;


public class Activator implements BundleActivator {
	public static BundleContext osgiContext = null;
	public static ModuleContext mContext = null;
	
	private Core echonetCore;
	private static Service echonetService = null;
	
	
	
	
	// Ontology area
	//
	private Ontology mySensorOnt = new MySensorOntology();
	public static CPublisher pub;
	public static SensorStateSubscriber csub;
	public static CSubcriber sub;
	boolean clientMode = true;
	

	public void start(final BundleContext bcontext) throws Exception {
		// Start Context
		Activator.osgiContext = bcontext;
		Activator.mContext = uAALBundleContainer.THE_CONTAINER.registerModule(new Object[] {bcontext});
		
		// Load ontology manager
		OntologyManagement.getInstance().register(mContext, mySensorOnt);
		pub = new CPublisher(mContext);
		if(clientMode) {		
			csub = new SensorStateSubscriber(mContext);
			// Initialize ECHONET interface 
			initialEchonetInterface();
			
			// Get device resources in iHouse
			ArrayList<EchonetLiteDevice> echonetDeviceList = new ArrayList<EchonetLiteDevice>();
			echonetDeviceList = getiHouseDevices();
			
			// Convert temperature device resources to DRF
			
			ArrayList<MySensor> sensorDRFList = new ArrayList<MySensor>();
			sensorDRFList = convertTemperatureSensorToRDF(echonetDeviceList);
			//System.out.println("I am subcriber");
			//sub = new SensorStateSubscriber(mContext);
			for(MySensor sensor : sensorDRFList) {
				ContextEvent mySensorContextEvent = new ContextEvent(sensor, MySensor.MY_URI);
				pub.publishContextEvent(mySensorContextEvent);
			}

			
		} else {
			System.out.println("Hello I am subcriber");
			sub = new CSubcriber(mContext);
			//MySensor s = new MySensor("http://ontology.universaal.org/MySenSorOntology.owl#"+Constants.TEMPERATURE_EVENT+"192.168.1.1");
			//s.changeProperty(MySensor.PROP_LOCALTION, "My House");
			//s.changeProperty(MySensor.PROP_OPERATION_STATUS,true);
			//ContextEvent mySensorContextEvent = new ContextEvent(s, MySensor.MY_URI);
			//pub.publishContextEvent(mySensorContextEvent);
			
		}
	}
	public void stop(BundleContext arg0) throws Exception {
		OntologyManagement.getInstance().unregister(mContext, mySensorOnt);
		if(sub != null) {
			sub.close();
		}
		if(csub != null) {
			csub.close();
		}
		if(pub != null)
			pub.close();
	}
	public boolean initialEchonetInterface() throws SocketException, SubnetException, TooManyObjectsException {
		boolean isSuccessed = false;
		
		if(echonetService == null) {
			NetworkInterface nif = NetworkInterface.getByName("eno1");
			echonetCore = new Core(Inet4Subnet.startSubnet(nif));
			echonetCore.startService();
			echonetService = new Service(echonetCore);
			isSuccessed = true;
		}
		return isSuccessed;
	}
	
	public ArrayList<EchonetLiteDevice> getiHouseDevices() throws SocketException, SubnetException, TooManyObjectsException, InterruptedException, EchonetObjectException {
		ScanEchonetDevice deviceScanner = new ScanEchonetDevice(echonetService);
		return deviceScanner.scanEDevices();		
	}
	
	public ArrayList<MySensor> convertTemperatureSensorToRDF(ArrayList<EchonetLiteDevice> devList) {

		long startTime = System.currentTimeMillis();
		ArrayList<MySensor> sensorList = new ArrayList<MySensor>();
		System.out.println("      Parsing uAAL Objects to RDF objects"); 
		for(EchonetLiteDevice dev : devList) {
			if(dev.getDataObjList().size() >=1) {
				if(dev.getDataObjList().get(0).getClass().equals(eTemperatureSensor.class)) {
					String ip = dev.getProfileObj().getDeviceIP();
					String location = dev.getProfileObj().getInstallLocation();
					if(location == null) {
						location ="Unknown";
					}
					eTemperatureSensor tempSensor=(eTemperatureSensor) dev.getDataObjList().get(0);
					boolean opStatus = tempSensor.isOperationStatus();
					float temp = tempSensor.getTemperature();
					MySensor sensor = new MySensor("http://ontology.universaal.org/MySenSorOntology.owl#"+Constants.TEMPERATURE_EVENT+ip);
					sensor.changeProperty(MySensor.PROP_LOCALTION, location);
					sensor.changeProperty(MySensor.PROP_OPERATION_STATUS,opStatus);
					sensor.changeProperty(MySensor.PROP_VALUE,temp);					
					sensorList.add(sensor);	
				}
			}		
			
		}
		
		System.out.println("      Finish parsing uAAL Objects to RDF objects within "+
				(System.currentTimeMillis() - startTime) + " ms.");
		return sensorList;
	}
	

}
