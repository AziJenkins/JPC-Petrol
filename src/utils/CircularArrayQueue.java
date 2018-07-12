package utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

import exceptions.EmptyQueueException;
import interfaces.QueueItem;

/**
 * A Circular Array Queue
 * @author AZJENKIN
 *
 * @param <T>
 */
public class CircularArrayQueue<T extends QueueItem> implements Iterable<T>{

	/**
	 * The index of the element at the front of the queue
	 */
	private int front;
	/**
	 * The index of the element at the rear of the queue
	 */
	private int rear;
	/**
	 * The array that holds queue items
	 */
	private QueueItem[] queue;
	/**
	 * The maximum size of the queue
	 */
	private int capacity;
	

	/**
	 * Constructor for CircularArrayQueue
	 * @param capacity The maximum size of the queue
	 */
	public CircularArrayQueue(int capacity) {
		this.capacity = capacity;
		queue = new QueueItem[capacity];
		front = -1;
		rear = 0;
	}

	/**
	 * Add an element to the rear of the queue
	 * @param t
	 * @return
	 * 
	 */
	public boolean add(T t) {
		if (isFull()) {
			return false;
		}
		if(front == -1) {
			front++;
		}
		queue[rear] = t;
		rear = (rear + 1) % capacity;
		return true;
	}

	/**
	 * Remove the element at the front of the queue
	 * @return
	 * @throws EmptyQueueException 
	 */
	@SuppressWarnings("unchecked")
	public T remove() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		QueueItem e = queue[front];
		queue[front] = null;
		front = (front + 1) % capacity;
		return (T) e;
	}

	/**
	 * Returns true if there is nothing in the queue
	 * @return
	 */
	public boolean isEmpty() {
		return front == -1 || queue[front] == null;
	}

	/**
	 * Returns true if the queue is full
	 * @return
	 */
	public boolean isFull() {
		return rear == front && queue[front] != null;
	}

	/**
	 * Return the first element in the queue without removing it
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T peek() {
		if (front < 0) {
			return null;
		}
		return (T) queue[front];
	}

	/**
	 * Check if the queue contains a specified element
	 * @param t
	 * @return
	 */
	public boolean contains(T t) {
		for (QueueItem thing : queue) {
			if (thing == t) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Returns an iterator for the queue
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<T> iterator() {
		Iterator<T> iterator = new Iterator<T>() {
			private int next = front;
			private int iFront = front;
			private boolean hasStarted = false;

			@Override
			public boolean hasNext() {
				return !isEmpty() && queue[next] != null && !(hasStarted && next == iFront);
			}

			@SuppressWarnings("unchecked")
			@Override
			public T next() {
				if (!hasStarted) {
					hasStarted = true;
				}
				T ret = (T) queue[next];
				next = (next + 1 ) % queue.length;
				return ret;
			}
		};
		return iterator;
	}
	
	public double getSize() {
		double size = 0;
		Iterator<T> i = iterator();
		while (i.hasNext()) {
			size += i.next().getSize();
		}
		return size;
	}
}
