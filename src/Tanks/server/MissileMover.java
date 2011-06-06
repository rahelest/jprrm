package Tanks.server;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import Tanks.shared.gameElements.Missile;

public class MissileMover {
	
	private ConcurrentHashMap<Missile, ClientSession> missiles = new ConcurrentHashMap<Missile, ClientSession>();
	
	public void newMissile(ClientSession client) {
		Missile m = new Missile(client.getId() + "M" + getNr(client), client.getTank().getX(), client.getTank().getY());
		missiles.put(m, client);
	}

	private String getNr(ClientSession client) {
		int id = 1;
		Set<Missile> mi = missiles.keySet();
		Set<String> ids = new HashSet<String>();
		for (Missile m: mi) {
			if(missiles.get(m).equals(client)) {
				ids.add(missiles.get(m).)
			}
		}
		do {
			id++;
		} while (client == value && !mi.contains(Integer.toString(id)));		
		return Integer.toString(id);
	}

}
