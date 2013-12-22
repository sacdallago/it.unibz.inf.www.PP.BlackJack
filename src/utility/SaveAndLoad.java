package utility;

/*
 * SaveAndLoad Class
 * 
 * Version 0.4
 * 
 * Created 09/04/2012
 * 
 * �2012 This class and the following code is under copyright of the FUB and meant for internal use only.
 */

import gui.*;
import core.*;

import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class SaveAndLoad{
	
	public static JFileChooser savedGame;
	public static File savedFile;
	public static File loadedFile;
	private static int result;

	/**
	 * Saves the status of a game in a ".dat" file using a specified name.
	 * The default path is set to a folder named "savedGames" in the same folder where this class is stored.
	 * This method is used in the main class, when selecting "save game" or "exit game".
	 * 
	 * @see BlackJack
	 */
	public static void save(){
		
		Date date = new Date(System.currentTimeMillis());
        String fileName = (String) JOptionPane.showInputDialog(BlackJack.getFrame(), "Title the saved game:", "Save", JOptionPane.PLAIN_MESSAGE, null, null, date.toLocaleString());
		if (fileName != null) {
			save(fileName);
		}
		
	}
	
	/**
	 * Saves the status of a game in a ".dat" file using a specified name.
	 * The default path is set to a folder named "savedGames" in the same folder where this class is stored.
	 * This method is used in the main class, when selecting "save game" or "exit game".
	 * @see BlackJack
	 *
	 * @param fileName  The name which will be used to store the file.
	 */
	public static void save(String fileName) {
		
		savedFile = new File("savedGames/" + fileName + ".dat");
		
		try {

			FileWriter fstream = new FileWriter(savedFile, true);
			BufferedWriter out = new BufferedWriter(fstream);
			
			String[] data = new String[10];  // 10 is just a temp value

			data[0] = Integer.toString(Controller.NrOfPlayers);
			data[1] = Integer.toString(Controller.indexOfActualPlayer);
			data[2] = Integer.toString(Controller.PlayerList.getFirst().getPlayerID()); //Exception
			data[3] = Boolean.toString(Controller.ai);
			data[4] = Integer.toString(Controller.actualRound);
			data[5] = "";
			data[6] = "";
			data[7] = "";
			
			for(ThePlayer p : Controller.PlayerList){
				data[5] += p.getBalance() + " ";
				data[6] += p.getBet() + " ";
				data[7] += p.getSumOfCards() + " ";
			}
			
			for (String dataToSave : data) {
				out.write(dataToSave + "\n");
			}

			out.close();

		} catch (IOException ex) {

			ex.printStackTrace();

		}
	}

	/**
	 * Loads the status of a game from a ".dat".
	 * The default path is set to a folder named "savedGames" in the same folder where this class is stored;
	 * If this folder is not existent, the user disposes of a file chooser to locate a saved game.
	 * This method is used in the main class, when selecting "load saved game".
	 * 
	 * @param file The file to be loaded from.
	 */
	public static void load(File file) {
		
		loadedFile = file;
		
		try {
			
			BlackJack.setUpGame();
			Scanner scan = new Scanner(loadedFile);
			String[] data = new String[10];  // 10 is just a temp value
			int position = 0;
			
			while (scan.hasNextLine()) {
	            data[position]= scan.nextLine();
	            position++;
	        }
			
			int NrOfPlayers = Integer.parseInt(data[0]);
			int indexOfActualPlayer = Integer.parseInt(data[1]);
			int firstPlayerID = Integer.parseInt(data[2]);
			boolean isVsAi = Boolean.parseBoolean(data[3]);
			int actualRound = Integer.parseInt(data[4]);
			
			new Controller(BlackJack.tablePane, NrOfPlayers, isVsAi);
			
			while (firstPlayerID != Controller.PlayerList.getFirst().getPlayerID()) {
				ThePlayer player = Controller.PlayerList.pollLast();
				Controller.PlayerList.addFirst(player);
				Controller.PlayerList.getLast().setDealer(true);
				Controller.PlayerList.getFirst().setDealer(false);
			}
			Controller.indexOfActualPlayer = indexOfActualPlayer;
			BlackJack.tablePane.paintArrow(indexOfActualPlayer);
			
			StringTokenizer tk = new StringTokenizer(data[5]);
			position = 0;
			
	        while (tk.hasMoreTokens()) {
	            int balance = Integer.parseInt(tk.nextToken());
	            Controller.PlayerList.get(position).setBalance(balance);
	            position++;
	        }
	        
	        tk = new StringTokenizer(data[6]);
			position = 0;

	        while (tk.hasMoreTokens()) {
	            int betAmount = Integer.parseInt(tk.nextToken());
	            Controller.PlayerList.get(position).setBet(betAmount);
	            position++;
	        }
			
	        tk = new StringTokenizer(data[7]);
			position = 0;

	        while (tk.hasMoreTokens()) {
	            int sumOfCards = Integer.parseInt(tk.nextToken());
	            Controller.PlayerList.get(position).setSumOfCards(sumOfCards);
	            position++;
	        }
	        
	        for (ThePlayer player : Controller.PlayerList) {
				if (player.getSumOfCards() == 0) {
					player.getDrawedCards().clear();
				}
			}
			
		} catch (Exception ex){
			
			ex.printStackTrace();
			
		}
		
	}
	
	/**
	 * Loads the status of a game from a ".dat" file using a file chooser.
	 * The default path is set to a folder named "savedGames" in the same folder where this class is stored;
	 * If this folder is not existent, the user disposes of a file chooser to locate a saved game.
	 * This method is used in the main class, when selecting "load saved game".
	 */
	public static void load() {

		savedGame = new JFileChooser(new File("savedGames/"));
		savedGame.setFileSelectionMode(JFileChooser.FILES_ONLY);
		savedGame.setAcceptAllFileFilterUsed(false);
		savedGame.addChoosableFileFilter(new MyFilter());
		
		int returnVal = savedGame.showOpenDialog(BlackJack.getFrame()); // result is = 1 if "cancel" button is pressed, or equal 0 if a file is selected
		
		if (returnVal == JFileChooser.APPROVE_OPTION){
			
			loadedFile = savedGame.getSelectedFile();
			System.out.println("choosen!" + loadedFile);
			load(loadedFile);

		}
	}
		
	private static class MyFilter extends javax.swing.filechooser.FileFilter {
		
	    public boolean accept(File file) {
	    	
	        String filename = file.getName();
	        return filename.endsWith(".dat");
	        
	    }
	    
	    public String getDescription() {
	    	
	        return "Svaed games of type *.dat";
	        
	    }
	}
}
