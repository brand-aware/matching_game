package core;
/**
 * @author wontzer
 * @version 1.0 - 9/21/2013
 */

import java.io.File;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

public class Properties{
	
	private URL coverPath;
	private URL emptyPath;
	private ConcurrentHashMap<String, String> suitPaths = 
			new ConcurrentHashMap<String, String>();
	
	private URL logo;
	private URL background;
	private String highScores;
	private String rootDir;
	
	private Board board;
	
	public Properties(String dir){
		rootDir = dir;
		// Determines where game is located and finds image folder
		coverPath = getClass().getResource("/cards/cover.png");
		emptyPath = getClass().getResource("/cards/empty.png");
		
		String cards = "/cards/";
		suitPaths.put("clubs", cards + "clubs");
		suitPaths.put("diamonds", cards + "diamonds");
		suitPaths.put("hearts", cards + "hearts");
		suitPaths.put("spades", cards + "spades");
		
		logo = getClass().getResource("/img/logo.png");
		background = getClass().getResource("/img/background.png");
		String companyPath = System.getProperty("user.home") + File.separator + "AppData" + File.separator + "Local"
				+ File.separator + "brand-aware";
		
		File companyFolder = new File(companyPath);
		if(!companyFolder.exists()) {
			companyFolder.mkdir();
		}
		
		String productPath = companyPath + File.separator + "matching_game";
		File productFolder = new File(productPath);
		if(!productFolder.exists()) {
			productFolder.mkdir();
		}
		highScores = productPath + File.separator + "highScores.txt";
	}
	
	public String getRootDir(){
		return rootDir;
	}
	
	public String suitPath(String suit){
		return suitPaths.get(suit);
	}
	public URL getCoverPath(){
		return coverPath;
	}
	public URL getEmptyPath(){
		return emptyPath;
	}
	public String getImageDir(){
		return "/img/";
	}
	public URL getLogo(){
		return logo;
	}
	public URL getBackground(){
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
