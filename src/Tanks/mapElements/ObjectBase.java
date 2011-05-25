package Tanks.mapElements;

public interface ObjectBase {

	public boolean getCollision(GameObject otherObject);
	public int getLocationX();
	public int getLocationY();
	public int getWidth();
	public int getHeight();
}
