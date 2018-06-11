package model;

public class SmallCar extends Vehicle {

	
	private static final double SMALL_CAR_SIZE = 1;
	private static final int SMALL_CAR_MINIMUM_FUEL_CAPACITY = 7;
	private static final int SMALL_CAR_MAXIMUM_FUEL_CAPACITY = 9;

	public SmallCar() {
		super(SMALL_CAR_SIZE, SMALL_CAR_MINIMUM_FUEL_CAPACITY, SMALL_CAR_MAXIMUM_FUEL_CAPACITY);
	}
}
