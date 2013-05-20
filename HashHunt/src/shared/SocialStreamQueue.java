package shared;

import java.util.LinkedList;

public class SocialStreamQueue<E> {
	
	private int LIMIT = 100;
	
	public LinkedList<E> queue = new LinkedList<>();
	
	public void addToQueue(E e) {
		if (queue.size() < LIMIT) {
			queue.add(e);
			LIMIT++;
		} else System.out.println("FULL");
	}
	
	public E getFromQueue() {
		LIMIT--;
		return queue.getFirst();
	}
	
	

}
