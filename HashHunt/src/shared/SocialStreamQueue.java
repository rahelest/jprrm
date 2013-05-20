package shared;

import java.util.LinkedList;

public class SocialStreamQueue {
	
	private int LIMIT = 100;
	
	public LinkedList<String> queue = new LinkedList<>();
	
	public boolean addToQueue(String s) {
		if (queue.size() < LIMIT) {
			queue.add(s);
			LIMIT++;
			return true;
		} else {
			System.out.println("FULL");
			return false;
		}
	}
	
	public String getFromQueue() {
		LIMIT--;
		return queue.getFirst();
	}
	
	

}
