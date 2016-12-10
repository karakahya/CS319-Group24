package com.savinghumanity.gamelogic;

import java.util.ArrayList;

import com.savinghumanity.entity.Bonus;
import com.savinghumanity.entity.Bullet;
import com.savinghumanity.entity.EnemyTank;
import com.savinghumanity.entity.Map;
import com.savinghumanity.entity.PlayerTank;

public class GameEngine {

	private long elapsedTime;
	private Map gameMap;
	private static ArrayList<Bullet> bulletList;
	private static ArrayList<Bonus> bonusList;
	private static ArrayList<EnemyTank> enemyTankList;
	private static ArrayList<PlayerTank> playerTankList;
	private GameEngine theGameEngine;

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
	 */
	public void startGame(String mapFile) {
		// TODO - implement GameEngine.startGame
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param keyPressed
	 */
	public void buttonPressed(int keyPressed) {
		// TODO - implement GameEngine.buttonPressed
		throw new UnsupportedOperationException();
	}

	public void destroyTank() {
		// TODO - implement GameEngine.destroyTank
		throw new UnsupportedOperationException();
	}

	public Bonus checkPowerUpPickup() {
		// TODO - implement GameEngine.checkPowerUpPickup
		throw new UnsupportedOperationException();
	}

	private GameEngine() {
		// TODO - implement GameEngine.GameEngine
		throw new UnsupportedOperationException();
	}

	public GameEngine getInstance() {
		// TODO - implement GameEngine.getInstance
		throw new UnsupportedOperationException();
	}

}