package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

import com.savinghumanity.gamelogic.GameEngine;

import javafx.scene.image.Image;

public abstract class Tank extends GameObject implements Animation {

	protected int speed;
	protected int fireRange;
	protected int damage;
	protected int health;
	protected Image[] lTank;
	protected Image[] uTank;
	protected Image[] rTank;
	protected Image[] dTank;
	protected boolean isMoving;
	protected MoveDirection direction;
	protected short animationFrame;
	protected Bullet currentBullet;
	
	public Tank(float posX,float posY,boolean isalive) {
		super(posX,posY,isalive);
		
		lTank = new Image[2];
		uTank = new Image[2];
		rTank = new Image[2];
		dTank = new Image[2];
		isMoving = false;
		animationFrame = 0;
		direction = MoveDirection.UP;
		currentBullet = null;
		
	}
	public void alternateAnimation() {
		if(direction == MoveDirection.UP && animationFrame == 0)
			this.image = uTank[1];
		else if(direction == MoveDirection.UP && animationFrame == 1)
			this.image = uTank[0];
		else if(direction == MoveDirection.RIGHT && animationFrame == 0)
			this.image = rTank[1];
		else if(direction == MoveDirection.RIGHT && animationFrame == 1)
			this.image = rTank[0];
		else if(direction == MoveDirection.DOWN && animationFrame == 0)
			this.image = dTank[1];
		else if(direction == MoveDirection.DOWN && animationFrame == 1)
			this.image = dTank[0];
		else if(direction == MoveDirection.LEFT && animationFrame == 0)
			this.image = lTank[1];
		else if(direction == MoveDirection.LEFT && animationFrame == 1)
			this.image = lTank[0];
		
		if(animationFrame == 0)
			animationFrame = 1;
		else	
			animationFrame = 0;
	}

	public void update() {
		posX += speedX;
		posY += speedY;
	}

	public void fire() {
		
		Bullet bullet = new Bullet(getPosX() , getPosY(), true, direction ,fireRange , damage);
		GameEngine.getAllObjects().add(bullet);
		GameEngine.getBulletList().add(bullet);
		currentBullet = bullet;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getFireRange() {
		return fireRange;
	}

	public void setFireRange(int fireRange) {
		this.fireRange = fireRange;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public Image[] getlTank() {
		return lTank;
	}

	public void setlTank(Image[] lTank) {
		this.lTank = lTank;
	}

	public Image[] getuTank() {
		return uTank;
	}

	public void setuTank(Image[] uTank) {
		this.uTank = uTank;
	}

	public Image[] getrTank() {
		return rTank;
	}

	public void setrTank(Image[] rTank) {
		this.rTank = rTank;
	}

	public Image[] getdTank() {
		return dTank;
	}

	public void setdTank(Image[] dTank) {
		this.dTank = dTank;
	}
	

}