package model;

import exceptions.MinGreaterThanMaxException;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * @author AZJENKIN
 *
 */
public class Truck extends Vehicle {

	/**
	 * The size of a Family Sedan
	 */
	private static final double TRUCK_SIZE = 2;
	/**
	 * The minimum fuel capacity for a Family Sedan
	 * Used to generate a random tank size
	 */
	private static final int TRUCK_MINIMUM_FUEL_CAPACITY = 30;
	/**
	 * The maximum fuel capacity for a Family Sedan
	 * Used to generate a random tank size
	 */
	private static final int TRUCK_MAXIMUM_FUEL_CAPACITY = 40;
	/**
	 * The probability that the driver of a Family Sedan will visit the Shop
	 */
	private static final int TRUCK_PROBABILITY_TO_SHOP = 1;
	/**
	 * The number of ticks before the driver of a Family Sedan will definitely not visit the Shop
	 */
	private static final int TRUCK_TICKS_BEFORE_NO_SHOPPING = 48;
	/**
	 * The minimum number of ticks the driver of a Family Sedan will spend in the Shop
	 */
	private static final int TRUCK_MINIMUM_SHOPPING_TICKS = 24;
	/**
	 * The maximum number of ticks the driver of a Family Sedan will spend in the Shop
	 */
	private static final int TRUCK_MAXIMUM_SHOPPING_TICKS = 36;
	/**
	 * The minimum amount of money the driver of a Family Sedan will spend if they visit the Shop
	 */
	private static final int TRUCK_MINIMUM_SHOPPING_SPEND = 15;
	/**
	 * The maximum amount of money the driver of a Family Sedan will spend if they visit the Shop
	 */
	private static final int TRUCK_MAXIMUM_SHOPPING_SPEND = 20;
	
	private SimpleBooleanProperty isHappy;
	
	/**
	 * Constructor for a Truck
	 * @throws MinGreaterThanMaxException
	 */
	public Truck() throws MinGreaterThanMaxException {
		super(TRUCK_SIZE, TRUCK_MINIMUM_FUEL_CAPACITY, TRUCK_MAXIMUM_FUEL_CAPACITY, TRUCK_PROBABILITY_TO_SHOP, TRUCK_TICKS_BEFORE_NO_SHOPPING, TRUCK_MINIMUM_SHOPPING_TICKS, TRUCK_MAXIMUM_SHOPPING_TICKS, TRUCK_MINIMUM_SHOPPING_SPEND, TRUCK_MAXIMUM_SHOPPING_SPEND);
		isHappy = new SimpleBooleanProperty(true);
	}

	@Override
	protected void decideToShop() {
		super.decideToShop();
		if (getDidShop() == false) {
			isHappy.set(false);
		}
		
	}
	
	/**
	 * Getter for isHappy
	 * 
	 * @return boolean
	 */
	public SimpleBooleanProperty getIsHappy() {
		return isHappy;
	}
}
