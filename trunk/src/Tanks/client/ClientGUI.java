package Tanks.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

public class ClientGUI {
	
	private ClientCore clientCore;
	JFrame window = new JFrame();
	private JTextField text = new JTextField("localhost:8888");
	JPanel top = new JPanel();
	JPanel center = new JPanel();
	JButton ok = new JButton("OK");
	GameMap map;
	
	
	public ClientGUI(ClientCore nClientCore) {
		clientCore = nClientCore;
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1000, 980);
		window.setVisible(true);
		window.setLayout(new BorderLayout());
		window.add(top, BorderLayout.NORTH);
		window.add(center, BorderLayout.CENTER);
		center.setLayout(null);

//		Dimension size = new Dimension(700, 30);
//		text.setPreferredSize(size);
		top.setLayout(new BorderLayout());
		
		top.add(ok, BorderLayout.EAST);
		top.add(text, BorderLayout.CENTER);
		
		ok.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clientCore.sendIP(text.getText())) {
//					clientCore.startGame();
					text.setVisible(false);
					ok.setVisible(false);
					clientCore.startGame();
					//ALUSTA MÄNGUGA
//					window.repaint();
				} else {
					System.out.println("Something went wrong, please check the address.");
					enableConnecting();
				}
				System.out.println(text.getText());
			}
		});
		window.addKeyListener(new KeyListen());
	}
	
	public void enableConnecting() {
		text.setVisible(true);
		ok.setVisible(true);
	}

	public void drawObject(GameObject obj) {
		System.out.println(obj);
		obj.setSize(obj.getWidth(), obj.getHeight());
		obj.setLocation(obj.getLocationX(), obj.getLocationY());
//		obj.setBackground(Color.BLACK);
		
		center.add(obj);
//		window.repaint();
		
	}
	
	public static void main(String[] args) {
		ClientGUI gui = new ClientGUI(null);
		gui.drawObject(new Tank("hj", 100, 100));
	}

	class KeyListen implements KeyListener {
	
		@Override
		public void keyPressed(KeyEvent key) {
			int code = key.getKeyCode();
			if (code == KeyEvent.VK_UP) {
				System.out.println("You pressed up");
				clientCore.moveNorth();
			} else if (code == KeyEvent.VK_DOWN) {
				System.out.println("Down!");
				clientCore.moveSouth();
			} else if (code == KeyEvent.VK_LEFT) {
				System.out.println("LEFT!");
				clientCore.moveEast();
			} else if (code == KeyEvent.VK_RIGHT) {
				System.out.println("Right!");
				clientCore.moveWest();
			} else if (code == KeyEvent.VK_SPACE) {
				System.out.println("FIRE!");
				clientCore.fire();
			}
		}
		@Override
		public void keyReleased(KeyEvent key) {
			}
	
		@Override
		public void keyTyped(KeyEvent key) {
		}}
}