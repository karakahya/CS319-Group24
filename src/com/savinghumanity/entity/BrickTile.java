package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;

import com.savinghumanity.file.FileManager;

public class BrickTile extends Tile {

	public BrickTile(int posX, int posY,boolean isAlive) {
		super(posX, posY, isAlive);
		BufferedImage temp;
		temp = FileManager.getEntitySprite().getSubimage(16*16, 0 * 16, 16, 16); // Coordinate for brick tile - (0,16) in sprite
		//Scale image to 32x32
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		//Convert BufferedImage to Image
		this.image = (SwingFXUtils.toFXImage(temp, null));
	}


}