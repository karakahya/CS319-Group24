package com.savinghumanity.entity;
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
	
	

}