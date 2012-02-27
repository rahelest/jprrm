package shared;

import java.util.ArrayList;
import java.util.Iterator;

import server.ActiveClients;
import server.ClientSession;
import shared.messageTypes.ScoreMessage;

/**
 * The class that sends messages to everyone.
 * @author JPRRM
 * 
 */
public class Broadcaster extends Thread {
	
	/**
	 * The timestamp of last score update.
	 */
	private long lastScoreUpdate;
	/**
	 * The time interval after which the scores should be updated.
	 */
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
						cli.sendMessage(msg);
					}
				} else { 
					activeClients.removeClient(cli);
				}
			}
			if (System.currentTimeMillis() - lastScoreUpdate > scoreUpdateInterval) {
				active = activeClients.iterator();
				ArrayList<String> scores = new ArrayList<String>();
				while (active.hasNext()) {
					ClientSession cli = active.next();
					if (cli.isAlive()) {
						scores.add("Player " + Integer.toString(cli.getClientID()) 
								+ ": " + cli.getExp());
					}
				}
				out.addMessage(new ScoreMessage(scores));
				lastScoreUpdate = System.currentTimeMillis();
			}
		}
	}
}
