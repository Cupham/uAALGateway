package context;


/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_6#Publishing_context_events */
import java.awt.List;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;

import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.context.ContextEventPattern;
import org.universAAL.middleware.context.ContextPublisher;
import org.universAAL.middleware.context.DefaultContextPublisher;
import org.universAAL.middleware.context.owl.ContextProvider;
import org.universAAL.middleware.context.owl.ContextProviderType;

import echonet.objects.EchonetLiteDevice;
import echonet.objects.eTemperatureSensor;
import echowand.logic.TooManyObjectsException;
import echowand.net.Inet4Subnet;
import echowand.net.SubnetException;
import echowand.object.EchonetObjectException;
import echowand.service.Core;
import echowand.service.Service;

import sensors.MySensor;
import services.EchonetConnect;
import services.ScanEchonetDevice;

public class SensorPublisher {
	
	private Core core;
	private ContextPublisher mySensorContextPublisher;
	private static Service service = null;

	public SensorPublisher(ModuleContext context) {
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
			System.out.println("Init a publisher with ID: " + mySensorContextPublisher.getMyID());
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void publishContextEvent() throws SocketException, SubnetException, TooManyObjectsException, InterruptedException, EchonetObjectException {
		// Get sensor from echonet network
		ArrayList<MySensor> sensorList = new ArrayList<MySensor>();
		int id = 1;
		ScanEchonetDevice deviceScanner = new ScanEchonetDevice(service);
		ArrayList<EchonetLiteDevice> devList = deviceScanner.scanEDevices();
		for(EchonetLiteDevice dev : devList) {
			if(dev.getDataObjList().size() >=1) {
				if(dev.getDataObjList().get(0).getClass().equals(eTemperatureSensor.class)) {
					String ip = dev.getProfileObj().getDeviceIP();
					String location = dev.getProfileObj().getInstallLocation();
					eTemperatureSensor tempSensor=(eTemperatureSensor) dev.getDataObjList().get(0);
					boolean opStatus = tempSensor.isOperationStatus();
					float temp = tempSensor.getTemperature();
					MySensor sensor = new MySensor("http://ontology.universaal.org/MySenSorOntology.owl#mySensor"+id);
					sensor.setMySensorLocation(location);
					sensor.setMySensorOperationStatus(opStatus);
					sensor.setMySensorValue(temp);					
					sensorList.add(sensor);	
					id++;
				}
			}
			
		}
		
		/*
		MySensor sensor = new MySensor("http://ontology.universaal.org/MySenSorOntology.owl#mysensor1");
		//float temp = EchonetConnect.getTemp();
		float temp = 5;
		sensor.setMySensorValue(temp);
		sensor.setMySensorLocation("Living Room");
		sensor.setMySensorOperationStatus(true);
		ContextEvent mySensorContextEvent = new ContextEvent(sensor.getURI());
		mySensorContextEvent.setProperty(MySensor.PROP_LOCALTION, sensor);
		
		mySensorContextPublisher.publish(mySensorContextEvent);
		System.out.println("Publisher ID: " + mySensorContextPublisher.getMyID() 
				+" Published  an event with URI: "+mySensorContextEvent.getURI());
		*/

		for (int i=0; i< sensorList.size();i++) {
			ContextEvent mySensorContextEvent = new ContextEvent(sensorList.get(i),MySensor.PROP_VALUE);
			mySensorContextPublisher.publish(mySensorContextEvent);
			//ContextEvent mySensorContextEvent = new ContextEvent(sensorList.get(i).getURI());
			//mySensorContextEvent.setProperty(MySensor.PROP_LOCALTION,sensorList.get(i).getMySensorLocation());
			//mySensorContextEvent.setProperty(MySensor.PROP_OPERATION_STATUS,sensorList.get(i).getMySensorOperationStatus());
			//mySensorContextEvent.setProperty(MySensor.PROP_VALUE,sensorList.get(i).getMySensorValue());
			System.out.println("Publisher ID: " + mySensorContextPublisher.getMyID() 
					+" Published  an event with URI: "+mySensorContextEvent.toString());
		}

	}

	
	public void close() {
		System.out.println("Closing a publisher with ID: " + mySensorContextPublisher.getMyID());
		mySensorContextPublisher.close();
	}

	public void communicationChannelBroken() {
		// TODO Auto-generated method stub
		System.out.println("There is a problem with connection!!");
	}



}
