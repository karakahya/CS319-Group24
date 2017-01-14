package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;

import com.savinghumanity.file.FileManager;
import com.savinghumanity.gamelogic.GameEngine;


public class EnemyTank extends Tank {

	// Enemy Tank level,
	// type = 0 --- level = 0
	// 1 -- level 1
	// 2 -- level 2
	private int type;
	private float distanceLeft;
	private long shootTime;
	private long minShootInterval; // in ms

	/**
	 * 
	 * @param xPos
	 * @param yPos
	 * @param type
	 */
	public EnemyTank(float xPos, float yPos,boolean isalive, int type) {
		super(xPos,yPos,isalive);
		BufferedImage temp;
		this.type = type;
		distanceLeft = 0;
		shootTime = 0;
		if(this.type==0){
			speed = 1;
			this.minShootInterval = 1000;
			this.fireRange = 300.0f;
			this.damage = 10;
			this.health = 10;
			this.speedX = 0.0f;
			this.speedY = 0.0f;


		}
		else if(this.type==1){
			this.minShootInterval = 750;
			this.speed = 1;
			this.fireRange = 300.0f;
			this.damage = 20;
			this.health = 20;
			this.speedX = 0.0f;
			this.speedY = 0.0f;


		}

		else if(this.type==2){
			this.minShootInterval = 500;
			this.speed = 1;
			this.fireRange = 300.0f;
			this.damage = 30;
			this.health = 30;
			this.speedX = 0.0f;
			this.speedY = 0.0f;


		}
		//Initialize Right Tank Animation
		temp = FileManager.getEntitySprite().getSubimage(14 * 16, (type+1) * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_4BYTE_ABGR, 32, 32, 2, 2);
		this.rTank[0] = (SwingFXUtils.toFXImage(temp, null));

		temp = FileManager.getEntitySprite().getSubimage(15 * 16, (type+1) * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_4BYTE_ABGR, 32, 32, 2, 2);
		this.rTank[1] = (SwingFXUtils.toFXImage(temp, null));
		//Initialize Down Tank Animation
		temp = FileManager.getEntitySprite().getSubimage(12 * 16, (type+1) * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_4BYTE_ABGR, 32, 32, 2, 2);
		this.dTank[0] = (SwingFXUtils.toFXImage(temp, null));

		temp = FileManager.getEntitySprite().getSubimage(13 * 16, (type+1) * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_4BYTE_ABGR, 32, 32, 2, 2);
		this.dTank[1] = (SwingFXUtils.toFXImage(temp, null));
		//Initialize Left Tank Animation
		temp = FileManager.getEntitySprite().getSubimage(10 * 16, (type+1) * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_4BYTE_ABGR, 32, 32, 2, 2);
		this.lTank[0] = (SwingFXUtils.toFXImage(temp, null));

		temp = FileManager.getEntitySprite().getSubimage(11 * 16, (type+1) * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_4BYTE_ABGR, 32, 32, 2, 2);
		this.lTank[1] = (SwingFXUtils.toFXImage(temp, null));
		//Initialize Up Tank Animation
		temp = FileManager.getEntitySprite().getSubimage(8 * 16, (type+1) * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_4BYTE_ABGR, 32, 32, 2, 2);
		this.uTank[0] = (SwingFXUtils.toFXImage(temp, null));

		temp = FileManager.getEntitySprite().getSubimage(9 * 16, (type+1) * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_4BYTE_ABGR, 32, 32, 2, 2);
		this.uTank[1] = (SwingFXUtils.toFXImage(temp, null));

		//Initially looking downwards
		this.image = dTank[0];
		this.direction = MoveDirection.DOWN;


	}

	public float getDistanceLeft() {
		return distanceLeft;
	}

	public void setDistanceLeft(float distanceLeft) {
		this.distanceLeft = distanceLeft;
	}
	/**
	 * Checks the first object it will hit, if it is an enemy tank, dont fire
	 * @return
	 */
	private boolean checkDistance(){
		GameObject instanceOfClosest = null;
		float closestDistance = fireRange;
		for(int i = 0 ; i < GameEngine.getInstance().getAllObjects().size(); i++){
			if(this == GameEngine.getInstance().getAllObjects().get(i)) continue;
			if( GameEngine.getInstance().getAllObjects().get(i) instanceof Bullet) continue;
			switch(direction){
			case UP:
				if(Math.abs(posX - GameEngine.getInstance().getAllObjects().get(i).posX) > 42) continue;
				if(posY - GameEngine.getInstance().getAllObjects().get(i).posY > 0 && Math.abs(posY - GameEngine.getInstance().getAllObjects().get(i).posY) < closestDistance){
					instanceOfClosest = GameEngine.getInstance().getAllObjects().get(i);
					closestDistance = Math.abs(posY - instanceOfClosest.posY);
				}
				break;
			case RIGHT:
				if(Math.abs(posY - GameEngine.getInstance().getAllObjects().get(i).posY) > 42) continue;
				if(posX - GameEngine.getInstance().getAllObjects().get(i).posX < 0 && Math.abs(posX - GameEngine.getInstance().getAllObjects().get(i).posX) < closestDistance){
					instanceOfClosest = GameEngine.getInstance().getAllObjects().get(i);
					closestDistance = Math.abs(posX - instanceOfClosest.posX);
				}
				break;
			case DOWN:
				if(Math.abs(posX - GameEngine.getInstance().getAllObjects().get(i).posX) > 42) continue;
				if(posY - GameEngine.getInstance().getAllObjects().get(i).posY < 0 && Math.abs(posY - GameEngine.getInstance().getAllObjects().get(i).posY) < closestDistance){
					instanceOfClosest = GameEngine.getInstance().getAllObjects().get(i);
					closestDistance = Math.abs(posY - instanceOfClosest.posY);
				}
				break;
			case LEFT:
				if(Math.abs(posY - GameEngine.getInstance().getAllObjects().get(i).posY) > 42) continue;
				if(posX - GameEngine.getInstance().getAllObjects().get(i).posX > 0 && Math.abs(posX - GameEngine.getInstance().getAllObjects().get(i).posX) < closestDistance){
					instanceOfClosest = GameEngine.getInstance().getAllObjects().get(i);
					closestDistance = Math.abs(posX - instanceOfClosest.posX);
				}
				break;
			}

		}

		if(!(instanceOfClosest instanceof EnemyTank) && Math.abs(closestDistance) < fireRange )
			return true;
		return false;
	}

	public void findAPath(PlayerTank pt){
		if(checkDistance() &&  (System.nanoTime() - shootTime) >= (minShootInterval * 1000000.0)){
			fire();
			shootTime = System.nanoTime();
			
		}
		if(distanceLeft > 0){
			distanceLeft -= speed;
			return;
		}
		float xDifference = pt.getPosX() - posX;
		float yDifference = pt.getPosY() - posY;
		distanceLeft = 32;
		speedX = 0.0f;
		speedY = 0.0f;
		double random = Math.random();
		if(random < 0.75){
			if(Math.abs(xDifference) < Math.abs(yDifference)){
				if(yDifference > 0)
					speedY = speed;
				else
					speedY = -speed;
			}
			else{
				if(xDifference > 0)
					speedX = speed;
				else
					speedX = -speed;
			}
		}
		else{
			random = Math.random();
			if(random < 0.25)
				speedY = -speed;
			else if(0.25 <= random && random < 0.5)
				speedY = speed;
			else if(0.5 <= random && random < 0.75 )
				speedX = -speed;
			else
				speedX = speed;
		}
		if(speedX > 0){
			direction = MoveDirection.RIGHT;
			setImage( rTank[0]);
		}
		else if(speedX < 0){
			direction = MoveDirection.LEFT;
			setImage( lTank[0]);
		}
		else if(speedY < 0){
			direction = MoveDirection.UP;
			setImage( uTank[0]);
		}
		else if(speedY > 0){
			direction = MoveDirection.DOWN;
			setImage( dTank[0]);
		}



	}

	public void checkDestination(PlayerTank pt){
		if( (Math.abs(getPosX() - pt.getPosX()) <= fireRange) && (getPosX()== pt.getPosX()  || getPosY() == pt.getPosY()))
			fire();

	}


}