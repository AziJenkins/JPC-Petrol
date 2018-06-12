/**
 * 
 */
package model;

import java.util.Random;
import java.util.UUID;

import exceptions.CustomerCarMismatchException;
import exceptions.MinGreaterThanMaxException;

/**
 * @author AZJENKIN
 *
 */
public abstract class Vehicle {
	private final double size;
	private final int fuelCapacity;
	private final int shoppingTicks;
	private final double shoppingSpend;
	private final double shopProbability;
	private final double ticksBeforeNoShop;
	private int currentFuel;
	private Boolean isFueled = false;
	private Boolean hasPaid = false;
	private UUID registration;
	private int ticksSinceArrival = 0;
	private Random rand = new Random();

	public Vehicle(double size, int minCapacity, int maxCapacity, double shopProbability, int ticksBeforeNoShop,
			int minShopTicks, int maxShopTicks, double minShopSpend, double maxShopSpend) throws MinGreaterThanMaxException {
		this.size = size;
		this.registration = UUID.randomUUID();
		this.currentFuel = 0;
		this.shopProbability = shopProbability;
		this.ticksBeforeNoShop = ticksBeforeNoShop;		
		this.fuelCapacity = calculateMaxCapacity(minCapacity, maxCapacity);
		this.shoppingTicks = calculateShoppingTicks(minShopTicks, maxShopTicks);
		this.shoppingSpend = calculateShopSpend(minShopSpend, maxShopSpend);
	}

	private int calculateMaxCapacity(int minCapacity, int maxCapacity) throws MinGreaterThanMaxException {
		int capacityDifference = maxCapacity - minCapacity;
		if (capacityDifference < 0)
			throw new MinGreaterThanMaxException();
		else if (capacityDifference == 0) 
			return maxCapacity;
		else 
			return rand.nextInt(maxCapacity - minCapacity) + minCapacity;
	}

	private int calculateShoppingTicks(int minShopTicks, int maxShopTicks) throws MinGreaterThanMaxException {
		int shopTickDifference = maxShopTicks - minShopTicks;
		if(shopTickDifference < 0)
			throw new MinGreaterThanMaxException();
		else if (shopTickDifference == 0) 
			return maxShopTicks;
		else 
			return rand.nextInt(maxShopTicks - minShopTicks) + minShopTicks;
		
	}

	private double calculateShopSpend(double minShopSpend, double maxShopSpend) throws MinGreaterThanMaxException {
		double shopSpendDifference = maxShopSpend - minShopSpend;
		if (shopSpendDifference < 0)
			throw new MinGreaterThanMaxException();
		else if (shopSpendDifference == 0)
			return maxShopSpend;
		else 
			return (rand.nextDouble() * (maxShopSpend - minShopSpend)) + minShopSpend;
	}

	public Boolean tryFill(int fuelRate) {
		if (!isFueled) {
			currentFuel += fuelRate;
			return true;
		} else {
			return false;
		}
	}

	public Customer leaveCar() {
		return new Customer(registration, shoppingTicks, shoppingSpend, decideToShop());
	}
	
	public Boolean decideToShop() {
		return ticksSinceArrival <= ticksBeforeNoShop;
	}

	public void reEnterCar(Customer c) throws CustomerCarMismatchException {
		if (c.getRegistration().equals(registration)) {
			hasPaid = c.getHasPaid();
		} else {
			throw new CustomerCarMismatchException();
		}
	}
	
	public void addTick() {
		this.ticksSinceArrival ++;
	}
}
