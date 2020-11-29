package container;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AnchorApp extends Application{
	

	@Override
	public void start(Stage stage) throws Exception {
		AnchorPane pane = new AnchorPane();
		
		Button bt1 = new Button("버튼1");
		Button bt2 = new Button("버튼2");
		Button bt3 = new Button("버튼3");
		
		
		bt1.setPrefSize(100, 100);
		bt1.setLayoutX(100);
		pane.getChildren().add(bt1);
		
		bt2.setLayoutX(200);
		bt2.setPrefSize(100, 100);
		pane.getChildren().add(bt2);
		
		bt3.setLayoutX(300);
		bt3.setPrefSize(100, 100);
		pane.getChildren().add(bt3);
		
		Scene s = new Scene(pane);
		stage.setWidth(500);
		stage.setHeight(500);
		stage.setScene(s);
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}

}