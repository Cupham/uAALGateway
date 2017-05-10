package mainpackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionHandler implements Runnable
{
	private Socket theclient;
	ServerSocket serverSocket = null;
	static SensorServer sensServ = null;
	public boolean killme = false;
	public String code = "no";
	public static BufferedWriter out;

	public ConnectionHandler(Socket theclient, SensorServer sensorServer)
	{
		this.theclient = theclient;
		sensServ = sensorServer;
		//Thread t = new Thread(this);
		//t.start();
	}

	public static void send(String message_type_id, String msg) throws IOException
	{
		if (!sensServ.killme)
		{
		String message_to_send = "subscription#" + message_type_id + "#" + msg;
		out.write(message_to_send); 
		out.newLine(); 
        out.flush();
		}
	}
	
	
	public void run()
	{
		if (!sensServ.killme)
		{
			System.out.println("run");
			try
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(
						theclient.getInputStream()));
				out = new BufferedWriter(new OutputStreamWriter(
								theclient.getOutputStream()));

				String inputLine;

				while (true)//( != null && !killme && sensServ!=null)
				{
					inputLine = in.readLine();
					String check;
					System.out.println("Line " + inputLine);
					
					if(sensServ!=null)
						{
						check = sensServ.publishMsg(inputLine);
						if ((check!="") && (check!="empty"))
						{out.write(check); 
						out.newLine(); 
		                out.flush();}
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

			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			try
			{
				theclient.close();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
