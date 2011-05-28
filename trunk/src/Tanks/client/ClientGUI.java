package Tanks.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Tanks.shared.GameMap;

public class ClientGUI extends Thread {
	
	private ClientCore clientCore;
	private JTextField text;
	
	
	public ClientGUI(ClientCore nClientCore) {
		clientCore = nClientCore;
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1000, 980);
		window.setVisible(true);
		
		window.setLayout(new BorderLayout());
		
		JPanel top = new JPanel();
		window.add(top, BorderLayout.NORTH);
		
		text = new JTextField();
		Dimension size = new Dimension(700, 30);
		text.setPreferredSize(size);
		top.setLayout(new FlowLayout());
		top.add(text);
		
		JButton ok = new JButton("OK");
		top.add(ok);
		
		ok.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clientCore.sendIP(text.getText())) {
					clientCore.startGame();
					text.setVisible(false);
					//ALUSTA MÄNGUGA
				}// else {
//					enableConnecting();
//				}
				System.out.println(text.getText());
			}
		});
	}
	
	public void enableConnecting() {
		text.setVisible(true);
	}
	
	public void play() {
		new GameMap();
	}

}
