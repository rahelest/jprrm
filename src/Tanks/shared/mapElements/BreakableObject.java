package Tanks.shared.mapElements;

public class BreakableObject extends GameObject {
	
	public BreakableObject(String ID, int x, int y, int width, int height, String image) {
		super(ID, x, y, width, height, false, false, true, image);
	}
	
}
