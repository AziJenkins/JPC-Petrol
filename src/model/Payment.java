package model;

/**
 * @author AZJENKIN
 *
 */
public class Payment {

	/**
	 * 
	 */
	private int fuelGallons;
	/**
	 * 
	 */
	private double shopMoney;
	
	/**
	 * @param fuelGallons
	 * @param shopMoney
	 */
	public Payment(int fuelGallons, double shopMoney) {
		this.fuelGallons = fuelGallons;
		this.shopMoney = shopMoney;
	}
}
