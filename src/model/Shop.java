package model;

import java.util.LinkedList;
import java.util.List;

import exceptions.CustomerAlreadyPresentException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

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
	private ObservableSet<Customer> holdingArea;

	/**
	 * Constructor for a Shop
	 */
	public Shop() {
		this.holdingArea = FXCollections.observableSet();
	}

	/**
	 * Add a Customer to the Shop
	 */
	public boolean add(Customer c) throws CustomerAlreadyPresentException {
		if (c.getWillShop()) {
			if (!holdingArea.add(c)) {
				throw new CustomerAlreadyPresentException();
			}
			return true;
		}
		return false;
	}
	
	

	/**
	 * Remove a Customer from the Shop
	 * 
	 * @return
	 */
	public List<Customer> remove(List<Customer> finishedShopping) {
		List<Customer> removed = new LinkedList<Customer>();
		for (Customer c : finishedShopping) {
			if(holdingArea.remove(c)) {
				removed.add(c);
			}
		}
		return removed;
	}

	/**
	 * Reduce the shopping timer by 1 for all Customers in the Shop Return list of
	 * customers who are done shopping
	 */
	public List<Customer> reduceAllTimers() {
		List<Customer> finishedShopping = new LinkedList<Customer>();
		for (Customer c : holdingArea) {
			if (c.reduceShopTicks() < 1) {
				finishedShopping.add(c);
				c.finishShopping();
			}
		}
		return finishedShopping;
	}

	/**
	 * 
	 * 
	 * @return List<Customer>
	 */
	public List<Customer> tick() {
		return remove(reduceAllTimers());
	}

	/**
	 * Getter for the set of Customers in the Shop
	 * 
	 * @return
	 */
	public ObservableSet<Customer> getContents() {
		return this.holdingArea;
	}
}