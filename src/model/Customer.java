package model;

import java.util.UUID;

/**
 * @author AZJENKIN
 *
 */
public class Customer {

	/**
	 * 
	 */
	private UUID registration;

	/**
	 * 
	 */
	private Boolean hasPaid = false;
	/**
	 * 
	 */
	private Boolean willShop;
	/**
	 * 
	 */
	private double shopTime;
	/**
	 * 
	 */
	private double shopSpend;
	/**
	 * 
	 */
	private int payTicks;
	/**
	 * 
	 */
	private int fuelBought;
	
	/**
	 * @param registration
	 * @param shopTime
	 * @param shopSpend
	 * @param fuelBought
	 * @param willShop
	 * @param payTicks
	 */
	public Customer(UUID registration, double shopTime, double shopSpend, int fuelBought, Boolean willShop, int payTicks) {
		this.registration = registration;
		this.shopTime = shopTime;
		this.shopSpend = shopSpend;
		this.willShop = willShop;
		this.fuelBought = fuelBought;
		this.payTicks = payTicks;
	}
	
	/**
	 * @return
	 */
	public UUID getRegistration() {
		return registration;
	}
	
	/**
	 * @return
	 */
	public Boolean getHasPaid() {
		return hasPaid;
	}
	
	/**
	 * @return
	 */
	public Payment pay() {
		double shopMoney = shopSpend;
		int fuel = fuelBought;
		shopSpend = 0;
		fuelBought = 0;
		hasPaid = true;
		return new Payment(fuel, shopMoney);
	}
}
