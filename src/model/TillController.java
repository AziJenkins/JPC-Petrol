package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import exceptions.EmptyQueueException;

/**
 * A controller for any number of Tills
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
	 */
	public void enqueue() {
		int shortestQueueIndex = 0;
		for (int i = 1; i < tills.length; i++) {
			if (tills[i].getQueueSize() < tills[shortestQueueIndex].getQueueSize()) {
				shortestQueueIndex = i;
			}
		}
	}
	
	/**
	 * Remove the Customer at the front of each Tills queue
	 * if they have already paid
	 */
	public List<Customer> dequeueFullyPaid() throws EmptyQueueException {
		LinkedList<Customer> completedCustomers = new LinkedList<Customer>();
		for (Till t : tills) {
			completedCustomers.add(t.dequeueWhenDone());
		}
		return completedCustomers;
	}
	
	/**
	 * Collect payments from the Customers at the front of
	 * each Tills queue if they are ready to pay
	 * @return
	 */
	public List<Payment> collectPayments() {
		LinkedList<Payment> payments = new LinkedList<Payment>();
		for (Till t : tills) {
			payments.add(t.collectPayment());
		}
		return payments;
	}
	
	/**
	 * Getter for the array of Tills 
	 * @return
	 */
	public Till[] getTills() {
		return this.tills;
	}
}