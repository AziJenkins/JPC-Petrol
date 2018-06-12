package model;

import exceptions.MinGreaterThanMaxException;

public class SmallCar extends Vehicle {

	
	private static final double SMALL_CAR_SIZE = 1;
	private static final int SMALL_CAR_MINIMUM_FUEL_CAPACITY = 7;
	private static final int SMALL_CAR_MAXIMUM_FUEL_CAPACITY = 9;
	private static final double SMALL_CAR_PROBABILITY_TO_SHOP = 0.3;
	private static final int SMALL_CAR_TICKS_BEFORE_NO_SHOPPING = 30;
	private static final int SMALL_CAR_MINIMUM_SHOPPING_TICKS = 12;
	private static final int SMALL_CAR_MAXIMUM_SHOPPING_TICKS = 24;
	private static final double SMALL_CAR_MINIMUM_SHOPPING_SPEND = 5;
	private static final double SMALL_CAR_MAXIMUM_SHOPPING_SPEND = 10;
	
	public SmallCar() throws MinGreaterThanMaxException {
		super(SMALL_CAR_SIZE, SMALL_CAR_MINIMUM_FUEL_CAPACITY, SMALL_CAR_MAXIMUM_FUEL_CAPACITY, SMALL_CAR_PROBABILITY_TO_SHOP, SMALL_CAR_TICKS_BEFORE_NO_SHOPPING, SMALL_CAR_MINIMUM_SHOPPING_TICKS, SMALL_CAR_MAXIMUM_SHOPPING_TICKS, SMALL_CAR_MINIMUM_SHOPPING_SPEND, SMALL_CAR_MAXIMUM_SHOPPING_SPEND);
	}
}
