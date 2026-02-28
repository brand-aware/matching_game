/**
 * @author wontzer
 * 
 * product of - brand-aware
 * 2017
 */
package core;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import highscores.NameInput;

public class CommonBoard {
	
	// animation "secret sauce"
	protected JFrame boardPage;
	protected JDesktopPane cardArea = null;
	protected Properties properties;
	
	// used for score keeping
	protected NameInput nameInput = null;
	
	// standard deck of cards and associated buttons/displays
	protected Deck deck = null;
	protected ArrayList<Card> cards = new ArrayList<Card>();
	protected ArrayList<JButton> display = new ArrayList<JButton>();
	
	// action buttons found on main board screen
	protected JButton start, stop, pause;
	// main display for game activity
	protected JTextField matches, time;
	
	
	protected double lastCheck;
	protected JButton pressedButton;
	
	// first and second (respectively) cards selected by
	// human in non-random gameplay
	protected int index1, index2;
	
	// gameplay modifiers (shouldn't be changed)
	protected int matchBonus = 10;
	protected int startTime = 120;
	
	protected boolean initialized = false;
	protected boolean started = false;
	protected boolean move = false;
	
	protected JMenuBar menuBar;
	protected JMenu file, options, difficulty, help;
	protected JMenuItem exit, about;
	protected JCheckBoxMenuItem normal, small, smaller, randomSmall, 
		randomSmaller;
	
	protected ArrayList<Card> randomMatches;
	
	// helps animations look natural for non-random
	// card flipping
	protected int animationCounter = 0;
	// animation counter for cpu or randomly flipped
	// card
	protected int randomCounter1 = 0;
	// animation counter for human selected card in
	// randomization scenario
	protected int randomCounter2 = 0;
	
	//determines if randomized card flipping will take place
	protected boolean randomize = false;
	
	// flags determining board size
	protected boolean normalFlag = true;
	protected boolean smallFlag = false;
	protected boolean smallerFlag = false;
	
	// flags that the user has selected a card in the
	// random flip scenario
	protected boolean randomFlip = false;
	protected int headerOffset = 0;

}
