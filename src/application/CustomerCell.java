package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import model.Customer;

public class CustomerCell extends ListCell<Customer> {

	// customer cell

	@Override
	public void updateItem(Customer item, boolean empty) {
		super.updateItem(item, empty);
		if (item != null) {
			try {
				Image image = new Image(new FileInputStream("Man-512.png"));
				ImageView imageView = new ImageView(image);
				imageView.setPreserveRatio(true);
				imageView.setFitWidth(50);
				setGraphic(imageView);
				setText("Customer ID:" + item.getRegistration().toString().substring(item.getRegistration().toString().length()-6));
				setTextFill(Paint.valueOf("white"));
				setStyle("-fx-background-color: #" + item.getRegistration().toString().substring(item.getRegistration().toString().length() - 6));
			} catch (FileNotFoundException e) {

			}
		} else {
			setText("");
			setTextFill(Paint.valueOf("white"));
			setStyle("-fx-background-color: white");
		}
	}

}
