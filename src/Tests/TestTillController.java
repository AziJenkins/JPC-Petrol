
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import exceptions.EmptyQueueException;
import model.Customer;
import model.Payment;
import model.TillController;

public class TestTillController {

	TillController tc;

	@Before
	public void before() {
		tc = new TillController(3, 10);
	}

	@Test
	public void testEnqueue() {
		Customer c = new Customer(UUID.randomUUID(), 0, 0, 5, false, 1);
		tc.enqueue(c);
		assertEquals(tc.getTills()[0].getQueue().peek(), c);

	}

	@Test
	public void testEnqueueOrder() {
		Customer c1 = new Customer(UUID.randomUUID(), 0, 0, 5, false, 1);
		Customer c2 = new Customer(UUID.randomUUID(), 0, 0, 5, false, 1);
		Customer c3 = new Customer(UUID.randomUUID(), 0, 0, 5, false, 1);
		Customer c4 = new Customer(UUID.randomUUID(), 0, 0, 5, false, 1);
		Customer c5 = new Customer(UUID.randomUUID(), 0, 0, 5, false, 1);

		tc.enqueue(c1);
		assertTrue(tc.getTills()[0].getQueue().contains(c1));
		tc.enqueue(c2);
		assertTrue(tc.getTills()[1].getQueue().contains(c2));
		tc.enqueue(c3);
		assertTrue(tc.getTills()[2].getQueue().contains(c3));
		tc.enqueue(c4);
		assertTrue(tc.getTills()[0].getQueue().contains(c4));

		try {
			assertTrue(tc.dequeueFullyPaid().contains(c3));
		} catch (EmptyQueueException e) {
		}
		tc.enqueue(c5);
		assertTrue(tc.getTills()[2].getQueue().contains(c5));
	}

	@Test
	public void testDequeueFullyPaid() {

		Customer c1 = new Customer(UUID.randomUUID(), 0, 0, 5, false, 0);
		Customer c2 = new Customer(UUID.randomUUID(), 0, 0, 5, false, 0);
		Customer c3 = new Customer(UUID.randomUUID(), 0, 0, 5, false, 0);

		c1.pay();
		c3.pay();

		tc.enqueue(c1);
		tc.enqueue(c2);
		tc.enqueue(c3);

		List<Customer> customers;
		try {
			customers = tc.dequeueFullyPaid();
			assertTrue(customers.contains(c1));
			assertTrue(customers.contains(c3));
			assertFalse(customers.contains(c2));
		} catch (EmptyQueueException e) {
		}

		assertTrue(tc.getTills()[0].getQueue().isEmpty());
		assertFalse(tc.getTills()[1].getQueue().isEmpty());
		assertTrue(tc.getTills()[2].getQueue().isEmpty());
	}

	@Test
	public void testCollectPayments() {

		Customer c1 = new Customer(UUID.randomUUID(), 0, 1, 1, false, 0);
		Customer c2 = new Customer(UUID.randomUUID(), 0, 2, 2, false, 0);
		Customer c3 = new Customer(UUID.randomUUID(), 0, 3, 3, false, 0);
		Payment c1pay = new Payment(1, 1);
		Payment c2pay = c2.pay();
		Payment c3pay = new Payment(3, 3);

		tc.enqueue(c1);
		tc.enqueue(c2);
		tc.enqueue(c3);

		List<Payment> payments = tc.collectPayments();
		assertTrue(payments.contains(c1pay));
		assertFalse(payments.contains(c2pay));
		assertTrue(payments.contains(c3pay));

	}
}
