package Tanks.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Set;

import Tanks.shared.*;
import Tanks.shared.gameElements.Tank;
import Tanks.shared.mapElements.GameObject;

public class ClientCore {

	private ClientGUI gui;
	private Socket sock;
	private String serverName;
	private int port;
	private int connectionTries = 0;
	private CommunicationBuffer inBuf;
	private CommunicationBuffer outBuf;
	private Broadcaster broadcaster;
	private Receiver receiver;
	private ObjectOutputStream netOut;
	private GameMap map;
	private int myID = -1;
	private int unit = 10;

	public ClientCore() {
		gui = new ClientGUI(this);	
	}

	
	public void startGame() {
		
		
		try {
			inBuf = new CommunicationBuffer();
			receiver = new Receiver(this, sock, inBuf);
			outBuf = new CommunicationBuffer();
			netOut = new ObjectOutputStream(sock.getOutputStream());
			Message message = new Message("Hi!");
			netOut.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//küsi kliente, stardi broadcaster
		//anna guile vastuvõtja viide
		while (true) {
			System.out.println("Ootan uut teadet");
			Message message = inBuf.getMessage();
			System.out.println("Sain uue teate!");
			try {
				int number = Integer.parseInt(message.extraString);
				if (myID == -1) {
					myID = number;
					System.out.println("My new ID: " + myID);
				}
				
			} catch (NumberFormatException e) {
				if (message.extraString == null) { 
					map = message.object;
					System.out.println(map);
					sendForDrawing(map);
				}
			}
		}
	}
	
	public void sendForDrawing(GameMap map) {
		HashMap<String, GameObject> objects = map.getObject();
		Set<String> keys = objects.keySet();
		for (String k : keys) {
			gui.drawObject(objects.get(k));
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
			e.printStackTrace();
			return tryConnecting();
		} catch (UnknownHostException e) {
			gui.enableConnecting();
			System.out.println("There is something wrong with the address");
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return tryConnecting();
		}
		return true;
	}
	
	private boolean tryConnecting() {
		if (connectionTries < 10) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) { }
			connectionTries++;
			if (connectToServer(serverName, port)) {
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
	
	public static void main(String[] args) {
		new ClientCore();
	}

	public void moveNorth() {
		outBuf.addMessage(new Message("N"));
	}


	public void moveSouth() {
		outBuf.addMessage(new Message("S"));
	}


	public void moveEast() {
		outBuf.addMessage(new Message("E"));
	}


	public void moveWest() {
		outBuf.addMessage(new Message("W"));
	}


	public void fire() {
		outBuf.addMessage(new Message("F"));
	}


	public void notifyConnectionLoss(Receiver receiver2) {
		try {
			sock.close();
			
			if (connectToServer(serverName, port)) {
//				receiver2.notify();
				outBuf.addMessage(new  Message(myID));
			} else {
				gui.enableConnecting();
				//nulli receiver kuidagi
			}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
}
