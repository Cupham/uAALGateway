package mainpackage;

import java.util.ArrayList;

import utils.SerializeUtils;
import echonet.objects.eTemperatureSensor;

/* ===========================================================================
 * This is where you can handle the inputs of the current CARESSES component
 * as soon as they are received. Just put inside the handleSubscribedMessage()
 * function whatever you want to do with the received message.
 * ===========================================================================
 */

public class SubscriptionHandler {
	
	SubscriptionHandler(){
		
	}
	
	public static void handleSubscribedMessage(String message_type_id, String msg){
		switch (message_type_id) {
		case "TemperatureSensor":
			System.out.println(String.format("INFO: Received the following message of type %s from subscription:\n %s \n", message_type_id, msg));
			eTemperatureSensor eSensor = SerializeUtils.temperatureSensorFromMessage(msg);		
			break;

		default:
			break;
		}
		
		
	}
}
