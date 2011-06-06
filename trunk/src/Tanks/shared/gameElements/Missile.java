package Tanks.shared.gameElements;

import java.awt.Color;
import java.io.Serializable;

import Tanks.server.ClientSession;
import Tanks.shared.GameMap;
import Tanks.shared.mapElements.GameObject;
import Tanks.shared.mapElements.UnbreakableObject;

public class Missile extends UnbreakableObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1727024859906113013L;
	protected String direction = "N";
	protected int speed;
	protected transient ClientSession owner;
	
	public Missile(String ID, int x, int y, String direction, int speed) {
		super(ID, x, y, 10, 15, true, "missile" + direction + ".png");
		setBackground(Color.BLACK);
		this.direction = direction;
		this.speed = speed;

	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public boolean move(GameMap map) {
		if(direction.equals("N")) {
			setLocation(getX() - speed, getY());
		} else if (direction.equals("S")) {
			setLocation(getX() + speed, getY());
		} else if (direction.equals("E")) {
			setLocation(getX(), getY() + speed);
		} else if (direction.equals("W")) {
			setLocation(getX(), getY() - speed);
		}
		
		GameObject collidee = checkCollision(map);
		if (collidee != null) {
			collidee.getDamaged();
			return false;
		}
			
		return true;
	} 
}
