package Tanks.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import Tanks.shared.CommunicationBuffer;
import Tanks.shared.GameMap;
import Tanks.shared.Message;
import Tanks.shared.Receiver;
import Tanks.shared.gameElements.Tank;
import Tanks.shared.mapElements.GameObject;

public class ClientSession extends Thread {
	private int clientID;
	private Socket sock;
	private ObjectOutputStream netOut;
	private Receiver receiver;
	private CommunicationBuffer inBuff;
	private InetAddress clientIP;
	private Object clientMonitor = new Object();
	private Object senderLock = new Object();
	
	private GameMap map;
	private GameObject tank;

	//siia tulevad mängija parameetrid
	private int tankspeed = 1;
	private int missilespeed = 1;
	private int exp = 0;

	
	public ClientSession(Socket sock, GameMap killingField, int clientID) throws IOException {
		this.setName("ClientSession - " + clientID + " " + clientIP);
		this.clientID = clientID;
		this.sock = sock;
		this.clientIP = sock.getInetAddress();
		this.map = killingField;
		netOut = new ObjectOutputStream(sock.getOutputStream());       
		// Kui voogude loomine ebaõnnestub, peab väljakutsuv meetod 
		// sokli sulgema. Kui lõim läks käima, vastutab lõim selle eest
		inBuff = new CommunicationBuffer();
		receiver =  new Receiver(this, sock, inBuff);
		
		start();
	}
	
	
	public InetAddress getClientIP() {
		return clientIP;
	}


	public synchronized void sendMessage(Message msg) {
		try {
			synchronized (senderLock) {
				netOut.writeObject(msg);
				netOut.flush();
				netOut.reset();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	public void run() {
		String key = "T" + (Integer.toString(clientID));
		tank = new Tank(key , 100, 100);
		map.addObject(tank);
		sendMessage(new Message(clientID));
		sendMessage(new Message(map));
		while(true) {
			Message temp = inBuff.getMessage();
//			System.out.println("uus serveriteade");
			if (temp.extraString.equals("F")) {
				//tulista
			} else if (temp.extraString.equals("N")) {
				//liigu põhja
				tank.setLocation(tank.getX(), tank.getY() - tankspeed);
				((Tank) tank).setDirection("N");

			} else if (temp.extraString.equals("S")) {
				//liigu lõunasse
				tank.setLocation(tank.getX(), tank.getY() + tankspeed);
				((Tank) tank).setDirection("S");
			} else if (temp.extraString.equals("W")) {
				//liigu läände
				tank.setLocation(tank.getX() + tankspeed, tank.getY());
				((Tank) tank).setDirection("W");
			} else if (temp.extraString.equals("E")) {
				//liigu itta
				tank.setLocation(tank.getX() - tankspeed, tank.getY());
				((Tank) tank).setDirection("E");
			}
			
			map.doYourStuff(tank);
		}
			
			
	}

	public synchronized void notifyConnectionLoss() {
		try {
			System.out.println("clientsessioni wait algus");
			synchronized(clientMonitor) {
//				receiver.wait();
				this.wait();
			}
			System.out.println("clientsessioni wait lopp");
		} catch (InterruptedException e) {
			System.out.println("receiveri notify algus");
			synchronized (receiver.getMonitor()) {
				receiver.notify();
			}
			System.out.println("saadan äratusteate kliendile");
			sendMessage(new Message(clientID));
			System.out.println("receiveri notify lopp");
			e.printStackTrace();
		} catch (IllegalMonitorStateException e) {
//			System.out.println("MONITOREXC");
			e.printStackTrace();
		}
	}
	
	public Object getClientMonitor() {
		System.out.println("Küsitakse monitori");
		return clientMonitor;
	}
	
}
