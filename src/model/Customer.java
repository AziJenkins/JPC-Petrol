package model;

import java.util.UUID;

/**
 * @author AZJENKIN
 *
 */
public class Customer {

	public static final int MAXIMUM_PAY_TICKS = 0;
	public static final int MINIMUM_PAY_TICKS = 0;
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
	private double shopTicks;
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
	private int fuelSpend;
	
	/**
	 * Customer constructor
	 * @param registration The unique registration of this Customers Vehicle
	 * @param shopTicks The number of ticks the Customer will shop for
	 * @param shopSpend The amount of money the Customer will spend in the Shop
	 * @param fuelSpend The amount of fuel the Customer will purchase
	 * @param willShop A flag to show if the Customer is happy enough to visit the Shop
	 * @param payTicks The number of ticks this Customer will take to pay
	 */
	public Customer(UUID registration, double shopTicks, double shopSpend, int fuelSpend, Boolean willShop, int payTicks) {
		this.registration = registration;
		this.shopTicks = shopTicks;
		this.shopSpend = shopSpend;
		this.willShop = willShop;
		this.fuelSpend = fuelSpend;
		this.payTicks = payTicks;
	}
	
	/**
	 * Getter for registration
	 * @return
	 */
	public UUID getRegistration() {
		return registration;
	}
	
	/**
	 * Getter for hasPaid
	 * @return
	 */
	public Boolean getHasPaid() {
		return hasPaid;
	}
	
	public int reducePayTicks() {
		return payTicks--;
	}
	
	/**
	 * Return a Payment consisting of the amount the Customer has fueled their car 
	 * and the amount of money they spent in the shop
	 * @return
	 */
	public Payment pay() {
		double shopMoney = shopSpend;
		int fuel = fuelSpend;
		shopSpend = 0;
		fuelSpend = 0;
		hasPaid = true;
		return new Payment(fuel, shopMoney);
	}
	
	public int getPayTicks() {
		return payTicks;
	}
}
