package com.savinghumanity.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javafx.scene.image.Image;

public abstract class GameObject {

	protected float posX;
	protected float posY;
	protected float speedX;
	protected float speedY;
	
	protected Image image;
	protected boolean isAlive;

	public GameObject(float posX,float posY,boolean isAlive){
		this.posX = posX;
		this.posY = posY;
		speedX = 0.0f;
		speedY = 0.0f;
		this.isAlive = true; // Alive at the beginning
	}



	public abstract void update();


	/**
	 * 
	 * @param obj other game object that is checked whether this object is collided
	 */
	public boolean collisionCheck(GameObject obj) {
		Rectangle rectOne = new Rectangle((int)(posX + speedX) , (int)(posY + speedY), 32, 32);
		Rectangle rectTwo = new Rectangle((int)(obj.getPosX() + obj.getSpeedX()) , (int)(obj.getPosY() + obj.getSpeedY()) , 32 , 32);
		if(rectOne.intersects(rectTwo))
			return true;
		return false;
	}
	/**
	 * scale image
	 * 
	 * @param sbi image to scale
	 * @param imageType type of image
	 * @param dWidth width of destination image
	 * @param dHeight height of destination image
	 * @param fWidth x-factor for transformation / scaling
	 * @param fHeight y-factor for transformation / scaling
	 * @return scaled image
	 */
	public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
		BufferedImage dbi = null;
		if(sbi != null) {
			dbi = new BufferedImage(dWidth, dHeight,imageType);
			Graphics2D g = dbi.createGraphics();
			AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
			g.drawRenderedImage(sbi, at);
		}
		return dbi;
	}

	public Image getImage(){
		return image;
	}

	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}

	public float getSpeedY() {
		return speedY;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}


}