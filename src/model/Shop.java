package model;

import java.util.HashSet;
import java.util.Set;

public class Shop {

	private HashSet<Customer> holdingArea;
	
	public Shop() {
		this.holdingArea = new HashSet<Customer>();
	}
	
	public void add() {
		
	}
	
	public Customer remove() {
		return null;
	}
	
	public void reduceAllTimers() {
		
	}
	
	public HashSet<Customer> getContents() {
		return this.holdingArea;
	}
}
