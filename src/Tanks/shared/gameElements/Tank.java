package Tanks.shared.gameElements;

import Tanks.server.ClientSession;
import Tanks.shared.mapElements.BreakableObject;

/**
 * The Tank object class.
 * @author JPRRM
 *
 */
public class Tank extends BreakableObject {

	/**
	 * An unique serial number.
	 */
	private static final long serialVersionUID = -3912186180720259310L;
	
	/**
	 * The tank's direction.
	 */
	protected String direction = "E";
	/**
	 * This is the owner of the tank.
	 */
	private ClientSession owner;
	/**
	 * The constructor.
	 * @param ID The name.
	 * @param x The x location.
	 * @param y The y location.
	 */
	public Tank(ClientSession owner, String ID, int x, int y) {
		super(ID, x, y, 60, 30, "tankE.png");
	}

	/**
	 * Asks for the direction.
	 * @return The direction.
	 */
	public synchronized String getDirection() {
		return direction;
	}

	/**
	 * Sets the new direction.
	 * @param nDirection The new direction.
	 */
	public synchronized void setDirection(String nDirection) {
		this.direction = nDirection;
//		System.out.println(direction);
		this.image = "tank" + direction + ".png";
	}
	
	public ClientSession getOwner() {
		return owner;
	}
}
