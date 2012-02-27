package shared.messageTypes;

public class CommandMessage implements Message {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2598498644394831771L;
	/**
	 * The string field.
	 */
	public String extraString;
	
	/**
	 * Generates a message with the ID.
	 * @param clientID The ID number.
	 */
	public CommandMessage(int clientID) {
		extraString = (Integer.toString(clientID));
	}
	
	/**
	 * Generates a message with a string.
	 * @param text The string.
	 */
	public CommandMessage(String text) {
		extraString = text;
	}

}
