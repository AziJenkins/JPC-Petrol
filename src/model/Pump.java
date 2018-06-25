package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

import utils.CircularArrayQueue;

/**
 * A fuel Pump
 * @author AZJENKIN
 *
 */
public class Pump {

	/**
	 * The capacity of the queue for the Pump
	 */
	public final static double PUMP_CAPACITY = 3;

	/**
	 * The amount of space still unused in the Pumps queue
	 */
	public double spaceUnused;
	/**
	 * The queue of Vehicles for the Pump
	 */
	private CircularArrayQueue<Vehicle> queue;
	
	/**
	 * Constructor for a Pump
	 */
	public Pump(double smallestVehicleSize) {
		this.spaceUnused = PUMP_CAPACITY;
		this.queue = new CircularArrayQueue<Vehicle>((int)Math.ceil(PUMP_CAPACITY * smallestVehicleSize));
	}

	/**
	 * Progress time at the pump
	 * This will alert the Vehicle at the front of the queue that time has passed
	 */
	public void tick() {
		
	}

	/**
	 * Getter for the queue for the Pump
	 * @return
	 */
	public CircularArrayQueue<Vehicle> getQueue() {
		return this.queue;
	}
}