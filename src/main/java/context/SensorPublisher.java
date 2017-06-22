package context;


/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_6#Publishing_context_events */
import java.awt.List;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;

import ont.MySensor;

import org.apache.log4j.Logger;
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.context.ContextEventPattern;
import org.universAAL.middleware.context.ContextPublisher;
import org.universAAL.middleware.context.DefaultContextPublisher;
import org.universAAL.middleware.context.owl.ContextProvider;
import org.universAAL.middleware.context.owl.ContextProviderType;
import org.universAAL.middleware.owl.ManagedIndividual;
import org.universAAL.ontology.device.MotionSensor;
import org.universAAL.ontology.device.TemperatureSensor;
import org.universAAL.ontology.location.Location;
import org.universAAL.ontology.phThing.Device;
import org.universAAL.ontology.profile.HWSubProfile;
import org.universAAL.ontology.profile.Profile;
import org.universAAL.ontology.profile.User;

import echonet.objects.EchonetLiteDevice;
import echonet.objects.eTemperatureSensor;
import echowand.logic.TooManyObjectsException;
import echowand.net.Inet4Subnet;
import echowand.net.SubnetException;
import echowand.object.EchonetObjectException;
import echowand.service.Core;
import echowand.service.Service;

import services.EchonetConnect;
import services.EchonetDeviceScanner;

public class SensorPublisher {
	final static Logger logger = Logger.getLogger(SensorPublisher.class);
	StringBuilder logs = new StringBuilder();
	
	private Core core;
	private ContextPublisher mySensorContextPublisher;
	private static Service service = null;

	public SensorPublisher(ModuleContext context) {
		/**
		 * Calculate executation time.
		 */
		long startTime = System.currentTimeMillis();
		StringBuilder logs = new StringBuilder();
		try {
			
			if(service == null) {
				NetworkInterface nif = NetworkInterface.getByName("eno1");
				core = new Core(Inet4Subnet.startSubnet(nif));
				core.startService();
				service = new Service(core);
			}
			ContextProvider myContextProvider = new ContextProvider(
				"http://ontology.universaal.org/MySenSorOntology.owl#MySensorContextProvider");
			myContextProvider.setType(ContextProviderType.gauge);
			myContextProvider.setProvidedEvents(new ContextEventPattern[] {
				new ContextEventPattern()
			});
			mySensorContextPublisher = new DefaultContextPublisher(context, myContextProvider);
			// Record log
			logs.append("Init publisher with ID: ");
			logs.append(mySensorContextPublisher.getMyID());
			logs.append(" within: ");
			logs.append(System.currentTimeMillis() - startTime);
			logs.append(" ms.");
			logger.info(logs);
			System.out.println(logs.toString());
			
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
	}
	
	public void publishContextEvent() throws SocketException, SubnetException, TooManyObjectsException, InterruptedException, EchonetObjectException {
		/**
		 * Calculate executation time.
		 */		
		
		// Get sensor from echonet network
		ArrayList<MySensor> sensorList = new ArrayList<MySensor>();
		EchonetDeviceScanner deviceScanner = new EchonetDeviceScanner(service);
		ArrayList<EchonetLiteDevice> devList = deviceScanner.scanEDevices();
		long startTime = System.currentTimeMillis();
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
					MySensor sensor = new MySensor("http://ontology.universaal.org/MySenSorOntology.owl#"+ip);
					sensor.changeProperty(MySensor.PROP_LOCALTION, location);
					sensor.changeProperty(MySensor.PROP_OPERATION_STATUS,opStatus);
					sensor.changeProperty(MySensor.PROP_VALUE,temp);					
					sensorList.add(sensor);	
				}
			}		
			
		}
		
		System.out.println("      Finish parsing uAAL Objects to RDF objects within "+
				(System.currentTimeMillis() - startTime) + " ms.");
		
		System.out.println("Publishing ontologies to Context Bus ..."); 
		long startTime1 = System.currentTimeMillis();
		for (int i=0; i< sensorList.size();i++) {
			MySensor sensor = sensorList.get(i);		
			ContextEvent mySensorContextEvent = new ContextEvent(sensor, MySensor.PROP_VALUE);
			//ContextEvent mySensorContextEvent1 = new ContextEvent(sensor, MySensor.PROP_LOCALTION);
			//ContextEvent mySensorContextEvent2 = new ContextEvent(sensor, MySensor.PROP_VALUE);
			mySensorContextPublisher.publish(mySensorContextEvent);
			//mySensorContextPublisher.publish(mySensorContextEvent1);
			//mySensorContextPublisher.publish(mySensorContextEvent2);
			//mySensorContextEvent.setProperty(MySensor.PROP_LOCALTION,sensorList.get(i).getMySensorLocation());
			//mySensorContextEvent.setProperty(MySensor.PROP_OPERATION_STATUS,sensorList.get(i).getMySensorOperationStatus());
			//mySensorContextEvent.setProperty(MySensor.PROP_VALUE,sensorList.get(i).getMySensorValue());
			System.out.println("   *********************");
			System.out.println("   EVENT: "+ (i+1));
			System.out.println("      SUBJECT: "+mySensorContextEvent.getSubjectURI()+"\n" + 
						"      SUBJECT TYPE: " + mySensorContextEvent.getSubjectTypeURI() + "\n" +
						"      RDFOBJECT: " + mySensorContextEvent.getRDFObject());
			System.out.println("      PREDICATE:" + mySensorContextEvent.getRDFPredicate());
			System.out.println("   *********************");
		}
		System.out.println("Finish publishing ontologies to Context Bus within "
				+ (System.currentTimeMillis() - startTime1) + " ms."); 
		// Record log
		//logs.append("within: " + (System.currentTimeMillis() - startTime) + " ms.");
		//logger.info(logs);

	}

	
	public void close() {
		long startTime =System.currentTimeMillis();
		mySensorContextPublisher.close();
		logger.info("Publisher ID: " + mySensorContextPublisher.getMyID() + 
				"has been closed within: " + (System.currentTimeMillis()-startTime) + " ms.");
	}

	public void communicationChannelBroken() {
		// TODO Auto-generated method stub
		logger.error("Communication channel was broken");
		System.out.println("There is a problem with connection!!");
	}



}
