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
import javafx.collections.ListChangeListener;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;
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
	private TextField txtFuelIncome;
	@FXML
	private TextField txtFuelPrice;
	@FXML
	private TextField txtShopIncome;
	@FXML
	private TextField txtLostShopIncome;
	@FXML
	private TextField txtLostGallons;
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
	private HBox tillContainer;

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
		txtChanceOfTrucks.setEditable(false);
		txtFuelPrice.setText("1.2");
		txtNumPumps.setText("2");
		txtNumTills.setText("2");
		simView.setDisable(true);
		txtFuelGallons.setEditable(false);
		txtShopIncome.setEditable(false);
		tillViews = new ArrayList<ListView<Customer>>();
		pumpViews = new ArrayList<ListView<Vehicle>>();
		shopContentsView.setCellFactory(new Callback<ListView<Customer>, ListCell<Customer>>() {

			@Override
			public ListCell<Customer> call(ListView<Customer> param) {
				return new CustomerCell();
			}
		});

	}

	public void tick() throws CustomerAlreadyPaidException, VehicleIsNotOccupiedException, VehicleAlreadyPaidException, VehicleNotFullException, CustomerCarMismatchException, CustomerAlreadyPresentException, CustomerHasNotPaidException,
			TillFullException, CustomerCouldNotFindVehicleException, MinGreaterThanMaxException, InterruptedException {
		sim.runSimulation(1);
	}

	public void createSimulation() {
		try {
			sim = new Simulator(Double.parseDouble(txtChanceOfSmallVehicle.getText()), Double.parseDouble(txtChanceOfFamilySedan.getText()), cbxTrucksAllowed.isSelected(), Integer.parseInt(txtNumPumps.getText()),
					Integer.parseInt(txtNumTills.getText()));
			createTillView();
			createPumpView();
			disableSettings();
			simView.setDisable(false);
			setupListeners();
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Not a number");
			alert.setHeaderText("You seem to have entered something that isnt a number");
			alert.show();
		}
	}

	public void disableSettings() {
		txtChanceOfSmallVehicle.setEditable(false);
		txtChanceOfFamilySedan.setEditable(false);
		txtNumTills.setEditable(false);
		txtNumPumps.setEditable(false);
		txtFuelPrice.setEditable(false);
		cbxTrucksAllowed.setDisable(true);
		btnCreateSim.setDisable(true);
	}

	public void enableSettings() {
		txtChanceOfSmallVehicle.setEditable(true);
		txtChanceOfFamilySedan.setEditable(true);
		txtNumTills.setEditable(true);
		txtNumPumps.setEditable(true);
		txtFuelGallons.setEditable(false);
		cbxTrucksAllowed.setDisable(false);
		btnCreateSim.setDisable(false);
	}

	public void deleteSimulation() {
		sim = null;
		enableSettings();
		simView.setDisable(true);
		clearSimView();
	}

	private void setupListeners() {
		sim.getStation().getGallonsSold().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				txtFuelGallons.setText(formatNumberString(newValue));
			}

		});
		sim.getStation().getShopIncome().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				txtShopIncome.setText(formatNumberString(newValue));
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
		sim.getStation().getLostGallons().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				txtLostGallons.setText(formatNumberString(newValue));
			}
		});
		sim.getStation().getLostShopIncome().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				txtLostShopIncome.setText(formatNumberString(newValue));
			}
			
		});
		txtFuelIncome.textProperty().bind(sim.getStation().getGallonsSold().multiply(Double.parseDouble(txtFuelPrice.getText())).asString());
	}
	
	private String formatNumberString(Number n) {
		String asString = n.toString();
		asString += "00000";
		return asString.substring(0, 5);
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
			lv.setCellFactory(new Callback<ListView<Customer>, ListCell<Customer>>() {

				@Override
				public ListCell<Customer> call(ListView<Customer> param) {
					return new ListCell<Customer>();
				}

			});
			tillViews.add(lv);
			HBox.setHgrow(lv, Priority.ALWAYS);
		}
		tillContainer.getChildren().addAll(tillViews);
	}

	private void createPumpView() {
		Pump[] pumps = sim.getStation().getPumpController().getPumps();
		for (int i = 0; i < Integer.parseInt(txtNumPumps.getText()); i++) {
			ListView<Vehicle> lv = new ListView<Vehicle>(pumps[i].getQueue().getObservable());
			lv.setCellFactory(new Callback<ListView<Vehicle>, ListCell<Vehicle>>() {

				@Override
				public ListCell<Vehicle> call(ListView<Vehicle> param) {
					return new VehicleCell();
				}
			});
			pumpViews.add(lv);
			HBox.setHgrow(lv, Priority.ALWAYS);
		}
		pumpContainer.getChildren().addAll(pumpViews);

	}

	public void runForMin() throws NumberFormatException, CustomerAlreadyPaidException, VehicleIsNotOccupiedException, VehicleAlreadyPaidException, VehicleNotFullException, CustomerCarMismatchException, CustomerAlreadyPresentException,
			CustomerHasNotPaidException, TillFullException, CustomerCouldNotFindVehicleException, MinGreaterThanMaxException, InterruptedException {
		try {
			sim.runSimulation(Integer.parseInt(txtTimeMin.getText()) * 6);
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Input");
			alert.setHeaderText("You must enter a number in the box labeled minutes");
			alert.showAndWait();
		}
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
