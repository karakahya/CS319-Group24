package com.savinghumanity.gamelogic;

import java.util.ArrayList;

import com.savinghumanity.entity.Bonus;
import com.savinghumanity.entity.BrickTile;
import com.savinghumanity.entity.Broken;
import com.savinghumanity.entity.Bullet;
import com.savinghumanity.entity.ConcreteTile;
import com.savinghumanity.entity.Confuse;
import com.savinghumanity.entity.EnemyTank;
import com.savinghumanity.entity.GameObject;
import com.savinghumanity.entity.GrassTile;
import com.savinghumanity.entity.Immortality;
import com.savinghumanity.entity.Increase;
import com.savinghumanity.entity.IncreaseBonusType;
import com.savinghumanity.entity.Map;
import com.savinghumanity.entity.MoveDirection;
import com.savinghumanity.entity.Neutralizer;
import com.savinghumanity.entity.PlayerTank;
import com.savinghumanity.entity.RandomDestroyer;
import com.savinghumanity.entity.Rooted;
import com.savinghumanity.entity.Tank;
import com.savinghumanity.entity.Tile;
import com.savinghumanity.file.FileManager;
import com.savinghumanity.gui.GameApplication;
import com.savinghumanity.input.InputManager;
import javafx.stage.Stage;

public class GameEngine {

    
        public enum GameType{
            Single,MultiplayerDM,MultiplayerTDM, 
        };
	private long elapsedTime;
        private int currentLevel;
	private static Map gameMap;
	private FileManager fileManager;
        private InputManager inputMgr;
	private static ArrayList<Bullet> bulletList;
	private static ArrayList<Bonus> bonusList;
	private static ArrayList<EnemyTank> enemyTankList;
	private static ArrayList<PlayerTank> playerTankList;
	private static GameEngine theGameEngine;
	private static ArrayList<GameObject> allObjects; // All objects in the game;
        private GameType gametype;
        private boolean isPaused;
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
        public void checkBonusDuration(){
            long curTime = System.nanoTime();
            for(int i = 0 ; i < playerTankList.size() ; i++){
                for(int j = 0; j < playerTankList.get(i).getTankBonuses().size() ; j++){
                    Bonus tanksBonus = playerTankList.get(i).getTankBonuses().get(j);
                    System.out.println(curTime - tanksBonus.getStartingTime() / 1000000000.0);
                    if((curTime - tanksBonus.getStartingTime()) / 1000000000.0 > tanksBonus.getDurationTime()){
                        playerTankList.get(i).getTankBonuses().remove(tanksBonus);
                        playerTankList.get(i).neutralize();
                    }
                    
                }
            }
        }
        //If true, level is completed
        public void singleModeVictoryCheck(Stage primaryStage){
           if(enemyTankList.isEmpty() && currentLevel < 3){
               startGame("data/map/Level" + (currentLevel+1) + ".txt", 1);
           }
           else if(enemyTankList.isEmpty() && currentLevel == 3){
               //Calculate high score, update highscore and show, then return main menu
               GameApplication.switchToMainMenuScene(primaryStage);
           }
        }
        public void multiDeathmatchVictoryCheck(Stage primaryStage){
             if(playerTankList.size() == 1)
                 GameApplication.switchToMainMenuScene(primaryStage);
                 
        }
        public void multiTeamDeathmatchVictoryCheck(Stage primaryStage) {
            // :(
         }
	private double calculateHighScore() {
		// TODO - implement GameEngine.calculateHighScore
		throw new UnsupportedOperationException();
	}

	public void bulletDistanceChecker() {
		for(int i = 0 ; i < bulletList.size() ; i++){
                    bulletList.get(i).updateBullet();
                    if(bulletList.get(i).getRange() <= 0.0f){
                        allObjects.remove(bulletList.get(i));
                        bulletList.get(i).getOwner().setBullet(null);
                        bulletList.remove(bulletList.get(i));
                        
                    }
                }
	}


	/**
	 * 
	 * @param mapFile
	 * @param playerCount how many user will be playing. 1 for singleplayer, 2-4 for multiplayer
	 */
	public void startGame(String mapFile, int playerCount) {
                currentLevel++;
                //clear all array lists and map
                
                enemyTankList.clear();
                playerTankList.clear();
                bulletList.clear();
                allObjects.clear();
                bonusList.clear();
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
                if(enemyTankList.size() > 0)
                    gametype = GameType.Single;
                
	}

    public GameType getGametype() {
        return gametype;
    }

    public void setGametype(GameType gametype) {
        this.gametype = gametype;
    }

	/**
	 * 
	 * @param keyPressed
	 */
        public void buttonReleased(String keyPressed){
            //player 1
            if(playerTankList.get(0) != null){
                switch(keyPressed){
                            case "W":
                                playerTankList.get(0).setSpeedY(0);
                                break;
                            case "A":
                                playerTankList.get(0).setSpeedX(0);
                                break;
                            case "S":
                                playerTankList.get(0).setSpeedY(0);
                                break;
                            case "D":
                                playerTankList.get(0).setSpeedX(0);
                                break;
                        }
            }
            //player 2
            if(playerTankList.get(1) != null){
                switch(keyPressed){
                            case "T":
                                playerTankList.get(1).setSpeedY(0);
                                break;
                            case "F":
                                playerTankList.get(1).setSpeedX(0);
                                break;
                            case "G":
                                playerTankList.get(1).setSpeedY(0);
                                break;
                            case "H":
                                playerTankList.get(1).setSpeedX(0);
                                break;
                        }
            }
            //player 3
            if(playerTankList.get(2) != null){
                switch(keyPressed){
                            case "I":
                                playerTankList.get(2).setSpeedY(0);
                                break;
                            case "J":
                                playerTankList.get(2).setSpeedX(0);
                                break;
                            case "K":
                                playerTankList.get(2).setSpeedY(0);
                                break;
                            case "L":
                                playerTankList.get(2).setSpeedX(0);
                                break;
                        }
            }
            //player 4
            if(playerTankList.get(3) != null){
                switch(keyPressed){
                            case "UP":
                                playerTankList.get(3).setSpeedY(0);
                                break;
                            case "LEFT":
                                playerTankList.get(3).setSpeedX(0);
                                break;
                            case "DOWN":
                                playerTankList.get(3).setSpeedY(0);
                                break;
                            case "RIGHT":
                                playerTankList.get(3).setSpeedX(0);
                                break;
                        }
            }
            
        }
        
	public void buttonPressed(String keyPressed) {
                    switch(keyPressed){
                        case "P":
                            if(!isPaused){
                                isPaused = true;
                                GameApplication.getATimer().stop();
                            }
                            else{
                                isPaused = false;
                                GameApplication.getATimer().start();
                            }
                            break;
                        case "W":    
                            playerTankList.get(0).setSpeedX(0);
                            if(playerTankList.get(0).canMove())
                                playerTankList.get(0).setSpeedY(-playerTankList.get(0).getSpeed());
                            else
                                playerTankList.get(0).setSpeedY(0);
                            playerTankList.get(0).setMoveDirection(MoveDirection.UP);
                            playerTankList.get(0).setImage( playerTankList.get(0).getuTank()[0]);
                            break;
                        case "A":
                            if(playerTankList.get(0).canMove())
                                playerTankList.get(0).setSpeedX(-playerTankList.get(0).getSpeed());
                            else
                                playerTankList.get(0).setSpeedX(0);
                            playerTankList.get(0).setSpeedY(0);
                            playerTankList.get(0).setMoveDirection(MoveDirection.LEFT);
                            playerTankList.get(0).setImage( playerTankList.get(0).getlTank()[0]);
                            break;
                        case "S":
                            playerTankList.get(0).setSpeedX(0);
                            if(playerTankList.get(0).canMove())
                                playerTankList.get(0).setSpeedY(playerTankList.get(0).getSpeed());
                            else
                                playerTankList.get(0).setSpeedY(0);
                            playerTankList.get(0).setMoveDirection(MoveDirection.DOWN);
                            playerTankList.get(0).setImage( playerTankList.get(0).getdTank()[0]);
                            break;
                         case "D":
                            if(playerTankList.get(0).canMove())
                                playerTankList.get(0).setSpeedX(playerTankList.get(0).getSpeed());
                            else
                                playerTankList.get(0).setSpeedX(0);
                            playerTankList.get(0).setSpeedY(0);
                            playerTankList.get(0).setMoveDirection(MoveDirection.RIGHT);
                            playerTankList.get(0).setImage( playerTankList.get(0).getrTank()[0]);
                            break;
                         case "CONTROL":
                             playerTankList.get(0).fire();
                             break;
                    }
                    // Player 2
                    if(playerTankList.get(1) != null){
                        switch(keyPressed){
                            case "T":    
                                playerTankList.get(1).setSpeedX(0);
                                if(playerTankList.get(1).canMove())
                                    playerTankList.get(1).setSpeedY(-playerTankList.get(1).getSpeed());
                                else
                                    playerTankList.get(1).setSpeedY(0);
                                playerTankList.get(1).setMoveDirection(MoveDirection.UP);
                                playerTankList.get(1).setImage( playerTankList.get(1).getuTank()[0]);
                                break;
                            case "F":
                                if(playerTankList.get(1).canMove())
                                    playerTankList.get(1).setSpeedX(-playerTankList.get(1).getSpeed());
                                else
                                    playerTankList.get(1).setSpeedX(0);
                                playerTankList.get(1).setSpeedY(0);
                                playerTankList.get(1).setMoveDirection(MoveDirection.LEFT);
                                playerTankList.get(1).setImage( playerTankList.get(1).getlTank()[0]);
                                break;
                            case "G":
                                playerTankList.get(1).setSpeedX(0);
                                if(playerTankList.get(1).canMove())
                                    playerTankList.get(1).setSpeedY(playerTankList.get(1).getSpeed());
                                else
                                    playerTankList.get(1).setSpeedY(0);
                                playerTankList.get(1).setMoveDirection(MoveDirection.DOWN);
                                playerTankList.get(1).setImage( playerTankList.get(1).getdTank()[0]);
                                break;
                             case "H":
                                if(playerTankList.get(1).canMove())
                                    playerTankList.get(1).setSpeedX(playerTankList.get(1).getSpeed());
                                else
                                    playerTankList.get(1).setSpeedX(0);
                                playerTankList.get(1).setSpeedY(0);
                                playerTankList.get(1).setMoveDirection(MoveDirection.RIGHT);
                                playerTankList.get(1).setImage( playerTankList.get(1).getrTank()[0]);
                                break;
                             case "B":
                                 playerTankList.get(1).fire();
                                 break;
                        }
                    }
                    
                    // Player 3
                    if(playerTankList.get(2) != null){
                        switch(keyPressed){
                            case "I":    
                                playerTankList.get(2).setSpeedX(0);
                                if(playerTankList.get(2).canMove())
                                    playerTankList.get(2).setSpeedY(-playerTankList.get(2).getSpeed());
                                else
                                    playerTankList.get(2).setSpeedY(0);
                                playerTankList.get(2).setMoveDirection(MoveDirection.UP);
                                playerTankList.get(2).setImage( playerTankList.get(2).getuTank()[0]);
                                break;
                            case "J":
                                if(playerTankList.get(2).canMove())
                                    playerTankList.get(2).setSpeedX(-playerTankList.get(2).getSpeed());
                                else
                                    playerTankList.get(2).setSpeedX(0);
                                playerTankList.get(2).setSpeedY(0);
                                playerTankList.get(2).setMoveDirection(MoveDirection.LEFT);
                                playerTankList.get(2).setImage( playerTankList.get(2).getlTank()[0]);
                                break;
                            case "K":
                                playerTankList.get(2).setSpeedX(0);
                                if(playerTankList.get(2).canMove())
                                    playerTankList.get(2).setSpeedY(playerTankList.get(2).getSpeed());
                                else
                                    playerTankList.get(2).setSpeedY(0);
                                playerTankList.get(2).setMoveDirection(MoveDirection.DOWN);
                                playerTankList.get(2).setImage( playerTankList.get(2).getdTank()[0]);
                                break;
                             case "L":
                                if(playerTankList.get(2).canMove())
                                    playerTankList.get(2).setSpeedX(playerTankList.get(2).getSpeed());
                                else
                                    playerTankList.get(2).setSpeedX(0);
                                playerTankList.get(2).setSpeedY(0);
                                playerTankList.get(2).setMoveDirection(MoveDirection.RIGHT);
                                playerTankList.get(2).setImage( playerTankList.get(2).getrTank()[0]);
                                break;
                             case "M":
                                 playerTankList.get(2).fire();
                                 break;
                        }
                    }
                    
                    // Player 4
                    if(playerTankList.get(3) != null){
                        switch(keyPressed){
                            case "UP":    
                                playerTankList.get(3).setSpeedX(0);
                                if(playerTankList.get(3).canMove())
                                    playerTankList.get(3).setSpeedY(-playerTankList.get(3).getSpeed());
                                else
                                    playerTankList.get(3).setSpeedY(0);
                                playerTankList.get(3).setMoveDirection(MoveDirection.UP);
                                playerTankList.get(3).setImage( playerTankList.get(3).getuTank()[0]);
                                break;
                            case "LEFT":
                                if(playerTankList.get(3).canMove())
                                    playerTankList.get(3).setSpeedX(-playerTankList.get(2).getSpeed());
                                else
                                    playerTankList.get(3).setSpeedX(0);
                                playerTankList.get(3).setSpeedY(0);
                                playerTankList.get(3).setMoveDirection(MoveDirection.LEFT);
                                playerTankList.get(3).setImage( playerTankList.get(3).getlTank()[0]);
                                break;
                            case "DOWN":
                                playerTankList.get(3).setSpeedX(0);
                                if(playerTankList.get(3).canMove())
                                    playerTankList.get(3).setSpeedY(playerTankList.get(3).getSpeed());
                                else
                                    playerTankList.get(3).setSpeedY(0);
                                playerTankList.get(3).setMoveDirection(MoveDirection.DOWN);
                                playerTankList.get(3).setImage( playerTankList.get(3).getdTank()[0]);
                                break;
                             case "RIGHT":
                                if(playerTankList.get(3).canMove())
                                    playerTankList.get(3).setSpeedX(playerTankList.get(3).getSpeed());
                                else
                                    playerTankList.get(3).setSpeedX(0);
                                playerTankList.get(3).setSpeedY(0);
                                playerTankList.get(3).setMoveDirection(MoveDirection.RIGHT);
                                playerTankList.get(3).setImage( playerTankList.get(3).getrTank()[0]);
                                break;
                             case "SHIFT":
                                 playerTankList.get(3).fire();
                                 break;
                        }
                    }
                
	}
	
	public void handleCollision(){
		for(int i = 0 ; i < allObjects.size() ; i++){
			for(int j = 0 ; j < allObjects.size() ; j++){
				if(i == j) continue;
				
				GameObject obj1 = allObjects.get(i);
				GameObject obj2 = allObjects.get(j);
				if(allObjects.get(i).collisionCheck(allObjects.get(j))){
					if(obj1 instanceof Tank && obj2 instanceof Tank){
						obj1.setSpeedX(0);
                                                obj1.setSpeedY(0);
                                                
                                                obj2.setSpeedX(0);
                                                obj2.setSpeedY(0);
                                                if(obj1 instanceof EnemyTank){
                                                    ((EnemyTank)obj1).setDistanceLeft(0.0f);
                                                }
                                                if(obj2 instanceof EnemyTank){
                                                    ((EnemyTank)obj2).setDistanceLeft(0.0f);
                                                }
					}
					else if(obj1 instanceof Tank && obj2 instanceof Bullet){
                                            if(obj1 != ((Bullet)(obj2)).getOwner()){
                                                if(!((obj1 instanceof EnemyTank) && (((Bullet)(obj2)).getOwner() instanceof  EnemyTank)))
                                                    ((Tank)obj1).setHealth(((Tank)obj1).getHealth() - ((Bullet)(obj2)).getDamage());
                                                bulletList.remove(obj2);
                                                allObjects.remove(obj2);
                                                ((Bullet)(obj2)).getOwner().setBullet(null);
                                                if(((Tank)obj1).getHealth() <= 0){
                                                    generateBonus(obj1.getPosX(),obj1.getPosY());
                                                    allObjects.remove(obj1);
                                                    if(obj1 instanceof EnemyTank)
                                                        enemyTankList.remove(obj1);
                                                    else if(obj1 instanceof PlayerTank)
                                                        playerTankList.remove(obj1);
                                                }
                                            }
                                            
					}
					else if(obj1 instanceof Tank && obj2 instanceof Tile){
						if(obj2 instanceof GrassTile) continue;
						obj1.setSpeedX(0.0f);
						obj1.setSpeedY(0.0f);
                                                if(obj1 instanceof EnemyTank){
                                                    ((EnemyTank)obj1).setDistanceLeft(0.0f);
                                                }
						
					}
					else if(obj1 instanceof PlayerTank && obj2 instanceof Bonus){
						((Bonus)obj2).effect((PlayerTank)obj1);
                                                bonusList.remove(obj2);
                                                allObjects.remove(obj2);
                                                ((PlayerTank)obj1).addBonus((Bonus)obj2);
					}
					else if(obj1 instanceof Bullet && obj2 instanceof Tile){
						if(obj2 instanceof ConcreteTile){
                                                    bulletList.remove(obj1);
                                                    allObjects.remove(obj1);
                                                    ((Bullet)(obj1)).getOwner().setBullet(null);
                                                }
                                                else if(obj2 instanceof BrickTile){
                                                   
                                                    bulletList.remove(obj1);
                                                    allObjects.remove(obj1);
                                                    allObjects.remove(obj2);
                                                    ((Bullet)(obj1)).getOwner().setBullet(null);
                                                    gameMap.destroyTile(((Tile)obj2).getArrX(),((Tile)obj2).getArrY());
                                                    
                                                }
					}
	
				}
			}
		}
	}

	public static void destroyTank(int i) {
		enemyTankList.remove(i);
	}


	private GameEngine() {
                currentLevel = 0;
		elapsedTime = 0;
		gameMap = null;
		fileManager =  FileManager.getInstance();
                inputMgr = InputManager.getInstance();
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
        // Increase 10 - Immortality - 5 , Rooted - 5, Broken = 10, Confused = 10
        private void generateBonus(float posX, float posY) {
            Bonus bonus = null;
            double random =  Math.random();
            if(random < 0.1f)
                bonus = new Increase(posX, posY,true,IncreaseBonusType.INCREASE_DAMAGE,10,System.nanoTime());
            else if(0.1f <= random && random < 0.2f)
                bonus = new Increase(posX, posY,true,IncreaseBonusType.INCREASE_HEALTH,10,System.nanoTime());
            else if(0.2f <= random && random < 0.3f)
                bonus = new Increase(posX, posY,true,IncreaseBonusType.INCREASE_SPEED,10,System.nanoTime());
            else if(0.3f <= random && random < 0.4f)
                bonus = new Increase(posX, posY,true,IncreaseBonusType.INCREASE_FIRE_RANGE,10,System.nanoTime());
            else if(0.4f <= random && random < 0.5f)
                bonus = new Broken(posX, posY,true,10,System.nanoTime());
            else if(0.5f <= random && random < 0.6f)
                bonus = new Confuse(posX, posY,true,10,System.nanoTime());
            else if(0.6f <= random && random < 0.7f)
                bonus = new Immortality(posX, posY,true,5,System.nanoTime());
            else if(0.7f <= random && random < 0.8f)
                bonus = new Neutralizer(posX, posY,true,10,System.nanoTime());
            else if(0.8f <= random && random < 0.9f)
                bonus = new RandomDestroyer(posX, posY,true,10,System.nanoTime());
            else if(0.9f <= random && random < 1f)
                bonus = new Rooted(posX, posY,true,5,System.nanoTime());
            allObjects.add(bonus);
            bonusList.add(bonus);
        }
	

}