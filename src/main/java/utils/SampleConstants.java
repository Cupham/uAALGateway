package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import echowand.common.EPC;

public class SampleConstants {
	
	private SampleConstants(){};
	
	// Installation Location enum
	public static String LIVING_ROOM = "Living room";
	public static String DINING_ROOM = "Dining room";
	public static String KITCHEN = "Kitchen";
	public static String BATHROOM = "Bathroom";
	public static String LAVATORY = "Lavatory";
	public static String WASH_CHANGING_ROOM = "Washroom/changing room";
	public static String PASSAGEWAY = "Passageway";	
	public static String ROOM = "Room";
	public static String STAIR_WAY = "Stairway";
	public static String FRONT_DOOR = "Front door";
	public static String STORE_ROOM = "Storeroom";	
	public static String GARDEN_PERIMETER = "Garden/perimeter";
	public static String GARAGE = "Garage";
	public static String VERANDA_BALCONY = "Veranda/balcony";
	public static String OTHER = "Veranda/balcony";
	public static String LOCATION_NOT_SPECIFIC = "Installation location not specified";
	public static String LOCATION_UNDEFINED = "Installation location undefined";
	public static String RESERVED = "Reserved";
	public static String FREE_DEFINITION = "Free Definition";
	
	//Communication technologies
	public static String PLC_A_D = "Power line Communication Protocol a and d systems";
	public static String LOW_POWER_RADIO_COMMUNICATION_PROTOCOL = "Low-Power Radio Communication Protocol";
	public static String EXTENDED_HBS = "Extended HBS";
	public static String IrDA = "IrDA";
	public static String LONTALK = "LonTalk";
	public static String BLUETOOTH = "Bluetooth";
	public static String ETHERNET = "Ethernet";	
	public static String IEEE802_11_11_B = "IEEE802.11/11b";
	public static String PLC_C = "Power line Communication Protocol c systems";
	public static String IPV6_ETHERNET = "IPv6/Ethernet";
	public static String IPV6_6LOWPAN = "IPv6/6LoWPAN";	
	public static String UNDEFINED = "Undefined";
	
	//fault description
	public static String SPECIAL_FAULT = "A fault has occurred but the recovery method or fault location cannot be determined.";
	public static String FAULT_UNDEFINED = "The fault undefined.";
	public static String NO_FAULT = "No fault";
	public static String RECOVERABLE_TYPE_1 = "Faults that can be recovered from by turning "
			+ "off the power switch and turning it on again or withdrawing and re-inserting the power plug.";
	public static String RECOVERABLE_TYPE_2 = "Faults that can be recovered from by pressing the reset button.";
	public static String RECOVERABLE_TYPE_3 = "Faults that can be recovered from by changing the way the device is mounted or opening/closing a lid or door.";
	public static String RECOVERABLE_TYPE_4 = "Faults that can be recovered from by supplying fuel, water, air, etc.";
	public static String RECOVERABLE_TYPE_5 = "Faults that can be recovered from by cleaning the device (filter etc.)";
	public static String RECOVERABLE_TYPE_6 = "Faults that can be recovered from by changing the battery or cell.";
	public static String RECOVERABLE_TYPE_7 = "User-definable domain";
	public static String REPAIR_REQUIRED_TYPE_1 = "Abnormal event or the tripping of a safety device.";
	public static String REPAIR_REQUIRED_TYPE_2 = "Fault in a switch.";
	public static String REPAIR_REQUIRED_TYPE_3 = "Fault in the sensor system.";
	public static String REPAIR_REQUIRED_TYPE_4 = "Fault in a component such as an actuator.";
	public static String REPAIR_REQUIRED_TYPE_5 = "Fault in a control circuit board.";
	public static String REPAIR_REQUIRED_TYPE_6 = "User-definable domain";
	public static String UNKNOWN_FAULT = "The fault undefined";
	
	
	public static int REFRESH_INTERVAL = 5000;
	public static int DELAY_INTERVAL  = 0;
	public static ArrayList<String> curtainDomain = new ArrayList<String>(
			Arrays.asList("192.168.2.157","192.168.2.158","192.168.2.183","192.168.2.184"));
	public static ArrayList<String> windowDomain = new ArrayList<String>(
			Arrays.asList("192.168.2.159","192.168.2.160","192.168.2.161","192.168.2.162",
						  "192.168.2.163","192.168.2.164","192.168.2.165","192.168.2.166",
						  "192.168.2.167","192.168.2.168"));
	public static ArrayList<String> entranceLock = new ArrayList<String>(
			Arrays.asList("192.168.2.148"));
	public static ArrayList<String> hotWater = new ArrayList<String>(
			Arrays.asList("192.168.2.149","192.168.2.150"));
	public static ArrayList<String> awning = new ArrayList<String>(
			Arrays.asList("192.168.2.169"));
	public static ArrayList<String> tv = new ArrayList<String>(
			Arrays.asList("192.168.2.134"));
	public static ArrayList<String> radio = new ArrayList<String>(
			Arrays.asList("192.168.2.136"));
	
	public static ArrayList<EPC> defaultEPCs = new ArrayList<EPC>(
		Arrays.asList(EPC.x80,EPC.x81,EPC.x82,EPC.x83,EPC.x84,EPC.x85,EPC.x86,EPC.x87,
					  EPC.x88,EPC.x89,EPC.x8A,EPC.x8B,EPC.x8C,EPC.x8D,EPC.x8E,EPC.x8F,
					  EPC.x93,EPC.x97,EPC.x98,EPC.x99,EPC.x9A));

	public static int getRefreshInterval() {
		return REFRESH_INTERVAL + new Random().nextInt(1000);
	}
	public static int getDelayInterval() {
		return DELAY_INTERVAL + + new Random().nextInt(2000);
	}
}
