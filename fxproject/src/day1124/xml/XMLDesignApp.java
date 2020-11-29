package day1124.xml;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class XMLDesignApp extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		URL url = this.getClass().getClassLoader().getResource("day1124/xml/main.fxml");
		FXMLLoader.load(url);
	}
	
	public void showWindow(Stage stage, Parent parent) {
		Scene s = new Scene(parent);
		stage.setScene(s);
		stage.setWidth(500);
		stage.setHeight(500);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
