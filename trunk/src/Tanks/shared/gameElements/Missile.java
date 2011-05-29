package Tanks.shared.gameElements;

import Tanks.shared.mapElements.UnbreakableObject;

public class Missile extends UnbreakableObject {

	protected String direction = "N";
	
	public Missile(String ID, int x, int y, int width, int height) {
		super(ID, x, y, 10, 15, true, "missile.png");
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}