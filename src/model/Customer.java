package model;

import java.util.UUID;

public class Customer {

	private UUID registration;

	private Boolean hasPaid = false;
	private Boolean willShop;
	private double shopTime;
	private double shopSpend;
	
	public Customer(UUID registration, double shopTime, double shopSpend, Boolean willShop) {
		this.registration = registration;
		this.shopTime = shopTime;
		this.shopSpend = shopSpend;
		this.willShop = willShop;
	}
	
	public UUID getRegistration() {
		return registration;
	}
	
	public Boolean getHasPaid() {
		return hasPaid;
	}
}
