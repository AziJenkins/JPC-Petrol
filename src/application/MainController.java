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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class MainController {
	@FXML
	private TextField txtTicks;
	@FXML
	private Button btnCreateSim;
	@FXML
	private TextField txtChanceOfSmallVehicle;
	@FXML
	private TextField txtChanceOfFamilySedan;
	@FXML
	private TextField txtChanceOfTrucks;
	@FXML
	private CheckBox cbxTrucksAllowed;
	@FXML
	private Button btnProgressTime;
	@FXML
	private TextField txtNumTills;
	@FXML
	private TextField txtNumPumps;
	
	private Simulator sim;
	private SimpleIntegerProperty numTicks;

	public void initialize() throws CustomerAlreadyPaidException, VehicleIsNotOccupiedException, VehicleAlreadyPaidException, VehicleNotFullException, CustomerCarMismatchException, CustomerAlreadyPresentException, CustomerHasNotPaidException, TillFullException, CustomerCouldNotFindVehicleException, MinGreaterThanMaxException, InterruptedException {
		txtChanceOfSmallVehicle.setText("0.01");
		txtChanceOfFamilySedan.setText("0.01");
		txtChanceOfTrucks.setText("0.02");
		txtChanceOfTrucks.setDisable(true);
		txtNumPumps.setText("2");
		txtNumTills.setText("2");
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
	
	
	public void tick() throws CustomerAlreadyPaidException, VehicleIsNotOccupiedException, VehicleAlreadyPaidException, VehicleNotFullException, CustomerCarMismatchException, CustomerAlreadyPresentException, CustomerHasNotPaidException, TillFullException, CustomerCouldNotFindVehicleException, MinGreaterThanMaxException, InterruptedException {
		sim.runSimulation(1);
	}
	
	public void createSimulation() {
		sim = new Simulator(Double.parseDouble(txtChanceOfSmallVehicle.getText()), Double.parseDouble(txtChanceOfFamilySedan.getText()), Double.parseDouble(txtChanceOfTrucks.getText()), cbxTrucksAllowed.isSelected(), Integer.parseInt(txtNumPumps.getText()), Integer.parseInt(txtNumTills.getText()));
		txtChanceOfSmallVehicle.setDisable(true);
		txtChanceOfFamilySedan.setDisable(true);
		txtNumPumps.setDisable(true);
		txtNumTills.setDisable(true);
	}

	public void deleteSimulation() {
		sim = null;
		txtChanceOfSmallVehicle.setDisable(false);
		txtChanceOfFamilySedan.setDisable(false);
		txtNumPumps.setDisable(false);
		txtNumTills.setDisable(false);
	}
}
