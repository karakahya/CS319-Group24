package com.savinghumanity.input;

import com.savinghumanity.entity.Bullet;

public class InputManager extends Bullet {

	private static boolean[] buttonsPressed;
	private InputManager theInputManage;

	public boolean[] getButtonsPressed() {
		return this.buttonsPressed;
	}

	/**
	 * 
	 * @param buttonsPressed
	 */
	public void setButtonsPressed(boolean[] buttonsPressed) {
		this.buttonsPressed = buttonsPressed;
	}

	public InputManager getInstance() {
		// TODO - implement InputManager.getInstance
		throw new UnsupportedOperationException();
	}

	private InputManager() {
		// TODO - implement InputManager.InputManager
		throw new UnsupportedOperationException();
	}

}