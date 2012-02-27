package Tanks.shared.mapElements;

/**
 * The Brick Wall object class.
 * @author JPRRM
 *
 */
public class BrickWall extends BreakableObject {

	/**
	 * An unique serial number.
	 */
	private static final long serialVersionUID = -5837804347373452228L;

	/**
	 * The constructor.
	 * @param ID The name.
	 * @param x The x location.
	 * @param y The y location.
	 */
	public BrickWall(String ID, int x, int y) {
		super(ID, x, y, 50, 20, "bwall.png");
	}

}
