package Tanks.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextField;

import Tanks.shared.*;

public class ClientCore {

	ClientGUI gui;
	CommunicationBuffer comBuf;
	Broadcaster broadcaster;
	Receiver receiver;

	public ClientCore() {
		
		gui = new ClientGUI(this);
		
		
		
		
	}
	
	
	public static void main(String[] args) throws InterruptedException{
		
		new ClientCore();
		
		try {
			// serveri nimi ning port
			String serverName = "localhost";
			int port = 8888;
			
			// Ühendumiseks vajalik abiobjekt. Kui serverinimi ei ole DNSi abil lahenduv, heidetakse UnknownHostException.
			InetAddress serverAddr = InetAddress.getByName(serverName);
			
			try {
				Socket sock = new Socket(serverAddr, port);
				
				try {
					System.out.println("Sain ühenduse serveriga " + serverAddr.getHostName());
					
					// küsime sokli sisend- ja väljundvood ning loome neile lugejad-kirjutajad
					BufferedReader netIn = new BufferedReader(
							new InputStreamReader(
									sock.getInputStream()));
				
					PrintWriter netOut = new PrintWriter(
								new BufferedWriter(
										new OutputStreamWriter(
												sock.getOutputStream())), true);
	
					
					// kuulame, kas serveril on meile midagi öelda:
					String fromServer = netIn.readLine();
					
					System.out.println("Sain teate: " + fromServer);
					
					// Kliendi (veidi lihtsakoeline) töötsükkel:
					for (int i = 0 ; i < 5 ; i++) {
						// vormistame teate ja saadame serverisse
						String response = "Teade number " + i;
						System.out.println("Saadan serverisse: " + response);
						netOut.println(response);

						// ning kuulame, mida server selle peale kostab
						fromServer = netIn.readLine();
						System.out.println("Sain serverist: " + fromServer);
					}
					
					// Andmevahetusprotokoll näeb ette, et klient annab käsu ühendus lõpetada
					// Kui siin sokkel jõuga kinni panna, siis server saab readLine() meetodis erindi
					
					System.out.println("Saadan serverisse: QUIT");
					netOut.println("QUIT");
				
				}
				finally {
					sock.close();		// Sokli sulgemine suleb ka tema sisend-väljundvood.
				}	
				
			} catch (ConnectException e) {
				System.out.println("Ei õnnestu luua ühendust serveriga " + serverAddr.getHostName() + ". Põhjus: " + e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (UnknownHostException e) {
			System.out.println("Tundmatu server: " + e.getMessage());
		}
}

		public boolean sendIP(String string) {
			String[] splitted = string.split(":");
			if (splitted.length != 2) return false;
			else {
				splitted[0] = serverName;
				splitted[1] = port;
				
			}
		}

		public void startGame() {
			// TODO Auto-generated method stub
			while(true) {
				updateGUI();
			}
		}
	}
