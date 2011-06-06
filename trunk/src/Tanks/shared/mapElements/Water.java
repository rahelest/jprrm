package Tanks.shared.mapElements;

/**
 * The game element Water class.
 * @author JPRRM
 *
 */
public class Water extends UnbreakableObject {

	/**
	 * An unique serial number.
	 */
	private static final long serialVersionUID = -261948047857168485L;
	
	/**
	 * The constructor.
	 * @param string The name.
	 * @param x The x location.
	 * @param y The y location.
	 */
	public Water(String string, int x, int y) {
		super(string, x, y, 150, 75, true, "water.png");
	}
	
//	public void paintComponent(Graphics g) {
//		g.drawImage(sprite, 0, 0, this);
//	}

}
