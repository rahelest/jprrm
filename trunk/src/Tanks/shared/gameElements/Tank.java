package Tanks.shared.gameElements;

import Tanks.shared.mapElements.BreakableObject;
import java.awt.*;

public class Tank extends BreakableObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3912186180720259310L;
	protected String direction = "N";
	
	public Tank(String ID, int x, int y) {
		super(ID, x, y, 60, 30, "tankEAST.png");
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(sprite, 0, 0, this);
		 Graphics2D g2d = (Graphics2D) g;
		 g2d.translate(getWidth()/2, getHeight()/2);
		 g2d.rotate(3);
//		 g2d.tra
	}

}
