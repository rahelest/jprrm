package Tanks.shared;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import Tanks.server.ActiveClients;
import Tanks.server.ClientSession;

/**
 * The class that sends messages to everyone.
 * @author JPRRM
 * 
 */
public class Broadcaster extends Thread {
	
	private long lastScoreUpdate;
	private final long scoreUpdateInterval = 1000;
	/**
	 * The outbound buffer where the messages are taken from.
	 */
	private CommunicationBuffer out = new CommunicationBuffer();
	/**
	 * The pointer to the class with the active clients.
	 */
	private ActiveClients activeClients = null;
	/**
	 * A lock for synchronizing the sending.
	 */
	private Object sendLock = new Object();
	
	/**
	 * The constructor.
	 * @param pointer Pointer for the client list holder class.
	 */
	public Broadcaster(ActiveClients pointer) {
		this.setName("Broadcaster - " + pointer.toString());
		activeClients = pointer;
		start();
	}
	
	/**
	 * Returns the outbound buffer pointer.
	 * @return The pointer.
	 */
	public synchronized CommunicationBuffer getMainOutbound() {
		return out;
	}
	
	/**
	 * The thread's run method that checks for new messages
	 * in the buffer and sends them to all the clients.
	 */
	public void run() {
		while (true) {
			Message msg = out.getMessage();
			Iterator<ClientSession> active = activeClients.iterator();
			while (active.hasNext()) {
				ClientSession cli = active.next();
				if (cli.isAlive()) {
					synchronized (sendLock) {
//					System.out.println(cli);
						cli.sendMessage(msg); //
					}
				} else { 
					active.remove(); // 
				}
			}
			if (System.currentTimeMillis() - lastScoreUpdate > scoreUpdateInterval) {
				active = activeClients.iterator();
				ConcurrentHashMap<ClientSession, Integer> scores
				= new ConcurrentHashMap<ClientSession, Integer>();
				while (active.hasNext()) {
					ClientSession cli = active.next();
					if (cli.isAlive()) {
						scores.put(cli, cli.getExp());
					}
				}
				out.addMessage(new Message(scores));
			}
		}
	}
}
