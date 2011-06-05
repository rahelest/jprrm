package Tanks.shared;

import java.util.Iterator;

import Tanks.server.ActiveClients;
import Tanks.server.ClientSession;

public class Broadcaster extends Thread {
	CommunicationBuffer out = new CommunicationBuffer();
	ActiveClients activeClients = null;
	private Object sendLock = new Object();
	
	public Broadcaster(ActiveClients pointer) {
		this.setName("Broadcaster - " + pointer.toString());
		activeClients = pointer;
		start();
	}
	
	public synchronized CommunicationBuffer getMainOutbound() {
		return out;
	}
	
	public void run() {
		while(true) {
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
		}
	}
}
