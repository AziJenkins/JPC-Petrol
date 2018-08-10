package model;

import java.util.UUID;

import exceptions.CustomerAlreadyPaidException;
import interfaces.QueueItem;

/**
 * @author AZJENKIN
 *
 */
public class Customer implements QueueItem {

	public static final int MAXIMUM_PAY_TICKS = 18;
	public static final int MINIMUM_PAY_TICKS = 12;
	/**
	 * The unique registration of this Customers Vehicle
	 */
	private UUID registration;

	/**
	 * A flag to show if the Customer has paid for their fuel
	 */
	private Boolean hasPaid = false;
	/**
	 * A flag to show if the Customer is happy enough to visit the Shop
	 */
	private Boolean willShop;
	/**
	 * The number of ticks the Customer will shop for
	 */
	private int shopTicks;
	/**
	 * The amount of money the Customer will spend in the Shop
	 */
	private double shopSpend;
	/**
	 * The number of ticks this Customer will take to pay
	 */
	private int payTicks;
	/**
	 * The amount of fuel the Customer will purchase
	 */
	private double fuelGallons;

	/**
	 * Customer constructor
	 * 
	 * @param registration The unique registration of this Customers Vehicle
	 * @param shopTicks    The number of ticks the Customer will shop for
	 * @param shopSpend    The amount of money the Customer will spend in the Shop
	 * @param fuelGallons  The amount of fuel the Customer will purchase
	 * @param willShop     A flag to show if the Customer is happy enough to visit
	 *                     the Shop
	 * @param payTicks     The number of ticks this Customer will take to pay
	 */
	public Customer(UUID registration, int shopTicks, double shopSpend, double fuelGallons, Boolean willShop,
			int payTicks) {
		this.registration = registration;
		this.shopTicks = shopTicks;
		this.shopSpend = shopSpend;
		this.willShop = willShop;
		this.fuelGallons = fuelGallons;
		this.payTicks = payTicks;
	}

	/**
	 * Getter for registration
	 * 
	 * @return
	 */
	public UUID getRegistration() {
		return registration;
	}

	/**
	 * Getter for hasPaid
	 * 
	 * @return
	 */
	public Boolean getHasPaid() {
		return hasPaid;
	}

	/**
	 * Return a Payment consisting of the amount the Customer has fueled their car
	 * and the amount of money they spent in the shop
	 * 
	 * @return
	 */
	public Payment pay() throws CustomerAlreadyPaidException {
		if (hasPaid) {
			throw new CustomerAlreadyPaidException();
		} else if (payTicks != 0) {
			reducePayTicks();
			return null;
		} else {
			double shopMoney = shopSpend;
			double fuel = fuelGallons;
			shopSpend = 0;
			fuelGallons = 0;
			hasPaid = true;
			return new Payment(fuel, shopMoney);
		}
	}

	/**
	 * Getter for willShop
	 * 
	 * @return
	 */
	public Boolean getWillShop() {
		return willShop;
	}

	/**
	 * Getter for finishShopping
	 * 
	 * @return
	 */
	public void finishShopping() {
		willShop = false;
	}

	/**
	 * Getter for shopTicks
	 * 
	 * @return
	 */
	public int getShopTicks() {
		return shopTicks;
	}

	/**
	 * Getter for payTicks
	 * 
	 * @return
	 */
	public int getPayTicks() {
		return payTicks;
	}
	
	/**
	 * update payTicks
	 * 
	 * @return
	 */

	public int reducePayTicks() {
		return payTicks--;
	}

	/**
	 * update shopTicks
	 * 
	 * @return
	 */
	public int reduceShopTicks() {
		return shopTicks--;
	}
	
	/*
	 * Test method. Needs to be removed
	 */
	public void setHasPaid(boolean b) {
		hasPaid = b;
		
	}

	/**
	 * Getter for shopSpend
	 * 
	 * @return
	 */
	public double getShopSpend() {
		return shopSpend;
	}
	
	/**
	 * Getter for registration
	 * 
	 * @return
	 */
	public String toString() {
		return registration.toString();
	}
}