package application;

import controller.Simulator;
import exceptions.CustomerAlreadyPaidException;
import exceptions.CustomerAlreadyPresentException;
import exceptions.CustomerCarMismatchException;
import exceptions.CustomerCouldNotFindVehicleException;
import exceptions.CustomerHasNotPaidException;
import exceptions.MinGreaterThanMaxException;
import exceptions.TillFullException;
import exceptions.VehicleAlreadyPaidException;
import exceptions.VehicleIsNotOccupiedException;
import exceptions.VehicleNotFullException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MainController {
	@FXML
	private TextField txtTicks;
	private Simulator sim;
	private SimpleIntegerProperty numTicks;

	public void initialize() throws CustomerAlreadyPaidException, VehicleIsNotOccupiedException, VehicleAlreadyPaidException, VehicleNotFullException, CustomerCarMismatchException, CustomerAlreadyPresentException, CustomerHasNotPaidException, TillFullException, CustomerCouldNotFindVehicleException, MinGreaterThanMaxException, InterruptedException {
		sim = new Simulator();
		numTicks = new SimpleIntegerProperty(sim.getTicks());
		numTicks.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				txtTicks.setText(""+newValue);
				
			}
		});
		
	}
	
	
	public void startSimulation() throws CustomerAlreadyPaidException, VehicleIsNotOccupiedException, VehicleAlreadyPaidException, VehicleNotFullException, CustomerCarMismatchException, CustomerAlreadyPresentException, CustomerHasNotPaidException, TillFullException, CustomerCouldNotFindVehicleException, MinGreaterThanMaxException, InterruptedException {
		//needs to create a simulator using these user inputs, simulator needs to be changed slightly and we can get rid of textbasedinterface
		//and then we can write some other methods in this to calculate ticks etc
		//carbike.getText
		//family.getText
		//truckBut.get this is a radiobutton
		//pumpsText.getText
		//tillsText.getText
		//
		//
		//		
		sim.runSimulation(2);
		sim.runSimulation(2);
	}
	

}
