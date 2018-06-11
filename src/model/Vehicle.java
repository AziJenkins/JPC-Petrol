/**
 * 
 */
package model;

import java.util.UUID;

/**
 * @author AZJENKIN
 *
 */
public abstract class Vehicle {
	private final double size;
	private final double fuelCapacity;
	private Boolean isFueled = false;
	private Boolean hasPaid = false;
	private UUID registration;
	
	
	public Vehicle(double size, double fuelCapacity) {
		this.size = size;
		this.fuelCapacity = fuelCapacity;
		this.registration = UUID.randomUUID();
	}
}
