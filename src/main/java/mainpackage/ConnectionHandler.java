package mainpackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionHandler implements Runnable {

	private Socket theclient;
	ServerSocket   serverSocket = null;
	public boolean killme       = false;
	public String  code         = "no";

	private static ScktServer scktServ = null;
	public  static BufferedWriter out;

	public ConnectionHandler(Socket theclient, ScktServer scktServer){
		this.theclient = theclient;
		scktServ = scktServer;
		//Thread t = new Thread(this);
		//t.start();
	}

	public static void sendSubscriptionMessage(String msg) throws IOException{

		if (!scktServ.killme) {
			out.write(msg);
			out.newLine();
			out.flush();
		}
	}

	public static void sendServiceRequestToClient(String msg) throws IOException{

		Component.ontology_updated = false;

		if (!scktServ.killme) {
			out.write(msg);
			out.newLine();
			out.flush();
		}

		String[] request_message = msg.split("#");
		Component.message_datatype_requested = request_message[1];

		// : Wait for the client to provide the message and for the server to update the ontology
		while(!Component.ontology_updated){}

		Component.ontology_updated = false;

	}
	
	public void run(){
		if (!scktServ.killme){
			System.out.println(Component.component_ID + " CONNECTION HANDLER: " + "Running...\n");
			try{
				BufferedReader in = new BufferedReader(new InputStreamReader(theclient.getInputStream()));
				out = new BufferedWriter(new OutputStreamWriter(theclient.getOutputStream()));

				String inputLine;

				while (true){ //( != null && !killme && scktServ!=null)

					inputLine = in.readLine();
					String check;
					System.out.println(Component.component_ID + " CONNECTION HANDLER: " + "Input message from client to server < " + inputLine + "\n");
					
					if(scktServ!=null){

						check = scktServ.publishMsg(inputLine);
						if (!(check.equals("")) && !(check.equals("empty"))){
							out.write(check);
							out.newLine();
		                	out.flush();
						}
					}

					if (inputLine.equals("Bye"))
						break;
					
					Thread.sleep(2000);
				}
				/*System.out.println("Close connection");
				in.close();
				theclient.close();
				if (serverSocket != null)
					serverSocket.close();*/

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				theclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
