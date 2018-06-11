package model;

import java.util.UUID;

public class Customer {

	private UUID registration;
	private Boolean hasPaid = false;
	private double ShopTime;
	
	public Customer(UUID registration) {
		this.registration = registration;
	}
}
