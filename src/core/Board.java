/**
 * @author mike802
 * 
 * product of - ???
 * 2017
 */
package core;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import balogging.BALoggerUtil;
import highscores.HighScores;
import highscores.IBoardOutline;
import highscores.NameInput;
import highscores.hsProperties;

public class Board extends Utilities implements IBoardOutline{
	
	private ButtonHandler handler;
	private MenuHandler menuHandler;
	
	public Board(){
		handler = new ButtonHandler();
		menuHandler = new MenuHandler();
		
		boardPage = new JFrame("matching_game");
	}
	
	private void createBoard(){
		boardPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String imageDir = properties.getImageDir();
		Image iconImage = Toolkit.getDefaultToolkit().getImage(imageDir + File.separator + "company.png");
		boardPage.setIconImage(iconImage);
		boardPage.setResizable(false);
		boardPage.setPreferredSize(new Dimension(800, 750));
		cardArea = new JDesktopPane();
		cardArea.setBounds(0, 0, 800, 750);
		String backgroundPath = properties.getBackground();
		ImageIcon background = new ImageIcon(backgroundPath);
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setIcon(background);
		backgroundLabel.setBounds(0, 0, 800, 750);
		cardArea.add(backgroundLabel);
		
		menuBar = new JMenuBar();
		file = new JMenu("file");
		options = new JMenu("options");
		difficulty = new JMenu("difficulty");
		help = new JMenu("help");
		
		exit = new JMenuItem("exit");
		exit.addActionListener(menuHandler);
		normal = new JCheckBoxMenuItem("normal");
		normal.addActionListener(menuHandler);
		normal.setSelected(true);
		small = new JCheckBoxMenuItem("small");
		small.addActionListener(menuHandler);
		smaller = new JCheckBoxMenuItem("smaller");
		smaller.addActionListener(menuHandler);
		randomSmall = new JCheckBoxMenuItem("small random");
		randomSmall.addActionListener(menuHandler);
		randomSmaller = new JCheckBoxMenuItem("smaller random");
		randomSmaller.addActionListener(menuHandler);
		about = new JMenuItem("about");
		about.addActionListener(menuHandler);
		
		file.add(exit);
		options.add(difficulty);
		difficulty.add(normal);
		difficulty.add(small);
		difficulty.add(smaller);
		difficulty.add(randomSmall);
		difficulty.add(randomSmaller);
		help.add(about);
		menuBar.add(file);
		menuBar.add(options);
		menuBar.add(help);
		boardPage.setJMenuBar(menuBar);
		
		JLabel logo = new JLabel();
		ImageIcon logoImage = new ImageIcon(properties.getLogo());
		int logox = (800 - 689) / 2;
		logo.setBounds(logox, 15, 689, 200);
		logo.setIcon(logoImage);
		
		int button1Xstart = (800 / 2) - (310 / 2);
		start = new JButton("start");
		start.setBounds(button1Xstart, 220, 100, 30);
		start.addActionListener(handler);
		
		stop = new JButton("stop");
		stop.setBounds(button1Xstart + 105, 220, 100, 30);
		stop.addActionListener(handler);
		stop.setEnabled(false);
		
		pause = new JButton("pause");
		pause.setBounds(button1Xstart + 210, 220, 100, 30);
		pause.setEnabled(false);
		pause.addActionListener(handler);
		
		int button2Xstart = (800 / 2) - (300 / 2);		
		JLabel matchesLabel = new JLabel("matches");
		matchesLabel.setBounds(button2Xstart, 255, 75, 30);
		matches = new JTextField("--");
		matches.setBounds(button2Xstart + 80, 255, 100, 30);
		matches.setEditable(false);
		
		JLabel timeLabel = new JLabel("time");
		timeLabel.setBounds(button2Xstart + 185, 255, 45, 30);
		time = new JTextField("--");
		time.setBounds(button2Xstart + 235, 255, 100, 30);
		time.setEditable(false);
		
		cardArea.add(logo);
		cardArea.add(start);
		cardArea.add(stop);
		cardArea.add(pause);
		cardArea.add(matchesLabel);
		cardArea.add(matches);
		cardArea.add(timeLabel);
		cardArea.add(time);
		
		cardArea.moveToFront(logo);
		cardArea.moveToFront(start);
		cardArea.moveToFront(stop);
		cardArea.moveToFront(pause);
		cardArea.moveToFront(matchesLabel);
		cardArea.moveToFront(matches);
		cardArea.moveToFront(timeLabel);
		cardArea.moveToFront(time);
		
		headerOffset = 290;
		
		loadCards();
		loadCoversNormal();
		
		boardPage.add(cardArea);
		boardPage.pack();
		boardPage.setVisible(true);
	}
	
	private class ButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			
			JButton newbutton = new JButton();
			Class<?> button = newbutton.getClass();
			Class<?> selected = event.getSource().getClass();
			if(event.getSource() == start){
				startGame();
			}else if(event.getSource() == stop){
				try {
					stopGame();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if(event.getSource() == pause){
				if(pause.getText().compareTo("pause") == 0){
					pause.setText("unpause");
				}else{
					lastCheck = System.currentTimeMillis();
					pause.setText("pause");
				}
			}else if(button == selected){
				if(!paused()){
					move = true;
					pressedButton = (JButton) event.getSource();
					if(randomize){
						if(!matched(pressedButton)){
							randomFlip = true;
						}
					}
				}
			}
		}
	}
	
	private class MenuHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == exit){
				System.exit(0);
			}else if(event.getSource() == normal){
				doNormalDifficulty();
			}else if(event.getSource() == small){
				doSmallDifficulty();
			}else if(event.getSource() == smaller){
				doSmallerDifficulty();
			}else if(event.getSource() == randomSmall){
				doSmallRandomDifficulty();
			}else if(event.getSource() == randomSmaller){
				doSmallerRandomDifficulty();
			}else if(event.getSource() == about){
				JOptionPane.showMessageDialog(null, 
						"product:\nmatching_game"
						+ "\n\nby\n???\n\ncontact:\n"
						+ "mike.drummond.802@hotmail.com", 
						"about", 
						JOptionPane.INFORMATION_MESSAGE, 
						new ImageIcon(properties.getImageDir() + File.separator + "company.png"));
			}
		}
	}
	
	private void doNormalDifficulty(){
		normalFlag = true;
		smallFlag = false;
		smallerFlag = false;
		randomize = false;
		small.setSelected(false);
		smaller.setSelected(false);
		randomSmall.setSelected(false);
		randomSmaller.setSelected(false);
		loadCards();
		loadCovers();
		startTime = 120;
	}
	
	private void doSmallDifficulty(){
		normalFlag = false;
		smallFlag = true;
		smallerFlag = false;
		randomize = false;
		loadCards();
		loadCovers();
		normal.setSelected(false);
		smaller.setSelected(false);
		randomSmall.setSelected(false);
		randomSmaller.setSelected(false);
		startTime = 45;
	}
	
	private void doSmallerDifficulty(){
		normalFlag = false;
		smallFlag = false;
		smallerFlag = true;
		randomize = false;
		loadCards();
		loadCovers();
		normal.setSelected(false);
		small.setSelected(false);
		randomSmall.setSelected(false);
		randomSmaller.setSelected(false);
		startTime = 20;
	}
	
	private void doSmallRandomDifficulty(){
		normalFlag = false;
		smallFlag = true;
		smallerFlag = false;
		randomize = true;
		loadCards();
		loadCovers();
		normal.setSelected(false);
		smaller.setSelected(false);
		small.setSelected(false);
		randomSmaller.setSelected(false);
		randomMatches = new ArrayList<Card>();
		startTime = 45;
	}
	
	private void doSmallerRandomDifficulty(){
		normalFlag = false;
		smallFlag = false;
		smallerFlag = true;
		randomize = true;
		loadCards();
		loadCovers();
		normal.setSelected(false);
		small.setSelected(false);
		randomSmall.setSelected(false);
		randomSmaller.setSelected(false);
		randomMatches = new ArrayList<Card>();
		startTime = 20;
	}
	
	public synchronized void doMove() throws IOException{
		if(started){
			updateTime();
		}
		if(move && !paused()){
			if(!randomize){
				for(int x = 0; x < display.size(); x++){
					JButton selectedButton = display.get(x);
					if(pressedButton == selectedButton){
						if(index1 == -1){
							flipFirstCard(selectedButton, x);
						}else{
							if(animationCounter == 0){
								flipSecondCard(selectedButton, x);
							}else if(animationCounter >= 15){
								decideOutcome();
							}else if(animationCounter < 15){
								animationCounter++;
							}
						}
					}
				}
			}else{
				if(randomCounter1 == 0){
					flipRandomCard();
					randomCounter1++;
				}else if(randomCounter1 > 15){
					if(randomCounter2 == 0){
						unflipRandomCard();
						randomCounter1 = 0;
					}
				}else{
					randomCounter1++;
				}
				
				if(randomFlip){
					if(randomCounter2 == 0){
						flipRandomSelectedCard();
						randomCounter2++;
					}else if(randomCounter2 > 15){
						decideRandomOutcome();
						randomFlip = false;
					}else{
						randomCounter2++;
					}
				}
			}
		}
	}
	
	private void decideOutcome() throws IOException{
		Card card1 = cards.get(index1);
		Card card2 = cards.get(index2);
		
		if(card1.getNumber() == card2.getNumber()){
			displayMatch();							
		}else{
			clearMismatch();
		}
		index1 = -1;
		index2 = -1;
		animationCounter = 0;
		move = false;
	}
	
	private void decideRandomOutcome() throws IOException{
		Card card1 = cards.get(index1);
		Card card2 = cards.get(index2);
		if(card1.getNumber() == card2.getNumber()){
			displayMatch();
			randomMatches.add(card1);
			randomMatches.add(card2);
		}else{
			clearMismatch();
		}
		index1 = -1;
		index2 = -1;
		randomCounter1 = 0;
		randomCounter2 = 0;
	}
	
	private void displayMatch() throws IOException{
		if(!randomize){
			display.get(index1).setEnabled(false);
			display.get(index2).setEnabled(false);
		}
		int matchScore = Integer.parseInt(matches.getText());
		matchScore++;
		matches.setText("" + matchScore);
		double currentTime = Double.parseDouble(time.getText());
		currentTime += matchBonus;
		time.setText("" + currentTime);
		
		if(won()){
			if(nameInput == null){
				nameInput = new NameInput(properties.getRootDir(), this);
			}
			double endScore = Double.parseDouble(time.getText());
			nameInput.setDescending();
			nameInput.init(endScore);
			cardArea.add(nameInput);
			cardArea.moveToFront(nameInput);
			stopGame();
			disable();
		}
	}
	
	private void clearMismatch(){
		display.get(index1).setEnabled(true);
		ImageIcon icon = new ImageIcon(properties.getCoverPath());
		display.get(index1).setIcon(icon);
		
		display.get(index2).setEnabled(true);
		icon = new ImageIcon(properties.getCoverPath());
		display.get(index2).setIcon(icon);
	}
	
	private void updateTime(){
		double currentScore = 0;
		if(!paused()){
			currentScore = Double.parseDouble(time.getText());
			double currentTime = System.currentTimeMillis();
			
			double difference = currentTime - lastCheck;
			difference = difference * .001;
			currentScore -= difference;
			DecimalFormat formatter = new DecimalFormat(".##");
			time.setText("" + formatter.format(currentScore));
			lastCheck = currentTime;
		}
		if (currentScore < 0){
			try {
				stopGame();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private boolean paused(){
		if(pause.getText().compareTo("unpause") == 0){
			return true;
		}
		return false;
	}
	
	private void startGame(){
		balogger.startTimer();
		lastCheck = System.currentTimeMillis();
		pause.setEnabled(true);
		stop.setEnabled(true);
		start.setEnabled(false);
		time.setText("" + startTime);
		started = true;
		matches.setText("0");
		for(int x = 0; x < display.size(); x++){
			display.get(x).setEnabled(true);
		}
		if(randomize){
			move = true;
		}
	}
	
	private void stopGame() throws IOException{
		balogger.stopTimer();
		balogger.logScore("", matches.getText());
		started = false;
		move = false;
		lastCheck = 0;
		time.setText("--");
		matches.setText("--");
		deck = null;
		cards = null;
		pause.setEnabled(false);
		pause.setText("pause");
		stop.setEnabled(false);
		start.setEnabled(true);
		loadCards();
		loadCovers();
		if(randomize){
			randomCounter1 = 0;
			randomCounter2 = 0;
			randomFlip = false;
		}else{
			animationCounter = 0;
			index1 = -1;
			index2 = -1;
		}
	}
	
	private final void loadCards(){
		if(deck == null){
			deck = new Deck();
			cards = new ArrayList<Card>();
		}else{
			deck.shuffle();
			cards = new ArrayList<Card>();
		}
		clearBoard();
		if(normalFlag){
			loadCardsNormal();
		}else if(smallFlag){
			loadCardsSmall();
		}else if(smallerFlag){
			loadCardsSmaller();
		}
	}
	
	private final void loadCardsNormal(){
		int count = 0;
		while(count < 52){
			int[] deckNumbers = deck.getDeck();
			String[] suits = deck.getSuits();
			
			int numNumbers = deckNumbers.length;
			int numSuits = suits.length;
			
			int number1 = (int) (numNumbers * Math.random());
			int number2 = (int) (numSuits * Math.random());
			
			if(!deck.hasBeenDealt(deckNumbers[number1], suits[number2])){
				deck.deal(deckNumbers[number1], suits[number2]);
				cards.add(new Card(deckNumbers[number1], suits[number2], properties));
				count++;
			}
		}
	}
	
	private final void loadCardsSmall(){
		int count = 0;
		while(count < 28){
			int[] deckNumbers = deck.getDeck();
			String[] suits = deck.getSuits();
			
			int numSuits = suits.length;
			
			int number1, number2;
			number1 = (int) (7 * Math.random());
			number2 = (int) (numSuits * Math.random());
			
			if(!deck.hasBeenDealt(deckNumbers[number1], suits[number2])){
				deck.deal(deckNumbers[number1], suits[number2]);
				cards.add(new Card(deckNumbers[number1], suits[number2], properties));
				count++;
			}
		}
	}
	
	private final void loadCardsSmaller(){
		int count = 0;
		while(count < 20){
			int[] deckNumbers = deck.getDeck();
			String[] suits = deck.getSuits();
			
			int numSuits = suits.length;
			
			int number1, number2;
			number1 = (int) (5 * Math.random());
			number2 = (int) (numSuits * Math.random());
			
			if(!deck.hasBeenDealt(deckNumbers[number1], suits[number2])){
				deck.deal(deckNumbers[number1], suits[number2]);
				cards.add(new Card(deckNumbers[number1], suits[number2], properties));
				count++;
			}
		}
	}
	
	private void clearBoard(){
		for(int x = 0; x < display.size(); x++){
			cardArea.moveToBack(display.get(x));
		}
	}

	private void loadCovers(){
		clearBoard();
		display = new ArrayList<JButton>();
		if(normalFlag){
			loadCoversNormal();
		}else if(smallFlag){
			loadCoversSmall();
		}else if(smallerFlag){
			loadCoversSmaller();
		}
	}
	
	private void loadCoversNormal(){
		int xoffset = (800 / 2) - (13 * 55 / 2) - (12 * 5 / 2) - 10;
		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 13; y++){
				String path = properties.getCoverPath();
				ImageIcon icon = new ImageIcon(path);
				JButton card = new JButton();
				card.setIcon(icon);
				int xcoord = 5 + (y * 55) + (y * 5) + xoffset;
				int ycoord = (x * (95 + 5)) + headerOffset;
				card.setBounds(xcoord, ycoord, 55, 95);
				card.addActionListener(handler);
				cardArea.add(card);
				cardArea.moveToFront(card);
				card.setEnabled(false);
				display.add(card);
			}
		}
	}
	
	private void loadCoversSmall(){
		int xoffset = (800 / 2) - (7 * 55 / 2) - (6 * 5 / 2) - 10;
		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 7; y++){
				String path = properties.getCoverPath();
				ImageIcon icon = new ImageIcon(path);
				JButton card = new JButton();
				card.setIcon(icon);
				int xcoord = 5 + (y * 55) + (y * 5) + xoffset;
				int ycoord = (x * (95 + 5)) + headerOffset;
				card.setBounds(xcoord, ycoord, 55, 95);
				card.addActionListener(handler);
				cardArea.add(card);
				cardArea.moveToFront(card);
				card.setEnabled(false);
				display.add(card);
			}
			System.out.println();
		}
	}
	
	private void loadCoversSmaller(){
		int xoffset = (800 / 2) - (5 * 55 / 2) - (4 * 5 / 2) - 10;
		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 5; y++){
				String path = properties.getCoverPath();
				ImageIcon icon = new ImageIcon(path);
				JButton card = new JButton();
				card.setIcon(icon);
				int xcoord = 5 + (y * 55) + (y * 5) + xoffset;
				int ycoord = (x * (95 + 5)) + headerOffset;
				card.setBounds(xcoord, ycoord, 55, 95);
				card.addActionListener(handler);
				cardArea.add(card);
				cardArea.moveToFront(card);
				card.setEnabled(false);
				display.add(card);
			}
		}
	}
	
	public final boolean initialized(){
		return initialized;
	}
	public void init(Properties p){
		properties = p;
		balogger = new BALoggerUtil(properties.getRootDir(), "matching_game");
		if(!initialized){
			createBoard();
			index1 = -1;
			index2 = -1;
			initialized = true;
		}
	}
	
	@Override
	public void init() {
		init(properties);
	}

	@Override
	public void enable() {
		start.setEnabled(true);
		file.setEnabled(true);
		options.setEnabled(true);
		help.setEnabled(true);
	}
	
	public void disable(){
		start.setEnabled(false);
		file.setEnabled(false);
		options.setEnabled(false);
		help.setEnabled(false);
	}

	@Override
	public void initHighScores(hsProperties props) {
		HighScores highScores = new HighScores(this);
		try {
			highScores.init(props);
			cardArea.add(highScores);
			cardArea.moveToFront(highScores);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void initHighScores(String name, String rank, int score, hsProperties props) {
		HighScores highScores = new HighScores(this);
		try {
			highScores.init(name, rank, score, props);
			cardArea.add(highScores);
			cardArea.moveToFront(highScores);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
