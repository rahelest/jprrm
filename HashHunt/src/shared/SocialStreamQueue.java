package shared;

import java.util.LinkedList;

public class SocialStreamQueue<E> {
	
	private int LIMIT = 100;
	
	public LinkedList<E> queue = new LinkedList<>();
	
	public boolean addToQueue(E e) {
		if (queue.size() < LIMIT) {
			queue.add(e);
			LIMIT++;
			return true;
		} else {
			System.out.println("FULL");
			return false;
		}
	}
	
	public E getFromQueue() {
		LIMIT--;
		return queue.getFirst();
	}
	
	

}
