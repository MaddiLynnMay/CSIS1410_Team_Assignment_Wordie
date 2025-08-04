package wordie;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * This class loads words from four specific lists. It also randomly selects
 * words from the user-selected topic.
 */
public class SetUp {

	private String selectedWord;
	private Topics selectedTopic;
	private final String basePath = "resources/";

	/**
	 * 
	 * @param topic The topic the user selects (from BIRDS, FOODS, COLORS, CAPITALS)
	 * @throws IOException If the file can't be read, it will throw an exception
	 */
	public void loadTopicWords(Topics topic) throws IOException {
		selectedTopic = topic;
		String fileName = "";
		switch (topic) {
		case BIRDS:
			fileName = "BirdsList.txt";
			break;
		case FOODS:
			fileName = "FoodsList.txt";
			break;
		case COLORS:
			fileName = "ColorsList.txt";
			break;
		case CAPITALS:
			fileName = "CapitalsList.txt";
			break;
		}

		List<String> words = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File(basePath + fileName))) {
			// Chooses only words that are five letters long
			while (scanner.hasNextLine()) {
				String word = scanner.nextLine().trim().toUpperCase();
				if (word.length() == 5) {
					words.add(word);
				}
			}
		}

		// chooses random word
		if (!words.isEmpty()) {
			Random rand = new Random();
			selectedWord = words.get(rand.nextInt(words.size()));
		} else {
			selectedWord = "No word could be chosen";
		}
	}

	/**
	 * Gets a five-letter word from the list
	 * 
	 * @return the chosen word
	 */
	public String getSelectedWord() {
		return selectedWord;
	}

	/**
	 * Gets the chosen topic
	 * 
	 * @return the chosen topic
	 */
	public Topics getSelectedTopic() {
		return selectedTopic;
	}
}
