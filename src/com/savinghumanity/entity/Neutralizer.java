package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

public class Neutralizer extends Bonus {

	public Neutralizer(int posX, int posY , boolean isalive,long duration, long start) {
		super(posX, posY , isalive , duration , start);
		// TODO Auto-generated constructor stub
	}

	public void effect(PlayerTank pt) {
		pt.neutralize();
	}

	
	public void update() {
		// TODO Auto-generated method stub
		
	}

}