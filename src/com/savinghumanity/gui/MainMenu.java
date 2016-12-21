package com.savinghumanity.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu extends Scene{

	public MainMenu(GridPane grid, Stage primaryStage) {
		super(grid, 800,600);
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(20);
		
		Text scenetitle = new Text ("Saving Humanity");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		
		Button button1 = new Button("Play SinglePlayer");
		Button button2 = new Button("Play MultiPlayer");
		Button button3 = new Button("Help");
		Button button4 = new Button("Exit"); 
		
		button1.setMaxWidth(Double.MAX_VALUE);
		button2.setMaxWidth(Double.MAX_VALUE);
		button3.setMaxWidth(Double.MAX_VALUE);
		button4.setMaxWidth(Double.MAX_VALUE);
		
		button1.setOnAction(e -> primaryStage.setScene(Main.gameScene));
		button2.setOnAction(e -> primaryStage.setScene(Main.multiplayerScene));
		button3.setOnAction(e -> primaryStage.setScene(Main.helpScene));
		button4.setOnAction(e -> System.exit(0));
		
		grid.add(scenetitle, 0, 0);
		grid.add(button1, 0, 1);
		grid.add(button2, 0, 2);
		grid.add(button3, 0, 3);
		grid.add(button4, 0, 4);
	}
	
}
