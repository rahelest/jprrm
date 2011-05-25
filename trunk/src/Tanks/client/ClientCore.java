package Tanks.client;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ClientCore {
	public static void main(String[] args) throws InterruptedException{
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(300, 300);
		window.setVisible(true);
		
		JLabel label1 = new JLabel("Tere graafika!");
		window.add(label1);
		
	}
}
