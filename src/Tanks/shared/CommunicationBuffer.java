package Tanks.shared;

import java.util.LinkedList;

public class CommunicationBuffer {
	private LinkedList<Message> messages = new LinkedList<Message>();
	private Object bufferLock = new Object();
	
	public synchronized void addMessage(Message message) {
		synchronized (bufferLock) {
			messages.add(message);
		}	
		this.notifyAll();
	}
	
	public synchronized Message getMessage() {
//		try {
			while (messages.isEmpty()) {
				try {
					wait();
				} catch (InterruptedException e) {}
			}
//		} catch (InterruptedException e) {}
		synchronized (bufferLock) {
			Message temp = messages.getFirst();
			messages.removeFirst();
			return temp;
		}
	}
}
