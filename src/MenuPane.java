import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

//This class builds the main menu that players will
//see when they first open the application

public class MenuPane extends VBox {
	
	private Font f = new Font(20);		//increases font size
	
	//Constructor
	public MenuPane() {
		TextArea instructions = new TextArea("\t\t\tWelcome to LearnToType!\n\n"			//displays the menu
				+ "Instructions: Press \"play game\" and type what you see\n"
				+ "\ton screen. Make sure to press the enter key after\n"
				+ "\tevery line. Then view you stats using the\n"
				+ "\t\"View Statistics\" button.");
		
		instructions.setEditable(false);
		instructions.setFont(f);
		
		this.getChildren().add(instructions);
	}
}
