package Tanks.server;

import java.util.LinkedList;

public class OutboundBuffer {
	private LinkedList<String> messages = new LinkedList<String>();
	
	public synchronized void addMessage(String message) {
		messages.add(message);
		this.notifyAll();
	}
	
	public synchronized String getMessage(String message) {
		try {
			while (messages.isEmpty()) {
				this.wait();
			}
		} catch (InterruptedException e) {}
		String temp = messages.getFirst();
		messages.removeFirst();
		return temp;
	}
}
