package Tanks.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import Tanks.shared.*;

/**
 * The core application for the client. Manages 
 * receiving and sending messages.
 * @author JPRRM
 *
 */
public class ClientCore extends Thread implements ConnectionManage {

	/**
	 * The gui pointer where to send drawing
	 * instructions.
	 */
	private ClientGUI gui;
	/**
	 * The socket variable for the connection.
	 */
	private Socket sock;
	/**
	 * The server name field for the connection.
	 */
	private String serverName;
	/** 
	 * The port number flied for the connection.
	 */
	private int port;
	/**
	 * The number that provides the number of
	 * tries to connect to the server.
	 */
	private int connectionTries = 0;
	/**
	 * The field for the inbound buffer. 
	 */
	private CommunicationBuffer inBuf;
	/**
	 * The outbound buffer stream for
	 * sending messages.
	 */
	private ObjectOutputStream netOut;
	private Receiver receiver;
	/**
	 * The new GameMap object, initialized
	 * for start of the game before the
	 * first map is receiver from the server.
	 */
	private GameMap map = new GameMap();
	/**
	 * The client's unique ID number.
	 */
	private int myID = -1;
	/**
	 * The ClientCore constructor.
	 */
	public ClientCore() {
		setName("ClientCore");
		gui = new ClientGUI(this);
	}

	/**
	 * The run method of the thread.
	 */
	public final void run() {

		while (true) {
//			System.out.println("Ootan uut teadet");
			Message message = inBuf.getMessage();
//			System.out.println("Sain uue teate!");
			try {
				int number = Integer.parseInt(message.extraString);
				if (myID == -1) {
					myID = number;
					System.out.println("My ID: " + myID);
				}
				
			} catch (NumberFormatException e) {
				if (message.extraString == null) {
					map = message.object;
					if (!gui.isAlive()) {
						gui.start();
					}
				} else if (message.extraString.equals("SC")) {
					gui.scores.setListData(message.scores.values().toArray());
				}
			}
			synchronized (gui) {
				gui.notify();
			}
		}
	}
	
	public void createComms() throws SocketException, IOException {

		inBuf = new CommunicationBuffer();
		netOut = new ObjectOutputStream(sock.getOutputStream());
		netOut.writeObject(new Message("Hi!"));
		receiver = new Receiver(this, sock, inBuf);
	}

		/**
		 * Receives the IP from the text field and
		 * then passes the extracted variables to 
		 * the method that connects to the server.
		 * @param string The tet from the text fielt.
		 * @return Whether the connecting succeeded.
		 */
		public boolean sendIP(String string) {
		String[] splitted = string.split(":");
		if (splitted.length != 2) {
			return false;
		} else {
			serverName = splitted[0];
			try {
				port = Integer.parseInt(splitted[1]);
				if (connectToServer(serverName, port)) {
					return true;
				} else {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}
		}
	}
	
	/**
	 * Tries to connect to the server.
	 * @param serversName The server's name.
	 * @param porty The server's port.
	 * @return Whether the connecting was successful.
	 */
	public boolean connectToServer(String serversName, int porty) {
		try {
			InetAddress serverAddr = InetAddress.getByName(serversName);
			sock = new Socket(serverAddr, porty);
			createComms();
		} catch (ConnectException e) {
//			e.printStackTrace();
			System.out.println("Please check the address!");
			return tryConnecting();
		} catch (UnknownHostException e) {
			gui.enableConnecting();
			System.out.println("There is something wrong with the address");
			return false;
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("IOException!");
			return false;
		}
		start();
		return true;
	}
	
	/**
	 * Manages the several tries of connecting to the server.
	 * @return Whether the tries were successful.
	 */
	private boolean tryConnecting() {
		if (connectionTries < 10) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) { }
			connectionTries++;
			if (connectToServer(serverName, port)) {
				notify();
				return true;
			}
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
		return false;
	}
	
	/**
	 * Sends the message to the server.
	 * @param message The message.
	 */
	public void sendMessage(Message message) {
		try {
			netOut.writeObject(message);
			netOut.flush();
			netOut.reset();
//			System.out.println("Teade teel....");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * The main method.
	 * @param args Nuffin.
	 */
	public static void main(String[] args) {
		new ClientCore();
	}

	/**
	 * Sends the serves the command to move north.
	 */
	public void moveNorth() {
		sendMessage(new Message("N"));
	}

	/**
	 * Sends the serves the command to move south.
	 */
	public void moveSouth() {
		sendMessage(new Message("S"));
	}

	/**
	 * Sends the serves the command to move east.
	 */
	public void moveEast() {
		sendMessage(new Message("E"));
	}

	/**
	 * Sends the serves the command to move west.
	 */
	public void moveWest() {
		sendMessage(new Message("W"));
	}

	/**
	 * Sends the serves the command to fire a missile.
	 */
	public void fire() {
		sendMessage(new Message("F"));
	}

	/**
	 * Method to notify the client when the connection is lost.
	 * @param receiver2 The receiver that gets the exception.
	 */
	public void notifyConnectionLoss(Receiver receiver2) {
		try {
			sock.close();
			
			if (connectToServer(serverName, port)) {
				receiver2.notify();
				sendMessage(new  Message(myID));
			} else {
				gui.enableConnecting();
				//nulli receiver kuidagi
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Gets the map received from the server.
	 * @return The map that contains information
	 * to draw the GUI.
	 */
	public synchronized GameMap getMap() {
		return map;
	}

	/**
	 * Asks for the client's ID.
	 * @return The ID number.
	 */
	public String getMyID() {
		return Integer.toString(myID);
	}
}
