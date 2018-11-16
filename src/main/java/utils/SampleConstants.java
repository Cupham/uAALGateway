package utils;

import java.util.ArrayList;
import java.util.Arrays;

public class SampleConstants {
	
	private SampleConstants(){};
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

}
