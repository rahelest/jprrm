package shared.mapElements;

import shared.GameMap;

/**
 * The unbreakable object type.
 * @author JPRRM
 *
 */
public class UnbreakableObject extends GameObject {
	
	/**
	 * An unique serial number.
	 */
	private static final long serialVersionUID = -7823040551339756381L;

	/**
	 * The constructor.
	 * @param string The name.
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @param width The objects width.
	 * @param height The objects height.
	 * @param bulletPassability Whether bullets can pass this.
	 * @param image Its image location.
	 */
	public UnbreakableObject(String string, int x, int y,
			int width, int height, Boolean bulletPassability, String image) {
		super(string, x, y, width, height, false, bulletPassability, false, image);
	}

	@Override
	public GameObject checkCollision(GameMap map) {
		// TODO Auto-generated method stub
		return null;
	}

}
