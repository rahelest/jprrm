package Tanks.shared.mapElements;

/**
 * The class for map decoration.
 * @author JPRRM
 *
 */
public class mapDecorObject extends GameObject {

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
	public mapDecorObject(String ID, int x, int y, int width, int height, String image) {
		super(ID, x, y, width, height, true, true, false, image);
		// TODO Auto-generated constructor stub
	}

}
