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
	private CommunicationBuffer inBuf;
	private CommunicationBuffer outBuf;
	private Broadcaster broadcaster;
	private Receiver receiver;

	public ClientCore() {
		gui = new ClientGUI(this);	
	}

	
	public void startGame() {
		
		inBuf = new CommunicationBuffer();
		try {
			receiver = new Receiver(sock, inBuf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//küsi kliente, stardi broadcaster
		//anna guile vastuvõtja viide
//		while (true) {
//			receiveInstructions();
//			passGUIInstructions();
//			makeGUIDraw();
//			//updateGUI();
//		}
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
			System.out.println("There is something wrong with the address");
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
				System.out.println("Connecting failed");
				return false;
			}
		}
		System.out.println("Connection established!");
		return true;
	}
	
	private void receiveInstructions()  {
		inBuf.getMessage();
	}
	
	public static void main(String[] args) {
		
		new ClientCore();

//						System.out.println("Saadan serverisse: " + response);
//						netOut.println(response);
	}
		
}
