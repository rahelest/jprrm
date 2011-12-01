package seljakott;

import static org.junit.Assert.*;
import org.junit.Test;

public class Testikas {

//	@Test
//	public void firstTest() {
//		NodePriorityQueue PQ = new NodePriorityQueue();
////		PQ.enqueue(null);
//		Node temp1 = new Node();
//		PQ.enqueue(temp1);
//		Node temp2 = new Node(1,10,2);
//		PQ.enqueue(temp2);
//		Node temp3 = new Node(1,6,2);
//		PQ.enqueue(temp3);
////		System.out.println(PQ);
//		assertEquals(PQ.dequeueNode(),temp3);
//		assertEquals(PQ.dequeueNode(),temp2);
//		assertEquals(PQ.dequeueNode(),temp1);
//	}
	
	@Test
	public void dynamicArray() {
		DynamicArray array = new DynamicArray(1);
		array.add(1);
		array.add(2);
		array.add(3);
		array.add(4);
		array.add(5);
		array.add(6);
		System.out.println(array.get(5));
		System.out.println(array.lastElementsIndex);
	}
}
