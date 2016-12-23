package com.savinghumanity.file;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;

import javax.imageio.ImageIO;

import com.savinghumanity.entity.BrickTile;
import com.savinghumanity.entity.ConcreteTile;
import com.savinghumanity.entity.EnemyTank;
import com.savinghumanity.entity.GrassTile;
import com.savinghumanity.entity.Map;
import com.savinghumanity.entity.PlayerTank;
import com.savinghumanity.entity.Tank;
import com.savinghumanity.entity.Tile;
import com.savinghumanity.entity.WaterTile;
import com.savinghumanity.gui.GameApplication;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {

	private ArrayList<Double> highScoreList;
	private Tile[][] mapObjectList;
	private static FileManager theFileManager;
	private static BufferedImage entitySprite;

	public void readHighScore() {
		// TODO - implement FileManager.readHighScore
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param newScore
	 */
	public void writeHighScore(double newScore) {
		// TODO - implement FileManager.writeHighScore
		throw new UnsupportedOperationException();
	}

	/**
	 * Reads a level file from disk and instantiates the corresponding map
	 * 
	 * Any error happens during map file reads are considered as fatal errors
	 * Henceforth the program will exit upon encountering exceptions in this method
	 * @param filePath File path for map file
	 * @param multiPlayerNo Count of how many players will be playing, 1 for single game, more for multiplayer game
	 * @param enemyTanks empty array list for enemy tanks, will be filled in the method
	 * @param playerTanks empty array list for player tanks, will be filled in the method
	 */
	public Map readMapFile(String filePath,int multiPlayerNo,ArrayList<EnemyTank> enemyTanks,ArrayList<PlayerTank> playerTanks) {
		Map gameMap = new Map();
		FileReader fr;
		int enemyTankCount = 0;
		float posX = 0,posY = 0;
		int tankType = -1;
		int tankNo = -1;
		boolean atEnemyTankDescription = false; // true if reader comes to the part where enemy tank count and coordinates are specified
		boolean atPlayerTankDescription = false;
		boolean isSinglePlayer = multiPlayerNo == 1;
		
		String numberPattern = "[0-9]+";
		// Create a Pattern object
	     Pattern r = Pattern.compile(numberPattern);

	     // Now create matcher object.
	     Matcher m;
	    //Try-with-resource  
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
			
			String currentLine;
			int rowCount = 0;
			while ((currentLine = br.readLine()) != null) {
				
					if(currentLine.length() >= 2 && currentLine.substring(0, 2).equals("//")) continue; // Ignore comments
					
					//If we are in the comment section yet
					
					if(!atEnemyTankDescription && isSinglePlayer){
						m = r.matcher(currentLine);
						//If we find a number it means that now we are in tank description line
						//First match is the tank count
						
						if(m.find()){
							atEnemyTankDescription = true;
							enemyTankCount = Integer.parseInt(m.group());
						}
						int groupCount = 0;
						
						while(m.find()){
							
							//Tank number
							if(groupCount % 4 == 0 )
								tankNo = Integer.parseInt(m.group());
							else if(groupCount %4 == 1)
								posX = Float.parseFloat(m.group());
							else if(groupCount %4 == 2)
								posY = Float.parseFloat(m.group());
							else{
								tankType = Integer.parseInt(m.group());
								EnemyTank newTank = new EnemyTank(posY * 32,posX * 32,true,tankType);
								enemyTanks.add(tankNo-1,newTank);
							}
							groupCount++;
						}
						
					}
					//If we are in the player tank specification part
					else if((isSinglePlayer && atEnemyTankDescription && !atPlayerTankDescription) || (!isSinglePlayer && !atEnemyTankDescription && !atPlayerTankDescription)){
						m = r.matcher(currentLine);
						
						int groupCount = 0;
						while(m.find()){
							atPlayerTankDescription = true;
							if(groupCount % 3 == 0){
								tankNo = Integer.parseInt(m.group());
								//Stop at specified player tank count
								if(tankNo > multiPlayerNo)
									continue;
							}
							else if(groupCount %3 == 1)
								posX = Integer.parseInt(m.group());
							else if(groupCount %3 == 2){
								posY = Integer.parseInt(m.group());
								PlayerTank newTank = new PlayerTank(posY * 32,posX * 32,true, 1,1,1,1,true,true,false , false);
								playerTanks.add(tankNo-1,newTank);
							}
							groupCount++;
						}
					}
					else if(atPlayerTankDescription && !currentLine.equals("")){
						int tileNo = 0;
						m = r.matcher(currentLine);
						int columnCount = 0;
						while(m.find()){
							Tile newTile = null;
							tileNo = Integer.parseInt(m.group());
							if(tileNo == 0)
								newTile = null;
							else if(tileNo == 1)
								newTile = new BrickTile(columnCount * 32, rowCount * 32 , true); 
							else if(tileNo == 2)
								newTile = new ConcreteTile(columnCount * 32, rowCount * 32 , true); 
							else if(tileNo == 3)
								newTile = new WaterTile(columnCount * 32, rowCount * 32 , true); 
							else if(tileNo == 4)
								newTile = new GrassTile(columnCount * 32,rowCount * 32 , true);
							if(rowCount >= gameMap.getMapHeight() || columnCount >= gameMap.getMapWidth());
								// TODO Add new Exception here and handle it in GameApllication or somewhere!
							gameMap.addTile(newTile, rowCount, columnCount);
							columnCount++;
							
						}
						rowCount++;
					}
				
				
			}
		//Game Map Exceptions are considered as fatal, hence program will terminate!	
		}catch(FileNotFoundException e){
			GameApplication.alertFileNotFound(filePath);
		}catch(IOException e){
			GameApplication.alertIOException(filePath);
		}
		return gameMap;
	}

	public static FileManager getInstance() {
		if(theFileManager == null)
			theFileManager = new FileManager();
		return theFileManager;
	}

	private FileManager() {
		highScoreList = new ArrayList<Double>();
		entitySprite = null;
		try {
		    entitySprite = ImageIO.read(new File("data/sprite/sprite.png"));
		}catch(FileNotFoundException e){
			GameApplication.alertFileNotFound("data/sprite/sprite.png");
		}catch (IOException e) {
			GameApplication.alertIOException("data/sprite/sprite.png");
		}
		
	}
	
	public Tile[][] getMapObjectList(){
		if(mapObjectList == null)
			return null;
		return mapObjectList;
	}
	public static BufferedImage getEntitySprite(){
		return entitySprite;
	}
	public static File getSoundFile(){
                String musicFile = "data/sound.mp3";
                File soundFile = new File(musicFile);
                return soundFile;
        }
	

}
