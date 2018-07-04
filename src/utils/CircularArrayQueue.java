package utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

import exceptions.EmptyQueueException;

/**
 * A Circular Array Queue
 * @author AZJENKIN
 *
 * @param <T>
 */
public class CircularArrayQueue<T> implements Iterable<T> {

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
	private T[] queue;
	/**
	 * The maximum size of the queue
	 */
	private int size;

	/**
	 * Constructor for CircularArrayQueue
	 * @param size The maximum size of the queue
	 */
	public CircularArrayQueue(int size) {
		this.size = size;
		queue = (T[]) new Object[size];
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
		rear = (rear + 1) % size;
		return true;
	}

	/**
	 * Remove the element at the front of the queue
	 * @return
	 * @throws EmptyQueueException 
	 */
	public T remove() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		T e = queue[front];
		queue[front] = null;
		front = (front + 1) % size;
		return e;
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
	public T peek() {
		return queue[front];
	}

	/**
	 * Check if the queue contains a specified element
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

	/*
	 * Returns an iterator for the queue
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<T> iterator() {
		Iterator<T> iterator = new Iterator<T>() {
			private int next = front;

			@Override
			public boolean hasNext() {
				return !isEmpty() && queue[next] != null;
			}

			@Override
			public T next() {
				return queue[next];
			}
		};
		return iterator;
	}
	
	public int getSize() {
		return size;
	}
}
