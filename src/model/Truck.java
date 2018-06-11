package model;

public class Truck extends Vehicle {

	private static final double TRUCK_SIZE = 2;
	private static final int TRUCK_MINIMUM_FUEL_CAPACITY = 30;
	private static final int TRUCK_MAXIMUM_FUEL_CAPACITY = 40;
	
	public Truck() {
		super(TRUCK_SIZE, TRUCK_MINIMUM_FUEL_CAPACITY, TRUCK_MAXIMUM_FUEL_CAPACITY);
	}
}
