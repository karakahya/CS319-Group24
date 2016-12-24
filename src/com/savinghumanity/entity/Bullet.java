package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

import com.savinghumanity.file.FileManager;
import com.savinghumanity.gamelogic.GameEngine;

import javafx.embed.swing.SwingFXUtils;

public class Bullet extends GameObject {

	private float destinationX;
	private float destinationY;
	private MoveDirection direction;
	private int damage;
        private Tank bulletOwner;
        private float range;
	public Bullet(float xPos, float yPos ,  boolean isalive, MoveDirection direction , float range, int damage , Tank ownerTank) {
		super(xPos,yPos , isalive);
                this.bulletOwner = ownerTank;
		this.damage = damage;
                this.range = range;
		BufferedImage temp = null;
		
		switch(direction){
		case UP:
                       
			speedX = 0.0f;
			speedY = -5f;
			destinationX = xPos;
			destinationY = yPos - range;
			temp = FileManager.getEntitySprite().getSubimage(20 * 16, 6 * 16 + 4, 8, 8); 
			
			break;
		case RIGHT:
			speedX = 5f;
			speedY = 0.0f;
			destinationX = xPos + range;
			destinationY = yPos;
			temp = FileManager.getEntitySprite().getSubimage(20 * 16 + 24, 6 * 16 + 4, 8, 16); 
			break;
		case DOWN:
			speedX = 0;
			speedY = 5f;
			destinationX = 0;
			destinationY = yPos + range;
			temp = FileManager.getEntitySprite().getSubimage(20 * 16 + 16, 6 * 16 + 4, 8, 16); 
			break;
		case LEFT:
			speedX = -5f;
			speedY = 0;
			destinationX = xPos - range;
			destinationY = yPos;
			temp = FileManager.getEntitySprite().getSubimage(20 * 16 + 8, 6 * 16 + 4, 8, 16); 
			break;
			
		}
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 16, 16, 2, 2);
		this.image = (SwingFXUtils.toFXImage(temp, null));
		
	}
        public Tank getOwner(){
            return bulletOwner;
        }
        public int getDamage(){
            return damage;
        }
        public float getRange(){
            return range;
        }
        public void updateBullet(){
            range -= Math.abs(speedX + speedY);
           
        }

}