package com.savinghumanity.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MultiplayerMenu extends Scene{
	
	public MultiplayerMenu(GridPane grid, Stage primaryStage) {
		super(grid, 800,600);
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(20);
		grid.setHgap(20);
		
		Text scenetitle = new Text ("Multiplayer Mode");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		//BackgroundImage backgroundImage = new BackGroundImage(new Image getClass.getResource(""))
		
		Label howMany = new Label("How many players:");
		TextField hmTextField = new TextField();
		hmTextField.setPromptText("Enter how many players");
		Button button1 = new Button("Play Deathmatch");
		Button button2 = new Button("Play TeamDeathmatch");
		Button button3 = new Button("Back"); 
		
		button1.setMaxWidth(Double.MAX_VALUE);
		button2.setMaxWidth(Double.MAX_VALUE);
		button3.setMaxWidth(Double.MAX_VALUE);
	
		
		button1.setOnAction(e -> primaryStage.setScene(Main.gameScene));
		button2.setOnAction(e -> primaryStage.setScene(Main.gameScene));
		button3.setOnAction(e -> primaryStage.setScene(Main.mainMenuScene));
		
		
		grid.add(scenetitle, 0, 0);
		grid.add(howMany, 0, 1);
		grid.add(hmTextField, 1, 1);
		grid.add(button1, 0, 2);
		grid.add(button2, 0, 3);
		grid.add(button3, 0, 4);
	
	}
}
