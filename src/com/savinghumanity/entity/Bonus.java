package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

public abstract class Bonus extends GameObject {
	public int durationTime;
	public long startingTime;
	
	public Bonus(int corX, int corY, boolean isalive, int duration, int start) {
		super(corX, corY, isalive);
		durationTime = duration;
		startingTime = start;
		
		
	}

	public abstract void effect();

}
