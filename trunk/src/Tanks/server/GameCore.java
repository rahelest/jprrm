package Tanks.server;

import Tanks.shared.Broadcaster;

public class GameCore extends Thread {

	ActiveClients clientList;
	Broadcaster messenger;
	public GameCore(ActiveClients clientList, Broadcaster messenger) {
		this.clientList = clientList;
		this.messenger = messenger;
		start();
	}

	public void run() {
		//siia tuleb mänguloogika
		while(true) {
			
		}
		//Input - collect player input
		//Control - update player and AI logic
		//Movement - move all objects to their desired locations
		//Collision - check for and resolve overlap
		//Render - render objects in your game world
		//Overlay - render interface elements
	}
}
