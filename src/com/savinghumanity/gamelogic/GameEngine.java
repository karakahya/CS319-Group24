package com.savinghumanity.gamelogic;

import java.util.ArrayList;

import com.savinghumanity.entity.Bonus;
import com.savinghumanity.entity.Bullet;
import com.savinghumanity.entity.EnemyTank;
import com.savinghumanity.entity.GameObject;
import com.savinghumanity.entity.Map;
import com.savinghumanity.entity.PlayerTank;
import com.savinghumanity.entity.Tank;
import com.savinghumanity.file.FileManager;

public class GameEngine {

	private long elapsedTime;
	private static Map gameMap;
	private FileManager fileManager;
	private static ArrayList<Bullet> bulletList;
	private static ArrayList<Bonus> bonusList;
	private static ArrayList<EnemyTank> enemyTankList;
	private static ArrayList<PlayerTank> playerTankList;
	private static GameEngine theGameEngine;
	private static ArrayList<GameObject> allObjects; // All objects in the game;

	public static ArrayList<GameObject> getAllObjects() {
		return allObjects;
	}

	public static void setAllObjects(ArrayList<GameObject> allObjects) {
		GameEngine.allObjects = allObjects;
	}
	public void addGameObject(GameObject obj){
		allObjects.add(obj);
	}
	public void removeGameObject(int i){
		allObjects.remove(i);
	}
	public boolean removeGameObject(GameObject obj){
		return allObjects.remove(obj);
		
	}

	private double calculateHighScore() {
		// TODO - implement GameEngine.calculateHighScore
		throw new UnsupportedOperationException();
	}

	private void bulletDistanceChecker() {
		// TODO - implement GameEngine.bulletDistanceChecker
		throw new UnsupportedOperationException();
	}

	private void updateTimer() {
		// TODO - implement GameEngine.updateTimer
		throw new UnsupportedOperationException();
	}

	private void updateObjects() {
		// TODO - implement GameEngine.updateObjects
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param mapFile
	 * @param playerCount how many user will be playing. 1 for singleplayer, 2-4 for multiplayer
	 */
	public void startGame(String mapFile, int playerCount) {
		//Initializes the Game map and also fill the player tank and enemy tank lists.
		gameMap = fileManager.readMapFile(mapFile,playerCount, enemyTankList,playerTankList);
		for(int i = 0 ; i < gameMap.getMapWidth() ; i++){
			for(int j = 0 ; j < gameMap.getMapHeight() ; j++){
				if(gameMap.getTile(i, j) != null)
					allObjects.add(gameMap.getTile(i, j)); // Add objects to the all objects array also
			}
		}
		for(EnemyTank a: enemyTankList){
			allObjects.add(a);
		}
		for(PlayerTank a: playerTankList){
			allObjects.add(a);
		}
	}

	/**
	 * 
	 * @param keyPressed
	 */
	public void buttonPressed(int keyPressed) {
		// TODO - implement GameEngine.buttonPressed
		throw new UnsupportedOperationException();
	}

	public static void destroyTank(int i) {
		enemyTankList.remove(i);
	}

	public Bonus checkPowerUpPickup() {
		// TODO - implement GameEngine.checkPowerUpPickup
		throw new UnsupportedOperationException();
	}

	private GameEngine() {
		elapsedTime = 0;
		gameMap = null;
		fileManager =  FileManager.getInstance();
		bulletList = new ArrayList<Bullet>();
		bonusList = new ArrayList<Bonus>();
		enemyTankList = new ArrayList<EnemyTank>();
		playerTankList = new ArrayList<PlayerTank>();
		allObjects = new ArrayList<GameObject>();
	}

	public static GameEngine getInstance() {
		if(theGameEngine == null)
			theGameEngine = new GameEngine();
		return theGameEngine;
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public static Map getGameMap() {
		return gameMap;
	}

	public static void setGameMap(Map gameMap) {
		GameEngine.gameMap = gameMap;
	}

	public FileManager getFileManager() {
		return fileManager;
	}

	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	public static ArrayList<Bullet> getBulletList() {
		return bulletList;
	}

	public static void setBulletList(ArrayList<Bullet> bulletList) {
		GameEngine.bulletList = bulletList;
	}

	public static ArrayList<Bonus> getBonusList() {
		return bonusList;
	}

	public static void setBonusList(ArrayList<Bonus> bonusList) {
		GameEngine.bonusList = bonusList;
	}

	public static ArrayList<EnemyTank> getEnemyTankList() {
		return enemyTankList;
	}

	public static void setEnemyTankList(ArrayList<EnemyTank> enemyTankList) {
		GameEngine.enemyTankList = enemyTankList;
	}

	public static ArrayList<PlayerTank> getPlayerTankList() {
		return playerTankList;
	}

	public static void setPlayerTankList(ArrayList<PlayerTank> playerTankList) {
		GameEngine.playerTankList = playerTankList;
	}

	public static GameEngine getTheGameEngine() {
		return theGameEngine;
	}

	public static void setTheGameEngine(GameEngine theGameEngine) {
		GameEngine.theGameEngine = theGameEngine;
	}
	

}