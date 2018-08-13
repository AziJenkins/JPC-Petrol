package model;

import exceptions.CustomerAlreadyPaidException;
import exceptions.TillFullException;
import utils.ObservableListQueue;

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
	private ObservableListQueue<Customer> queue;

	/**
	 * Constructor for a Till
	 */
	public Till(int maxQueueSize) {
		queue = new ObservableListQueue<Customer>(maxQueueSize);
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
	
	/**
	 * If the queue is empty or firts customer hasn't paid 
	 * don't return anything, if those conditions are not met
	 * call remove() on the queue
	 * 
	 * @return Customer
	 */

	public Customer dequeueWhenDone() {
		if (queue.isEmpty() || !queue.peek().getHasPaid()) {
			return null;
		}
		return queue.remove();
	}

	/**
	 * Getter for the queue of Customers
	 * 
	 * @return
	 */
	public ObservableListQueue<Customer> getQueue() {
		return this.queue;
	}

	public double getQueueSize() {
		return queue.getSize();
	}

}