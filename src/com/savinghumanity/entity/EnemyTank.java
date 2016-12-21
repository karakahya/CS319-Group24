package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;

import com.savinghumanity.file.FileManager;


public class EnemyTank extends Tank {

	// Enemy Tank level,
	// type = 0 --- level = 0
	// 1 -- level 1
	// 2 -- level 2
	private int type;

	/**
	 * 
	 * @param xPos
	 * @param yPos
	 * @param type
	 */
	public EnemyTank(int xPos, int yPos,boolean isalive, int type) {
		super(xPos,yPos,isalive);
		BufferedImage temp;
		this.type = type;
		if(this.type==0){
			this.speed = 10;
			this.fireRange = 10;
			this.damage = 10;
			this.health = 10;
			this.speedX = 10;
			this.speedY = 10;


		}
		else if(this.type==1){
			this.speed = 20;
			this.fireRange = 20;
			this.damage = 20;
			this.health = 20;
			this.speedX = 20;
			this.speedY = 20;


		}

		else if(this.type==2){
			this.speed = 20;
			this.fireRange = 20;
			this.damage = 20;
			this.health = 20;
			this.speedX = 20;
			this.speedY = 20;


		}
		//Initialize Right Tank Animation
		temp = FileManager.getEntitySprite().getSubimage(14 * 16, (type+1) * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.rTank[0] = (SwingFXUtils.toFXImage(temp, null));

		temp = FileManager.getEntitySprite().getSubimage(15 * 16, (type+1) * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.rTank[1] = (SwingFXUtils.toFXImage(temp, null));
		//Initialize Down Tank Animation
		temp = FileManager.getEntitySprite().getSubimage(12 * 16, (type+1) * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.dTank[0] = (SwingFXUtils.toFXImage(temp, null));

		temp = FileManager.getEntitySprite().getSubimage(13 * 16, (type+1) * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.dTank[1] = (SwingFXUtils.toFXImage(temp, null));
		//Initialize Left Tank Animation
		temp = FileManager.getEntitySprite().getSubimage(10 * 16, (type+1) * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.lTank[0] = (SwingFXUtils.toFXImage(temp, null));

		temp = FileManager.getEntitySprite().getSubimage(11 * 16, (type+1) * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.lTank[1] = (SwingFXUtils.toFXImage(temp, null));
		//Initialize Up Tank Animation
		temp = FileManager.getEntitySprite().getSubimage(8 * 16, (type+1) * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.uTank[0] = (SwingFXUtils.toFXImage(temp, null));

		temp = FileManager.getEntitySprite().getSubimage(9 * 16, (type+1) * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.uTank[1] = (SwingFXUtils.toFXImage(temp, null));

		//Initially looking downwards
		this.image = dTank[0];
		this.direction = MoveDirection.DOWN;


	}

	public void findAPath(){
		//TODO - implement findAPath

	}

	public void checkDestination(PlayerTank pt){
		if( (Math.abs(getPosX() - pt.getPosX()) <= fireRange) && (getPosX()== pt.getPosX()  || getPosY() == pt.getPosY()))
			fire();

	}


}