package Tanks.server;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ActiveClients {

	private CopyOnWriteArrayList<ClientSession> clientList = new CopyOnWriteArrayList<ClientSession>();
	private Object iteratorLock = new Object();
	
	public void addClient(ClientSession client) {
			clientList.add(client);
	}

	public Iterator<ClientSession> iterator() {
			Iterator<ClientSession> i = clientList.iterator();
			return i;
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
