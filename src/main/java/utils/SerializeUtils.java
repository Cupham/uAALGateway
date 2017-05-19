package utils;

import java.util.ArrayList;

import com.google.gson.Gson;

import echonet.objects.EchonetLiteDevice;
import echonet.objects.eDataObject;
import echonet.objects.eProfileObject;
import echonet.objects.eTemperatureSensor;

public class SerializeUtils {
	public static eTemperatureSensor temperatureSensorFromMessage(String message) {
		Gson gson = new Gson();
		eTemperatureSensor rs =  gson.fromJson(message, eTemperatureSensor.class);
		return rs;
	}
	public static eTemperatureSensor temperatureSensorFromEDataObjects(ArrayList<eDataObject> objList) {
		for(eDataObject dataObj : objList) {
			if (dataObj.getClass().equals(eTemperatureSensor.class)) {
				return (eTemperatureSensor) dataObj;
			}
		}
		return null;
	}
	public static String messageFromTemperatureSensor(eTemperatureSensor sensor) {
		Gson gson = new Gson();
		return gson.toJson(sensor);
	}
}
