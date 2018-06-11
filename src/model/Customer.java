package model;

import java.util.UUID;

public class Customer {

	private UUID registration;
	private Boolean hasPaid = false;
	private double shopTime;
	private double shopSpend;
	
	public Customer(UUID registration, double shopTime, double shopSpend) {
		this.registration = registration;
		this.shopTime = shopTime;
		this.shopSpend = shopSpend;
	}
}
