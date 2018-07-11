

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import exceptions.EmptyQueueException;
import utils.CircularArrayQueue;

/**
 * Tests for the {@link CircularArrayQueue} 
 * @author AZJENKIN
 *
 */
class TestCircularArrayQueue {
	
	
	/** Tests for the add and remove methods of the {@link CircularArrayQueue}
	 * @throws EmptyQueueException
	 */
	@Test
	void testAddRemove() throws EmptyQueueException {
		CircularArrayQueue<String> stringQueue = new CircularArrayQueue<>(3);
		assertTrue(stringQueue.add("First"));
		assertTrue(stringQueue.add("Second"));
		assertTrue(stringQueue.add("Third"));
		assertEquals("First", stringQueue.remove());
		assertEquals("Second", stringQueue.remove());
		assertEquals("Third", stringQueue.remove());
		assertTrue(stringQueue.add("First"));
		assertTrue(stringQueue.add("Second"));
		assertTrue(stringQueue.add("Third"));
		assertEquals("First", stringQueue.remove());
		assertEquals("Second", stringQueue.remove());
		assertEquals("Third", stringQueue.remove());
		assertTrue(stringQueue.add("First"));
		assertTrue(stringQueue.add("Second"));
		assertTrue(stringQueue.add("Third"));
		assertFalse(stringQueue.add("Not added"));
		assertEquals("First", stringQueue.remove());
		assertEquals("Second", stringQueue.remove());
		assertEquals("Third", stringQueue.remove());
		try {
			stringQueue.remove();
		} catch (EmptyQueueException e) {
			assertTrue(true);
		}

	}

	/**
	 * Tests for the iterator method of the {@link CircularArrayQueue}
	 */
	@Test
	void testIterable() {
		CircularArrayQueue<String> s = new CircularArrayQueue<>(3);
		s.add("1");
		Iterator<String> i = s.iterator();
		assertTrue(i.hasNext());
		assertEquals("1", i.next());
	}
	
	@Test
	public void testIsEmpty() throws EmptyQueueException {
		CircularArrayQueue<String> q = new CircularArrayQueue<>(3);
		assertTrue(q.isEmpty());
		q.add("First");
		assertFalse(q.isEmpty());
		q.remove();
		assertTrue(q.isEmpty());
		q.add("First");
		q.add("Second");
		assertFalse(q.isEmpty());
		q.add("Third");
		assertFalse(q.isEmpty());
		q.remove();
		assertFalse(q.isEmpty());
		q.remove();
		q.remove();
		assertTrue(q.isEmpty());
	}
	
	@Test
	public void testIsFull() throws EmptyQueueException {
		CircularArrayQueue<String> q = new CircularArrayQueue<>(3);
		assertFalse(q.isFull());
		q.add("First");
		assertFalse(q.isFull());
		q.add("Second");
		assertFalse(q.isFull());
		q.add("Third");
		assertTrue(q.isFull());
		q.remove();
		assertFalse(q.isFull());
		q.add("Fourth");
		assertTrue(q.isFull());
		q.remove();
		assertFalse(q.isFull());
		q.remove();
		assertFalse(q.isFull());
		q.remove();
		assertFalse(q.isFull());
		q.add("1");
		assertFalse(q.isFull());
		q.add("2");
		assertFalse(q.isFull());
		q.add("3");
		assertTrue(q.isFull());
	}
	
	@Test 
	public void testPeek() throws EmptyQueueException {
		CircularArrayQueue<String> q = new CircularArrayQueue<>(3);
		assertNull(q.peek());
		q.add("First");
		assertEquals("First", q.peek());
		assertEquals("First", q.peek());
		
		q.add("Second");
		assertEquals("First", q.peek());
		
		q.remove();
		assertEquals("Second", q.peek());
		
		q.add("Third");
		assertEquals("Second", q.peek());
		
		q.add("Fourth");
		assertEquals("Second", q.peek());
		
		q.remove();
		assertEquals("Third", q.peek());
		
		q.remove();
		assertEquals("Fourth", q.peek());
		
		q.remove();
		assertNull(q.peek());
	}
	
	@Test
	public void testContains() throws EmptyQueueException {
		CircularArrayQueue<String> q = new CircularArrayQueue<>(3);
		assertFalse(q.contains("First"));
		q.add("First");
		assertTrue(q.contains("First"));
		q.add("Second");
		q.add("Third");
		assertFalse(q.contains("Fourth"));
		assertTrue(q.contains("First"));
		assertTrue(q.contains("Second"));
		assertTrue(q.contains("Third"));
		q.remove();
		assertFalse(q.contains("First"));
		assertTrue(q.contains("Second"));
		assertTrue(q.contains("Third"));
		q.remove();
		assertFalse(q.contains("Second"));
		assertTrue(q.contains("Third"));
		q.remove();
		assertFalse(q.contains("Third"));
	}
	
	@Test
	public void testGetSize() {
		
	}
}
