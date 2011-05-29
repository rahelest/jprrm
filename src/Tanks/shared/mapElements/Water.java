package Tanks.shared.mapElements;

public class Water extends UnbreakableObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -261948047857168485L;

	public Water(String string, int x, int y) {
		super(string, x, y, 150, 75, true, "water.png");
	}

}
