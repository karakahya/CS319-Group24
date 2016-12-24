package com.savinghumanity.input;

import com.savinghumanity.gamelogic.GameEngine;
import com.savinghumanity.gui.GameApplication;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import javafx.event.EventHandler;

public class InputManager {

	private static ArrayList<String> buttonsPressed;
	private static InputManager theInputManager;
	
	public static ArrayList<String> getButtonsPressed() {
		return buttonsPressed;
	}
	 	
	public static InputManager getInstance() {
		if(theInputManager == null)
			theInputManager = new InputManager();
		return theInputManager;
	}
	
	private InputManager() {
            buttonsPressed = new ArrayList<String>();
            
            GameApplication.getGameScene().setOnKeyPressed(
	        new EventHandler<KeyEvent>()
	        {
                        @Override
			public void handle(KeyEvent e)
	                {
	                	String code = e.getCode().toString();
	                    	GameEngine.getInstance().buttonPressed(code);
	                        	
	                }
                });
	        
            GameApplication.getGameScene().setOnKeyReleased(
	 	new EventHandler<KeyEvent>()
	        {
                        @Override
	                public void handle(KeyEvent e)
	                {
                                
	    	                String code = e.getCode().toString();
	        	        GameEngine.getInstance().buttonReleased(code);
	                }
                });
		/*
		 *----- Keys ---
		 * Player 1 ---- Move keys: w,a,s,d shoot: space
		 * Player 2 ---- Move keys: y,g,h,j shoot: m
		 * Player 3 ---- Move keys: p,l,þ,i shoot: .
		 * Player 4 ---- Move keys: 8,4,5,6(numpad) shoot: 0 (numpad)
		 * Q for sound on-off , P for pause and continue.
		 */  
		
	}


}