package Tanks.shared;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

import Tanks.client.ClientCore;
import Tanks.server.ClientSession;

/**
 * The receiver the receives messages.
 * @author JPRRM
 *
 */
public class Receiver extends Thread {
	
	/**
	 * The receiver's socket.
	 */
	private Socket sock;
	/**
	 * The in stream.
	 */
	private ObjectInputStream netIn;
	/**
	 * The in buffer.
	 */
	private CommunicationBuffer in;
	/**
	 * The clientSession pointer.
	 */
	private ClientSession session;
	/**
	 * The clientCore pointer.
	 */
	private ClientCore clientCore;

	/**
	 * The main constructor.
	 * @param socket The socket.
	 * @param inbound The buffer.
	 * @throws IOException The exception.
	 */
	private Receiver(Socket socket, CommunicationBuffer inbound) throws IOException {
		this.setName("Receiver - " + socket.getInetAddress());
		this.sock = socket;
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
	
	/**
	 * The constructor for the ClientSession.
	 * @param nSession The pointer.
	 * @param socket The socket.
	 * @param inbound The buffer.
	 * @throws IOException An exception.
	 */
	public Receiver(ClientSession nSession, Socket socket, CommunicationBuffer inbound) throws IOException {
		this(socket, inbound);
		this.session = nSession;
	}
	
	/**
	 * The constructor for the ClientCore.
	 * @param nClientCore The pointer.
	 * @param socket The socket.
	 * @param inbound The buffer.
	 * @throws IOException An exception.
	 */
	public Receiver(ClientCore nClientCore, Socket socket, CommunicationBuffer inbound) throws IOException {
		this(socket, inbound);
		this.clientCore = nClientCore;
	}
	
	/**
	 * The thread's run method.
	 */
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
						System.out.println("Klient katkes");
						System.out.println("receiveri wait algus");
						synchronized (this) {
							session.notifyConnectionLoss();
							this.wait();
						}
						System.out.println("receiveri notify lopp");
						System.out.println("Receiveri wait lõpp");
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					} catch (IllegalMonitorStateException e2) {
						e2.printStackTrace();
					}
					
				} else {
					try {
						synchronized (this) {
							clientCore.notifyConnectionLoss(this);
							this.wait();
						}
					} catch (InterruptedException e1) {
//						e1.printStackTrace();
						System.out.println("Receiveri wait lõpp");
					}
					
				}
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
