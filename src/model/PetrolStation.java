package model;

import java.util.HashSet;

import exceptions.CustomerAlreadyPresentException;

/**
 * @author AZJENKIN
 * A model Petrol Station
 */
public class PetrolStation {

	
	/**
	 * A controller for all of the pumps at the Petrol Station
	 */
	private PumpController pumps;
	/**
	 * A controller for all of the tills at the Petrol Station
	 */
	private TillController tills;
	/**
	 * The Shop in the Petrol Station
	 */
	private Shop shop = new Shop();

	/**
	 * The number of ticks that have passed at this Petrol Station
	 */
	private int ticksPassed = 0;
	/**
	 * The total income from fuel sales
	 */
	private int gallonsSold = 0;
	/**
	 * The total income from shop sales
	 */
	private double shopIncome = 0;
	
	/**
	 * Constructor for a petrol station
	 * @param numPumps = the number of Pumps at the Petrol Station
	 * @param numTills = the number of Tills at the Petrol Station
	 */
	public PetrolStation(int numPumps, int numTills, double smallestVehicle, int maxQueueSize) {
		this.pumps = new PumpController(numPumps, smallestVehicle);
		this.tills = new TillController(numTills, maxQueueSize);
		
	}
	
	/**
	 * Getter for fuel income
	 * @return
	 */
	public double getGallonsSold() {
		return gallonsSold;
	}

	/**
	 * Getter for shop income
	 * @return
	 */
	public double getShopIncome() {
		return shopIncome;
	}
	
	/**
	 * Collects payments from any Customer that is ready to pay 
	 * at any of the Tills in the Petrol Station
	 */
	public void collectPayments() {
		Payment[] payments = tills.collectPayments();
		for (Payment p : payments) {
			gallonsSold += p.getFuelGallons();
			shopIncome += p.getShopMoney();
		}
	}
	
	/**
	 * Getter for the Shop
	 * @return
	 */
	public Shop getShop() {
		return this.shop;
	}
	
	/**
	 * Getter for the Pump Controller
	 * @return
	 */
	public PumpController getPumps() {
		return this.pumps;
	}
	
	/**
	 * Getter for the Till Controller
	 * @return
	 */
	public TillController getTills() {
		return this.tills;
	}
	
	/**
	 * Takes a Vehicle and passes it to the Pump Controller
	 * @param v
	 * @return
	 */
	public Boolean recieveVehicle(Vehicle v) {
		return pumps.enqueue(v);
	}
	
	/**
	 * Takes a Customer and passes it to either the Shop or
	 * the Till Controller according to what the Customer wants
	 * @param c
	 * @throws CustomerAlreadyPresentException 
	 */
	public void recieveCustomer(Customer c) throws CustomerAlreadyPresentException {
		if (c.getHasPaid()) {
			//find car
		} else {
			shop.add(c);
		}	
	}
	
	/**
	 * Asks the Pump Controller to dequeue any Vehicles that are ready to leave
	 */
	public void dispatchComplete() {
		pumps.dequeueAllFullyPaid();
	}
	
	/**
	 * Progress time at the Petrol Station by 1 tick
	 * This will alert the Pump Controller, Till Controller 
	 * and Shop that time has Passed;
	 */
	public void tick() {
		//not ordered
		
		
	}

	/**
	 * Getter for the number of ticks that have passed
	 * @return
	 */
	public int getTicks() {
		return this.ticksPassed;
	}
}
