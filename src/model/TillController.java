package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import exceptions.CustomerAlreadyPaidException;
import exceptions.EmptyQueueException;
import exceptions.TillFullException;

/**
 * A controller for any number of Tills
 * 
 * @author AZJENKIN
 *
 */
public class TillController {

	/**
	 * An array containing all the Tills belonging to the controller
	 */
	private Till[] tills;

	/**
	 * Constructor for a Till Controller
	 * 
	 * @param numTills
	 */
	public TillController(int numTills, int maxQueueSize) {
		tills = new Till[numTills];
		for (int i = 0; i < numTills; i++) {
			tills[i] = new Till(maxQueueSize);
		}
	}

	/**
	 * Give a Customer to the Till with the shortest queue
	 * 
	 * @throws TillFullException
	 */
	public void enqueue(Customer c) throws TillFullException {
		int shortestQueueIndex = 0;
		for (int i = 1; i < tills.length; i++) {
			if (tills[i].getQueueSize() < tills[shortestQueueIndex].getQueueSize()) {
				shortestQueueIndex = i;
			}
		}
		tills[shortestQueueIndex].enqueue(c);
	}

	/**
	 * Remove the Customer at the front of each Tills queue if they have already
	 * paid
	 */
	public List<Customer> dequeueFullyPaid() {
		LinkedList<Customer> completedCustomers = new LinkedList<Customer>();
		for (Till t : tills) {
			Customer c = t.dequeueWhenDone();
			if (c != null) {
				completedCustomers.add(c);
			}
		}
		return completedCustomers;
	}

	/**
	 * Collect payments from the Customers at the front of each Tills queue if they
	 * are ready to pay
	 * 
	 * @return
	 * @throws CustomerAlreadyPaidException
	 * @throws NoCustomerPresentException
	 */
	public List<Payment> collectPayments() throws CustomerAlreadyPaidException {
		LinkedList<Payment> payments = new LinkedList<Payment>();
		for (Till t : tills) {
			Payment p = t.collectPayment();
			if (p != null) {
				payments.add(p);
			}
		}
		return payments;
	}

	/**
	 * Getter for the array of Tills
	 * 
	 * @return
	 */
	public Till[] getTills() {
		return this.tills;
	}
	
	public void tick() {
		//call tick on each till
	}
}