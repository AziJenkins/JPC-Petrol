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
	private int fuelGallons;

	/**
	 * The amount of money spent in the shop
	 */
	private double shopMoney;
	
	/**
	 * Constructor for payment
	 * @param fuelGallons
	 * @param shopMoney
	 */
	public Payment(int fuelGallons, double shopMoney) {
		this.fuelGallons = fuelGallons;
		this.shopMoney = shopMoney;
	}	
	
	public int getFuelGallons() {
		return fuelGallons;
	}

	public double getShopMoney() {
		return shopMoney;
	}
}
