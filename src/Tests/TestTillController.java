
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import exceptions.CustomerAlreadyPaidException;
import exceptions.EmptyQueueException;
import exceptions.TillFullException;
import model.Customer;
import model.Payment;
import model.TillController;

public class TestTillController {

	TillController tc;

	@BeforeEach
	public void setUp() {
		tc = new TillController(3, 10);
	}

	@Test
	public void testEnqueue() {
		setUp();
		Customer c = new Customer(UUID.randomUUID(), 0, 0, 5, false, 1);
		try {
			tc.enqueue(c);
		} catch (TillFullException e1) {
			assertTrue(false);
		}
		assertEquals(tc.getTills()[0].getQueue().peek(), c);

		for (int i = 0; i < 29; i++) {
			try {
				tc.enqueue(c);
			} catch (TillFullException e) {
				assertTrue(false);
			}
		}
		boolean flag = false;
		try {
			tc.enqueue(c);
		} catch (TillFullException e) {
			flag = true;
		}
		assertTrue(flag);
	}

	@Test
	public void testEnqueueOrder() throws TillFullException, CustomerAlreadyPaidException {
		setUp();
		Customer c1 = new Customer(UUID.randomUUID(), 0, 0, 5, false, 1);
		Customer c2 = new Customer(UUID.randomUUID(), 0, 0, 5, false, 0);
		Customer c3 = new Customer(UUID.randomUUID(), 0, 0, 5, false, 1);
		Customer c4 = new Customer(UUID.randomUUID(), 0, 0, 5, false, 1);
		Customer c5 = new Customer(UUID.randomUUID(), 0, 0, 5, false, 1);
		c2.pay();

		tc.enqueue(c1);
		assertTrue(tc.getTills()[0].getQueue().contains(c1));
		tc.enqueue(c2);
		assertTrue(tc.getTills()[1].getQueue().contains(c2));
		tc.enqueue(c3);
		assertTrue(tc.getTills()[2].getQueue().contains(c3));
		tc.enqueue(c4);
		assertTrue(tc.getTills()[0].getQueue().contains(c4));


		assertTrue(tc.dequeueFullyPaid().contains(c2));

		tc.enqueue(c5);
		assertTrue(tc.getTills()[1].getQueue().contains(c5));
	}

	@Test
	public void testDequeueFullyPaid() throws CustomerAlreadyPaidException, TillFullException {
		setUp();
		Customer c1 = new Customer(UUID.randomUUID(), 0, 0, 5, false, 0);
		Customer c2 = new Customer(UUID.randomUUID(), 0, 0, 5, false, 0);
		Customer c3 = new Customer(UUID.randomUUID(), 0, 0, 5, false, 0);

		c1.pay();
		c3.pay();

		tc.enqueue(c1);
		tc.enqueue(c2);
		tc.enqueue(c3);

		List<Customer> customers;

		customers = tc.dequeueFullyPaid();
		assertTrue(customers.contains(c1));
		assertTrue(customers.contains(c3));
		assertFalse(customers.contains(c2));

		assertTrue(tc.getTills()[0].getQueue().isEmpty());
		assertFalse(tc.getTills()[1].getQueue().isEmpty());
		assertTrue(tc.getTills()[2].getQueue().isEmpty());
		
		tc.collectPayments();
		customers = tc.dequeueFullyPaid();
		assertTrue(customers.contains(c2));
		assertTrue(tc.getTills()[1].getQueue().isEmpty());
	}

	@Test
	public void testCollectPayments() throws TillFullException {
		setUp();
		Customer c1 = new Customer(UUID.randomUUID(), 0, 1, 1, false, 0);
		Customer c2 = new Customer(UUID.randomUUID(), 0, 2, 2, false, 1);
		Customer c3 = new Customer(UUID.randomUUID(), 0, 3, 3, false, 0);

		tc.enqueue(c1);
		tc.enqueue(c2);
		tc.enqueue(c3);

		List<Payment> payments;
		try {
			payments = tc.collectPayments();
			assertTrue(payments.get(0).getFuelGallons() == 1 && payments.get(0).getShopMoney() == 1);
			assertTrue(payments.get(1).getFuelGallons() == 3 && payments.get(1).getShopMoney() == 3);
			assertTrue(payments.size() == 2);
		} catch (CustomerAlreadyPaidException e) {
			assertTrue(false);
		}
		boolean flag = false;
		try {
			payments = tc.collectPayments();
		} catch (CustomerAlreadyPaidException e) {
			flag = true;
		}
		assertTrue(flag);

		tc.dequeueFullyPaid();
	}
}
