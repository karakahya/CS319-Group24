package com.savinghumanity.gui;

import com.savinghumanity.gamelogic.GameEngine;
import com.savinghumanity.gamelogic.GameEngine.GameType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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
		ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList("2","3","4"));
		Button button1 = new Button("Play Deathmatch");
		Button button2 = new Button("Play TeamDeathmatch");
		Button button3 = new Button("Back");
                
		
		button1.setMaxWidth(Double.MAX_VALUE);
		button2.setMaxWidth(Double.MAX_VALUE);
		button3.setMaxWidth(Double.MAX_VALUE);
                
                cb.setTooltip(new Tooltip("Select number of players"));
                cb.setValue("2");
	
		
		button1.setOnAction(e -> {
                    final int x = getChoice(cb);
                    GameEngine.getInstance().startGame("data/map/multi.txt", x);
                    GameEngine.getInstance().setGametype(GameType.MultiplayerDM);
                    primaryStage.setScene(GameApplication.gameScene);
                    GameApplication.aTimer.start();
                    System.out.println(x);
                        });
		button2.setOnAction(e -> primaryStage.setScene(GameApplication.gameScene));
		button3.setOnAction(e -> primaryStage.setScene(GameApplication.mainMenuScene));
                
                /*cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Integer>(){
            
                public void changed(ObservableValue ov, Integer number){
                    x = number;
                }
        });
		
		*/
		grid.add(scenetitle, 0, 0);
		grid.add(howMany, 0, 1);
		grid.add(cb, 1, 1);
		grid.add(button1, 0, 2);
		grid.add(button2, 0, 3);
		grid.add(button3, 0, 4);
	
	}
         private int getChoice(ChoiceBox<String> choiceBox){
            String x = choiceBox.getValue();
            return Integer.parseInt(x);
         }
}