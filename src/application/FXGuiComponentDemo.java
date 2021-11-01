package application;

//This program demonstrates some most commonly used GUI components in JavaFX

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;		//Button, Label, TextField is inside this pkg
import javafx.scene.layout.FlowPane;//This is used to set up the layout
import javafx.geometry.Insets;

public class FXGuiComponentDemo extends Application
{
public void start(Stage primaryStage)
{
  // Create a flowpane and set its properties
  FlowPane pane = new FlowPane();

  //An Inset object specifies the size of the border of a pane.
  //The following creates an Insets with the border sizes for top (11),
  //right (12),bottom (13), and left (14) in pixels
  pane.setPadding(new Insets(11, 12, 13, 14));
  pane.setHgap(5);	//set horizontal gap between nodes
  pane.setVgap(5);	//set vertical gap between nodes

  //Create various GUI component
  Button button = new Button("A Button");
  Label label = new Label("This is a label");
  TextField textField = new TextField("this is a text field");
  TextArea textArea = new TextArea("This is a text area");
	CheckBox checkBox = new CheckBox("Check Box");
	RadioButton radioButton = new RadioButton("Radio Button");

	ComboBox comboBox = new ComboBox();
	comboBox.getItems().addAll("Option 1","Option 2","Option 3");

  //Place all nodes in the flow pane
  pane.getChildren().addAll(button,label, textField, textArea, checkBox, radioButton, comboBox);

  // Create a scene and place it in the stage
  Scene scene = new Scene(pane, 500, 300);
  primaryStage.setTitle("JavaFX GUI Nodes Demo"); // Set the stage title
  primaryStage.setScene(scene); // Place the scene in the stage
  primaryStage.show(); // Display the stage
}

public static void main(String[] args){
	Application.launch(args);
}
}
