package model;

import java.util.HashSet;
import java.util.Set;

/**
 * A Shop
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
	 */
	public void add() {
		
	}
	
	/**
	 * Remove a Customer from the Shop
	 * @return
	 */
	public Customer remove() {
		return null;
	}
	
	/**
	 * Reduce the shopping timer by 1 for all
	 * Customers in the Shop
	 */
	public void reduceAllTimers() {
		
	}
	
	/**
	 * Getter for the set of Customers in the Shop
	 * @return
	 */
	public HashSet<Customer> getContents() {
		return this.holdingArea;
	}
}
