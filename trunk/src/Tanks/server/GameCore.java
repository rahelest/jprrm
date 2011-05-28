package Tanks.server;

import Tanks.shared.Broadcaster;
import Tanks.shared.CommunicationBuffer;
import Tanks.shared.GameMap;
import Tanks.shared.Message;
import Tanks.shared.mapElements.GameObject;

public class GameCore extends Thread {

	ActiveClients clientList;
	Broadcaster messenger;
	CommunicationBuffer inbound;
	GameMap killingField;
	
	public GameCore(ActiveClients clientList, Broadcaster messenger, CommunicationBuffer inbound) {
		this.clientList = clientList;
		this.messenger = messenger;
		this.inbound = inbound;
		this.killingField = new GameMap();
		start();
	}

	public void run() {
		//siia tuleb mĆ¤nguloogika
		Message temp;
		while(true) {
			temp = getInput();
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
	
	private Message getInput() {
		return inbound.getMessage();
	}

	private void updateLocations(Message pointer) {
		pointer.message
	}
	
	private void checkCollisions() {
		
	}
	
	private void broadcastResult() {
		
	}
}
