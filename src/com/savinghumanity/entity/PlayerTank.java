package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;

import com.savinghumanity.file.FileManager;

public class PlayerTank extends Tank {

	private boolean canMove;
	private boolean canFire;
	private boolean invincible;
	private boolean isConfused;

	/**
	 * 
	 * @param xPos
	 * @param yPos
	 */
	public PlayerTank(int corX, int corY,boolean isalive, int firstSpeed, int firstRange, int firstDamage, int firstHealth, int firstX, int firstY, boolean movable, boolean firable, boolean invince , boolean isConfused){
		super(corX, corY,isalive);
		firstSpeed = speed;
		firstRange = fireRange;
		firstDamage = damage;
		firstHealth = health;
		firstX = speedX;
		firstY = speedY;
		canMove = movable;
		canFire = firable;
		invincible = invince;
		this.isConfused = isConfused;
		//Initialize Right Tank Animation
		BufferedImage temp;
		
		temp = FileManager.getEntitySprite().getSubimage(6 * 16, 0 * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.rTank[0] = (SwingFXUtils.toFXImage(temp, null));

		temp = FileManager.getEntitySprite().getSubimage(7 * 16, 0 * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.rTank[1] = (SwingFXUtils.toFXImage(temp, null));
		//Initialize Down Tank Animation
		temp = FileManager.getEntitySprite().getSubimage(4 * 16, 0 * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.dTank[0] = (SwingFXUtils.toFXImage(temp, null));

		temp = FileManager.getEntitySprite().getSubimage(5 * 16, 0 * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.dTank[1] = (SwingFXUtils.toFXImage(temp, null));
		//Initialize Left Tank Animation
		temp = FileManager.getEntitySprite().getSubimage(2 * 16, 0 * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.lTank[0] = (SwingFXUtils.toFXImage(temp, null));

		temp = FileManager.getEntitySprite().getSubimage(3 * 16, 0 * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.lTank[1] = (SwingFXUtils.toFXImage(temp, null));
		//Initialize Up Tank Animation
		temp = FileManager.getEntitySprite().getSubimage(0 * 16, 0 * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.uTank[0] = (SwingFXUtils.toFXImage(temp, null));

		temp = FileManager.getEntitySprite().getSubimage(1 * 16, 0 * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.uTank[1] = (SwingFXUtils.toFXImage(temp, null));

		//Initially looking upwards
		this.image = uTank[0];



	}
	@Override
	public void fire(){
		if(canFire)
			super.fire();
		return;
	}
	public void neutralize(){
		//TODO -- implement neutralize() method
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


	public boolean isConfused() {
		return isConfused;
	}


	public void setConfused(boolean isConfused) {
		this.isConfused = isConfused;
	}



}