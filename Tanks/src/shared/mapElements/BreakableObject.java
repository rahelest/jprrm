package shared.mapElements;

import shared.GameMap;

/**
 * The breakable object type.
 * @author JPRRM
 *
 */
public class BreakableObject extends GameObject {
	
	/**
	 * An unique serial number.
	 */
	private static final long serialVersionUID = 7581130302156684292L;

	/**
	 * The constructor.
	 * @param ID The name.
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @param width The objects width.
	 * @param height The objects height.
	 * @param image Its image location.
	 */
	public BreakableObject(String ID, int x, int y, int width, int height, String image) {
		super(ID, x, y, width, height, false, false, true, image);
	}
	
}
