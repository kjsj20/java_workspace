package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {
	
	public void start(Stage stage) throws Exception{

//			VBox root = new VBox();
//			root.setPrefSize(400, 400);
//			root.setAlignment(Pos.CENTER);
//			
//			Button btn = new Button("종료");
//			btn.setPrefWidth(80);
//			btn.setOnAction(event -> Platform.exit());
//			root.getChildren().add(btn);
//			
//			Scene scene = new Scene(root);
//			primaryStage.setTitle("Hello JavaFX");
//			primaryStage.setResizable(false);
//			primaryStage.setScene(scene);
//			primaryStage.show();
			Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
			
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
