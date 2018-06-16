package model;

import java.util.HashSet;

/**
 * @author AZJENKIN
 *
 */
public class PetrolStation {

	
	/**
	 * 
	 */
	private PumpController pumps;
	/**
	 * 
	 */
	private TillController tills;
	/**
	 * 
	 */
	private Shop shop = new Shop();

	/**
	 * 
	 */
	private int ticksPassed = 0;
	/**
	 * 
	 */
	private double fuelIncome = 0;
	/**
	 * 
	 */
	private double shopIncome = 0;
	/**
	 * 
	 */
	private HashSet<Vehicle> allowedVehicles; // should be in simulator - can calculate minimum space for vehicle

	/**
	 * @param numPumps
	 * @param numTills
	 */
	public PetrolStation(int numPumps, int numTills) {
		this.pumps = new PumpController(numPumps);
		this.tills = new TillController(numTills);
		
	}
	
	/**
	 * @return
	 */
	public double getFuelIncome() {
		return fuelIncome;
	}

	/**
	 * @return
	 */
	public double getShopIncome() {
		return shopIncome;
	}
	
	/**
	 * 
	 */
	public void collectPayments() {
		
	}
	
	/**
	 * @return
	 */
	public Shop getShop() {
		return this.shop;
	}
	
	/**
	 * @return
	 */
	public PumpController getPumps() {
		return this.pumps;
	}
	
	/**
	 * @return
	 */
	public TillController getTills() {
		return this.tills;
	}
	
	/**
	 * @param v
	 * @return
	 */
	public Boolean recieveVehicle(Vehicle v) {
		return false;
	}
	
	/**
	 * @param c
	 */
	public void recieveCustomer(Customer c) {
		
	}
	
	/**
	 * 
	 */
	public void dispatchComplete() {
		
	}
	
	/**
	 * 
	 */
	public void tick() {
		
	}

	/**
	 * @return
	 */
	public int getTicks() {
		return this.ticksPassed;
	}
}
