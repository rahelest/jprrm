package Tanks.shared.gameElements;

import Tanks.shared.mapElements.BreakableObject;

public class Tank extends BreakableObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3912186180720259310L;
	protected String direction = "N";
	
	public Tank(String ID, int x, int y) {
		super(ID, x, y, 60, 30, "tank.png");
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}
