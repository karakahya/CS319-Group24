package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

public abstract class Bonus extends GameObject {

	public Bonus(int posX, int posY, boolean isalive,long duration, long start) {
		super(posX, posY,isalive);
		durationTime = duration;
		startingTime = start;
		
	}

	private long durationTime;
	private long startingTime;

	public abstract void effect(PlayerTank pt);

}