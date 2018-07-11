package model;

import exceptions.EmptyQueueException;
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
	
	public void enqueue(Customer c) {
		queue.add(c);
	}
	
	/**
	 * Collect a Payment from the Customer at the front of the queue
	 * @return
	 */
	public Payment collectPayment() {
		return queue.peek().pay();
	}
	
	/**
	 * Reduce the payment timer of the Customer at the front of the queue
	 */
	public void reduceTimer() {
		queue.peek().reducePayTicks();
	}
	
	public Customer dequeueWhenDone() throws EmptyQueueException {
		if (queue.peek().getPayTicks() < 1) {
			return queue.remove();
		}
		return null;
	}

	/**
	 * Getter for the queue of Customers
	 * @return
	 */
	public CircularArrayQueue<Customer> getQueue() {
		return this.queue;
	}
	
	public int getQueueSize() {
		return queue.getSize();
	}
}