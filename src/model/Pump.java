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
	
	public final static double FUEL_RATE = 1;

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
		this.queue = new CircularArrayQueue<Vehicle>(PUMP_CAPACITY, smallestVehicleSize);
	}
	
	public boolean enqueue(Vehicle v) {
		if (queue.add(v)) {
			spaceUnused -= v.getSize();
			return true;
		}
		return false;
	}

	private boolean fill() {
		return queue.peek().tryFill(FUEL_RATE);
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
	
	public double getQueueSize() {
		return queue.getSize();
	}
}