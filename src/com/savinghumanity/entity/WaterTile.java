package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import com.savinghumanity.file.FileManager;

public class WaterTile extends Tile implements Animation {

	private Image[] animation;
	short animationFrame;
	
	public WaterTile(int posX, int posY, boolean isAlive) {
		super(posX, posY , isAlive);
		animation = new Image[2];
		animationFrame = 0;
		BufferedImage temp;
		temp = FileManager.getEntitySprite().getSubimage(17 * 16, 3 * 16, 16, 16); // Coordinate for water tile - (3,17) in sprite
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.image = (SwingFXUtils.toFXImage(temp, null));
		
		//Sets animation frames
		animation[0] = this.image;
		temp = FileManager.getEntitySprite().getSubimage(16 * 16, 3 * 16, 16, 16);
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		animation[1] = (SwingFXUtils.toFXImage(temp, null));
		// TODO Auto-generated constructor stub
	}
	
	public void alternateAnimation(){
		if(animationFrame == 0){
			this.image = animation[1];
			animationFrame = 1;
		}
		else{
			this.image = animation[0];
			animationFrame = 0;
		}
	}
}