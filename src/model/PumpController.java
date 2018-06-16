package model;

/**
 * @author AZJENKIN
 *
 */
public class PumpController {

	/**
	 * 
	 */
	private Pump[] pumps;
	
	/**
	 * @param numPumps
	 */
	public PumpController(int numPumps) {
		pumps = new Pump[numPumps];
		for (int i = 0; i < numPumps; i++) {
			pumps[i] = new Pump();
		}
	}
	
	/**
	 * @param v
	 * @return
	 */
	public Boolean enqueue(Vehicle v) {
		return false;
	}
	
	/**
	 * 
	 */
	public void dequeueAllFullyPaid() {
		
	}
	
	/**
	 * 
	 */
	public void tick() {
		
	}
	
	/**
	 * @return
	 */
	public Pump[] getPumps() {
		return this.pumps;
	}
}
