package core;
/**
 * @author wontzer
 * @version 1.0 - 9/21/2013
 */


import java.io.File;

public class Card {
	
	private int number;
	private String suit;
	
	private String filename;
	private String path;
	
	private Properties properties;
	
	public Card (int num, String s, Properties p){
		number = num;
		suit = s;
		properties = p;
		
		if(number < 11){
			filename = number + "_" + suit;
		}else if(number == 11){
			filename = "J_" + suit;
		}else if(number == 12){
			filename = "Q_" + suit;
		}else if(number == 13){
			filename = "K_" + suit;
		}else if(number == 14){
			filename = "A_" + suit;
		}
		
		path = properties.suitPath(suit) + File.separator + filename + ".png";
	}
	
	public int getNumber(){
		return number;
	}
	public String getSuit(){
		return suit;
	}
	
	public String getPath(){
		return path;
	}
	public String getDetails(){
		String details = "";
		
		if(number < 11){
			details = number + " of " + suit;
		}else if(number == 11){
			details = "Jack of " + suit;
		}else if(number == 12){
			details = "Queen of " + suit;
		}else if(number == 13){
			details = "King of " + suit;
		}else if(number == 14){
			details = "Ace of " + suit;
		}
		return details;
	}
}
