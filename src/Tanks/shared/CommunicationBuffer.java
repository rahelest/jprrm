package Tanks.shared;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import Tanks.shared.mapElements.GameObject;
import Tanks.shared.messageTypes.Message;
import Tanks.shared.messageTypes.MissilesMessage;

/**
 * The buffer for receiving and sending messages.
 * @author JPRRM
 *
 */
public class CommunicationBuffer {
	/**
	 * The list with messages.
	 */
	private LinkedList<Message> messages = new LinkedList<Message>();
	/**
	 * The lock for the messages.
	 */
	private Object bufferLock = new Object();
	
	/**
	 * Adds a new message to the list.
	 * @param message The new message.
	 */
	public synchronized void addMessage(Message message) {
		synchronized (bufferLock) {
			messages.add(message);
		}	
		this.notifyAll();
	}
	
	/**
	 * Asks for a message.
	 * @return The message.
	 */
	public synchronized Message getMessage() {
//		try {
			while (messages.isEmpty()) {
				try {
					wait();
				} catch (InterruptedException e) { }
			}
//		} catch (InterruptedException e) {}
		synchronized (bufferLock) {
			Message temp = messages.getFirst();
			messages.removeFirst();
			return temp;
		}
	}

	public synchronized void sendMissiles(ConcurrentHashMap<String, GameObject> missiles) {
		synchronized (bufferLock) {
			messages.add(new MissilesMessage(missiles));
		}	
		this.notifyAll();
	}
}
