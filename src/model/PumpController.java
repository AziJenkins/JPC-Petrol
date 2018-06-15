package model;

public class PumpController {

	private Pump[] pumps;
	
	public PumpController(int numPumps) {
		pumps = new Pump[numPumps];
		for (int i = 0; i < numPumps; i++) {
			pumps[i] = new Pump();
		}
	}
	
	public Boolean enqueue(Vehicle v) {
		return false;
	}
	
	public void dequeueAllFullyPaid() {
		
	}
	
	public void tick() {
		
	}
	
	public Pump[] getPumps() {
		return this.pumps;
	}
}
