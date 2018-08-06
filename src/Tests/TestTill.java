import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.Test;

import exceptions.CustomerAlreadyPaidException;
import exceptions.TillFullException;
import model.Customer;
import model.Payment;
import model.Till;

public class TestTill {

	@Test
	public void testEnqueue() {
		Till t = new Till(3);
		Customer c1 = new Customer(UUID.randomUUID(), 0, 10, 5, false, 0);
		Customer c2 = new Customer(UUID.randomUUID(), 0, 10, 5, false, 0);
		Customer c3 = new Customer(UUID.randomUUID(), 0, 10, 5, false, 0);
		Customer c4 = new Customer(UUID.randomUUID(), 0, 10, 5, false, 0);
		try {
			c1.pay();
			c2.pay();
			c3.pay();
			c4.pay();
		} catch (CustomerAlreadyPaidException e1) {
			assertTrue(false);
		}
		
		try {
			t.enqueue(c1);
			t.enqueue(c2);
			t.enqueue(c3);
		} catch (TillFullException e) {
			assertTrue(false);
		}
		boolean flag = false;
		try {
			t.enqueue(c4);
		} catch (TillFullException e) {
			flag = true;
		}
		assertTrue(flag);
		try {
			t.dequeueWhenDone();
			t.enqueue(c4);
		} catch (Exception e) {
			assertTrue(false);
		}
		
		
		flag = false;
		try {
			t.enqueue(c4);
		} catch (TillFullException e) {
			flag = true;
		}
		assertTrue(flag);
	}

	@Test
	public void testCollectPayment() throws TillFullException {
		Till t = new Till(10);

		try {
			assertNull(t.collectPayment());
		} catch (CustomerAlreadyPaidException e) {
			assertTrue(false);
		}

		Customer c = new Customer(UUID.randomUUID(), 0, 10, 5, false, 2);
		Customer c1 = new Customer(UUID.randomUUID(), 0, 100, 100, false, 0);
		t.enqueue(c);
		t.enqueue(c1);

		Payment p = new Payment(0, 0);
		try {
			assertNull(t.collectPayment());
			assertNull(t.collectPayment());
			p = t.collectPayment();
		} catch (CustomerAlreadyPaidException e) {
			assertTrue(false);
		}
		assertEquals(10, p.getShopMoney());
		assertEquals(5, p.getFuelGallons());

		boolean flag = false;
		try {
			t.collectPayment();
		} catch (CustomerAlreadyPaidException e) {
			flag = true;
		} 
		assertTrue(flag);

	}

	@Test
	public void testDequeueWhenDone() throws TillFullException {
		Till t = new Till(10);
		Customer c1 = new Customer(UUID.randomUUID(), 0, 10, 5, false, 0);
		Customer c2 = new Customer(UUID.randomUUID(), 0, 10, 5, false, 1);
		Customer c3 = new Customer(UUID.randomUUID(), 0, 10, 5, false, 0);
		Customer c4 = new Customer(UUID.randomUUID(), 0, 10, 5, false, 0);
		try {
			c4.pay();
		} catch (CustomerAlreadyPaidException e) {
			assertTrue(false);
		}

		t.enqueue(c1);
		t.enqueue(c2);
		t.enqueue(c3);
		t.enqueue(c4);

		try {
			assertNull(t.dequeueWhenDone());
			t.collectPayment();
			assertEquals(c1, t.dequeueWhenDone());
			assertNull(t.dequeueWhenDone());
			t.collectPayment();
			assertNull(t.dequeueWhenDone());
			t.collectPayment();
			assertEquals(c2, t.dequeueWhenDone());
			assertNull(t.dequeueWhenDone());
			t.collectPayment();
			assertEquals(c3, t.dequeueWhenDone());
			assertEquals(c4, t.dequeueWhenDone());
		} catch (Exception e) {
			assertTrue(false);
		}
	}
}
