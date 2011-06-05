package Tanks.shared;


import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import Tanks.server.ObjectFactory;
import Tanks.shared.gameElements.Tank;
import Tanks.shared.mapElements.GameObject;
import Tanks.shared.mapElements.Water;

public class GameMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6541854117698278749L;
	private ConcurrentHashMap<String, GameObject> objects = new ConcurrentHashMap<String, GameObject>();
	private transient BufferedImage background;
	private transient ObjectFactory factory;
	private transient CommunicationBuffer outBuff;
	private transient Object mapLock = new Object();
	
	public GameMap() {
		loadBackGround();
	}
	
	public GameMap(Broadcaster messenger, ObjectFactory factory) {
		this.factory = factory;
		this.outBuff = messenger.getMainOutbound();
		loadBackGround();				
	}
	
	public void addObject(GameObject objectToBeAdded) {
			objects.put(objectToBeAdded.getID(), objectToBeAdded);
	}

	public GameObject getObject(String ID) {
			return objects.get(ID);
	}
	
	public Rectangle betBounds() {
		Tank tank = new Tank("test", 0, 0);
		return new Rectangle(tank.getWidth(), tank.getHeight(), 900 - 2 * tank.getWidth(), 900 - 2 * tank.getHeight());
	}

	public void removeObject(String objectID) {
			objects.remove(objectID);

	}
	
	public BufferedImage getBackground() {
		return background;
	}
	
	public void doYourStuff(GameObject object) {
			objects.remove(object.getID());
			objects.put(object.getID(), object);
			outBuff.addMessage(new Message(this));
	}

	public GameMap doYourStuffDontSend(GameObject object) {
			objects.remove(object.getID());
			objects.put(object.getID(), object);
			return this;
	}

	private void loadBackGround() {
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
	}

	public ConcurrentHashMap<String, GameObject> getObject() {
			return objects;
	}
	
}
