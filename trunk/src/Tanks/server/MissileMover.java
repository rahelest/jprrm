package Tanks.server;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import Tanks.shared.gameElements.Missile;
import Tanks.shared.gameElements.Tank;
import Tanks.shared.mapElements.GameObject;

/**
 * Moves and manages the missiles.
 * @author JPRRM
 *
 */
public class MissileMover extends Thread {
	
	/**
	 * List with the missiles.
	 */
	private static ConcurrentHashMap<Missile, ClientSession> missiles
		= new ConcurrentHashMap<Missile, ClientSession>();
	/**
	 * Time to wait before updating locations.
	 */
	private static final int waitTime = 50;
	
	/**
	 * The constructor.
	 */
	public MissileMover() {
		start();
	}
	
	/**
	 * The thread's run method.
	 */
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
			} catch (InterruptedException e) { }
		}
	}
	
	/**
	 * Creates a new missile.
	 * @param client The owner's pointer.
	 */
	public static synchronized void newMissile(ClientSession client) {
		Tank tank = client.getTank();
		int x = determineCoordinateX(tank, tank.getWidth(), tank.getX());
		int y = determineCoordinateY(tank, tank.getHeight(), tank.getY());
		Missile m = new Missile(client.getId() + "M" + getNr(client),
				x, y, tank.getDirection(), client.getMissileSpeed());
		missiles.put(m, client);
	}
	
	/**
	 * Gets the x coordinate depending on directions.
	 * @param tank The owner tank.
	 * @param width The tank's width.
	 * @param location The tank's x coordinate.
	 * @return The x:
	 */
	private static int determineCoordinateX(Tank tank, int width, int location) {
		String dir = tank.getDirection();	
		if (dir.equals("N") || dir.equals("S")) {
			return location + width / 2;
		} else if (dir.equals("E")) {
			return location + width + 2;
		} else if (dir.equals("W")) {
			return location - 2;
		} else {
			return 0;
		}
	}
	
	/**
	 * Gets the x coordinate depending on directions.
	 * @param tank The owner tank.
	 * @param height The tank's height.
	 * @param location The tank's y coordinate.
	 * @return The y.
	 */
	private static int determineCoordinateY(Tank tank, int height, int location) {
		String dir = tank.getDirection();
		if (dir.equals("E") || dir.equals("W")) {
			return location + height / 2;
		} else if (dir.equals("S")) {
			return location + height + 2;
		} else if (dir.equals("N")) {
			return location - 2;
		} else {
			return 0;
		}
	}
	
	/**
	 * Returns the list with missiles.
	 * @return The list.
	 */
	public static synchronized ConcurrentHashMap<String, GameObject> getMissiles() {
		ConcurrentHashMap<String, GameObject> temp = new ConcurrentHashMap<String, GameObject>();
		for (Missile m : missiles.keySet()) {
			temp.put(m.getID(), m);
		}
		return temp;
	}

	/**
	 * Gets a number for the missiles.
	 * @param client The owner.
	 * @return A new ID.
	 */
	private static String getNr(ClientSession client) {
		int id = 1;
		Set<Missile> mi = missiles.keySet();
		Set<String> ids = new HashSet<String>();
		for (Missile m : mi) {
			if (missiles.get(m).equals(client)) {
				ids.add(m.getID());
			}
		}
		do {
			id++;
		} while (ids.contains(Integer.toString(id)));		
		return Integer.toString(id);
	}
	
	

}
