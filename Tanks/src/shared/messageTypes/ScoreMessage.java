package shared.messageTypes;

import java.util.ArrayList;

public class ScoreMessage implements Message {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4249710142491570146L;
	/**
	 * The list with the scores.
	 */
	public ArrayList<String> scores;
	
	public ScoreMessage(ArrayList<String> scores) {
		this.scores = scores;
	}

}
