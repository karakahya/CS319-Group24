package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

public abstract class GameObject {

	private int posX;
	private int posY;
	private BufferedImage image;
	private boolean isAlive;

	public abstract void update();

	/**
	 * 
	 * @param obj
	 */
	public boolean collisionCheck(GameObject obj) {
		// TODO - implement GameObject.collisionCheck
		throw new UnsupportedOperationException();
	}

}