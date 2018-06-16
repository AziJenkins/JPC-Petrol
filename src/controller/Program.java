package controller;

/**
 * @author AZJENKIN
 *
 */
public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Simulator s = new Simulator();
		s.view.showAll(s.getStation());
	}

}
