package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

public class Tank extends GameObject {
	public int speed;
	public int fireRange;
	public int damage;
	public int health;
	public int speedX;
	public int speedY;
	
	public Tank(int corX, int corY,  int firstSpeed, int firstRange, int firstDamage, int firstHealth, int firstX, int firstY) {
		super(corX, corY);
		speed = firstSpeed;
		fireRange = firstRange;
		damage = firstDamage;
		health = firstHealth;
		speedX = firstX;
		speedY = firstY;
		isAlive = true;
	}
	
	public void setSpeed(int value){
		
		speed = value;
	}

	public void setFireRange(int value){
		
		fireRange = value;
	}
	
	public void setDamage(int value){
		
		damage = value;
	}
	
	public void setHealth(int value){
		
		health = value;
	}
	
	public void setSpeedX(int value){
		
		speedX = value;
	}
	
	public void setSpeedY(int value){
		
		speedY = value;
	}
	
	
	
	
	public void update(){
		
		
	}
	
	public void fire(){
		Bullet bull = new Bullet();
		
		
	}
}
