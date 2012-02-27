package shared.messageTypes;

import shared.GameMap;


public class DecorObjectsMessage implements Message {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1832999617233281346L;
	/**
	 * The map field.
	 */
	public GameMap object;
	
	/**
	 * Creates a message with the map. 
	 * @param map The map.
	 */
	public DecorObjectsMessage(GameMap map) {
		object = map;
	}
}
