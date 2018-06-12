package model;

public class PetrolStation {

	
	public PumpQueue pumps;
	public TillQueue tills;
	public Shop shop = new Shop();
	private double fuelIncome = 0;
	private double shopIncome = 0;

	public PetrolStation(int numPumps, int numTills) {
		this.pumps = new PumpQueue(numPumps);
		this.tills = new TillQueue(numTills);
		
	}
	
	public void collectPayments() {
		
	}
}
