package Tanks.server;

import Tanks.shared.Broadcaster;
import Tanks.shared.CommunicationBuffer;

public class GameCore extends Thread {

	ActiveClients clientList;
	Broadcaster messenger;
	CommunicationBuffer inbound;
	
	public GameCore(ActiveClients clientList, Broadcaster messenger, CommunicationBuffer inbound) {
		this.clientList = clientList;
		this.messenger = messenger;
		this.inbound = inbound;
		start();
	}

	public void run() {
		//siia tuleb mĆ¤nguloogika
		while(true) {
			getInput();
			updateLocations();
			checkCollisions();
			broadcastResult();
		}
		//Input - collect player input
		//Control - update player and AI logic
		//Movement - move all objects to their desired locations
		//Collision - check for and resolve overlap
		//Render - render objects in your game world
		//Overlay - render interface elements
	}
	
	private void getInput() {
		inbound.getMessage();
	}
	
	private void updateLocations() {
		
	}
	
	private void checkCollisions() {
		
	}
	
	private void broadcastResult() {
		
	}
}
