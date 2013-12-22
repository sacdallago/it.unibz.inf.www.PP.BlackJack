package core;
/*
 * Player Class
 * 
 * Version 0.4
 * 
 * Created 16/03/2012
 * 
 * �2012 This class and the following code is under copyright of the FUB and meant for internal use only.
 */


public abstract class Player {

	private int playerID;
	private int money;
	private boolean dealer;
	
	public enum Action { STAND, SURRENDER, DRAW, FIRSTCARD, SECONDCARD, DEALERCARD, BET } 
	
	/**
	 * The framework will call this method whenever a new game is started. This happens
	 * essentially right after the instantiation of the derived class.
	 * 
	 * @param numberOfPlayers Number of players participating in the newly started game.
	 * @param numberOfDecks Number of card decks used to play.
	 */
	public abstract void newGame(int numberOfPlayers, int numberOfDecks);
	
	/**
	 * Convenience method that indicates the start of a new round. Is called by the
	 * framework right before dealing new cards.
	 */
	public abstract void newRound();
	
	/**
	 * Core method to be implemented by derived classes. The framework calls this method
	 * whenever something happens in the game. The method is called for every player for
	 * every action that occurs / is taken by a player.
	 * 
	 * @param playerID ID of the player to whom the action pertains.
	 * @param action The action itself.
	 * @param data If the action has a value (FIRSTCARD, SECONDHARD, DEALERCARD, BET),
	 * this parameter carries the value. If the action does not have a value, this parameter
	 * is set to -1.
	 */
	public abstract void notify(int playerID, Action action, int data);
	
	/**
	 * The framework will call this method once all initial cards are dealt. The return
	 * value indicates the amount of money the player wants to bet.
	 * 
	 * @return Amount to be bet.
	 */
	public abstract int getBet();
	
	/**
	 * The framework will call this method when it is this player's turn to take an action.
	 * 
	 * @return The action to be taken (STAND, SURRENDER, DRAW).
	 */
	public abstract Action getAction();
	
	/**
	 * This method assigns a ID to the player.
	 *
	 * @param A int-value representing the ID of the player.
	 */
	public final void setPlayerID(int playerID) {
		
		this.playerID = playerID;
		
	}
	
	/**
	 * This method returns the ID of the player.
	 * 
	 * @return An int value representing the ID of the player.
	 */
	public final int getPlayerID() {
		
		return playerID;
		
	}
	
	/**
	 * This method assigns a value to the actual budget of the player.
	 *
	 * @param A int-value representing the actual budget of the player.
	 */
	public final void setBalance(int money) {
		
		this.money = money;
		
	}
	
	/**
	 * This method returns the actual budget of the player.
	 * 
	 * @return An int value representing the actual budget of the player.
	 */
	public final int getBalance() {
		
		return money;
		
	}
	
	/**
	 * This method stores the information which tells us if the player is the dealer or not.
	 *
	 * @param A boolean value representing true if the player is the dealer and false if the player is not the dealer.
	 */
	public final void setDealer(boolean dealer) {
		
		this.dealer = dealer;
	}
	
	/**
	 * This method returns the information if the player is the dealer or not.
	 * 
	 * @return A boolean value representing true if the player is the dealer and false if the player is not the dealer.
	 */
	public boolean isDealer() {
		
		return dealer;
		
	}
	
}
