package Tanks.server;

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
	private static final int waitTime = 10;
	private static long lastScoreUpdate = 0;
	/**
	 * ID for missiles.
	 */
	private static int id = 0;
	
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
			}
			if (!missiles.isEmpty()) {
				System.out.println("Liigutan: " + missiles);
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
		Missile test = new Missile("test", 0, 0, tank.getDirection(), 0);
		System.out.println("clintdirection ! " + tank.getDirection());
		int x = determineCoordinateX(tank, tank.getWidth(), tank.getX(), test.getWidth());
		int y = determineCoordinateY(tank, tank.getHeight(), tank.getY(), test.getHeight());
		Missile m = new Missile(client.getId() + "M" + ++id,
				x, y, tank.getDirection(), client.getMissileSpeed());
		System.out.println("MIssile! " + tank.getDirection());
		missiles.put(m, client);
	}
	
	/**
	 * Gets the x coordinate depending on directions.
	 * @param tank The owner tank.
	 * @param width The tank's width.
	 * @param location The tank's x coordinate.
	 * @param i 
	 * @return The x:
	 */
	private static int determineCoordinateX(Tank tank, int width, int location, int mWidth) {
		String dir = tank.getDirection();	
		if (dir.equals("N") || dir.equals("S")) {
			return location + width / 2 - mWidth / 2;
		} else if (dir.equals("E")) {
			return location + width + mWidth + 2;
		} else if (dir.equals("W")) {
			return location - mWidth - 2;
		} else {
			return 0;
		}
	}
	
	/**
	 * Gets the x coordinate depending on directions.
	 * @param tank The owner tank.
	 * @param height The tank's height.
	 * @param location The tank's y coordinate.
	 * @param i 
	 * @return The y.
	 */
	private static int determineCoordinateY(Tank tank, int height, int location, int mHeight) {
		String dir = tank.getDirection();
		if (dir.equals("E") || dir.equals("W")) {
			return location + height / 2 - mHeight / 2;
		} else if (dir.equals("S")) {
			return location + height + mHeight + 2;
		} else if (dir.equals("N")) {
			return location - mHeight - 2;
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
}
