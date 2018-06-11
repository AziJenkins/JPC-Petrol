package model;

public class Truck extends Vehicle {

	private static final double TRUCK_SIZE = 2;
	private static final int TRUCK_MINIMUM_FUEL_CAPACITY = 30;
	private static final int TRUCK_MAXIMUM_FUEL_CAPACITY = 40;
	private static final int TRUCK_PROBABILITY_TO_SHOP = 1;
	private static final int TRUCK_TICKS_BEFORE_NO_SHOPPING = 48;
	private static final int TRUCK_MINIMUM_SHOPPING_TICKS = 24;
	private static final int TRUCK_MAXIMUM_SHOPPING_TICKS = 36;
	private static final int TRUCK_MINIMUM_SHOPPING_SPEND = 15;
	private static final int TRUCK_MAXIMUM_SHOPPING_SPEND = 20;
	
	public Truck() {
		super(TRUCK_SIZE, TRUCK_MINIMUM_FUEL_CAPACITY, TRUCK_MAXIMUM_FUEL_CAPACITY);
	}
}
