package Tanks.shared.mapElements;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Set;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import Tanks.shared.GameMap;
import Tanks.shared.gameElements.Missile;

/**
 * The base class for all objects with the necessary methods.
 * @author JPRRM
 *
 */
public abstract class GameObject extends JPanel implements ObjectBase, Serializable {
	
	/**
	 * An unique serial number.
	 */
	private static final long serialVersionUID = 8547305178396375911L;
	/**
	 * The objects unique ID.
	 */
	protected String ID;
	/**
	 * Whether the object is passable by other objects.
	 */
	protected transient boolean passable;
	/**
	 * Whether the object is passable by missiles.
	 */
	protected transient boolean bulletPassable;
	/**
	 * Whether the object is breakable by missiles.
	 */
	protected transient boolean breakable;
	/**
	 * The object's image address.
	 */
	protected String image;
	/**
	 * The actual loaded image.
	 */
	protected transient BufferedImage sprite;
	
	/**
	 * The constructor.
	 * @param nID The unique id.
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @param width The object's width.
	 * @param height The object's height.
	 * @param passability The passability field.
	 * @param bPassability Bulletpassability field.
	 * @param breakability The breakability field.
	 * @param nImage The image address.
	 */
	public GameObject(String nID, int x, int y, int width,
			int height, boolean passability, boolean bPassability,
			boolean breakability, String nImage) {
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		this.ID = nID;
		setLocation(x, y);
		setSize(width, height);
		this.passable = passability;
		this.bulletPassable = bPassability;
		this.image = nImage;
	}
	
	/**
	 * Sets the new image address.
	 * @param nImage The address.
	 */
	public synchronized void setImage(String nImage) {
		this.image = nImage;
	}
	
	/**
	 * Asks the address.
	 * @return The address.
	 */
	public synchronized String getImage() {
		return image;
	}
	
	/**
	 * Loads the image to the sprite.
	 */
	public void loadImage() {
		if(sprite == null) {
					try {
				sprite = ImageIO.read(new File("src//" + image));
			} catch (FileNotFoundException e) {
				System.out.println("The image " + image + " was not found - missing file!");
				e.printStackTrace();
			} catch (IIOException e) {			
				System.out.println("The image " + image + " could not be loaded - image error!");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("General IO exception reading image!");
				e.printStackTrace();			
			}
		}
		
	}
	
	/**
	 * Paints the image.
	 * @param g The graphins component.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(sprite, 0, 0, this);
	}
	
	/**
	 * Returns the object's bounds.
	 * @return The bounds rectangle.
	 */
	public Rectangle getBounds() {
	    return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	/**
	 * Asks the object's ID.
	 * @return The ID.
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Checks for collision with other objects.
	 * @param map The map with the objects.
	 * @return The object collided with.
	 */
	public GameObject checkCollision(GameMap map) {
		
//		ConcurrentHashMap mappy = map.getObject().clone();
		Set<String> keys = map.getObjects().keySet();
		keys.remove(ID);
		Boolean tempBool;
		if (this instanceof Missile) {
			tempBool = isBulletPassable();
		} else {
			tempBool = isPassable();
		}
		for (String s : keys) {
			GameObject tempPointer = map.getObject(s);
			if (!tempBool && getBounds().intersects(tempPointer.getBounds())) {
				return tempPointer;
			}
//			if(!getBounds().intersects(map.betBounds())) {
//				return true;
//			}
			//TODO: KIRJUTA KAARDIÄÄREKONTROLIJA
		}
		return null;
	}
	
	/**
	 * Returns the breakable field value.
	 * @return The field.
	 */
	public boolean isBreakable() {
		return breakable;
	}

	/**
	 * Applies received damage.
	 */
	public void getDamaged() {
		//TODO 
		System.out.printf(this + ": \nsain pihta!\n\n");
		
	}

	/**
	 * @return the passable
	 */
	public boolean isPassable() {
		return passable;
	}

	/**
	 * @return the bulletPassable
	 */
	public boolean isBulletPassable() {
		return bulletPassable;
	}
	
}
