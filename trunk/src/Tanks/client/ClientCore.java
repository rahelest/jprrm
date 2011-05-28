package Tanks.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import Tanks.shared.*;

public class ClientCore {

	private ClientGUI gui;
	private Socket sock;
	private int connectionTries = 0;
	private CommunicationBuffer inBuf;
	private CommunicationBuffer outBuf;
	private Broadcaster broadcaster;
	private Receiver receiver;
	private ObjectOutputStream netOut;
	private int myID = -1;

	public ClientCore() {
		gui = new ClientGUI(this);	
	}

	
	public void startGame() {
		
		
		try {
			inBuf = new CommunicationBuffer();
			receiver = new Receiver(sock, inBuf);
			outBuf = new CommunicationBuffer();
			netOut = new ObjectOutputStream(sock.getOutputStream());
			Message message = new Message();
			message.extraString = "Hi!";
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
					System.out.println(myID);
					gui.play();
				}
				
			} catch (NumberFormatException e) {
				//siis ei ole int
				if (message.extraString == null) {
					//siis on object määratud
					
					gui.drawObject(message.object);
				}
			}
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
		
}
