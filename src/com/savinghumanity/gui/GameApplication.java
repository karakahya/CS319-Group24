package com.savinghumanity.gui;

import java.util.ArrayList;

import com.savinghumanity.entity.Map;
import com.savinghumanity.entity.Tank;
import com.savinghumanity.file.FileManager;
import com.savinghumanity.gamelogic.GameEngine;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameApplication extends Application {

	protected static GameEngine engine;
	protected static Help helpScene;
	protected static MainMenu mainMenuScene;
	protected static MultiplayerMenu multiplayerMenuScene;
	protected static GameScene gameScene;
    protected static AnimationTimer aTimer;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO - implement GameApplication.main
		launch(args);
	}

	/**
	 * 
	 * @param primaryStage
	 */

	
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Saving Humanity");
		primaryStage.setResizable(false);
		
		
		GridPane grid = new GridPane();
		//Dummy mainmenu - taken from JavaFX login menu application
		mainMenuScene = new MainMenu(grid,primaryStage);

		//Initialize pane that will be used for specific scene!
		Group group = new Group();
		//Pass that pane to the constructor so that scene can call super()
		gameScene = new GameScene(group,primaryStage);
		
		GridPane helpGrid = new GridPane();
		helpScene = new Help(helpGrid,primaryStage);
		
		GridPane multiplayerGrid = new GridPane();
		multiplayerMenuScene = new MultiplayerMenu(multiplayerGrid,primaryStage);
		
		
		engine = GameEngine.getInstance();
		
		
		primaryStage.setScene(mainMenuScene);
		primaryStage.show();





	}

	public static Help getHelpScene() {
		return helpScene;
	}

	public static void setHelpScene(Help helpScene) {
		GameApplication.helpScene = helpScene;
	}

	public static MainMenu getMainMenuScene() {
		return mainMenuScene;
	}

	public static void setMainMenuScene(MainMenu mainMenuScene) {
		GameApplication.mainMenuScene = mainMenuScene;
	}
        public static AnimationTimer getATimer(){
            return aTimer;
        }
	public static MultiplayerMenu getMultiplayerMenuScene() {
		return multiplayerMenuScene;
	}

	public static void setMultiplayerMenuScene(MultiplayerMenu multiplayerMenuScene) {
		GameApplication.multiplayerMenuScene = multiplayerMenuScene;
	}

	public static GameScene getGameScene() {
		return gameScene;
	}

	public static void setGameScene(GameScene gameScene) {
		GameApplication.gameScene = gameScene;
	}

	public static void switchToGameScene(Stage primaryStage){
		primaryStage.setScene(gameScene);
	}
	public static void switchToMainMenuScene(Stage primaryStage){
		primaryStage.setScene(mainMenuScene);
	}
	public static void switchToHelpScene(Stage primaryStage){
		primaryStage.setScene(helpScene);
	}
	public static void switchToMultiplayerMenu(Stage primaryStage){
		primaryStage.setScene(multiplayerMenuScene);
	}

	public static void alertFileNotFound(String filePath){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("File Reading Error");
		alert.setHeaderText("File Reading Error");
		alert.setContentText("File couldn't found on the path specified:" + filePath + "Please make sure that game is installed correctly on your system! Program is now exiting" );
		alert.showAndWait();
		Platform.exit();
		System.exit(0);
	}
	public static void alertIOException(String filePath){
		GameApplication.getATimer().stop();
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("IO Exception");
		alert.setHeaderText("Input/Output Exception");
		alert.setContentText("An IOException is occurred in the file:" + filePath + "Program is exiting now" );
		alert.showAndWait();
		Platform.exit();
		System.exit(0);
	}
	public static void alertLevelFinished(int level){
		GameApplication.getATimer().stop();
		Alert alert = new Alert(AlertType.INFORMATION , "Level " + level + " has completed.",ButtonType.OK);
		alert.setTitle("Level Finished");
		alert.setContentText("You have finished current level!" );
		alert.setOnCloseRequest(new EventHandler<DialogEvent>(){
			@Override
			public void handle(DialogEvent e){
				GameApplication.getATimer().start();
			}
		});
		alert.show();
		
		
		//alert.showAndWait();
	}
	public static void alertPlayerDie(){
		GameApplication.getATimer().stop();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("You died");
		alert.setContentText("You have failed the humanity!" );
		alert.show();
		
		

	}


}