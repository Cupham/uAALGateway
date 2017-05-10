package mainpackage;

import java.lang.reflect.Array;
import java.net.SocketException;
import java.util.ArrayList;

import echowand.logic.TooManyObjectsException;
import echowand.net.SubnetException;
import echowand.object.EchonetObjectException;
import services.ScanEchonetDevice;
import utils.SerializeUtils;
import echonet.objects.*;

/* ==============================================================================
 * Dumb example of one possible way to interact with the UniversAAL Skeleton
 * ==============================================================================
 * This solution allows for the interaction with the UniversAAL Skeleton from
 * the "inside" of UniversAAL and, therefore, without the need of a socket
 * communication with an external program.
 * ------------------------------------------------------------------------------
 *  - Create a new Thread in the Activator and give it, as parameter, an instance
 *    of a class which implements Runnable, e.g. an instance of the current class
 *    "Example".
 *  - You can then use the public void method run() of this class as the Main 
 *    function of your program.
 *  - If you want to PUBLISH a message you need to call the function:
 *    Activator.cpublisher.publishContextEvent(String datatype, String message)
 *    where "datatype" is a string indicating if the message is of type D1.1, D1.2 
 *    and so on (e.g. "D7.1") and "message" is the message to be published.
 *    The function returns a string which can be used to know if the datatype was
 *    valid or not (i.e. if the current CARESSES component is allowed to publish
 *    that type of message).
 */

public class Example implements Runnable {
	
	// : Example messages taken from the document Functional_Architecture_Description_v4.2
	
	private static String example_msg_d1_1 = "[... : \n(Move, ?R robot ?L1 location ?L2 location, (?P1(at?R)?L1), (?E1(at?R)?L2), (adjacent ?L1 ?L2) (movingTime ?R ?L1 ?L2 ?T), (duration ?THIS [?T 1440]) (meets ?P1 ?THIS) (meets ?THIS ?E1) (duration ?E1 [1 1440])) : \n...]";
	private static String example_msg_d1_2 = "[Greta : (1.0, 0.5, 0.0)]";
	private static String example_msg_d2_1 = "[...:\n(Move, ?R robot ?L1 location ?L2 location, (continuous speed) (discrete mode)):\n(Greet, ?R robot, ?P1 person, (discrete language) (continuous volume) (discrete mode)):\n...]";
	private static String example_msg_d2_2 = "[...:\n(User, Name, Greta):\n(User, hasRoom, Kitchen):\n(Kitchen, POS, I love your kitchen! It is small but very cozy!):\n(Kitchen, with, Coffee_Machine):\n(Kitchen, with, SEN_TV):\n(Coffee_Machine, QUE, Do you have a coffee machine in your kitchen?):\n(SEN_TV, QUE, Do you like to watch TV in the kitchen?):\n...]";
	private static String example_msg_d2_3 = "[Greta: (Swedish, 60, Soft)]";
	private static String example_msg_d2_4 = "[...:\n(0, 0, 1, 0, 0, 0):\n(0.8, 0.2, 0, 0, 0, 0):\n(0, 0.2, 0, 0.3, 0.2, 0.3):\n...]";
	private static String example_msg_d3_1 = "[...:\n(Greta, NOT(visit-by-friend AND house-dirty)):\n(Greta, NOT(telephone-call AND after 10pm)):\n(Yoko, NOT(visit-by-friend AND house-dirty)):\n(Yoko, NOT(visit-by-family-member AND house-dirty)):\n...]";
	private static String example_msg_d3_2 = "[... :\n(Greta, visit-by-friend AND house-dirty, 0.2):\n(Greta, visit-by-family-member AND house-dirty, 0.6):\n(Yoko, telephone-call AND after 10pm, 0.8):\n...]";
	private static String example_msg_d4_1 = "[Go_to: kitchen: now]";
	private static String example_msg_d4_2 = "[Greta: hasPositive: biscuits]";
	private static String example_msg_d5_1 = "[Remind_medication: blue_pill: between 12.00 and 12.30]";
	private static String example_msg_d5_2 = "[Yoko: hasTaken: pills]";
	private static String example_msg_d6_1 = "[Greta: Greta Ahlgren: 10/04/2017: 12:05: (2.3, 1.0, 0.0): (1.2, 0.0, 90.0): Kitchen.FridgeArea: Standing: -: Cooking: Eating: Excited]";
	private static String example_msg_d6_2 = "[Cup-22: Tea cup: (2.3, 1.0, 0.0): Kitchen: Cup: Red: (254, 10, 10)]";
	private static String example_msg_d6_3 = "[R1: Pepper: 10/04/2017, 12:05: (2.3, 1.0, 0.0): Kitchen: Greet: (Swedish, 60, Soft): Running]";
	private static String example_msg_d7_1 = "[Greet: (R1, Greta), (Swedish, 60, Soft) ]";
	private static String example_msg_d7_2 = "[Greet: (0.8, 0.2, 0, 0, 0, 0)]";
	private static String example_msg_d8_1 = "[nap : 12:00 : 13:00 : bedroom]";
	private static String example_msg_d8_2 = "NOT FILLED YET";
	private static String example_msg_d11_1 = "[...:\n(Go_to, ?L Location, ?T Time):\n(Remind, ?A Action ?N Nreminders, ?T Time):\n...]";
	private static String example_msg_d11_2 = "[...:\n(sadness , speech_frequency low, speech_volume low, heart_beat low):\n(interest, eye_gaze high, speech_frequency high, heart_beat normal):\n...]";
	private static String example_msg_d11_3 = "[...:\n(eating, location kitchen, speech_frequency low, co2 high):\n(praying, location living room, speech_frequency low, hand_position praying_position):\n...]";
	private static String example_msg_d11_4 = "NOT FILLED YET";
	private static String example_msg_Temperature = "Temperature = 23";
	
	private static String[] datatypes = {"D1.1", "D1.2", "D2.1",  "D2.2", "D2.3", "D2.4", "D3.1", "D3.2", "D4.1", "D4.2", "D5.1", "D5.2", "D6.1", "D6.2", 
		"D6.3", "D7.1", "D7.2", "D8.1", "D8.2", "D11.1", "D11.2", "D11.3", "D11.4", "TemperatureSensor"};
	
	private static String[] example_messages = {example_msg_d1_1, example_msg_d1_2, example_msg_d2_1, example_msg_d2_2, example_msg_d2_3, 
		example_msg_d2_4, example_msg_d3_1, example_msg_d3_2, example_msg_d4_1, example_msg_d4_2, example_msg_d5_1, example_msg_d5_2, 
		example_msg_d6_1, example_msg_d6_2, example_msg_d6_3, example_msg_d7_1, example_msg_d7_2, example_msg_d8_1, example_msg_d8_2, 
		example_msg_d11_1, example_msg_d11_2, example_msg_d11_3, example_msg_d11_4,example_msg_Temperature};

	
	// : This function can be used as the Main function of your program.
	private ArrayList<EchonetLiteDevice> echonetDeviceList = new ArrayList<EchonetLiteDevice>();
	ScanEchonetDevice deviceScanner = new ScanEchonetDevice(Activator.echonetService);
	ArrayList<eTemperatureSensor> sensorList = new ArrayList<eTemperatureSensor>();
	public void run() {
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
						String publisher_response = Activator.cpublisher.publishContextEvent("TemperatureSensor", msg);
						System.out.println("INFO: " + publisher_response + "\n");
						System.out.println(Activator.i_TemperatureSensor.getIP());
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
		}
		System.out.println(Activator.i_TemperatureSensor.getIP());
		
		

		//for (int i = 0; i < example_messages.length; i++){
		//	
		//	String publisher_response = Activator.cpublisher.publishContextEvent(datatypes[i], example_messages[i]);
		//	System.out.println("INFO: " + publisher_response + "\n");
		//}
		
	}



}
