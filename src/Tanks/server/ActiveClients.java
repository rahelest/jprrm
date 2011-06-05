package Tanks.server;

import java.net.InetAddress;
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

	public boolean exists(InetAddress inetAddress) {
		// TODO Auto-generated method stub
		for (ClientSession temp : clientList) {
			if(temp.getClientIP().equals(inetAddress)) {
				return true;
			}
		}
		return false;
		
	}
	
	public ClientSession getExisting(InetAddress inetAddress) {
		ClientSession temp = null;
		for (ClientSession pointer : clientList) {
			if(pointer.getClientIP().equals(inetAddress)) {
				temp = pointer;
				break;
			}		
		}
		System.out.println("Leidsin kliendi üles");
		return temp;
	}
	
	
}
