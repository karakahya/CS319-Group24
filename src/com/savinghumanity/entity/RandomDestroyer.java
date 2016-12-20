package com.savinghumanity.entity;
import java.util.Random;
import java.awt.image.BufferedImage;

public class RandomDestroyer extends Bonus {

	public RandomDestroyer(int corX, int corY, boolean isalive, int duration, int start){
		
		super(corX,corY,isalive,duration,start);
}
	
	public void effect(int size, EnemyTank[] army){
		Random random = new Random();
		int lucky = random.nextInt(size);
		
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
