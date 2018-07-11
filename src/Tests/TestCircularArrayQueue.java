

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
}
