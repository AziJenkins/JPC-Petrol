package Tests;

import org.junit.Before;
import model.PumpController;

public class TestPumpController {

	public PumpController pc;
	@Before
	public void setup() {
		pc = new PumpController(4, 0.75);
	}
	
	@Test
	public void testConstructor() {
		
	}
	
	@Test
	public void testEnqueue() {
		
	}
	
	@Test
	public void testDequeueAllFullyPaid() {
		
	}
	
	@Test
	public void testTick() {
		
	}
}
