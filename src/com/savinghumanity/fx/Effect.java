package com.savinghumanity.fx;

import com.savinghumanity.entity.GameObject;
import com.savinghumanity.file.FileManager;

import javafx.scene.image.Image;
/**
 * 
 * @author Oguzhan Karakahya
 * This class represents FX objects and treats them as game objects. They are not affected by collisions and can move
 * 
 */
public class Effect extends GameObject {
	
	private Image[] effectFrames; // Array of effect images
	private short currentFrame; // Current frame number
	private short effectSize; // Number of images in the effect
	private long transitionTime; // in milliseconds. Defines effect transition time. if 20 frames in an effect and transition is 10ms.

	private int maxLifeCycle; // How many times it will be rewinded before it disappears
	private int currentLifeCycle;
	private GameObject attachedObject; // The object in which the affect is attached.
	private long previousTime;
	private long passedTime;
	private String effectName;
	private int padX;
	private int padY;

	public Effect(float posX, float posY){
		super(posX,posY,true);
		image = null;
		currentLifeCycle = 0;
		previousTime = System.nanoTime();
		passedTime = 0;
		currentFrame = 0;
	}
	public Effect(float posX, float posY , short effectSize, short transitionTime, int maxLifeCycle,
			GameObject attachedObject,String path, String effectName, int padX, int padY) {
		super(posX, posY, true);
		this.effectSize = effectSize;
		this.transitionTime = transitionTime;
		this.maxLifeCycle = maxLifeCycle;
		this.attachedObject = attachedObject;
		this.effectName = effectName;
		this.padX = padX;
		this.padY = padY;
		posX += padX;
		posY += padY;
		effectFrames = new Image[effectSize];
		image = null;
		currentLifeCycle = 0;
		previousTime = System.nanoTime();
		passedTime = 0;
		currentFrame = 0;
		//Fill the image array, this method works if effect frames are at different files.
		effectFrames = FileManager.getInstance().getEffectArray(path, effectSize, "png",effectName );
	
		image = effectFrames[0];
	}
	public Effect(float posX, float posY , short effectSize, short transitionTime, int maxLifeCycle, 
					GameObject attachedObject,String path, String effectName, int padX, int padY,int startX, int startY,
					int rowCount, int columnCount, int xSize, int ySize) {
		super(posX, posY, true);
		this.effectSize = effectSize;
		this.transitionTime = transitionTime;
		this.maxLifeCycle = maxLifeCycle;
		this.attachedObject = attachedObject;
		this.effectName = effectName;
		this.padX = padX;
		this.padY = padY;
		posX += padX;
		posY += padY;
		effectFrames = new Image[effectSize];
		image = null;
		currentLifeCycle = 0;
		previousTime = System.nanoTime();
		passedTime = 0;
		currentFrame = 0;
		//Fill the image array, this method works if effect frames are at different files.
		effectFrames = FileManager.getInstance().getEffectArray(path, startX,startY,rowCount, columnCount,xSize , ySize );
	
		image = effectFrames[0];
	}
	
	@Override
	public void update(){
		super.update();
		//Attach effect to the object via synchronizing position with it
		if(attachedObject != null){
			posX = attachedObject.getPosX() + padX;
			posY = attachedObject.getPosY() + padY;
		}
		if((System.nanoTime() - previousTime + passedTime) / 1000000.0 > transitionTime){
			previousTime = System.nanoTime();
			passedTime = 0;
			if(currentFrame >= effectSize){
				currentFrame = 0;
				currentLifeCycle++;
				if(currentLifeCycle >= maxLifeCycle)
					isAlive = false;
				else
					image = effectFrames[currentFrame];
			}
			else{
				image = effectFrames[currentFrame];
				currentFrame++;
			}
		
		}
		else
			passedTime += System.nanoTime() - previousTime;
		
	}
	public Image[] getEffectFrames() {
		return effectFrames;
	}
	public void setEffectFrames(Image[] effectFrames) {
		this.effectFrames = effectFrames;
	}
	public short getCurrentFrame() {
		return currentFrame;
	}
	public void setCurrentFrame(short currentFrame) {
		this.currentFrame = currentFrame;
	}
	public short getEffectSize() {
		return effectSize;
	}
	public void setEffectSize(short effectSize) {
		this.effectSize = effectSize;
	}
	public long getTransitionTime() {
		return transitionTime;
	}
	public void setTransitionTime(long transitionTime) {
		this.transitionTime = transitionTime;
	}
	public Image getCurrentImage() {
		return image;
	}
	public void setCurrentImage(Image currentImage) {
		this.image = currentImage;
	}
	public int getMaxLifeCycle() {
		return maxLifeCycle;
	}
	public void setMaxLifeCycle(int maxLifeCycle) {
		this.maxLifeCycle = maxLifeCycle;
	}
	public int getCurrentLifeCycle() {
		return currentLifeCycle;
	}
	public void setCurrentLifeCycle(int currentLifeCycle) {
		this.currentLifeCycle = currentLifeCycle;
	}
	public GameObject getAttachedObject() {
		return attachedObject;
	}
	public void setAttachedObject(GameObject attachedObject) {
		this.attachedObject = attachedObject;
	}
	public long getPreviousTime() {
		return previousTime;
	}
	public void setPreviousTime(long previousTime) {
		this.previousTime = previousTime;
	}
	public long getPassedTime() {
		return passedTime;
	}
	public void setPassedTime(long passedTime) {
		this.passedTime = passedTime;
	}
	public String getEffectName() {
		return effectName;
	}
	public void setEffectName(String effectName) {
		this.effectName = effectName;
	}
	public int getPadX() {
		return padX;
	}
	public void setPadX(int padX) {
		this.padX = padX;
	}
	public int getPadY() {
		return padY;
	}
	public void setPadY(int padY) {
		this.padY = padY;
	}
	
	

}
