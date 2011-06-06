package Tanks.shared;

import java.io.Serializable;

import Tanks.shared.mapElements.GameObject;

public class Message implements Serializable {
	
	/**
	 * An unique serial number.
	 */
	private static final long serialVersionUID = -2075547926990832957L;

//	private int counter;
	
	public int receiver = -1;
	public int sender = 0;
	public String extraString; //kui tüü kah
	public GameMap object;
	
	public Message (GameMap map) {
//		counter++;
		object = map;
	}

	public Message(int clientID) {
//		super();
		receiver = clientID;
		sender = 0;
		extraString = (Integer.toString(clientID));
		object = null;
	}
	
	public Message(String text) {
		extraString = text;
		receiver = 0;
	}
	
	
	
}
