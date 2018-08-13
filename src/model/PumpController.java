package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import exceptions.CustomerAlreadyPresentException;
import exceptions.CustomerCarMismatchException;
import exceptions.CustomerCouldNotFindVehicleException;
import exceptions.CustomerHasNotPaidException;
import exceptions.VehicleAlreadyPaidException;
import exceptions.VehicleIsNotOccupiedException;
import exceptions.VehicleNotFullException;

/**
 * A controller for any number of Pumps
 * 
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
	 * 
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
	 * 
	 * @param v
	 * @return
	 */
	public Boolean enqueue(Vehicle v) {
		int shortestQueueIndex = 0;
		for (int i = 0; i < pumps.length; i++) {
			if (pumps[i].getQueueSize() < pumps[shortestQueueIndex].getQueueSize()) {
				shortestQueueIndex = i;
			}
		}
		return pumps[shortestQueueIndex].enqueue(v);
	}

	/**
	 * Remove all Vehicles that have paid for fuel and any Shop purchases
	 */
	public List<Vehicle> dequeueAllFullyPaid() {
		List<Vehicle> complete = new LinkedList<Vehicle>();
		for (int i = 0; i < pumps.length; i++) {
			Vehicle v = pumps[i].dequeueWhenFullyPaid();
			if (v != null) {
				complete.add(v);
			}
		}
		return complete;
	}

	/**
	 * Progress time This will alert each Pump that time has passed
	 * @return 
	 * 
	 * @throws VehicleNotFullException
	 * @throws VehicleAlreadyPaidException
	 * @throws VehicleIsNotOccupiedException
	 * @throws CustomerHasNotPaidException
	 * @throws CustomerAlreadyPresentException
	 * @throws CustomerCarMismatchException
	 * @throws CustomerCouldNotFindVehicleException
	 */
	public List<Customer> tick()
			throws VehicleIsNotOccupiedException, VehicleAlreadyPaidException, VehicleNotFullException, CustomerCarMismatchException, CustomerAlreadyPresentException, CustomerHasNotPaidException{
		ArrayList<Customer> ready = new ArrayList<Customer>();
		for (int i = 0; i < pumps.length; i++) {
			Customer c = pumps[i].tick();
			if (c != null) {
				ready.add(c);
			}

		}
		return ready;
		// call tick on each pump
		// put customers back in car
	}

	/**
	 * Getter for the array of Pumps
	 * 
	 * @return
	 */
	public Pump[] getPumps() {
		return this.pumps;
	}

	/**
	 * takes a customer and puts the customer back
	 * into the car if all checks are true
	 * 
	 * @param c
	 * @throws CustomerCarMismatchException
	 * @throws CustomerAlreadyPresentException
	 * @throws CustomerHasNotPaidException
	 * @throws CustomerCouldNotFindVehicleException
	 * 
	 */
	public void recieveCustomer(Customer c) throws CustomerCarMismatchException, CustomerAlreadyPresentException, CustomerHasNotPaidException, CustomerCouldNotFindVehicleException {
		boolean found = false;
		for (Pump p : pumps) {
			if (p.getQueue().peek() != null && p.getQueue().peek().getRegistration() == c.getRegistration()) {
				p.getQueue().peek().reEnterCar(c);
				found = true;
				break;
			}
		}
		if (!found) {
			throw new CustomerCouldNotFindVehicleException();
		}
	}
}