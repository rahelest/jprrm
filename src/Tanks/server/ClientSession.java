package Tanks.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
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
	private GameMap map;
	private GameObject tank;
	/*
	//siia tulevad mängija parameetrid
	private int tankspeed = 1;
	private int missilespeed = 1;
	private int exp = 0;
	*/
	
	public ClientSession(Socket sock, CommunicationBuffer inbound, GameMap killingField, int clientID) throws IOException {
		this.clientID = clientID;
		this.sock = sock;
		this.map = killingField;
		netOut = new ObjectOutputStream(sock.getOutputStream());       
		// Kui voogude loomine ebaõnnestub, peab väljakutsuv meetod 
		// sokli sulgema. Kui lõim läks käima, vastutab lõim selle eest
		
		receiver =  new Receiver(sock, inbound);
		inBuff = inbound;
		start();
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
		while(true) {
			Message temp = inBuff.getMessage();
			tank = temp.object.getObject(key);
			map.doYourStuff(tank);
			//siia tuleb mürsukontroll
			
		}
	}
	
}
