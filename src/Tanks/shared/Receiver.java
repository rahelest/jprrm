package Tanks.shared;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

import Tanks.client.ClientCore;
import Tanks.server.ClientSession;

public class Receiver extends Thread {
	
	private Socket sock;
	private ObjectInputStream netIn;
	CommunicationBuffer in;
	ClientSession session;
	ClientCore clientCore;
	
	private Receiver(Socket sock, CommunicationBuffer inbound) throws IOException {
		this.sock = sock;
		this.in = inbound;
		System.out.println("alustan netIn-i loomist");
		InputStream iS = sock.getInputStream();
		System.out.println("vaheprintout");
		try {
			System.out.println("netIn-i loomise algus");
			netIn = new ObjectInputStream(iS);
		} catch (EOFException e) {
			 
		}
		System.out.println("netIn-i loomine valmis");
		start();
	}
	public Receiver(ClientSession session, Socket sock, CommunicationBuffer inbound) throws IOException {
		this(sock, inbound);
		this.session = session;
	}
	
	public Receiver(ClientCore clientCore, Socket sock, CommunicationBuffer inbound) throws IOException {
		this(sock, inbound);
		this.clientCore = clientCore;
	}
	
	
	public void run() {
		while (true) {
			Message fromClient = null;
			try {
				fromClient = (Message) netIn.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println("Class not found, contact Your reseller and complain!");
				e.printStackTrace();
			} catch (SocketException e) {
				e.printStackTrace();
				if (session != null) {
					try {
						System.out.println("receivery wait algus");
						this.wait();
						System.out.println("receiveri notify lopp");
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalMonitorStateException e2) {
						System.out.println("MONITOREXC");
					}
					session.notifyConnectionLoss();
				} else {
					try {
						clientCore.notifyConnectionLoss(this);
						this.wait();						
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			} catch (IOException e) {
				System.out.println("General IO error, there's noone to complain to!");
				e.printStackTrace();
				
//				try {
//					wait();
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
			}
			
			if (fromClient != null) {
				in.addMessage(fromClient);
			}
		}		

	}

}
