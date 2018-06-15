package model;

public class TillController {
	
	private Till[] tills;
	
	public TillController(int numTills) {
		tills = new Till[numTills];
		for (int i = 0; i < numTills; i++) {
			tills[i] = new Till();
		}
	}
	
	public void enqueue() {
		
	}
	
	public void dequeue() {
		
	}
	
	public Payment[] collectPayments() {
		return null;
	}
	
	public Till[] getTills() {
		return this.tills;
	}
}
