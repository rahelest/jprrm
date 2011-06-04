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
	public HashMap<String, GameObject> objects = new HashMap<String, GameObject>();
	private transient BufferedImage background;
	private transient ObjectFactory factory;
	private transient CommunicationBuffer outBuff;
	
	public synchronized void addObject(GameObject objectToBeAdded) {
		objects.put(objectToBeAdded.getID(), objectToBeAdded);
	}

	public synchronized GameObject getObject(String ID) {
		return objects.get(ID);
	}

	public synchronized void removeObject(String objectID) {
		objects.remove(objectID);
	}
	
	public BufferedImage getBackground() {
		return background;
	}
	
	public synchronized void doYourStuff(GameObject object) {
		objects.remove(object.getID());
		objects.put(object.getID(), object);
		outBuff.addMessage(new Message(this));
	}

	public synchronized GameMap doYourStuffDontSend(GameObject object) {
		objects.remove(object.getID());
		objects.put(object.getID(), object);
		return this;
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

	public GameMap() {
		loadBackGround();
	}

	public HashMap<String, GameObject> getObject() {
		return objects;	
	}

	
}
