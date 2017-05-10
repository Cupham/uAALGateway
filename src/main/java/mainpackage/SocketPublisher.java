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

	protected SocketPublisher(ModuleContext context, ContextProvider providerInfo) {
		super(context, providerInfo);

		// TODO Auto-generated constructor stub
	}

	public String publishCESocketMessage(String msg) {
		
		String reply="empty";

		String[] actions = msg.split("#");
		if (actions[0].equals("publish"))
		{
			reply=Activator.cpublisher.publishContextEvent(actions[1], actions[2]);			
		}
		
		if (actions[0].equals("get"))
		{
			if (Component.is_Cahrim){
				switch (actions[1]){
				case "D7.1":
					reply=Activator.i_D7_1.getMessage();
					break;
				case "D7.2":
					reply=Activator.i_D7_2.getMessage();
					break;
				case "D11.1":
					reply=Activator.i_D11_1.getMessage();
					break;
				case "D11.2":
					reply=Activator.i_D11_2.getMessage();
					break;
				case "D11.3":
					reply=Activator.i_D11_3.getMessage();
					break;
				case "D11.4":
					reply=Activator.i_D11_4.getMessage();
					break;
				default:
					reply="No message found";}
			}
			if (Component.is_Ckb){
				switch (actions[1]){
				case "D5.1":
					reply=Activator.i_D5_1.getMessage();
					break;
				case "D5.2":
					reply=Activator.i_D5_2.getMessage();
					break;
				case "D8.1":
					reply=Activator.i_D8_1.getMessage();
					break;
				case "D8.2":
					reply=Activator.i_D8_2.getMessage();
					break;
				default:
					reply="No message found";
				}
			}
			if (Component.is_Cspem){
				switch (actions[1]){
				case "D1.1":
				    reply=Activator.i_D1_1.getMessage();
				    break;
				case "D1.2":
					reply=Activator.i_D1_2.getMessage();
					break;
				case "D2.1":
					reply=Activator.i_D2_1.getMessage();
					break;
				case "D2.2":
					reply=Activator.i_D2_2.getMessage();
					break;
				case "D2.3":
					reply=Activator.i_D2_3.getMessage();
					break;
				case "D2.4":
					reply=Activator.i_D2_4.getMessage();
					break;
				case "D3.1":
					reply=Activator.i_D3_1.getMessage();
					break;
				case "D3.2":
					reply=Activator.i_D3_2.getMessage();
					break;
				case "D4.1":
					reply=Activator.i_D4_1.getMessage();
					break;
				case "D4.2":
					reply=Activator.i_D4_2.getMessage();
					break;
				case "D5.1":
					reply=Activator.i_D5_1.getMessage();
					break;
				case "D5.2":
					reply=Activator.i_D5_2.getMessage();
					break;
				case "D6.1":
					reply=Activator.i_D6_1.getMessage();
					break;
				case "D6.2":
					reply=Activator.i_D6_2.getMessage();
					break;
				case "D6.3":
					reply=Activator.i_D6_3.getMessage();
					break;
				default:
					reply="No message found";
				}
			}
			
		}
		
		if (reply=="")
			reply="Empty Message";
		return reply;
		
		//	scaller.getIndividualSet(actions[1]);
		//if (actions[0].equals("addIndividual"))
		//	scaller.addIndividual(actions[1], actions[2]);

	}

	protected SocketPublisher(ModuleContext context) {
		super(context, getProviderInfo());
		mycontext = context;
		System.out.println("Crea il server");
		System.out.flush();

		// TODO Auto-generated constructor stub
	}

	private static ContextProvider getProviderInfo() {
		ContextProvider myContextProvider = new ContextProvider(CONTEXT_PROVIDER);
		myContextProvider.setType(ContextProviderType.gauge);

		myContextProvider.setProvidedEvents(new ContextEventPattern[] { new ContextEventPattern() });
		return myContextProvider;
	}

	public void startServer() {
		SensorServer server = new SensorServer(this);

		Thread thserver = new Thread(server);
		Thread th = new Thread(thserver);
		th.start();
	}

	public void communicationChannelBroken() {
		// TODO Auto-generated method stub

	}

}
