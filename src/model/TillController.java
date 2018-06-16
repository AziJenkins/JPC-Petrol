package model;

/**
 * @author AZJENKIN
 *
 */
public class TillController {
	
	/**
	 * 
	 */
	private Till[] tills;
	
	/**
	 * @param numTills
	 */
	public TillController(int numTills) {
		tills = new Till[numTills];
		for (int i = 0; i < numTills; i++) {
			tills[i] = new Till();
		}
	}
	
	/**
	 * 
	 */
	public void enqueue() {
		
	}
	
	/**
	 * 
	 */
	public void dequeue() {
		
	}
	
	/**
	 * @return
	 */
	public Payment[] collectPayments() {
		return null;
	}
	
	/**
	 * @return
	 */
	public Till[] getTills() {
		return this.tills;
	}
}
