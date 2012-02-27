package shared.messageTypes;

import java.util.concurrent.ConcurrentHashMap;

import shared.mapElements.GameObject;


public abstract class MapMessage implements Message {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 761489636725606488L;
	/**
	 * The map field.
	 */
	public ConcurrentHashMap<String, GameObject> objects;
	
	/**
	 * Creates a message with the map. 
	 * @param map The map.
	 */
	public MapMessage(ConcurrentHashMap<String, GameObject> nObjects) {
		objects = nObjects;
	}

}
