package shared.messageTypes;

import java.util.concurrent.ConcurrentHashMap;

import shared.mapElements.GameObject;

public class MissilesMessage extends MapMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2072426741705133992L;

	public MissilesMessage(ConcurrentHashMap<String, GameObject> nObjects) {
		super(nObjects);
	}

}
