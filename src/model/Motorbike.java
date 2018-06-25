package model;

import exceptions.MinGreaterThanMaxException;

/**
 * @author AZJENKIN
 *
 */
public class Motorbike extends Vehicle {

	/**
	 * The size of a Motorbike
	 */
	private static final double MOTORBIKE_SIZE = 0.75;
	/**
	 * The minimum fuel capacity for a Motorbike
	 * Used to generate a random tank size
	 */
	private static final int MOTORBIKE_MINIMUM_FUEL_CAPACITY = 5;
	/**
	 * The maximum fuel capacity for a Motorbike
	 * Used to generate a random tank size
	 */
	private static final int MOTORBIKE_MAXIMUM_FUEL_CAPACITY = 5;
	/**
	 * The number of ticks before the driver of a Motorbike will definitely not visit the Shop
	 */
	private static final int MOTORBIKE_TICKS_BEFORE_NO_SHOPPING = 0;
	/**
	 * The probability that the driver of a Motorbike will visit the Shop
	 */
	private static final int MOTORBIKE_SHOP_PROBABILITY = 0;
	/**
	 * The minimum number of ticks the driver of a Motorbike will spend in the Shop
	 */
	private static final int MOTORBIKE_MINIMUM_SHOPPING_TICKS = 0;
	/**
	 * The maximum number of ticks the driver of a Motorbike will spend in the Shop
	 */
	private static final int MOTORBIKE_MAXIMUM_SHOPPING_TICKS = 0;
	/**
	 * The minimum amount of money the driver of a Motorbike will spend if they visit the Shop
	 */
	private static final int MOTORBIKE_MINIMUM_SHOPPING_SPEND = 0;
	/**
	 * The maximum amount of money the driver of a Motorbike will spend if they visit the Shop
	 */
	private static final int MOTORBIKE_MAXIMUM_SHOPPING_SPEND = 0;
	
	/**
	 * Constructor for a Motorbike
	 * @throws MinGreaterThanMaxException
	 */
	public Motorbike() throws MinGreaterThanMaxException{
		super(MOTORBIKE_SIZE, MOTORBIKE_MINIMUM_FUEL_CAPACITY, MOTORBIKE_MAXIMUM_FUEL_CAPACITY, MOTORBIKE_SHOP_PROBABILITY, MOTORBIKE_TICKS_BEFORE_NO_SHOPPING, MOTORBIKE_MINIMUM_SHOPPING_TICKS, MOTORBIKE_MAXIMUM_SHOPPING_TICKS, MOTORBIKE_MINIMUM_SHOPPING_SPEND, MOTORBIKE_MAXIMUM_SHOPPING_SPEND);
	}
}
