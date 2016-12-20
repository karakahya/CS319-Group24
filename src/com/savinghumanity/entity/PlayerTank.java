package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

public class PlayerTank extends Tank {
	
	public boolean canMove;
	public boolean canFire;
	public boolean invincible;
	
	public PlayerTank(int corX, int corY, boolean isalive, int firstSpeed, int firstRange, int firstDamage, int firstHealth, int firstX, int firstY, boolean movable, boolean firable, boolean invince){
		super(corX, corY, isalive, firstSpeed, firstRange, firstDamage, firstHealth, firstX, firstY);
		canMove = movable;
		canFire = firable;
		invincible = invince;
		
		
	}
	
	
	public void setcanMove(boolean effect){
		canMove = effect;
	}

	public void setcanFire(boolean effect){
		canFire = effect;
	}

	public void setinvincible(boolean effect){
		invincible = effect;
	}

}
