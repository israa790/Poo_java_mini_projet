package mini_projet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main_fx extends Application {

	 public void start(Stage primaryStage) throws Exception{
	 Parent root =
	FXMLLoader.load(getClass().getResource("proj_controller.fxml"));
	 primaryStage.setTitle("Gérer Compte");
	 primaryStage.setScene(new Scene(root, 800, 600));
	 primaryStage.initStyle(StageStyle.TRANSPARENT);
	 primaryStage.show();
	 }
	 
	 public static void main(String[] args) {
		   launch(args);
		 }
}
