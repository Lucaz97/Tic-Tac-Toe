package ProjectFiles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/*Made by Luca Collini luca.collini@maxwell.mi.it
 * 
 * This is a project I wanted to work on to pracice a bit and get nvolved with the minimax algorithm,
 * unfortunately I was not able to figure it out without loking at someone's code.
 * 
 * THE MINIMAX ALGORITHM IMPLEMENTED IN THE IAcomp.java is taken from http://www.codebytes.in/2014/08/minimax-algorithm-tic-tac-toe-ai-in.html
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */



public class MainTic extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("GameWindow.fxml"));
			Scene scene = new Scene(root,490,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("TicTacToe!");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
		
		
	}
	
	
}