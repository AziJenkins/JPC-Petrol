import controller.Simulator;

/**
 * @author AZJENKIN
 * The Program
 */
public class Program {

	/**
	 * @param args Main method for running the program 
	 * Creates an instance of the simulator class
	 * and then calls the show all method within the simulator 
	 * to begin the text based interface of the program.
	 */
	public static void main(String[] args) {
		Simulator s = new Simulator();
		s.view.showAll(s.getStation());
	}

}
