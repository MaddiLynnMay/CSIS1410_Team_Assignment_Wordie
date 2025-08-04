package wordie;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

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

		for (int i = 0; i < guessWord.length(); i++) {
			CharSequence letterChecking = Character.toString(guessWord.charAt(i));
			JLabel label = displayRow[i];
			label.setOpaque(true);

			// check if letter is in the word
			if (actualWord.contains(letterChecking)) {
				// if letters match exactly set green
				if (actualWord.charAt(i) == guessWord.charAt(i)) {
					displayRow[i].setBackground(Color.GREEN);
					displayRow[i].setText(Character.toString(guessWord.charAt(i)));
				}
				// letter must be somewhere else set yellow
				else {
					displayRow[i].setBackground(Color.YELLOW);
					displayRow[i].setText(Character.toString(guessWord.charAt(i)));
				}
			} else {
				displayRow[i].setBackground(Color.GRAY);
				displayRow[i].setText(Character.toString(guessWord.charAt(i)));
			}
		}

		
		int lastGuess = 4;

		if (guessWord.equals(actualWord)) {
			userWinMessage();
		} // if this is the fifth guess, and it isn't correct
		else if (guessCount == lastGuess) {
			userLossMessage(actualWord);
		}

	}

	// check for loss / display loss message
	public void userLossMessage(String actualWord) {
		JOptionPane.showMessageDialog(null, "You lose! The word was: " + actualWord);
	}

	// check for win / display win message
	public void userWinMessage() {
		JOptionPane.showMessageDialog(null, "Great job!");
	}

}
