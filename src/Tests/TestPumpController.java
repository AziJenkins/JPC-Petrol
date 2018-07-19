

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.EmptyQueueException;
import exceptions.MinGreaterThanMaxException;
import model.FamilySedan;
import model.Motorbike;
import model.Pump;
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
	public void testEnqueueFull() throws MinGreaterThanMaxException {
		PumpController pc = new PumpController(2, 0.75);
		Truck t1 = new Truck();
		Truck t2 = new Truck();
		Truck t3 = new Truck();
		
		assertTrue(pc.enqueue(t1));
		assertTrue(pc.enqueue(t2));
		assertFalse(pc.enqueue(t3));
		
		Pump[] pumps = pc.getPumps();
		Iterator<Vehicle> i1 = pumps[0].getQueue().iterator();
		assertTrue(i1.hasNext());
		assertEquals(t1, i1.next());
		assertFalse(i1.hasNext());
		
		Iterator<Vehicle> i2 = pumps[1].getQueue().iterator();
		assertTrue(i2.hasNext());
		assertEquals(t2, i2.next());
		assertFalse(i2.hasNext());
		
		assertFalse(pumps[0].getQueue().contains(t3));
		assertFalse(pumps[1].getQueue().contains(t3));
	}
	
	@Test
	public void testDequeueAllFullyPaid() throws MinGreaterThanMaxException {
		/*
		 * add 3 cars to pc
		 * force fully paid 2
		 * 
		 * dequeue
		 * assert they are returned
		 * assert queues are empy
		 * assert the not paid is still there
		 */
		
		PumpController pc = new PumpController(4, 0.75);
		SmallCar sc = new SmallCar();
		Motorbike mb = new Motorbike();
		FamilySedan fs = new FamilySedan();

		
		sc.setHasPaid(true);
		mb.setHasPaid(true);
		
		pc.enqueue(sc);
		pc.enqueue(mb);
		pc.enqueue(fs);
		
		List<Vehicle> completed = pc.dequeueAllFullyPaid();
		assertTrue(completed.contains(sc) && completed.contains(mb) && !completed.contains(fs));
		assertTrue(completed.size() == 2);
		
		Pump[] pumps = pc.getPumps();
		assertTrue(pumps[0].getSpaceUnused() == 3);
		assertTrue(pumps[1].getSpaceUnused() == 3);
		assertEquals(fs, pumps[2].getQueue().peek());
		assertTrue(pumps[3].getSpaceUnused() == 3);
	}
	
	@Test
	public void testTick() {
		
	}
}
