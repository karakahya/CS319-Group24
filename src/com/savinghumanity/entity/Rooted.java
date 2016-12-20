package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

public class Rooted extends Bonus {

	public Rooted(int corX, int corY, boolean isalive, int duration, int start){
		
		
		super(corX,corY,isalive,duration,start);
}
	
	public void effect(PlayerTank pt){
		
		
		pt.setcanMove(false);
		
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
