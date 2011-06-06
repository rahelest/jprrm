package Tanks.server;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import Tanks.shared.gameElements.Missile;
import Tanks.shared.gameElements.Tank;
import Tanks.shared.mapElements.GameObject;

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
		} catch (InterruptedException e) {}
	}
	
	public static void newMissile(ClientSession client) {
		Tank tank = client.getTank();
		System.out.println("Saan tankiviite");
		Missile m = new Missile(client.getId() + "M" + getNr(client), tank.getX(), tank.getY(), tank.getDirection(), client.getMissileSpeed());
		System.out.println("Uus mürsk loodud");
		missiles.put(m, client);
		System.out.println("Uus mürsk lisatud");
	}
	
	public static ConcurrentHashMap<String, GameObject> getMissiles() {
		ConcurrentHashMap<String, GameObject> temp = new ConcurrentHashMap<String, GameObject>();
		for ( Missile m : missiles.keySet()) {
			temp.put(m.getID(), m);
		}
		return temp;
	}

	private static String getNr(ClientSession client) {
		int id = 1;
		System.out.println("Loon numbrit");
		Set<Missile> mi = missiles.keySet();
		Set<String> ids = new HashSet<String>();
		System.out.println("Hakkan lisama");
		for (Missile m: mi) {
			if(missiles.get(m).equals(client)) {
				ids.add(m.getID());
			}
		}
		System.out.println("Hakkan otsima");
		do {
			id++;
		} while (ids.contains(Integer.toString(id)));		
		System.out.println("GetNr valmis");
		return Integer.toString(id);
	}
	
	

}
