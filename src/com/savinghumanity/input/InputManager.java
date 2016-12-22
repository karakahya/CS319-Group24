package com.savinghumanity.input;

import javafx.scene.input.KeyEvent;
import java.util.ArrayList;

public class InputManager {

	ArrayList<String> buttonsPressed;
	private InputManager theInputManager;
	

	public ArrayList<String> getButtonsPressed() {
		return this.buttonsPressed;
	}
	 
	GameScene.getScene().setOnKeyPressed(
	        new EventHandler<KeyEvent>()
	        {
			public void handle(KeyEvent e)
	                {
	                	String code = e.getCode().toString();
	                    	if ( !input.contains(code) )
	                        	input.add( code );
	                }
	});
	        
	GameScene.getScene().setOnKeyReleased(
	 	new EventHandler<KeyEvent>()
	        {
	                public void handle(KeyEvent e)
	                {
	    	                String code = e.getCode().toString();
	        	        input.remove( code );
	                }
	});
	
	public InputManager getInstance() {
		if(theInputManager == null)
			theInputManager = new InputManager();
		return theInputManager;
	}
	
	private InputManager() {
		/*
		 *----- Keys ---
		 * Player 1 ---- Move keys: w,a,s,d shoot: space
		 * Player 2 ---- Move keys: y,g,h,j shoot: m
		 * Player 3 ---- Move keys: p,l,þ,i shoot: .
		 * Player 4 ---- Move keys: 8,4,5,6(numpad) shoot: 0 (numpad)
		 * Q for sound on-off , P for pause and continue.
		 */  
		buttonsPressed = new ArrayList<String>();
	}


}
