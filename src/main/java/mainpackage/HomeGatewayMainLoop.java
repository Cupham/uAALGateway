package mainpackage;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;

import echonet.objects.EchonetLiteDevice;
import echonet.objects.eAirConditioner;
import echonet.objects.eDataObject;
import echonet.objects.eTemperatureSensor;
import echowand.net.Inet4Subnet;
import echowand.service.Core;
import echowand.service.Service;
import homegateway.services.EchonetDeviceScanner;
import ontologies.HomeAirConditioner;
import ontologies.TemperatureSensor;
import utils.InputValidator;
import utils.OntologyHelper;

public class HomeGatewayMainLoop {
	
	private static int INTERVAL = 1000;
	private String hgwIP;
	private String nifCard;
	private Service echonetService;
	private Thread resourceScannerThread;
	private EchonetDeviceScanner deviceScanner;
	
	public static ArrayList<TemperatureSensor> temperatureSensorOntologies;
	public static ArrayList<HomeAirConditioner> homeAirconditionerOntologies;
	public static ArrayList<EchonetLiteDevice> echonetDevices;
	
	
	
	public HomeGatewayMainLoop(String networkInterfaceCard) {
		this.nifCard = networkInterfaceCard;
		this.resourceScannerThread = null;
		this.deviceScanner = null;
	}
	
	public boolean intial() {
		boolean rs = false;
		// init EchonetInterface
		if(initEchonetInterface()) {
			System.out.println("Initialize Echonet Interface Successfully!");
			rs = true;
		} else {
			System.out.println("There was an error during nitializing the Echonet Interface");
		}
		
		// Init a thread to scan and get all Home Network Device Resources
		
	//	initHNScanner();
		
		return rs;
	}
	
	public void run() {
		
	}
	
	
	// Initialize Home Network Scanner
	   // Get all echonet devices
	   // Translate EOJ, EPC into readable data
	   // Create corresponding ontologies
	/*
	private void initHNScanner() {
		this.resourceScannerThread = new Thread() {
			public void run() {
				while(true) {
					try {
						sleep(INTERVAL*5);
						System.out.println("Scanning the home network for echonet device resources...");
						EchonetDeviceScanner deviceScanner = new EchonetDeviceScanner(echonetService);
						ArrayList<eTemperatureSensor> eTemperatureSensors = new ArrayList<eTemperatureSensor>();
						ArrayList<eAirConditioner> eHomeAirConditioners =  new ArrayList<eAirConditioner>();
						ArrayList<EchonetLiteDevice> eDevices = new ArrayList<EchonetLiteDevice>();
						
						// Get and categorize all echonet devices in the home network
						eDevices = deviceScanner.scanEDevices();
						
						if(eDevices.size()  == 0) {
							System.out.println("ERR: There is no device in the home network");
						} else {
							for(EchonetLiteDevice dev : eDevices) {
								// Categorize Echonet Device into specific types
								for(eDataObject dataObj: dev.getDataObjList()) {
									   //Temperature Sensor
									if(dataObj.getClass().equals(eTemperatureSensor.class)) {
										eTemperatureSensor temperatureSensor = (eTemperatureSensor) dataObj;
									//	temperatureSensor.setDeviceIP(dev.getProfileObj().getDeviceIP());
										eTemperatureSensors.add(temperatureSensor);
										// Home Aircondtioner
									} else if(dataObj.getClass().equals(eAirConditioner.class)) {
										eAirConditioner aircondtioner = (eAirConditioner) dataObj;
									//	aircondtioner.setDeviceIP(dev.getProfileObj().getDeviceIP());
										eHomeAirConditioners.add(aircondtioner);
										// other devices
									} else {
										// TODO:
									}
								}
								
							}
						}
						
						// Create Ontology for uAAL Middleware from categorized objects.
						
						//TemperatureSensor Ontology
						if(eTemperatureSensors.size() == 0) {
							System.out.println("ERR: There is no Echonet Temperature Sensor in the home network");
						} else {
							for(eTemperatureSensor temperatureSensor : eTemperatureSensors) {
							//	String uriSuffix =  temperatureSensor.getDeviceIP() + "_"+temperatureSensor.getInstanceCode(); 
							//	System.out.println("Translating uAAL objects(TemperatureSensor "+temperatureSensor.getInstanceCode()+") with IP "+temperatureSensor.getDeviceIP() +" to uAAL objects...");
								Activator.i_TemperatureSensor = new TemperatureSensor(CaressesOntology.NAMESPACE +"I_TemperatureSensor"+uriSuffix);
								OntologyHelper.initTemperatureSensorOntology(temperatureSensor, Activator.i_TemperatureSensor);
								Activator.temperatureSensorOntologies.add(Activator.i_TemperatureSensor);
							}
						}
						
						//Home Airconditioner Ontology
						if(eHomeAirConditioners.size() == 0) {
							System.out.println("ERR: There is no Airconditioner in the home network");
						} else {
							for( eAirConditioner airconditioner : eHomeAirConditioners) {
							//	String uriSuffix =  airconditioner.getDeviceIP() + "_"+airconditioner.getInstanceCode(); 
								System.out.println("Translating uAAL objects(Airconditioner "+airconditioner.getInstanceCode()+") with IP "+airconditioner.getDeviceIP() +" to uAAL objects...");
								Activator.i_HomeAirConditoner = new HomeAirConditioner(CaressesOntology.NAMESPACE +"I_Airconditioner"+uriSuffix);
								OntologyHelper.initHomeAirconditionerOntology(airconditioner, Activator.i_HomeAirConditoner);
								Activator.homeAirconditionerOntologies.add(Activator.i_HomeAirConditoner);
								
							}
						}
						
					} catch (Exception e) {
						System.out.println(e.toString());
					}
					
				}
			}
			
		};
	}
	*/
	private boolean initEchonetInterface () {
		boolean rs = true;
		try {
			if(InputValidator.isNotNull(this.nifCard)) {
				rs = false;
			} else {
				// Initialize Home Gateway Network Interface
				NetworkInterface nif = NetworkInterface.getByName(nifCard);
				this.hgwIP = getHGW_IP(nif);
				if(!InputValidator.isNotNull(this.hgwIP)) {
					rs = false;
				}
				if(this.echonetService == null) {
				// Start Service 		
					Core core = new Core(Inet4Subnet.startSubnet(nif));
					if(core.startService()){
						this.echonetService = new Service(core);
						rs = true;				
					} else {
						rs = false;
					}
				}
			}
		} catch (Exception e) {
			rs = false;
		}
		return rs;
		
	}
	private static String getHGW_IP(NetworkInterface nif) {
		String ip = nif.getInetAddresses().nextElement().getHostAddress();
		Enumeration<InetAddress> inetAddress = nif.getInetAddresses();
		InetAddress currentAddress;

		currentAddress = inetAddress.nextElement();
		while (inetAddress.hasMoreElements()) {
			currentAddress = inetAddress.nextElement();
			if (currentAddress instanceof Inet4Address && !currentAddress.isLoopbackAddress()) {
				ip = currentAddress.getHostAddress();
				break;
			}
		}
		return ip.trim();
}

}
