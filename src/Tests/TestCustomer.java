
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
	public void testPay() throws CustomerAlreadyPaidException {
		Customer readyToPay = new Customer(UUID.randomUUID(), 0, 5.25, 7, false, 0);
		Customer notReadyToPay = new Customer(UUID.randomUUID(), 0, 2, 5, false, 3);
		
		Payment p = readyToPay.pay();
		assertEquals(5.25, p.getShopMoney(), 0.01); //method deprecated 0.01 seems sensible considering no calculations should have happened
		assertEquals(7, p.getFuelGallons(), 0.01);
		
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
		
		p = notReadyToPay.pay();
		assertEquals(2, p.getShopMoney(), 0.01);
		assertEquals(5, p.getFuelGallons(), 0.01);
	}
}
