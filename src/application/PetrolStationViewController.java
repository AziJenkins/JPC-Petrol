package application;

import javafx.collections.ObservableSet;
import model.Customer;
import model.PetrolStation;

public class PetrolStationViewController {

	private PetrolStation pc;
	
	@FXML
	private ObservableSet<Customer> shop;
	
	public public PetrolStationViewController(PetrolStation pc) {
		this.pc = pc;
		shop = pc.getShop().getContents();
	}
	
	
}
