package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

import utils.CircularArrayQueue;

/**
 * A Till
 * @author AZJENKIN
 *
 */
public class Till {
	
	/**
	 * A queue of Customers for the Till
	 */
	private CircularArrayQueue<Customer> queue;
	
	/**
	 * Constructor for a Till
	 */
	public Till(int maxQueueSize) {
		queue = new CircularArrayQueue<Customer>(maxQueueSize);
	}
	
	/**
	 * Collect a Payment from the Customer at the front of the queue
	 * @return
	 */
	public Payment collectPayment() {
		return null;
	}
	
	/**
	 * Reduce the payment timer of the Customer at the front of the queue
	 */
	public void reduceTimer() {
		
	}

	/**
	 * Getter for the queue of Customers
	 * @return
	 */
	public CircularArrayQueue<Customer> getQueue() {
		return this.queue;
	}
}
