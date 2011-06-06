package Tanks.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class ServerCommandListener extends Thread {
	private ActiveClients clientList;
	
	public ServerCommandListener(ActiveClients clientList) {
		this.clientList = clientList;
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
				}
			} catch (IOException e) {
				System.out.println("Error reading the input, please retry!");
			}
        }
	}
}
