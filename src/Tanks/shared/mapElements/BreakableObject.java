package Tanks.shared.mapElements;

public class BreakableObject extends GameObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7581130302156684292L;

	public BreakableObject(String ID, int x, int y, int width, int height, String image) {
		super(ID, x, y, width, height, false, false, true, image);
	}
	
}
