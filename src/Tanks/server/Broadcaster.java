package Tanks.server;

import java.util.Iterator;

public class Broadcaster extends Thread {
	OutboundBuffer out = new OutboundBuffer();
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
