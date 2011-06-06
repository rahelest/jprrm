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
	 * @param map
	 * @return
	 */
	GameObject checkCollision(GameMap map);
	boolean isBreakable();
}
