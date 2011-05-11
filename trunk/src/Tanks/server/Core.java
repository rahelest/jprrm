package Tanks.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Core {
	
	public static void main(String[] args) {
		
		int port = 8888;
	
		try {
			ServerSocket serv = new ServerSocket(port);
			
			// Serveri igavene töötsükkel
			while (true) {
				System.out.println("Server: kuulan pordil: " + port);
				Socket clisock = serv.accept();			// accept() jääb ootama, kuniks luuakse ühendus
				
				try {
					new ClientSession(clisock);			// loome kliendiseansi lõime ning uuesti tagasi porti kuulama
				} catch (IOException e) {
					clisock.close();					// Kui ühendust ei loodud, sulgeme sokli
				}
					
				System.out.println("Server: alati valmis uusi kliente teenindama!");
				
			} // töötsükli lõpp
	
		} catch (IOException e) {
			System.out.println("IO viga :" + e.getMessage());
			e.printStackTrace();
		}
	}
}
