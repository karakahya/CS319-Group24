package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

public class Bullet extends GameObject {
	
	public int speedX;
	public int speedY;
	public int destinationX;
	public int destinationY;
	
	public Bullet(int corX, int corY, boolean isalive, int vX, int vY, int firstX, int firstY ){
		
		super(corX, corY, isalive);
		speedX = vX;
		speedY = vY;
		destinationX = firstX;
		destinationY = firstY;
		
		
		
	}
	
	public update(){
		
		
	}
	
	

}
