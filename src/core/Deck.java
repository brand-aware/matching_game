package core;
/**
 * @author mike802
 * @version 1.0 - 2/28/2013
 */


import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class Deck {
		
	public Deck(){
		
	}
	
	private int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
	
	private String[] suits = {"clubs", "diamonds", "hearts", "spades"};
	
	private ConcurrentHashMap<Integer, ArrayList<String>> cards = new ConcurrentHashMap<Integer, ArrayList<String>>();
	
	public void deal(int number, String suit){
		if(cards.containsKey(number)){
			cards.get(number).add(suit);
		}else{
			cards.put(number, new ArrayList<String>());
			cards.get(number).add(suit);
		}
	}
	
	public boolean hasBeenDealt(int number, String suit){
		if(cards.containsKey(number)){
			ArrayList<String> dealtSuits = cards.get(number);
			for(int x = 0; x < dealtSuits.size(); x++){
				String dealt = dealtSuits.get(x);
				if(suit.compareTo(dealt) == 0){
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void shuffle(){
		cards = new ConcurrentHashMap<Integer, ArrayList<String>>();
	}
	
	public int[] getDeck(){
		return values;
	}
	
	public String[] getSuits(){
		return suits;
	}
}
