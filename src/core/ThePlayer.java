package core;
/*
 * ThePlayer Class
 * 
 * Version 0.4
 * 
 * Created 16/03/2012
 * 
 * �2012 This class and the following code is under copyright of the FUB and meant for internal use only.
 */

import java.util.LinkedList;

public class ThePlayer extends Player{

	private LinkedList<Card> drawedCards;
	private int SumOfCards;
	private int ActualBetAmount;
	public static int IndexCounter = 0;
	private int NumOfCards;
	private Action lastAction;
	
	/**
	 * This method initializes a new object of type ThePlayer which contains a LinkedList with the handcards of the player
	 * and assigns an ID-number and an initial balance to the new player.
	 * 
	 * @param dealer A boolean value representing true in the case that the player is the dealer and false in the case
	 * that the player is not the dealer.
	 */
	public ThePlayer(boolean dealer) {
		
		drawedCards = new LinkedList<Card>();
		SumOfCards = 0;
		setBalance(1000);
		ActualBetAmount = 0;
		IndexCounter++;
		setPlayerID(IndexCounter);
		setDealer(dealer);
		NumOfCards = 0;
		
	}
	
	/**
	 * This method is executed in the case that the player is the dealer and hits till the player has at least a
	 * number of points of 17.
	 */
	public void executesTurn() {
		
		while (getSumOfCards() < 17) {
		    Controller.distributeCards(this);
		}
		Controller.stand();
		
	}
	
	/**
	 * This method adds a card to the handcards of the player.
	 *
	 * @param card An object of type Card which is added to the handcards of the player.
	 */
	public void drawCard(Card card) {
		
		drawedCards.add(card);
		SumOfCards += card.getRank();
		NumOfCards++;
		
	}
	
	/**
	 * This method returns the handcards of the player.
	 *
	 * @return a LinkedList containing objects of type Card which contains the handcards of the player.
	 */
	public LinkedList<Card> getDrawedCards() {
		
		return drawedCards;
		
	}
	
	/**
	 * This method resets the actual bet-amount of the player to 0.
	 */
	public void resetActualBetAmount() {
		
		ActualBetAmount = 0;
		
	}
	
	/**
	 * This method resets the number of points and the number of cards of the player to 0.
	 */
	public void resetSumOfCards() {
		
		SumOfCards = 0;
		NumOfCards = 0;
		
	}
	
	/**
	 * This method assigns a number of points to the player.
	 * 
	 * @param SumOfCards An int-value representing the number of points of the player.
	 */
	public void setSumOfCards(int SumOfCards) {
		
		this.SumOfCards = SumOfCards;
		
	}
	
	/**
	 * Returns the actual number of points of the player.
	 *
	 * @return An integer representing the actual number of points of the player.
	 */
	public int getSumOfCards() {
		
		return SumOfCards;
		
	}
	
	/**
	 * Returns the actual number of Cards of the player.
	 *
	 * @return An integer representing the number of Cards of the player.
	 */
	public int getNumOfCards() {
		
		return NumOfCards;
		
	}
	
	/**
	 * This method assigns a bet amount to the player.
	 * 
	 * @param bet An int-value representing the bet amount of the player.
	 */
	public void setBet(int bet) {
		
		this.ActualBetAmount = bet;
		
	}
	
	/**
	 * Reduces the actual budget of the player by 'diff'.
	 *
	 * @param diff An integer representing the amount to be subtracted from the balance of the player.
	 */
	public void reduceBudget(int diff) {
		
		setSumOfCards(0);
		resetActualBetAmount();
		setBalance(getBalance() - diff);
		
	}
	
	/**
	 * Rises the actual budget of the player by 'diff'.
	 *
	 * @param diff An integer representing the amount to be added to the balance of the player.
	 */
	public void riseBudget(int diff) {
		
		setBalance(getBalance() + diff);
		
	}
	
	@Override
	public void newGame(int numberOfPlayers, int numberOfDecks) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void newRound() {
		// TODO Auto-generated method stub
		
	}

	public void notify(int playerID, Action action, int data) {
		
		lastAction = action;
		if (action == Action.BET) {
			ActualBetAmount = data;
		}
		
	}

	public int getBet() {
		
		return ActualBetAmount;
		
	}

	public Action getAction() {
		
		return lastAction;
		
	}
	
}
