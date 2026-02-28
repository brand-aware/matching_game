/**
 * @author wontzer
 * 
 * product of: brand-aware
 * 2019
 */
package core;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Utility class used to extrapolate non-essential
 * methods.  Extends CommondBoard as part of the
 * inheritance class structure to hide away unnecessary
 * variable declarations.
 */
public class Utilities extends CommonBoard{
	
	/**
	 * Selects a card at random and flip it over.
	 * Card must have "cover" displayed at the time
	 * of being selected, otherwise another card is
	 * picked.
	 */
	protected void flipRandomCard(){
		double maxSize = cards.size();
		int index = (int) (Math.random() * maxSize);
		Card card = cards.get(index);
		while(matched(card)){
			index = (int) (Math.random() * maxSize);
			card = cards.get(index);
		}
		String path = card.getPath();
		ImageIcon icon = new ImageIcon(path);
		display.get(index).setIcon(icon);
		display.get(index).setEnabled(false);
		index1 = index;
	}
	
	/**
	 * Flips card selected by a human in the random
	 * flip gameplay scenario
	 * 
	 */
	protected void flipRandomSelectedCard(){
		for(int x = 0; x < display.size(); x++){
			if(display.get(x) == pressedButton){
				Card card = cards.get(x);
				String path = card.getPath();
				ImageIcon icon = new ImageIcon(path);
				display.get(x).setIcon(icon);
				display.get(x).setEnabled(false);
				pressedButton = null;
				index2 = x;
				break;
			}
		}
	}
	
	/**
	 * When a player fails to make a selection,
	 * flips the randomly selected card back over automatically
	 */
	protected void unflipRandomCard(){
		String path = properties.getCoverPath();
		ImageIcon icon = new ImageIcon(path);
		display.get(index1).setIcon(icon);
		display.get(index1).setEnabled(true);
		index1 = -1;
	}
	
	/**
	 * Determines if selected card has already been
	 * matched during gameplay
	 * 
	 * @param JButton button
	 * @return boolean isMatched
	 */
	protected boolean matched(JButton button){
		Card selected = null;
		
		//finds which card from displayed portion of deck
		//was selected
		for(int x = 0; x < display.size(); x++){
			if(display.get(x) == button){
				selected = cards.get(x);
				break;
			}
		}
		//checks if card has already been set as a match - 
		//has been flipped randomly and successfully matched
		for(int y = 0; y < randomMatches.size(); y++){
			if(randomMatches.get(y) == selected){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * used by cpu to determine which cards are already flipped
	 * 
	 * @param Card card
	 * @return boolean isMatched
	 */
	protected boolean matched(Card card){
		for(int x = 0; x < randomMatches.size(); x++){
			if(randomMatches.get(x) == card){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * In non-random gameplay, first card selected by human is
	 * flipped.  index1 used to keep track of "first" card.
	 * 
	 * @param JButton selectedButton
	 * @param int position
	 */
	protected void flipFirstCard(JButton selectedButton, int position){
		index1 = position;
		Card card = cards.get(index1);
		String path = card.getPath();
		ImageIcon icon = new ImageIcon(path);
		selectedButton.setIcon(icon);
		display.set(index1, selectedButton);
		display.get(index1).setEnabled(false);
		move = false;
	}
	
	/**
	 * In non-random gameplay, second card selected by human is
	 * flipped.  index2 used to keep track of "second" card.  Animation
	 * counter is started in case it's needed.
	 * 
	 * @param JButton selectedButton
	 * @param int position
	 */
	protected void flipSecondCard(JButton selectedButton, int position){
		index2 = position;
		Card card = cards.get(index2);
		String path = card.getPath();
		ImageIcon icon = new ImageIcon(path);
		selectedButton.setIcon(icon);
		display.set(index2, selectedButton);
		animationCounter++;
	}
	
	/**
	 * Checks if game ending criteria has been met (all cards
	 * based on board size are matched and flipped over)
	 * 
	 * @return boolean isWon
	 */
	protected boolean won(){
		int progress = Integer.parseInt(matches.getText());
		if(normalFlag){
			if(progress == 26){
				return true;
			}
		}else if(smallFlag){
			if(progress == 14){
				return true;
			}
		}else if(smallerFlag){
			if(progress == 10){
				return true;
			}
		}
		return false;
	}

}
