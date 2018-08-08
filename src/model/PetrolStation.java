package model;

import java.util.List;

import exceptions.CustomerAlreadyPaidException;
import exceptions.CustomerAlreadyPresentException;
import exceptions.CustomerCarMismatchException;
import exceptions.CustomerCouldNotFindVehicleException;
import exceptions.CustomerHasNotPaidException;
import exceptions.TillFullException;
import exceptions.VehicleAlreadyPaidException;
import exceptions.VehicleIsNotOccupiedException;
import exceptions.VehicleNotFullException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author AZJENKIN A model Petrol Station
 */
public class PetrolStation {

	/**
	 * A controller for all of the pumps at the Petrol Station
	 */
	private PumpController pumps;
	/**
	 * A controller for all of the tills at the Petrol Station
	 */
	private TillController tills;
	/**
	 * The Shop in the Petrol Station
	 */
	private Shop shop = new Shop();

	/**
	 * The number of ticks that have passed at this Petrol Station
	 */
	private SimpleIntegerProperty ticksPassed;
	/**
	 * The total income from fuel sales
	 */
	private SimpleDoubleProperty gallonsSold;
	/**
	 * The total income from shop sales
	 */
	private SimpleDoubleProperty shopIncome;

	/**
	 * Constructor for a petrol station
	 * 
	 * @param numPumps = the number of Pumps at the Petrol Station
	 * @param numTills = the number of Tills at the Petrol Station
	 */
	public PetrolStation(int numPumps, int numTills, double smallestVehicle, int maxQueueSize) {
		this.pumps = new PumpController(numPumps, smallestVehicle);
		this.tills = new TillController(numTills, maxQueueSize);   
		ticksPassed = new SimpleIntegerProperty(0);
		gallonsSold = new SimpleDoubleProperty(0);
		shopIncome = new SimpleDoubleProperty(0);
	}

	/**
	 * Getter for fuel income
	 * 
	 * @return
	 */
	public SimpleDoubleProperty getGallonsSold() {
		return gallonsSold;
	}

	/**
	 * Getter for shop income
	 * 
	 * @return
	 */
	public SimpleDoubleProperty getShopIncome() {
		return shopIncome;
	}

	/**
	 * Collects payments from any Customer that is ready to pay at any of the Tills
	 * in the Petrol Station
	 * 
	 * @throws CustomerAlreadyPaidException
	 */
	public void collectPayments() throws CustomerAlreadyPaidException {
		List<Payment> payments = tills.collectPayments();
		for (Payment p : payments) {
			gallonsSold.add(p.getFuelGallons());
			shopIncome.add(p.getShopMoney());
		}
	}

	/**
	 * Getter for the Shop
	 * 
	 * @return
	 */
	public Shop getShop() {
		return this.shop;
	}

	/**
	 * Getter for the Pump Controller
	 * 
	 * @return
	 */
	public PumpController getPumpController() {
		return this.pumps;
	}

	/**
	 * Getter for the Till Controller
	 * 
	 * @return
	 */
	public TillController getTillController() {
		return this.tills;
	}

	/**
	 * Takes a Vehicle and passes it to the Pump Controller
	 * 
	 * @param v
	 * @return
	 */
	public Boolean recieveVehicle(Vehicle v) {
		return pumps.enqueue(v);
	}

	/**
	 * Takes a Customer and passes it to either the Shop or the Till Controller
	 * according to what the Customer wants
	 * 
	 * @param c
	 * @throws CustomerCouldNotFindVehicleException
	 * @throws CustomerHasNotPaidException
	 * @throws CustomerCarMismatchException
	 * @throws TillFullException
	 */
	public void recieveCustomer(Customer c) throws CustomerAlreadyPresentException, CustomerCarMismatchException,
			CustomerHasNotPaidException, CustomerCouldNotFindVehicleException, TillFullException {
		if (c.getHasPaid()) {
			pumps.recieveCustomer(c);
		} else if (c.getWillShop()) {
			shop.add(c);
		} else {
			tills.enqueue(c);
		}
	}
	
	public void recieveCustomers(List<Customer> customers) throws CustomerAlreadyPresentException, CustomerCarMismatchException, CustomerHasNotPaidException, CustomerCouldNotFindVehicleException, TillFullException {
		for (Customer c: customers) {
			recieveCustomer(c);
		}
	}

	/**
	 * Asks the Pump Controller to dequeue any Vehicles that are ready to leave
	 * 
	 * @return
	 */
	public List<Vehicle> dispatchComplete() {
		return pumps.dequeueAllFullyPaid();
	}

	/**
	 * Progress time at the Petrol Station by 1 tick This will alert the Pump
	 * Controller, Till Controller and Shop that time has Passed;
	 * 
	 * @throws CustomerAlreadyPaidException
	 * @throws CustomerHasNotPaidException
	 * @throws CustomerAlreadyPresentException
	 * @throws CustomerCarMismatchException
	 * @throws VehicleNotFullException
	 * @throws VehicleAlreadyPaidException
	 * @throws VehicleIsNotOccupiedException
	 * @throws TillFullException 
	 * @throws CustomerCouldNotFindVehicleException 
	 */
	public void tick(Vehicle v) throws CustomerAlreadyPaidException, VehicleIsNotOccupiedException,
			VehicleAlreadyPaidException, VehicleNotFullException, CustomerCarMismatchException,
			CustomerAlreadyPresentException, CustomerHasNotPaidException, TillFullException, CustomerCouldNotFindVehicleException {
		pumps.dequeueAllFullyPaid();
		List<Customer> finishedPaying = tills.dequeueFullyPaid();
		collectPayments();
		recieveCustomers(shop.tick());
		recieveCustomers(pumps.tick());
		recieveCustomers(finishedPaying);
		if (v != null) {
			recieveVehicle(v);
		}
		ticksPassed.set(ticksPassed.get()+1);
		/*
		 * dequeue all paid dequeue paid customers tick TillQueue tick shop tick pumps
		 * add vehicle
		 */
	}

	/**
	 * Getter for the number of ticks that have passed
	 * 
	 * @return
	 */
	public SimpleIntegerProperty getTicks() {
		return this.ticksPassed;
	}
}