package com.savinghumanity.entity;

import java.awt.Color;
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



	public void update(){
            posX += speedX;
            posY += speedY;
        }


	/**
	 * 
	 * @param obj other game object that is checked whether this object is collided
	 */
	public boolean collisionCheck(GameObject obj) {
                Rectangle rectOne = null;
                Rectangle rectTwo = null;
                if(this instanceof Bullet ){
                    rectOne = new Rectangle((int)(posX + speedX) , (int)(posY + speedY), 14, 14);
                }
                else{
                    rectOne = new Rectangle((int)(posX + speedX) , (int)(posY + speedY), 30, 30);
                }
                if(obj instanceof Bullet){
                    rectTwo = new Rectangle((int)(obj.getPosX() + obj.getSpeedX()) , (int)(obj.getPosY() + obj.getSpeedY()), 14, 14);
                }
                else{
                    rectTwo = new Rectangle((int)(obj.getPosX() + obj.getSpeedX()) , (int)(obj.getPosY() + obj.getSpeedY()) , 30 , 30);

                }
		
		
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
		Color bGroundColor = new Color(0.0f,0.0f,1.0f);
		if(sbi != null) {
			dbi = new BufferedImage(dWidth, dHeight,imageType);
			Graphics2D g = dbi.createGraphics();
			AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
			g.drawRenderedImage(sbi, at);
			g.setBackground(bGroundColor);
		}
		filterBackgroundTransparency(dbi);
		return dbi;
	}
	/**
	 * Basically convert non-alpha image with background to transparent image by filtering black pixels. Only works with black backgrounds!
	 * @param oldImage
	 */
	public static void filterBackgroundTransparency(BufferedImage oldImage){
		for(int i = 0 ; i < oldImage.getWidth() ; i++){
			for(int j = 0 ; j < oldImage.getHeight() ; j++){
				
				if(oldImage.getRGB(i, j) == -16777215){ // Black pixel with 0 transparency
					
					oldImage.setRGB(i, j, 0x00000000); // Full transparent
				}
					
			}
		}
		
	}
	/**
	 * Takes a bufferedImage and filters the background by taking the upperleft pixel as sample and filter each pixel with same values.
	 * @param oldImage
	 */
	public static void filterImageGeneral(BufferedImage oldImage){
		int samplePixel = oldImage.getRGB(0, 0);
		for(int i = 0 ; i < oldImage.getWidth() ; i++){
			for(int j = 0 ; j < oldImage.getHeight() ; j++){
				if(oldImage.getRGB(i,j) == samplePixel)
					oldImage.setRGB(i, j, 0x00000000); // Full transparent
			}
			
		}
	}

	public Image getImage(){
		return image;
	}

        public void setImage(Image image) {
            this.image = image;
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