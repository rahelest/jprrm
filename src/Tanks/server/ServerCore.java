package Tanks.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Tanks.shared.Broadcaster;
import Tanks.shared.GameMap;

public class ServerCore {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		int port = 8888;	
		int clientID = 1;
		
		try {
			ObjectFactory factory = new ObjectFactory();
			ServerSocket serv = new ServerSocket(port);
			ActiveClients clientList = new ActiveClients();
			Broadcaster messenger = new Broadcaster(clientList);
			GameMap killingField = new GameMap(messenger, factory);
			while (true) {
				Socket clientSock = serv.accept();
				try {
					if(clientList.exists(clientSock.getInetAddress())) {
						System.out.println("clientsessioni notify algus");
						ClientSession exSessPointer = clientList.getExisting(clientSock.getInetAddress());
						synchronized(exSessPointer) {
							System.out.println("luku algus");
							exSessPointer.notify();
							}
						System.out.println("clientsessioni notify lopp");
					} else {
						clientList.addClient(new ClientSession(clientSock, killingField, clientID));			// loome kliendiseansi lõime ning uuesti tagasi porti kuulama
						System.out.println("Klient ühines edukalt, ID = " + clientID);
						clientID++;	
					}
							
				} catch (IOException e) {
					clientSock.close();
				}
			}	
		} catch (IOException e) {
			System.out.println("IO viga: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
