package model;

import exceptions.CustomerAlreadyPaidException;
import exceptions.EmptyQueueException;
import exceptions.TillFullException;
import utils.CircularArrayQueue;

/**
 * A Till
 * 
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
		queue = new CircularArrayQueue<Customer>(maxQueueSize, 1);
	}

	public void enqueue(Customer c) throws TillFullException {
		if (!queue.add(c)) {
			throw new TillFullException();
		}
	}

	/**
	 * Collect a Payment from the Customer at the front of the queue
	 * 
	 * @return
	 * @throws CustomerAlreadyPaidException
	 */
	public Payment collectPayment() throws CustomerAlreadyPaidException {
		if (queue.peek() != null) {
			return queue.peek().pay();
		}
		return null;
	}

	public Customer dequeueWhenDone() {
		if (queue.isEmpty() || !queue.peek().getHasPaid()) {
			return null;
		}
		try {
			return queue.remove();
		} catch (EmptyQueueException e) {
			return null;
		}
	}

	/**
	 * Getter for the queue of Customers
	 * 
	 * @return
	 */
	public CircularArrayQueue<Customer> getQueue() {
		return this.queue;
	}

	public double getQueueSize() {
		return queue.getSize();
	}

}