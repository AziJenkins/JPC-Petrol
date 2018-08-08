package utils;


import java.util.ArrayList;
import java.util.Iterator;

import interfaces.QueueItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A Circular Array Queue
 * @author AZJENKIN
 *
 * @param <T>
 */
public class ObservableListQueue<T extends QueueItem> implements Iterable<T> {

	/**
	 * The ObservableList that holds queue items
	 */
	private ObservableList<T> queue;
	/**
	 * The maximum size of the queue
	 */
	private double capacity;
	/**
	 * The unused space in the queue
	 */
	private double spaceUnused;
	

	/**
	 * Constructor for CircularArrayQueue
	 * @param capacity The maximum size of the queue
	 */
	public ObservableListQueue(double capacity) {
		this.capacity = capacity;
		this.spaceUnused = capacity;
		queue = FXCollections.observableList(new ArrayList<T>());
	}

	/**
	 * Add an element to the rear of the queue
	 * @param t
	 * @return
	 * 
	 */
	public boolean add(T t) {
		if (isFull() || t.getSize() > spaceUnused) {
			return false;
		}
		queue.add(t);
		spaceUnused -= t.getSize();
		return true;
	}

	/**
	 * Remove the element at the front of the queue
	 * @return
	 */
	public T remove() {
		T t = queue.remove(0);
		spaceUnused += t.getSize();
		return t;
	}

	/**
	 * Returns true if there is nothing in the queue
	 * @return
	 */
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	/**
	 * Returns true if the queue is full
	 * @return
	 */
	public boolean isFull() {
		return spaceUnused == 0;
	}

	/**
	 * Return the first element in the queue without removing it
	 * @return
	 */
	public T peek() {
		try {
			return queue.get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * Check if the queue contains a specified element
	 * @param t
	 * @return
	 */
	public boolean contains(T t) {
		return queue.contains(t);
	}

	/*
	 * Returns an iterator for the queue
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<T> iterator() {
		return queue.iterator();
	}
	
	public double getSize() {
		return capacity - spaceUnused;
	}
	
	public ObservableList<T> getObservable() {
		return queue;
	}

}