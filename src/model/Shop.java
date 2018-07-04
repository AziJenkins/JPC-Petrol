package model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import exceptions.CustomerAlreadyPresentException;

/**
 * A Shop
 * 
 * @author AZJENKIN
 *
 */
public class Shop {

	/**
	 * A set of all Customers in the Shop
	 */
	private HashSet<Customer> holdingArea;

	/**
	 * Constructor for a Shop
	 */
	public Shop() {
		this.holdingArea = new HashSet<Customer>();
	}

	/**
	 * Add a Customer to the Shop
	 * @throws CustomerAlreadyPresentException 
	 */
	public void add(Customer c) throws CustomerAlreadyPresentException {
		if (!holdingArea.add(c)) {
			throw new CustomerAlreadyPresentException();
		}
	}

	/**
	 * Remove a Customer from the Shop
	 * @return
	 */
	public List<Customer> remove(List<Customer> finishedShopping) {
		for (Customer c : finishedShopping) {
			holdingArea.remove(c);
		}
		return finishedShopping;
	}

	/**
	 * Reduce the shopping timer by 1 for all Customers in the Shop
	 */
	public List<Customer> reduceAllTimers() {
		List<Customer> finishedShopping = new LinkedList<Customer>();
		for (Customer c : holdingArea) {
			if (c.reducePayTicks() < 1) {
				finishedShopping.add(c);
			}
		}
		return finishedShopping;
	}
	
	public List<Customer> tick() {
		return remove(reduceAllTimers());
	}

	/**
	 * Getter for the set of Customers in the Shop
	 * 
	 * @return
	 */
	public HashSet<Customer> getContents() {
		return this.holdingArea;
	}
}
