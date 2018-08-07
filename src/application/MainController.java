package application;

import java.util.LinkedList;

import javax.swing.event.AncestorEvent;

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
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableNumberValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Customer;

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
	@FXML
	private GridPane simSettings;
	@FXML
	private GridPane simView;
	@FXML
	private ListView<Customer> shopContentsView;
	@FXML
	private VBox tillContainer;
	
	private Simulator sim;


	public void initialize() throws CustomerAlreadyPaidException, VehicleIsNotOccupiedException, VehicleAlreadyPaidException, VehicleNotFullException, CustomerCarMismatchException, CustomerAlreadyPresentException, CustomerHasNotPaidException, TillFullException, CustomerCouldNotFindVehicleException, MinGreaterThanMaxException, InterruptedException {
		txtChanceOfSmallVehicle.setText("0.01");
		txtChanceOfFamilySedan.setText("0.01");
		txtChanceOfTrucks.setText("0.02");
		txtChanceOfTrucks.setDisable(true);
		txtNumPumps.setText("2");
		txtNumTills.setText("2");
		simView.setDisable(true);
	}
	
	
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

	
	
	public void tick() throws CustomerAlreadyPaidException, VehicleIsNotOccupiedException, VehicleAlreadyPaidException, VehicleNotFullException, CustomerCarMismatchException, CustomerAlreadyPresentException, CustomerHasNotPaidException, TillFullException, CustomerCouldNotFindVehicleException, MinGreaterThanMaxException, InterruptedException {
		sim.runSimulation(10);
	}
	
	public void createSimulation() {
		sim = new Simulator(Double.parseDouble(txtChanceOfSmallVehicle.getText()), Double.parseDouble(txtChanceOfFamilySedan.getText()), Double.parseDouble(txtChanceOfTrucks.getText()), cbxTrucksAllowed.isSelected(), Integer.parseInt(txtNumPumps.getText()), Integer.parseInt(txtNumTills.getText()));
		createTillView();
		simSettings.setDisable(true);
		simView.setDisable(false);
		setupListeners();
	}

	public void deleteSimulation() {
		sim = null;
		simSettings.setDisable(false);
		simView.setDisable(true);
		clearSimView();
	}
	
	public void setupListeners() {
		sim.getStation().getTicks().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				txtTicks.setText(newValue.toString());
			}
			
		});
		sim.getStation().getShop().getContents().addListener(new SetChangeListener<Customer>() {

			@Override
			public void onChanged(Change<? extends Customer> change) {
				shopContentsView.getItems().setAll(change.getSet());
			}
			
		});
	}
	
	public void clearSimView() {
		txtTicks.setText("");
	}
	
	private void createTillView() {
		LinkedList<ListView<Customer>> tills = new LinkedList<ListView<Customer>>();
		for (int i = 0; i < Integer.parseInt(txtNumTills.getText()); i++) {
			tillContainer.getChildren().add(new ListView<Customer>());
		}
		
	}
}
