package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

public class Immortality extends Bonus {

	
	public Immortality(int corX, int corY, boolean isalive, int duration, int start){
		
	
		super(corX,corY,isalive,duration,start);
}
	
	public void effect(int size, EnemyTank[] army){
		
		for(int i=0;i<size;i++){
			army[i].setDamage(0);
		}
		
		
	}
}



	public void update() {
		// TODO Auto-generated method stub
		
	}

}
