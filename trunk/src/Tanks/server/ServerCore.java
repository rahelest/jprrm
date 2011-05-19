package Tanks.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Tanks.network.Broadcaster;

public class ServerCore {
	
	public static void main(String[] args) {		
		int port = 8888;
		ActiveClients clientList = new ActiveClients();
//		OutboundBuffer outBound = new OutboundBuffer();
		Broadcaster messenger = new Broadcaster(clientList);
		
		try {
			ServerSocket serv = new ServerSocket(port);
			
			while (true) {
				Socket clientSock = serv.accept();			// accept() jääb ootama, kuniks luuakse ühendus
				try {
					clientList.addClient(new ClientSession(clientSock));			// loome kliendiseansi lõime ning uuesti tagasi porti kuulama
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
