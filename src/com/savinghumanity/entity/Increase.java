package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

public class Increase extends Bonus {

	public Increase(int posX, int posY,IncreaseBonusType type) {
		super(posX, posY);
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	private IncreaseBonusType type;

	public void effect() {
		// TODO - implement Increase.effect
		throw new UnsupportedOperationException();
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}

}