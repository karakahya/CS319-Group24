package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;

import com.savinghumanity.file.FileManager;
import java.util.ArrayList;

public class PlayerTank extends Tank {

	private boolean canMove;
	private boolean canFire;
	private boolean invincible;
	private boolean isConfused;
        private ArrayList<Bonus> tankBonuses;

	/**
	 * 
	 * @param xPos
	 * @param yPos
	 */
	public PlayerTank(float corX, float corY,boolean isalive, float firstSpeed, float firstRange, int firstDamage, int firstHealth, boolean movable, boolean firable, boolean invince , boolean isConfused){
		super(corX, corY,isalive);
		speed = firstSpeed;
                fireRange = firstRange;
                damage = firstDamage;
                health = firstHealth;
		//Initial Speeds
		speedX = 0.0f;
		speedY = 0.0f;
		tankBonuses = new ArrayList<Bonus>();
		canMove = movable;
		canFire = firable;
		invincible = invince;
		this.isConfused = isConfused;
		//Initialize Right Tank Animation
		BufferedImage temp;
		
		temp = FileManager.getEntitySprite().getSubimage(6 * 16, 0 * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.rTank[0] = (SwingFXUtils.toFXImage(temp, null));

		temp = FileManager.getEntitySprite().getSubimage(7 * 16, 0 * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.rTank[1] = (SwingFXUtils.toFXImage(temp, null));
		//Initialize Down Tank Animation
		temp = FileManager.getEntitySprite().getSubimage(4 * 16, 0 * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.dTank[0] = (SwingFXUtils.toFXImage(temp, null));

		temp = FileManager.getEntitySprite().getSubimage(5 * 16, 0 * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.dTank[1] = (SwingFXUtils.toFXImage(temp, null));
		//Initialize Left Tank Animation
		temp = FileManager.getEntitySprite().getSubimage(2 * 16, 0 * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.lTank[0] = (SwingFXUtils.toFXImage(temp, null));

		temp = FileManager.getEntitySprite().getSubimage(3 * 16, 0 * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.lTank[1] = (SwingFXUtils.toFXImage(temp, null));
		//Initialize Up Tank Animation
		temp = FileManager.getEntitySprite().getSubimage(0 * 16, 0 * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.uTank[0] = (SwingFXUtils.toFXImage(temp, null));

		temp = FileManager.getEntitySprite().getSubimage(1 * 16, 0 * 16, 16, 16); 
		temp = GameObject.scale(temp, BufferedImage.TYPE_3BYTE_BGR, 32, 32, 2, 2);
		this.uTank[1] = (SwingFXUtils.toFXImage(temp, null));

		//Initially looking upwards
		this.image = uTank[0];



	}

    public ArrayList<Bonus> getTankBonuses() {
        return tankBonuses;
    }

    public void setTankBonuses(ArrayList<Bonus> tankBonuses) {
        this.tankBonuses = tankBonuses;
    }
	@Override
	public void fire(){
		if(canFire)
			super.fire();
		return;
	}

    public boolean isCanFire() {
        return canFire;
    }
	public void addBonus(Bonus bonus){
            tankBonuses.add(bonus);
        }
	public void neutralize(){
		canFire = true;
                canMove = true;
                damage = 10;
                fireRange = 300.0f;
                invincible = false;
                isConfused = false;
                
	}
	public void setcanMove(boolean effect){
		canMove = effect;
	}

    public boolean canMove() {
        return canMove;
    }

    public boolean canFire() {
        return canFire;
    }

    public boolean isInvincible() {
        return invincible;
    }

    public boolean isConfused() {
        return isConfused;
    }

	public void setcanFire(boolean effect){
		canFire = effect;
	}

	public void setinvincible(boolean effect){
		invincible = effect;
	}


	


	public void setConfused(boolean isConfused) {
		this.isConfused = isConfused;
	}



}