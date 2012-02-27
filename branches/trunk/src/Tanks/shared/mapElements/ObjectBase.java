package Tanks.shared.mapElements;

import Tanks.shared.GameMap;

/**
 * An interface for compulsory methods.
 * @author JPRRM
 *
 */
public interface ObjectBase {

	/**
	 * Checks collision with other objects.
	 * @param map The map.
	 * @return The collidee;
	 */
	GameObject checkCollision(GameMap map);
	/**
	 * Returns the breakable field.
	 * @return The breakable field.
	 */
	boolean isBreakable();
}
