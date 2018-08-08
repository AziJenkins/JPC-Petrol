package utils;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.sun.javafx.property.adapter.PropertyDescriptor.Listener;

import exceptions.EmptyQueueException;
import interfaces.QueueItem;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.scene.shape.ObservableFaceArray;
import jdk.jfr.events.FileReadEvent;

/**
 * A Circular Array Queue
 * @author AZJENKIN
 *
 * @param <T>
 */
public class ObservableCircularArrayQueue<T extends QueueItem> implements Iterable<T>, ObservableValue<T[]> {

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
	private double capacity;
	
	private double spaceUnused;
	
	private ChangeListener<? super T[]> changeListener;
	

	/**
	 * Constructor for CircularArrayQueue
	 * @param capacity The maximum size of the queue
	 */
	@SuppressWarnings("unchecked")
	public ObservableCircularArrayQueue(double capacity, double smallestItem) {
		this.capacity = capacity;
		this.spaceUnused = capacity;
		queue = (T[]) new QueueItem[(int)Math.ceil(capacity / smallestItem)];
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
		if (isFull() || t.getSize() > spaceUnused) {
			return false;
		}
		if(front == -1) {
			front++;
		}
		T[] oldValue = getValue();
		queue[rear] = t;
		rear = (rear + 1) % queue.length;
		spaceUnused -= t.getSize();
		T[] newValue = getValue(); //TODO do this gud
		//changeListener.changed(this, oldValue, newValue);
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
		front = (front + 1) % queue.length;
		spaceUnused += e.getSize();
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
		if (front < 0) {
			return null;
		}
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
			private int iFront = front;
			private boolean hasStarted = false;

			@Override
			public boolean hasNext() {
				return !isEmpty() && queue[next] != null && !(hasStarted && next == iFront);
			}

			@Override
			public T next() {
				if (!hasStarted) {
					hasStarted = true;
				}
				T ret = queue[next];
				next = (next + 1 ) % queue.length;
				return ret;
			}
		};
		return iterator;
	}
	
	public double getSize() {
		return capacity - spaceUnused;
	}

	@Override
	public void addListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T[] getValue() {
		@SuppressWarnings("unchecked")
		T[] value = (T[]) new QueueItem[queue.length];
		for(int i = 0; i < queue.length; i++) {
			value[i] = queue[i+front%queue.length];
		}
		return value;
	}

	@Override
	public void addListener(ChangeListener<? super T[]> arg0) {
		changeListener = arg0;
	}

	@Override
	public void removeListener(ChangeListener<? super T[]> arg0) {
		// TODO Auto-generated method stub
		
	}

}