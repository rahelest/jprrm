package Tanks.shared.mapElements;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Water extends UnbreakableObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -261948047857168485L;

	public Water(String string, int x, int y) {
		super(string, x, y, 150, 75, true, "water.png");
	}
	
//	public void paintComponent(Graphics g) {
//		g.drawImage(sprite, 0, 0, this);
//	}

}
