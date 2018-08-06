import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import exceptions.CustomerAlreadyPresentException;
import exceptions.CustomerCarMismatchException;
import exceptions.CustomerHasNotPaidException;
import exceptions.EmptyQueueException;
import exceptions.MinGreaterThanMaxException;
import exceptions.VehicleAlreadyPaidException;
import exceptions.VehicleIsNotOccupiedException;
import exceptions.VehicleNotFullException;
import model.Customer;
import model.FamilySedan;
import model.Motorbike;
import model.Pump;
import model.SmallCar;
import model.Truck;
import model.Vehicle;
import utils.CircularArrayQueue;

public class TestPump {

	@Test
	public void testTick() {

	}

	@Test
	public void testFill() throws MinGreaterThanMaxException, EmptyQueueException {
		Pump p = new Pump(0.75);
		Vehicle v1 = new SmallCar();
		Vehicle v2 = new Motorbike();

		p.enqueue(v1);
		p.enqueue(v2);

		for (int i = 0; i < v1.getFuelCapacity(); i++) {
			assertTrue(p.fill());
		}
		assertFalse(p.fill());

		CircularArrayQueue<Vehicle> q = p.getQueue();
		q.remove();

		for (int i = 0; i < v2.getFuelCapacity(); i++) {
			assertTrue(p.fill());
		}
		assertFalse(p.fill());
	}

	@Test
	public void testDequeueWhenDone() throws MinGreaterThanMaxException, VehicleIsNotOccupiedException, VehicleAlreadyPaidException, VehicleNotFullException, CustomerCarMismatchException, CustomerAlreadyPresentException, CustomerHasNotPaidException {
		Pump p = new Pump(0.75);
		Vehicle v = new SmallCar();
		Vehicle out;
		p.enqueue(v);
		assertTrue(p.getQueueSize() == 1);
		
		out = p.dequeueWhenFullyPaid();
		assertNull(out);
		assertTrue(p.getQueue().contains(v)); //hasnt paid
		assertTrue(p.getQueueSize() == 1);
		
		v.tryFill(100);
		Customer c = v.leaveVehicle();
		
		out = p.dequeueWhenFullyPaid();
		assertNull(out);
		assertTrue(p.getQueue().contains(v)); //both
		assertTrue(p.getQueueSize() == 1);
		
		v.setHasPaid(true);
		out = p.dequeueWhenFullyPaid();
		assertNull(out);
		assertTrue(p.getQueue().contains(v)); //isnt occupied
		assertTrue(p.getQueueSize() == 1);
		
		c.setHasPaid(true);
		v.reEnterCar(c);
		out = p.dequeueWhenFullyPaid();
		assertEquals(out, v);
		assertFalse(p.getQueue().contains(v));
		assertTrue(p.getQueueSize() == 0);
	}

	@Test
	public void testEnqueue() throws MinGreaterThanMaxException {
		Pump p = new Pump(0.75);
		SmallCar sc = new SmallCar();
		Motorbike mb = new Motorbike();
		FamilySedan fs = new FamilySedan();
		Truck tr = new Truck();
		
		assertTrue(p.enqueue(sc));
		assertTrue(p.getQueue().contains(sc));
		assertTrue(p.getQueueSize() == 1);
		assertTrue(p.enqueue(sc));
		assertTrue(p.getQueueSize() == 2);
		assertTrue(p.enqueue(sc));
		assertTrue(p.getQueueSize() == 3);
		assertFalse(p.enqueue(sc));
		
		p = new Pump(0.75);
		
		assertTrue(p.enqueue(tr));
		assertTrue(p.getQueueSize() == 2);
		assertTrue(p.getQueue().contains(tr));
		assertTrue(p.enqueue(mb));
		assertTrue(p.getQueueSize() == 2.75);
		assertTrue(p.getQueue().contains(mb) && p.getQueue().contains(tr));
		assertFalse(p.enqueue(sc));
		assertFalse(p.getQueue().contains(sc));
		assertTrue(p.getQueueSize() == 2.75);
		
		p = new Pump(0.75);
		
		assertTrue(p.enqueue(fs));
		assertTrue(p.getQueueSize() == 1.5);
		assertTrue(p.getQueue().contains(fs));
		assertFalse(p.enqueue(tr));
		assertFalse(p.getQueue().contains(tr));
		assertTrue(p.getQueueSize() == 1.5);
		assertTrue(p.enqueue(mb));
		assertTrue(p.getQueueSize() == 2.25);
		assertTrue(p.getQueue().contains(fs) && p.getQueue().contains(mb));
		assertFalse(p.enqueue(sc));
		assertTrue(p.getQueueSize() == 2.25);
		assertTrue(p.getQueue().contains(fs) && p.getQueue().contains(mb) && !p.getQueue().contains(tr) && !p.getQueue().contains(sc));
		
		
	}
}
