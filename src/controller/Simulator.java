package controller;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import exceptions.CustomerAlreadyPaidException;
import exceptions.CustomerAlreadyPresentException;
import exceptions.CustomerCarMismatchException;
import exceptions.CustomerCouldNotFindVehicleException;
import exceptions.CustomerHasNotPaidException;
import exceptions.MinGreaterThanMaxException;
import exceptions.TillFullException;
import exceptions.VehicleAlreadyPaidException;
import exceptions.VehicleIsNotOccupiedException;
import exceptions.VehicleNotFullException;
import view.TextBasedInterface;
import model.FamilySedan;
import model.Motorbike;
import model.PetrolStation;
import model.SmallCar;
import model.Truck;
import model.Vehicle;

/**
 * @author AZJENKIN
 *
 */
public class Simulator {

	/**
	 * A constant to store the maximum queue size for a single till
	 */
	public final static int MAX_QUEUE_SIZE = 5;

	/**
	 * A constant to store the minimum size of a vehicle
	 */
	public final static double SMALLEST_VEHICLE = 0.75;
	/**
	 * A constant to store the initial value of variable t
	 */
	public static final double INITIAL_T = 0.02;
	/**
	 * The Petrol Station model
	 */
	private PetrolStation station;
	/**
	 * The probability that a Small Car or Motorbike will arrive
	 */
	private final double p;
	/**
	 * The probability that a Family Sedan will arrive
	 */
	private final double q;
	/**
	 * The probability that a Truck will arrive
	 */
	private double t;
	/**
	 * A value to represent if the Petrol Station allows Trucks
	 */
	private boolean trucksAllowed;
	/**
	 * A Randomiser for deciding on if a Vehicle will spawn and which type it will
	 * be
	 */
	public static Random rand = new Random();
	/**
	 * The text based interface for the application
	 */
	public TextBasedInterface view;
	
	private int ticksElapsed;

	/**
	 * Initializing a simulator will initialize a model Petrol Station and a User
	 * Interface It will prompt the User Interface to gather the user chosen
	 * variables
	 */
	public Simulator(double p, double q, double t, boolean trucksAllowed, int numPumps, int numTills) {
		
		this.p = p;
		this.q = q;
		this.t = INITIAL_T;
		this.station = new PetrolStation(numPumps, numTills, SMALLEST_VEHICLE, MAX_QUEUE_SIZE);
		ticksElapsed = 0;
	}

	/**
	 * Run the simulation for a given number of ticks
	 * 
	 * @throws CustomerCouldNotFindVehicleException
	 * @throws TillFullException
	 * @throws CustomerHasNotPaidException
	 * @throws CustomerAlreadyPresentException
	 * @throws CustomerCarMismatchException
	 * @throws VehicleNotFullException
	 * @throws VehicleAlreadyPaidException
	 * @throws VehicleIsNotOccupiedException
	 * @throws CustomerAlreadyPaidException
	 * @throws MinGreaterThanMaxException 
	 * @throws InterruptedException 
	 */
	public void runSimulation(int ticks) throws CustomerAlreadyPaidException, VehicleIsNotOccupiedException, VehicleAlreadyPaidException, VehicleNotFullException, CustomerCarMismatchException, CustomerAlreadyPresentException, CustomerHasNotPaidException,
			TillFullException, CustomerCouldNotFindVehicleException, MinGreaterThanMaxException, InterruptedException {
		for (int i = 0; i < ticks; i ++) {
			station.tick(rollForVehicle());
			ticksElapsed++;
			TimeUnit.SECONDS.sleep(1);
		}
	}

	/**
	 * Generates a random number and based on the result compared to the chances of Vehicles spawning will either return a
	 * new Vehicle of the appropriate type or null
	 * @return Either null or a Vehicle
	 * @throws MinGreaterThanMaxException 
	 */
	private Vehicle rollForVehicle() throws MinGreaterThanMaxException {
		double chance = rand.nextDouble();
	
		if (chance <= p) {
			return new SmallCar();
		} else if (chance <= 2*p) {
			return new Motorbike();
		} else if (chance <= (2*p)+q) {
			return new FamilySedan();
		} else if (trucksAllowed && chance <= (2*p)+q+t) {
			return new Truck();
		} else {
			return null;
		}
	}

	/**
	 * Getter for the Simulators model Petrol Station
	 * 
	 * @return
	 */
	public PetrolStation getStation() {
		return this.station;
	}
	
	public int getTicks() {
		return ticksElapsed;
	}

}
