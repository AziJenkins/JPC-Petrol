import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
		
	}

	@Test
	public void testRemove() {
		Shop s = new Shop();
		
	}

	@Test
	public void testReduceAllTimers() {

	}

	@Test
	public void testTick() {

	}
}
