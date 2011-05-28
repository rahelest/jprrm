package Tanks.shared;

import java.io.Serializable;

import Tanks.shared.mapElements.GameObject;

public class Message implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2075547926990832957L;

	private int counter;
	
	public int receiver;
	public int sender;
	public String extraString; //kui tüü kah
	public GameObject object;
	
	public Message () {
		counter++;
	}

	public Message(int clientID) {
		super();
		receiver = clientID;
		sender = 0;
		extraString = (Integer.toString(clientID));
		object = null;
	}
	
	
}
