package shared.mapElements;

/**
 * The class for map decoration.
 * @author JPRRM
 *
 */
public abstract class MapDecorObject extends GameObject {

	/**
	 * An unique serial number.
	 */
	private static final long serialVersionUID = 2551278594425695324L;

	/**
	 * The constructor.
	 * @param ID The name.
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @param width The objects width.
	 * @param height The objects height.
	 * @param image Its image location.
	 */
	public MapDecorObject(String ID, int x, int y, int width, int height, String image) {
		super(ID, x, y, width, height, true, true, false, image);
	}

}
