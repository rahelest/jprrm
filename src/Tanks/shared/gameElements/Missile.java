package Tanks.shared.gameElements;

import Tanks.shared.mapElements.UnbreakableObject;

public class Missile extends UnbreakableObject implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1727024859906113013L;
	protected String direction = "N";
	
	
	public Missile(String ID, int x, int y, String direction) {
		super(ID, x, y, 10, 15, true, "missile" + direction + ".png");
		this.direction = direction;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
