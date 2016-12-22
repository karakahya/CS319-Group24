package com.savinghumanity.gui;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;

import java.util.ArrayList;

import com.savinghumanity.entity.Animation;
import com.savinghumanity.entity.EnemyTank;
import com.savinghumanity.entity.GameObject;
import com.savinghumanity.entity.Tile;
import com.savinghumanity.entity.WaterTile;
import com.savinghumanity.gamelogic.GameEngine;

import javafx.animation.AnimationTimer;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

public class GameScene extends Scene {
	public GameScene(Group groupPane, Stage primaryStage) {
		super(groupPane,800,640,Color.BLACK);
		
		final int animationAlternateTime = 500; // in milliseconds, time intervals for alternating images
		final int updateTime = 10; // 10 ms
		final Label timeLabel = new Label("0");
		timeLabel.setLayoutX(640.0);
		timeLabel.setContentDisplay(ContentDisplay.RIGHT);
		timeLabel.setTextFill(Color.RED);

		Canvas canvas = new Canvas(800,640);
		groupPane.getChildren().add(canvas);
		groupPane.getChildren().add(timeLabel);



		final GraphicsContext gc = canvas.getGraphicsContext2D();
		final long startNanoTime = System.nanoTime();
		new AnimationTimer()
		{
			String secBefore = "";
			long iterations = 0;
			long FPS;
			long oldNanoTime = 0;
			double animationTimer = 0;
			double updateTimer = 0;
			public void handle(long currentNanoTime)
			{
				
				double t = (currentNanoTime - startNanoTime) / 1000000000.0;
				
				
				
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
				
				drawGameObjects(gc); // Draw every game object
				  // total elapsed milliseconds
				animationTimer += System.nanoTime() - oldNanoTime;
				updateTimer += System.nanoTime() - oldNanoTime;
				if( animationTimer / 1000000.0 > animationAlternateTime){ // If elapsed time is passes animation time, then alternate animations
					animationTimer = 0.0;
					alternateAnimations();
				}
				if(updateTimer / 1000000.0 > updateTime){
					GameEngine.getInstance().handleCollision();
					updateGameObjects();
					updateTimer = 0.0f;
				}
				oldNanoTime = System.nanoTime(); // update old nano time
				
				timeLabel.setText("Time: " + min + "." + sec + " min.\n FPS:" + (FPS) ); // Display time and FPS
				
				
			
			}
			private void updateGameObjects() {
				for(int i = 0 ; i < GameEngine.getAllObjects().size() ; i++){
					GameEngine.getAllObjects().get(i).update();
				}
				
			}


		}.start();
	}
	/**
	 * Draws all objects to their respective positions if they are alive
	 * @param g GraphicsContext
	 */
	private void drawGameObjects(GraphicsContext g){
		for(int i = 0; i < GameEngine.getAllObjects().size() ; i++){
			GameObject curObj = GameEngine.getAllObjects().get(i);
			if(curObj.isAlive())
				g.drawImage(GameEngine.getAllObjects().get(i).getImage(), (int)(curObj.getPosX()) , (int)(curObj.getPosY()));
		}
	}
	/**
	 * Look inside all game objects, then find that are of type Animation, then alternate their images
	 */
	public void alternateAnimations(){
		for(int i = 0; i < GameEngine.getAllObjects().size() ; i++){
			if(GameEngine.getAllObjects().get(i) instanceof Animation)
				((Animation)(GameEngine.getAllObjects().get(i))).alternateAnimation();
		}
		
	}
	// Mert senin kodlarý buraya aldým. Bir kýsmýný yukarýda kullandým zaten. ihtiyaç anýnda el frenini çekip buradan alabiliriz tekrar
	/*
	 @Override
	    public void start(Stage theStage) 
	    {
	        theStage.setTitle("Saving Humanity!");
	        theStage.setResizable(false);
	        theStage.getIcons().add(new Image("/up2.png"));
	        
	        Group root = new Group();
	        Scene theScene = new Scene(root);
	        theStage.setScene(theScene);
	        
	        Label timeLabel = new Label("0");
	        Canvas canvas = new Canvas(320,240);
	        root.getChildren().add(canvas);
	        root.getChildren().add(timeLabel);
	        
	        ArrayList<String> input = new ArrayList<String>();
	 
	        theScene.setOnKeyPressed(
	            new EventHandler<KeyEvent>()
	            {
	                public void handle(KeyEvent e)
	                {
	                    String code = e.getCode().toString();
	 
	                    // only add once... prevent duplicates
	                    if ( !input.contains(code) )
	                        input.add( code );
	                }
	            });
	        
	        theScene.setOnKeyReleased(
	            new EventHandler<KeyEvent>()
	            {
	                public void handle(KeyEvent e)
	                {
	                    String code = e.getCode().toString();
	                    input.remove( code );
	                }
	            });
	        
	        GraphicsContext gc = canvas.getGraphicsContext2D();
	        
	        Image rTank = new Image("/right.png");
	        Image lTank = new Image("/left.png");
	        Image uTank = new Image("/up2.png");
	        Image dTank = new Image("/down.png");
	        Image back = new Image("/back.png");
	        Image rmen = new Image("/rmen.png");
	        Image grass = new Image("/grass.png");
	        Image bullet = new Image("/bullet.png");
	        
	        
	        final long startNanoTime = System.nanoTime();
	        
	        
	        new AnimationTimer()
	        {
	            Image last = rTank;
	            double x = 0;
	            double y = 0;
	            double bx = 0;
	            double by = 0;
	            double bsx = 0;
	            double bsy = 0;
	            @Override
	            public void handle(long currentNanoTime)
	            {
	                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
	                timeLabel.setLayoutX(290.0);
	                timeLabel.setContentDisplay(ContentDisplay.RIGHT);
	                timeLabel.setTextFill(Color.RED);
	                String min = "" + (int) t/60;
	                String sec = "" + (int) t % 60;
	                timeLabel.setText(min + "." + sec);
	                
	                if(input.contains("RIGHT") && x < 272){
	                    x = x + 0.1;
	                    bx = bx + 0.1;
	                    last = rTank;
	                }
	                else if(input.contains("LEFT")){
	                    x = x - 0.1;
	                    bx = bx - 0.1;
	                    last = lTank;
	                }
	                else if(input.contains("UP")){
	                    y = y - 0.1;
	                    by = by - 0.1;
	                    last = uTank;
	                }
	                else if(input.contains("DOWN")){
	                    y = y + 0.1;
	                    by = by + 0.1;
	                    last = dTank;
	                }
	                else {}
	                
	                if(input.contains("SPACE")){ 
	                    if(last == uTank){
	                        bsx = 0;
	                        bsy = -0.3;
	                    }
	                }
	                if(input.contains("SPACE")){ 
	                    if(last == dTank){
	                        bsx = 0;
	                        bsy = + 0.3;
	                    }
	                }
	                if(input.contains("SPACE")){ 
	                    if(last == rTank){
	                        bsx = + 0.3;
	                        bsy = 0;
	                    }
	                }
	                if(input.contains("SPACE")){ 
	                    if(last == lTank){
	                        bsx = - 0.3;
	                        bsy = 0;
	                    }
	                }
	                if(input.contains("R")){
	                    bsx = 0;
	                    bsy = 0;
	                    bx = x;
	                    by = y;
	                    
	                }
	                bx = bx + bsx;
	                by = by + bsy;
	                
	                gc.drawImage(back, 0 , 0);
	                gc.drawImage(rmen, 288,0);
	                
	                if(input.contains("RIGHT"))
	                    gc.drawImage(rTank, x, y);
	                else if(input.contains("LEFT"))
	                    gc.drawImage(lTank, x, y);
	                else if(input.contains("UP"))
	                    gc.drawImage(uTank, x, y);
	                else if(input.contains("DOWN"))
	                    gc.drawImage(dTank, x, y);
	                else
	                    gc.drawImage(last, x, y);
	                
	                if(bsx != 0 || bsy != 0)
	                    gc.drawImage(bullet, bx, by);
	                
	                gc.drawImage(grass, 128, 96);
	                gc.drawImage(grass, 144, 96);
	                gc.drawImage(grass, 160, 96);
	                gc.drawImage(grass, 128, 112);
	                gc.drawImage(grass, 144, 112);
	                gc.drawImage(grass, 160, 112);
	            }
	        }.start();

	        theStage.show();
	        
	    
        }
	 * 
	 * */
	 
}