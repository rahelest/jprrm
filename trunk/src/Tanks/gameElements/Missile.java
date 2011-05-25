package Tanks.gameElements;

import Tanks.mapElements.UnbreakableObject;

public class Missile extends UnbreakableObject {

	protected String direction = "N";
	
	public Missile(int x, int y, int width, int height) {
		super(x, y, 10, 15, true);
		// TODO Auto-generated constructor stub
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}
