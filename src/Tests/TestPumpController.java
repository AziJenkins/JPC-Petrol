

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import exceptions.EmptyQueueException;
import exceptions.MinGreaterThanMaxException;
import model.FamilySedan;
import model.Motorbike;
import model.PumpController;
import model.SmallCar;
import model.Truck;
import model.Vehicle;

public class TestPumpController {

	public PumpController pc;
	@Before
	public void setup() {
		pc = new PumpController(4, 0.75);
	}
	
	@Test
	public void testEnqueue() throws MinGreaterThanMaxException, EmptyQueueException {
		/*
		 * add car
		 * assert its there
		 * 
		 * add same car
		 * assert exception thrown
		 */
		SmallCar sc = new SmallCar();
		Motorbike mb = new Motorbike();
		FamilySedan fs = new FamilySedan();
		Truck tr = new Truck();
		LinkedList<Vehicle> q1 = new LinkedList<Vehicle>();
		LinkedList<Vehicle> q2 = new LinkedList<Vehicle>();
		LinkedList<Vehicle> q3 = new LinkedList<Vehicle>();
		LinkedList<Vehicle> q4 = new LinkedList<Vehicle>();
		
		assertTrue(pc.enqueue(sc)); //queue 1 space 1 capacity 1
		q1.add(sc);
		assertTrue(pc.enqueue(mb)); //queue 2 space 1 capacity 0.75
		q2.add(mb);
		assertTrue(pc.enqueue(fs)); //queue 3 space 1 capacity 1.5
		q3.add(fs);
		assertTrue(pc.enqueue(tr)); //queue 4 space 1 capacity 2
		q4.add(tr);
		assertTrue(pc.enqueue(sc)); //queue 2 space 2 capacity 1.75
		q2.add(sc);
		assertTrue(pc.enqueue(sc)); //queue 1 space 2 capacity 2
		q1.add(sc);
		assertFalse(pc.enqueue(tr)); //too large for any queue
		assertTrue(pc.enqueue(fs)); //queue 3 space 2 capacity 3
		q3.add(fs);
		assertTrue(pc.enqueue(mb)); //queue 2 space 3 capacity 2.5
		q2.add(mb);
		assertFalse(pc.enqueue(fs)); //too large for any queue
		assertTrue(pc.enqueue(mb)); //queue 1 space 3 capacity 2.75
		q1.add(mb);
		
		for (Vehicle v: q1) {
			assertEquals(v, pc.getPumps()[0].getQueue().remove());
		}
		for (Vehicle v: q2) {
			assertEquals(v, pc.getPumps()[1].getQueue().remove());
		}
		for (Vehicle v: q3) {
			assertEquals(v, pc.getPumps()[2].getQueue().remove());
		}
		for (Vehicle v: q4) {
			assertEquals(v, pc.getPumps()[3].getQueue().remove());
		}
		
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
