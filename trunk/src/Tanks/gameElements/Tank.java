package Tanks.gameElements;

import Tanks.mapElements.BreakableObject;

public class Tank extends BreakableObject {

	protected String direction = "N";
	
	public Tank(int x, int y) {
		super(x, y, 40, 40);
		// TODO Auto-generated constructor stub
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}
