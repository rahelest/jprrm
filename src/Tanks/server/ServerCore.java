package Tanks.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Tanks.shared.Broadcaster;
import Tanks.shared.CommunicationBuffer;
import Tanks.shared.CoreBase;

public class ServerCore {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {		
		int port = 8888;	
		int clientID = 1;
		
		try {
			ServerSocket serv = new ServerSocket(port);
			ActiveClients clientList = new ActiveClients();
			Broadcaster messenger = new Broadcaster(clientList);
			CommunicationBuffer inbound = new CommunicationBuffer();
			GameCore game = new GameCore(clientList, messenger, inbound);
			while (true) {
				Socket clientSock = serv.accept();			// accept() jääb ootama, kuniks luuakse ühendus
				try {
					clientList.addClient(new ClientSession(clientSock, inbound, clientID));			// loome kliendiseansi lõime ning uuesti tagasi porti kuulama
					System.out.println("Klient ühines edukalt, ID = " + clientID);
					clientID++;					
				} catch (IOException e) {
					clientSock.close();					// Kui ühendust ei loodud, sulgeme sokli
				}
			}	
		} catch (IOException e) {
			System.out.println("IO viga :" + e.getMessage());
			e.printStackTrace();
		}
	}
}
