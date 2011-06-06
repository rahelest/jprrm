package Tanks.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import Tanks.shared.Broadcaster;
import Tanks.shared.GameMap;

public class ServerCommandListener extends Thread {
	private ActiveClients clientList;
	private Broadcaster messenger;
	
	public ServerCommandListener(ActiveClients clientList, Broadcaster messenger) {
		setName("ServerCommandListener");
		this.clientList = clientList;
		this.messenger = messenger;		
		start();
	}
	
	public final void run() {
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(isr);
        while(true) {
        	try {
				String tempString = input.readLine();
				Iterator<ClientSession> active = clientList.iterator();
				if(tempString.equalsIgnoreCase("RESET")) {
					System.out.println("Sending RESET command, stand by!");
					while (active.hasNext()) {
						ClientSession client = active.next();
						if (client.isAlive()) {
							client.reStart();
						}
					}
					ObjectFactory.createMap(messenger, 2, 0, 0, 0);
				}
			} catch (IOException e) {
				System.out.println("Error reading the input, please retry!");
			}
        }
	}
}
