package Tanks.shared;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Tanks.shared.mapElements.GameObject;

/**
 * The class that keeps all the game objects.
 * @author JPRRM
 *
 */
public class GameMap extends JPanel implements Serializable {

	/**
	 * An unique serial number.
	 */
	private static final long serialVersionUID = -6541854117698278749L;
	/**
	 * List with the game objects.
	 */
	private ConcurrentHashMap<String, GameObject> objects = new ConcurrentHashMap<String, GameObject>();
	/**
	 * List with missiles.
	 */
	private ConcurrentHashMap<String, GameObject> missiles = new ConcurrentHashMap<String, GameObject>();

	/**
	 * The background image.
	 */
	private transient BufferedImage background;
	/**
	 * The outbound buffer.
	 */
	private transient CommunicationBuffer outBuff;
	
	/**
	 * The constructor.
	 */
	public GameMap() {
		setSize(900, 900);
		loadBackGround();
	}
	
	/**
	 * The new constructor.
	 * @param messenger The broadcaster.
	 */
	public GameMap(Broadcaster messenger) {
		this.outBuff = messenger.getMainOutbound();
		setSize(900, 900);
		loadBackGround();				
	}
	
	/**
	 * Paints the image.
	 * @param g The graphins component.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this);
	}
	
	/**
	 * Adds a new object.
	 * @param objectToBeAdded The new object.
	 */
	public void addObject(GameObject objectToBeAdded) {
			objects.put(objectToBeAdded.getID(), objectToBeAdded);
	}

	/**
	 * Returns an object.
	 * @param ID The ID of the returnable object.
	 * @return The object.
	 */
	public GameObject getObject(String ID) {
			return objects.get(ID);
	}
	
//	/**
//	 * Returns the bounds.
//	 * @return A rectangle.
//	 */
//	public Rectangle betBounds() {
//		Tank tank = new Tank("test", 0, 0);
//		return new Rectangle(tank.getWidth(), tank.getHeight(),
//				900 - 2 * tank.getWidth(), 900 - 2 * tank.getHeight());
//	}
	
	

	/**
	 * Removes the object from the list.
	 * @param objectID The object's id.
	 */
	public void removeObject(String objectID) {
			objects.remove(objectID);

	}
		
	/**
	 * Replaces one of the objects.
	 * @param object The new object.
	 */
	public void doYourStuff(GameObject object) {
			objects.remove(object.getID());
			objects.put(object.getID(), object);
			outBuff.addMessage(new Message(this));
	}

	/**
	 * Loads the background.
	 */
	public void loadBackGround() {
		try {
			background = ImageIO.read(new File("src//background.png"));
		} catch (FileNotFoundException e) {
			System.out.println("The background image was not found - missing file!");
			e.printStackTrace();
		} catch (IIOException e) {			
			System.out.println("The background image could not be loaded - image error!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("General IO exception reading background image!");
			e.printStackTrace();			
		}
	}

	/**
	 * Returns the objects list.
	 * @return The list.
	 */
	public ConcurrentHashMap<String, GameObject> getObjects() {
			return objects;
	}
	
	/**
	 * Returns the missiles list.
	 * @return The list.
	 */
	public ConcurrentHashMap<String, GameObject> getMissiles() {
		return missiles;
	}

	/**
	 * Adds new missiles to the list after clearing it.
	 * @param nMissiles The new missiles.
	 */
	public void addMissiles(ConcurrentHashMap<String, GameObject> nMissiles) {
		this.missiles.clear();
		this.missiles.putAll(nMissiles);
	}	
}
