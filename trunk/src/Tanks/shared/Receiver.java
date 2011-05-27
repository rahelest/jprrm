package Tanks.shared;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receiver extends Thread {
	
	private Socket sock;
	private BufferedReader netIn;
	CommunicationBuffer in;
	
	public Receiver(Socket sock, CommunicationBuffer inbound) throws IOException {
		this.sock = sock;
		this.in = inbound;
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
