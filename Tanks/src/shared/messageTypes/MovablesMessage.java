package shared.messageTypes;

import java.util.concurrent.ConcurrentHashMap;

import shared.mapElements.GameObject;

public class MovablesMessage extends MapMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4192072382422073843L;

	public MovablesMessage(ConcurrentHashMap<String, GameObject> nObjects) {
		super(nObjects);
	}

}
