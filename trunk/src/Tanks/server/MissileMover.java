package Tanks.server;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import Tanks.shared.gameElements.Missile;
import Tanks.shared.gameElements.Tank;

public class MissileMover extends Thread {
	
	private static ConcurrentHashMap<Missile, ClientSession> missiles = new ConcurrentHashMap<Missile, ClientSession>();
	private static int waitTime = 50; 
	
	public MissileMover() {
		start();
	}
	
	public void run() {
		for (Missile m : missiles.keySet()) {
			if (m.move(missiles.get(m).getMap())) {
				missiles.remove(m);
			}
		}
		try {
			sleep(waitTime);
		} catch (InterruptedException e) {
		}
		
	}
	
	public static void newMissile(ClientSession client) {
		Tank tank = client.getTank();
		Missile m = new Missile(client.getId() + "M" + getNr(client), tank.getX(), tank.getY(), tank.getDirection());
		missiles.put(m, client);
	}

	private static String getNr(ClientSession client) {
		int id = 1;
		Set<Missile> mi = missiles.keySet();
		Set<String> ids = new HashSet<String>();
		for (Missile m: mi) {
			if(missiles.get(m).equals(client)) {
				ids.add(m.getID());
			}
		}
		do {
			id++;
		} while (!ids.contains(Integer.toString(id)));		
		return Integer.toString(id);
	}
	
	

}
