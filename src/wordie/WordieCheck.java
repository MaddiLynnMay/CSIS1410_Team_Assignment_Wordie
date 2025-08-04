package wordie;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * This class handles the game logic testing checking the guess words against the actual word,
 * displaying that information to the user. 
 * 
 * It also checks that user input words are the correct amount of letters, and handles displaying 
 * win and loss messages to the user
 */
public class WordieCheck {

	/*
	 * Checks if the word the user entered is a viable word if its too long or short
	 * will return false
	 */
	public boolean letterCountCheck(String inputWord) {
		if (inputWord.length() == 5) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Takes in information from the GUI and compares the user guess to the actual
	 * word, displaying the results to the user.
	 * 
	 * letter in userGuess is in the word but in the wrong position -> label turns
	 * yellow letter in userGuess is in the word and in the correct position ->
	 * label turns green letter in userGuess is not in the word -> label turns gray
	 * 
	 * Also checks for a user win or loss and calls the functions if necessary
	 * 
	 * @param guessWord
	 * @param actualWord
	 * @param displayRow
	 * @param guessCount
	 */
	public void compareWords(String guessWord, String actualWord, JLabel[] displayRow, int guessCount) {
		guessWord = guessWord.toUpperCase();
		actualWord = actualWord.toUpperCase();

		//this will keep track of the occurrence of each char in word
		int[] actualWordCharCount = new int[26];
		
		//this lets us keep track of which letters are in the correct place so they do not get turned grey
		int lettersInWord = 5;
		boolean[] greenLetter = new boolean[lettersInWord];
		
		//first we check for letter counts in the actual word
		for(int i=0; i<actualWord.length(); i++) {
			//updates the count in the array at the letter index. Each character starts at a higher int so subtracting 'A' gets us to the correct index
			actualWordCharCount[actualWord.charAt(i) - 'A']++;
		}
		
		//First pass we show all greens this will ensure any left over letter counts are yellow
		for (int i = 0; i < guessWord.length(); i++) {
			//CharSequence letterChecking = Character.toString(guessWord.charAt(i));
			JLabel label = displayRow[i];
			label.setOpaque(true);
			
			//if the chars match in the same location
			if (actualWord.charAt(i) == guessWord.charAt(i)) {
				//displays the correct box
				displayRow[i].setBackground(Color.GREEN);
				displayRow[i].setText(Character.toString(guessWord.charAt(i)));
				
				//save that as a correct letter to protect from being turned grey
				greenLetter[i] = true;
				
				//decrements the number of that letter left in actual word count
				actualWordCharCount[actualWord.charAt(i)- 'A']--;
			}
		}
		
		//On the second pass we handle the remaining yellow letters and set wrong letters grey
		for (int i = 0; i < guessWord.length(); i++) {
			//if the guess word letter is still contained in the letter count we mark it yellow
			if(greenLetter[i] != true) {
				if (actualWordCharCount[guessWord.charAt(i)- 'A']>0) {
					displayRow[i].setBackground(Color.YELLOW);
					displayRow[i].setText(Character.toString(guessWord.charAt(i)));
				
					//decrements the number of that letter left in actual word count
					actualWordCharCount[guessWord.charAt(i) - 'A']--;
				}
				//Remaining letters are not green or yellow so they are grey
				else {
					displayRow[i].setBackground(Color.GRAY);
					displayRow[i].setText(Character.toString(guessWord.charAt(i)));
				}
			}
		}
		
		int lastGuess=4;
		//display win message if the words match exactly
		if (guessWord.equals(actualWord)) {
			userWinMessage();
		} 
		//display the loss message if they reached the last guess and havent won
		else if (guessCount == lastGuess) {
			userLossMessage(actualWord);
		}
		
	}
	

	/**
	 * Displays a loss message and tells the user what the word was
	 * @param actualWord
	 */
	public void userLossMessage(String actualWord) {
		JOptionPane.showMessageDialog(null, "You lose! The word was: " + actualWord);
	}

	/**
	 * Displays the win message
	 */
	public void userWinMessage() {
		JOptionPane.showMessageDialog(null, "Great job!");
	}

}