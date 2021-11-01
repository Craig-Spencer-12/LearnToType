import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

// This class creates the game pane that will be
// Accessed when the user presses the play game button

public class GamePane extends BorderPane {

	//Instance variables
	private File[] gameTextList;
	private TextArea game;													//Used to display the random chosen text file
	private TextField playerField;											//Takes users inputs
	private ArrayList<String> textArr = new ArrayList<String>();			//Will be used to check the users inputs
	
	private String textName, lineText;
	private int currentLine = 0, lineCount = 0, charCount = 0, mistakes = 0, wpm;
	
	private boolean firstLetter = true, gameComplete = false;
	private static long startTime, endTime;

	public GamePane() throws IOException {
		
		Random rand = new Random();
		gameTextList = new File[5];											//change the number when adding more files
		
		for (int i = 0; i < gameTextList.length; i++) {						//fills the file array with all the text files
			textName = ("input" + i + ".txt");								//used in the game
			gameTextList[i] = new File(textName);
		}

		File gameText = gameTextList[rand.nextInt(gameTextList.length)];	//chooses a random file to be used in the game
		Scanner in = new Scanner(gameText);
		
		lineText = in.nextLine();											
		textArr.add(lineText);
		game = new TextArea(lineText);
		charCount += lineText.length();
		
		playerField = new TextField("The clock will start when you press a key");
		
		while(in.hasNextLine()) {											//fills the TextArea and array list
			lineText = in.nextLine();										//with the proper information
			game.appendText("\n" + lineText);
			textArr.add(lineText);
			charCount += lineText.length();
			lineCount++;
		}
		
		VBox topPane = new VBox();											//creates a VBox for the nodes to be placed in
	      topPane.setSpacing(40);
	      topPane.setPadding(new Insets(50, 10, 10, 10));
	      topPane.setStyle("-fx-border-color: black");
	      topPane.getChildren().addAll(playerField, game);
	      
	    this.setTop(topPane);

	    playerField.setOnKeyPressed(e -> {									//Every time the user presses a key the program
	    	if (firstLetter) {												//checks to see if this was the first time a
	    		startTime = System.nanoTime();								//key was pressed.  If so the clock starts
	    		firstLetter = false;
	    	}
	    });
	    
	    playerField.setOnAction(e -> {										//When the user presses enter the program check to see
	    	if (playerField.getText().equals(textArr.get(currentLine)) &&	//if the line the entered matches the one stored in the
	    			currentLine == lineCount) {								//array list
	    		
	    		endTime = System.nanoTime();								//stops clock
	    		
	    		int seconds = (int) ((endTime - startTime) / 1000000000.0);	//calculates time in minutes:seconds and wpm
	    		wpm = (int) ((int) (charCount / 6) / (seconds / 60.0));
	    		int minutes = seconds / 60;
	    		seconds %= 60;
	    		
	    		gameComplete = true;										//sets the game to complete and locks the
	    		playerField.clear();										//The user from inputing more keystrokes
	    		playerField.setDisable(true);
	    		game.setEditable(false);
	    		
	    		String filler = "0";										//puts a "0" in front of seconds if the
	    		if (seconds > 9)											//value is less than 10
	    			filler = "";
	    		
	    		game.setText("Nice Work!\n"									//Sets the text area as a victory screen
	    				+ "You typed " + charCount + " characters and made " + mistakes + " mistakes\n"
	    				+ "It took you a total of " + minutes + ":" + filler + seconds + "\n"
	    				+ "This gives you a score of " + wpm + " words per minute");
	    		
	    		
	    	}
	    	
	    	else if (playerField.getText().equals(textArr.get(currentLine))) {	//removes a line once
	    		playerField.clear();
	    		currentLine++;
	    		
	    		game.clear();
	    		for (int i = currentLine; i < textArr.size(); i++)			//removes the completed line from the TextArea
	    			game.appendText(textArr.get(i) + "\n");
	    	}
	    	
	    	else {															//if the line is wrong the number of mistakes
	    		mistakes++;													//increases and the text turns red
	    		playerField.setStyle("-fx-text-inner-color: red;");
	    	}
	    });
	}
	
	//Accessor methods
	public boolean getGameComplete() {
		return gameComplete;
	}
	
	public int getCharCount() {
		return charCount;
	}
	
	public int getMistakes() {
		return mistakes;
	}
	
	public int getWPM() {
		return wpm;
	}

}
