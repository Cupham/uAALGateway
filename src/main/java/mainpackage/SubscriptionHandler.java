package mainpackage;

import java.util.ArrayList;

import echonet.objects.eTemperatureSensor;
/* ===========================================================================
 * This is where you can handle the inputs of the current CARESSES component
 * as soon as they are received on the Context Bus. Just put inside the
 * handleSubscribedMessage() method whatever you want to do with the received
 * message.
 * ===========================================================================
 */

public class SubscriptionHandler {
	
	SubscriptionHandler(){
		
	}
	
	public static void handleSubscribedMessage(String subscription_response_message){

		// : Parse the received message

		String[] received_msg = subscription_response_message.split("#");
		String msg_type_ID = received_msg[1];
		String msg_content = received_msg[2];

		// YOU CAN DELETE THE FOLLOWING LINE

		System.out.println(String.format(" SUBSCRIPTION HANDLER: INFO: Received the following message from subscription:\n %s \n", subscription_response_message));

		// DO SOMETHING WITH msg_content...

	}

}
