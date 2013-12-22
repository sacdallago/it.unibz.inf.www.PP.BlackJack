package gui;
/*
 * TablePanel Class
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
import java.util.*;
import javax.swing.*;

public class TablePanel extends JPanel {
	
	private Image table;
	private int NrOfPlayers;
	private LinkedList<ThePlayer> Playerlist;
	private Image Backside;
	private Image Arrow;
	private int actualPlayerIndex;
	
	/**
	 * This method initializes a new object of type TablePanel by assigning an object of type Image to the table, to
	 * the backside of the cards and to the arrow which indicates the actual player.
	 */
	public TablePanel() {
		
		table = Toolkit.getDefaultToolkit().getImage("BlackJackImages/Table/full.jpg");
		Backside = Toolkit.getDefaultToolkit().getImage("cards/Backside.png");
		Arrow = Toolkit.getDefaultToolkit().getImage("BlackJackImages/Arrow-Down.png");
		
	}
	
	/**
	 * This method updates the Playerlist in order to repaint the actual game situation on the game-table.
	 *
	 * @param Playerlist A LinkedList which contains the players.
	 */
	public void paintCards(LinkedList<ThePlayer> Playerlist) {
		
		this.Playerlist = Playerlist;
		this.repaint();
		
	}
	
	/**
	 * This method updates the index of the actual player, in order to paint an arrow over his position (in the case
	 * that the actual player is the dealer, the arrow is painted under his position).
	 *
	 * @param actualPlayer  An integer indicating the index of the actual player.
	 */
	public void paintArrow(int actualPlayer) {
		
		actualPlayerIndex = actualPlayer;
		if (actualPlayer == Controller.PlayerList.size() - 1) {
			Arrow = Toolkit.getDefaultToolkit().getImage("BlackJackImages/Arrow-Up.png");
		} else {
			Arrow = Toolkit.getDefaultToolkit().getImage("BlackJackImages/Arrow-Down.png");
		}
		this.repaint();
		
	}
	
	/**
	 * This method draws the whole graphical interface of the actual game situation.
	 *
	 * @param graphic An object of type Graphics which is used as graphical interface in order to draw on.
	 */
	public void paint(Graphics graphic) {
		
		int i = 0;
		int j = 0;
		int q = 0;
		int h = 0;
		
		graphic.drawImage(table, 0, 0, this.getWidth(), this.getHeight(), this);
		
		graphic.drawImage(Backside, this.getWidth() / 2 - 100, (int) (this.getHeight() / 2.8), 80, 110, this);
		
		for(Card c : Playerlist.getLast().getDrawedCards()) {
			q++;
			graphic.drawImage(c.getPicture(), this.getWidth() / 2 + q * 20, (int) (this.getHeight() / 2.8), 80, 110, this);
		}
		graphic.setColor(Color.WHITE);
		graphic.drawString("Player " + Playerlist.getLast().getPlayerID(), this.getWidth() / 2 + 20, (int) (this.getHeight() / 2.8) + 150);
		graphic.setColor(Color.BLACK);
		
		paintArrow(graphic);
		
		graphic.drawString("Sum of cards: " + Playerlist.getLast().getSumOfCards(), this.getWidth() / 2 +  20, (int) (this.getHeight() / 1.7 - 25));
		
		for(ThePlayer player : Playerlist) {
			if (player.isDealer() == false) {
				i++;
				j = 0;
				for(Card c : player.getDrawedCards()) {
					graphic.drawImage(c.getPicture(), this.getWidth() / (Controller.NrOfPlayers) * i + j * 20 - 20, (int) (this.getHeight() / 1.5), 80, 110, this);
					j++;
				}
				paintPlayerInformations(graphic, player, i);
				h++;
			}
		}
		
	}
	
	/**
	 * This method draws the arrow (which indicates the actual player) on the game table.
	 *
	 * @param graphic An object of type Graphics which is used as graphical interface in order to draw on.
	 */
	public void paintArrow(Graphics graphic) {
		
		if (actualPlayerIndex == Controller.PlayerList.size() - 1) {
			graphic.drawImage(Arrow, this.getWidth() / 4 * 2, this.getHeight() / 2 + 30, 80, 100, this);
		} else {
			graphic.drawImage(Arrow, this.getWidth() / (Controller.NrOfPlayers) * (actualPlayerIndex + 1), this.getHeight() / 2 + 40, 80, 100, this);
		}
		
	}
	
	/**
	 * This method draws the informations on the game table, which simplify the game for the players.
	 *
	 * @param graphic An object of type Graphics which is used as graphical interface in order to draw on.
	 * @param p The player whose informations are drawn on the table.
	 * @param i An integer which is needed for placing the informations on the right position of the table.
	 */
	public void paintPlayerInformations(Graphics graphic, ThePlayer player, int i) {
		
		graphic.setColor(Color.BLACK);
		graphic.drawString("Sum of cards: " + player.getSumOfCards(), this.getWidth() / (Controller.NrOfPlayers) * i - 20, (int) (this.getHeight() / 1.1) - 30);
		graphic.setColor(Color.YELLOW);
		graphic.drawString(player.getBet() + "$", this.getWidth() / (Controller.NrOfPlayers) * i - 20, (int) (this.getHeight() / 1.1));
		graphic.drawString("Budget: " + player.getBalance() + "$", this.getWidth() / (Controller.NrOfPlayers) * i - 20, (int) (this.getHeight() / 1.1) + 14);
		graphic.setColor(Color.WHITE);
		graphic.drawString("Player " + player.getPlayerID(), this.getWidth() / (Controller.NrOfPlayers) * i - 20, (int) (this.getHeight() / 1.1) + 30);
		graphic.setColor(Color.BLACK);
		
	}
	
}
