package Tanks.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
	 * Width for the map.
	 */
	static int mapWidth;
	/**
	 * Height for the map.
	 */
	static int mapHeight;
	
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
		Tank test = new Tank("Test", 0, 0);
		mapWidth = map.getWidth();
		mapHeight = map.getHeight();
		int tankWidth = test.getWidth();
		int tankHeight = test.getHeight();
		GameObject tank = new Tank(name, rand.nextInt(mapWidth - tankWidth),
				rand.nextInt(mapHeight - tankHeight));
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
//		System.out.println("Enne gamemapi");
		GameMap map = new GameMap(messenger);
//		System.out.println("pärast gamemapi");
		Random rand = new Random();
		GameObject object2;
		
		mapWidth = map.getWidth();
		mapHeight = map.getHeight();
//		System.out.println("Mõõtmed käes");
		if (iron > 0) {
			IronWall test = new IronWall("Test", 0, 0);
			int ironWidth = test.getWidth();
			int ironHeight = test.getHeight();
//			System.out.println("Mõõtmed käes2");
			object2 = createIronWall(rand.nextInt(mapWidth - ironWidth),
					rand.nextInt(mapHeight - ironHeight));
			map.addObject(object2);
			System.out.println(" 1");
			for (int i = 1; i < iron; i++) {
				GameObject object = createIronWall(object2.getX()
						+ object2.getWidth() + 1, object2.getY());
				map = addingToMap(map, object);
//				System.out.println("addingtomap valma");
				object2 = object;
			}
		}
		
//		System.out.println("raud vaslma");
		
		if (brick > 0) {
			BrickWall test = new BrickWall("Test", 0, 0);
			int brickWidth = test.getWidth();
			int brickHeight = test.getHeight();
			object2 = createBrickWall(rand.nextInt(mapWidth - brickWidth),
					rand.nextInt(mapHeight - brickHeight));
			map.addObject(object2);
			for (int i = 1; i < brick; i++) {
				GameObject object = createBrickWall(object2.getX()
						+ object2.getWidth() + 1, object2.getY());
				map = addingToMap(map, object);
			}
		}
		
//		System.out.println("Brick ");
		
		GameObject test = new Water("Test", 0, 0);
		int waterWidth = test.getWidth();
		int waterHeight = test.getHeight();
		for (int i = 0; i < water; i++) {
			GameObject object = createWater(rand.nextInt(mapWidth - waterWidth),
					rand.nextInt(mapHeight - waterHeight));
			map = addingToMap(map, object);
		}
		
//		System.out.println("Water");
		
		test = new Tree("Test", 0, 0);
		int treeWidth = test.getWidth();
		int treeHeight = test.getHeight();
		for (int i = 0; i < tree; i++) {
			GameObject object = createTree(rand.nextInt(mapWidth - treeWidth),
					rand.nextInt(mapHeight - treeHeight));
			map = addingToMap(map, object);
		}
//		System.out.println("Tree");
		
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
		System.out.println("Siin");
		Random rand = new Random();
		while (true) {
//			System.out.println("Kordus????");
//			System.out.println(object.checkCollision(map));
			if (object.checkCollision(map) == null) {
				map.addObject(object);
				return map;
			} else {
				object.setLocation(rand.nextInt(mapWidth - object.getWidth()),
						rand.nextInt(mapHeight - object.getHeight()));
			}
		}
	}
	
	/**
	 * Saves the map to a file.
	 * @param map The map.
	 */
	public static void saveToFile(GameMap map) {
		System.out.println("Hakkan salvestama!");
		int nr = 0;
		do {
			nr++;
		} while (new File("Map" + nr + ".txt").exists());
		System.out.println("Sain numbri: " + nr);
		try {
			PrintWriter writer = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("Map" + nr + ".txt"))));
			
			ConcurrentHashMap<String, GameObject> objects = map.getObjects();
			Collection<GameObject> values = objects.values();
			
			for (GameObject g : values) {
				if (g instanceof IronWall) {
					writer.println("IronWall " + g.getID() + " " + g.getX() + " " + g.getY());
					System.out.println("Kirjutan raua");
				} else if (g instanceof BrickWall) {
					writer.println("BrickWall " + g.getID() + " " + g.getX() + " " + g.getY());
					System.out.println("Kirjutan tellise");
				} else if (g instanceof Tree) {
					writer.println("Tree " + g.getID() + " " + g.getX() + " " + g.getY());
					System.out.println("Kirjutan puu");
				} else if (g instanceof Water) {
					writer.println("Water " + g.getID() + " " + g.getX() + " " + g.getY());
					System.out.println("Kirjutan vee");
				}
			}	
			writer.flush();
			writer.close();
			System.out.println("Salvestamine valmis!");
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}	
	}
	
	/**
	 * Loads a map from a text file.
	 * @param number The number.
	 * @param messenger The broadcaster pointer.
	 * @return The loaded map.
	 */
	public static GameMap loadFromFile(int number, Broadcaster messenger) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("Map" + number + ".txt")));
			
			GameMap map = new GameMap(messenger);
			String row = br.readLine();
			while (row != null) {
				String[] splitted = row.split(" ");
				if (splitted.length == 4) {
					String ID = splitted[1];
					int x = Integer.parseInt(splitted[2]);
					int y = Integer.parseInt(splitted[3]);
					if (splitted[0].equals("Tree")) {
						map.addObject(new Tree(ID, x, y));
					} else if (splitted[0].equals("Water")) {
						map.addObject(new Water(ID, x, y));
					} else if (splitted[0].equals("IronWall")) {
						map.addObject(new IronWall(ID, x, y));
					} else if (splitted[0].equals("BrickWall")) {
						map.addObject(new BrickWall(ID, x, y));
					} else {
						return null;
					}
				} else {
					return null;
				}
				row = br.readLine();
			}
				
			br.close();	
			return map;
		} catch (FileNotFoundException e) {
			System.out.println("File not found, new map generated!");
			Random rand = new Random();
			return createMap(messenger, rand.nextInt(3), rand.nextInt(4), rand.nextInt(5), rand.nextInt(5));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
