package com.savinghumanity.input;



public class InputManager {

	private static boolean[] buttonsPressed;
	private InputManager theInputManager;
	

	public boolean[] getButtonsPressed() {
		return this.buttonsPressed;
	}

	/**
	 * Saves the pressed buttons in an array. 
	 * @param buttonsPressed
	 */
	public void setButtonsPressed(boolean[] buttonsPressed) {
		this.buttonsPressed = buttonsPressed;
	}

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
		 * Q for sound on-off , P for pause and continue , so in total 22 keys are defined
		 */ 
		buttonsPressed = new boolean[22]; // 22 keys specified above 
		
	}


}