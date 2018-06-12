package model;

import exceptions.MinGreaterThanMaxException;

public class Motorbike extends Vehicle {

	private static final double MOTORBIKE_SIZE = 0.75;
	private static final int MOTORBIKE_MINIMUM_FUEL_CAPACITY = 5;
	private static final int MOTORBIKE_MAXIMUM_FUEL_CAPACITY = 5;
	private static final int MOTORBIKE_TICKS_BEFORE_NO_SHOPPING = 0;
	private static final int MOTORBIKE_SHOP_PROBABILITY = 0;
	private static final int MOTORBIKE_MINIMUM_SHOPPING_TICKS = 0;
	private static final int MOTORBIKE_MAXIMUM_SHOPPING_TICKS = 0;
	private static final int MOTORBIKE_MINIMUM_SHOPPING_SPEND = 0;
	private static final int MOTORBIKE_MAXIMUM_SHOPPING_SPEND = 0;
	
	public Motorbike() throws MinGreaterThanMaxException{
		super(MOTORBIKE_SIZE, MOTORBIKE_MINIMUM_FUEL_CAPACITY, MOTORBIKE_MAXIMUM_FUEL_CAPACITY, MOTORBIKE_SHOP_PROBABILITY, MOTORBIKE_TICKS_BEFORE_NO_SHOPPING, MOTORBIKE_MINIMUM_SHOPPING_TICKS, MOTORBIKE_MAXIMUM_SHOPPING_TICKS, MOTORBIKE_MINIMUM_SHOPPING_SPEND, MOTORBIKE_MAXIMUM_SHOPPING_SPEND);
	}
}
