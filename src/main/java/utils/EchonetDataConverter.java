/**
 * Convert DataObject
 */
package utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.universAAL.ontology.echonetontology.values.EchonetDeviceGroupCodeValue;

import echowand.common.EPC;
import echowand.object.EchonetObjectException;
import echowand.object.ObjectData;
import echowand.service.result.ResultData;

/**
 * @author Cu Pham
 *
 */
public class EchonetDataConverter {

	private final static String notFaultDescription = "The fault description is not found!";
	public enum FaultType {
		Recoverable_Faults, Require_Repair_Faults, Undefined
	};

	/**
	 * convert data to integer value. Apply for value have max 4 bytes: status,
	 * temperature, ...
	 * 
	 * @param data
	 * @return byte value
	 */
	public static byte dataToByte(ObjectData data) {
		return (new BigInteger(data.toBytes()).byteValue());
	}

	/**
	 * convert data to integer value. Apply for value have max 4 bytes: status,
	 * temperature, ...
	 * 
	 * @param data
	 * @return integer value
	 */
	public static int dataToInteger(ObjectData data) {
		return (new BigInteger(data.toBytes()).intValue());
	}
	public static int dataToInteger(ResultData data) {
		return (new BigInteger(data.toBytes()).intValue());
	}
	
	public static float dataToFloat(ObjectData data) {
		return (new BigInteger(data.toBytes()).floatValue());
	}
	public static float dataToFloat(ResultData data) {
		return (new BigInteger(data.toBytes()).floatValue());
	}
	/**
	 * convert data to short value. Apply for value have max 2 bytes.
	 * 
	 * @param data
	 * @return short value
	 */
	public static short dataToShort(ObjectData data) {
		return (new BigInteger(data.toBytes()).shortValue());
	}
	public static short dataToShort(ResultData data) {
		return (new BigInteger(data.toBytes()).shortValue());
	}

	/**
	 * convert data to long value. Apply for value have max 8 bytes.
	 * 
	 * @param data
	 * @return long value
	 */
	public static long dataToLong(ObjectData data) {
		return (new BigInteger(data.toBytes()).longValue());
	}
	public static long dataToLong(ResultData data) {
		return (new BigInteger(data.toBytes()).longValue());
	}

	/**
	 * convert data to integer value. Apply for value have max 4 bytes: status,
	 * temperature, ...
	 * 
	 * @param input
	 * @return integer value
	 */
	public static int dataToInteger(byte[] input) {
		return (new BigInteger(input).intValue());
	}
	
	public static float datatoFloat(byte[] input) {
		return (new BigInteger(input).floatValue());
	}

	/**
	 * convert data to string value.
	 * 
	 * @param input
	 * @return string value
	 */
	public static String dataToString(ObjectData data) {
		return (new String(data.toBytes()));
	}
	public static String dataToString(ResultData data) {
		return (new String(data.toBytes()));
	}

	/**
	 * Convert to standard version
	 * 
	 * @param data
	 * @return string as standard version
	 */
	public static String dataToVersion(ObjectData data) {
		int dataSize = data.size();

		if (dataSize != 4) {
			return "Invalid";
		}

		int major = (0x000000ff) & data.get(0);
		int minor = (0x000000ff) & data.get(1);
		int tail = (0x000000ff) & data.get(3);
		byte b3 = data.get(2);

		String supported = "" + (char) b3;

		String versionString = String.format("%d.%d (%s) %d", major, minor, supported, tail);

		return versionString;
	}
	public static String dataToVersion(ResultData data) {
		int dataSize = data.size();

		if (dataSize != 4) {
			return "Invalid";
		}

		int major = (0x000000ff) & data.get(0);
		int minor = (0x000000ff) & data.get(1);
		int tail = (0x000000ff) & data.get(3);
		byte b3 = data.get(2);

		String supported = "" + (char) b3;

		String versionString = String.format("%d.%d (%s) %d", major, minor, supported, tail);

		return versionString;
	}

	/**
	 * Convert to fault code
	 * 
	 * @param data
	 * @return string as fault code
	 */
	public static String dataToFaultCode(ObjectData data) {
		int dataSize = data.size();

		if (dataSize <= 5) {
			return "Invalid";
		}

		int size = (0x000000ff) & data.get(0);
		if (size < 1) {
			return "Invalid";
		}
		byte manuCode[] = new byte[3];
		manuCode[0] = data.get(1);
		manuCode[1] = data.get(2);
		manuCode[2] = data.get(3);
		String manCode = new String(manuCode);
		size = (dataSize < size) ? dataSize : size;
		byte stringFault[] = new byte[size];
		int j = 0;

		for (int i = 4; (i < dataSize && j < size); i++) {
			stringFault[j++] = data.get(i);
		}
		String fault = new String(stringFault);
		String faultCode = String.format("%s - %s", manCode, fault);
		return faultCode;
	}
	public static String dataToFaultCode(ResultData data) {
		int dataSize = data.size();

		if (dataSize <= 5) {
			return "Invalid";
		}

		int size = (0x000000ff) & data.get(0);
		if (size < 1) {
			return "Invalid";
		}
		byte manuCode[] = new byte[3];
		manuCode[0] = data.get(1);
		manuCode[1] = data.get(2);
		manuCode[2] = data.get(3);
		String manCode = new String(manuCode);
		size = (dataSize < size) ? dataSize : size;
		byte stringFault[] = new byte[size];
		int j = 0;

		for (int i = 4; (i < dataSize && j < size); i++) {
			stringFault[j++] = data.get(i);
		}
		String fault = new String(stringFault);
		String faultCode = String.format("%s - %s", manCode, fault);
		return faultCode;
	}
	
	/**
	 * Convert to data AirConditioner OperationMode
	 * 
	 * @param data
	 * @return string as fault code
	 */
	public static String dataToAirConditionerOperationMode(ObjectData data) {
		int dataSize = data.size();
		String rs = "";
		if (dataSize != 1) {
			rs =  "Invalid";
		}

		switch (data.get(0)) {
			case (byte)0x41:
				rs = "Automatic";
				break;
			case (byte) 0x42:
				rs = "Cooling";
				break;
			case (byte) 0x43:
				rs = "Heating";
				break;
			case (byte) 0x44:
				rs = "Dehumidification";
				break;
			case (byte) 0x45:
				rs = "Air circulator";
			break;
			case (byte) 0x40:
				rs = "Other";
			break;
			default:
				rs = "Invalid";
				break;
		}
		
		return rs;
	}
	public static String dataToAirConditionerOperationMode(ResultData data) {
		int dataSize = data.size();
		String rs = "";
		if (dataSize != 1) {
			rs =  "Invalid";
		}

		switch (data.get(0)) {
			case (byte)0x41:
				rs = "Automatic";
				break;
			case (byte) 0x42:
				rs = "Cooling";
				break;
			case (byte) 0x43:
				rs = "Heating";
				break;
			case (byte) 0x44:
				rs = "Dehumidification";
				break;
			case (byte) 0x45:
				rs = "Air circulator";
			break;
			case (byte) 0x40:
				rs = "Other";
			break;
			default:
				rs = "Invalid";
				break;
		}
		
		return rs;
	}
	
	public static ObjectData dataFromAirConditionerOperationMode(String mode) {
		ObjectData rs = null;

		switch (mode.toLowerCase().trim()) {
			case "automatic":
				rs = new ObjectData((byte)0x41);
				break;
			case "cooling":
				rs = new ObjectData((byte)0x42);
				break;
			case "heating":
				rs = new ObjectData((byte)0x43);
				break;
			case "dehumidification":
				rs = new ObjectData((byte)0x44);
				break;
			case "air circulator":
				rs = new ObjectData((byte)0x45);
			break;
			default:
				rs = new ObjectData((byte)0x40);
				break;
		}
		
		return rs;
	}
	
	public static String dataToAirConditionerFlowRate(ObjectData data) {
		int dataSize = data.size();
		String rs = "";
		if (dataSize != 1) {
			rs =  "Invalid";
		}

		switch (data.get(0)) {
			case (byte)0x41:
				rs = "Automatic";
				break;
			case (byte) 0x31:
				rs = "Level 1";
				break;
			case (byte) 0x32:
				rs = "Level 2";
				break;
			case (byte) 0x33:
				rs = "Level 3";
				break;
			case (byte) 0x34:
				rs = "Level 4";
			break;
			case (byte) 0x35:
				rs = "Level 5";
			break;
			case (byte) 0x36:
				rs = "Level 6";
				break;
			case (byte) 0x37:
				rs = "Level 7";
			break;
			case (byte) 0x38:
				rs = "Level 8";
			break;
			default:
				rs = "Automatic";
				break;
		}
		
		return rs;
	}
	public static String dataToAirConditionerFlowRate(ResultData data) {
		int dataSize = data.size();
		String rs = "";
		if (dataSize != 1) {
			rs =  "Invalid";
		}

		switch (data.get(0)) {
			case (byte)0x41:
				rs = "Automatic";
				break;
			case (byte) 0x31:
				rs = "Level 1";
				break;
			case (byte) 0x32:
				rs = "Level 2";
				break;
			case (byte) 0x33:
				rs = "Level 3";
				break;
			case (byte) 0x34:
				rs = "Level 4";
			break;
			case (byte) 0x35:
				rs = "Level 5";
			break;
			case (byte) 0x36:
				rs = "Level 6";
				break;
			case (byte) 0x37:
				rs = "Level 7";
			break;
			case (byte) 0x38:
				rs = "Level 8";
			break;
			default:
				rs = "Automatic";
				break;
		}
		
		return rs;
	}
	
	public static ObjectData dataFromAirConditionerFlowRate(String airFlowRate) {
		ObjectData data = null;
		switch (airFlowRate.trim().toLowerCase()) {
		case "automatic":
			data = new ObjectData((byte)0x41);
			break;
		case "level 1":
			data = new ObjectData((byte)0x31);
			break;
		case "level 2":
			data = new ObjectData((byte)0x32);
			break;
		case "level 3":
			data = new ObjectData((byte)0x33);
			break;
		case "level 4":
			data = new ObjectData((byte)0x34);
			break;
		case "level 5":		
			data = new ObjectData((byte)0x35);
			break;
		case "level 6":
			data = new ObjectData((byte)0x36);
			break;
		case "level 7":
			data = new ObjectData((byte)0x37);
			break;
		case "level 8":
			data = new ObjectData((byte)0x38);
			break;
		default:
			data = new ObjectData((byte)0x41);
			break;
		}
		return data;
	}

	/**
	 * Get bit in byte by index
	 * 
	 * @param b
	 * @param bit
	 * @return true - 1, false - 0
	 */
	private static Boolean isBitSet(byte b, int bit) {
		return (b & (1 << bit)) != 0;
	}

	/**
	 * Get bit array as hashmap from byte with 3 bit lowest is 0
	 * 
	 * @param data
	 * @return hashmap bit and index
	 */
	private static HashMap<Integer, Integer> getHashmapDefaultFromByte(byte data) {
		HashMap<Integer, Integer> firstMap = new HashMap<Integer, Integer>();
		firstMap.put(0, 0);
		firstMap.put(1, 0);
		firstMap.put(2, 0);
		for (int i = 3; i < 8; i++) {
			firstMap.put(i, (isBitSet(data, i)) ? 1 : 0);
		}
		return firstMap;
	}

	/**
	 * Get bit array as hashmap from list bit: true - 1, false - 0
	 * 
	 * @param data
	 * @return hashmap bit and index
	 */
	private static HashMap<Integer, Integer> getHashmap(int bit7, int bit6, int bit5, int bit4, int bit3, int bit2,
			int bit1, int bit0) {
		HashMap<Integer, Integer> firstMap = new HashMap<Integer, Integer>();
		firstMap.put(0, (bit0 == 0) ? 0 : 1);
		firstMap.put(1, (bit1 == 0) ? 0 : 1);
		firstMap.put(2, (bit2 == 0) ? 0 : 1);
		firstMap.put(3, (bit3 == 0) ? 0 : 1);
		firstMap.put(4, (bit4 == 0) ? 0 : 1);
		firstMap.put(5, (bit5 == 0) ? 0 : 1);
		firstMap.put(6, (bit6 == 0) ? 0 : 1);
		firstMap.put(7, (bit7 == 0) ? 0 : 1);

		return firstMap;
	}

	private static boolean equalsHashmap(HashMap<Integer, Integer> h1, HashMap<Integer, Integer> h2) {
		try {

			for (int i = 0; i < 8; i++) {
				if (h1.get(i) != h2.get(i)) {
					return false;
				}
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * Convert install location
	 * 
	 * @param odata
	 * @return string as install location
	 */
	public static String dataToInstallLocation(ObjectData odata) {
		byte data[] = odata.toBytes();
		byte firstByte = data[0];
		if (firstByte == (byte) 0xFF) {
			return "Installation location not specified";
		} else if (firstByte == (byte) 0x00) {
			return "Installation location undefined";
		} else if (firstByte == (byte) 0x01) { // 17 bytes
			String rs = "";
			for (int i = 1; i < data.length; i++) {
				rs += data[i];
			}
			return rs;
		} else if (!isBitSet(firstByte, 3) && !isBitSet(firstByte, 4) && !isBitSet(firstByte, 5)
				&& !isBitSet(firstByte, 6) && !isBitSet(firstByte, 7)) {
			return "Reserved for future use.";
		} else {
			HashMap<Integer, Integer> firstMap = getHashmapDefaultFromByte(firstByte);
			if (equalsHashmap(firstMap, getHashmap(0, 1, 1, 1, 1, 0, 0, 0))) {
				return "Others";
			} else if (equalsHashmap(firstMap, getHashmap(0, 1, 1, 1, 0, 0, 0, 0))) {
				return "Veranda/balcony";
			} else if (equalsHashmap(firstMap, getHashmap(0, 1, 1, 0, 1, 0, 0, 0))) {
				return "Garage";
			} else if (equalsHashmap(firstMap, getHashmap(0, 1, 1, 0, 0, 0, 0, 0))) {
				return "Garden/perimeter";
			} else if (equalsHashmap(firstMap, getHashmap(0, 1, 0, 1, 1, 0, 0, 0))) {
				return "Storeroom";
			} else if (equalsHashmap(firstMap, getHashmap(0, 1, 0, 1, 0, 0, 0, 0))) {
				return "Front door";
			} else if (equalsHashmap(firstMap, getHashmap(0, 1, 0, 0, 1, 0, 0, 0))) {
				return "Stairway";
			} else if (equalsHashmap(firstMap, getHashmap(0, 1, 0, 0, 0, 0, 0, 0))) {
				return "Room";
			} else if (equalsHashmap(firstMap, getHashmap(0, 0, 1, 1, 1, 0, 0, 0))) {
				return "Passageway";
			} else if (equalsHashmap(firstMap, getHashmap(0, 0, 1, 1, 0, 0, 0, 0))) {
				return "Washroom/changing room";
			} else if (equalsHashmap(firstMap, getHashmap(0, 0, 1, 0, 1, 0, 0, 0))) {
				return "Lavatory";
			} else if (equalsHashmap(firstMap, getHashmap(0, 0, 1, 0, 0, 0, 0, 0))) {
				return "Bathroom";
			} else if (equalsHashmap(firstMap, getHashmap(0, 0, 0, 1, 1, 0, 0, 0))) {
				return "Kitchen";
			} else if (equalsHashmap(firstMap, getHashmap(0, 0, 0, 1, 0, 0, 0, 0))) {
				return "Dining room";
			} else if (equalsHashmap(firstMap, getHashmap(0, 0, 0, 0, 1, 0, 0, 0))) {
				return "Living room";
			} else {
				return "Free definition";
			}
		}
	}
	public static String dataToInstallLocation(ResultData rdata) {
		byte data[] = rdata.toBytes();
		byte firstByte = data[0];
		if (firstByte == (byte) 0xFF) {
			return "Installation location not specified";
		} else if (firstByte == (byte) 0x00) {
			return "Installation location undefined";
		} else if (firstByte == (byte) 0x01) { // 17 bytes
			String rs = "";
			for (int i = 1; i < data.length; i++) {
				rs += data[i];
			}
			return rs;
		} else if (!isBitSet(firstByte, 3) && !isBitSet(firstByte, 4) && !isBitSet(firstByte, 5)
				&& !isBitSet(firstByte, 6) && !isBitSet(firstByte, 7)) {
			return "Reserved for future use.";
		} else {
			HashMap<Integer, Integer> firstMap = getHashmapDefaultFromByte(firstByte);
			if (equalsHashmap(firstMap, getHashmap(0, 1, 1, 1, 1, 0, 0, 0))) {
				return "Others";
			} else if (equalsHashmap(firstMap, getHashmap(0, 1, 1, 1, 0, 0, 0, 0))) {
				return "Veranda/balcony";
			} else if (equalsHashmap(firstMap, getHashmap(0, 1, 1, 0, 1, 0, 0, 0))) {
				return "Garage";
			} else if (equalsHashmap(firstMap, getHashmap(0, 1, 1, 0, 0, 0, 0, 0))) {
				return "Garden/perimeter";
			} else if (equalsHashmap(firstMap, getHashmap(0, 1, 0, 1, 1, 0, 0, 0))) {
				return "Storeroom";
			} else if (equalsHashmap(firstMap, getHashmap(0, 1, 0, 1, 0, 0, 0, 0))) {
				return "Front door";
			} else if (equalsHashmap(firstMap, getHashmap(0, 1, 0, 0, 1, 0, 0, 0))) {
				return "Stairway";
			} else if (equalsHashmap(firstMap, getHashmap(0, 1, 0, 0, 0, 0, 0, 0))) {
				return "Room";
			} else if (equalsHashmap(firstMap, getHashmap(0, 0, 1, 1, 1, 0, 0, 0))) {
				return "Passageway";
			} else if (equalsHashmap(firstMap, getHashmap(0, 0, 1, 1, 0, 0, 0, 0))) {
				return "Washroom/changing room";
			} else if (equalsHashmap(firstMap, getHashmap(0, 0, 1, 0, 1, 0, 0, 0))) {
				return "Lavatory";
			} else if (equalsHashmap(firstMap, getHashmap(0, 0, 1, 0, 0, 0, 0, 0))) {
				return "Bathroom";
			} else if (equalsHashmap(firstMap, getHashmap(0, 0, 0, 1, 1, 0, 0, 0))) {
				return "Kitchen";
			} else if (equalsHashmap(firstMap, getHashmap(0, 0, 0, 1, 0, 0, 0, 0))) {
				return "Dining room";
			} else if (equalsHashmap(firstMap, getHashmap(0, 0, 0, 0, 1, 0, 0, 0))) {
				return "Living room";
			} else {
				return "Free definition";
			}
		}
	}
	public static EchonetDeviceGroupCodeValue groupCodeFromByte(byte input) {
		switch (input) {
		case (byte) (0x00):
			return  EchonetDeviceGroupCodeValue.SensorRelatedDevice;
		case (byte) (0x01):
			return EchonetDeviceGroupCodeValue.AirConditionerRelatedDevice;
		case (byte) (0x02):
			return  EchonetDeviceGroupCodeValue.HousingFacilityRelatedDevice;
		case (byte) (0x03):
			return EchonetDeviceGroupCodeValue.CookingHouseholdRelatedDevice;
		case (byte) (0x04):
			return  EchonetDeviceGroupCodeValue.HealthRelatedDevice;
		case (byte) (0x05):
			return EchonetDeviceGroupCodeValue.ManagementOperationRelatedDevice;
		case (byte) (0x06):
			return  EchonetDeviceGroupCodeValue.AudiovisualRelatedDevice;
		default:
			return null;
		}
	}
	
	public static byte byteFromGroupCode(EchonetDeviceGroupCodeValue groupCode) {
		byte rs = 0x11;
		if(EchonetDeviceGroupCodeValue.SensorRelatedDevice.equals(groupCode)) {
			rs = (byte) 0x00;
		} else if(EchonetDeviceGroupCodeValue.AirConditionerRelatedDevice.equals(groupCode)) {
			rs = (byte) 0x01;
		} else if(EchonetDeviceGroupCodeValue.HousingFacilityRelatedDevice.equals(groupCode)) {
			rs = (byte) 0x02;
		} else if(EchonetDeviceGroupCodeValue.CookingHouseholdRelatedDevice.equals(groupCode)) {
			rs = (byte) 0x03;
		} else if(EchonetDeviceGroupCodeValue.HealthRelatedDevice.equals(groupCode)) {
			rs = (byte) 0x04;
		} else if(EchonetDeviceGroupCodeValue.ManagementOperationRelatedDevice.equals(groupCode)) {
			rs = (byte) 0x05;
		} else if(EchonetDeviceGroupCodeValue.AudiovisualRelatedDevice.equals(groupCode)) {
			rs = (byte) 0x06;
		}
		return rs;
	}
	public static ObjectData installLocationtoDataObj(String installation) {
		ObjectData data = null;
		switch (installation.trim().toLowerCase()) {
		case "living room":
			data = new ObjectData((byte)0x08);
			break;
		case "dining room":
			data = new ObjectData((byte)0x10);
			break;
		case "kitchen":
			data = new ObjectData((byte)0x18);
			break;
		case "bathroom":
			data = new ObjectData((byte)0x20);
			break;
		case "lavatory":
			data = new ObjectData((byte)0x28);
			break;
		case "washroom":		
		case "changing room":
			data = new ObjectData((byte)0x30);
			break;
		case "passageway":
			data = new ObjectData((byte)0x38);
			break;
		case "room":
			data = new ObjectData((byte)0x40);
			break;
		case "stairway":
			data = new ObjectData((byte)0x48);
			break;
		case "front door":
			data = new ObjectData((byte)0x50);
			break;
		case "storeroom":
			data = new ObjectData((byte)0x58);
			break;
		case "garden":			
		case "perimeter":
			data = new ObjectData((byte)0x60);
			break;	
		case "garage":
			data = new ObjectData((byte)0x68);
			break;
		case "veranda":
		case "balcony":
			data = new ObjectData((byte)0x70);
			break;
		default:
			data = new ObjectData((byte)0x78);
			break;
		}
		return data;
	}
	public static Date dataDateTime(ObjectData odata) {
		try {
			Calendar calendar = Calendar.getInstance();
			Date rs = null;
			byte dateArray[];
			dateArray = odata.toBytes();
			int year = (((dateArray[0] & 0xff) << 8) | (dateArray[1] & 0xff));
			int month = dateArray[2];
			int day = dateArray[3];
			calendar.set(year, month, day);
			rs = calendar.getTime();
			return rs;
		} catch (Exception ex) {
			return null;
		}
	}
	public static Date dataToDate(ResultData rdata) {
		try {
			Calendar calendar = Calendar.getInstance();
			Date rs = null;
			byte dateArray[];
			dateArray = rdata.toBytes();
			int year = (((dateArray[0] & 0xff) << 8) | (dateArray[1] & 0xff));
			int month = dateArray[2];
			int day = dateArray[3];
			calendar.set(year, month, day);
			rs = calendar.getTime();
			return rs;
		} catch (Exception ex) {
			return null;
		}
	}
	public static String dataToTime(ResultData rdata) {
		byte timeArray[];
		timeArray = rdata.toBytes();
		int h = timeArray[0];
		int m = timeArray[1];
		return "" + ((h < 10) ? ("0" + h) : (h + "")) + ":" + ((m < 10) ? ("0" + m) : ("" + m));
	}

	/**
	 * Convert identification number
	 * 
	 * @param odata
	 * @return string as identification number
	 */
	public static String dataToIdentifiCationNumber(ObjectData odata) {
		byte data[] = odata.toBytes();
		byte firstByte = data[0];
		String comProtocol = "";
		String number = "";
		byte realData[] = new byte[data.length - 1];
		for (int i = 1; i < data.length; i++) {
			realData[i - 1] = data[i];
		}
		if (firstByte == (byte) 0xFE) {
			for (int i = 0; i < realData.length; i++) {
				number += realData[i];
			}
			return number;

		} else if (firstByte == (byte) 0xFF) {
			for (int i = 0; i < realData.length; i++) {
				number += realData[i];
			}
			return number;
		} else {

			switch (firstByte) {
			case (byte) 0x11:
			case (byte) 0x12:
			case (byte) 0x13:
			case (byte) 0x14:
			case (byte) 0x15:
			case (byte) 0x16:
			case (byte) 0x17:
			case (byte) 0x18:
			case (byte) 0x19:
			case (byte) 0x1A:
			case (byte) 0x1B:
			case (byte) 0x1C:
			case (byte) 0x1D:
			case (byte) 0x1E:
			case (byte) 0x1F:
				comProtocol = "Power line Communication Protocol a and d systems";
				break;
			case (byte) 0x31:
			case (byte) 0x32:
			case (byte) 0x33:
			case (byte) 0x34:
			case (byte) 0x35:
			case (byte) 0x36:
			case (byte) 0x37:
			case (byte) 0x38:
			case (byte) 0x39:
			case (byte) 0x3A:
			case (byte) 0x3B:
			case (byte) 0x3C:
			case (byte) 0x3D:
			case (byte) 0x3E:
			case (byte) 0x3F:
				comProtocol = "Low-Power Radio Communication Protocol";
				break;
			case (byte) 0x41:
			case (byte) 0x42:
			case (byte) 0x43:
			case (byte) 0x44:
			case (byte) 0x45:
			case (byte) 0x46:
			case (byte) 0x47:
			case (byte) 0x48:
			case (byte) 0x49:
			case (byte) 0x4A:
			case (byte) 0x4B:
			case (byte) 0x4C:
			case (byte) 0x4D:
			case (byte) 0x4E:
			case (byte) 0x4F:
				comProtocol = "Extended HBS";
				break;
			case (byte) 0x51:
			case (byte) 0x52:
			case (byte) 0x53:
			case (byte) 0x54:
			case (byte) 0x55:
			case (byte) 0x56:
			case (byte) 0x57:
			case (byte) 0x58:
			case (byte) 0x59:
			case (byte) 0x5A:
			case (byte) 0x5B:
			case (byte) 0x5C:
			case (byte) 0x5D:
			case (byte) 0x5E:
			case (byte) 0x5F:
				comProtocol = "IrDA";
				break;
			case (byte) 0x61:
			case (byte) 0x62:
			case (byte) 0x63:
			case (byte) 0x64:
			case (byte) 0x65:
			case (byte) 0x66:
			case (byte) 0x67:
			case (byte) 0x68:
			case (byte) 0x69:
			case (byte) 0x6A:
			case (byte) 0x6B:
			case (byte) 0x6C:
			case (byte) 0x6D:
			case (byte) 0x6E:
			case (byte) 0x6F:
				comProtocol = "LonTalk";
				break;
			case (byte) 0x71:
			case (byte) 0x72:
			case (byte) 0x73:
			case (byte) 0x74:
			case (byte) 0x75:
			case (byte) 0x76:
			case (byte) 0x77:
			case (byte) 0x78:
			case (byte) 0x79:
			case (byte) 0x7A:
			case (byte) 0x7B:
			case (byte) 0x7C:
			case (byte) 0x7D:
			case (byte) 0x7E:
			case (byte) 0x7F:
				comProtocol = "Bluetooth";
				break;
			case (byte) 0x81:
			case (byte) 0x82:
			case (byte) 0x83:
			case (byte) 0x84:
			case (byte) 0x85:
			case (byte) 0x86:
			case (byte) 0x87:
			case (byte) 0x88:
			case (byte) 0x89:
			case (byte) 0x8A:
			case (byte) 0x8B:
			case (byte) 0x8C:
			case (byte) 0x8D:
			case (byte) 0x8E:
			case (byte) 0x8F:
				comProtocol = "Ethernet";
				break;
			case (byte) 0x91:
			case (byte) 0x92:
			case (byte) 0x93:
			case (byte) 0x94:
			case (byte) 0x95:
			case (byte) 0x96:
			case (byte) 0x97:
			case (byte) 0x98:
			case (byte) 0x99:
			case (byte) 0x9A:
			case (byte) 0x9B:
			case (byte) 0x9C:
			case (byte) 0x9D:
			case (byte) 0x9E:
			case (byte) 0x9F:
				comProtocol = "IEEE802.11/11b";
				break;
			case (byte) 0xA1:
				comProtocol = "Power line Communication Protocol c systems";
				break;
			case (byte) 0xB1:
				comProtocol = "IPv6/Ethernet";
				break;
			case (byte) 0xB2:
				comProtocol = "IPv6/6LoWPAN";
				break;
			default:
				comProtocol = "Undefined";
				break;

			}
			number = new BigInteger(realData).toString() + "";
			return comProtocol + ((number.trim().length() > 1) ? " " + number : "");
		}
	}
	public static String dataToIdentifiCationNumber(ResultData rdata) {
		byte data[] = rdata.toBytes();
		byte firstByte = data[0];
		String comProtocol = "";
		String number = "";
		byte realData[] = new byte[data.length - 1];
		for (int i = 1; i < data.length; i++) {
			realData[i - 1] = data[i];
		}
		if (firstByte == (byte) 0xFE) {
			for (int i = 0; i < realData.length; i++) {
				number += realData[i];
			}
			return number;

		} else if (firstByte == (byte) 0xFF) {
			for (int i = 0; i < realData.length; i++) {
				number += realData[i];
			}
			return number;
		} else {

			switch (firstByte) {
			case (byte) 0x11:
			case (byte) 0x12:
			case (byte) 0x13:
			case (byte) 0x14:
			case (byte) 0x15:
			case (byte) 0x16:
			case (byte) 0x17:
			case (byte) 0x18:
			case (byte) 0x19:
			case (byte) 0x1A:
			case (byte) 0x1B:
			case (byte) 0x1C:
			case (byte) 0x1D:
			case (byte) 0x1E:
			case (byte) 0x1F:
				comProtocol = "Power line Communication Protocol a and d systems";
				break;
			case (byte) 0x31:
			case (byte) 0x32:
			case (byte) 0x33:
			case (byte) 0x34:
			case (byte) 0x35:
			case (byte) 0x36:
			case (byte) 0x37:
			case (byte) 0x38:
			case (byte) 0x39:
			case (byte) 0x3A:
			case (byte) 0x3B:
			case (byte) 0x3C:
			case (byte) 0x3D:
			case (byte) 0x3E:
			case (byte) 0x3F:
				comProtocol = "Low-Power Radio Communication Protocol";
				break;
			case (byte) 0x41:
			case (byte) 0x42:
			case (byte) 0x43:
			case (byte) 0x44:
			case (byte) 0x45:
			case (byte) 0x46:
			case (byte) 0x47:
			case (byte) 0x48:
			case (byte) 0x49:
			case (byte) 0x4A:
			case (byte) 0x4B:
			case (byte) 0x4C:
			case (byte) 0x4D:
			case (byte) 0x4E:
			case (byte) 0x4F:
				comProtocol = "Extended HBS";
				break;
			case (byte) 0x51:
			case (byte) 0x52:
			case (byte) 0x53:
			case (byte) 0x54:
			case (byte) 0x55:
			case (byte) 0x56:
			case (byte) 0x57:
			case (byte) 0x58:
			case (byte) 0x59:
			case (byte) 0x5A:
			case (byte) 0x5B:
			case (byte) 0x5C:
			case (byte) 0x5D:
			case (byte) 0x5E:
			case (byte) 0x5F:
				comProtocol = "IrDA";
				break;
			case (byte) 0x61:
			case (byte) 0x62:
			case (byte) 0x63:
			case (byte) 0x64:
			case (byte) 0x65:
			case (byte) 0x66:
			case (byte) 0x67:
			case (byte) 0x68:
			case (byte) 0x69:
			case (byte) 0x6A:
			case (byte) 0x6B:
			case (byte) 0x6C:
			case (byte) 0x6D:
			case (byte) 0x6E:
			case (byte) 0x6F:
				comProtocol = "LonTalk";
				break;
			case (byte) 0x71:
			case (byte) 0x72:
			case (byte) 0x73:
			case (byte) 0x74:
			case (byte) 0x75:
			case (byte) 0x76:
			case (byte) 0x77:
			case (byte) 0x78:
			case (byte) 0x79:
			case (byte) 0x7A:
			case (byte) 0x7B:
			case (byte) 0x7C:
			case (byte) 0x7D:
			case (byte) 0x7E:
			case (byte) 0x7F:
				comProtocol = "Bluetooth";
				break;
			case (byte) 0x81:
			case (byte) 0x82:
			case (byte) 0x83:
			case (byte) 0x84:
			case (byte) 0x85:
			case (byte) 0x86:
			case (byte) 0x87:
			case (byte) 0x88:
			case (byte) 0x89:
			case (byte) 0x8A:
			case (byte) 0x8B:
			case (byte) 0x8C:
			case (byte) 0x8D:
			case (byte) 0x8E:
			case (byte) 0x8F:
				comProtocol = "Ethernet";
				break;
			case (byte) 0x91:
			case (byte) 0x92:
			case (byte) 0x93:
			case (byte) 0x94:
			case (byte) 0x95:
			case (byte) 0x96:
			case (byte) 0x97:
			case (byte) 0x98:
			case (byte) 0x99:
			case (byte) 0x9A:
			case (byte) 0x9B:
			case (byte) 0x9C:
			case (byte) 0x9D:
			case (byte) 0x9E:
			case (byte) 0x9F:
				comProtocol = "IEEE802.11/11b";
				break;
			case (byte) 0xA1:
				comProtocol = "Power line Communication Protocol c systems";
				break;
			case (byte) 0xB1:
				comProtocol = "IPv6/Ethernet";
				break;
			case (byte) 0xB2:
				comProtocol = "IPv6/6LoWPAN";
				break;
			default:
				comProtocol = "Undefined";
				break;

			}
			number = new BigInteger(realData).toString() + "";
			return comProtocol + ((number.trim().length() > 1) ? " " + number : "");
		}
	}

	public static String getFaultDetail(ObjectData odata) throws EchonetObjectException {
		FaultType faultType = null;
		String contentSpecification = "";
		byte[] allData = odata.toBytes();

		if (allData[1] != (byte) 0x00) {
			if (allData[0] == (byte) 0xFF) {
				faultType = FaultType.Undefined;
				contentSpecification = "A fault has occurred but the recovery"
						+ " method or fault location cannot be determined.";
			} else {
				faultType = FaultType.Undefined;
				contentSpecification = "The fault undefined.";
			}
		} else {
			switch (allData[0]) {
			case (byte) 0x00:
				contentSpecification = "No fault";
				faultType = null;
				break;
			case (byte) 0x01:
				contentSpecification = "Faults that can be recovered from by turning off the power switch and turning it on again or withdrawing and re-inserting the power plug.";
				faultType = FaultType.Recoverable_Faults;
				break;
			case (byte) 0x02:
				contentSpecification = "Faults that can be recovered from by pressing the reset button.";
				faultType = FaultType.Recoverable_Faults;
				break;
			case (byte) 0x03:
				contentSpecification = "Faults that can be recovered from by changing the way the device is mounted or opening/closing a lid or door.";
				faultType = FaultType.Recoverable_Faults;
				break;
			case (byte) 0x04:
				contentSpecification = "Faults that can be recovered from by supplying fuel, water, air, etc.";
				faultType = FaultType.Recoverable_Faults;
				break;
			case (byte) 0x05:
				contentSpecification = "Faults that can be recovered from by cleaning the device (filter etc.)";
				faultType = FaultType.Recoverable_Faults;
				break;
			case (byte) 0x06:
				contentSpecification = "Faults that can be recovered from by changing the battery or cell.";
				faultType = FaultType.Recoverable_Faults;
				break;
			case (byte) 0x07:
			case (byte) 0x08:
				contentSpecification = "Undefined";
				faultType = FaultType.Recoverable_Faults;
				break;
			case (byte) 0x09:
				contentSpecification = "User-definable domain";
				faultType = FaultType.Recoverable_Faults;
				break;
			case (byte) 0x0A:
			case (byte) 0x0B:
			case (byte) 0x0C:
			case (byte) 0x0D:
			case (byte) 0x0E:
			case (byte) 0x0F:
			case (byte) 0x11:
			case (byte) 0x12:
			case (byte) 0x13:
				contentSpecification = "Abnormal event or the tripping of a safety device.";
				faultType = FaultType.Require_Repair_Faults;
				break;
			case (byte) 0x14:
			case (byte) 0x15:
			case (byte) 0x16:
			case (byte) 0x17:
			case (byte) 0x18:
			case (byte) 0x19:
			case (byte) 0x1A:
			case (byte) 0x1B:
			case (byte) 0x1C:
			case (byte) 0x1D:
				contentSpecification = "Fault in a switch.";
				faultType = FaultType.Require_Repair_Faults;
				break;
			case (byte) 0x1E:
			case (byte) 0x1F:
			case (byte) 0x21:
			case (byte) 0x22:
			case (byte) 0x23:
			case (byte) 0x24:
			case (byte) 0x25:
			case (byte) 0x26:
			case (byte) 0x27:
			case (byte) 0x28:
			case (byte) 0x29:
			case (byte) 0x2A:
			case (byte) 0x2B:
			case (byte) 0x2C:
			case (byte) 0x2D:
			case (byte) 0x2E:
			case (byte) 0x2F:
			case (byte) 0x31:
			case (byte) 0x32:
			case (byte) 0x33:
			case (byte) 0x34:
			case (byte) 0x35:
			case (byte) 0x36:
			case (byte) 0x37:
			case (byte) 0x38:
			case (byte) 0x39:
			case (byte) 0x3A:
			case (byte) 0x3B:
				contentSpecification = "Fault in the sensor system.";
				faultType = FaultType.Require_Repair_Faults;
				break;
			case (byte) 0x3C:
			case (byte) 0x3D:
			case (byte) 0x3E:
			case (byte) 0x3F:
			case (byte) 0x41:
			case (byte) 0x42:
			case (byte) 0x43:
			case (byte) 0x44:
			case (byte) 0x45:
			case (byte) 0x46:
			case (byte) 0x47:
			case (byte) 0x48:
			case (byte) 0x49:
			case (byte) 0x4A:
			case (byte) 0x4B:
			case (byte) 0x4C:
			case (byte) 0x4D:
			case (byte) 0x4E:
			case (byte) 0x4F:
			case (byte) 0x51:
			case (byte) 0x52:
			case (byte) 0x53:
			case (byte) 0x54:
			case (byte) 0x55:
			case (byte) 0x56:
			case (byte) 0x57:
			case (byte) 0x58:
			case (byte) 0x59:
				contentSpecification = "Fault in a component such as an actuator.";
				faultType = FaultType.Require_Repair_Faults;
				break;
			case (byte) 0x5A:
			case (byte) 0x5B:
			case (byte) 0x5C:
			case (byte) 0x5D:
			case (byte) 0x5E:
			case (byte) 0x5F:
			case (byte) 0x61:
			case (byte) 0x62:
			case (byte) 0x63:
			case (byte) 0x64:
			case (byte) 0x65:
			case (byte) 0x66:
			case (byte) 0x67:
			case (byte) 0x68:
			case (byte) 0x69:
			case (byte) 0x6A:
			case (byte) 0x6B:
			case (byte) 0x6C:
			case (byte) 0x6D:
			case (byte) 0x6E:
				contentSpecification = "Fault in a control circuit board.";
				faultType = FaultType.Require_Repair_Faults;
				break;
			default:
				if (EchonetDataConverter.dataToInteger(allData) <= (byte) 0x03E8
						&& EchonetDataConverter.dataToInteger(allData) >= (byte) 0x006F) {
					contentSpecification = "User-definable domain";
					faultType = FaultType.Require_Repair_Faults;
				} else {
					faultType = FaultType.Undefined;
					contentSpecification = "The fault undefined.";
				}
				break;
			}
		}

		return (faultType == null) ? notFaultDescription : faultType.toString() + ":" + contentSpecification;
	}
	public static String getFaultDetail(ResultData rdata) throws EchonetObjectException {
		FaultType faultType = null;
		String contentSpecification = "";
		byte[] allData = rdata.toBytes();

		if (allData[1] != (byte) 0x00) {
			if (allData[0] == (byte) 0xFF) {
				faultType = FaultType.Undefined;
				contentSpecification = "A fault has occurred but the recovery"
						+ " method or fault location cannot be determined.";
			} else {
				faultType = FaultType.Undefined;
				contentSpecification = "The fault undefined.";
			}
		} else {
			switch (allData[0]) {
			case (byte) 0x00:
				contentSpecification = "No fault";
				faultType = null;
				break;
			case (byte) 0x01:
				contentSpecification = "Faults that can be recovered from by turning off the power switch and turning it on again or withdrawing and re-inserting the power plug.";
				faultType = FaultType.Recoverable_Faults;
				break;
			case (byte) 0x02:
				contentSpecification = "Faults that can be recovered from by pressing the reset button.";
				faultType = FaultType.Recoverable_Faults;
				break;
			case (byte) 0x03:
				contentSpecification = "Faults that can be recovered from by changing the way the device is mounted or opening/closing a lid or door.";
				faultType = FaultType.Recoverable_Faults;
				break;
			case (byte) 0x04:
				contentSpecification = "Faults that can be recovered from by supplying fuel, water, air, etc.";
				faultType = FaultType.Recoverable_Faults;
				break;
			case (byte) 0x05:
				contentSpecification = "Faults that can be recovered from by cleaning the device (filter etc.)";
				faultType = FaultType.Recoverable_Faults;
				break;
			case (byte) 0x06:
				contentSpecification = "Faults that can be recovered from by changing the battery or cell.";
				faultType = FaultType.Recoverable_Faults;
				break;
			case (byte) 0x07:
			case (byte) 0x08:
				contentSpecification = "Undefined";
				faultType = FaultType.Recoverable_Faults;
				break;
			case (byte) 0x09:
				contentSpecification = "User-definable domain";
				faultType = FaultType.Recoverable_Faults;
				break;
			case (byte) 0x0A:
			case (byte) 0x0B:
			case (byte) 0x0C:
			case (byte) 0x0D:
			case (byte) 0x0E:
			case (byte) 0x0F:
			case (byte) 0x11:
			case (byte) 0x12:
			case (byte) 0x13:
				contentSpecification = "Abnormal event or the tripping of a safety device.";
				faultType = FaultType.Require_Repair_Faults;
				break;
			case (byte) 0x14:
			case (byte) 0x15:
			case (byte) 0x16:
			case (byte) 0x17:
			case (byte) 0x18:
			case (byte) 0x19:
			case (byte) 0x1A:
			case (byte) 0x1B:
			case (byte) 0x1C:
			case (byte) 0x1D:
				contentSpecification = "Fault in a switch.";
				faultType = FaultType.Require_Repair_Faults;
				break;
			case (byte) 0x1E:
			case (byte) 0x1F:
			case (byte) 0x21:
			case (byte) 0x22:
			case (byte) 0x23:
			case (byte) 0x24:
			case (byte) 0x25:
			case (byte) 0x26:
			case (byte) 0x27:
			case (byte) 0x28:
			case (byte) 0x29:
			case (byte) 0x2A:
			case (byte) 0x2B:
			case (byte) 0x2C:
			case (byte) 0x2D:
			case (byte) 0x2E:
			case (byte) 0x2F:
			case (byte) 0x31:
			case (byte) 0x32:
			case (byte) 0x33:
			case (byte) 0x34:
			case (byte) 0x35:
			case (byte) 0x36:
			case (byte) 0x37:
			case (byte) 0x38:
			case (byte) 0x39:
			case (byte) 0x3A:
			case (byte) 0x3B:
				contentSpecification = "Fault in the sensor system.";
				faultType = FaultType.Require_Repair_Faults;
				break;
			case (byte) 0x3C:
			case (byte) 0x3D:
			case (byte) 0x3E:
			case (byte) 0x3F:
			case (byte) 0x41:
			case (byte) 0x42:
			case (byte) 0x43:
			case (byte) 0x44:
			case (byte) 0x45:
			case (byte) 0x46:
			case (byte) 0x47:
			case (byte) 0x48:
			case (byte) 0x49:
			case (byte) 0x4A:
			case (byte) 0x4B:
			case (byte) 0x4C:
			case (byte) 0x4D:
			case (byte) 0x4E:
			case (byte) 0x4F:
			case (byte) 0x51:
			case (byte) 0x52:
			case (byte) 0x53:
			case (byte) 0x54:
			case (byte) 0x55:
			case (byte) 0x56:
			case (byte) 0x57:
			case (byte) 0x58:
			case (byte) 0x59:
				contentSpecification = "Fault in a component such as an actuator.";
				faultType = FaultType.Require_Repair_Faults;
				break;
			case (byte) 0x5A:
			case (byte) 0x5B:
			case (byte) 0x5C:
			case (byte) 0x5D:
			case (byte) 0x5E:
			case (byte) 0x5F:
			case (byte) 0x61:
			case (byte) 0x62:
			case (byte) 0x63:
			case (byte) 0x64:
			case (byte) 0x65:
			case (byte) 0x66:
			case (byte) 0x67:
			case (byte) 0x68:
			case (byte) 0x69:
			case (byte) 0x6A:
			case (byte) 0x6B:
			case (byte) 0x6C:
			case (byte) 0x6D:
			case (byte) 0x6E:
				contentSpecification = "Fault in a control circuit board.";
				faultType = FaultType.Require_Repair_Faults;
				break;
			default:
				if (EchonetDataConverter.dataToInteger(allData) <= (byte) 0x03E8
						&& EchonetDataConverter.dataToInteger(allData) >= (byte) 0x006F) {
					contentSpecification = "User-definable domain";
					faultType = FaultType.Require_Repair_Faults;
				} else {
					faultType = FaultType.Undefined;
					contentSpecification = "The fault undefined.";
				}
				break;
			}
		}

		return (faultType == null) ? notFaultDescription : faultType.toString() + ":" + contentSpecification;
	}

	/**
	 * Convert data to real
	 * 
	 * @param data
	 * @return double value
	 */
	public static double dataToReal(ObjectData data) {
		boolean is_negative = false;
		int dataSize = data.size();
		BigDecimal up = BigDecimal.valueOf(0x0100);
		BigDecimal dec = new BigDecimal(0);

		for (int i = 0; i < dataSize; i++) {
			if ((i == 0) && (data.get(i) < 0)) {
				is_negative = true;
			}

			int pos_data;
			if (is_negative) {
				pos_data = 0xff & ((int) ((byte) -1) ^ data.get(i));
			} else {
				pos_data = 0x00ff & (int) data.get(i);
			}

			BigDecimal cur = BigDecimal.valueOf(pos_data);
			dec = dec.multiply(up).add(cur);
		}

		if (is_negative) {
			dec = dec.add(BigDecimal.valueOf(1));
			dec = dec.negate();
		}
		return dec.doubleValue();
	}
	public static String dataToCummalativeTime(ResultData data) {
		byte timeArray[];
		timeArray = data.toBytes();
		String unit = "";
		switch (timeArray[0]) {
		case (byte) 0x41:
			unit = "seconds";
			break;
		case (byte) 0x42:
			unit = "months";
			break;
		case (byte) 0x43:
			unit = "hours";
			break;
		case (byte) 0x44:
			unit = "days";
			break;
		default:
			unit = "seconds";
			break;
		}
		byte valueArray[] = new byte[4];
		valueArray[0] = timeArray[1];
		valueArray[1] = timeArray[2];
		valueArray[2] = timeArray[3];
		valueArray[3] = timeArray[4];
		int timeSpan = EchonetDataConverter.dataToInteger(valueArray);
		return timeSpan + " " + unit;
	}
	public static String getIPAddr(String input) {
		String IPADDRESS_PATTERN = 
		        "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
		String ip = "";
		Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
		Matcher matcher = pattern.matcher(input);

		if (matcher.find()) {
			ip = matcher.group();
		}
		return ip;
	}

	public static byte[] requestData(EPC epc, String data) {
		try {
			byte[] result;
			data = data.trim();
			switch (epc) {
			case x80:
				Boolean status = null;
				status = Boolean.parseBoolean(data);
				if (status == null)
					throw new ParseException("Cannot parse to boolean as operation status: EPC.x80.", 0);
				result = new byte[1];
				result[0] = ((status) ? ((byte) 0x30) : ((byte) 0x31));
				return result;
			case x81:
				data = data.toLowerCase();
				result = new byte[1];
				switch (data) {
				case "living room":
					result[0] = (byte) 0x08;
					break;
				case "dining room":
					result[0] = (byte) 0x10;
					break;
				case "kitchen":
					result[0] = (byte) 0x18;
					break;
				case "bathroom":
					result[0] = (byte) 0x20;
					break;
				case "lavatory":
					result[0] = (byte) 0x28;
					break;
				case "washroom/changing room":
					result[0] = (byte) 0x30;
					break;
				case "passageway":
					result[0] = (byte) 0x38;
					break;
				case "room":
					result[0] = (byte) 0x40;
					break;
				case "stairway":
					result[0] = (byte) 0x48;
					break;
				case "front door":
					result[0] = (byte) 0x50;
					break;
				case "storeroom":
					result[0] = (byte) 0x58;
					break;
				case "garden/perimeter":
					result[0] = (byte) 0x60;
					break;
				case "garage":
					result[0] = (byte) 0x68;
					break;
				case "veranda/balcony":
					result[0] = (byte) 0x70;
					break;
				case "others":
					result[0] = (byte) 0x78;
					break;
				default:
					result[0] = (byte) 0x00;
					break;
				}
				return result;
			case x87:
				Integer setting = null;
				setting = Integer.parseInt(data);
				if (setting == null) {
					throw new ParseException("Cannot parse to number as current limit setting: EPC.x87.",0);
				}
				result = new byte[1];
				if (setting.intValue() < 0)
					setting = 0;
				if (setting.intValue() > 100)
					setting = 100;
				result[0] = setting.byteValue();
				return result;
			case x8F:
				Boolean saving = null;
				saving = Boolean.parseBoolean(data);
				if (saving == null) {
					throw new ParseException("Cannot parse to boolean as power-saving operation setting: EPC.x8F.", 0);
				}
				result = new byte[1];
				result[0] = ((saving) ? ((byte) 0x41) : ((byte) 0x42));
				return result;
			case x93:
				Boolean remote = null;
				remote = Boolean.parseBoolean(data);
				if (remote == null) {
					throw new ParseException("Cannot parse to boolean as remote control setting: EPC.x93.",0);
				}
				result = new byte[1];
				result[0] = ((remote) ? ((byte) 0x41) : ((byte) 0x42));
				return result;
			case x97:
				String[] allTime = data.split(":");
				Integer hour = Integer.parseInt(allTime[0]);
				Integer minutes = Integer.parseInt(allTime[1]);
				if (hour == null || minutes == null) {
					throw new ParseException("Cannot parse to time as current time setting: EPC.x97.",0);
				}
				result = new byte[2];
				result[0] = hour.byteValue();
				result[1] = minutes.byteValue();
				return result;
			case x98:
				String[] allDate = data.split(":");
				Integer year = Integer.parseInt(allDate[0]);
				Integer month = Integer.parseInt(allDate[1]);
				Integer day = Integer.parseInt(allDate[1]);
				if (year == null || month == null || day == null) {
					throw new ParseException("Cannot parse to time as current date setting: EPC.x98.",0);
				}
				byte[] dataYear = new byte[2];
				dataYear[0] = (byte) (year & 0xFF);
				dataYear[1] = (byte) ((year >> 8) & 0xFF);

				result = new byte[4];
				result[0] = dataYear[1];
				result[1] = dataYear[0];
				result[2] = month.byteValue();
				result[3] = day.byteValue();
				return result;
			case x99:
				Integer power = Integer.parseInt(data);
				if (power == null) {
					throw new ParseException("Cannot parse to number as power limit setting: EPC.x99.",0);
				}
				byte[] dataPower = new byte[2];
				dataPower[0] = (byte) (power & 0xFF);
				dataPower[1] = (byte) ((power >> 8) & 0xFF);

				result = new byte[2];
				result[0] = dataPower[1];
				result[1] = dataPower[0];
				return result;
			default:
				return null;
			}
		} catch (ParseException ex) {
			return null;
		} catch (Exception ex) {
			return null;
		}
	}

}
