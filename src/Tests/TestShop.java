import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import exceptions.CustomerAlreadyPresentException;
import model.Customer;
import model.Shop;

public class TestShop {

	@Test
	public void testAdd() throws CustomerAlreadyPresentException {
		Shop s = new Shop();
		Customer c1 = new Customer(UUID.randomUUID(), 0, 0, 0, true, 0);
		assertTrue(s.add(c1));
		boolean flag = false;
		try {
			s.add(c1);
		} catch (CustomerAlreadyPresentException e) {
			flag = true;
		}
		assertTrue(flag);

		Customer c2 = new Customer(UUID.randomUUID(), 0, 0, 0, false, 0);
		assertFalse(s.add(c2));
		
		Customer c3 = new Customer(UUID.randomUUID(), 0, 0, 0, true, 0);
		assertTrue(s.add(c3));
	}

	@Test
	public void testRemove() throws CustomerAlreadyPresentException {
		Shop s = new Shop();
		
		Customer c1 = new Customer(UUID.randomUUID(), 0, 0, 0, true, 0);
		Customer c2 = new Customer(UUID.randomUUID(), 0, 0, 0, true, 0);
		Customer c3 = new Customer(UUID.randomUUID(), 0, 0, 0, true, 0);
		Customer c4 = new Customer(UUID.randomUUID(), 0, 0, 0, true, 0);
		List<Customer> customers = new ArrayList<>();
		customers.add(c1);
		customers.add(c2);
		customers.add(c3);
		
		s.add(c1);
		s.add(c2);
		s.add(c3);
		
		assertEquals(customers, s.remove(customers));
		assertTrue(s.remove(customers).isEmpty());
		assertTrue(s.getContents().isEmpty());
		
		s.add(c1);
		s.add(c2);
		s.add(c4);
		
		List<Customer> retrnd = s.remove(customers);
		customers.remove(c3);
		assertEquals(customers, retrnd);
		assertTrue(s.getContents().contains(c4) && s.getContents().size() == 1);
	}

	@Test
	public void testReduceAllTimers() throws CustomerAlreadyPresentException {
		Shop s = new Shop();
		assertEquals(new ArrayList<Customer>(), s.reduceAllTimers());
		
		Customer first = new Customer(UUID.randomUUID(), 0, 0, 0, true, 0);
		Customer second1 = new Customer(UUID.randomUUID(), 1, 0, 0, true, 0);
		Customer second2 = new Customer(UUID.randomUUID(), 1, 0, 0, true, 0);
		Customer fourth = new Customer(UUID.randomUUID(), 3, 0, 0, true, 0);
		
		s.add(first);
		s.add(second2);
		s.add(second1);
		s.add(fourth);
		
		List<Customer> atZero = new ArrayList<Customer>();
		atZero.add(first);
		
		assertTrue(atZero.containsAll(s.reduceAllTimers()));
		
		atZero.add(second1);
		atZero.add(second2);
		
		assertTrue(atZero.containsAll(s.reduceAllTimers()));
		
		assertTrue(atZero.containsAll(s.reduceAllTimers()));
		
		atZero.add(fourth);
		
		assertTrue(atZero.containsAll(s.reduceAllTimers()));
	}

	@Test
	public void testTick() {

	}
}
