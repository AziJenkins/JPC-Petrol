package model;

public class TillQueue {
	
	private Till[] tills;
	
	public TillQueue(int numTills) {
		tills = new Till[numTills];
		for (int i = 0; i < numTills; i++) {
			tills[i] = new Till();
		}
	}
	
	public void enqueue() {
		
	}
	
	public void dequeue() {
		
	}
	
	public double payFuel() {
		
	}
	
	public double payShop() {
		
	}
}
