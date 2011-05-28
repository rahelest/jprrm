package Tanks.shared;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import Tanks.shared.mapElements.GameObject;
import Tanks.shared.mapElements.Water;

public class GameMap {

	private HashMap<String, GameObject> objects = new HashMap<String, GameObject>();
	private BufferedImage background;
	
	public synchronized void addObject(GameObject objectToBeAdded) {
		objects.put(objectToBeAdded.getID(), objectToBeAdded);
	}

	public BufferedImage getBackground() {
		return background;
	}

	public synchronized void removeObject(int objectID) {
		objects.remove(objectID);
	}
	
	public GameMap() {
		try {
			background = ImageIO.read(new File("src//background.png"));
		} catch (IIOException e) {			
			System.out.println("The background image could not be loaded - image error!");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("The backround image was not found - missing file!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("General IO exception reading background image!");
			e.printStackTrace();			
		}
		
//		elements.add(new Water(200, 200));
//		elements.add(new Water(800, 200));
//		elements.add(new Water(800, 800));
//		elements.add(new Water(200, 800));
	}

	
}
