package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Control {
	@FXML
	private Button button;
	
	public void initialize() {
		//TODO
	}
	
	@FXML
	private void handleButtonAction(ActionEvent event) {
		System.out.println("hi");
	}
}
