package shared.mapElements;

/**
 * The Tree object class.
 * @author JPRRM
 *
 */
public class Tree extends MapDecorObject {

	/**
	 * An unique serial number.
	 */
	private static final long serialVersionUID = 3222753290111853094L;
	
	/**
	 * The constructor.
	 * @param ID The name.
	 * @param x The x location.
	 * @param y The y location.
	 */
	public Tree(String ID, int x, int y) {
		super(ID, x, y, 120, 120, "tree.png");
	}

}
