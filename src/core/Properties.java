package core;
/**
 * @author mike802
 * @version 1.0 - 9/21/2013
 */

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

public class Properties{
	
	private String coverPath;
	private String emptyPath;
	private ConcurrentHashMap<String, String> suitPaths = 
			new ConcurrentHashMap<String, String>();
	
	private String logo;
	private String rootDir;
	private String background;
	private String highScores;
	
	private Board board;
	
	public Properties(String dir){
		rootDir = dir;
		coverPath = rootDir + File.separator + "cards" + File.separator + "cover.png";
		emptyPath = rootDir + File.separator + "cards" + File.separator + "empty.png";
		
		String cards = rootDir + File.separator + "cards" + File.separator;
		suitPaths.put("clubs", cards + "clubs");
		suitPaths.put("diamonds", cards + "diamonds");
		suitPaths.put("hearts", cards + "hearts");
		suitPaths.put("spades", cards + "spades");
		
		logo = rootDir + File.separator + "img" + File.separator + "logo.png";
		background = rootDir + File.separator + "img" + File.separator + "background.png";
		highScores = rootDir + "high_scores.txt";
		
	}
	
	public String getRootDir(){
		return rootDir;
	}
	
	public String suitPath(String suit){
		return suitPaths.get(suit);
	}
	public String getCoverPath(){
		return coverPath;
	}
	public String getEmptyPath(){
		return emptyPath;
	}
	public String getImageDir(){
		return rootDir + File.separator + "img";
	}
	public String getLogo(){
		return logo;
	}
	public String getBackground(){
		return background;
	}
	public String getHighScores(){
		return highScores;
	}
	public void setBoard(Board b){
		board = b;
	}
	public Board getBoard(){
		return board;
	}
}
