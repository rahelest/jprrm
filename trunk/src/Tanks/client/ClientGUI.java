package Tanks.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Tanks.shared.GameMap;
import Tanks.shared.gameElements.Tank;
import Tanks.shared.mapElements.GameObject;
import Tanks.shared.mapElements.Tree;
import Tanks.shared.mapElements.Water;

public class ClientGUI extends Thread {
	
	private ClientCore clientCore;
	private JFrame window = new JFrame();
	private JTextField text = new JTextField("localhost:8888");
	private JPanel top = new JPanel();
	private JPanel center = new JPanel();
	private JButton ok = new JButton("OK");
	private GameMap map;
	private GameObject tank;	
	
	public ClientGUI(ClientCore nClientCore) {
		setName("ClientGUI");
		clientCore = nClientCore;
		window.setFocusable(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(400, 500);
		window.setVisible(true);
		window.getContentPane().setLayout(new BorderLayout());
		window.getContentPane().add(top, BorderLayout.NORTH);
		window.getContentPane().add(center, BorderLayout.CENTER);
		center.setLayout(null);
		ok.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clientCore.sendIP(text.getText())) {
//					System.out.println("PRINT");
					text.setVisible(false);
					ok.setVisible(false);
					window.repaint();
				} else {
					System.out.println("Something went wrong, please check the address.");
					enableConnecting();
				}
//				System.out.println(text.getText());
			}
		});
//		Dimension size = new Dimension(700, 30);
//		text.setPreferredSize(size);
		top.setLayout(new BorderLayout());		
		top.add(ok, BorderLayout.EAST);
		top.add(text, BorderLayout.CENTER);		
		window.addKeyListener(new KeyListen());
		start();
	}
	
	public void run() {
		while(true) {
			map = clientCore.getMap();
			sendForDrawing(map);
			center.repaint();
			try {
				synchronized(this) {
					wait();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}
	}
	
	private void sendForDrawing(GameMap map) {
		center.removeAll();
		HashMap<String, GameObject> objects = map.getObject();
		Set<String> keys = objects.keySet();
		for (String k : keys) {
			drawObject(objects.get(k));
//			System.out.println(objects.get(k));
		}
	}

	public void drawObject(GameObject obj) {
		obj.loadImage();
//		System.out.println(obj.getClass().getSimpleName());
//		obj.setSize(obj.getWidth(), obj.getHeight());
//		System.out.println(obj.getSize());
//		obj.setLocation(obj.getX(), obj.getY());
//		System.out.println(obj.getX() + " " + obj.getY());
//		obj.setBackground(Color.BLACK);

		center.add(obj);
//		center.repaint();
		
	}
	
	public void enableConnecting() {
		text.setVisible(true);
		ok.setVisible(true);
	}
	
	public static void main(String[] args) {
		ClientGUI gui = new ClientGUI(null);
		gui.map = new GameMap();
		gui.tank = new Tank("hj", 100, 100);
		gui.map.addObject(new Water("we", 32, 150));
		gui.map.addObject(new Tank("we2", 43, 244));
		gui.map.addObject(new Tree("we3", 143, 244));
		
		gui.sendForDrawing(gui.map);
//		GameObject water = new Water("hj2", 100, 100);
//		GameObject water2 = new Water("hj3", 300, 300);
		gui.drawObject(gui.tank);
//		gui.drawObject(water);
//		gui.drawObject(water2);
//		System.out.println("Test: kollitud " + gui.tank.getCollision(water));
//		System.out.println("Test: kollimata " + gui.tank.getCollision(water2));
	}

	class KeyListen implements KeyListener {
	
		@Override
		public void keyPressed(KeyEvent key) {
			try{
				int code = key.getKeyCode();
				if (code == KeyEvent.VK_UP) {
					System.out.println("UP!");
					clientCore.moveNorth();
//					tank.setLocation(tank.getX(), tank.getY() - 2);
//					System.out.println("Tank " + tank.getY()) ;
				} else if (code == KeyEvent.VK_DOWN) {
					System.out.println("DOWN!");
					clientCore.moveSouth();
//					tank.setLocation(tank.getX(), tank.getY() + 2);
				} else if (code == KeyEvent.VK_LEFT) {
					System.out.println("LEFT!");
					clientCore.moveEast();
//					tank.setLocation(tank.getX() - 2, tank.getY());
				} else if (code == KeyEvent.VK_RIGHT) {
					System.out.println("RIGHT!");
					clientCore.moveWest();
//					tank.setLocation(tank.getX() + 2, tank.getY());
				} else if (code == KeyEvent.VK_SPACE) {
					System.out.println("FIRE!");
					clientCore.fire();
				}
			} catch (NullPointerException e) {}
//				System.out.println(tank);
//				drawObject(tank);
//				tank.repaint();
//				System.out.println(tank.getX() + " " + tank.getY());
//				center.repaint();
		}
		@Override
		public void keyReleased(KeyEvent key) {
			
		}
	
		@Override
		public void keyTyped(KeyEvent key) {
			
		}
	}
}