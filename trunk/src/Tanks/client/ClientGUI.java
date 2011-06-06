package Tanks.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Tanks.shared.GameMap;
import Tanks.shared.gameElements.Tank;
import Tanks.shared.mapElements.BreakableObject;
import Tanks.shared.mapElements.GameObject;
import Tanks.shared.mapElements.MapDecorObject;
import Tanks.shared.mapElements.UnbreakableObject;

/**
 * 
 * @author JPRRM
 *
 */
public class ClientGUI extends Thread {
	
	/**
	 * The time to sleep between key types.
	 */
	private final int repressLimit = 100;
	/**
	 * The saved time of the last key press.
	 */
	private long lastKeyPressed;
	/**
	 * Pointer for the client core object.
	 */
	private ClientCore clientCore;
	/**
	 * The whole window of the GUI.
	 */
	private JFrame window = new JFrame();
	/**
	 * The text field on the window.
	 */
	private JTextField text = new JTextField("192.168.1.101:8888");
	 /**
	  * The small top panel on the window. 
	  */
	private JPanel top = new JPanel();
	/**
	 * The center paner where the the tanks are.
	 */
	private JLayeredPane center = new JLayeredPane();
	/**
	 * The button which makes the client to connect.
	 */
	private JButton ok = new JButton("OK");
	/**
	 * The map variable with the game elements.
	 */
	private GameMap map;
	/**
	 * The label with the tank name.
	 */
//	private JLabel myName = new JLabel("My tank!", JLabel.CENTER);
	
	/**
	 * The constructor for the GUI class.
	 * @param nClientCore The client core pointer.
	 */
	public ClientGUI(ClientCore nClientCore) {
		setName("ClientGUI");
		clientCore = nClientCore;
		window.setFocusable(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(800, 500);
		window.getContentPane().setLayout(new BorderLayout());
		window.getContentPane().add(top, BorderLayout.NORTH);
		window.getContentPane().add(center, BorderLayout.CENTER);
		center.setLayout(null);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clientCore.sendIP(text.getText())) {
					text.setVisible(false);
					ok.setVisible(false);
					window.addKeyListener(new KeyListen());
					window.repaint();
				} else {
					System.out.println("Something went wrong, please check the address.");
					enableConnecting();
				}
			}
		});
		top.setLayout(new BorderLayout());		
		top.add(ok, BorderLayout.EAST);
		top.add(text, BorderLayout.CENTER);
		window.setVisible(true);
	}
	
	/**
	 * The thread's runner method.
	 */
	public void run() {
		drawUnchangeable();		
		while (true) {
			ConcurrentHashMap<String, GameObject> objects = map.getObjects();
			center.add(map, new Integer(0));			
			map = clientCore.getMap();			
			objects = map.getObjects();
			objects.putAll(map.getMissiles());			
			for (GameObject obj : objects.values()) {
				obj.loadImage();
				if (obj instanceof BreakableObject) {
					center.add(obj, new Integer(1));
				}	
			}
		
			center.validate();
			center.repaint();
				
			try {
				synchronized (this) {
					wait();
				}
			} catch (InterruptedException e) { }
			center.remove(1);
			center.remove(0);
		}
		
	}
	
	/**
	 * Makes the text field and button visible again
	 * to enable the user to enter new connecting info.
	 */
	public void enableConnecting() {
		text.setVisible(true);
		ok.setVisible(true);
	}
	
	public void drawUnchangeable() {
		map = clientCore.getMap();
		System.out.println(map);
		ConcurrentHashMap<String, GameObject> objects = map.getObjects();
		for (GameObject obj : objects.values()) {
			if (obj instanceof UnbreakableObject || obj instanceof MapDecorObject) {
				obj.loadImage();
				center.add(obj, new Integer (2));
			}
		}
	}

/*
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
*/

	/**
	 * Acts upon certain pressed keys.
	 */
	class KeyListen implements KeyListener {
	
		@Override
		public void keyPressed(KeyEvent key) {
			if (System.currentTimeMillis() - lastKeyPressed > repressLimit) {
				int code = key.getKeyCode();
				if (code == KeyEvent.VK_UP) {
					clientCore.moveNorth();
				} else if (code == KeyEvent.VK_DOWN) {
					clientCore.moveSouth();
				} else if (code == KeyEvent.VK_LEFT) {
					clientCore.moveWest();
				} else if (code == KeyEvent.VK_RIGHT) {
					clientCore.moveEast();
				} else if (code == KeyEvent.VK_SPACE) {
					clientCore.fire();
				}
				lastKeyPressed = System.currentTimeMillis();
			}
		}
		
		@Override
		public void keyReleased(KeyEvent key) {
		}
	
		@Override
		public void keyTyped(KeyEvent key) {
		}
	}
}