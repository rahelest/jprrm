package Tanks.shared;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Receiver extends Thread {
	
	private Socket sock;
	private ObjectInputStream netIn;
	CommunicationBuffer in;
	
	public Receiver(Socket sock, CommunicationBuffer inbound) throws IOException {
		this.sock = sock;
		this.in = inbound;
		System.out.println("alustan netIn-i loomist");
		InputStream iS = sock.getInputStream();
		System.out.println("vaheprintout");
		try {
			netIn = new ObjectInputStream(iS);
		} catch (EOFException e) {
			 
		}
		System.out.println("netIn-i loomine valmis");
		start();
	}
	
	public void run() {
		while (true) {
			Message fromClient = null;
			try {
				fromClient = (Message) netIn.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println("Class not found, contact Your reseller and complain!");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("General IO error, there's noone to complain to!");
				e.printStackTrace();
			}
			
			if (fromClient != null) {
				in.addMessage(fromClient);
			}
		}		

	}

}
