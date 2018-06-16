package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

import utils.CircularArrayQueue;

/**
 * @author AZJENKIN
 *
 */
public class Pump {

	/**
	 * 
	 */
	public final static double PUMP_CAPACITY = 3;
	/**
	 * 
	 */
	public final static double SMALLEST_VEHICLE = 0.75; //this is an awful place to store this
	
	/**
	 * 
	 */
	public double spaceUnused;
	/**
	 * 
	 */
	private CircularArrayQueue<Vehicle> queue;
	
	/**
	 * 
	 */
	public Pump() {
		this.spaceUnused = PUMP_CAPACITY;
		this.queue = new CircularArrayQueue<Vehicle>((int)Math.ceil(PUMP_CAPACITY * SMALLEST_VEHICLE));
	}

	/**
	 * 
	 */
	public void tick() {
		
	}

	/**
	 * @return
	 */
	public CircularArrayQueue<Vehicle> getQueue() {
		return this.queue;
	}
}