package core;

import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Output {

	private static String winner;
	private static String draw;
	
	/**
	 * This method prints out the result of the last Game-round (winners of the round) using a Dialog-window.
	 * 
	 * @param WinnerList A LinkedList containing objects of type ThePlayer, which contains the players who won the actual
	 * round of the game.
	 * @param DrawList A LinkedList containing objects of type ThePlayer, which contains the players who reached the
	 * same number of points as the dealer in the actual round of the game.
	 * @param actualRound An int representing the actual round of the game.
	 */
	public static void ResultOutput(LinkedList<ThePlayer> WinnerList, LinkedList<ThePlayer> DrawList, int actualRound) {
		
		printWinner(WinnerList, DrawList, actualRound);
		printDraw(DrawList);
		if (winner == "") {//if the a player won with the same number of points as the dealer
			for (ThePlayer player : DrawList) {
				winner = winner + " Player " + player.getPlayerID() + ", ";
			}
			winner = winner + " and the dealer won the round";
		}
		if (WinnerList.isEmpty() == false) {
			winner += draw;
		}
		Object[] options = {"Start next Round"};
		int choice = JOptionPane.showOptionDialog(null, winner, "End of round " + actualRound, JOptionPane.DEFAULT_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		if (choice == 0) {
			Controller.startNewGame();
		}
		
	}
	
	/**
	 * This method builds the String-object containing all the informations about the winners of the actual round of
	 * the game.
	 * 
	 * @param WinnerList A LinkedList containing objects of type ThePlayer, which contains the players who won the actual
	 * round of the game.
	 * @param DrawList A LinkedList containing objects of type ThePlayer, which contains the players who reached the
	 * same number of points as the dealer in the actual round of the game.
	 * @param actualRound An int representing the actual round of the game.
	 */
	private static void printWinner(LinkedList<ThePlayer> WinnerList, LinkedList<ThePlayer> DrawList, int actualRound) {
		
		winner = "";
		for (ThePlayer player : WinnerList) {
			winner = winner + " Player " + player.getPlayerID() + ", ";
		}
		if (winner != "") {
			winner += "won round " + actualRound + ". ";
		}
		if (WinnerList.isEmpty() && DrawList.isEmpty() && Controller.PlayerList.getLast().getSumOfCards() <=21) {
			winner = "The dealer has won the round";
		}
		
	}
	
	/**
	 * This method builds the String-object containing all the informations about the players who drawed with the dealer
	 * in the actual round of the game.
	 * 
	 * @param DrawList A LinkedList containing objects of type ThePlayer, which contains the players who reached the
	 * same number of points as the dealer in the actual round of the game.
	 */
	private static void printDraw(LinkedList<ThePlayer> DrawList) {
		
		draw = "\n";
		for (ThePlayer player : DrawList) {
			draw = draw + " Player " + player.getPlayerID() + ", ";
		}
		if (draw != "\n") {
			draw += "reached the same number of points as the dealer ";
		}
		
	}
	
}
