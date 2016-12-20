package com.savinghumanity.entity;

import java.awt.image.BufferedImage;


public class EnemyTank extends Tank{

	public int type;
	
	public EnemyTank(int corX, int corY, boolean isalive, int typeEnemy){
		posX = corX;
		posY = corY;
		isAlive = isalive;
		type = typeEnemy;
		
		if(type==1){
			speed = 10;
			fireRange = 10;
			damage = 10;
			health = 10;
			speedX = 10;
			speedY = 10;
			
			
		}
		else if(type==2){
			speed = 20;
			fireRange = 20;
			damage = 20;
			health = 20;
			speedX = 20;
			speedY = 20;
			
			
		}
		
		else if(type==3){
			speed = 20;
			fireRange = 20;
			damage = 20;
			health = 20;
			speedX = 20;
			speedY = 20;
			
	
		}
		
		
		
		}
	
	public void findaPath(){
		
		
	}
	
	public void checkDestination(EnemyTank et, PlayerTank pt){
		if(et.getPosX()== pt.getPosX() || et.getPosY() == pt.getPosY())
			et.fire();
		
		
		
	}
	
	
}
