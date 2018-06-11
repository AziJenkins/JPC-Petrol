package model;

public class TillQueue {
	
	private Till[] tills;
	
	public TillQueue(int noTills) {
		tills = new Till[noTills];
		for (int i = 0; i < noTills; i++) {
			tills[i] = new Till();
		}
	}
}
