/**
 * 
 */
package model;

import java.util.UUID;

import controller.Simulator;
import exceptions.CustomerAlreadyPresentException;
import exceptions.CustomerCarMismatchException;
import exceptions.CustomerHasNotPaidException;
import exceptions.MinGreaterThanMaxException;
import exceptions.VehicleAlreadyPaidException;
import exceptions.VehicleIsNotOccupiedException;
import exceptions.VehicleNotFullException;
import interfaces.QueueItem;

/**
 * A model Vehicle
 * 
 * @author AZJENKIN
 *
 */
public abstract class Vehicle implements QueueItem {
	/**
	 * The amount of space the Vehicle takes up in a queue
	 */
	private final double size;
	/**
	 * The capacity of the Vehicles fuel tank in Gallons
	 */
	private final int fuelCapacity;
	/**
	 * The amount of time the driver of the Vehicle will spend in the Shop if they
	 * visit
	 */
	private final int shoppingTicks;
	/**
	 * The amount of money the driver of the Vehicle will spend in the Shop if they
	 * visit
	 */
	private final double shoppingSpend;
	/**
	 * The probability that the driver of the Vehicle will visit the shop given that
	 * they fueled fast enough
	 */
	private final double shopProbability;
	/**
	 * If the driver of the Vehicle does not complete fueling within this time they
	 * will not visit the shop
	 */
	private final double ticksBeforeNoShop;
	/**
	 * The time that the driver of the Vehicle will take to complete payment at the
	 * till
	 */
	private final int payTicks;
	/**
	 * The current amount of fuel in the Vehicle
	 */
	private double currentFuel;
	/**
	 * A flag to show that the Vehicle is full
	 */
	private Boolean isFueled = false;
	/**
	 * A flag to show that the driver of the Vehicle has paid
	 */
	private Boolean hasPaid = false;
	/**
	 * A flag to show that the driver of the Vehicle is in it
	 */
	private Boolean isOccupied = true; // TODO set this
	/**
	 * The unique registration of the Vehicle
	 */
	private boolean didShop;

	private UUID registration;
	/**
	 * The number of ticks the Vehicle has been at the Petrol Station
	 */
	private int ticksSinceArrival = 0;

	/**
	 * Constructor for a Vehicle
	 * 
	 * @param size              The amount of space the Vehicle takes up in a queue
	 * @param minCapacity       The minimum capacity of the Vehicles fuel tank
	 * @param maxCapacity       The maximum capacity of the Vehicles fuel tank
	 * @param shopProbability   The probability that the driver of the Vehicle will
	 *                          visit the Shop
	 * @param ticksBeforeNoShop The time before which the driver of the Vehicle will
	 *                          definitely not visit the Shop
	 * @param minShopTicks      The minimum (ticks)time the driver of the Vehicle
	 *                          will spend browsing the Shop
	 * @param maxShopTicks      The maximum (ticks)time the driver of the Vehicle
	 *                          will spend browsing the Shop
	 * @param minShopSpend      The minimum amount the driver will spend if they
	 *                          visit the Shop
	 * @param maxShopSpend      The maximum amount the driver will spend if they
	 *                          visit the Shop
	 * @throws MinGreaterThanMaxException
	 */
	public Vehicle(double size, int minCapacity, int maxCapacity, double shopProbability, int ticksBeforeNoShop, int minShopTicks, int maxShopTicks, double minShopSpend, double maxShopSpend) throws MinGreaterThanMaxException {
		this.size = size;
		this.registration = UUID.randomUUID();
		this.currentFuel = 0;
		this.shopProbability = shopProbability;
		this.ticksBeforeNoShop = ticksBeforeNoShop;
		this.fuelCapacity = (int) Math.round(randomiseBetweenLimits(minCapacity, maxCapacity));
		this.shoppingTicks = (int) Math.round(randomiseBetweenLimits(minShopTicks, maxShopTicks));
		this.shoppingSpend = (double) Math.round(randomiseBetweenLimits(minShopSpend, maxShopSpend) * 100) / 100;
		this.payTicks = (int) Math.round(randomiseBetweenLimits(Customer.MINIMUM_PAY_TICKS, Customer.MAXIMUM_PAY_TICKS));
	}

	/**
	 * Chooses a random number between a lower limit and an upper limit
	 * 
	 * @param minValue
	 * @param maxValue
	 * @return
	 * @throws MinGreaterThanMaxException
	 */
	private double randomiseBetweenLimits(double minValue, double maxValue) throws MinGreaterThanMaxException {
		double capacityDifference = maxValue - minValue;
		if (capacityDifference < 0)
			throw new MinGreaterThanMaxException();
		else if (capacityDifference == 0)
			return maxValue;
		else
			return (Simulator.rand.nextDouble() * (maxValue - minValue)) + minValue;
	}

	/**
	 * Attempt to fill the Vehicle
	 * 
	 * @param fuelRate The number of gallons to fuel the Vehicle
	 * @return
	 */
	public Boolean tryFill(double fuelRate) {
		if (!isFueled) {
			currentFuel += fuelRate;
			if (currentFuel >= fuelCapacity) {
				isFueled = true;
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Getter for the size of the Vehicle
	 * 
	 * @return
	 */
	@Override
	public double getSize() {
		return size;
	}

	/**
	 * Create a driver to leave the Vehicle
	 * 
	 * @return
	 * @throws VehicleIsNotOccupiedException
	 * @throws VehicleAlreadyPaidException
	 * @throws VehicleNotFullException
	 */
	public Customer leaveVehicle() throws VehicleIsNotOccupiedException, VehicleAlreadyPaidException, VehicleNotFullException {
		if (!isFueled) {
			throw new VehicleNotFullException();
		} else if (!isOccupied) {
			throw new VehicleIsNotOccupiedException();
		} else if (hasPaid) {
			throw new VehicleAlreadyPaidException();
		} else {
			isOccupied = false;
			decideToShop();
			int adjustedShopTicks = shoppingTicks;
			double adjustedShopSpend = shoppingSpend;
			if (!didShop) {
				adjustedShopTicks = 0;
				adjustedShopSpend = 0;
			}
			return new Customer(registration, adjustedShopTicks, adjustedShopSpend, currentFuel, didShop, payTicks);
		}
	}

	/**
	 * Choose to shop based of the number of ticks since arrival to a Petrol Station
	 * compared to a reasonable amount of time (ticksBeforeNoShop)
	 * 
	 * @return
	 */
	protected void decideToShop() {
		if (ticksSinceArrival <= ticksBeforeNoShop) {
			didShop = Simulator.rand.nextDouble() < shopProbability;
		} else {
			didShop = false;
		}
	}

	/**
	 * Set the hasPaid and isOccupied flags when the drive reenters their Vehicle
	 * 
	 * @param c A Customer
	 * @throws CustomerAlreadyPresentException
	 * @throws CustomerHasNotPaidException
	 */
	public void reEnterCar(Customer c) throws CustomerCarMismatchException, CustomerAlreadyPresentException, CustomerHasNotPaidException {
		if (c.getRegistration().equals(registration)) {
			if (isOccupied) {
				throw new CustomerAlreadyPresentException();
			}
			if (c.getHasPaid()) {
				hasPaid = c.getHasPaid();
				isOccupied = true;
			} else {
				throw new CustomerHasNotPaidException();
			}
			setIsOccupied(true);
		} else {
			throw new CustomerCarMismatchException();
		}

		// TODO throw CustomerAlreadyPresentException if already occupied
	}

	public int getFuelCapacity() {
		return fuelCapacity;
	}

	public boolean getHasPaid() {
		return hasPaid;
	}

	public UUID getRegistration() {
		return registration;
	}

	/**
	 * Increase the number of ticks since arrival
	 */
	public void addTick() {
		this.ticksSinceArrival++;
	}

	public Boolean getIsOccupied() {
		return isOccupied;
	}

	private void setIsOccupied(Boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	/*
	 * TODO Test method. Needs to be removed
	 */
	public void setHasPaid(boolean b) {
		hasPaid = b;

	}
	
	public boolean getDidShop() {
		return didShop;
	}
	
	public double getShoppingSpend() {
		return shoppingSpend;
	}
}
