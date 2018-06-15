package model;

import java.util.UUID;

public class Customer {

	private UUID registration;

	private Boolean hasPaid = false;
	private Boolean willShop;
	private double shopTime;
	private double shopSpend;
	private int payTicks;
	private int fuelBought;
	
	public Customer(UUID registration, double shopTime, double shopSpend, int fuelBought, Boolean willShop, int payTicks) {
		this.registration = registration;
		this.shopTime = shopTime;
		this.shopSpend = shopSpend;
		this.willShop = willShop;
		this.fuelBought = fuelBought;
		this.payTicks = payTicks;
	}
	
	public UUID getRegistration() {
		return registration;
	}
	
	public Boolean getHasPaid() {
		return hasPaid;
	}
	
	public Payment pay() {
		double shopMoney = shopSpend;
		int fuel = fuelBought;
		shopSpend = 0;
		fuelBought = 0;
		hasPaid = true;
		return new Payment(fuel, shopMoney);
	}
}
