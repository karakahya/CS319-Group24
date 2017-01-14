package com.savinghumanity.entity;

import com.savinghumanity.file.FileManager;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;

public abstract class Bonus extends GameObject {

	public Bonus(float posX, float posY, boolean isalive,long duration, long start) {
		super(posX, posY,isalive);
		durationTime = duration;
		startingTime = start;

		BufferedImage temp = null;
		temp = FileManager.getEntitySprite().getSubimage(19 * 16, 7 * 16, 16, 16);
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		image = SwingFXUtils.toFXImage(temp, null);
	}

	public long getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(long durationTime) {
		this.durationTime = durationTime;
	}

	public long getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(long startingTime) {
		this.startingTime = startingTime;
	}

	private long durationTime;
	private long startingTime;

	public abstract void effect(PlayerTank pt);

}