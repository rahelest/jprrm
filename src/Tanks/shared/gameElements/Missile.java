package Tanks.shared.gameElements;

import Tanks.server.ClientSession;
import Tanks.shared.mapElements.UnbreakableObject;

public class Missile extends UnbreakableObject implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1727024859906113013L;
	protected String direction = "N";
	protected int speed;
	
	public Missile(String ID, int x, int y, String direction, int speed) {
		super(ID, x, y, 10, 15, true, "missile" + direction + ".png");
		this.direction = direction;
		new Thread(this).start();
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public void run() {
		if(direction.equals("N")) {
			while(true) {
				setLocation(getX() - speed, getY());
			}
		} else if (direction.equals("S")) {
			while(true) {
				setLocation(getX() + speed, getY());
			}
		} else if (direction.equals("E")) {
			while(true) {
				setLocation(getX(), getY() + speed);
			}
		} else if (direction.equals("W")) {
			while(true) {
				setLocation(getX(), getY() - speed);
			}
		}

	}

}
