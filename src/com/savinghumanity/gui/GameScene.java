package com.savinghumanity.gui;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;

import java.util.ArrayList;

import com.savinghumanity.entity.Animation;
import com.savinghumanity.entity.Bullet;
import com.savinghumanity.entity.EnemyTank;
import com.savinghumanity.entity.GameObject;
import com.savinghumanity.entity.Increase;
import com.savinghumanity.entity.IncreaseBonusType;
import com.savinghumanity.entity.Tile;
import com.savinghumanity.entity.WaterTile;
import com.savinghumanity.file.FileManager;
import com.savinghumanity.fx.Effect;
import com.savinghumanity.gamelogic.GameEngine;
import com.savinghumanity.input.InputManager;
import java.io.File;
import java.net.URL;

import javafx.animation.AnimationTimer;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

public class GameScene extends Scene {
	public GameScene(Group groupPane, Stage primaryStage) {
		super(groupPane,800,640,Color.BLACK);

		final int animationAlternateTime = 400; // in milliseconds, time intervals for alternating images
		final int updateTime = 8; // in ms
		final Label timeLabel = new Label("0");
		timeLabel.setLayoutX(640.0);
		timeLabel.setContentDisplay(ContentDisplay.RIGHT);
		timeLabel.setTextFill(Color.RED);

		Label bonusLabel = new Label("");
		bonusLabel.setLayoutX(640.0);
		bonusLabel.setLayoutY(50.0);
		
		Label healthLabel = new Label("");
		healthLabel.setLayoutX(640.0);
		healthLabel.setLayoutY(100.0);

		//Inserting song
		String musicFile = "sound.mp3";     // For example
		Media sound = new Media(FileManager.getSoundFile().toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
		mediaPlayer.setCycleCount(-1);

		final Button soundButton = new Button("M");
		soundButton.autosize();
		soundButton.setLayoutX(760.0);
		soundButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				if(mediaPlayer.isMute())
					mediaPlayer.setMute(false);
				else
					mediaPlayer.setMute(true);
			}
		});

		Canvas canvas = new Canvas(800,640);
		groupPane.getChildren().add(canvas);
		groupPane.getChildren().add(timeLabel);
		groupPane.getChildren().add(soundButton);
		groupPane.getChildren().add(bonusLabel);
		groupPane.getChildren().add(healthLabel);

		final GraphicsContext gc = canvas.getGraphicsContext2D();

		GameApplication.aTimer = new AnimationTimer()
		{
			long startNanoTime = System.nanoTime();
			String secBefore = "";
			long iterations = 0;
			long FPS;
			long oldNanoTime = System.nanoTime();
			double animationTimer = 0;
			double updateTimer = 0;

			@Override
			public void handle(long currentNanoTime)
			{       
				//System.out.println(GameEngine.getInstance().getAllObjects().size());
				gc.clearRect(0, 0, 800, 640);

				double t = (currentNanoTime - startNanoTime) / 1000000000.0;
				bonusLabel.setText("");

				drawGameObjects(gc); // Draw every game object
				// total elapsed milliseconds
				animationTimer += System.nanoTime() - oldNanoTime;
				updateTimer += System.nanoTime() - oldNanoTime;


				String min = "" + (int) t/60;
				String sec = "" + (int) t % 60;
				if(!sec.equals(secBefore)){ // If one second is passed, then look for total iterations in that time interval
					FPS = iterations;
					iterations = 0;
				}
				else{
					iterations++;
				}
				secBefore = sec;

				if( animationTimer / 1000000.0 > animationAlternateTime){ // If elapsed time is passes animation time, then alternate animations
					animationTimer = 0.0;
					alternateAnimations();
				}
				

				oldNanoTime = System.nanoTime(); // update old nano time

				timeLabel.setText("Time: " + min + "." + sec + " min.\n FPS:" + (FPS) ); // Display time and FPS
				healthLabel.setText("Health:" + GameEngine.getInstance().getPlayerTankList().get(0).getHealth());
				String bonusList = "";
				for(int i = 0 ; i < GameEngine.getInstance().getPlayerTankList().get(0).getTankBonuses().size() ; i++ ){
					if(!(GameEngine.getInstance().getPlayerTankList().get(0).getTankBonuses().get(i) instanceof Increase))
						bonusList += GameEngine.getInstance().getPlayerTankList().get(0).getTankBonuses().get(i).getClass().getSimpleName() + "\n";
					else{
						if(((Increase)(GameEngine.getInstance().getPlayerTankList().get(0).getTankBonuses().get(i))).getType() == IncreaseBonusType.INCREASE_DAMAGE)
							bonusList += "Increase Damage\n";
						else if(((Increase)(GameEngine.getInstance().getPlayerTankList().get(0).getTankBonuses().get(i))).getType() == IncreaseBonusType.INCREASE_FIRE_RANGE)
							bonusList += "Increase Fire Range\n";
						else if(((Increase)(GameEngine.getInstance().getPlayerTankList().get(0).getTankBonuses().get(i))).getType() == IncreaseBonusType.INCREASE_HEALTH)
							bonusList += "Increase Health\n";
						else if(((Increase)(GameEngine.getInstance().getPlayerTankList().get(0).getTankBonuses().get(i))).getType() == IncreaseBonusType.INCREASE_SPEED)
							bonusList += "Increase Speed\n";
					}
				}
				if(!GameEngine.getInstance().getPlayerTankList().get(0).getTankBonuses().isEmpty())
					bonusLabel.setText(bonusList);

				if(updateTimer / 1000000.0 > updateTime){
					GameEngine.getInstance().handleCollision();
					updateGameObjects();
					updateTimer = 0.0f;
				}

			}
			private void updateGameObjects() {
				switch(GameEngine.getInstance().getGametype()){
				case Single:
					GameEngine.getInstance().singleModeVictoryCheck(primaryStage);
					break;
				case MultiplayerDM:
					GameEngine.getInstance().multiDeathmatchVictoryCheck(primaryStage);
					break;
				case MultiplayerTDM:
					GameEngine.getInstance().multiTeamDeathmatchVictoryCheck(primaryStage);
					break;
				}

				GameEngine.getInstance().bulletDistanceChecker();
				GameEngine.getInstance().checkBonusDuration();
				GameEngine.getInstance().sweepDeadEffects();
				for(int i = 0 ; i < GameEngine.getInstance().getAllObjects().size() ; i++){

					GameEngine.getInstance().getAllObjects().get(i).update();
					if(GameEngine.getInstance().getAllObjects().get(i) instanceof Bullet)
						((Bullet)(GameEngine.getInstance().getAllObjects().get(i))).updateBullet();
					if(GameEngine.getInstance().getAllObjects().get(i) instanceof EnemyTank){
						try{
							((EnemyTank)(GameEngine.getInstance().getAllObjects().get(i))).findAPath(GameEngine.getInstance().getPlayerTankList().get(0));
						}catch(IndexOutOfBoundsException e){
							
						}
					}
				}

			}




		};
	}
	/**
	 * Draws all objects to their respective positions if they are alive
	 * @param g GraphicsContext
	 */
	private void drawGameObjects(GraphicsContext g){
		for(int i = 0; i < GameEngine.getInstance().getAllObjects().size() ; i++){
			GameObject curObj = GameEngine.getInstance().getAllObjects().get(i);
			if(curObj.isAlive())
				g.drawImage(GameEngine.getInstance().getAllObjects().get(i).getImage(), (int)(curObj.getPosX()) , (int)(curObj.getPosY()));
		}
	}
	/**
	 * Look inside all game objects, then find that are of type Animation, then alternate their images
	 */
	public void alternateAnimations(){
		for(int i = 0; i < GameEngine.getInstance().getAllObjects().size() ; i++){
			if(GameEngine.getInstance().getAllObjects().get(i) instanceof Animation)
				((Animation)(GameEngine.getInstance().getAllObjects().get(i))).alternateAnimation();
		}

	}
	

}
