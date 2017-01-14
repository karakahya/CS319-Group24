package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

import com.savinghumanity.fx.Effect;
import com.savinghumanity.gamelogic.GameEngine;

public class Immortality extends Bonus {

	public Immortality(float posX, float posY, boolean isalive , long duration, long start) {
		super(posX, posY, isalive , duration, start);
		// TODO Auto-generated constructor stub
	}

	public void effect(PlayerTank pt) {
		pt.setinvincible(true);
		Effect newEffect = new Effect(pt.getPosX() , pt.getPosY(), (short)32,(short)18,3,(GameObject)pt,"data/FX/fx7_energyBall","aura_test_1_32_",-20,-25);
		GameEngine.getInstance().createEffect(newEffect);
	}


}