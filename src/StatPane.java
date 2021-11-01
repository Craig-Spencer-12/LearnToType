import java.io.IOException;
import java.io.Serializable;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

//This class holds the information of the player's statistics
//that are stored on the gameData.ba file

public class StatPane extends BorderPane implements Serializable {
	
	//Instance variables
	private transient TextArea window;
	
	private int gameCount = 0, wpm, avgWPM, charCount, totalChars, 
			mistakes, totalMistakes, bestChars, bestMistakes, bestWPM;
	
	//constructor
	public StatPane() throws IOException{
		
		window = new TextArea();
		window.setEditable(false);
		displayInfo();
		window.setPrefHeight(350);

		window.setStyle("-fx-opacity: 10;");
		
		GridPane topPane = new GridPane();

	      topPane.setPadding(new Insets(50, 10, 10, 10));
	      topPane.setStyle("-fx-border-color: black");
	      topPane.add(window, 0, 1);  
	      
	    this.setBottom(window);
	}
	
	//displays the game data into a user friendly text format
	private void displayInfo() {
		window.setText(("Total Games Played : " + gameCount + "\n\n"
				+ "Your Last Game:\n"
				+ "Characters Typed: " + charCount + "\n"
				+ "Mistakes: " + mistakes + "\n"
				+ "Words Per Minute: " + wpm +"\n\n"
				+ "All Games:\n"
				+ "Characters Typed: " + totalChars + "\n"
				+ "Mistakes: " + totalMistakes + "\n"
				+ "Words Per Minute: " + avgWPM + "\n\n")
				+ "Best Game:\n"
				+ "Characters Typed: " + bestChars + "\n"
				+ "Mistakes: " + bestMistakes + "\n"
				+ "Words Per Minute: " + bestWPM);
	}
	
	//resets all game data
	public void clear() {
		
		window.clear();
		
		this.gameCount = 0;
		this.wpm = 0;
		this.avgWPM = 0;
		this.charCount = 0;
		this.totalChars = 0;
		this.mistakes = 0;
		this.totalMistakes = 0;
		this.bestChars = 0;
		this.bestMistakes = 0;
		this.bestWPM = 0;
		
		displayInfo();
	}
	
	//adds the data of a completed game to the stats
	public void inputGameInfo(int chars, int mis, int words) {
		
		if (this.bestWPM < words) {
			
			this.bestChars = chars;
			this.bestMistakes = mis;
			this.bestWPM = words;
		}
		
		this.charCount = chars;
		this.mistakes = mis;
		this.wpm = words;
		
		totalChars += charCount;
		totalMistakes += mistakes;
		
		int temp = (avgWPM * gameCount) + wpm;
		gameCount++;
		avgWPM = temp / gameCount;		
	}
	
	//Uses game data saved in the gameData.ba file to set up the game stats
	public void setUp(StatPane sp) {
		
		this.gameCount = sp.gameCount;
		
		this.wpm = sp.wpm;
		this.avgWPM = sp.avgWPM;
		this.bestWPM = sp.bestWPM;
		
		this.charCount = sp.charCount;
		this.totalChars = sp.totalChars;
		this.bestChars = sp.bestChars;
		
		this.mistakes = sp.mistakes;
		this.totalMistakes = sp.totalMistakes;
		this.bestMistakes = sp.bestMistakes;
		
		displayInfo();
	}
}
