package Tanks.shared.mapElements;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public abstract class GameObject extends JPanel implements ObjectBase, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8547305178396375911L;
	protected String ID;
//	protected int locationX;
//	protected int locationY;
//	protected int width;
//	protected int height;
	protected transient boolean passable;
	protected transient boolean bulletPassable;
	protected transient boolean breakable;
	protected transient HashSet<Integer> thisXcoord = new HashSet<Integer>();
	protected transient HashSet<Integer> thisYcoord = new HashSet<Integer>();
	
	protected String image;
	protected transient BufferedImage sprite;
	
	public GameObject(String ID, int x, int y, int width, int height, boolean passability, boolean bPassability, boolean breakability, String image) {
		setLayout(null);
		setBackground(new Color(0,0,0,0));
		this.ID = ID;
		setLocation(x, y);
		setSize(width, height);
		this.passable = passability;
		this.bulletPassable = bPassability;
		this.image = image;
		createX();
		createY();
	}
	
	public void loadImage() {
		try {
			sprite = ImageIO.read(new File("src//" + image));
		} catch (FileNotFoundException e) {
			System.out.println("The backround image was not found - missing file!");
			e.printStackTrace();
		} catch (IIOException e) {			
			System.out.println("The background image could not be loaded - image error!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("General IO exception reading background image!");
			e.printStackTrace();			
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(sprite, 0, 0, this);
	}

	public String getID() {
		return ID;
	}
	
	public boolean getCollision(GameObject otherObject) {
		if(checkCoordinates(otherObject, this.getX(), getWidth()) && checkCoordinates(otherObject, getY(), getHeight())) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkCoordinates(GameObject otherObject, int corner, int size) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
//		int tempInt = width;
		int x = 2;
		
		temp.add(corner); temp.add(corner + size);
		if (getCollision(otherObject, temp)) {
			return true;
		}
		temp.clear();
//		System.out.println(width * 0 + " " + width);
		
		while(x <= size / 2) {
			for (int i = 1; i < x; i += 1) {
				temp.add(corner + (size / x) * i);
			}
			if (getCollision(otherObject, temp)) {
				return true;
			}
			temp.clear();
//			System.out.println();
			x = x * 2;
		}
		for (int i = 1; i < size; i++) {
			temp.add(corner + i);
		}
		if (getCollision(otherObject, temp)) {
			return true;
		}
		return false;
		
	}

	private void createX() {
		for (int i = getX(); i <= (getX() + getWidth()); i++) {
			this.thisXcoord.add(i);
		}
	}
	
	private void createY() {
		for (int i = getY(); i <= (getY() + getHeight()); i++) {
			this.thisXcoord.add(i);
		}
	}
	
	private boolean getCollision(GameObject otherObject, ArrayList<Integer> temp) {
		if(!passable) {
			for (int i : temp) {
				if (otherObject.getXset().contains(i)) {
					return true;
				}
			}
			for (int i : thisYcoord) {
				if (otherObject.getYset().contains(i)) {
					return true;
				}
			}
// nt kui moodustab objectidest setid ja siis hakkab ühekaupa kumbagi läbi käima, for each if contains....
		}
		return false;
	}

	private HashSet<Integer> getXset() {
		return thisXcoord;
	}
	
	private HashSet<Integer> getYset() {
		return thisYcoord;
	}
	
	public boolean isBreakable() {
		return breakable;
	}
}
