package Tanks.server;

import Tanks.network.Broadcaster;

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
	}
}
