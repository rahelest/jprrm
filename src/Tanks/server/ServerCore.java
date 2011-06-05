package Tanks.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import Tanks.shared.Broadcaster;
import Tanks.shared.GameMap;
import Tanks.shared.mapElements.GameObject;

public class ServerCore {
	
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
							exSessPointer.updateOnReconnect(clientSock);
							exSessPointer.notify();
							System.out.println(exSessPointer);
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
	
	public GameMap createMap(Broadcaster messenger, ObjectFactory factory, int water, int tree, int brick, int iron) {
		GameMap map = new GameMap(messenger, factory);
		Random rand = new Random();
		
		GameObject object2 = factory.createIronWall(rand.nextInt(900), rand.nextInt(900));
		map.addObject(object2);
		for (int i = 1; i < iron; i++) {
			GameObject object = factory.createIronWall(object2.getX() + object2.getWidth() + 1, object2.getY());
			map = addingToMap(map, object);
			object2 = object;
		}
		
		for (int i = 0; i < brick; i++) {
			GameObject object = factory.createBrickWall(rand.nextInt(900), rand.nextInt(900));
			map = addingToMap(map, object);
		}
		
		for (int i = 0; i < tree; i++) {
			GameObject object = factory.createTree(rand.nextInt(900), rand.nextInt(900));
			map = addingToMap(map, object);
		}
		
		for (int i = 0; i < water; i++) {
			GameObject object = factory.createWater(rand.nextInt(900), rand.nextInt(900));
			map = addingToMap(map, object);
		}
		
		return map;
	}
	
	private GameMap addingToMap(GameMap map, GameObject object) {
		Random rand = new Random();
		while (true) {
			if (!object.checkCollision(map)) {
				map.addObject(object);
				return map;
			} else {
				object.setLocation(rand.nextInt(900), rand.nextInt(900));
			}
		}
	}
}
