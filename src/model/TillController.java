package model;

/**
 * A controller for any number of Tills
 * @author AZJENKIN
 *
 */
public class TillController {
	
	/**
	 * An array containing all the Tills belonging to the controller
	 */
	private Till[] tills;
	
	/**
	 * Constructor for a Till Controller
	 * @param numTills
	 */
	public TillController(int numTills, int maxQueueSize) {
		tills = new Till[numTills];
		for (int i = 0; i < numTills; i++) {
			tills[i] = new Till(maxQueueSize);
		}
	}
	
	/**
	 * Give a Customer to the Till with the shortest queue
	 */
	public void enqueue() {
		
	}
	
	/**
	 * Remove the Customer at the front of each Tills queue
	 * if they have already paid
	 */
	public void dequeueFullyPaid() {
		
	}
	
	/**
	 * Collect payments from the Customers at the front of
	 * each Tills queue if they are ready to pay
	 * @return
	 */
	public Payment[] collectPayments() {
		return null;
	}
	
	/**
	 * Getter for the array of Tills 
	 * @return
	 */
	public Till[] getTills() {
		return this.tills;
	}
}
