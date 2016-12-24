package com.savinghumanity.entity;

import java.awt.image.BufferedImage;

public abstract class Tile extends GameObject {
        private int arrX;
        private int arrY;
	
	public Tile(int posX, int posY, boolean isalive,int arrX, int arrY) {
		super(posX, posY, isalive);
                this.arrX = arrX;
                this.arrY = arrY;
		// TODO Auto-generated constructor stub
	}

	
	public void update() {
		// TODO Auto-generated method stub
		
	}
        public int getArrX(){
            return arrX;
        }
        public int getArrY(){
            return arrY;
        }

    public void setArrX(int arrX) {
        this.arrX = arrX;
    }

    public void setArrY(int arrY) {
        this.arrY = arrY;
    }
}