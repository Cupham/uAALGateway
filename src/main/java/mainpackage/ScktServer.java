package mainpackage;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ScktServer implements Runnable {

	private ServerSocket server;
	private int port = 12345;
	ArrayList<String> msgs;
	boolean killme = false;
	ArrayList<Thread> thcons = new ArrayList<Thread>();
	ArrayList<ConnectionHandler> conhs = new ArrayList<ConnectionHandler>();

	SocketPublisher myPublisher;
	
	public ScktServer(SocketPublisher mypub) {
		System.out.println(Component.component_ID + " SCKT SERVER: " + "Creating the socket\n"); System.out.flush();
		myPublisher=mypub;
		msgs = new ArrayList<String>();
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ScktServer() {
		System.out.println(Component.component_ID + " SCKT SERVER: " + "Creating the socket\n"); System.out.flush();
		//myPublisher=mypub;
		msgs = new ArrayList<String>();
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		System.out.println(Component.component_ID + " SCKT SERVER: " + "Waiting for client message...\n");
		System.out.flush();

		while (!killme) {
			try {
				Socket socket = server.accept();
				
				ConnectionHandler cd = new ConnectionHandler(socket, this);
				conhs.add(cd);
				Thread th = new Thread(cd);
				th.start();
				thcons.add(th);
				System.out.println(Component.component_ID + " SCKT SERVER: " + "New Connection\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(ConnectionHandler ch : conhs)
			ch.killme = true;
		for(Thread th: thcons)
			th.stop();
	}

	public ArrayList getMessageList() {
		// TODO Auto-generated method stub
		return msgs;
	}
	
	public void addMessage(String msg){
		msgs.add(msg);
	}
	
	public void clearMessageList(){
		
		msgs.clear();
		System.out.println("msg clear " + msgs.size());
	}

	public void kill(){
		killme=true;
	}
	
	public void setCode(String code) {
		System.out.println(Component.component_ID + " SCKT SERVER: " + "Send code ");
		for(ConnectionHandler ch : conhs)
			ch.code = code;
	}
	
	public String publishMsg(String msg) {
		return myPublisher.publishCESocketMessage(msg);
	}

}
