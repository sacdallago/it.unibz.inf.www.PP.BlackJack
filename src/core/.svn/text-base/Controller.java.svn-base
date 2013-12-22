package core;
/*
 * Controller Class
 * 
 * Version 0.4
 * 
 * Created 16/03/2012
 * 
 * �2012 This class and the following code is under copyright of the FUB and meant for internal use only.
 */

import gui.*;

import java.util.*;
import javax.swing.JOptionPane;

import utility.*;

public class Controller {
	
	private static TablePanel table;
	public static LinkedList<ThePlayer> PlayerList;
	private static CardList FullCardStack;
	public static LinkedList<Card> CardStack;
	private static Random rand;
	public static ThePlayer actualPlayer;
	public static int indexOfActualPlayer;
	private static LinkedList<ThePlayer> WinnerList;
	private static LinkedList<ThePlayer> DrawList;
	public static int NrOfPlayers;
	public static int actualRound;
	public static boolean ai;
	private static AiController aiController;
	
	/**
	 * This method initializes a new Controller-object which contains a LinkedList with the players, a LinkedList with
	 * all the existing cards, a LinkedList with the actual cards on the deck, a LinkedList with the players who won 
	 * the last round, a LinkedList with the players who reached a draw with the dealer and initializes a new game. 
	 *
	 * @param table	A TablePanel Object representing the graphical interface where the whole game is drawn on.
	 * @param NrOfPlayers	An int representing the number of players.
	 * @param ai A boolean representing true in case of a single vs. ai game and false in case of a multiplayer game.
	 */
	public Controller(TablePanel table, int NrOfPlayers, boolean ai) {
		
		BlackJack.save.setEnabled(true);
		this.table = table;
		PlayerList = new LinkedList<ThePlayer>();
		FullCardStack = new CardList();
		CardStack = FullCardStack.fillCardList();
		this.ai = ai;
		createPlayers(NrOfPlayers);
		this.NrOfPlayers = NrOfPlayers;
		rand = new Random();
		WinnerList = new LinkedList<ThePlayer>();
		DrawList = new LinkedList<ThePlayer>();
		actualRound = 0;
		aiController = new AiController();
		startNewGame();
		
	}
	
	/**
	 * This method fills the list of players of size NrOfPlayers.
	 *
	 * @param NrOfPlayers	An int-value representing the number of players.
	 */
	private void createPlayers(int NrOfPlayers) {
		
		ThePlayer.IndexCounter = 0;
		for (int q=0; q<NrOfPlayers - 1; q++) {
			if (q == 0 || ai == false) {
				PlayerList.add(new ThePlayer(false));
			} else {
				PlayerList.add(new Intelligence(false));
			}
		}
		if (ai == false) {
			PlayerList.add(new ThePlayer(true));
		} else {
			PlayerList.add(new Intelligence(true));
		}
		
	}
	
	/**
	 * This method distributes a card from the CardStack to the player and removes the given card from the 'CardStack'.
	 *
	 * @param player An Object of type Player who gets the card.
	 */
	public static void distributeCards(ThePlayer player) {
		
		rand = new Random();
		int q = rand.nextInt(CardStack.size() - 1);
		player.drawCard(CardStack.get(q));
		CardStack.remove(q);
		table.paintCards(PlayerList);
		
	}
	
	/**
	 * This method removes the cards from the player if he busted and adds them again to the 'CardStack'.
	 * In the case that the player surrendered, the method works in the nearly same way, with the only difference, 
	 * that the budget of the player rises by the half of the amount he bet.
	 *
	 * @param player A Object of the type Player who busted or surrendered.
	 */
	private static void removeCards(ThePlayer player) {
		
		for (Card card : player.getDrawedCards()) {
			CardStack.add(card);
		}
		player.getDrawedCards().clear();
		if (player.getAction() == ThePlayer.Action.SURRENDER) {
			player.riseBudget(player.getBet() / 2);
		} else { //if the player busted
			actualPlayer.resetActualBetAmount();
			actualPlayer.setSumOfCards(0);
			nextPlayer();
		}
		table.paintCards(PlayerList);
		
	}
	
	/**
	 * This method checks the last move of the actual player (if the player hits, stands, surrenders or bets) and 
	 * executes the consequent actions.
	 *
	 * @param lastEvent	An integer representing the move of the actual player (1...player hits, 2...player stands, 
	 * 3...player surrenders, 4...player bets).
	 */
	public static void playGame(int lastEvent) {
		
		if (lastEvent == 1) {
			actualPlayer.notify(actualPlayer.getPlayerID(), ThePlayer.Action.DRAW, 0);
			distributeCards(actualPlayer);
			if (bust(actualPlayer) == true) {
				lastEvent = 2;
				removeCards(actualPlayer);
			}
		}
		else if (lastEvent == 2) {
			stand();
		}
		else if (lastEvent == 3) {
			surrender();
		}
		else if (lastEvent == 4) {
			bet();
		}
		if (lastEvent > 4) {
			actualPlayer.notify(actualPlayer.getPlayerID(), ThePlayer.Action.BET, lastEvent);
		}
		
	}
	
	/**
	 * This method is used to execute the consequent actions when the actual player decides to stand.
	 */
	public static void stand() {
		
		actualPlayer.notify(actualPlayer.getPlayerID(), ThePlayer.Action.STAND, 0);
		nextPlayer();
		
	}

	/**
	 * This method is used to execute the consequent actions when the actual player decides to surrender.
	 */
	private static void surrender() {
		
		actualPlayer.notify(actualPlayer.getPlayerID(), ThePlayer.Action.SURRENDER, 0);
		removeCards(actualPlayer);
		actualPlayer.resetActualBetAmount();
		actualPlayer.setSumOfCards(0);
		nextPlayer();
		
	}

	/**
	 * This method is used to execute the consequent actions when the actual player decides to bet a certain amount of
	 * money.
	 */
	private static void bet() {
		
		actualPlayer.notify(actualPlayer.getPlayerID(), ThePlayer.Action.BET, ButtonPanel.betSlider.getValue());
		actualPlayer.setBalance(actualPlayer.getBalance() - actualPlayer.getBet());
		ButtonPanel.betSlider.setMaximum(actualPlayer.getBalance());
		ButtonPanel.betSlider.setValue(0);
		ButtonPanel.enableButtons(1);
		table.paintCards(PlayerList);
		
	}
	
	/**
	 * This method prepares the next player for his turn.
	 */
	private static void nextPlayer() {
		
		indexOfActualPlayer++;
		ButtonPanel.betSlider.setValue(0);
		table.paintArrow(indexOfActualPlayer);
		ButtonPanel.enableButtons(0);
		if (indexOfActualPlayer != PlayerList.size()) {// if the round is not finished
			actualPlayer = PlayerList.get(indexOfActualPlayer);
			ButtonPanel.betSlider.setMaximum(actualPlayer.getBalance());
			if (indexOfActualPlayer == PlayerList.size() - 1) {// if the actual player is the dealer
				ButtonPanel.enableButtons(3);
			}
			if (ai == true && actualPlayer.getPlayerID() != 1) {//if the actual player is the AI (not Player 1)
				ButtonPanel.enableButtons(2);
				aiController.intelligencePlay();
			} else {//if the actual player is Player 1
				aiController.timer.stop();
			}
		} else {//if the round is finished
			aiController.timer.stop();
			checkWinner();
		}
		
	}
	
	/**
	 * At the end of each round, this method checks which players has won the round, adds them to a 'WinnerList'
	 * and gives those players their money. In the case that some players have the same number of points as the dealer,
	 * they are added to the 'DrawList'.
	 */
	private static void checkWinner() {
		
		WinnerList.clear();
		DrawList.clear();
		for (ThePlayer player : PlayerList) {
			if (player.getSumOfCards() > PlayerList.getLast().getSumOfCards() || 
					(player.getSumOfCards() > 0 && player.getSumOfCards() <= 21) && PlayerList.getLast().getSumOfCards() > 21 &&
					player.isDealer() == false) {
				player.riseBudget(player.getBet() * 2);
				WinnerList.add(player);
			}
			else if (player.getSumOfCards() == PlayerList.getLast().getSumOfCards() && player.isDealer() == false) {
				DrawList.add(player);
			}
		}
		Output.ResultOutput(WinnerList, DrawList, actualRound);
		
	}
	
	/**
	 * This method checks if the player busted or not.
	 *
	 * @param player An Object of type player which will be checked if he busted.
	 * 
	 * @return Returns a boolean representing true if the player busted and false if the player did not bust.
	 */
	public static boolean bust(ThePlayer player) {
		
		if (player.getSumOfCards() > 21) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * This method is used to prepare a new round of the game.
	 */
	public static void startNewGame() {
		
		if (actualRound > 0) {
			movePlayerList();
		}
		ButtonPanel.betSlider.setValue(0);
		for (ThePlayer player : PlayerList) {
			for (Card card : player.getDrawedCards()) {
				CardStack.add(card);
			}
			player.getDrawedCards().clear();
		}
		for (ThePlayer player : PlayerList) {
			player.resetSumOfCards();
			player.resetActualBetAmount();
			distributeCards(player);
			if (player.isDealer() == false) {
				distributeCards(player);
			}
		}
		actualPlayer = PlayerList.get(0);
		indexOfActualPlayer = 0;
		table.paintArrow(indexOfActualPlayer);
		ButtonPanel.betSlider.setMaximum(actualPlayer.getBalance());
		ButtonPanel.enableButtons(0);
		actualRound++;
		if (ai == true && actualPlayer.getPlayerID() != 1) {
			AiController.intelligencePlay();
			ButtonPanel.enableButtons(2);
		}
		
	}
	
	/**
	 * This method prepares the 'PlayerList' at the beginning of each new round by shifting it by one position.
	 */
	private static void movePlayerList() {
		
		ThePlayer player = PlayerList.pollLast();
		PlayerList.addFirst(player);
		PlayerList.getLast().setDealer(true);
		PlayerList.getFirst().setDealer(false);
		
	}
	
}
