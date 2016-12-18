package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

public abstract class Bonus extends GameObject {

	public Bonus(int posX, int posY) {
		super(posX, posY);
		// TODO Auto-generated constructor stub
	}

	private int durationTime;
	private long startingTime;

	public abstract void effect();

}