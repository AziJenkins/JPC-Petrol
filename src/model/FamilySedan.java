package model;

import exceptions.MinGreaterThanMaxException;

/**
 * @author AZJENKIN
 *
 */
public class FamilySedan extends Vehicle {

	/**
	 * The size of a Family Sedan
	 */
	private static final double FAMILY_SEDAN_SIZE = 1.5;
	/**
	 * The minimum fuel capacity for a Family Sedan
	 * Used to generate a random tank size
	 */
	private static final int FAMILY_SEDAN_MINIMUM_FUEL_CAPACITY = 12;
	/**
	 * The maximum fuel capacity for a Family Sedan
	 * Used to generate a random tank size
	 */
	private static final int FAMILY_SEDAN_MAXUMIM_FUEL_CAPACITY = 18;
	/**
	 * The probability that the driver of a Family Sedan will visit the Shop
	 */
	private static final double FAMILY_SEDAN_PROBABILITY_TO_SHOP = 0.4;
	/**
	 * The number of ticks before the driver of a Family Sedan will definitely not visit the Shop
	 */
	private static final int FAMILY_SEDAN_TICKS_BEFORE_NO_SHOPPING = 60;
	/**
	 * The minimum number of ticks the driver of a Family Sedan will spend in the Shop
	 */
	private static final int FAMILY_SEDAN_MINIMUM_SHOPPING_TICKS = 12;
	/**
	 * The maximum number of ticks the driver of a Family Sedan will spend in the Shop
	 */
	private static final int FAMILY_SEDAN_MAXIMUM_SHOPPING_TICKS = 30;
	/**
	 * The minimum amount of money the driver of a Family Sedan will spend if they visit the Shop
	 */
	private static final double FAMILY_SEDAN_MINIMUM_SHOPPING_SPEND = 8;
	/**
	 * The maximum amount of money the driver of a Family Sedan will spend if they visit the Shop
	 */
	private static final double FAMILY_SEDAN_MAXIMUM_SHOPPING_SPEND = 16;
	
	/**
	 * Constructor for a Family Sedan
	 * @throws MinGreaterThanMaxException
	 */
	public FamilySedan() throws MinGreaterThanMaxException{
		super(FAMILY_SEDAN_SIZE, FAMILY_SEDAN_MINIMUM_FUEL_CAPACITY, FAMILY_SEDAN_MAXUMIM_FUEL_CAPACITY, FAMILY_SEDAN_PROBABILITY_TO_SHOP, FAMILY_SEDAN_TICKS_BEFORE_NO_SHOPPING, FAMILY_SEDAN_MINIMUM_SHOPPING_TICKS, FAMILY_SEDAN_MAXIMUM_SHOPPING_TICKS, FAMILY_SEDAN_MINIMUM_SHOPPING_SPEND, FAMILY_SEDAN_MAXIMUM_SHOPPING_SPEND);
	}
}
