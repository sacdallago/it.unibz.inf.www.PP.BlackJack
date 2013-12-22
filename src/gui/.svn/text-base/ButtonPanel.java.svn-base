package gui;
/*
 * Button Class (containing the main method)
 * 
 * Version 0.4
 * 
 * Created 16/03/2012
 * 
 * �2012 This class and the following code is under copyright of the FUB and meant for internal use only.
 */

import java.awt.Component;
import utility.*;
import core.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ButtonPanel extends JPanel {
	
	private static JButton hit;
	private static JButton stand;
	private static JButton surrender;
	private static JButton bet;
	public static JSlider betSlider;
	private ActionListener listen = new ButtonListener();
	private ChangeListener slider = new SliderListener();

	/**
	 * This method initializes a new object of type ButtonPanel and adds an ActionListener to the three new JButtons 
	 * for hit, stand, surrender and bet, and to a new object of type JSlider indicating the amount which the actual 
	 * player wants to bet. After this, those five components are added to the new object of this class.
	 */
	public ButtonPanel() {
		
		hit = new JButton("Hit");
		stand = new JButton("Stand");
		surrender = new JButton("Surrender");
		bet = new JButton("Bet");
		
		betSlider = new JSlider(JSlider.HORIZONTAL, 0, 1000, 0);
		betSlider.setMajorTickSpacing(500);
		betSlider.setMinorTickSpacing(20);
		betSlider.setPaintLabels(true);
		betSlider.setPaintTicks(true);
		
		hit.addActionListener(listen);
		stand.addActionListener(listen);
		surrender.addActionListener(listen);
		bet.addActionListener(listen);
		
		betSlider.addChangeListener(slider);
		
		this.add(betSlider);
		this.add(bet);
		this.add(hit);
		this.add(stand);
		this.add(surrender);
		
	}
	
	/**
	 * This method adapts the four buttons bet, hit, stand, surrender, the slider betSlider and the two menu bars newGame
	 * and save of the BlackJack class to the actual game-situation by enabling them or not.
	 * 
	 * @param enableCase An integer representing 0 in the case that the actual player is forced to bet, 1 in the case
	 * that the actual player can choose every possible action, 2 in the case that the actual player is the ai and 3
	 * in the case that the actual player is the dealer.
	 */
	public static void enableButtons(int enableCase) {
		
		switch (enableCase){
			case 0:
			bet.setEnabled(true);
			hit.setEnabled(false);
			stand.setEnabled(false);
			surrender.setEnabled(false);
			betSlider.setEnabled(true);
			BlackJack.newGame.setEnabled(true);
			BlackJack.save.setEnabled(true);
			break;
		
			case 1:
			bet.setEnabled(false);
			hit.setEnabled(true);
			stand.setEnabled(true);
			surrender.setEnabled(true);
			betSlider.setEnabled(false);
			BlackJack.save.setEnabled(false);
			break;
		
			case 2:
			bet.setEnabled(false);
			hit.setEnabled(false);
			stand.setEnabled(false);
			surrender.setEnabled(false);
			betSlider.setEnabled(false);
			BlackJack.newGame.setEnabled(false);
			BlackJack.save.setEnabled(false);
			break;
		
			case 3:
			bet.setEnabled(false);
			hit.setEnabled(true);
			stand.setEnabled(true);
			surrender.setEnabled(false);
			betSlider.setEnabled(false);
			BlackJack.save.setEnabled(true);
			break;
		}
		
	}
	
	private static class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			
			if (event.getSource() == hit) {
				Controller.playGame(1);
			}
			if (event.getSource() == stand) {
				Controller.playGame(2);
			}
			if (event.getSource() == surrender) {
				Controller.playGame(3);
			}
			if (event.getSource() == bet) {
				Controller.playGame(4);
			}
			
		}
		
	}
	
	private class SliderListener implements ChangeListener {

		public void stateChanged(ChangeEvent arg0) {
			
			Controller.playGame(betSlider.getValue());
			
		}
		
	}
	
}
