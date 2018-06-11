package model;

public class Motorbike extends Vehicle {

	private static final double MOTORBIKE_SIZE = 0.75;
	private static final int MOTORBIKE_MINIMUM_FUEL_CAPACITY = 5;
	private static final int MOTORBIKE_MAXIMUM_FUEL_CAPACITY = 5;
	
	public Motorbike() {
		super(MOTORBIKE_SIZE, MOTORBIKE_MINIMUM_FUEL_CAPACITY, MOTORBIKE_MAXIMUM_FUEL_CAPACITY);
	}
}
