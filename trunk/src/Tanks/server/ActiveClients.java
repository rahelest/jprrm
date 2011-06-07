package Tanks.server;

import java.net.InetAddress;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The class that hold the list of active clients.
 * @author JPRRM
 *
 */
public class ActiveClients {

	/**
	 * List with the clients.
	 */
	private CopyOnWriteArrayList<ClientSession> clientList = new CopyOnWriteArrayList<ClientSession>();
	
	/**
	 * Adds a new client to the list.
	 * @param client The new client.
	 */
	public void addClient(ClientSession client) {
			clientList.add(client);
	}
	
	/**
	 * Return the iterator of the list.
	 * @return The iterator.
	 */
	public Iterator<ClientSession> iterator() {
			Iterator<ClientSession> i = clientList.iterator();
			return i;
	}

	/**
	 * Checks whether the clients are present.
	 * @param inetAddress The IP-address.
	 * @return Whether the client exists on the list.
	 */
	public boolean exists(InetAddress inetAddress) {
		for (ClientSession temp : clientList) {
			if (temp.getClientIP().equals(inetAddress)) {
				return true;
			}
		}
		return false;
		
	}

	/**
	 * Asks the list for the existing client.
	 * @param inetAddress The IP after which to search.
	 * @return The found client.
	 */
	public ClientSession getExisting(InetAddress inetAddress) {
		ClientSession temp = null;
		for (ClientSession pointer : clientList) {
			if (pointer.getClientIP().equals(inetAddress)) {
				temp = pointer;
				break;
			}		
		}
		System.out.println("Leidsin kliendi üles");
		return temp;
	}
	
	public void removeClient(ClientSession toBeRemoved) {
		clientList.remove(toBeRemoved);
	}
	
}
