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

	private ClientGUI gui;
	private Socket sock;
	private int connectionTries = 0;
	private CommunicationBuffer comBuf;
	private Broadcaster broadcaster;
	private Receiver receiver;

	public ClientCore() {
		gui = new ClientGUI(this);	
	}
	
	public void execute() {
		
		//MUIDU PEAKS TA NENDE RECEIVERITEGA TEGELEMA, NEID AVAMA
		BufferedReader netIn = new BufferedReader(
				new InputStreamReader(
						sock.getInputStream()));
	
		PrintWriter netOut = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									sock.getOutputStream())), true);
		//anna guile vastuvõtja viide
		while (true) {
			receiveInstructions();
			passGUIInstructions();
			makeGUIDraw();
		}
	}
	
	
	public static void main(String[] args) {
		
		new ClientCore();

//						System.out.println("Saadan serverisse: " + response);
//						netOut.println(response);
	}



	
	public boolean sendIP(String string) {
		String[] splitted = string.split(":");
		if (splitted.length != 2) return false;
		else {
			String serverName = splitted[0];
			try {
				int port = Integer.parseInt(splitted[1]);
				if (connectToServer(serverName, port)) {
					//startgame-----------------------------------------------------------------
					return true;
				} else {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}
		}
	}
		
	public boolean connectToServer(String serverName, int port) {
		try {
			InetAddress serverAddr = InetAddress.getByName(serverName);
			sock = new Socket(serverAddr, port);
		} catch (UnknownHostException e) {
			gui.enableConnecting();
			//tevita vigasest adrst
			return false;
		} catch (IOException e) {
			if (connectionTries < 3) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) { }
				connectionTries++;
				connectToServer(serverName, port);
				e.printStackTrace();
			} else {
				connectionTries = 0;
				//teavita
				return false;
			}
		} finally {
			try {
				sock.close();
			} catch (IOException e) {}
		}
		execute();
		return true;
	}
	
	public void startGame() {
		// TODO Auto-generated method stub
		while(true) {
//				updateGUI();
		}
	}
	
	
}
