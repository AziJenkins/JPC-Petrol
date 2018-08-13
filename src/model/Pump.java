package model;

import java.util.Iterator;

import exceptions.VehicleAlreadyPaidException;
import exceptions.VehicleIsNotOccupiedException;
import exceptions.VehicleNotFullException;
import utils.ObservableListQueue;

/**
 * A fuel Pump
 * 
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
	private double spaceUnused;
	/**
	 * The queue of Vehicles for the Pump
	 */
	private ObservableListQueue<Vehicle> queue;

	/**
	 * Constructor for a Pump
	 */
	public Pump(double smallestVehicleSize) {
		this.spaceUnused = PUMP_CAPACITY;
		this.queue = new ObservableListQueue<Vehicle>(PUMP_CAPACITY);
	}
	
	/**
	 * Takes a vehicle and adds it to the pump queue
	 * 
	 * @param v
	 * @return boolean
	 */
	public boolean enqueue(Vehicle v) {
		if (queue.add(v)) {
			spaceUnused -= v.getSize();
			return true;
		}
		return false;
	}

	/**
	 * try to fill the next vehicle in the queue
	 * 
	 * @return boolean
	 */
	public boolean fill() {
		if (queue.iterator().hasNext()) {
			return queue.peek().tryFill(FUEL_RATE);
		}
		else {
			return false;
		}
	}

	/**
	 * Progress time at the pump This will alert the Vehicle at the front of the
	 * queue that time has passed
	 * 
	 * @throws VehicleNotFullException
	 * @throws VehicleAlreadyPaidException
	 * @throws VehicleIsNotOccupiedException
	 */
	public Customer tick()
			throws VehicleIsNotOccupiedException, VehicleAlreadyPaidException, VehicleNotFullException {
		Iterator<Vehicle> i = queue.iterator();
		while (i.hasNext()) {
			i.next().addTick();
		}
		if(!fill()) {
			if (queue.iterator().hasNext() && queue.peek().getIsOccupied()) {
				return queue.peek().leaveVehicle();
			}
		}
		return null;
		// tryfill
		// leave car
	}
	
	/**
	 * Getter for space unused
	 * 
	 * @return
	 */
	public double getSpaceUnused() {
		return spaceUnused;
	}

	/**
	 * Getter for the queue for the Pump
	 * 
	 * @return
	 */
	public ObservableListQueue<Vehicle> getQueue() {
		return this.queue;
	}

	/**
	 * Getter for queue size
	 * 
	 * @return
	 */
	public double getQueueSize() {
		return queue.getSize();
	}

	/**
	 * loop through vehicles in the queue and check if the 
	 * vehicle has paid, if so then remove from the queue
	 * 
	 * @return Vehicle
	 */
	public Vehicle dequeueWhenFullyPaid() {
		Vehicle v = queue.peek();
		if (v != null && v.getHasPaid() && v.getIsOccupied()) {
			v = queue.remove();
			spaceUnused = PUMP_CAPACITY - queue.getSize();
			return v;
		}
		return null;
	}
}