package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

public class Broken extends Bonus {

	public Broken(float posX, float posY, boolean isalive , long duration, long start) {
		super(posX, posY, isalive , duration , start);
		// TODO Auto-generated constructor stub
	}

	public void effect(PlayerTank pt) {
		pt.setcanFire(false);
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}

}