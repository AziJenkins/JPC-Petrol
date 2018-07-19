import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import exceptions.CustomerAlreadyPresentException;
import exceptions.CustomerCarMismatchException;
import exceptions.CustomerHasNotPaidException;
import exceptions.MinGreaterThanMaxException;
import exceptions.VehicleAlreadyPaidException;
import exceptions.VehicleIsNotOccupiedException;
import exceptions.VehicleNotFullException;
import model.Customer;
import model.FamilySedan;
import model.Motorbike;
import model.SmallCar;
import model.Truck;

public class TestVehicle {
	
	@Test
	public void testTryFill() throws MinGreaterThanMaxException {
		Motorbike mb = new Motorbike();
		SmallCar sc = new SmallCar();
		FamilySedan fs = new FamilySedan();
		Truck tr = new Truck();
		
		for (int i = 0; i < mb.getFuelCapacity(); i++) {
			assertTrue(mb.tryFill(1));
		}
		assertFalse(mb.tryFill(1));
		
		for (int i = 0; i < sc.getFuelCapacity(); i++) {
			assertTrue(sc.tryFill(1));
		}
		assertFalse(sc.tryFill(1));
		
		for (int i = 0; i < fs.getFuelCapacity(); i++) {
			assertTrue(fs.tryFill(1));
		}
		assertFalse(fs.tryFill(1));
		
		for (int i = 0; i < tr.getFuelCapacity(); i++) {
			assertTrue(tr.tryFill(1));
		}
		assertFalse(tr.tryFill(1));
	}
	
	@Test
	public void testLeaveVehicle() throws MinGreaterThanMaxException, VehicleIsNotOccupiedException, VehicleNotFullException, VehicleAlreadyPaidException { 
		Motorbike mb = new Motorbike();
		SmallCar sc = new SmallCar();
		FamilySedan fs = new FamilySedan();
		Truck tr = new Truck();
		
		mb.tryFill(100);
		sc.tryFill(100);

		
		Customer mbc = mb.leaveVehicle();
		Customer scc = sc.leaveVehicle();
		Customer fsc;
		Customer trc;
		int i = 0;
		try {
			fsc = fs.leaveVehicle();
		} catch (VehicleNotFullException e1) {
			i++;
		}
		try {
			trc = tr.leaveVehicle();
		} catch (VehicleNotFullException e1) {
			i++;
		}
		assertEquals(2, i);
		
		fs.tryFill(100);
		tr.tryFill(100);
		tr.setHasPaid(true);
		
		fsc = fs.leaveVehicle();
		i = 0;
		try {
			trc = tr.leaveVehicle();
		} catch (VehicleAlreadyPaidException e1) {
			i++;
		}
		assertEquals(1, i);
		
		tr.setHasPaid(false);
		trc = tr.leaveVehicle();
		
		assertEquals(mb.getRegistration(), mbc.getRegistration());
		assertEquals(sc.getRegistration(), scc.getRegistration());
		assertEquals(fs.getRegistration(), fsc.getRegistration());
		assertEquals(tr.getRegistration(), trc.getRegistration());
		
		assertTrue(!mbc.getHasPaid() && !scc.getHasPaid() && !fsc.getHasPaid() && !trc.getHasPaid());
		
		i = 0;
		try {
			mb.leaveVehicle();
		} catch (VehicleIsNotOccupiedException e) {
			i++;
		}
		try {
			sc.leaveVehicle();
		} catch (VehicleIsNotOccupiedException e) {
			i++;
		}
		try {
			fs.leaveVehicle();
		} catch (VehicleIsNotOccupiedException e) {
			i++;
		}
		try {
			tr.leaveVehicle();
		} catch (VehicleIsNotOccupiedException e) {
			i++;
		}
		assertEquals(4, i);
	}
	
	@Test
	public void testReenterCar() throws MinGreaterThanMaxException, VehicleIsNotOccupiedException, VehicleNotFullException, VehicleAlreadyPaidException, CustomerCarMismatchException, CustomerHasNotPaidException, CustomerAlreadyPresentException {
		Motorbike mb = new Motorbike();
		SmallCar sc = new SmallCar();
		FamilySedan fs = new FamilySedan();
		Truck tr = new Truck();
		
		mb.tryFill(100);
		sc.tryFill(100);
		fs.tryFill(100);
		tr.tryFill(100);
		
		Customer mbc = mb.leaveVehicle();
		Customer scc = sc.leaveVehicle();
		Customer fsc = fs.leaveVehicle();
		Customer trc = tr.leaveVehicle();
		
		fsc.setHasPaid(true);
		trc.setHasPaid(true);
		
		int i = 0;
		try {
			mb.reEnterCar(mbc);
		} catch (CustomerHasNotPaidException e1) {
			i++;
		}
		try {
			sc.reEnterCar(scc);
		} catch (CustomerHasNotPaidException e) {
			i++;
		}
		assertEquals(2, i);
		
		mbc.setHasPaid(true);
		scc.setHasPaid(true);
		
		i = 0;
		try {
			mb.reEnterCar(scc);
		} catch (CustomerCarMismatchException e) {
			i++;
		}
		try {
			mb.reEnterCar(fsc);
		} catch (CustomerCarMismatchException e) {
			i++;
		}
		try {
			mb.reEnterCar(trc);
		} catch (CustomerCarMismatchException e) {
			i++;
		}
		assertEquals(3, i);
		
		try {
			sc.reEnterCar(scc);
			mb.reEnterCar(mbc);
			fs.reEnterCar(fsc);
			tr.reEnterCar(trc);
		} catch (CustomerCarMismatchException e) {
			assertTrue(false);
		}
		
		i = 0;
		try {
			sc.reEnterCar(scc);
		} catch (CustomerAlreadyPresentException e) {
			i++;
		}
		try {
			mb.reEnterCar(mbc);
		} catch (CustomerAlreadyPresentException e) {
			i++;
		}
		try {
			fs.reEnterCar(fsc);
		} catch (CustomerAlreadyPresentException e) {
			i++;
		}
		try {
			tr.reEnterCar(trc);
		} catch (CustomerAlreadyPresentException e) {
			i++;
		}
		
	}
	
}
