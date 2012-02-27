package Tanks.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import Tanks.shared.Broadcaster;
import Tanks.shared.GameMap;

/**
 * 
 * @author JPRRM
 *
 */
public class ServerCommandListener extends Thread {
	
	/**
	 * Holds the list of active clients.
	 */
	private ActiveClients clientList;
	/**
	 * Pointer for the broadcaster.
	 */
	private Broadcaster messenger;
	
	/**
	 * The constructor.
	 * @param nClientList List with clients.
	 * @param nMessenger The broadcaster.
	 */
	public ServerCommandListener(ActiveClients nClientList, Broadcaster nMessenger) {
		setName("ServerCommandListener");
		setDaemon(true);
		this.clientList = nClientList;
		this.messenger = nMessenger;		
		start();
	}
	
	/**
	 * The run method.
	 */
	public final void run() {
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(isr);
        while (true) {
        	try {
				String tempString = input.readLine();
				Iterator<ClientSession> active = clientList.iterator();
				if (tempString.equalsIgnoreCase("RESET")) {
					System.out.println("Sending RESET command, stand by!");
					GameMap newMap = ObjectFactory.generateRandomMap(messenger);
					while (active.hasNext()) {
						ClientSession client = active.next();
						if (client.isAlive()) {
							client.reStart(newMap);
						}
					}
					
				} else if (tempString.equalsIgnoreCase("QUIT")) {
					System.out.println("Quitting server.");
				} else {
					System.out.println("Wrong command! reset and quit available.");
				}
			} catch (IOException e) {
				System.out.println("Error reading the input, please retry!");
			}
        }
	}
}
