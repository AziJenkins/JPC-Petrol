package model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author AZJENKIN
 *
 */
public class Shop {

	/**
	 * 
	 */
	private HashSet<Customer> holdingArea;
	
	/**
	 * 
	 */
	public Shop() {
		this.holdingArea = new HashSet<Customer>();
	}
	
	/**
	 * 
	 */
	public void add() {
		
	}
	
	/**
	 * @return
	 */
	public Customer remove() {
		return null;
	}
	
	/**
	 * 
	 */
	public void reduceAllTimers() {
		
	}
	
	/**
	 * @return
	 */
	public HashSet<Customer> getContents() {
		return this.holdingArea;
	}
}
