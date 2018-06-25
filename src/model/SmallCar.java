package model;

import exceptions.MinGreaterThanMaxException;

/**
 * @author AZJENKIN
 *
 */
public class SmallCar extends Vehicle {

	
	/**
	 * The size of a Small Car
	 */
	private static final double SMALL_CAR_SIZE = 1;
	/**
	 * The minimum fuel capacity for a Small Car
	 * Used to generate a random tank size
	 */
	private static final int SMALL_CAR_MINIMUM_FUEL_CAPACITY = 7;
	/**
	 * The maximum fuel capacity for a Small Car
	 * Used to generate a random tank size
	 */
	private static final int SMALL_CAR_MAXIMUM_FUEL_CAPACITY = 9;
	/**
	 * The probability that the driver of a Small Car will visit the Shop
	 */
	private static final double SMALL_CAR_PROBABILITY_TO_SHOP = 0.3;
	/**
	 * The number of ticks before the driver of a Small Car will definitely not visit the Shop
	 */
	private static final int SMALL_CAR_TICKS_BEFORE_NO_SHOPPING = 30;
	/**
	 * The minimum number of ticks the driver of a Small Car will spend in the Shop
	 */
	private static final int SMALL_CAR_MINIMUM_SHOPPING_TICKS = 12;
	/**
	 * The maximum number of ticks the driver of a Small Car will spend in the Shop
	 */
	private static final int SMALL_CAR_MAXIMUM_SHOPPING_TICKS = 24;
	/**
	 * The minimum amount of money the driver of a Small Car will spend if they visit the Shop
	 */
	private static final double SMALL_CAR_MINIMUM_SHOPPING_SPEND = 5;
	/**
	 * The maximum amount of money the driver of a Small Car will spend if they visit the Shop
	 */
	private static final double SMALL_CAR_MAXIMUM_SHOPPING_SPEND = 10;
	
	/**
	 * Constructor for a Small Car
	 * @throws MinGreaterThanMaxException
	 */
	public SmallCar() throws MinGreaterThanMaxException {
		super(SMALL_CAR_SIZE, SMALL_CAR_MINIMUM_FUEL_CAPACITY, SMALL_CAR_MAXIMUM_FUEL_CAPACITY, SMALL_CAR_PROBABILITY_TO_SHOP, SMALL_CAR_TICKS_BEFORE_NO_SHOPPING, SMALL_CAR_MINIMUM_SHOPPING_TICKS, SMALL_CAR_MAXIMUM_SHOPPING_TICKS, SMALL_CAR_MINIMUM_SHOPPING_SPEND, SMALL_CAR_MAXIMUM_SHOPPING_SPEND);
	}
}
