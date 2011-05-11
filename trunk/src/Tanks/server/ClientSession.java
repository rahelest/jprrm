package Tanks.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

class ClientSession extends Thread {
	private Socket sock;
	private BufferedReader netIn;
	private PrintWriter netOut;
	
	
	public ClientSession(Socket sock) throws IOException {
		this.sock = sock;
		
		netIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));

		netOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())), true);		

		// Kui voogude loomine ebaõnnestub, peab väljakutsuv meetod 
		// sokli sulgema. Kui lõim läks käima, vastutab lõim selle eest
		
		start();
	}
	
	public void sendMessage(String message) {
		netOut.write(message);
	}
	
	public void run() {
		
	}
	
}
