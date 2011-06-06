package Tanks.shared.mapElements;

/**
 * The Iron Wall object class.
 * @author JPRRM
 *
 */
public class IronWall extends UnbreakableObject {

	/**
	 * An unique serial number.
	 */
	private static final long serialVersionUID = -1521019454972491269L;

	/**
	 * The constructor.
	 * @param string The name.
	 * @param x The x location.
	 * @param y The y location.
	 */
	public IronWall(String string, int x, int y) {
		super(string, x, y, 50, 20, false, "iwall.png");
		// TODO Auto-generated constructor stub
	}

}
