package core;

import java.awt.event.*;
import javax.swing.Timer;

import core.Player.Action;

public class AiController {

	public static Timer timer;
	private final int DELAY = 2000;
	
	/**
	 * This method initializes a new object of type Timer.
	 */
	public AiController() {
		
		timer = new Timer(DELAY, new TaskPerformer());
		
	}
	
	/**
	 * This method starts the timer which executes the ActionListener TaskPerformer every two seconds.
	 */
	public static void intelligencePlay() {
		
		timer.start();
		
	}
	
	private class TaskPerformer implements ActionListener{
		
		public void actionPerformed(ActionEvent event){
			
			Controller.actualPlayer.executesTurn();
			if (Controller.actualPlayer.getAction() == Action.DRAW) {
				Controller.playGame(1);
			} else if (Controller.actualPlayer.getAction() == Action.STAND) {
				Controller.playGame(2);
			} else if (Controller.actualPlayer.getAction() == Action.SURRENDER) {
				Controller.playGame(3);
			}
			
		}
		
	}
	
}