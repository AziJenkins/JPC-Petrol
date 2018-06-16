package model;

import exceptions.MinGreaterThanMaxException;

/**
 * @author AZJENKIN
 *
 */
public class FamilySedan extends Vehicle {

	/**
	 * 
	 */
	private static final double FAMILY_SEDAN_SIZE = 1.5;
	/**
	 * 
	 */
	private static final int FAMILY_SEDAN_MINIMUM_FUEL_CAPACITY = 12;
	/**
	 * 
	 */
	private static final int FAMILY_SEDAN_MAXUMIM_FUEL_CAPACITY = 18;
	/**
	 * 
	 */
	private static final double FAMILY_SEDAN_PROBABILITY_TO_SHOP = 0.4;
	/**
	 * 
	 */
	private static final int FAMILY_SEDAN_TICKS_BEFORE_NO_SHOPPING = 60;
	/**
	 * 
	 */
	private static final int FAMILY_SEDAN_MINIMUM_SHOPPING_TICKS = 12;
	/**
	 * 
	 */
	private static final int FAMILY_SEDAN_MAXIMUM_SHOPPING_TICKS = 30;
	/**
	 * 
	 */
	private static final double FAMILY_SEDAN_MINIMUM_SHOPPING_SPEND = 8;
	/**
	 * 
	 */
	private static final double FAMILY_SEDAN_MAXIMUM_SHOPPING_SPEND = 16;
	
	/**
	 * @throws MinGreaterThanMaxException
	 */
	public FamilySedan() throws MinGreaterThanMaxException{
		super(FAMILY_SEDAN_SIZE, FAMILY_SEDAN_MINIMUM_FUEL_CAPACITY, FAMILY_SEDAN_MAXUMIM_FUEL_CAPACITY, FAMILY_SEDAN_PROBABILITY_TO_SHOP, FAMILY_SEDAN_TICKS_BEFORE_NO_SHOPPING, FAMILY_SEDAN_MINIMUM_SHOPPING_TICKS, FAMILY_SEDAN_MAXIMUM_SHOPPING_TICKS, FAMILY_SEDAN_MINIMUM_SHOPPING_SPEND, FAMILY_SEDAN_MAXIMUM_SHOPPING_SPEND);
	}
}
