package Tanks.server;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import Tanks.shared.Broadcaster;
import Tanks.shared.GameMap;

/**
 * The server's core class.
 * @author JPRRM
 *
 */
public final class ServerCore {
	
	/**
	 * The hiding constructor.
	 */
	private ServerCore() { }
      
	/**
	 * The main method.
	 * @param args Nuffin
	 */
	@SuppressWarnings("unused")
    public static void main(String[] args) {
	    int port = 8888;
	    int clientID = 1;
	    
	    try {
//     		ObjectFactory factory = new ObjectFactory();
	        ServerSocket serv = new ServerSocket(port);
	        ActiveClients clientList = new ActiveClients();
	        Broadcaster messenger = new Broadcaster(clientList);
//	        GameMap killingField =  ObjectFactory.loadFromFile(2, messenger);
	        GameMap killingField = ObjectFactory.createMap(messenger, 2, 1, 2, 2);
	        MissileMover missileMover = new MissileMover(messenger, killingField);
//	        ObjectFactory.saveToFile(killingField);
//	        System.out.println("Kaart loodud");
	        ServerCommandListener serverInput = new ServerCommandListener(clientList, messenger);
	        while (true) {
                Socket clientSock = serv.accept();
                try {
                    /*if (clientList.exists(clientSock.getInetAddress())) {
                        System.out.println("clientsessioni notify algus");
                        ClientSession exSessPointer = clientList.getExisting(clientSock.getInetAddress());
                        exSessPointer.updateOnReconnect(clientSock);
                        synchronized (exSessPointer) {
                                System.out.println("luku algus");
                                exSessPointer.notify();                                                 
                                System.out.println(exSessPointer);
                        }
                        System.out.println("clientsessioni notify lopp");
                    } else {*/
                            clientList.addClient(new ClientSession(clientSock,
                            		killingField, clientID));
                            System.out.println("Klient ühines edukalt, ID = " + clientID);
                            clientID++;     
//                    }
                                        
                } catch (IOException e) {
                        clientSock.close();
                }       
	        }
	    } catch (BindException e) {
        	System.out.println("Only one server can be running at any time!");
        	System.exit(0);
        } catch (IOException e) {
            System.out.println("IO viga: " + e.getMessage());
            e.printStackTrace();
	    }
    }
}
