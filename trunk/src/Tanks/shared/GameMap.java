package Tanks.shared;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import Tanks.shared.mapElements.GameObject;

public class GameMap {

	private HashMap<String, GameObject> objects = new HashMap<String, GameObject>();
	private BufferedImage background;
	private CommunicationBuffer outBuff;
	
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
	
	public GameMap(Broadcaster messenger) {
		this.outBuff = messenger.getMainOutbound();
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
