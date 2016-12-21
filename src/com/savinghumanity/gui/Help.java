package com.savinghumanity.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Help extends Scene {

	public Help(GridPane grid, Stage primaryStage) {
		super(grid, 800, 600);
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(20);
		grid.setVgap(20);
			
		Text scenetitle = new Text ("Help");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		
		Label label = new Label();
		Button button = new Button("Back"); 
		
		label.setText("For Single Player: \n"
				+ "press W-A-S-D to move the tank, press Left_Ctrl to shoot \n"
				+ "For Multi Player: \n"
				+ "press W-A-S-D to move the tank1, press Left_Ctrl to shoot \n"
				+ "press G-Y-H-J to move the tank2, press B to shoot \n"
				+ "press O-K-L-Ş to move the tank3, press Right_Ctrl to shoot \n"
				+ "press arrow keys to move the tank4, press numpad 0(zero) to shoot \n"
				+ "press Q for sound on/off \n"
				+ "press P to pause/continue the game");
		
		button.setMaxWidth(Double.MAX_VALUE);
			
		button.setOnAction(e -> primaryStage.setScene(GameApplication.mainMenuScene));
			
		grid.add(scenetitle, 0, 0);
		grid.add(label, 0, 1);
		grid.add(button, 0, 4);
		}
		
}
