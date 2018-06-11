package model;

public class FamilySedan extends Vehicle {

	private static final double FAMILY_SEDAN_SIZE = 1.5;
	private static final int FAMILY_SEDAN_MINIMUM_FUEL_CAPACITY = 12;
	private static final int FAMILY_SEDAN_MAXUMIM_FUEL_CAPACITY = 18;
	
	public FamilySedan() {
		super(FAMILY_SEDAN_SIZE, FAMILY_SEDAN_MINIMUM_FUEL_CAPACITY, FAMILY_SEDAN_MAXUMIM_FUEL_CAPACITY);
	}
}
