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
	
	private GameMap map;
	private GameObject tank;

	//siia tulevad mängija parameetrid
	private int tankspeed = 1;
	private int missilespeed = 1;
	private int exp = 0;

	
	public ClientSession(Socket sock, CommunicationBuffer inbound, GameMap killingField, int clientID) throws IOException {
		this.clientID = clientID;
		this.sock = sock;
		this.clientIP = sock.getInetAddress();
		this.map = killingField;
		netOut = new ObjectOutputStream(sock.getOutputStream());       
		// Kui voogude loomine ebaõnnestub, peab väljakutsuv meetod 
		// sokli sulgema. Kui lõim läks käima, vastutab lõim selle eest
		
		receiver =  new Receiver(this, sock, inbound);
		inBuff = inbound;
		start();
	}
	
	
	public InetAddress getClientIP() {
		return clientIP;
	}


	public void sendMessage(Message msg) {
		try {
			netOut.writeObject(msg);
			netOut.flush();
			netOut.reset();
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
			if (temp.extraString.equals("F")) {
				//tulista
			} else if (temp.extraString.equals("N")) {
				//liigu põhja
				tank.setLocationY(tank.getLocationY() - tankspeed);
				((Tank) tank).setDirection("N");
			} else if (temp.extraString.equals("S")) {
				//liigu lõunasse
				tank.setLocationY(tank.getLocationY() + tankspeed);
				((Tank) tank).setDirection("S");
			} else if (temp.extraString.equals("W")) {
				//liigu läände
				tank.setLocationX(tank.getLocationX() - tankspeed);
				((Tank) tank).setDirection("W");
			} else if (temp.extraString.equals("E")) {
				//liigu itta
				tank.setLocationX(tank.getLocationX() + tankspeed);
				((Tank) tank).setDirection("E");
			}
			
			map.doYourStuff(tank);
		}
			
			
	}



	public void notifyConnectionLoss(Receiver receiver2) {
		try {
			this.wait();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		receiver.notify();
		
	}
	
}
