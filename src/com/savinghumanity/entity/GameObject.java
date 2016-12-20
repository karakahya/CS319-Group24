package com.savinghumanity.entity;

import java.awt.image.BufferedImage;
public class GameObject {

	public int posX;
	public int posY;
	public BufferedImage image;
	public boolean isAlive;
	
	public GameObject(int corX, int corY){
		posX = corX;
		posY = corY;
		isAlive = true;
		
		
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public boolean getAlive() {
		return isAlive;
	}
	
	public void setIsAlive(boolean live) {
		isAlive = live;
	}
	
	public void update(GameObject obj1, int x, int y) {
		setPosX(obj1.getPosX()+x);
		setPosY(obj1.getPosY()+y);
		
		
	}
	
	
	
	public boolean colissionCheck(GameObject obj1,GameObject obj2 ) {
		
		if(obj1.getPosX() < obj2.getPosX()){
			if((obj1.getPosX()+16) < obj2.getPosX() )
				if(obj1.getPosY() < obj2.getPosY()){
					if((obj1.getPosY()+16) < obj2.getPosY() )
						return false;
					else if((obj2.getPosY()+16) < obj1.getPosY() )
						return false;
					 else 
						return true;
			}
		
		}
		else if((obj2.getPosX()+16) < obj1.getPosX()){
			   if(obj1.getPosY() < obj2.getPosY()){
				   if((obj1.getPosY()+16) < obj2.getPosY() )
					   return false;
				   else if((obj2.getPosY()+16) < obj1.getPosY())
							return false;
					 	else 
					 		return true;
				
		}
		}
	}
}
