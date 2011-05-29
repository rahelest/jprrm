package Tanks.shared.gameElements;

import Tanks.shared.mapElements.BreakableObject;

public class Tank extends BreakableObject {

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