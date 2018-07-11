
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import exceptions.CustomerAlreadyPaidException;
import model.Customer;
import model.Payment;

public class TestCustomer {

	@Test
	public void testConstructor() {
		
	}
	
	@Test
	public void testPay() throws CustomerAlreadyPaidException {
		Customer readyToPay = new Customer(UUID.randomUUID(), 0, 5.25, 7, false, 0);
		Customer notReadyToPay = new Customer(UUID.randomUUID(), 0, 2, 5, false, 3);
		
		assertEquals(new Payment(7, 5.25), readyToPay.pay());
		boolean flag = false;
		try {
			readyToPay.pay();
		} catch (CustomerAlreadyPaidException e) {
			flag = true;
		}
		assertTrue(flag);
		
		assertNull(notReadyToPay.pay());
		assertNull(notReadyToPay.pay());
		assertNull(notReadyToPay.pay());
		assertEquals(new Payment(5, 2), notReadyToPay.pay());
	}
}
