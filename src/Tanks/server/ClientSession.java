package Tanks.server;

import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

import Tanks.shared.CommunicationBuffer;
import Tanks.shared.ConnectionManage;
import Tanks.shared.GameMap;
import Tanks.shared.Receiver;
import Tanks.shared.gameElements.Tank;
import Tanks.shared.mapElements.GameObject;
import Tanks.shared.messageTypes.*;

/**
 * The client maganging thread.
 * @author JPRRM
 *
 */
public class ClientSession extends Thread implements ConnectionManage {
	
	/**
	 * The client's ID.
	 */
	private int clientID;
	/**
	 * The client's socket.
	 */
	private Socket sock;
	/**
	 * The out stream.
	 */
	private ObjectOutputStream netOut;
	/**
	 * The receiver's pointer.
	 */
	private Receiver receiver;
	/**
	 * The in buffer.
	 */
	private CommunicationBuffer inBuff;
	/**
	 * The client's IP address.
	 */
	private InetAddress clientIP;
	/**
	 * The lock for the sender.
	 */
	private Object senderLock = new Object();
	/**
	 * The map pointer.
	 */
	private GameMap map;
	/**
	 * The client's tank pointer.
	 */
	private GameObject tank;

	/**
	 * The tank's speed.
	 */
	private int tankSpeed = 5;
	/**
	 * The missile's speed.
	 */
	private int missileSpeed = 1;
	/**
	 * The experience gained.
	 */
	private int exp = 0;
	
	/**
	 * This boolean is used to stop the main while-cycle.
	 */
	private Boolean run = true;
	/**
	 * This is a helper string to hold the player's ID.
	 */
	private String key;
	
	/**
	 * This is the dimension of vertically aligned tank.
	 */
	private Dimension tankV = new Dimension(30, 60);
	/**
	 * This is the dimension of horizontally aligned tank.
	 */
	private Dimension tankH = new Dimension(60, 30);
	
	/**
	 * The constructor.
	 * @param nSock The socket.
	 * @param killingField The map.
	 * @param nClientID The ID.
	 * @throws IOException An exception.
	 */
	public ClientSession(Socket nSock, GameMap killingField, int nClientID) throws IOException {
		this.clientID = nClientID;
		this.sock = nSock;
		this.clientIP = nSock.getInetAddress();
		this.map = killingField;
		createComms();
		this.setName("ClientSession - " + clientID + " " + clientIP);
		key = "T" + (Integer.toString(clientID));
		start();
	}
	
	/**
	 * The thread's run method.
	 */
	public void run() {
		map = ObjectFactory.spawnTank(map, key, this);
		tank = map.getBreakable("T" + Integer.toString(clientID));
		sendMessage(new CommandMessage(clientID));
		sendMessage(new DecorObjectsMessage(map));
		sendMessage(new MovablesMessage(map.getBreakables()));
		while (run) {
//			System.out.println("While algus! Image: " + ((Tank) tank).getImage());
			CommandMessage temp = (CommandMessage) inBuff.getMessage();
//			System.out.println("TEADE!");
			Tank tempTank = new Tank(this, tank.getID(), tank.getX(), tank.getY());
			tempTank.setDirection(((Tank) tank).getDirection());
			if (temp.extraString.equals("F")) {
//				System.out.println("Fire! Image: " + ((Tank) tank).getImage());
				MissileMover.newMissile(this);
//				System.out.println("Afterfire! Image: " + ((Tank) tank).getImage());
			} else if (temp.extraString.equals("N")) {
				//liigu põhja
				tempTank.setLocation(tank.getX(), tank.getY() - tankSpeed);
				tempTank.setDirection("N");
				tempTank.setSize(tankV);
			} else if (temp.extraString.equals("S")) {
				//liigu lõunasse
				tempTank.setLocation(tank.getX(), tank.getY() + tankSpeed);
				tempTank.setDirection("S");
				tempTank.setSize(tankV);
			} else if (temp.extraString.equals("W")) {
				//liigu läände
				tempTank.setLocation(tank.getX() - tankSpeed, tank.getY());
				tempTank.setDirection("W");
				tempTank.setSize(tankH);
			} else if (temp.extraString.equals("E")) {
				//liigu itta
				tempTank.setLocation(tank.getX() + tankSpeed, tank.getY());
				tempTank.setDirection("E");
				tempTank.setSize(tankH);
			}
			if (tempTank.checkCollision(map) == null) {
//				System.out.println("Checkcollision!! Image: " + ((Tank) tank).getImage());
				tank.setLocation(tempTank.getX(), tempTank.getY());
				tank.setSize(tempTank.getWidth(), tempTank.getHeight());
//				tank.setImage(tempTank.getImage());
				((Tank) tank).setDirection(tempTank.getDirection());
//				System.out.println("Pärast setdirectioni! Image: " + ((Tank) tank).getImage());
			}
//			System.out.println("Enne addmisilesi! Image: " + ((Tank) tank).getImage());
			map.addMissiles(MissileMover.getMissiles());
//			System.out.println("Ennedoyourstuffi! Image: " + ((Tank) tank).getImage());
			map.doYourStuff(tank);
		}			
	}
	
	/**
	 * Renews the socket field.
	 * @param nSock The field.
	 * @throws IOException An exception.
	 */
	public void updateOnReconnect(Socket nSock) throws IOException{
		this.sock = nSock;
		createComms();
	}
	
	/**
	 * Creates the important streams.
	 * @throws IOException An exception.
	 */
	public void createComms() throws IOException {
		netOut = new ObjectOutputStream(sock.getOutputStream());       
		inBuff = new CommunicationBuffer();
		receiver =  new Receiver(this, sock, inBuff);
	}
	
	/**
	 * Returns the clientIP.
	 * @return The IP.
	 */
	public InetAddress getClientIP() {
		return clientIP;
	}

	/**
	 * @return the clientID
	 */
	public int getClientID() {
		return clientID;
	}

	/**
	 * Sends a new message.
	 * @param msg The message.
	 */
	@SuppressWarnings("deprecation")
	public void sendMessage(Message msg) {
		try {
			synchronized (senderLock) {
				netOut.writeObject(msg);
				netOut.flush();
//				System.out.println("Teade teel " + msg);
				netOut.reset();
			}
		} catch (SocketException e) {
			System.out.println("A client was unreachable,"
					+ " removing it from the list!");
			map.removeBreakable("T" + clientID);
			this.stop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 	

	/**
	 * What to do when connection is lost.
	 * @param receiver2 The reciver that gives the error.
	 */
	public void notifyConnectionLoss(Receiver receiver2) {
		try {
//			System.out.println("clientsessioni wait algus");
			synchronized (this) {
				this.wait();
//				System.out.println("proov clisess wait järel");
			}
//			System.out.println("clientsessioni wait lopp");
//			System.out.println("receiveri notify algus");
			synchronized (receiver) {
				receiver.notify();
				run = false;
//				System.out.println("prooviprint receiverlock");
			}
//			System.out.println("saadan äratusteate kliendile");
			sendMessage(new CommandMessage(clientID));
			sendMessage(new MovablesMessage(map.getBreakables()));
//			System.out.println("receiveri notify lopp");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IllegalMonitorStateException e) {
//			System.out.println("MONITOREXC");
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the tank.
	 * @return The tank.
	 */
	public synchronized Tank getTank() {
		return (Tank) tank;
	}
	
	/**
	 * Returns the map.
	 * @return The map.
	 */
	public synchronized GameMap getMap() {
		return map;
	}
	
	/**
	 * Returns the missile speed.
	 * @return The missile speed.
	 */
	public synchronized int getMissileSpeed() {
		return missileSpeed;
	}

	/**
	 * Reset the client's parametres.
	 */
	public void reStart(GameMap newMap) {
		map = newMap;
		map = ObjectFactory.spawnTank(map, key, this);
		exp = 0;
		tankSpeed = 5;
		missileSpeed = 1;
//		sendMessage(new Message("RESET"));
	}

	/**
	 * @param tankSpeed the tankSpeed to set
	 */
	public void setTankSpeed(int tankSpeed) {
		this.tankSpeed = tankSpeed;
	}

	/**
	 * @param missileSpeed the missileSpeed to set
	 */
	public void setMissileSpeed(int missileSpeed) {
		this.missileSpeed = missileSpeed;
	}

	/**
	 * @return the exp
	 */
	public int getExp() {
		return exp;
	}

	/**
	 * @param exp the exp to set
	 */
	public void increaseExp() {
		this.exp++;
	}

	public void gotHit() {
		map.removeBreakable("T" + clientID);
		map = ObjectFactory.spawnTank(map, "T" + clientID, this);
		tank = map.getBreakable("T" + clientID);
	}
}
