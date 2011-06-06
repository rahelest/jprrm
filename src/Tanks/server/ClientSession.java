package Tanks.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import Tanks.shared.CommunicationBuffer;
import Tanks.shared.GameMap;
import Tanks.shared.Message;
import Tanks.shared.Receiver;
import Tanks.shared.gameElements.Missile;
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
	private int tankSpeed = 1;
	private int missileSpeed = 1;
	private int exp = 0;

	
	public ClientSession(Socket sock, GameMap killingField, int clientID) throws IOException{
		this.clientID = clientID;
		this.sock = sock;
		this.clientIP = sock.getInetAddress();
		this.map = killingField;
		createComms();
		this.setName("ClientSession - " + clientID + " " + clientIP);
		start();
	}
	
	public void run() {
		String key = "T" + (Integer.toString(clientID));
		tank = ObjectFactory.spawnTank(map, key);
		map.addObject(tank);
		sendMessage(new Message(clientID));
		sendMessage(new Message(map));
		while(true) {
			Message temp = inBuff.getMessage();
//			System.out.println("TEADE!");
			Tank tempTank = new Tank(tank.getID(), tank.getX(), tank.getY());
			if (temp.extraString.equals("F")) {
				MissileMover.newMissile(this);
			} else if (temp.extraString.equals("N")) {
				//liigu põhja
				tempTank.setLocation(tank.getX(), tank.getY() - tankSpeed);
				tempTank.setDirection("N");
				tempTank.setSize(30, 60);
				tempTank.setImage("tankN.png");
			} else if (temp.extraString.equals("S")) {
				//liigu lõunasse
				tempTank.setLocation(tank.getX(), tank.getY() + tankSpeed);
				tempTank.setDirection("S");
				tempTank.setSize(30, 60);
				tempTank.setImage("tankS.png");
			} else if (temp.extraString.equals("W")) {
				//liigu läände
				tempTank.setLocation(tank.getX() - tankSpeed, tank.getY());
				tempTank.setDirection("W");
				tempTank.setSize(60, 30);
				tempTank.setImage("tankW.png");
			} else if (temp.extraString.equals("E")) {
				//liigu itta
				tempTank.setLocation(tank.getX() + tankSpeed, tank.getY());
				tempTank.setDirection("E");
				tempTank.setSize(60, 30);
				tempTank.setImage("tankE.png");
			}
			if (tempTank.checkCollision(map) == null) {
				tank.setLocation(tempTank.getX(), tempTank.getY());
				tank.setSize(tempTank.getWidth(), tempTank.getHeight());
				tank.setImage(tempTank.getImage());
			}
			map.getObject().putAll(MissileMover.getMissiles());
			map.doYourStuff(tank);
		}			
	}
	
	public void updateOnReconnect(Socket sock) throws IOException{
		this.sock = sock;
		createComms();
	}
	
	private void createComms() throws IOException {
		netOut = new ObjectOutputStream(sock.getOutputStream());       
		inBuff = new CommunicationBuffer();
		receiver =  new Receiver(this, sock, inBuff);
	}
	
	public InetAddress getClientIP() {
		return clientIP;
	}

	public void sendMessage(Message msg) {
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

	public void notifyConnectionLoss() {
		try {
			System.out.println("clientsessioni wait algus");
			synchronized(this) {
				this.wait();
				System.out.println("proov clisess wait järel");
			}
			System.out.println("clientsessioni wait lopp");
			System.out.println("receiveri notify algus");
			synchronized (receiver) {
				receiver.notify();
				System.out.println("prooviprint receiverlock");
			}
			System.out.println("saadan äratusteate kliendile");
			sendMessage(new Message(clientID));
			sendMessage(new Message(map));
			System.out.println("receiveri notify lopp");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IllegalMonitorStateException e) {
//			System.out.println("MONITOREXC");
			e.printStackTrace();
		}
	}
	
	public synchronized Tank getTank() {
		return (Tank) tank;
	}
	
	public synchronized GameMap getMap() {
		return map;
	}
	
	public synchronized int getMissileSpeed() {
		return missileSpeed;
	}
}
