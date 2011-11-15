package Tanks.shared;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The serializable message class.
 * @author JPRRM
 *
 */
public class Message implements Serializable {
	
	/**
	 * An unique serial number.
	 */
	private static final long serialVersionUID = -2075547926990832957L;

	/**
	 * The string field.
	 */
	public String extraString;
	/**
	 * The map field.
	 */
	public GameMap object;
	
	public ArrayList<String> scores;
	
	public Message(ArrayList<String> scores) {
		extraString = "SC";
		this.scores = scores;
	}
	
	/**
	 * Creates a message with the map. 
	 * @param map The map.
	 */
	public Message(GameMap map) {
		object = map;
	}

	/**
	 * Generates a message with the ID.
	 * @param clientID The ID number.
	 */
	public Message(int clientID) {
		extraString = (Integer.toString(clientID));
		object = null;
	}
	
	/**
	 * Generates a message with a string.
	 * @param text The string.
	 */
	public Message(String text) {
		extraString = text;
	}
}
