package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

public class Immortality extends Bonus {

	public Immortality(float posX, float posY, boolean isalive , long duration, long start) {
		super(posX, posY, isalive , duration, start);
		// TODO Auto-generated constructor stub
	}

	public void effect(PlayerTank pt) {
		pt.setinvincible(true);
	}

	public void update() {
		
		
	}

}