package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

import com.savinghumanity.gamelogic.GameEngine;

import javafx.scene.image.Image;

public abstract class Tank extends GameObject implements Animation {

	protected float speed;
	protected float fireRange;
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
            if(speedX == 0 && speedY == 0) return;
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


	public void fire() {
		if(currentBullet != null) return;
                if(this instanceof PlayerTank && !((PlayerTank)this).isCanFire() ) return;
                
		Bullet bullet = new Bullet(getPosX() , getPosY(), true, direction ,fireRange , damage,this);
		GameEngine.getInstance().getAllObjects().add(bullet);
		GameEngine.getBulletList().add(bullet);
		currentBullet = bullet;
	}
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getFireRange() {
		return fireRange;
	}

	public void setFireRange(float fireRange) {
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
        
        public void setMoveDirection(MoveDirection direction){
            this.direction = direction;
        }
        public MoveDirection getMoveDirection(){
            return this.direction;
        }
        public Bullet getBullet(){
            return currentBullet;
        }
        public void setBullet(Bullet value){
            currentBullet = value;
        }
	

}