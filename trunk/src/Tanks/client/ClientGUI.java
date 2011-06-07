package Tanks.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
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
	 * The center panel where the tanks are.
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
	 * This is the list that displays current scores.
	 */
	protected JList scores = new JList();
	/**
	 * The size of the main window.
	 */
	private Dimension mainSize = new Dimension(1000, 900);
	
	/**
	 * The constructor for the GUI class.
	 * @param nClientCore The client core pointer.
	 */
	public ClientGUI(ClientCore nClientCore) {
		setName("ClientGUI");
		clientCore = nClientCore;
		window.setFocusable(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(mainSize);
		window.getContentPane().setLayout(new BorderLayout());
		window.getContentPane().add(top, BorderLayout.NORTH);
		window.getContentPane().add(center, BorderLayout.CENTER);
		window.getContentPane().add(scores, BorderLayout.EAST);
		center.setLayout(null);
		center.setDoubleBuffered(true);
		center.setFocusable(true);
		center.setRequestFocusEnabled(true);
		center.grabFocus();
		
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clientCore.sendIP(text.getText())) {
					text.setVisible(false);
					ok.setVisible(false);
					center.addKeyListener(new KeyListen());
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
//		start();
		window.setVisible(true);
		window.toFront();
	}
	
	/**
	 * The thread's runner method.
	 */
	public void run() {
//		BufferedImage buffer = new BufferedImage(900, 900, BufferedImage.TYPE_BYTE_INDEXED);
//		BufferedImage onScreen = new BufferedImage(900, 900, BufferedImage.TYPE_BYTE_INDEXED);
//		buffer.setAccelerationPriority(1);
//		Graphics2D bufferG = buffer.createGraphics();
//		Graphics2D onScreenG = onScreen.createGraphics();
//		Boolean drawBuffer = true;
		map = clientCore.getMap();
		center.add(map, JLayeredPane.DEFAULT_LAYER);
		while (true) {
			center.removeAll();
			map = clientCore.getMap();
			
			ConcurrentHashMap<String, GameObject> objects = map.getObjects();
			objects.putAll(map.getMissiles());
//			Set<String> keys = objects.keySet();			
			for (GameObject obj : objects.values()) {
//				drawObject(objects.get(k));				
//				GameObject obj = objects.get(k);
				if (obj instanceof UnbreakableObject || obj instanceof BreakableObject) {
					center.add(obj, JLayeredPane.MODAL_LAYER);
				} else if (obj instanceof MapDecorObject) {
					center.add(obj, JLayeredPane.POPUP_LAYER);
				}
//				System.out.println(objects.get(k));
				
			}
//			buffer.flush();
//			JLabel picLabel = new JLabel(new ImageIcon(buffer));
////			center.validate();
//			if (drawBuffer) {
//				center.paintAll(bufferG);
//				picLabel = new JLabel(new ImageIcon(onScreen));
//			} else {
//				center.paintAll(onScreenG);
//				picLabel = new JLabel(new ImageIcon(buffer));
//			}
//			drawBuffer = !drawBuffer;			
//			window.add(picLabel);
//			window.repaint();
			center.validate();
//			center.repaint();
			try {
				synchronized (this) {
					wait();
				}
			} catch (InterruptedException e) { }
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