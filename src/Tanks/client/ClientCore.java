package Tanks.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
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
			receiver = new Receiver(sock, inBuf);
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
			Message message = inBuf.getMessage();
			try {
				int number = Integer.parseInt(message.extraString);
				if (myID == -1) {
					myID = number;
					System.out.println("My new ID: " + myID);
					gui.play();
				}
				
			} catch (NumberFormatException e) {
				//siis ei ole int
				if (message.extraString == null) {
					//siis on object määratud
					sendForDrawing(message.object);
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
			String serverName = splitted[0];
			try {
				int port = Integer.parseInt(splitted[1]);
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
		} catch (UnknownHostException e) {
			gui.enableConnecting();
			System.out.println("There is something wrong with the address");
			return false;
		} catch (IOException e) {
			if (connectionTries < 3) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) { }
				connectionTries++;
				connectToServer(serverName, port);
				e.printStackTrace();
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
		}
		System.out.println("Connection established!");
		return true;
	}
	
	public static void main(String[] args) {
		new ClientCore();
	}

//	private void replaceAndSend(GameObject tank) {
//		map.doYourStuff(tank);
//		outBuf.addMessage(new Message(map));
//	}

	public void moveNorth() {
		Tank tank = (Tank) map.getObject(Integer.toString(myID));
		tank.setLocationY(tank.getLocationY() - unit);
		tank.setDirection("N");
//		replaceAndSend(tank);
		map.doYourStuff(tank);
		outBuf.addMessage(new Message(map));
	}


	public void moveSouth() {
		Tank tank = (Tank) map.getObject(Integer.toString(myID));
		tank.setLocationY(tank.getLocationY() + unit);
		tank.setDirection("S");
		map.doYourStuff(tank);
		outBuf.addMessage(new Message(map));
		
	}


	public void moveEast() {
		Tank tank = (Tank) map.getObject(Integer.toString(myID));
		tank.setLocationX(tank.getLocationX() + unit);
		tank.setDirection("E");
		map.doYourStuff(tank);
		outBuf.addMessage(new Message(map));
		
	}


	public void moveWest() {
		Tank tank = (Tank) map.getObject(Integer.toString(myID));
		tank.setLocationX(tank.getLocationX() - unit);
		tank.setDirection("W");
		map.doYourStuff(tank);
		outBuf.addMessage(new Message(map)); 
		
	}


	public void fire() {
		outBuf.addMessage(new Message("FIRE!"));
	}
		
}
