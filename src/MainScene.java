import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//This class builds the main scene that all panes will be placed on

public class MainScene extends Application {

	//Instance variables
	private Scene scene1, scene2, scene3;
	private Button play, stat, return1, return2, clearStats;
	private File gameFile = new File("gameData.ba");
	
	public void start(Stage primaryStage) throws Exception {

		//Crates all the Panes I made
		MenuPane menu = new MenuPane();
		StatPane stats = new StatPane();
		GamePane game = new GamePane();
		
		//adds buttons
		play = new Button("Play Game");
		stat = new Button("View Statistics");
		return1 = new Button("Return to Main Menu");
		return2 = new Button("Return to Main Menu");
		clearStats = new Button("Clear Data");
		
		//creates the rootPane to be added to the scene
		BorderPane rootPane1 = new BorderPane();
		StackPane rootPane2 = new StackPane();
		StackPane rootPane3 = new StackPane();
		
		//creates the button panes to be added to the scene
		HBox bPane1 = new HBox(150);
		VBox bPane2 = new VBox();
		HBox bPane3 = new HBox(150);
		
		//adds buttons to their proper panes
		bPane1.getChildren().addAll(play, stat);
		bPane2.getChildren().add(return1);
		bPane3.getChildren().addAll(return2, clearStats);
		
		bPane1.setPadding(new Insets(50, 40, 40, 140));
		
		//rootPane1.getChildren().addAll(menu, bPane1);
		rootPane1.setTop(bPane1);
		rootPane1.setCenter(menu);
		rootPane2.getChildren().addAll(game, bPane2);
		rootPane3.getChildren().addAll(stats, bPane3);
	
		//creates the 3 scenes
		scene1 = new Scene(rootPane1, 600, 400);
		scene2 = new Scene(rootPane2, 500, 300);
		scene3 = new Scene(rootPane3, 500, 400);
				
		//gives functionality to each button primaryStage.setScene(scene1)
		play.setOnAction(e -> {													//sends player to game pane / reads .ba file
			primaryStage.setScene(scene2);
			
			try {
                FileInputStream fInputStream = new FileInputStream(gameFile); //create objects
                ObjectInputStream oInputStream = new ObjectInputStream(fInputStream);
                StatPane tempStats = (StatPane) oInputStream.readObject(); //read the object
                stats.setUp(tempStats);
                fInputStream.close();
                oInputStream.close();
            } 
			
			catch (ClassNotFoundException e1) {System.out.println(" was not found");}
            catch (NotSerializableException e1) {System.out.println("Not serializable exception");}
            catch (IOException e1) {System.out.println(" was not found");}
		});
		
		
		stat.setOnAction(e -> {													//sends player to stat pane / reads .ba file
			primaryStage.setScene(scene3);
			try{
                FileInputStream fInputStream = new FileInputStream(gameFile); //create objects
                ObjectInputStream oInputStream = new ObjectInputStream(fInputStream);
                StatPane tempStats = (StatPane) oInputStream.readObject(); //read the object
                stats.setUp(tempStats);
                fInputStream.close();
                oInputStream.close();
            } 
			
			catch (ClassNotFoundException e1) {System.out.println(" was not found");}
            catch (NotSerializableException e1) {System.out.println("Not serializable exception");}
            catch (IOException e1) {System.out.println(" was not found");}
		});
		
		return1.setOnAction(e -> {												//sends player to menu pane / updates .ba file
			try {
				if(game.getGameComplete()) {
					stats.inputGameInfo(game.getCharCount(), game.getMistakes(), game.getWPM());
					
					try{
						gameFile = new File("gameData.ba");
                        FileOutputStream fOutStream = new FileOutputStream(gameFile);
                        ObjectOutputStream oOutStream = new ObjectOutputStream(fOutStream);
                        oOutStream.writeObject(stats);
                        fOutStream.close();
                        oOutStream.close();
                     } 
					
					catch (NotSerializableException e1) {System.out.println("Not Serializable Exception");}
                    catch (IOException e1) {System.out.println(" was not found");}
				}
				
				restart(primaryStage);
			}
			
			catch (Exception e1) {System.out.println(e1);}
		});
		
		return2.setOnAction(e -> primaryStage.setScene(scene1));				//sends player to menu pane
		
		clearStats.setOnAction(e -> {											//deletes .ba file
			stats.clear();
			
			try{
				gameFile.delete();
				gameFile = new File("gameData.ba");
                FileInputStream fInputStream = new FileInputStream(gameFile); //create objects
                ObjectInputStream oInputStream = new ObjectInputStream(fInputStream);
                StatPane tempStats = (StatPane) oInputStream.readObject(); //read the object
                stats.setUp(tempStats);
                fInputStream.close();
                oInputStream.close();
            }
			
			catch (ClassNotFoundException e1) {}
            catch (NotSerializableException e1) {System.out.println("Not serializable exception");}
            catch (IOException e1) {}
		});
		
		primaryStage.setTitle("Learn to Type"); 
		primaryStage.setScene(scene1);
		primaryStage.show();
	}
	
	public void restart(Stage stage) throws Exception {						//restarts the stage but does not delete game data
	    start(stage);
	}
	
	public static void main(String[] args){
		Application.launch(args);
	}
}
