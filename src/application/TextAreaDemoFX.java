package application;

//TextAreaDemoFX.java
//This program demonstrate event handling in a text field or text area
//getText(), setText(), clear() and appendText()

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;	//**Need to import
import javafx.event.EventHandler;	//**Need to import

public class TextAreaDemoFX extends Application
{
	private TextArea textArea;
	private TextField textField;
	private Button button;

  public void start(Stage stage)
  {
		textArea = new TextArea("Original text");
		textField = new TextField();

		//entered text will be in blue color
		textField.setStyle("-fx-text-fill: blue");	//css

      button = new Button("Click to append text");
     // button.setMinWidth(50);
      button.setOnAction(new ButtonHandler());

      VBox vbox = new VBox(textArea, textField, button);

      //set the vertical spacing between the nodes
      vbox.setSpacing(10);

		Scene scene = new Scene(vbox, 300, 200);
		stage.setTitle("TextArea Event Demo"); // Set the stage title
		stage.setScene(scene); // Place the scene in the stage
      stage.show(); // Display the stage
  }

  private class ButtonHandler implements EventHandler<ActionEvent>
	{
		  //Override the abstract method handle()
	  	  public void handle(ActionEvent e)
	      {
			  String text = textField.getText();
			  textArea.appendText("\n" + text);
			  textField.clear();
	 	  }
  }
  
  public static void main(String[] args){
		Application.launch(args);
	}
}