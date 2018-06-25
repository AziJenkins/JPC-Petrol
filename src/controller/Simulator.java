package controller;

import java.util.Random;

import view.TextBasedInterface;

import model.PetrolStation;
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
	 * A Randomiser for deciding on if a Vehicle will spawn and which type it will be
	 */
	public static Random rand;
	/**
	 * The text based interface for the application
	 */
	public TextBasedInterface view;
	
	/**
	 * Initializing a simulator will initialize a model Petrol Station and a User Interface
	 * It will prompt the User Interface to gather the user chosen variables
	 */
	public Simulator() {
		this.view = new TextBasedInterface();
		view.start();
		this.p = view.getP();
		this.q = view.getQ();
		if (view.getTrucksAllowed()) {
			t = INITIAL_T;
		}
		int numPumps = view.getNumPumps();
		int numTills = view.getNumTills();
		this.station = new PetrolStation(numPumps, numTills, SMALLEST_VEHICLE, MAX_QUEUE_SIZE);
	}

	/**
	 * Progress time by 1 tick
	 * This method will progress time in the model and alert the user interface to update
	 */
	public void tick() {
		
		
	}
	
	/**
	 * Generates a random number and based on the result compared to the chances of Vehicles spawning will either return a
	 * new Vehicle of the appropriate type or null
	 * @return Either null or a Vehicle
	 */
	public Vehicle rollForVehicle() {
		return null;
	}
	
	/**
	 * Give the specified Vehicle to the petrol station
	 * @param v = the Vehicle to push 
	 */
	public void pushVehicle(Vehicle v) {
		
	}
	
	/**
	 * Getter for the Simulators model Petrol Station
	 * @return
	 */
	public PetrolStation getStation() {
		return this.station;
	}
	
}
