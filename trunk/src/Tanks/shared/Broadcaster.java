package Tanks.shared;

import java.util.Iterator;

import Tanks.server.ActiveClients;
import Tanks.server.ClientSession;

public class Broadcaster extends Thread {
	CommunicationBuffer out = new CommunicationBuffer();
	ActiveClients activeClients = null;
	
	public Broadcaster(ActiveClients pointer) {
		activeClients = pointer;
		start();
	}
	
	public void run() {
		String msg = out.getMessage();
		Iterator<ClientSession> active = activeClients.iterator();
		while (active.hasNext()) {
			ClientSession cli = active.next();
			if (cli.isAlive()) {
			cli.sendMessage(msg); // 
			} else { 
			active.remove(); // 
			}
		}
	}
}
