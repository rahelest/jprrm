package Tanks.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Tanks.shared.GameMap;
import Tanks.shared.mapElements.GameObject;

public class ClientGUI {
	
	private ClientCore clientCore;
	JFrame window = new JFrame();
	private JTextField text = new JTextField();
	JPanel top = new JPanel();
	JButton ok = new JButton("OK");
	
	
	public ClientGUI(ClientCore nClientCore) {
		clientCore = nClientCore;
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1000, 980);
		window.setVisible(true);
		window.setLayout(new BorderLayout());
		window.add(top, BorderLayout.NORTH);

		Dimension size = new Dimension(700, 30);
		text.setPreferredSize(size);
		top.setLayout(new FlowLayout());
		top.add(text);

		top.add(ok);
		
		ok.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clientCore.sendIP(text.getText())) {
					clientCore.startGame();
					text.setVisible(false);
					ok.setVisible(false);
					//ALUSTA MÄNGUGA
				} else {
					System.out.println("Something went wrong, please check the address.");
					enableConnecting();
				}
				System.out.println(text.getText());
			}
		});
	}
	
	public void enableConnecting() {
		text.setVisible(true);
		ok.setVisible(true);
	}
	
	public void play() {
		new GameMap();
	}

	public void drawObject(GameObject object) {
		JPanel obj = new JPanel();
		obj.setBackground(Color.BLACK);
		obj.setSize(object.getWidth(), object.getHeight());
		obj.setLocation(object.getLocationX(), object.getLocationY());
		window.add(obj);
		
	}
	
	public static void main(String[] args) {
		new ClientGUI(null);
	}

}
