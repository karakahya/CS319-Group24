package com.savinghumanity.fx;
import com.savinghumanity.entity.GameObject;

import javafx.scene.image.Image;
public class FXFactory {
	private Effect boomEffect1;
	private Effect boomEffect2;
	/**
	 * FXFactory creates high res effects once and than just copy images to the newly created effects
	 * so that program does not need to read effect files each time a new effect is created
	 */
	public FXFactory(){
		boomEffect1 = new Effect(0,0,(short)64, (short)5, 1, null , "Data/FX/boom3.png","boom",0,0,0,0,8,8,128,128);
		boomEffect2 = new Effect(0,0,(short)42, (short)5, 1, null , "Data/FX/boom4.png","boom",0,0,0,0,7,6,130,130);
		
	}
	
	public Effect createBoomEffect1(float posX, float posY, GameObject attachedObject,
			short transitionTime, int maxLifeCycle, int padX, int padY){
		Effect newBoom = new Effect(posX,posY);
		newBoom.setAttachedObject(attachedObject);
		newBoom.setTransitionTime(transitionTime);
		newBoom.setMaxLifeCycle(maxLifeCycle);
		newBoom.setPadX(padX);
		newBoom.setPadY(padY);
		newBoom.setPosX(posX + padX);
		newBoom.setPosY(posY + padY);
		newBoom.setEffectSize((short)64);
		newBoom.setEffectFrames(new Image[64]);
		newBoom.setEffectFrames(boomEffect1.getEffectFrames());
		return newBoom;
	}
	public Effect createBoomEffect2(float posX, float posY, GameObject attachedObject,
			short transitionTime, int maxLifeCycle, int padX, int padY){
		Effect newBoom = new Effect(posX,posY);
		newBoom.setAttachedObject(attachedObject);
		newBoom.setTransitionTime(transitionTime);
		newBoom.setMaxLifeCycle(maxLifeCycle);
		newBoom.setPadX(padX);
		newBoom.setPadY(padY);
		newBoom.setPosX(posX + padX);
		newBoom.setPosY(posY + padY);
		newBoom.setEffectSize((short)42);
		newBoom.setEffectFrames(new Image[42]);
		newBoom.setEffectFrames(boomEffect2.getEffectFrames());
		return newBoom;
	}

}
