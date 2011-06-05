package Tanks.shared;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import Tanks.server.ObjectFactory;
import Tanks.shared.mapElements.GameObject;
import Tanks.shared.mapElements.Water;

public class GameMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6541854117698278749L;
	private HashMap<String, GameObject> objects = new HashMap<String, GameObject>();
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
		GameObject temp = factory.createWater(200, 200);
		objects.put(temp.getID(), temp);
		temp = factory.createWater(800, 200);
		objects.put(temp.getID(), temp);
		temp = factory.createWater(800, 800);
		objects.put(temp.getID(), temp);
		temp = factory.createWater(200, 800);
		objects.put(temp.getID(), temp);
	}
	
	public void addObject(GameObject objectToBeAdded) {
		synchronized (mapLock) {
			objects.put(objectToBeAdded.getID(), objectToBeAdded);
		}
	}

	public GameObject getObject(String ID) {
		synchronized (mapLock) {
			return objects.get(ID);
		}
	}

	public void removeObject(String objectID) {
		synchronized (mapLock) {
			objects.remove(objectID);
		}
	}
	
	public BufferedImage getBackground() {
		return background;
	}
	
	public void doYourStuff(GameObject object) {
		synchronized (mapLock) {
			objects.remove(object.getID());
			objects.put(object.getID(), object);
			outBuff.addMessage(new Message(this));
		}
	}

	public GameMap doYourStuffDontSend(GameObject object) {
		synchronized (mapLock) {
			objects.remove(object.getID());
			objects.put(object.getID(), object);
			return this;
		}
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

	public synchronized HashMap<String, GameObject> getObject() {
			return objects;
	}
	
}
