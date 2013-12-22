package core;

public class Intelligence extends ThePlayer{
	
	private int handStackDiff;
	private int positiveCards;
	
	/**
	 * This method initializes a new Intelligence-object by initializing its superclass 'ThePlayer'.
	 * 
	 * @param dealer A boolean which informs the superclass 'ThePlayer' if the Intelligence-player is the dealer (true) 
	 * or not (false).
	 */
	public Intelligence(boolean dealer) {
		
		super(dealer);
		
	}
	
	/**
	 * This method executes the turn of the ai-player by checking the actually most appropiate action to execute.
	 */
	public void executesTurn() {
		
		handStackDiff = 21 - getSumOfCards();
		positiveCards = 0;
		for (Card card : Controller.CardStack) {
			if (card.getRank() <= handStackDiff) {
				positiveCards++;
			}
		}
		notify(getPlayerID(), Action.BET, checkBet());
		setBalance(getBalance() - getBet());
		if (checkHit() == true) {
			notify(getPlayerID(), Action.DRAW, -1);
		} else {
			if (checkSurrender() == true) {
				notify(getPlayerID(), Action.SURRENDER, -1);
			} else {
				notify(getPlayerID(), Action.STAND, -1);
			}
		}
		
	}
	
	/**
	 * This method checks if the ai-player should hit or not.
	 * 
	 * @return A boolean returning 'true' in the case that the ai-player should hit and 'false' in the case that the
	 * ai-player should not hit.
	 */
	private boolean checkHit() {
		
		if (positiveCards > Controller.CardStack.size() / 2) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * This method calculates the amount which the ai-player should bet.
	 * 
	 * @return An int value representing the amount which the ai-player wants to bet.
	 */
	private int checkBet() {
		
		if (handStackDiff == 0) {
			handStackDiff = 1;
		}
		else if (handStackDiff == 1) {
			handStackDiff = 2;
		}
		return getBalance() / handStackDiff;
		
	}
	
	/**
	 * This method checks if the ai-player should surrender or not.
	 * 
	 * @return A boolean returning 'true' in the case that the ai-player should surrender and 'false' in the case that
	 * the ai-player should just stand.
	 */
	private boolean checkSurrender() {
		
		if (handStackDiff <= 6 && handStackDiff >= 4) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
