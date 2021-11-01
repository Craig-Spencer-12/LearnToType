package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	Button b1, b2;
	public void start(Stage primaryStage) {
		try {
			
			VBox root = new VBox();
			b1 = new Button("1");
			root.getChildren().add(b1);
			
			VBox root2 = new VBox();
			b2 = new Button("2");
			root2.getChildren().add(b2);
			
			Scene scene = new Scene(root,400,400);
			Scene scene2 = new Scene(root2,500,400);
			
			b1.setOnAction(e -> primaryStage.setScene(scene2));
			b2.setOnAction(e -> primaryStage.setScene(scene));
			
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.print("error");
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

//Menu

//count down

//progress bar + wpm + timer

//Text

//input window

//stats
