package Tanks.shared.mapElements;

import java.util.HashSet;

public abstract class GameObject implements ObjectBase {
	
	protected int locationX;
	protected int locationY;
	protected int width;
	protected int height;
	protected boolean passable;
	protected boolean bulletPassable;
	protected boolean breakable;
	protected HashSet<Integer> thisXcoord = new HashSet<Integer>();
	protected HashSet<Integer> thisYcoord = new HashSet<Integer>();
	
	public GameObject(int x, int y, int width, int height, boolean passability, boolean bPassability, boolean breakability) {
		this.locationX = x;
		this.locationY = y;
		this.height = height;
		this.width = width;
		this.passable = passability;
		this.bulletPassable = bPassability;
		createX();
		createY();
	}

	private void createX() {
		for (int i = locationX; i <= (locationX + width); i++) {
			this.thisXcoord.add(i);
		}
	}
	
	private void createY() {
		for (int i = locationY; i <= (locationY + height); i++) {
			this.thisXcoord.add(i);
		}
	}
	
	public boolean getCollision(GameObject otherObject) {
		if(!passable) {
			for (int i : thisXcoord) {
				if (otherObject.getXset().contains(i)) {
					return true;
				}
			}
			for (int i : thisYcoord) {
				if (otherObject.getYset().contains(i)) {
					return true;
				}
			}
// nt kui moodustab objectidest setid ja siis hakkab ühekaupa kumbagi läbi käima, for each if contains....
		}
		return false;
	}

	public HashSet<Integer> getXset() {
		return thisXcoord;
	}
	
	public HashSet<Integer> getYset() {
		return thisYcoord;
	}
	
	public int getLocationX() {
		return locationX;
	}

	public int getLocationY() {
		return locationY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public boolean isBreakable() {
		return breakable;
	}
}
