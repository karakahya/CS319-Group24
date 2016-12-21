package com.savinghumanity.entity;

import java.awt.image.BufferedImage;
import java.util.Random;

import com.savinghumanity.gamelogic.GameEngine;

public class RandomDestroyer extends Bonus {

	public RandomDestroyer(int posX, int posY , boolean isalive,long duration, long start) {
		super(posX, posY , isalive , duration , start);
		
	}

	public void effect(PlayerTank pt) {
		Random random = new Random();
		int lucky = random.nextInt(GameEngine.getEnemyTankList().size());
		GameEngine.destroyTank(lucky);
	}

	
	public void update() {
		// TODO Auto-generated method stub
		
	}

}