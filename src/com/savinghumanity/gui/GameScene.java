/*
package com.savinghumanity.gui;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class GameScene extends Scene {

	public GameScene(Parent arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
}
*/

import java.io.File;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
 
public class GameScene extends Application 
{
    public static void main(String[] args) 
    {
        launch(args);
    }
 
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
}
