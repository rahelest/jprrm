package Tanks.shared;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Receiver extends Thread {
	
	private Socket sock;
	private ObjectInputStream netIn;
	CommunicationBuffer in;
	
	public Receiver(Socket sock, CommunicationBuffer inbound) throws IOException {
		this.sock = sock;
		this.in = inbound;
		netIn = new ObjectInputStream(sock.getInputStream());
		start();
	}
	
	public void run() {
		while (true) {
			Message fromClient = null;
			try {
				fromClient = (Message) netIn.readObject();
				
			} catch (IOException e) {
				System.out.println("General IO error, there's noone to complain to!");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println("Class not found, contact Your reseller and complain!");
				e.printStackTrace();
			}
			
			if (fromClient != null) {
				in.addMessage(fromClient);
			}
		}
		
	}
}
