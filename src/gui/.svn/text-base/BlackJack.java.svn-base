package gui;
/*
 * BlackJack Class (containing the main method)
 * 
 * Version 0.4
 * 
 * Created 16/03/2012
 * 
 * �2012 This class and the following code is under copyright of the FUB and meant for internal use only.
 */

import java.awt.*;
import utility.*;
import core.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.Date;
import javax.swing.*;

public class BlackJack{

	public static JMenu newGame = new JMenu("New Game");
	public static JMenuItem save = new JMenuItem("Save game");
	public static TablePanel tablePane = new TablePanel();
	public static Controller controller;
	protected static JMenuItem threeMultiPlayers;
	protected static JMenuItem fourMultiPlayers;
	protected static JMenuItem fiveMultiPlayers;
	protected static JMenuItem threeAIPlayers;
	protected static JMenuItem fourAIPlayers;
	protected static JMenuItem fiveAIPlayers;
	protected static JMenuItem reload;
	protected static JMenuItem exit;
	protected static JMenuItem replay;
	protected static JMenuItem highScore;
	protected static JMenuItem about;
	protected static JMenuItem version;
    private static JPanel panel = new JPanel();
    private static JFrame frame;
    private static ButtonPanel buttonPane = new ButtonPanel();
    private static ImageIcon IntroImg;
    private static JLabel IntroLabel;
    private static int NrOfPlayers = 0;
    private static ActionListener listen;

    public static void main(String[] args) {
    	
        setListen(new Listener());
        JMenuBar bar = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        bar.add(menu);

        //New game options---------------------------
        menu.add(newGame);
        
        JMenu singleVsAi = new JMenu("Single Player vs. AI");
        newGame.add(singleVsAi);
        
        //Multiplayer menu---------------------------
        JMenu multiplayer = new JMenu("Multiplayer");
        newGame.add(multiplayer);
        
        setThreePlayers(new JMenuItem("Three Players"));
        multiplayer.add(getThreePlayers());
    	getThreePlayers().addActionListener(getListen());
    	
    	fourMultiPlayers = new JMenuItem("Four Players");
    	multiplayer.add(fourMultiPlayers);
    	fourMultiPlayers.addActionListener(getListen());
    	
    	fiveMultiPlayers = new JMenuItem("Five Players");
    	multiplayer.add(fiveMultiPlayers);
    	fiveMultiPlayers.addActionListener(getListen());
    	
    	threeAIPlayers = new JMenuItem("Three Players");
    	singleVsAi.add(threeAIPlayers);
    	threeAIPlayers.addActionListener(getListen());
    	
    	fourAIPlayers = new JMenuItem("Four Players");
    	singleVsAi.add(fourAIPlayers);
    	fourAIPlayers.addActionListener(getListen());
    	
    	fiveAIPlayers = new JMenuItem("Five Players");
    	singleVsAi.add(fiveAIPlayers);
    	fiveAIPlayers.addActionListener(getListen());

        //General Menu---------------------------
    	reload = new JMenuItem("Load saved game");
    	menu.add(reload);
    	reload.addActionListener(getListen());
    	
    	save.setEnabled(false);
    	menu.add(save);
    	save.addActionListener(getListen());
    	
    	exit = new JMenuItem("Exit");
    	menu.add(exit);
    	exit.addActionListener(getListen());

        //Game options---------------------------
        JMenu gameOp = new JMenu("Game options");
        bar.add(gameOp);

        replay = new JMenuItem("Replay best game");
        gameOp.add(replay);
    	replay.addActionListener(getListen());
    	
    	highScore = new JMenuItem("View high score");
        gameOp.add(highScore);
        highScore.addActionListener(getListen());

        //?---------------------------
        JMenu general = new JMenu("?");
        bar.add(general);
        
        about = new JMenuItem("About");
        general.add(about);
        about.addActionListener(getListen());
        
        version = new JMenuItem("Version");
        general.add(version);
        version.addActionListener(getListen());

        //General frame and opening settings---------------------------
        JPanel welcomePane = new JPanel();
        welcomePane.setBorder(BorderFactory.createRaisedBevelBorder());
        
        IntroImg = new ImageIcon("BlackJackImages/Intro-Window.jpg");
        IntroLabel = new JLabel(IntroImg);
        welcomePane.add(IntroLabel);

        panel.add(welcomePane);
        panel.setLayout(new GridLayout());

        setFrame(new JFrame());
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getFrame().setJMenuBar(bar);
        getFrame().getContentPane().add(panel, BorderLayout.CENTER);
        getFrame().pack();
        getFrame().setBounds(20, 20, 1000, 780);
        getFrame().setTitle("Blackjack by M.L.F., M.A. and C.D.");
        getFrame().setVisible(true);
        
    }

    private static class Listener implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
        	
            if (exit.isArmed()) {
                int key = JOptionPane.showConfirmDialog(getFrame(), "Press 'Yes' to save the current game and exit, 'No' to exit or 'Cancel' to resume the game.", "Save and exit?", JOptionPane.YES_NO_CANCEL_OPTION);
                if (key == 0) {
                	SaveAndLoad.save();
                    System.exit(0);
                }
                if (key == 1) {
                    System.exit(0);
                }
            }
            else if (save.isArmed()) {
            	SaveAndLoad.save();
            }
            else if (reload.isArmed()) {
            	SaveAndLoad.load();
            }
            else if (getThreePlayers().isArmed()) {
				setNrOfPlayers(3);
				setUpMultiplayerGame();
			}
			else if (fourMultiPlayers.isArmed()) {
				setNrOfPlayers(4);
				setUpMultiplayerGame();
			}
			else if (fiveMultiPlayers.isArmed()) {
				setNrOfPlayers(5);
				setUpMultiplayerGame();
			}
			else if (threeAIPlayers.isArmed()) {
				setNrOfPlayers(3);
				setUpAiGame();
			}
			else if (fourAIPlayers.isArmed()) {
				setNrOfPlayers(4);
				setUpAiGame();
			}
			else if (fiveAIPlayers.isArmed()) {
				setNrOfPlayers(5);
				setUpAiGame();
			}
			else if (version.isArmed()) {
                JOptionPane.showMessageDialog(getFrame(), "Version release:\nAlpha 0.0.1");
            }
			else if (about.isArmed()) {
                JOptionPane.showMessageDialog(getFrame(), "Blackjack by M.L.F., M.A. and C.D.");
            }
            else {
            	notImplemented();
            }
        }
    }
    
    /**
	 * This method is used to set up the main components of the GUI.
	 */
    public static void setUpGame() {
    	
    	panel.removeAll();
		panel.add(tablePane);
		panel.updateUI();
		getFrame().getContentPane().add(buttonPane, BorderLayout.SOUTH);
    	
    }
    
    /**
	 * This method is used to initialize an object of the class controller (for the multiplayer - mode).
	 */
    public static void setUpMultiplayerGame() {
    	
    	setUpGame();
    	
    	try {
    		AiController.timer.stop();
    	} catch (Exception ex) {
    		System.err.println("Timer has never been initialised.");
    	}
		controller = new Controller(tablePane, getNrOfPlayers(), false);
		
    }
    
    /**
	 * This method is used to initialize an object of the class AiController (for the single player vs. cpu - mode).
	 */
    public static void setUpAiGame() {
    	
    	setUpGame();
    	controller = new Controller(tablePane, getNrOfPlayers(), true);
    	
    }

	/**
	 * This method is used to return an object of type JFrame where the whole gui of the game is drawn on.
	 * 
	 * @return An object of type JFrame representing the frame of the gui.
	 */
	public static JFrame getFrame() {
		return frame;
	}

	/**
	 * This method assigns an object of type JFrame.
	 * 
	 * @param frame An object of type JFrame representing the frame of the gui.
	 */
	public static void setFrame(JFrame frame) {
		BlackJack.frame = frame;
	}

	/**
	 * This method returns an object of type JMenuItem which is used to build up a multiplayer-game for three players.
	 * 
	 * @return An object of type JMenuItem representing the menu item for the three players-multiplayer game.
	 */
	public static JMenuItem getThreePlayers() {
		return threeMultiPlayers;
	}

	/**
	 * This method sets up an object of type JMenuItem which is used to build up a game for three players.
	 * 
	 * @param threePlayers An object of type JMenuItem representing the menu item for the three players game.
	 */
	public static void setThreePlayers(JMenuItem threePlayers) {
		BlackJack.threeMultiPlayers = threePlayers;
	}

	/**
	 * This method returns an object of type ActionListener which reacts to all the menu items of the menu bar.
	 * 
	 * @return An object of type ActionListener for the reaction to all the menu items of the menu bar.
	 */
	public static ActionListener getListen() {
		return listen;
	}

	/**
	 * This method assigns an object of type ActionListener to the 'listen' - ActionListener controlling the actions
	 * performed on the menu bar.
	 * 
	 * @param listen An object of type ActionListener representing the ActionListener controlling the actions
	 * performed on the menu bar.
	 */
	public static void setListen(ActionListener listen) {
		BlackJack.listen = listen;
	}

	/**
	 * This method returns the number of players participating in the game.
	 * 
	 * @return An integer representing the number of players participating in the game.
	 */
	public static int getNrOfPlayers() {
		return NrOfPlayers;
	}

	/**
	 * This method assigns the number of players participating in the game.
	 * 
	 * @param nrOfPlayers An integer representing the number of players participating in the game.
	 */
	public static void setNrOfPlayers(int nrOfPlayers) {
		NrOfPlayers = nrOfPlayers;
	}
	
	/**
	 * Internal method that notifies of not yet implemented features.
	 */
	private static void notImplemented() {
		
		JOptionPane.showMessageDialog(frame, "This feature is not available in the free version of the game.");
		
	}
    
}