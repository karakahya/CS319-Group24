package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

public class Increase extends Bonus {

	public Increase(int posX, int posY, boolean isalive,IncreaseBonusType type , long duration , long start) {
		super(posX, posY , isalive , duration , start);
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	private IncreaseBonusType type;

	public void effect(PlayerTank pt) {
		switch(type){
		case INCREASE_FIRE_RANGE:
			pt.setFireRange(pt.getFireRange() + 5);
			break;
		case INCREASE_SPEED:
			pt.setSpeed(pt.getSpeed() + 5);
			break;
		case INCREASE_DAMAGE:
			pt.setDamage(pt.getDamage() + 5);
			break;
		case INCREASE_HEALTH:
			pt.setHealth(pt.getHealth() + 5);
			break;
		}
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}

}