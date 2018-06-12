/**
 * 
 */
package model;

import java.util.Random;
import java.util.UUID;

/**
 * @author AZJENKIN
 *
 */
public abstract class Vehicle {
	private final double size;
	private final int fuelCapacity;
	private int currentFuel;
	private Boolean isFueled = false;
	private Boolean hasPaid = false;
	private UUID registration;
	private int ticksSinceArrival = 0;
	private Random rand = new Random();
	
	
	public Vehicle(double size, int minCapacity, int maxCapacity) {
		this.size = size;
		this.registration = UUID.randomUUID();
		this.currentFuel = 0;
		if (maxCapacity - minCapacity <= 0) {
			this.fuelCapacity = maxCapacity;
		} else {
		this.fuelCapacity = rand.nextInt(maxCapacity - minCapacity) + minCapacity;
		}
	}
	
	public Boolean fill(int fuelRate) {
	
	}
	
	public Customer leaveCar() {
		
	}
	
	public void reEnterCar(Customer c) {
		
	}
}
