package Tanks.client;

import java.awt.Graphics;

import javax.swing.JPanel;

import Tanks.shared.mapElements.GameObject;

public class Drawer extends JPanel {
	
	GameObject object;
	
	private static final long serialVersionUID = 6934418325674765149L;

	public Drawer(GameObject object) {
		this.object = object;
		//imagesitt
	
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}
}
