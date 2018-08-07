package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener.Change;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Customer;
import model.Shop;
import model.TillController;

public class ShopViewController extends ListView<Customer>{

	@FXML
	private ObservableSet<Customer> contents;
	
	public ShopViewController(Shop shop) {
		contents = FXCollections.observableSet(shop.getContents());
		ListView<Customer> ShopContentsView = new ListView<>();
		contents.addListener((Change<? extends Customer> c) -> {
		    if (c.wasAdded()) {
		        ShopContentsView.getItems().add(c.getElementAdded());
		    }
		    if (c.wasRemoved()) {
		        ShopContentsView.getItems().remove(c.getElementRemoved());
		    }
		});
	}
}
