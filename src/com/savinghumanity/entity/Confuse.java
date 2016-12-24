package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

public class Confuse extends Bonus {

	public Confuse(float posX, float posY,  boolean isalive,long duration, long start) {
		super(posX, posY, isalive , duration , start);
		
	}

	public void effect(PlayerTank pt) {
		pt.setConfused(true);
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}

}