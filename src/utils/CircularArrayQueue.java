package utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 * @author AZJENKIN
 *
 * @param <T>
 */
public class CircularArrayQueue<T> implements Iterable<T> {

	/**
	 * 
	 */
	private int front;
	/**
	 * 
	 */
	private int rear;
	/**
	 * 
	 */
	private T[] queue;
	/**
	 * 
	 */
	private int size;

	/**
	 * @param size
	 */
	public CircularArrayQueue(int size) {
		this.size = size;
		queue = (T[]) new Object[size];
		front = -1;
		rear = -1;
	}

	/**
	 * @param t
	 * @return
	 */
	public boolean add(T t) {
		if (!isFull()) {
			queue[rear] = t;
			rear = (rear + 1) % size;
			return true;
		}
		return false;
	}

	/**
	 * @return
	 */
	public T remove() {
		if (!isEmpty()) {
			front = (front + 1) % size;
			T removed = queue[front];
			queue[front] = null;
			return removed;
		}
		return null;
	}

	/**
	 * @return
	 */
	public boolean isEmpty() {
		if (queue[front + 1] == null) {
			return true;
		}
		return false;
	}

	/**
	 * @return
	 */
	public boolean isFull() {
		if (queue[front].equals(queue[rear]) && front == rear) {
			return true;
		}
		return false;
	}

	/**
	 * @return
	 */
	public T peek() {
		return queue[front + 1];
	}

	/**
	 * @param t
	 * @return
	 */
	public boolean contains(T t) {
		for (T thing : queue) {
			if (thing == t) {
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<T> iterator() {
		Iterator<T> iterator = new Iterator<T>() {
			private int current = front;

			@Override
			public boolean hasNext() {
				return current < size && queue[current + 1] != null;
			}

			@Override
			public T next() {
				return queue[current];
			}
		};
		return iterator;
	}
}
