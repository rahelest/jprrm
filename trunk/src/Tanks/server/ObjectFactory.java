package Tanks.server;

import java.util.Random;

import Tanks.shared.Broadcaster;
import Tanks.shared.GameMap;
import Tanks.shared.gameElements.Tank;
import Tanks.shared.mapElements.*;

public class ObjectFactory {

	static int id = 0;
	
	public static Tank spawnTank(GameMap map, String name) {
		Random rand = new Random();
		GameObject tank = new Tank (name, rand.nextInt(900 - 60), rand.nextInt(900 - 30));
		map = addingToMap(map, tank);
		return (Tank) map.getObject(name);
	}
	
	private static Water createWater(int x, int y) {
		return new Water("a" + ++id, x, y);
	}
	
	
	private static Tree createTree(int x, int y) {
		return new Tree("a" + ++id, x, y);
	}
	
	private static BrickWall createBrickWall(int x, int y) {
		return new BrickWall("a" + ++id, x, y);
	}
	
	private static IronWall createIronWall(int x, int y) {
		return new IronWall("a" + ++id, x, y);
	}
	
	
	public static GameMap createMap(Broadcaster messenger, int water, int tree, int brick, int iron) {
		GameMap map = new GameMap(messenger);
		Random rand = new Random();
		
		GameObject object2 = createIronWall(rand.nextInt(900 - 60), rand.nextInt(900 - 30));
		map.addObject(object2);
		for (int i = 1; i < iron; i++) {
			GameObject object = createIronWall(object2.getX() + object2.getWidth() + 1, object2.getY());
			map = addingToMap(map, object);
			object2 = object;
		}
		
		for (int i = 0; i < brick; i++) {
			GameObject object = createBrickWall(rand.nextInt(850), rand.nextInt(850));
			map = addingToMap(map, object);
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
	
	private static GameMap addingToMap(GameMap map, GameObject object) {
		Random rand = new Random();
		while (true) {
			if (!object.checkCollision(map)) {
				map.addObject(object);
				return map;
			} else {
				object.setLocation(rand.nextInt(900 - object.getWidth()), rand.nextInt(900) - object.getHeight());
			}
		}
	}
}
