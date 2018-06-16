package view;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import model.*;

/**
 * @author AZJENKIN
 *
 */
public class TextBasedInterface {

	/**
	 * 
	 */
	private Scanner scan;

	/**
	 * 
	 */
	public TextBasedInterface() {
		scan = new Scanner(System.in);
	}

	/**
	 * 
	 */
	public void start() {
		System.out.println("Welcome to the Petrol Station.");
	}

	/**
	 * 
	 */
	public void end() {
		System.out.println("Thank you for playing!!!");
		scan.close();
	}

	/**
	 * @param p
	 */
	public void showAll(PetrolStation p) {
		System.out.println("Petrol Station:");
		showIncome(p);
		showTime(p);
		showShop(p.getShop());
		showAllTills(p.getTills());
		showAllPumps(p.getPumps());
	}

	/**
	 * @param p
	 */
	public void showAllPumps(PumpController p) {
		System.out.println("Pumps");
		Pump[] pumps = p.getPumps();
		for (int i = 0; i < pumps.length; i++) {
			System.out.println("Pump " + i + 1);
			showPump(pumps[i]);
		}
	}

	/**
	 * @param p
	 */
	public void showPump(Pump p) {
		System.out.println(" contains:");
		Iterator<?> i = p.getQueue().iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
		}
	}

	/**
	 * @param t
	 */
	public void showAllTills(TillController t) {
		System.out.println("Tills");
		Till[] tills = t.getTills();
		for (int i = 0; i < tills.length; i++) {
			System.out.println("Till " + i + 1);
			showTill(tills[i]);
		}
	}

	/**
	 * @param t
	 */
	public void showTill(Till t) {
		System.out.println(" contains:");
		Iterator<?> i = t.getQueue().iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
		}
	}

	/**
	 * @param p
	 */
	public void showShop(Shop p) {
		System.out.println("The shop contains the following Customers:");
		HashSet<Customer> s = p.getContents();
		Iterator<Customer> i = s.iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
		}
	}

	/**
	 * @param p
	 */
	public void showTime(PetrolStation p) {
		int time = p.getTicks() * 10;
		System.out.println(time + " seconds have passed");
	}

	/**
	 * @param p
	 */
	public void showIncome(PetrolStation p) {
		double fuelIncome = p.getFuelIncome();
		double shopIncome = p.getShopIncome();
		System.out.println("Fuel income: " + fuelIncome);
		System.out.println("Shop income: " + shopIncome);
	}

	/**
	 * @return
	 */
	public double getP() {
		System.out.println("Please choose the chance of small cars and motorbikes (0.01, 0.02, 0.03, 0.04, 0.05)");
		while (true) {
			String input = scan.nextLine();
			switch(input) {
			case "0.01" :
				return 0.01;
			case "0.02" :
				return 0.02;
			case "0.03" :
				return 0.03;
			case "0.04" :
				return 0.04;
			case "0.05" :
				return 0.05;
			default :
				System.out.println("Please choose one of these options: 0.01 0.02 0.03 0.04 0.05");
			}
		}
	}

	/**
	 * @return
	 */
	public double getQ() {
		System.out.println("Please choose the chance of family sedans (0.01, 0.02, 0.03, 0.04, 0.05)");
		while (true) {
			String input = scan.nextLine();
			switch(input) {
			case "0.01" :
				return 0.01;
			case "0.02" :
				return 0.02;
			case "0.03" :
				return 0.03;
			case "0.04" :
				return 0.04;
			case "0.05" :
				return 0.05;
			default :
				System.out.println("Please choose one of these options: 0.01 0.02 0.03 0.04 0.05");
			}
		}
	}

	/**
	 * @return
	 */
	public boolean getTrucksAllowed() {
		System.out.println("Are trucks allowed in the station? y/n");
		while (true) {
			String input = scan.nextLine();
			switch (input.toLowerCase()) {
			case "y":
				return true;
			case "n":
				return false;
			default:
				System.out.println("Please enter Y or N");
			}
		}
	}

	/**
	 * @return
	 */
	public int getNumPumps() {
		System.out.println("Please choose the number of pumps (1, 2, 4)");
		while (true) {
			String input = scan.nextLine();
			switch(input) {
			case "1" :
				return 1;
			case "2" :
				return 2;
			case "4" :
				return 4;
			default :
				System.out.println("Please choose one of these options 1 2 4");
			}
		}
	}

	/**
	 * @return
	 */
	public int getNumTills() {
		System.out.println("Please choose the number of tills (1, 2, 4)");
		while (true) {
			String input = scan.nextLine();
			switch(input) {
			case "1" :
				return 1;
			case "2" :
				return 2;
			case "4" :
				return 4;
			default :
				System.out.println("Please choose one of these options 1 2 4");
			}
		}
	}
}
