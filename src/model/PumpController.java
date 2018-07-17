package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * A controller for any number of Pumps
 * @author AZJENKIN
 *
 */
public class PumpController {

	/**
	 * An array containing all Pumps belonging to the Controller
	 */
	private Pump[] pumps;
	
	/**
	 * Constructor for a Pump Controller
	 * @param numPumps
	 */
	public PumpController(int numPumps, double smallestVehicle) {
		pumps = new Pump[numPumps];
		for (int i = 0; i < numPumps; i++) {
			pumps[i] = new Pump(smallestVehicle);
		}
	}
	
	/**
	 * Give a Vehicle to the Pump with the shortest queue
	 * @param v
	 * @return
	 */
	public Boolean enqueue(Vehicle v) {
		int shortestQueueIndex = 0;
		for(int i = 0; i < pumps.length; i++) {
			if(pumps[i].getQueueSize() < pumps[shortestQueueIndex].getQueueSize()) {
				shortestQueueIndex = i;
			}
		}
		return pumps[shortestQueueIndex].enqueue(v);
	}
	
	/**
	 * Remove all Vehicles that have paid for fuel and any Shop purchases
	 */
	public void dequeueAllFullyPaid() {
		for(int i = 0; i < pumps.length; i++) {
				pumps[i].dequeueWhenFullyPaid();
			}
		} //TODO refactor to use the pump.dequeueFullyPaid()
	
	
	/**
	 * Progress time
	 * This will alert each Pump that time has passed
	 */
	public void tick() {
		
	}
	
	/**
	 * Getter for the array of Pumps
	 * @return
	 */
	public Pump[] getPumps() {
		return this.pumps;
	}
}