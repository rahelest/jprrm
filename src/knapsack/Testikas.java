package knapsack;

import static org.junit.Assert.*;
import knapsack.baastyybid.Node;
import knapsack.baastyybid.NodePriorityQueue;

import org.junit.Test;

public class Testikas {

	@Test
	public void firstTest() {
		NodePriorityQueue PQ = new NodePriorityQueue();
		PQ.enqueue(null);
		Node temp1 = new Node();
		PQ.enqueue(temp1);
		Node temp2 = new Node(1,10,2);
		PQ.enqueue(temp2);
		Node temp3 = new Node(1,6,2);
		PQ.enqueue(temp3);
		assertEquals(PQ.dequeueNode(),temp3);
		assertEquals(PQ.dequeueNode(),temp2);
		assertEquals(PQ.dequeueNode(),temp1);
	}
}
