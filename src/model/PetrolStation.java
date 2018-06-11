package model;

public class PetrolStation {

	
	public PumpQueue pumps;
	public TillQueue tills;
	public Shop shop = new Shop();
	private double fuelIncome = 0;
	private double shopIncome = 0;

	public PetrolStation(int noPumps, int noTills) {
		this.pumps = new PumpQueue(noPumps);
		this.tills = new TillQueue(noTills);
		
	}
}
