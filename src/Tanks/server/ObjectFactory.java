package Tanks.server;

import java.util.Random;

import Tanks.shared.Broadcaster;
import Tanks.shared.GameMap;
import Tanks.shared.gameElements.Tank;
import Tanks.shared.mapElements.*;

/**
 * Creates new objects and maps.
 * @author JPRRM
 *
 */
public final class ObjectFactory {
	
	/**
	 * The id number.
	 */
	static int id = 0;
	
	/**
	 * The hiding constructor.
	 */
	private ObjectFactory() { }
	
	/**
	 * Spawns a tank.
	 * @param map The map whre to spawn.
	 * @param name The name of the tank.
	 * @return A tank.
	 */
	public static Tank spawnTank(GameMap map, String name) {
		Random rand = new Random();
		GameObject tank = new Tank(name, rand.nextInt(500 - 60), rand.nextInt(500 - 30));
		map = addingToMap(map, tank);
		return (Tank) map.getObject(name);
	}
	
	/**
	 * Creates water.
	 * @param x x location.
	 * @param y y location.
	 * @return The new object.
	 */
	private static Water createWater(int x, int y) {
		return new Water("a" + ++id, x, y);
	}
	
	/**
	 * Creates a tree.
	 * @param x x location.
	 * @param y y location.
	 * @return The new object.
	 */
	private static Tree createTree(int x, int y) {
		return new Tree("a" + ++id, x, y);
	}
	
	/**
	 * Creates a brick wall.
	 * @param x x location.
	 * @param y y location.
	 * @return The new object.
	 */
	private static BrickWall createBrickWall(int x, int y) {
		return new BrickWall("a" + ++id, x, y);
	}
	
	/**
	 * Creates an iron wall.
	 * @param x x location.
	 * @param y y location.
	 * @return The new object.
	 */
	private static IronWall createIronWall(int x, int y) {
		return new IronWall("a" + ++id, x, y);
	}
	

	/**
	 * Generates a map.
	 * @param messenger The needed broadcaster pointer.
	 * @param water How many water to create.
	 * @param tree How many trees to create.
	 * @param brick How many brick walls to create.
	 * @param iron How many iron walls to create.
	 * @return The new map.
	 */
	public static GameMap createMap(Broadcaster messenger, int water, int tree, int brick, int iron) {
		GameMap map = new GameMap(messenger);
		Random rand = new Random();
		GameObject object2;
		
		if (iron > 0) {
			object2 = createIronWall(rand.nextInt(900 - 60), rand.nextInt(900 - 30));
			map.addObject(object2);
			for (int i = 1; i < iron; i++) {
				GameObject object = createIronWall(object2.getX()
						+ object2.getWidth() + 1, object2.getY());
				map = addingToMap(map, object);
				object2 = object;
			}
		}
		
		if (brick > 0) {
			object2 = createBrickWall(rand.nextInt(900 - 60), rand.nextInt(900 - 30));
			map.addObject(object2);
			for (int i = 1; i < brick; i++) {
				GameObject object = createBrickWall(object2.getX()
						+ object2.getWidth() + 1, object2.getY());
				map = addingToMap(map, object);
			}
		}
		
		for (int i = 0; i < tree; i++) {
			GameObject object = createTree(rand.nextInt(800), rand.nextInt(800));
			map = addingToMap(map, object);
		}
		
		for (int i = 0; i < water; i++) {
			GameObject object = createWater(rand.nextInt(800), rand.nextInt(800));
			map = addingToMap(map, object);
		}
		
		return map;
	}
	
	/**
	 * Add to the map while making sure the object does not
	 * collide with other objects.
	 * @param map The map.
	 * @param object The object.
	 * @return The map.
	 */
	private static GameMap addingToMap(GameMap map, GameObject object) {
		Random rand = new Random();
		while (true) {
			if (object.checkCollision(map) == null) {
				map.addObject(object);
				return map;
			} else {
				object.setLocation(rand.nextInt(900 - object.getWidth()),
						rand.nextInt(900) - object.getHeight());
			}
		}
	}
}
