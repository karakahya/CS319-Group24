package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;

import com.savinghumanity.file.FileManager;

public class ConcreteTile extends Tile {

	public ConcreteTile(int posX, int posY, boolean isAlive,int arrX, int arrY) {
		super(posX, posY, isAlive,arrX,arrY);
		BufferedImage temp;
		temp = FileManager.getEntitySprite().getSubimage(16 * 16, 1 * 16, 16, 16); // Coordinate for concrete tile - (1,16) in sprite
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.image = (SwingFXUtils.toFXImage(temp, null));
		// TODO Auto-generated constructor stub
	}
}