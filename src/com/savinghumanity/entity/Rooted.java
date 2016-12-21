package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

public class Rooted extends Bonus {

	public Rooted(int posX, int posY , boolean isalive,long duration, long start) {
		super(posX, posY , isalive , duration , start);
		
	}

	public void effect(PlayerTank pt) {
		pt.setcanMove(false);
	}

	
	public void update() {
		// TODO Auto-generated method stub
		
	}

}