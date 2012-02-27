package shared;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import shared.messageTypes.Message;

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
	 * The pointer to the MAKER OF THIS GODDAMN RECEIVER!
	 */
	private ConnectionManage connectionManage;

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
		InputStream iS = sock.getInputStream();
		try {
			netIn = new ObjectInputStream(iS);
		} catch (EOFException e) {
			 
		}
		start();
	}
	
	/**
	 * An alternative overloaded constructor for creating the receiver.
	 * @param conn the creator
	 * @param socket the socket used for comms
	 * @param inbound the inbound buffer
	 * @throws IOException a general error
	 */
	public Receiver(ConnectionManage conn, Socket socket, 
			CommunicationBuffer inbound) throws IOException {
		this(socket, inbound);
		this.connectionManage = conn;
	}
	
	/**
	 * The thread's run method.
	 */
	@SuppressWarnings("deprecation")
	public void run() {
		while (true) {
			Message fromClient = null;
			try {
				fromClient = (Message) netIn.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println("Class not found, contact Your reseller and complain!");
			} catch (SocketException e) {			
				try {
					System.out.println("The connection was lost");
					this.stop();
					synchronized (this) {
						System.out.println(connectionManage);
						connectionManage.notifyConnectionLoss(this);
						this.wait();
					}
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			} catch (IOException e) {
				System.out.println("A general IO error ocurred, there's noone to complain to!");
			}
			
			if (fromClient != null) {
				in.addMessage(fromClient);
			}
		}
	}
}
