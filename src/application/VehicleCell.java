package application;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Vehicle;
import model.FamilySedan;
import model.Motorbike;
import model.SmallCar;
import model.Truck;

public class VehicleCell extends ListCell<Vehicle> {
	// add other vehicles here
	@Override
	public void updateItem(Vehicle item, boolean empty) {
		super.updateItem(item, empty);
		if (item != null && Motorbike.class == item.getClass()) {
			try {
				Image image = new Image(new FileInputStream("Motorbike-Icon.png"));
				ImageView imageView = new ImageView(image);
				imageView.setPreserveRatio(true);
				imageView.setFitWidth(50);
				setGraphic(imageView);
				setText(item.getRegistration().toString() + " Capacity:" + item.getFuelCapacity());
				
			} catch (FileNotFoundException e) {

			}
			
			
		} else if (item != null && FamilySedan.class == item.getClass()) {
			try {
				Image image = new Image(new FileInputStream("download.png"));
				ImageView imageView = new ImageView(image);
				imageView.setPreserveRatio(true);
				imageView.setFitWidth(50);
				setGraphic(imageView);
				setText(item.getRegistration().toString() + " Capacity:" + item.getFuelCapacity());
				
			} catch (FileNotFoundException e) {

			}
		} else if (item != null && SmallCar.class == item.getClass()) {
			try {
				Image image = new Image(new FileInputStream("car.png"));
				ImageView imageView = new ImageView(image);
				imageView.setPreserveRatio(true);
				imageView.setFitWidth(50);
				setGraphic(imageView);
				setText(item.getRegistration().toString() + " Capacity:" + item.getFuelCapacity());
				
			} catch (FileNotFoundException e) {

			}
		} else if (item != null && Truck.class == item.getClass()) {
			try {
				Image image = new Image(new FileInputStream("truck.png"));
				ImageView imageView = new ImageView(image);
				imageView.setPreserveRatio(true);
				imageView.setFitWidth(50);
				setGraphic(imageView);
				setText(item.getRegistration().toString() + " Capacity:" + item.getFuelCapacity());
				
			} catch (FileNotFoundException e) {

			}
		}
			else {
			setText("");
			setGraphic(null);
		}
	}
}
