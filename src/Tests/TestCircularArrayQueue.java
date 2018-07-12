

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import exceptions.EmptyQueueException;
import interfaces.QueueItem;
import utils.CircularArrayQueue;

/**
 * Tests for the {@link CircularArrayQueue} 
 * @author AZJENKIN
 *
 */
class TestCircularArrayQueue {
	
	class StringItem implements QueueItem {
		public String s;
		public double size;
		
		public StringItem(String s) {
			this.s = s;
			size = 1;
		}
		public StringItem(String s, double size) {
			this.s = s;
			this.size = size;
		}
		public double getSize() {
			return size;
		}
	}
	/** Tests for the add and remove methods of the {@link CircularArrayQueue}
	 * @throws EmptyQueueException
	 */
	@Test
	void testAddRemove() throws EmptyQueueException {
		CircularArrayQueue<StringItem> stringQueue = new CircularArrayQueue<StringItem>(3, 1);
		assertTrue(stringQueue.add(new StringItem("First")));
		assertTrue(stringQueue.add(new StringItem("Second")));
		assertTrue(stringQueue.add(new StringItem("Third")));
		assertEquals("First", stringQueue.remove().s);
		assertEquals("Second", stringQueue.remove().s);
		assertEquals("Third", stringQueue.remove().s);
		assertTrue(stringQueue.add(new StringItem("First")));
		assertTrue(stringQueue.add(new StringItem("Second")));
		assertTrue(stringQueue.add(new StringItem("Third")));
		assertEquals("First", stringQueue.remove().s);
		assertEquals("Second", stringQueue.remove().s);
		assertEquals("Third", stringQueue.remove().s);
		assertTrue(stringQueue.add(new StringItem("First")));
		assertTrue(stringQueue.add(new StringItem("Second")));
		assertTrue(stringQueue.add(new StringItem("Third")));
		assertFalse(stringQueue.add(new StringItem("Not added")));
		assertEquals("First", stringQueue.remove().s);
		assertEquals("Second", stringQueue.remove().s);
		assertEquals("Third", stringQueue.remove().s);
		boolean flag = false;
		try {
			stringQueue.remove();
		} catch (EmptyQueueException e) {
			flag = true;
		}
		assertTrue(flag);

	}
	
	@Test
	void testAddRemoveWithSize() throws EmptyQueueException {
		CircularArrayQueue<StringItem> stringQueue = new CircularArrayQueue<StringItem>(3, 0.5);
		StringItem s1 = new StringItem("s1", 0.5);
		StringItem s2 = new StringItem("s2", 1);
		StringItem s3 = new StringItem("s3", 2);
		StringItem s4 = new StringItem("s4", 10);
		
		assertTrue(stringQueue.add(s1));
		assertFalse(stringQueue.add(s4));
		assertTrue(stringQueue.add(s3));
		assertFalse(stringQueue.add(s2));
		assertFalse(stringQueue.add(s3));
		assertTrue(stringQueue.add(s1));
		
		stringQueue.remove();
		assertTrue(stringQueue.add(s1));
		stringQueue.remove();
		assertTrue(stringQueue.add(s2));
		assertTrue(stringQueue.add(s1));
		assertTrue(stringQueue.add(s1));
		assertFalse(stringQueue.add(s1));
		stringQueue.remove();
		
	}

	/**
	 * Tests for the iterator method of the {@link CircularArrayQueue}
	 */
	@Test
	void testIterable() {
		CircularArrayQueue<StringItem> s = new CircularArrayQueue<StringItem>(3, 1);
		s.add(new StringItem("1"));
		Iterator<StringItem> i = s.iterator();
		assertTrue(i.hasNext());
		assertEquals("1", i.next().s);
	}
	
	@Test
	public void testIsEmpty() throws EmptyQueueException {
		CircularArrayQueue<StringItem> q = new CircularArrayQueue<StringItem>(3, 1);
		assertTrue(q.isEmpty());
		q.add(new StringItem("First"));
		assertFalse(q.isEmpty());
		q.remove();
		assertTrue(q.isEmpty());
		q.add(new StringItem("First"));
		q.add(new StringItem("Second"));
		assertFalse(q.isEmpty());
		q.add(new StringItem("Third"));
		assertFalse(q.isEmpty());
		q.remove();
		assertFalse(q.isEmpty());
		q.remove();
		q.remove();
		assertTrue(q.isEmpty());
	}
	
	@Test
	public void testIsFull() throws EmptyQueueException {
		CircularArrayQueue<StringItem> q = new CircularArrayQueue<StringItem>(3, 1);
		assertFalse(q.isFull());
		q.add(new StringItem("First"));
		assertFalse(q.isFull());
		q.add(new StringItem("Second"));
		assertFalse(q.isFull());
		q.add(new StringItem("Third"));
		assertTrue(q.isFull());
		q.remove();
		assertFalse(q.isFull());
		q.add(new StringItem("Fourth"));
		assertTrue(q.isFull());
		q.remove();
		assertFalse(q.isFull());
		q.remove();
		assertFalse(q.isFull());
		q.remove();
		assertFalse(q.isFull());
		q.add(new StringItem("1"));
		assertFalse(q.isFull());
		q.add(new StringItem("2"));
		assertFalse(q.isFull());
		q.add(new StringItem("3"));
		assertTrue(q.isFull());
	}
	
	@Test 
	public void testPeek() throws EmptyQueueException {
		CircularArrayQueue<StringItem> q = new CircularArrayQueue<StringItem>(3, 1);
		assertNull(q.peek());
		q.add(new StringItem("First"));
		assertEquals("First", q.peek().s);
		assertEquals("First", q.peek().s);
		
		q.add(new StringItem("Second"));
		assertEquals("First", q.peek().s);
		
		q.remove();
		assertEquals("Second", q.peek().s);
		
		q.add(new StringItem("Third"));
		assertEquals("Second", q.peek().s);
		
		q.add(new StringItem("Fourth"));
		assertEquals("Second", q.peek().s);
		
		q.remove();
		assertEquals("Third", q.peek().s);
		
		q.remove();
		assertEquals("Fourth", q.peek().s);
		
		q.remove();
		assertNull(q.peek());
	}
	
	@Test
	public void testContains() throws EmptyQueueException {
		CircularArrayQueue<StringItem> q = new CircularArrayQueue<StringItem>(3, 1);
		StringItem first = new StringItem("First");
		StringItem second = new StringItem("Second");
		StringItem third = new StringItem("Third");
		StringItem fourth = new StringItem("Fourth");
		assertFalse(q.contains(first));
		q.add(first);
		assertTrue(q.contains(first));
		q.add(second);
		q.add(third);
		assertFalse(q.contains(fourth));
		assertTrue(q.contains(first));
		assertTrue(q.contains(second));
		assertTrue(q.contains(third));
		q.remove();
		assertFalse(q.contains(first));
		assertTrue(q.contains(second));
		assertTrue(q.contains(third));
		q.remove();
		assertFalse(q.contains(second));
		assertTrue(q.contains(third));
		q.remove();
		assertFalse(q.contains(third));
	}
	
	@Test
	public void testGetSize() throws EmptyQueueException {
		CircularArrayQueue<StringItem> q = new CircularArrayQueue<StringItem>(3, 0.2);
		StringItem small = new StringItem("small", 0.2);
		StringItem medium = new StringItem("medium", 0.5);
		StringItem large = new StringItem("large", 2);
		
		assertEquals(0, q.getSize(), 0.01);
		q.add(small);
		assertEquals(0.2, q.getSize(), 0.01);
		q.remove();
		assertEquals(0, q.getSize(), 0.01);
		
		q.add(small);
		q.add(small);
		assertEquals(0.4, q.getSize(), 0.01);
		
		q.add(medium);
		assertEquals(0.9, q.getSize(), 0.01);
		
		q.remove();
		assertEquals(0.7, q.getSize(), 0.01);
		
		q.add(large);
		assertEquals(2.7, q.getSize(), 0.01);
		q.remove();
		assertEquals(2.5, q.getSize(), 0.01);
		q.remove();
		assertEquals(2, q.getSize(), 0.01);
		q.remove();
		assertEquals(0, q.getSize(), 0.01);
		
	}
}
