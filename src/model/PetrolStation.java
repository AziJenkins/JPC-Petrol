package model;

import java.util.HashSet;

public class PetrolStation {

	
	private PumpController pumps;
	private TillController tills;
	private Shop shop = new Shop();

	private int ticksPassed = 0;
	private double fuelIncome = 0;
	private double shopIncome = 0;
	private HashSet<Vehicle> allowedVehicles; // should be in simulator - can calculate minimum space for vehicle

	public PetrolStation(int numPumps, int numTills) {
		this.pumps = new PumpController(numPumps);
		this.tills = new TillController(numTills);
		
	}
	
	public double getFuelIncome() {
		return fuelIncome;
	}

	public double getShopIncome() {
		return shopIncome;
	}
	
	public void collectPayments() {
		
	}
	
	public Shop getShop() {
		return this.shop;
	}
	
	public PumpController getPumps() {
		return this.pumps;
	}
	
	public TillController getTills() {
		return this.tills;
	}
	
	public Boolean recieveVehicle(Vehicle v) {
		return false;
	}
	
	public void recieveCustomer(Customer c) {
		
	}
	
	public void dispatchComplete() {
		
	}
	
	public void tick() {
		
	}

	public int getTicks() {
		return this.ticksPassed;
	}
}
