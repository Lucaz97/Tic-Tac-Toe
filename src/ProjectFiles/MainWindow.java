package ProjectFiles;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class MainWindow implements Initializable {

	public final Image backgroundImage = new Image(getClass().getResourceAsStream("cellBackground.png"));
	public final Image crossed = new Image(getClass().getResourceAsStream("cellCross.png"));
	public final Image circled = new Image(getClass().getResourceAsStream("cellCircle.png"));

	@FXML
	protected Label userScoreTxt;
	@FXML
	protected Label turnText;
	@FXML
	protected Label compScoreTxt;
	@FXML
	protected Button button1;
	@FXML
	protected Button button2;
	@FXML
	protected Button button3;
	@FXML
	protected Button button4;
	@FXML
	protected Button button5;
	@FXML
	protected Button button6;
	@FXML
	protected Button button7;
	@FXML
	protected Button button8;
	@FXML
	protected Button button9;
	@FXML
	protected Button PlayAgain;
	@FXML
	protected Label gameNumber;
	
	protected int button1State = 0;
	protected int button2State = 0;
	protected int button3State = 0;
	protected int button4State = 0;
	protected int button5State = 0;
	protected int button6State = 0;
	protected int button7State = 0;
	protected int button8State = 0;
	protected int button9State = 0;
	
	protected int rowState = 0;
	protected int colState = 0;
	protected int diagState = 0;
	public boolean playerTurn = true;
	
	private int userScore = 0;
	private int compScore = 0;
	private int gameNum = 1;
	
	
	
	protected IAcomp myIA = new IAcomp();
	
	
	protected int[] buttonState = {button1State, button2State, button3State, button4State, button5State, button6State, button7State, button8State, button9State};
	
	public Griglia field = new Griglia(buttonState);
	
	
	
	private void checkIAmove(){
		System.out.println("chechink AI move");
		for(int row = 0; row < 3; row++){
			for(int col = 0; col <3; col++){
				if(field.getCell(row, col) == 2){
					if(col + row == 0){
						button1.setGraphic(new ImageView(circled));
					} else if (col+row == 4){
						button9.setGraphic(new ImageView(circled));
					} else if (row == 1 && col == 1){
						button5.setGraphic(new ImageView(circled));
					} else if (row == 1 && col == 0){
						button4.setGraphic(new ImageView(circled));
					} else if (row == 1 && col == 2){
						button6.setGraphic(new ImageView(circled));
					} else if (row == 0 && col == 1){
						button2.setGraphic(new ImageView(circled));
					} else if (row == 0 && col == 2){
						button3.setGraphic(new ImageView(circled));
					} else if (row == 2 && col == 0){
						button7.setGraphic(new ImageView(circled));
					} else if (row ==2 && col == 1){
						button8.setGraphic(new ImageView(circled));
					}
				}
			}
		}
	}
	
	
	
	public void buttonPressed(int x, int y, Button button){
		if(field.getCell(x,y) == 0 && playerTurn == true){
			field.setCell(x, y, 1);
			button.setGraphic(new ImageView(crossed));
			playerTurn = false;
			
			if(myIA.hasXWon()){
				PlayAgain.setText("         YOU WON, \n Press to play again");
				PlayAgain.setVisible(true);
				userScore = userScore + 1;
			} else if (myIA.hasOWon()){
				PlayAgain.setText("         YOU LOST, \n Press to play again");
				PlayAgain.setVisible(true);
				compScore = compScore + 1;
			} else if (field.getRowState(0) == 4 && field.getRowState(1) == 4 && field.getRowState(2) == 4){
				PlayAgain.setText("       It's a draw!, \n Press to play again");
				PlayAgain.setVisible(true);
			} else {
				turnText.setText("AI Turn");
				System.out.println("passa il turno all'AI");
				myIA.setGrill(field);
				myIA.callMinimax(0, 1);
				for (PointsAndScores pas : myIA.rootsChildrenScores) {
	                System.out.println("Point: " + pas.point + " Score: " + pas.score);
	            }
				myIA.placeAMove(myIA.returnBestMove(), 2);
				myIA.toGrill(field);
				checkIAmove();
				
				for(int i = 0; i < 3; i++){
					rowState = field.getRowState(i);
					colState = field.getColState(i);
					if(rowState != 0 || colState != 0){
						break;
					}
				}
				diagState = field.getDiagonalsState();
				
				if(myIA.hasXWon()){
					PlayAgain.setText("        YOU WON, \n Press to play again");
					PlayAgain.setVisible(true);
					userScore = userScore + 1;
				} else if (myIA.hasOWon()){
					PlayAgain.setText("       YOU LOST, \n Press to play again");
					PlayAgain.setVisible(true);
					compScore = compScore + 1;
				} else if (field.getRowState(0) == 4 && field.getRowState(1) == 4 && field.getRowState(2) == 4){
					PlayAgain.setText("      It's a draw!, \n Press to play again");
					PlayAgain.setVisible(true);
				} else {
					playerTurn = true;
					turnText.setText("Your turn");
				}
			}
			
			compScoreTxt.setText(Integer.toString(compScore));
			userScoreTxt.setText(Integer.toString(userScore));
		} else {
			System.out.println("this cell is not avaiable");
		}
	}
	
	@Override
	public void initialize(URL GameWindow, ResourceBundle projectFiles) {
		// TODO Auto-generated method stub
		button1.setGraphic(new ImageView(backgroundImage));
		button2.setGraphic(new ImageView(backgroundImage));
		button3.setGraphic(new ImageView(backgroundImage));
		button4.setGraphic(new ImageView(backgroundImage));
		button5.setGraphic(new ImageView(backgroundImage));
		button6.setGraphic(new ImageView(backgroundImage));
		button7.setGraphic(new ImageView(backgroundImage));
		button8.setGraphic(new ImageView(backgroundImage));
		button9.setGraphic(new ImageView(backgroundImage));
		PlayAgain.setVisible( false);
		PlayAgain.setText("Im not supposed to be seen");
		gameNumber.setText("Game 1");
		button1.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("button1 pressed");
				buttonPressed(0,0,button1);
			}
			
		});
		
		button2.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("button2 pressed");
				buttonPressed(0,1,button2);
			}
			
		});
		
		button3.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("button3 pressed");
				buttonPressed(0,2,button3);
			}
			
		});
		
		button4.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("button4 pressed");
				buttonPressed(1,0,button4);
			}
			
		});
		
		button5.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("button5 pressed");
				buttonPressed(1,1,button5);
			}
			
		});
		
		button6.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("button6 pressed");
				buttonPressed(1,2,button6);
			}
			
		});
		
		button7.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("button7 pressed");
				buttonPressed(2,0,button7);
			}
			
		});
		
		button8.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("button8 pressed");
				buttonPressed(2,1,button8);
			}
			
		});
		
		button9.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("button9 pressed");
				buttonPressed(2,2,button9);
			}
			
		});
		
		PlayAgain.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(PlayAgain.isVisible()){
					for(int row = 0; row < 3; row++){
						for(int col = 0; col <3; col++){
							field.setCell(row, col, 0);
						}
					}
					myIA.setGrill(field);
					button1.setGraphic(new ImageView(backgroundImage));
					button2.setGraphic(new ImageView(backgroundImage));
					button3.setGraphic(new ImageView(backgroundImage));
					button4.setGraphic(new ImageView(backgroundImage));
					button5.setGraphic(new ImageView(backgroundImage));
					button6.setGraphic(new ImageView(backgroundImage));
					button7.setGraphic(new ImageView(backgroundImage));
					button8.setGraphic(new ImageView(backgroundImage));
					button9.setGraphic(new ImageView(backgroundImage));
					
					gameNum = gameNum +1;
					String num =Integer.toString(gameNum);
					gameNumber.setText("Game " + num);
					
					playerTurn = true;
					turnText.setText("Your turn");
					PlayAgain.setVisible(false);
				}
			}
		});
	}
	

    
}


	


