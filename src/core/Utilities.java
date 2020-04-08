package core;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Utilities extends CommonBoard{
	
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
	
	protected void unflipRandomCard(){
		String path = properties.getCoverPath();
		ImageIcon icon = new ImageIcon(path);
		display.get(index1).setIcon(icon);
		display.get(index1).setEnabled(true);
		index1 = -1;
	}
	
	protected boolean matched(JButton button){
		Card selected = null;
		for(int x = 0; x < display.size(); x++){
			if(display.get(x) == button){
				selected = cards.get(x);
				break;
			}
		}
		for(int y = 0; y < randomMatches.size(); y++){
			if(randomMatches.get(y) == selected){
				return true;
			}
		}
		
		return false;
	}
	
	protected boolean matched(Card card){
		for(int x = 0; x < randomMatches.size(); x++){
			if(randomMatches.get(x) == card){
				return true;
			}
		}
		return false;
	}
	
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
	
	protected void flipSecondCard(JButton selectedButton, int position){
		index2 = position;
		Card card = cards.get(index2);
		String path = card.getPath();
		ImageIcon icon = new ImageIcon(path);
		selectedButton.setIcon(icon);
		display.set(index2, selectedButton);
		animationCounter++;
	}
	
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
