package Tanks.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import Tanks.shared.*;

public class ClientCore extends Thread {

	private ClientGUI gui;
	private Socket sock;
	private String serverName;
	private int port;
	private int connectionTries = 0;
	private CommunicationBuffer inBuf;
	private ObjectOutputStream netOut;
	private GameMap map = new GameMap();
	private int myID = -1;
	
	public ClientCore() {
		setName("ClientCore");
		gui = new ClientGUI(this);
	}

	
	public void run() {		
		
		try {
			inBuf = new CommunicationBuffer();
			new Receiver(this, sock, inBuf);
			netOut = new ObjectOutputStream(sock.getOutputStream());
			netOut.writeObject(new Message("Hi!"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//küsi kliente, stardi broadcaster
		//anna guile vastuvõtja viide
		while (true) {
//			System.out.println("Ootan uut teadet");
			Message message = inBuf.getMessage();
//			System.out.println("Sain uue teate!");
			try {
				int number = Integer.parseInt(message.extraString);
				if (myID == -1) {
					myID = number;
					System.out.println("My new ID: " + myID);
				}
				
			} catch (NumberFormatException e) {
				if (message.extraString == null) { 
					map = message.object;
//					System.out.println(map);
					}
			}
			synchronized(gui) {
				gui.notify();
			}
		}
	}


	public boolean sendIP(String string) {
		String[] splitted = string.split(":");
		if (splitted.length != 2) return false;
		else {
			serverName = splitted[0];
			try {
				port = Integer.parseInt(splitted[1]);
				if (connectToServer(serverName, port)) {
					//startgame-----------------------------------------------------------------
					return true;
				} else {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}
		}
	}
		
	public boolean connectToServer(String serverName, int port) {
		try {
			InetAddress serverAddr = InetAddress.getByName(serverName);
			sock = new Socket(serverAddr, port);
			
		} catch (ConnectException e) {
//			e.printStackTrace();
			System.out.println("ConnectException!");
			return tryConnecting();
		} catch (UnknownHostException e) {
			gui.enableConnecting();
			System.out.println("There is something wrong with the address");
			return false;
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("IOException!");
			return tryConnecting();
		}
		start();
		return true;
	}
	
	private boolean tryConnecting() {
		if (connectionTries < 10) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) { }
			connectionTries++;
			if (connectToServer(serverName, port)) {
				notify();
				return true;
			}
		} else {
			connectionTries = 0;
			System.out.println("Connecting failed");
			try {
				sock.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return false;
			}
		return false;
	}
	
	private void sendMessage(Message message) {
		try {
			netOut.writeObject(message);
			netOut.flush();
			netOut.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new ClientCore();
	}

	public void moveNorth() {
		sendMessage(new Message("N"));
	}


	public void moveSouth() {
		sendMessage(new Message("S"));
	}


	public void moveEast() {
		sendMessage(new Message("E"));
	}


	public void moveWest() {
		sendMessage(new Message("W"));
	}


	public void fire() {
		sendMessage(new Message("F"));
	}


	public void notifyConnectionLoss(Receiver receiver2) {
		try {
			sock.close();
			
			if (connectToServer(serverName, port)) {
				receiver2.notify();
				sendMessage(new  Message(myID));
			} else {
				gui.enableConnecting();
				//nulli receiver kuidagi
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	public synchronized GameMap getMap() {
		return map;
	}


	public Object getMyID() {
		return myID;
	}
		
}
