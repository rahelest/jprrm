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
	private int tankspeed = 1;
	private int missilespeed = 1;
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
		tank = ObjectFactory.spawnTank(map, key);
		map.addObject(tank);
		sendMessage(new Message(clientID));
		sendMessage(new Message(map));
		while(true) {
			Message temp = inBuff.getMessage();
			System.out.println("TEADE!");
			GameObject tempTank = new Tank(tank.getID(), tank.getX(), tank.getY());
			if (temp.extraString.equals("F")) {
				//tulista
				new Missile("M" + clientID, (tank.getX()), (tank.getY()), ((Tank)tank).getDirection() , missilespeed);
			} else if (temp.extraString.equals("N")) {
				//liigu põhja
				tempTank.setLocation(tank.getX(), tank.getY() - tankspeed);
				((Tank) tempTank).setDirection("N");
				tempTank.setSize(30, 60);
				tempTank.setImage("tankNORTH.png");
			} else if (temp.extraString.equals("S")) {
				//liigu lõunasse
				tempTank.setLocation(tank.getX(), tank.getY() + tankspeed);
				((Tank) tempTank).setDirection("S");
				tempTank.setSize(30, 60);
				tempTank.setImage("tankSOUTH.png");
			} else if (temp.extraString.equals("W")) {
				//liigu läände
				tempTank.setLocation(tank.getX() - tankspeed, tank.getY());
				((Tank) tempTank).setDirection("W");
				tempTank.setSize(60, 30);
				tempTank.setImage("tankWEST.png");
			} else if (temp.extraString.equals("E")) {
				//liigu itta
				tempTank.setLocation(tank.getX() + tankspeed, tank.getY());
				((Tank) tempTank).setDirection("E");
				tempTank.setSize(60, 30);
				tempTank.setImage("tankEAST.png");
			}
			if (!tempTank.checkCollision(map)) {
				tank.setLocation(tempTank.getX(), tempTank.getY());
				tank.setSize(tempTank.getWidth(), tempTank.getHeight());
				tank.setImage(tempTank.getImage());
			}
			map.doYourStuff(tank);
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
	
	public Object getClientMonitor() {
		System.out.println("Küsitakse monitori");
		return clientMonitor;
	}
	
}
