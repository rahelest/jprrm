package Tanks.mapElements;

public abstract class GameObject implements ObjectBase {
	
	protected int locationX;
	protected int locationY;
	protected int width;
	protected int height;
	protected boolean passable;
	protected boolean bulletPassable;
	
	public GameObject(int x, int y, int width, int height, boolean passability, boolean bPassability) {
		this.locationX = x;
		this.locationY = y;
		this.height = height;
		this.width = width;
		this.passable = passability;
		this.bulletPassable = bPassability;
	}

	public boolean getCollision(GameObject otherObject) {
		if(!passable) {
			for (int i = 0; i <= width; i++) {
				
			}
// nt kui moodustab objectidest setid ja siis hakkab ühekaupa kumbagi läbi käima, for each if contains....
		}
		return false;
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
}
