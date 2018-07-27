import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import exceptions.CustomerAlreadyPaidException;
import exceptions.CustomerAlreadyPresentException;
import exceptions.CustomerCarMismatchException;
import exceptions.CustomerCouldNotFindVehicleException;
import exceptions.CustomerHasNotPaidException;
import exceptions.EmptyQueueException;
import exceptions.MinGreaterThanMaxException;
import exceptions.TillFullException;
import exceptions.VehicleAlreadyPaidException;
import exceptions.VehicleIsNotOccupiedException;
import exceptions.VehicleNotFullException;
import model.Customer;
import model.FamilySedan;
import model.Motorbike;
import model.Payment;
import model.PetrolStation;
import model.Pump;
import model.SmallCar;
import model.Vehicle;

public class TestPetrolStation {

	PetrolStation ps;



	@Test
	public void testCollectPayments() throws CustomerAlreadyPresentException, CustomerAlreadyPaidException, CustomerCarMismatchException, CustomerHasNotPaidException, CustomerCouldNotFindVehicleException, TillFullException, EmptyQueueException {
		setup();
		Customer c1 = new Customer(UUID.randomUUID(), 0, 1, 1, false, 2);
		Customer c2 = new Customer(UUID.randomUUID(), 0, 2, 2, false, 1);
		Customer c3 = new Customer(UUID.randomUUID(), 0, 3, 3, false, 1);

		ps.recieveCustomer(c1);
		ps.recieveCustomer(c2);
		ps.recieveCustomer(c3);

		assertEquals(0, ps.getGallonsSold());
		assertEquals(0, ps.getShopIncome());
		ps.collectPayments();
		assertEquals(0, ps.getGallonsSold());
		assertEquals(0, ps.getShopIncome());
		ps.collectPayments();
		assertEquals(5, ps.getGallonsSold());
		assertEquals(5, ps.getShopIncome());
		ps.getTillController().getTills()[1].getQueue().remove();
		ps.getTillController().getTills()[2].getQueue().remove();
		ps.collectPayments();
		assertEquals(6, ps.getGallonsSold());
		assertEquals(6, ps.getShopIncome());
	}

	@Test
	public void testRecieveCustomer()
			throws CustomerAlreadyPaidException, VehicleIsNotOccupiedException, VehicleAlreadyPaidException,
			VehicleNotFullException, MinGreaterThanMaxException, CustomerAlreadyPresentException, CustomerCarMismatchException, CustomerHasNotPaidException, CustomerCouldNotFindVehicleException, TillFullException {
		setup();
		Customer toShop = new Customer(UUID.randomUUID(), 5, 5, 3, true, 2);
		Customer toTills = new Customer(UUID.randomUUID(), 0, 0, 4, false, 5);
		Vehicle mb = new Motorbike();
		mb.tryFill(mb.getFuelCapacity());
		Customer toVehicle = mb.leaveVehicle();
		Payment p = null;
		while (p == null) {
			p = toVehicle.pay();
		}
		ps.recieveVehicle(mb);

		assertFalse(ps.getShop().getContents().contains(toShop));
		assertFalse(ps.getTillController().getTills()[0].getQueue().contains(toTills));
		assertFalse(ps.getPumpController().getPumps()[0].getQueue().iterator().next().getIsOccupied());

		ps.recieveCustomer(toShop);
		ps.recieveCustomer(toTills);
		ps.recieveCustomer(toVehicle);

		assertTrue(ps.getShop().getContents().contains(toShop));
		assertTrue(ps.getTillController().getTills()[0].getQueue().contains(toTills));
		assertTrue(ps.getPumpController().getPumps()[0].getQueue().iterator().next().getIsOccupied());

		int i = 0;
		try {
			ps.recieveCustomer(toShop);
		} catch (CustomerAlreadyPresentException e) {
			i++;
		}
		try {
			ps.recieveCustomer(toTills);
		} catch (CustomerAlreadyPresentException e) {
			i++;
		}
		try {
			ps.recieveCustomer(toVehicle);
		} catch (CustomerAlreadyPresentException e) {
			i++;
		}
		assertEquals(3, i);
	}

	@Test
	public void testDispatchComplete() throws MinGreaterThanMaxException, VehicleIsNotOccupiedException,
			VehicleAlreadyPaidException, VehicleNotFullException, CustomerAlreadyPaidException,
			CustomerCarMismatchException, CustomerAlreadyPresentException, CustomerHasNotPaidException {
		setup();
		Vehicle readyToLeave = new SmallCar();
		readyToLeave.tryFill(readyToLeave.getFuelCapacity());
		Customer scc = readyToLeave.leaveVehicle();
		Payment pay = null;
		while (pay == null) {
			pay = scc.pay();
		}
		readyToLeave.reEnterCar(scc);
		Vehicle fullyFueled = new Motorbike();
		fullyFueled.tryFill(fullyFueled.getFuelCapacity());
		Vehicle customerInShop = new FamilySedan();
		customerInShop.tryFill(customerInShop.getFuelCapacity());
		customerInShop.leaveVehicle();

		ps.recieveVehicle(readyToLeave);
		ps.recieveVehicle(fullyFueled);
		ps.recieveVehicle(customerInShop);

		List<Vehicle> dispatchedVehicles = ps.dispatchComplete();
		boolean fullyFueledFlag = false;
		boolean customerInShopFlag = false;
		for (Pump p : ps.getPumpController().getPumps()) {
			if (p.getQueue().contains(readyToLeave)) {
				assertTrue(false);
			}
			if (p.getQueue().contains(fullyFueled)) {
				fullyFueledFlag = true;
			}
			if (p.getQueue().contains(customerInShop)) {
				customerInShopFlag = true;
			}
		}
		assertTrue(fullyFueledFlag);
		assertTrue(customerInShopFlag);
		assertTrue(dispatchedVehicles.contains(readyToLeave));
	}

	@Test
	public void testTick() throws MinGreaterThanMaxException, CustomerAlreadyPaidException, VehicleIsNotOccupiedException, VehicleAlreadyPaidException, VehicleNotFullException, CustomerCarMismatchException, CustomerAlreadyPresentException, CustomerHasNotPaidException, TillFullException, CustomerCouldNotFindVehicleException {
		setup();	//must replace random number generation with constant
		Vehicle v = new SmallCar();
		ps.tick(v);	//v enters queue for pumps
		
		for (int i = 0; i < v.getFuelCapacity(); i++) { // v is being fuelled
			ps.tick(null); //v fuels
			assertTrue(v.getIsOccupied());
		}
		ps.tick(null); //v is full, customer leaves, enters shop or tills
		assertFalse(v.getIsOccupied());
		
		if (ps.getShop().getContents().iterator().hasNext()) {	// make sure to re run test to be sure it went into this block or remove random element from Vehicle.leaveVehicle()
			Customer c = ps.getShop().getContents().iterator().next(); //customer is in shop
			assertEquals(v.getRegistration(), c.getRegistration());
			int ticks = c.getShopTicks();
			for (int i = 0; i < ticks; i++) {
				ps.tick(null); //c's shop timer is reduced
				assertTrue(ps.getShop().getContents().contains(c));
			}
			ps.tick(null); //c leaves shop to tills
		}
		
		Customer c = ps.getTillController().getTills()[0].getQueue().peek();
		double spend = c.getShopSpend();
		int ticks = c.getPayTicks();
		for (int i = 0; i < ticks; i++) {
			ps.tick(null); //c's pay timer is reduced
			assertEquals(0, ps.getGallonsSold());
			assertEquals(0, ps.getShopIncome());
		}
		ps.tick(null); //c pays
		assertEquals(v.getFuelCapacity(), ps.getGallonsSold());
		assertEquals(spend, ps.getShopIncome());
		ps.tick(null); //c leaves tills, goes to v
		assertTrue(v.getIsOccupied());
		assertTrue(ps.getPumpController().getPumps()[0].getQueue().contains(v));
		ps.tick(null); //v leaves
		assertFalse(ps.getPumpController().getPumps()[0].getQueue().contains(v));
	}

	public void setup() {
		ps = new PetrolStation(4, 3, 0.75, 3);
	}
}
