package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;

import com.savinghumanity.file.FileManager;
public class GrassTile extends Tile {

	public GrassTile(int posX, int posY, boolean isalive,int arrX, int arrY) {
		super(posX, posY, isalive, arrX, arrY);
		BufferedImage temp = FileManager.getEntitySprite().getSubimage(17 * 16, 2 * 16, 16, 16);
		  // Coordinate for grass tile - (2,17) in sprite
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.image = (SwingFXUtils.toFXImage(temp, null));
		// TODO Auto-generated constructor stub
	}
}