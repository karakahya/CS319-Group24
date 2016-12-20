package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

public class Broken extends Bonus {

	public Broken(int corX, int corY, boolean isalive, int duration, int start){
			
			
			super(corX,corY,isalive,duration,start);
	}
		
	public void effect(PlayerTank pt){
			
			pt.setcanFire(false);
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}

}
