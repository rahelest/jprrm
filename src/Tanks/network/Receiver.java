package Tanks.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receiver extends Thread {
	
	private Socket sock;
	private BufferedReader netIn;
	CommunicationBuffer in = new CommunicationBuffer();
	
	public Receiver(Socket sock) throws IOException {
		this.sock = sock;
		
		netIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		start();
	}
	
	public void run() {
		while (true) {
			String fromClient = null;
			try {
				fromClient = netIn.readLine();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (fromClient != null) {
				in.addMessage(fromClient);
		}
	}
		
	}
}
