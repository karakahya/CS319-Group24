package com.savinghumanity.entity;

import com.savinghumanity.gamelogic.GameEngine;

public class Map {
	private int mapWidth;
	private int mapHeight;
	private Tile[][] tileMap;

	/**
	 * Destroys the given tile in the map. It will be shown blank
	 * @param i
	 * @param j
	 */
	public Map(){
		mapWidth = 20;
		mapHeight = 20;
		tileMap = new Tile[mapWidth][mapHeight];
	}
	public void destroyTile(int i, int j) {
		//Remove the reference and destroy the tile
		tileMap[i][j] = null;
		
	}
	public void addTile(Tile tile, int i, int j){
		tileMap[i][j] = tile;
	}
	public Tile getTile(int i, int j){
		return tileMap[i][j];
	}
	public int getMapWidth() {
		return mapWidth;
	}
	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}
	public int getMapHeight() {
		return mapHeight;
	}
	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}
	
	public void createRandomMap(int playerTankCount, int enemyTankCount){
		//Put concrete borders first
		for(int i = 0 ; i < mapWidth; i ++){
			for(int j = 0 ; j < mapHeight ; j++){
				if(i == 0 || i == mapWidth-1 || j == 0 || j == mapHeight-1){
					tileMap[i][j] = new ConcreteTile(j * 32, i * 32 , true,i, j);
				}
			}
		}
		for(int i = 0 ; i < playerTankCount ; i++){
			GameEngine.getInstance().getPlayerTankList().add(new PlayerTank(((int)(Math.random() * 19 + 1) * 32),((int)(Math.random() * 19 + 1) * 32),true, 1.0f,300.0f,10,100,true,true,false , false));
		}
		for(int i = 0 ; i < enemyTankCount ; i++){
			GameEngine.getInstance().getEnemyTankList().add(new EnemyTank(((int)(Math.random() * 19 + 1) * 32),((int)(Math.random() * 19 + 1) * 32),true,(int)(Math.random() * 2)));
		}
		
	}
	
	

}