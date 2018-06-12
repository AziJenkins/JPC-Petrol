package model;

public class PumpQueue {

	private Pump[] pumps;
	
	public PumpQueue(int numPumps) {
		pumps = new Pump[numPumps];
		for (int i = 0; i < numPumps; i++) {
			pumps[i] = new Pump();
		}
	}
	
	public void fuel(int fuelAmount) {
		
	}
	
	public bool enqueue(Vehicle v) {
		
	}
	
	public void dequeueAllFullyPaid() {
		
	}
}
