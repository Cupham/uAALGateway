package mainpackage;

/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_6#Publishing_context_events */

import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.context.ContextEventPattern;
import org.universAAL.middleware.context.ContextPublisher;
import org.universAAL.middleware.context.owl.ContextProvider;
import org.universAAL.middleware.context.owl.ContextProviderType;

public class SocketPublisher extends ContextPublisher {

	public static final String CONTEXT_PROVIDER = CaressesOntology.NAMESPACE + "CaressesContextProvider";
	int msgCount = 0;
	ModuleContext mycontext;

//	protected SocketPublisher(ModuleContext context, ContextProvider providerInfo) {
//		super(context, providerInfo);
//	}

	protected SocketPublisher(ModuleContext context) {
		super(context, getProviderInfo());
		mycontext = context;
//		System.out.println(Component.component_ID + " SOCKET PUBLISHER"); System.out.flush();
	}

	public String publishCESocketMessage(String msg) {
		
		String reply = "empty";

		String[] socket_message = msg.split("#");
		
		String command         = socket_message[0];
		String message_type_ID = socket_message[1];
		String message_content = "";
		if (socket_message.length == 3) {
			message_content = socket_message[2];
		}

		// : PUBLISH

		if (command.equals("publish")){
			reply = Activator.cpublisher.publishContextEvent(message_type_ID, message_content);
		}

		// : REQUEST A MESSAGE (through service bus)

		else if (command.equals("request")){
			reply = Activator.scaller.requestMessage(message_type_ID);
		}

		// : PROVIDE A MESSAGE REQUESTED AS SERVICE

		else if (command.equals("provide")){

			if (message_type_ID.equals(Component.message_datatype_requested)){
				OntologyUpdater.updateOntology(message_type_ID, message_content);
				Component.ontology_updated = true;
				Component.message_datatype_requested = "NO_REQUEST";
				reply = "INFO: Service has been provided";
			} else if (Component.message_datatype_requested.equals("NO_REQUEST")){
				reply = "ERROR: No component has requested this service";
			} else {
				reply = "ERROR: This is not the message requested. Check again the datatype!";
			}

		}

		// : GET LAST MESSAGE RECEIVED
		
		else if (command.equals("get")){

			reply = "gotten#" + message_type_ID + "#";

			if (Component.is_Cahrim){
				switch (message_type_ID){
				case "D7.1":
					reply = reply + Activator.i_D7_1.getMessage();
					break;
				case "D7.2":
					reply = reply + Activator.i_D7_2.getMessage();
					break;
				case "D11.1":
					reply = reply + Activator.i_D11_1.getMessage();
					break;
				case "D11.2":
					reply = reply + Activator.i_D11_2.getMessage();
					break;
				case "D11.3":
					reply = reply + Activator.i_D11_3.getMessage();
					break;
				case "D11.4":
					reply = reply + Activator.i_D11_4.getMessage();
					break;
				default:
					reply = "INFO: No message found";}
			}
			if (Component.is_Ckb){
				switch (message_type_ID){
				case "D5.1":
					reply = reply + Activator.i_D5_1.getMessage();
					break;
				case "D5.2":
					reply = reply + Activator.i_D5_2.getMessage();
					break;
				case "D8.1":
					reply = reply + Activator.i_D8_1.getMessage();
					break;
				case "D8.2":
					reply = reply + Activator.i_D8_2.getMessage();
					break;
				default:
					reply = "INFO: No message found";
				}
			}
			if (Component.is_Cspem){
				switch (message_type_ID){
				case "D1.1":
				    reply = reply + Activator.i_D1_1.getMessage();
				    break;
				case "D1.2":
					reply = reply + Activator.i_D1_2.getMessage();
					break;
				case "D2.1":
					reply = reply + Activator.i_D2_1.getMessage();
					break;
				case "D2.2":
					reply = reply + Activator.i_D2_2.getMessage();
					break;
				case "D2.3":
					reply = reply + Activator.i_D2_3.getMessage();
					break;
				case "D2.4":
					reply = reply + Activator.i_D2_4.getMessage();
					break;
				case "D3.1":
					reply = reply + Activator.i_D3_1.getMessage();
					break;
				case "D3.2":
					reply = reply + Activator.i_D3_2.getMessage();
					break;
				case "D4.1":
					reply = reply + Activator.i_D4_1.getMessage();
					break;
				case "D4.2":
					reply = reply + Activator.i_D4_2.getMessage();
					break;
				case "D5.1":
					reply = reply + Activator.i_D5_1.getMessage();
					break;
				case "D5.2":
					reply = reply + Activator.i_D5_2.getMessage();
					break;
				case "D6.1":
					reply = reply + Activator.i_D6_1.getMessage();
					break;
				case "D6.2":
					reply = reply + Activator.i_D6_2.getMessage();
					break;
				case "D6.3":
					reply = reply + Activator.i_D6_3.getMessage();
					break;
				default:
					reply = "INFO: No message found";
				}
			}

		} else {
			reply = "ERROR: Invalid message from client";
		}

		if (reply.equals(""))
			reply = "INFO: Empty Message";

		System.out.println(Component.component_ID + " SOCKET PUBLISHER: Output message from server to client > " + reply + "\n");

		return reply;
		
		//	scaller.getIndividualSet(message_type_ID);
		//if (command.equals("addIndividual"))
		//	scaller.addIndividual(message_type_ID, message_content);

	}

	private static ContextProvider getProviderInfo() {
		ContextProvider myContextProvider = new ContextProvider(CONTEXT_PROVIDER);
		myContextProvider.setType(ContextProviderType.gauge);
		myContextProvider.setProvidedEvents(new ContextEventPattern[] { new ContextEventPattern() });
		return myContextProvider;
	}

	public void startServer() {
		ScktServer server = new ScktServer(this);
		Thread thserver = new Thread(server);
		Thread th = new Thread(thserver);
		th.start();
	}

	public void communicationChannelBroken() {
		System.out.println(Component.component_ID + " SOCKET PUBLISHER: Lost connection with the bus\n");
	}

}
