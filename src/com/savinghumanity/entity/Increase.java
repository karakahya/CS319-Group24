package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

public class Increase extends Bonus {

	public Increase(float posX, float posY, boolean isalive,IncreaseBonusType type , long duration , long start) {
		super(posX, posY , isalive , duration , start);
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	private IncreaseBonusType type;

	public void effect(PlayerTank pt) {
		switch(type){
		case INCREASE_FIRE_RANGE:
			pt.setFireRange(pt.getFireRange() + 200.0f);
			break;
		case INCREASE_SPEED:
			pt.setSpeed(pt.getSpeed() + 1.0f);
			break;
		case INCREASE_DAMAGE:
			pt.setDamage((int)(pt.getDamage() * 1.5));
			break;
		case INCREASE_HEALTH:
			pt.setHealth(pt.getHealth() + 25);
			break;
		}
	}
	public IncreaseBonusType getType(){
		return type;
	}
	

}