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
		while (true) {
			for (Missile m : missiles.keySet()) {
				if (m.move(missiles.get(m).getMap())) {
					missiles.remove(m);
				}
				System.out.println(m);
			}
			try {
				sleep(waitTime);
			} catch (InterruptedException e) {}
		}

	}
	
	public static void newMissile(ClientSession client) {
		Tank tank = client.getTank();
		int x = determineCoordinateX(tank, tank.getWidth(), tank.getX());
		int y = determineCoordinateY(tank, tank.getHeight(), tank.getY());
//		System.out.println("Saan tankiviite");
		Missile m = new Missile(client.getId() + "M" + getNr(client), x, y, tank.getDirection(), client.getMissileSpeed());
//		System.out.println("Uus mürsk loodud");
		missiles.put(m, client);
//		System.out.println("Uus mürsk lisatud");
	}
	
	private static int determineCoordinateX(Tank tank, int size, int place) {
		String dir = tank.getDirection();
			
		if (dir.equals("N") || dir.equals("S")) {
			return place + size / 2;
		} else if (dir.equals("E")) {
			return place + size + 2;
		} else if (dir.equals("W")) {
			return place - 2;
		} else return 0;
	}
	
	private static int determineCoordinateY(Tank tank, int size, int place) {
		String dir = tank.getDirection();
			
		if (dir.equals("E") || dir.equals("W")) {
			return place + size / 2;
		} else if (dir.equals("S")) {
			return place + size + 2;
		} else if (dir.equals("N")) {
			return place - 2;
		} else return 0;
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
//		System.out.println("Loon numbrit");
		Set<Missile> mi = missiles.keySet();
		Set<String> ids = new HashSet<String>();
//		System.out.println("Hakkan lisama");
		for (Missile m: mi) {
			if(missiles.get(m).equals(client)) {
				ids.add(m.getID());
			}
		}
//		System.out.println("Hakkan otsima");
		do {
			id++;
		} while (ids.contains(Integer.toString(id)));		
//		System.out.println("GetNr valmis");
		return Integer.toString(id);
	}
	
	

}
