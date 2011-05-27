package Tanks.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import Tanks.shared.gameElements.Tank;
import Tanks.shared.mapElements.GameObject;

public class ClientSession extends Thread {
	private Socket sock;
	private PrintWriter netOut;
	private GameObject tank;
	private int tankspeed = 1;
	private int missilespeed = 1;
	private int exp = 0;
	
	
	public ClientSession(Socket sock) throws IOException {
		this.sock = sock;

		netOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())), true);		

		// Kui voogude loomine ebaõnnestub, peab väljakutsuv meetod 
		// sokli sulgema. Kui lõim läks käima, vastutab lõim selle eest
		
		start();
	}
	
	public void sendMessage(String message) {
		netOut.write(message);
	} 
	
	public void run() {
		tank = new Tank(100, 100);
		while(true) {
			//do something, pole kindel mida
		}
	}
	
}
