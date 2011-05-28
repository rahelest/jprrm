package Tanks.shared;

import java.util.LinkedList;

public class CommunicationBuffer {
	private LinkedList<Message> messages = new LinkedList<Message>();
	
	public synchronized void addMessage(Message message) {
		messages.add(message);
		this.notifyAll();
	}
	
	public synchronized Message getMessage() {
		try {
			while (messages.isEmpty()) {
				this.wait();
			}
		} catch (InterruptedException e) {}
		Message temp = messages.getFirst();
		messages.removeFirst();
		return temp;
	}
}
