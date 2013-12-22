package core;
/*
 * Card Class
 * 
 * Version 0.4
 * 
 * Created 16/03/2012
 * 
 * �2012 This class and the following code is under copyright of the FUB and meant for internal use only.
 */

import java.awt.*;

public class Card {

	private int rank;
	private Image picture;
	
	/**
	 * This method assigns a rank and a image of the card-cover to the card.
	 * 
	 * @param rank An int representing the rank of the card.
	 * @param picturePath A String representing the path of the picture used to represent the cover of the card.
	 */
	public Card(int rank, String picturePath) {
		
		this.rank = rank;
		picture = Toolkit.getDefaultToolkit().getImage(picturePath);
		
	}
	
	/**
	 * This method assigns a rank-value to the card.
	 * 
	 * @param rank An integer representing the rank of the card.
	 */
	public void setRank(int rank) {
		
		this.rank = rank;
		
	}
	
	/**
	 * This method returns the rank of the card.
	 * 
	 * @return An integer representing the rank of the card.
	 */
	public int getRank() {
		
		return rank;
		
	}
	
	/**
	 * This method returns the picture of the card-cover.
	 * 
	 * @return An image representing the card-cover.
	 */
	public Image getPicture() {
		
		return picture;
		
	}
	
}
