package application;

import java.util.ArrayList;
import java.util.List;

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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Customer;
import model.Pump;
import model.Till;
import model.Vehicle;

public class MainController {
	@FXML
	private TextField txtTicks;
	@FXML
	private TextField txtFuelGallons;
	@FXML
	private TextField txtShopIncome;
	@FXML
	private TextField txtTimeMin;
	@FXML
	private TextField txtTimeHour;
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

	private List<ListView<Customer>> tillViews;
	@FXML
	private HBox pumpContainer;

	private List<ListView<Vehicle>> pumpViews;

	@FXML

	private Simulator sim;

	public void initialize() throws CustomerAlreadyPaidException, VehicleIsNotOccupiedException, VehicleAlreadyPaidException, VehicleNotFullException, CustomerCarMismatchException, CustomerAlreadyPresentException, CustomerHasNotPaidException,
			TillFullException, CustomerCouldNotFindVehicleException, MinGreaterThanMaxException, InterruptedException {
		txtChanceOfSmallVehicle.setText("0.01");
		txtChanceOfFamilySedan.setText("0.01");
		txtChanceOfTrucks.setText("0.02");
		txtChanceOfTrucks.setDisable(true);
		txtNumPumps.setText("2");
		txtNumTills.setText("2");
		simView.setDisable(true);
		tillViews = new ArrayList<ListView<Customer>>();
		pumpViews = new ArrayList<ListView<Vehicle>>();
	}

	public void tick() throws CustomerAlreadyPaidException, VehicleIsNotOccupiedException, VehicleAlreadyPaidException, VehicleNotFullException, CustomerCarMismatchException, CustomerAlreadyPresentException, CustomerHasNotPaidException,
			TillFullException, CustomerCouldNotFindVehicleException, MinGreaterThanMaxException, InterruptedException {
		sim.runSimulation(1);
	}

	public void createSimulation() {
		sim = new Simulator(Double.parseDouble(txtChanceOfSmallVehicle.getText()), Double.parseDouble(txtChanceOfFamilySedan.getText()), cbxTrucksAllowed.isSelected(), Integer.parseInt(txtNumPumps.getText()), Integer.parseInt(txtNumTills.getText()));
		createTillView();
		createPumpView();
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

	private void setupListeners() {
		sim.getStation().getGallonsSold().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				txtFuelGallons.setText("" + newValue.toString());
			}

		});
		sim.getStation().getShopIncome().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				txtShopIncome.setText("" + newValue.toString());
			}

		});
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

	private void clearSimView() {
		tillViews = new ArrayList<ListView<Customer>>();
		pumpViews = new ArrayList<ListView<Vehicle>>();
		tillContainer.getChildren().clear();
		pumpContainer.getChildren().clear();
		txtTicks.clear();
	}

	private void createTillView() {
		Till[] tills = sim.getStation().getTillController().getTills();
		for (int i = 0; i < Integer.parseInt(txtNumTills.getText()); i++) {
			ListView<Customer> lv = new ListView<Customer>(tills[i].getQueue().getObservable());
			tillViews.add(lv);
		}
		tillContainer.getChildren().addAll(tillViews);
	}

	private void createPumpView() {
		Pump[] pumps = sim.getStation().getPumpController().getPumps();
		for (int i = 0; i < Integer.parseInt(txtNumPumps.getText()); i++) {
			ListView<Vehicle> lv = new ListView<Vehicle>(pumps[i].getQueue().getObservable());
			pumpViews.add(lv);
		}
		pumpContainer.getChildren().addAll(pumpViews);
	}

	public void runForMin() throws NumberFormatException, CustomerAlreadyPaidException, VehicleIsNotOccupiedException, VehicleAlreadyPaidException, VehicleNotFullException, CustomerCarMismatchException, CustomerAlreadyPresentException,
			CustomerHasNotPaidException, TillFullException, CustomerCouldNotFindVehicleException, MinGreaterThanMaxException, InterruptedException {
		sim.runSimulation(Integer.parseInt(txtTimeMin.getText()) * 6);
	}

	public void runForHour() throws NumberFormatException, CustomerAlreadyPaidException, VehicleIsNotOccupiedException, VehicleAlreadyPaidException, VehicleNotFullException, CustomerCarMismatchException, CustomerAlreadyPresentException,
			CustomerHasNotPaidException, TillFullException, CustomerCouldNotFindVehicleException, MinGreaterThanMaxException, InterruptedException {
		sim.runSimulation(Integer.parseInt(txtTimeHour.getText()) * 6 * 60);
	}

	public void runAll() throws CustomerAlreadyPaidException, VehicleIsNotOccupiedException, VehicleAlreadyPaidException, VehicleNotFullException, CustomerCarMismatchException, CustomerAlreadyPresentException, CustomerHasNotPaidException,
			TillFullException, CustomerCouldNotFindVehicleException, MinGreaterThanMaxException, InterruptedException {
		Double[] probabilities = { 0.01, 0.02, 0.03, 0.04, 0.05 };
		int[] numQueues = { 1, 2, 4 };
		for (int trucks = 0; trucks < 2; trucks++) {
			for (int smallVehicle = 0; smallVehicle < 5; smallVehicle++) {
				for (int familyVehicle = 0; familyVehicle < 5; familyVehicle++) {
					for (int numTills = 0; numTills < 3; numTills++) {
						for (int numPumps = 0; numPumps < 3; numPumps++) {
							Simulator s = new Simulator(probabilities[smallVehicle], probabilities[familyVehicle], trucks < 1 ? true : false, numQueues[numPumps], numQueues[numTills]);
							s.runSimulation(1440);
							// write outputs to file
						}
					}
				}
			}
		}
	}
}
