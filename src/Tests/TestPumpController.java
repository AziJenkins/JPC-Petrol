package Tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import model.PumpController;
import model.SmallCar;

public class TestPumpController {

	public PumpController pc;
	@Before
	public void setup() {
		pc = new PumpController(4, 0.75);
	}
	
	@Test
	public void testEnqueue() {
		/*
		 * add car
		 * assert its there
		 * 
		 * add same car
		 * assert exception thrown
		 */
	}
	
	@Test
	public void testEnqueueFull() {
		/*
		 * add cars until full
		 * add car
		 * check exception thrown and queue is in same state
		 */
	}
	
	@Test
	public void testDequeueAllFullyPaid() {
		/*
		 * add 3 cars to pc
		 * force fully paid 2
		 * 
		 * dequeue
		 * assert they are returned
		 * assert queues are empy
		 * assert the not paid is still there
		 */
	}
	
	@Test
	public void testTick() {
		
	}
}
