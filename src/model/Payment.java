package model;

/**
 * @author AZJENKIN
 * A tuple containing the number of gallons of a fuel to be bought and
 * the amount spent in the shop
 */
public class Payment {

	/**
	 * The number of gallons of fuel to be bought
	 */
	private double fuelGallons;

	/**
	 * The amount of money spent in the shop
	 */
	private double shopMoney;
	
	/**
	 * Constructor for payment
	 * @param fuelGallons
	 * @param shopMoney
	 */
	public Payment(double fuelGallons, double shopMoney) {
		this.fuelGallons = fuelGallons;
		this.shopMoney = shopMoney;
	}	
	
	/**
	 * Getter for fuelGallons
	 * 
	 * @return
	 */
	public double getFuelGallons() {
		return fuelGallons;
	}

	/**
	 * Getter for shopMoney
	 * 
	 * @return
	 */
	public double getShopMoney() {
		return shopMoney;
	}
}
