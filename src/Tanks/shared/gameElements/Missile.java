package Tanks.shared.gameElements;

import java.io.Serializable;

import Tanks.server.ClientSession;
import Tanks.server.MissileMover;
import Tanks.shared.GameMap;
import Tanks.shared.mapElements.GameObject;
import Tanks.shared.mapElements.UnbreakableObject;

/**
 * The missile class.
 * @author JPRRM
 *
 */
public class Missile extends UnbreakableObject implements Serializable {

	/**
	 * An unique serial number.
	 */
	private static final long serialVersionUID = 1727024859906113013L;
	/**
	 * The direction field.
	 */
	protected String direction = "N";
	/**
	 * The speed field.
	 */
	protected int speed;
	
	/**
	 * The constructor.
	 * @param ID The unique ID.
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @param nDirection The direction field.
	 * @param nSpeed The speed field.
	 */
	public Missile(String ID, int x, int y, String nDirection, int nSpeed) {
		super(ID, x, y, 10, 15, true, "missile" + nDirection + ".png");
		this.direction = nDirection;
		this.speed = nSpeed;

	}

	/**
	 * Returns the direction.
	 * @return The direction.
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * Sets a new direction.
	 * @param nDirection The new direction.
	 */
	public void setDirection(String nDirection) {
		this.direction = nDirection;
	}

	/**
	 * Moves the missile.
	 * @param map The map where it is moved.
	 * @param owner 
	 * @return Whether it collided with something.
	 */
	public boolean move(GameMap map, ClientSession owner) {
		if (direction.equals("N")) {
			setLocation(getX(), getY() - speed);
		} else if (direction.equals("S")) {
			setLocation(getX(), getY() + speed);
		} else if (direction.equals("E")) {
			setLocation(getX() + speed, getY());
		} else if (direction.equals("W")) {
			setLocation(getX() - speed, getY());
		}
		
		GameObject collidee = checkCollision(map);
		if (collidee != null) {
			if (collidee instanceof Tank) {
				owner.increaseExp();
				System.out.println(collidee);
				System.out.println(MissileMover.getOwner(collidee));
				
				MissileMover.getOwner(collidee).gotHit();
			}
			collidee.getDamaged(map);
			return false;
		} else {
			return true;
		}
	} 
}
