package com.savinghumanity.file;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.savinghumanity.entity.BrickTile;
import com.savinghumanity.entity.ConcreteTile;
import com.savinghumanity.entity.EnemyTank;
import com.savinghumanity.entity.GrassTile;
import com.savinghumanity.entity.Map;
import com.savinghumanity.entity.Tank;
import com.savinghumanity.entity.Tile;
import com.savinghumanity.entity.WaterTile;
import com.savinghumanity.gui.GameApplication;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {

	private ArrayList<Double> highScoreList;
	private Tile[][] mapObjectList;
	private FileManager theFileManager;
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
	 * Any error happens during map file reads are considered as fatal errors
	 * Henceforth the program will exit upon encountering exceptions in this method
	 * @param filePath
	 * @param enemyTanks
	 */
	public Map readMapFile(String filePath,ArrayList<Tank> enemyTanks) {
		Map gameMap = new Map();
		FileReader fr;
		int enemyTankCount = 0;
		int posX = -1,posY = -1;
		int tankNo = -1;
		boolean atTankDescription = false; // true if reader comes to the part where enemy tank count and coordinates are specified
		
		String numberPattern = "[0-9]";
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
					
					if(!atTankDescription){
						m = r.matcher(currentLine);
						//If we find a number it means that now we are in tank description line
						//First match is the tank count
						
						if(m.find()){
							atTankDescription = true;
							enemyTankCount = Integer.parseInt(m.group());
						}
						int groupCount = 0;
						
						while(m.find()){
							
							//Tank number
							if(groupCount % 3 == 0 )
								tankNo = Integer.parseInt(m.group());
							else if(groupCount %3 == 1)
								posX = Integer.parseInt(m.group());
							else if(groupCount %3 == 2){
								
								posY = Integer.parseInt(m.group());
								Tank newTank = new EnemyTank(posX,posY,0);
								enemyTanks.add(tankNo-1,newTank);
								
							}
							groupCount++;
						}
						
					}
					// If we are reading the map tiles
					else if(atTankDescription && !currentLine.equals("")){
						int tileNo = 0;
						m = r.matcher(currentLine);
						int columnCount = 0;
						while(m.find()){
							Tile newTile = null;
							tileNo = Integer.parseInt(m.group());
							if(tileNo == 0)
								newTile = null;
							else if(tileNo == 1)
								newTile = new BrickTile(rowCount, columnCount); 
							else if(tileNo == 2)
								newTile = new ConcreteTile(rowCount, columnCount); 
							else if(tileNo == 3)
								newTile = new WaterTile(rowCount, columnCount); 
							else if(tileNo == 4)
								newTile = new GrassTile(rowCount,columnCount);
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
			GameApplication.alertIOException();
		}
		return gameMap;
	}

	public FileManager getInstance() {
		if(theFileManager == null)
			theFileManager = new FileManager();
		return theFileManager;
	}

	private FileManager() {
		highScoreList = new ArrayList<Double>();
		
	}
	
	public Tile[][] getMapObjectList(){
		if(mapObjectList == null)
			return null;
		return mapObjectList;
	}
	
	public static void main(String[] args){
		FileManager test = new FileManager();
		ArrayList<Tank> tanks = new ArrayList<Tank>();
		Map gameMap = test.readMapFile("data/map/level1.txt", tanks);
	}

}