package Tanks.shared;

import java.io.Serializable;

import Tanks.shared.mapElements.GameObject;

public class Message implements Serializable {
	
	private int counter;
	
	public int receiver;
	public int sender;
	public String extraString;
	public GameObject object;

	public Message(int clientID) {
		receiver = clientID;
		sender = 0;
		extraString = ("ClientID =" + Integer.toString(clientID));
		object = null;
	}
	
	
}
