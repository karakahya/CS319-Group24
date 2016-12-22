package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

import com.savinghumanity.file.FileManager;

import javafx.embed.swing.SwingFXUtils;

public class Bullet extends GameObject {

	private float destinationX;
	private float destinationY;
	private MoveDirection direction;
	private int damage;
	public Bullet(float xPos, float yPos ,  boolean isalive, MoveDirection direction , int range, int damage) {
		super(xPos,yPos , isalive);
		this.damage = damage;
		BufferedImage temp = null;
		
		switch(direction){
		case UP:
			speedX = 0.0f;
			speedY = -0.1f;
			destinationX = xPos;
			destinationY = yPos - range;
			temp = FileManager.getEntitySprite().getSubimage(20 * 16, 6 * 16, 8, 8); 
			
			break;
		case RIGHT:
			speedX = 0.1f;
			speedY = 0.0f;
			destinationX = xPos + range;
			destinationY = yPos;
			temp = FileManager.getEntitySprite().getSubimage(20 * 16 + 24, 6 * 16, 8, 8); 
			break;
		case DOWN:
			speedX = 0;
			speedY = 0.1f;
			destinationX = 0;
			destinationY = yPos + range;
			temp = FileManager.getEntitySprite().getSubimage(20 * 16 + 16, 6 * 16, 8, 8); 
			break;
		case LEFT:
			speedX = -0.1f;
			speedY = 0;
			destinationX = xPos - range;
			destinationY = yPos;
			temp = FileManager.getEntitySprite().getSubimage(20 * 16 + 8, 6 * 16, 8, 8); 
			break;
			
		}
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 16, 16, 2, 2);
		this.image = (SwingFXUtils.toFXImage(temp, null));
		
	}

	public void update() {
		// TODO - implement Bullet.update
		throw new UnsupportedOperationException();
	}

}