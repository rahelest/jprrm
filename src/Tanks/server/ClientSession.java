package Tanks.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Tanks.shared.CommunicationBuffer;
import Tanks.shared.Message;
import Tanks.shared.Receiver;
import Tanks.shared.gameElements.Tank;
import Tanks.shared.mapElements.GameObject;

public class ClientSession extends Thread {
	private int clientID;
	private Socket sock;
	private ObjectOutputStream netOut;
	private GameObject tank;
	private int tankspeed = 1;
	private int missilespeed = 1;
	private int exp = 0;
	Receiver receiver;
	
	public ClientSession(Socket sock, CommunicationBuffer inbound, int clientID) throws IOException {
		this.clientID = clientID;
		this.sock = sock;		
		netOut = new ObjectOutputStream(sock.getOutputStream());       
		// Kui voogude loomine ebaõnnestub, peab väljakutsuv meetod 
		// sokli sulgema. Kui lõim läks käima, vastutab lõim selle eest
		receiver =  new Receiver(sock, inbound);
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
		tank = new Tank("T" + (Integer.toString(clientID)), 100, 100);
		sendMessage(new Message(clientID));
		while(true) {
			
		}
	}
	
}
