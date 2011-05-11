package Tanks.server;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ActiveClients {

	private List<ClientSession> clientList = new LinkedList<ClientSession>();
	private Object iteratorLock = new Object();
	
	public synchronized void addClient(ClientSession client) {
		clientList.add(client);
	}

	public Iterator<ClientSession> iterator() {
		synchronized(iteratorLock) {
			Iterator<ClientSession> i = clientList.iterator();
			return i;
		}
	}
	
	
}
