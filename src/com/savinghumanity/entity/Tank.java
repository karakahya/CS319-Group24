package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

public abstract class Tank extends GameObject {

	private int speed;
	private int fireRange;
	private int damage;
	private int health;
	private int speedX;
	private int speedY;

	public Tank(int posX,int posY) {
		super(posX,posY);
		// TODO - implement Tank.Tank
	}

	public void update() {
		// TODO - implement Tank.update
		throw new UnsupportedOperationException();
	}

	public void fire() {
		// TODO - implement Tank.fire
		throw new UnsupportedOperationException();
	}

}