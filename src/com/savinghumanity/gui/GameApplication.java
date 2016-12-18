package com.savinghumanity.gui;

import com.savinghumanity.gamelogic.GameEngine;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameApplication extends Application {

	private static GameEngine engine;
	private static Help helpScene;
	private static MainMenu mainMenuScene;
	private static MultiplayerMenu multiplayerMenuScene;
	private static GameScene gameScene;

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

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Saving Humanity");
		
		
		 GridPane grid = new GridPane();
		//Dummy mainmenu - taken from JavaFX login menu application
		 mainMenuScene = new MainMenu(grid,primaryStage);
		
		//Initialize pane that will be used for specific scene!
		Group group = new Group();
		//Pass that pane to the constructor so that scene can call super()
		 gameScene = new GameScene(group,primaryStage);
		
		primaryStage.setScene(mainMenuScene);
		primaryStage.show();
		
		
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
		alert.setContentText("File couldn't found on the path specified:" + filePath + "/nPlease make sure that game is installed correctly on your system! Program is now exiting" );
		alert.showAndWait();
		Platform.exit();
	    System.exit(0);
	}
	public static void alertIOException(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("IO Exception");
		alert.setHeaderText("Input/Output Exception");
		alert.setContentText("An IOException is occurred. Program is exiting now" );
		alert.showAndWait();
		Platform.exit();
	    System.exit(0);
	}
	

}