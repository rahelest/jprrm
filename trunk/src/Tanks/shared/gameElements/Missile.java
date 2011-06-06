package Tanks.shared.gameElements;

import java.io.Serializable;

import Tanks.server.ClientSession;
import Tanks.shared.GameMap;
import Tanks.shared.mapElements.UnbreakableObject;

public class Missile extends UnbreakableObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1727024859906113013L;
	protected String direction = "N";
	protected int speed;
	protected transient ClientSession owner;
	
	public Missile(String ID, int x, int y) {
		super(ID, x, y, 10, 15, true, "missile" + owner.getTank().getDirection() + ".png");
		this.direction = owner.getTank().getDirection();
		this.speed = owner.getMissileSpeed();

	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public void run() {
		GameMap map = owner.getMap();
		while(true) {		
			if(direction.equals("N")) {
					setLocation(getX() - speed, getY());
			} else if (direction.equals("S")) {
					setLocation(getX() + speed, getY());
			} else if (direction.equals("E")) {
					setLocation(getX(), getY() + speed);
			} else if (direction.equals("W")) {
					setLocation(getX(), getY() - speed);
			}
			map.doYourStuff(this);
		}
		
	}

}
