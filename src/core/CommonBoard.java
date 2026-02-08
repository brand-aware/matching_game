/**
 * @author mike802
 * 
 * product of - ???
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
	
	protected JFrame boardPage;
	protected JDesktopPane cardArea = null;
	protected Properties properties;
	protected NameInput nameInput = null;
	
	protected Deck deck = null;
	protected ArrayList<Card> cards = new ArrayList<Card>();
	protected ArrayList<JButton> display = new ArrayList<JButton>();
	protected JButton start, stop, pause;
	protected JTextField matches, time;
	protected double lastCheck;
	//private ArrayList<String> unmatched;
	//private ArrayList<JButton> matched;
	protected JButton pressedButton;
	protected int index1, index2;
	
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
	protected int animationCounter = 0;
	protected int randomCounter1 = 0;
	protected int randomCounter2 = 0;
	
	protected boolean randomize = false;
	protected boolean normalFlag = true;
	protected boolean smallFlag = false;
	protected boolean smallerFlag = false;
	protected boolean randomFlip = false;
	protected int headerOffset = 0;

}
